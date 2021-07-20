package com.darian.aop.logger.util.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestTemplateDemo {

    public static void main(String[] args) {
        postDemo();
    }

    public static void postDemo() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://gper.club/server-api/collection/addCollections?userId=175&sourceId=1281" ;
        HttpHeaders headers = new HttpHeaders();

        headers.put("Accept", ofList("*/*"));
        headers.put("Accept-Encoding", ofList("gzip", "deflate", "br"));
        headers.put("Accept-Language", ofList("zh-CN", "zh;q=0.9"));
        headers.put("accessToken", ofList(""));
        headers.put("Connection", ofList("keep-alive"));
        headers.put("Content-Length", ofList("0"));
        
        headers.put("Host", ofList("gper.club"));
        headers.put("Origin", ofList("https://gper.club"));
        headers.put("Referer", ofList("https://gper.club/articles/7e7e7f7ff7g5fgccg64"));
        headers.put("Sec-Fetch-Mode", ofList("cors"));

        headers.put("Sec-Fetch-Site", ofList("same-origin"));
        headers.put("User-Agent", ofList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();


        map.add("userId", "175");
        map.add("sourceId", "1281");
        map.add("sourceType", "App\\Models\\Article");
        map.add("rand", "");
        map.add("appKey", "");
        map.add("appSecret", "");
        map.add("timestamp", "");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
    }


    public static List<String> ofList(String... parm) {
        List<String> list = new ArrayList<>();
        for (String s : parm) {
            list.add(s);
        }
        return list;
    }

}
