package com.ichong.job;

import com.ichong.annotation.XxlRegister;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ClassName: DataSyncJob
 * Description:数据同步任务
 *
 * @author 陈高文
 * @date 2023/5/5 14:43
 */
@Component
@Slf4j
public class DataSyncJob {

    @XxlJob("dataSyncJob")
    @XxlRegister(jobDesc = "数据同步任务")
    public void dataSyncJob() {
        for (int i = 0; i < 10; i++) {
            log.info("数据同步执行次数：{}", i);
        }
    }
}
