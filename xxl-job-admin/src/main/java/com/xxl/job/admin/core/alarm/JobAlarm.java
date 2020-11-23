package com.xxl.job.admin.core.alarm;

import com.xxl.job.admin.jpaCode.model.XxlJobInfoEntity;
import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfoEntity info, XxlJobLogEntity jobLog);

}
