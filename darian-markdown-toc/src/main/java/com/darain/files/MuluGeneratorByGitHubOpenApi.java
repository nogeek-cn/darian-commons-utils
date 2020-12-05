package com.darain.files;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/***
 * 从 github 的开放 API 拿到对应的文件
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/1/18  11:20
 */
public class MuluGeneratorByGitHubOpenApi {

    public static void main(String[] args) {
        getForUrl();
//        getGitHubUrl();
    }



    public static void getForUrl() {

        String url = "https://api.github.com/repos/Darian1996/docs/git/trees/master?recursive=1&_=1565620103566";
        String response = MuluGeneratorByGitHubOpenApi.get(url);
        ResponseModule responseModule = JSONObject.parseObject(response, ResponseModule.class);
        responseModule.getTree().stream()
                .map(TreeOne::getPath)
                .filter(path -> path.endsWith(".md"))
                // https://github.com/Darian1996/docs/blob/master/2017/2017-05-01-什么是性能优化-james/什么是性能优化.md
                .map(path -> "- [" + path + "](https://github.com/Darian1996/docs/blob/master/" + path + ")")
                .forEach(System.out::println);
    }

    /**
     * 向指定URL发送GET方法的请求
     */
    public static String get(String url) {
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Authorization", "token e3f5fc0686780c8f73e8f73ff380170875188678");
            connection.setRequestProperty("Referer", "https://github.com/");
            connection.setRequestProperty("Sec-Fetch-Mode", "cors");

            connection.setConnectTimeout(500000);
            connection.setReadTimeout(500000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            System.err.println("Exception occur when send http get request!" + e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }


}

@Data
class ResponseModule {
    private String sha;
    private boolean truncated;
    private String url;
    private List<TreeOne> tree;
}

@Data
class TreeOne {
    private String mode;
    private String path;
    private String sha;
    private String size;
    private String type;
    private String url;
}
