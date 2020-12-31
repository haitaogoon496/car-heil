package com.mljr.heil.common.exception;

import com.lyqc.base.common.Result;
import com.mljr.heil.common.consts.HeilCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.exceptions.JedisException;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午10:40 2018/1/28
 */
@ControllerAdvice
public class ExceptionInterceptor {

	private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    @ExceptionHandler({AlertException.class})
    @ResponseBody
    Result<Object> handleException(AlertException e, HttpServletRequest request) {

        StringBuilder buffer = new StringBuilder(512);
        buffer.append(e.getClass()).append(": ").append(e.getMessage());
        for (StackTraceElement stack : e.getStackTrace()) {
            if (stack.getClassName().startsWith("com.mljr")) {
                buffer.append("\n\t").append("at ").append(stack.toString());
            }
        }
        log.warn("IP:{} {}-{} RESP:{}-{} CALL:{}", request.getAttribute("ip"), request.getMethod(), request.getRequestURI(), e.getCode(), e.getMessage(), buffer);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = JedisException.class)
    Result<Object> handleException(JedisException e, HttpServletRequest request) {
        log.error("redis 异常 - message:{},user:{}", e.getMessage(),request.getSession().getAttribute("user"));
        return Result.fail(HeilCode.E_400, "redis异常,请稍后重试~");
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    Result<Object> handleException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error("{} {} {}", request.getMethod(), request.getRequestURI(), e.getMessage());
        return Result.fail(HeilCode.E_400, e.getMessage());
    }

    @ExceptionHandler(org.springframework.beans.TypeMismatchException.class)
    @ResponseBody
    Result<Object> handleException(org.springframework.beans.TypeMismatchException e, HttpServletRequest request) {
        log.warn("{} {} {}", request.getMethod(), request.getRequestURI(), e.getMessage());
        return Result.fail(HeilCode.E_400, "参数类型错误:" + e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    Result<Object> handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        log.error(request.getMethod() + " " + request.getRequestURI() + " Message:{}", e.getMessage());
        return Result.fail(HeilCode.E_400, "请勿重复操作!");
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    Result<Object> handleException(NullPointerException e, HttpServletRequest request) {
        String ip = (String) request.getAttribute("ip");
        log.error("空指针异常 IP:{} {} - {}", request.getMethod(), request.getRequestURI(), e);
        //TODO 空指针异常发送邮件
        return Result.fail(HeilCode.E_500, "系统处理异常，请稍后重试!");
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
        log.error("exception - msg:{}", ex.getMessage(),ex);
        if(ex instanceof BizException){
            BizException bussException = (BizException)ex;
            return Result.failInBusiness(bussException.getMsg());
        }
        if(ex instanceof RuntimeException){
            return Result.failInRuntime(ex.getMessage());
        }
        return Result.failInServer(ex.getMessage());
    }

}
