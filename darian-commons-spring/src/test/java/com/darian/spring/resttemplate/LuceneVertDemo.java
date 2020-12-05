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
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2020/11/15  22:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuceneVertDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(LuceneVertDemo.class);

    static int executeCount = 1000;

    static int urlListSize = 100;

    static String token_value = "darian";

    static String Referer_value = "http://localhost";

    static String host_name = "http://localhost:9999";

    //static String host_name = "https://www.darian.top";

    // 3 / 4
    static List<String> urlList;

    static {
        urlList = Arrays.asList(
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll",
                "/search?cache=true&parm=cat%20ll",
                "/search?cache=true&parm=ll%20ll",
                "/search?cache=true&parm=clear%20ll",
                "/search?cache=true&parm=man%20ll");
        urlList = new ArrayList<>(urlList);
    }

    static AtomicLong failCountAtomicLong = new AtomicLong(0);

    @Test
    public void executeOne() {
        postDemo(host_name + urlList.get(0));
    }

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

        headers.put("token", ofList(token_value));

        headers.put("Cookie", ofList("token=" + token_value));

        headers.put("Referer", ofList(Referer_value));

        headers.put("User-Agent",
                ofList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537"
                        + ".36"));

        HttpEntity<String> request = new HttpEntity<String>("{}", headers);

        ResponseEntity<ResponseT> response = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, request, ResponseT.class);
        } catch (RestClientException e) {
            LOGGER.error("调用异常: " + e.getLocalizedMessage());
            failCountAtomicLong.incrementAndGet();
            return;
        }
        int statusCodeValue = response.getStatusCodeValue();
        Assert.isTrue(statusCodeValue == 200, "http 接口报错");
        if (!"200".equalsIgnoreCase(response.getBody().getCode())) {
            LOGGER.error("业务报错");
            LOGGER.error(response.getBody().getNotifyMsg());
            return;
        }

        long end = System.currentTimeMillis();
        long cost_s = (end - start) / 1000;
        long cost_ms = (end - start) % 1000;

        String thisCostString = +cost_s + " s " + cost_ms + " ms";

        LOGGER.info("success: [ " + thisCostString + " ]");
    }

    public static class ResponseT<T> {
        private String code;

        private String notifyMsg;

        private T dataBody;

        private String time;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getNotifyMsg() {
            return notifyMsg;
        }

        public void setNotifyMsg(String notifyMsg) {
            this.notifyMsg = notifyMsg;
        }

        public T getDataBody() {
            return dataBody;
        }

        public void setDataBody(T dataBody) {
            this.dataBody = dataBody;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getApplicationStartTime() {
            return applicationStartTime;
        }

        public void setApplicationStartTime(String applicationStartTime) {
            this.applicationStartTime = applicationStartTime;
        }

        /**
         * 系统启动时间
         */
        private String applicationStartTime;

        @Override
        public String toString() {
            return "ResponseT{" +
                    "code='" + code + '\'' +
                    ", notifyMsg='" + notifyMsg + '\'' +
                    ", dataBody=" + dataBody +
                    ", time=" + time +
                    ", applicationStartTime='" + applicationStartTime + '\'' +
                    '}';
        }
    }

    public static List<String> ofList(String... parm) {
        List<String> list = new ArrayList<>();
        for (String s : parm) {
            list.add(s);
        }
        return list;
    }
}
