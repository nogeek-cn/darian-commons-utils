package com.darian;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ReentrantLockAQSDemo {

    // 使用公平锁
    static ReentrantLock reentrantLock = new ReentrantLock(true);

    // 线程数量
    static int threadCount = 6;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CompletionService completionService = new ExecutorCompletionService(executorService);

        String thisThreadName = Thread.currentThread().getName();
        System.out.println("[" + thisThreadName + "] \t\t\t 初始状态：\t\t aqs_list:[ " + getAllThreadNameList() + " ]");

        for (int i = 0; i < threadCount; i++) {
            completionService.submit(new DemoThread(), null);
        }

        int count = 0;
        while (count < threadCount) { // 等待所有任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        System.out.println("[" + thisThreadName + "] \t\t\t 结束状态：\t\t aqs_list:[ " + getAllThreadNameList() + " ]");
        executorService.shutdown();

        //[main] 			 初始状态：		 aqs_list:[  ]
        //[pool-1-thread-3]竞争到锁之前： 	 aqs_list:[  ]
        //[pool-1-thread-3]竞争到锁之后： 	 aqs_list:[  ]
        //[pool-1-thread-4]竞争到锁之前： 	 aqs_list:[  ]
        //[pool-1-thread-5]竞争到锁之前： 	 aqs_list:[ (null thread), pool-1-thread-4 ]
        //[pool-1-thread-6]竞争到锁之前： 	 aqs_list:[ (null thread), pool-1-thread-4, pool-1-thread-5 ]
        //[pool-1-thread-2]竞争到锁之前： 	 aqs_list:[ (null thread), pool-1-thread-4, pool-1-thread-5, pool-1-thread-6 ]
        //[pool-1-thread-1]竞争到锁之前： 	 aqs_list:[ (null thread), pool-1-thread-4, pool-1-thread-5 ]
        //[pool-1-thread-3]竞争解锁以后： 	 aqs_list:[ (null thread) ]
        //[pool-1-thread-4]竞争到锁之后： 	 aqs_list:[ (null thread), pool-1-thread-5, pool-1-thread-6, pool-1-thread-2, pool-1-thread-1 ]
        //[pool-1-thread-5]竞争到锁之后： 	 aqs_list:[ (null thread), pool-1-thread-6, pool-1-thread-2, pool-1-thread-1 ]
        //[pool-1-thread-4]竞争解锁以后： 	 aqs_list:[ (null thread), pool-1-thread-6, pool-1-thread-2, pool-1-thread-1 ]
        //[pool-1-thread-5]竞争解锁以后： 	 aqs_list:[ (null thread), pool-1-thread-2, pool-1-thread-1 ]
        //[pool-1-thread-6]竞争到锁之后： 	 aqs_list:[ (null thread), pool-1-thread-2, pool-1-thread-1 ]
        //[pool-1-thread-2]竞争到锁之后： 	 aqs_list:[ (null thread), pool-1-thread-1 ]
        //[pool-1-thread-6]竞争解锁以后： 	 aqs_list:[ (null thread), pool-1-thread-1 ]
        //[pool-1-thread-1]竞争到锁之后： 	 aqs_list:[ (null thread) ]
        //[pool-1-thread-2]竞争解锁以后： 	 aqs_list:[ (null thread) ]
        //[pool-1-thread-1]竞争解锁以后： 	 aqs_list:[ (null thread) ]
        //[main] 			 结束状态：		 aqs_list:[ (null thread) ]

    }

    public static class DemoThread extends Thread {

        /**
         * 争抢到锁
         * 暂停 3 s
         * 释放锁
         */
        @Override
        public void run() {
            String thisThreadName = Thread.currentThread().getName();
            try {
                System.out.println("[" + thisThreadName + "]竞争到锁之前： \t aqs_list:[ " + getAllThreadNameList() + " ]");
                reentrantLock.lock();
                System.out.println("[" + thisThreadName + "]竞争到锁之后： \t aqs_list:[ " + getAllThreadNameList() + " ]");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                System.out.println("[" + thisThreadName + "]竞争解锁以后： \t aqs_list:[ " + getAllThreadNameList() + " ]");
            }

        }
    }

    /**
     * 拿到 AQS 中的所有节点的 threadNamList
     *
     * @return
     */
    public static String getAllThreadNameList() {
        try {
            Field syncField = ReentrantLock.class.getDeclaredField("sync");
            syncField.setAccessible(true);
            Object sync = syncField.get(reentrantLock);

            Field headField = AbstractQueuedSynchronizer.class.getDeclaredField("head");
            headField.setAccessible(true);

            Object head = headField.get(sync);

            List<String> threadNameList = getAllThreadNameListByHead(head, new ArrayList<>());

            return threadNameList.stream().collect(Collectors.joining(", "));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 传入 AQS Node 拿到，从 node 开始到最后一位的 threadNameList
     *
     * @param node           起始节点
     * @param threadNameList
     * @return
     */
    public static List<String> getAllThreadNameListByHead(Object node, List<String> threadNameList) {
        try {
            if (node == null) {
                return threadNameList;
            }
            threadNameList.add(getThread(node));

            Class<?> aClass = node.getClass();
            Field nextField = aClass.getDeclaredField("next");
            nextField.setAccessible(true);

            Object next = nextField.get(node);


            return getAllThreadNameListByHead(next, threadNameList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return threadNameList;

    }

    /**
     * 根据 AQS.Node 拿到 Node 的 Thread
     *
     * @param node
     * @return
     */
    public static String getThread(Object node) {
        try {
            if (node == null) {
                return "(null node)";
            }
            Class<?> aClass = node.getClass();
            Field threadField = aClass.getDeclaredField("thread");
            threadField.setAccessible(true);

            Thread thread = (Thread) threadField.get(node);
            if (thread == null) {
                return "(null thread)";
            }
            return thread.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}