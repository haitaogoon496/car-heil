package com.mljr.heil.component;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.util.BeanMapper;
import com.mljr.heil.document.BaseDocument;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import com.mongodb.WriteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description: mongodb
 * @Date : 2019/3/27 下午4:28
 * @Author : 石冬冬-Seig Heil
 */
@Component
@Slf4j
public class MongodbComponent {
    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * 新增
     * @param doc 文档对象
     */
    public void insert(BaseDocument doc){
        insert(doc,doc.getCollectionName());
    }

    /**
     * 新增
     * @param doc 文档对象
     * @param collectionName 集合名称
     */
    public void insert(BaseDocument doc,String collectionName){
        try {
            doc.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime()));
            String prefix = Optional.of(doc.getIdPrefix()).orElse("MG");
            String suffix = TimeTools.format(TimeTools.createNowTime(),"yyyyMMddHHmmssSSS");
            doc.setId(MessageFormat.format("{0}{1}",prefix,suffix));
            if(null != collectionName){
                mongoTemplate.insert(doc,collectionName);
            }else{
                mongoTemplate.insert(doc);
            }
        } catch (Exception e) {
            log.error("mongodb insert exception,doc={}", JSON.toJSONString(doc),e);
        }
    }

    /**
     * 删除
     * @param doc
     * @return
     */
    public boolean remove(BaseDocument doc){
        WriteResult writeResult = null;
        try {
            writeResult =  mongoTemplate.remove(buildQuery(doc),doc.getCollectionName());
        } catch (RuntimeException e) {
            log.error("mongodb remove exception,doc={}", JSON.toJSONString(doc),e);
        }
        return writeResult.getN() > 0;
    }

    /**
     * 查询
     * @param doc
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> find(BaseDocument doc, Class<T> clazz){
        return mongoTemplate.find(buildQuery(doc),clazz);
    }
    /**
     * 分页查询
     * @param pageForm
     * @param clazz
     * @return
     */
    public <T> PageVO<List<T>> findByPaging(PageForm<BaseDocument> pageForm, Class<T> clazz){
        List<T> list = Collections.emptyList();
        int count = 0;
        try {
            BaseDocument params = pageForm.getForm();
            int start = pageForm.getStart();
            int limit = pageForm.getLimit();
            Query query = buildQuery(params);
            query.skip((start - 1) * limit);
            query.limit(limit);
            list = mongoTemplate.find(query,clazz);
            count = (int)mongoTemplate.count(query,clazz);
        } catch (Exception e) {
            log.error("mongodb findByPaging exception,doc={}", JSON.toJSONString(pageForm),e);
        }
        return PageVO.newInstance(count,list);
    }
    /**
     * 构造查询条件
     * @param doc
     * @return
     */
    Query buildQuery(BaseDocument doc){
        Query query = new Query();
        Criteria criteria = new Criteria();
        Map<String, Object> mapping = BeanMapper.objectToMap(doc);
        if(null != mapping && !mapping.isEmpty()){
            mapping.entrySet().stream().filter(entry -> null != entry.getValue() && StringTools.isNotEmpty(entry.getValue().toString()))
                    .forEach(entry -> criteria.and(entry.getKey()).is(entry.getValue()));
        }
        query.addCriteria(criteria);
        log.info("mongodb find query={}",query.toString());
        return query;
    }
}
