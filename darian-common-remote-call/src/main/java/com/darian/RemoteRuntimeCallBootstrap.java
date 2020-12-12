package com.darian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/12  9:50
 */
@SpringBootApplication
public class RemoteRuntimeCallBootstrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RemoteRuntimeCallBootstrap.class);


        springApplication.run(args);

        System.out.println("http://localhost:8080/searchClassesList?name=darian");
        System.out.println("http://localhost:8080/searchMethodsList?className=com.darian.service.RemoteRuntimeCallApi");
    }
}
