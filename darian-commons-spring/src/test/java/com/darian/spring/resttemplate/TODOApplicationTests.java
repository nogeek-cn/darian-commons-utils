package com.darian.spring.resttemplate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TODOApplicationTests {


    private static Logger LOGGER = LoggerFactory.getLogger(LuceneVertDemo.class);

    static int executeCount = 100;

    static int urlListSize = 500;

    static String host_name = "http://127.0.0.1:8082";

    // 3 / 4
    static List<String> urlList = Arrays.asList(
            "/todos");

    static AtomicLong failCountAtomicLong = new AtomicLong(0);

    public static ThreadPoolTaskExecutor rangeCourseProcessorThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPoolTaskExecutor.setCorePoolSize(150);
        // 设置最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(150);
        // 设置队列容量
        threadPoolTaskExecutor.setQueueCapacity(1000000);
        // 设置线程活跃时间（秒）
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        threadPoolTaskExecutor.setThreadNamePrefix("Test-");
        // 设置拒绝策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolTaskExecutor;
    }

    @Test
    public void execute_most() {
        long start = System.currentTimeMillis();
        urlList = new ArrayList<>(urlList);
        while (urlList.size() < urlListSize) {
            urlList.addAll(urlList);
        }
        urlList = urlList.subList(0, urlListSize);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = rangeCourseProcessorThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();

        for (int i = 0; i < executeCount; i++) {

            LOGGER.info("" + urlList.size());

            CountDownLatch countDownLatch = new CountDownLatch(urlList.size());
            for (String url : urlList) {
                threadPoolTaskExecutor.submit(() -> {
                    try {
                        postDemo(host_name + url);
                    } catch (Exception e) {
                        LOGGER.error("", e);
                    } finally {
                        countDownLatch.countDown();
                    }
                });

                //                LOGGER.info("[" + i + "][" + url + "]");
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                LOGGER.error("", e);
            }

            LOGGER.info("[" + i + "]");

        }

        long failCount = failCountAtomicLong.get();
        LOGGER.info("failCount: : " + failCount);
        int allCount = executeCount * urlList.size();
        LOGGER.info("allCount: " + allCount);
        LOGGER.info("successCount: " + (allCount - failCount));

        long end = System.currentTimeMillis();
        long cost_s = (end - start) / 1000;
        long cost_ms = (end - start) % 1000;

        String thisCostString = +cost_s + " s " + cost_ms + " ms";
        LOGGER.info("thisCostString: " + (thisCostString));

        threadPoolTaskExecutor.shutdown();
    }

    public static void postDemo(String uri) {
        long start = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<String>("{}", headers);

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        } catch (RestClientException e) {
            LOGGER.error("调用异常: " + e.getLocalizedMessage());
            failCountAtomicLong.incrementAndGet();
            return;
        }
        int statusCodeValue = response.getStatusCodeValue();
        Assert.isTrue(statusCodeValue == 200, "http 接口报错");


        long end = System.currentTimeMillis();
        long cost_s = (end - start) / 1000;
        long cost_ms = (end - start) % 1000;

        String thisCostString = +cost_s + " s " + cost_ms + " ms";

        LOGGER.info("success: [ " + thisCostString + " ]");
    }

}
