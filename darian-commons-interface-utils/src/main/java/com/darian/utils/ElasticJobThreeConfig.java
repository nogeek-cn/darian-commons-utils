package com.darian;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElasticJobThreeConfig {

    /**
     * split层捞数据条数
     */
    private int splitLoadSize = 10;

    /**
     * 捞数据随机休息毫秒数
     */
    private long loaderRandomSleep = 0;

    /**
     * loader层捞数据条数
     */
    private int loaderLoadSize = 10;

    /**
     * 打包下发条数
     */
    private int loaderPackSize = 1;

    /**
     * 执行速率
     */
    private double executorRateLimit = 1000;

    /**
     * 限流控制器
     */
    private RateLimiter executorRateLimiter = RateLimiter.create(executorRateLimit);

    public ElasticJobThreeConfig(long loaderRandomSleep, int loaderLoadSize, int splitLoadSize,
                                 int loaderPackSize, double executorRateLimit) {
        this.loaderRandomSleep = loaderRandomSleep;
        this.loaderLoadSize = loaderLoadSize;
        this.splitLoadSize = splitLoadSize;
        this.loaderPackSize = loaderPackSize;
        this.executorRateLimit = executorRateLimit;
        if (executorRateLimiter == null) {
            executorRateLimiter = RateLimiter.create(executorRateLimit);
        } else {
            executorRateLimiter.setRate(executorRateLimit);
        }
    }

    public ElasticJobThreeConfig() {
    }

    public void setExecutorRateLimit(double executorRateLimit) {
        if (executorRateLimiter == null) {
            this.executorRateLimiter = RateLimiter.create(executorRateLimit);
        } else {
            this.executorRateLimiter.setRate(executorRateLimit);
        }
        this.executorRateLimit = executorRateLimit;
    }
}