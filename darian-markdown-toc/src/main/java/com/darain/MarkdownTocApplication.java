package com.darain;

import com.darain.files.GeneratorFromFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/1/18  16:38
 */
@SpringBootApplication
public class MarkdownTocApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarkdownTocApplication.class, args);

        System.out.println(System.getProperty("WRITE_TO_FILE"));
    }
}
