package com.darian.multiThreadRead;

public class BigFileReaderTest {

    public static void main(String[] args) {
        String filePath = getThisClassPackagePathWhenMaven() + "reliability.txt";

        filePath = "F:\\reliability.txt";
        int threadSize = 100;

        BigFileReader.Builder builder = new BigFileReader.Builder(filePath, line -> {
//                        String threadName = Thread.currentThread().getName();
//             打印每行数据
//                        System.err.println("[" + threadName + "] read line: " + line);
        });
        builder.withTreadSize(threadSize)
                .withCharset("gbk")
                .withBufferSize(1024 * 1024);
        BigFileReader bigFileReader = builder.build();
        bigFileReader.start();
    }

    public static String getThisClassPackagePathWhenMaven() {
        String jvmPath = BigFileReader.class.getResource("").getPath();
        return jvmPath.substring(1, jvmPath.length())
                .replace("target/classes", "src/main/java");
    }

    // ----------------- 12 核
    // 行数          线程数  耗时 ms
    //--------------------------- 固态
    // 205964641    1   16807
    // 205964641    2   13716
    // 205964641    5   9815 / 8775 / 9237
    // 205964641    10  6176
    // 205964641    50  6115
    //-------
    // 823858561    2   55931
    // 823858561    5   32378
    // 823858561    10  26597 / 32937
    // 823858561    20 26058 / 26176

    // -------------------- 机械
    // 823858561    2   51869 / 65652 / 59332 / 53987 / 81990
    // 823858561    5   39800
    // 823858561    10  32626 / 38410 / 34066
    // 823858561    12  26355 / 39180 / 28956 / 24940
    // 823858561    50  24874 / 25907 / 29737 / 26071
    // 823858561    100 24582 / 23672
}