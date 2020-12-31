package com.mljr.heil.common.IdGen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午10:40 2018/1/28
 */
public class UniqueID {
    private static Logger logger = LoggerFactory.getLogger(UniqueID.class);
    private static IdWorker idWorder;

    public UniqueID() {
    }

    public static synchronized long generateId() {
        return idWorder.nextId();
    }

    static {
        int machineNo = Integer.parseInt(LocalHostUtil.getLastSegment());
        idWorder = new IdWorker((long)machineNo);
        logger.info("当前运行机器LastSegmentIP:" + machineNo);
    }
}
