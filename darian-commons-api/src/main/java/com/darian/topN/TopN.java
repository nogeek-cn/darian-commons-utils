package com.darian.topN;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

public class TopN {

    private static List<Integer>          integerList   = new LinkedList<>();
    private static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
    private static Integer                toPN          = 5;

    static void generator() {
        Random random = new Random();
        for (int i = 1; i <= 100000; i++) {
            int rNum = random.nextInt(10000);
            integerList.add(rNum);
        }

    }

    static void addBigData() {
        integerList.add(100005);
        integerList.add(100004);
        integerList.add(100003);
        integerList.add(100002);
        integerList.add(100001);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 大数在前
        //bigDataBeforeDemo();
        // 大数在后
        bigDataAfterDemo();

        toPNDemo();

        toPNThreadDemo();

        forkTopNDemo();

        addAlltaskTopNDemo();

        collectionSortDemo();

    }

    private static void toPNThreadDemo() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2); // 创建线程池
        CompletionService completionService = new ExecutorCompletionService(executorService);
        List<Integer> integer1 = integerList.subList(0, integerList.size() / 2);
        List<Integer> integer2 = integerList.subList(integerList.size() / 2, integerList.size());

        // futureA 保存 queryA 的结果
        Future<List<Integer>> futureA = completionService.submit(new TopNThred(integer1));
        // futureB 保存 queryB 的结果
        Future<List<Integer>> futureB = completionService.submit(new TopNThred(integer2));
        int count = 0;
        while (count < 2) { // 等待三个任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        //
        integer1 = futureA.get();
        integer2 = futureB.get();

        integer1.addAll(integer2);
        staticPriorityQueueOneAddOne(integer1,priorityQueue);


        long costTime = System.currentTimeMillis() - start;
        System.out.println("topNThreadDemo cost ：" + costTime + " ms");
        for (int i = priorityQueue.size() - 1; i >= 0; i--) {
            System.out.println(priorityQueue.poll());
        }

        System.out.println("====================================");
        executorService.shutdown();

    }

    private static class TopNThred implements Callable<List<Integer>> {
        PriorityQueue<Integer> priorityQueueOne = new PriorityQueue();
        private final List<Integer> integerList ;

        TopNThred(List<Integer> integerList1){
            this.integerList = integerList1;
        }

        @Override
        public List<Integer> call() throws Exception {
            staticPriorityQueueOneAddOne(integerList, priorityQueueOne);
            return new LinkedList<>(priorityQueueOne);
        }
    }

    private static void staticPriorityQueueOneAddOne(List<Integer> integerList, PriorityQueue<Integer> priorityQueueOne){
        integerList.forEach(value -> {
            if (priorityQueueOne.size() < toPN) {
                priorityQueueOne.add(value);
            } else {
                Integer minElement = priorityQueueOne.peek();
                if (value.compareTo(minElement) > 0) { // 将新元素与当前堆顶元素比较，保留较小的元素
                    priorityQueueOne.poll();
                    priorityQueueOne.add(value);
                }
            }
        });
    }


    private static void bigDataAfterDemo() {
        generator();
        addBigData();
    }

    private static void bigDataBeforeDemo() {
        addBigData();
        generator();
    }

    private static void collectionSortDemo() {
        long collectionSortBegin = System.currentTimeMillis();
        integerList.sort(Collections.reverseOrder());
        long collectionSortEnd = System.currentTimeMillis();
        System.out.println("collectionSort cost:  " + (collectionSortEnd - collectionSortBegin) + " ms");

        for (int i = 0; i < toPN; i++) {
            System.out.println(integerList.get(i));
        }
    }

    private static void addAlltaskTopNDemo() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AddAlltask addAlltask = new AddAlltask(integerList, toPN, priorityQueue);
        long addAlltaskBegin = System.currentTimeMillis();
        forkJoinPool.invoke(addAlltask);
        long addAlltaskEnd = System.currentTimeMillis();
        System.out.println("addAlltaskTopN cost:  " + (addAlltaskEnd - addAlltaskBegin) + " ms");

        for (int i = priorityQueue.size() - 1; i >= 0; i--) {
            System.out.println(priorityQueue.poll());
        }

        System.out.println("====================================");
    }

    private static void forkTopNDemo() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Addtask addtask = new Addtask(integerList, toPN, priorityQueue);
        long forkTopNBegin = System.currentTimeMillis();
        forkJoinPool.invoke(addtask);
        long forkTopNEnd = System.currentTimeMillis();
        System.out.println("forkTopN cost:  " + (forkTopNEnd - forkTopNBegin) + " ms");

        for (int i = priorityQueue.size() - 1; i >= 0; i--) {
            System.out.println(priorityQueue.poll());
        }
        System.out.println("====================================");
    }

    private static void toPNDemo() {
        long toPNBegin = System.currentTimeMillis();
        integerList.forEach(value -> {
            if (priorityQueue.size() < toPN) {
                priorityQueue.add(value);
            } else {
                Integer minElement = priorityQueue.peek();
                if (value.compareTo(minElement) > 0) { // 将新元素与当前堆顶元素比较，保留较小的元素
                    priorityQueue.poll();
                    priorityQueue.add(value);
                }
            }
        });

        long toPNEnd = System.currentTimeMillis();

        System.out.println("toPN cost:  " + (toPNEnd - toPNBegin) + " ms");
        for (int i = priorityQueue.size() - 1; i >= 0; i--) {
            System.out.println(priorityQueue.poll());
        }

        System.out.println("====================================");
    }

    private static class Addtask extends RecursiveAction {
        private final List<Integer>          nums;
        private final Integer                toPN;
        private final PriorityQueue<Integer> priorityQueue;

        public Addtask(List<Integer> nums, Integer toPN, PriorityQueue<Integer> priorityQueue) {
            this.nums = nums;
            this.toPN = toPN;
            this.priorityQueue = priorityQueue;
        }

        @Override
        protected void compute() {
            int size = nums.size();
            if (size > 1) {
                // 二分法
                int parts = size / 2;
                // 上半部
                List<Integer> leftPart = nums.subList(0, parts);
                Addtask leftTask = new Addtask(leftPart, toPN, priorityQueue);
                // 下半部
                List<Integer> rightPart = nums.subList(parts, size);
                Addtask rightTask = new Addtask(rightPart, toPN, priorityQueue);
                invokeAll(leftTask, rightTask); // fork/join 操作
            } else {// 当前的元素只有一个
                if (size == 0) {// 保护
                    return;
                }
                Integer value = nums.get(0);
                // 累加
                if (priorityQueue.size() < toPN) {
                    priorityQueue.add(value);
                } else {
                    Integer minElement = priorityQueue.peek();
                    if (value.compareTo(minElement) > 0) { // 将新元素与当前堆顶元素比较，保留较小的元素
                        priorityQueue.poll();
                        priorityQueue.add(value);
                    }
                }
            }
        }
    }

    private static class AddAlltask extends RecursiveAction {
        private final List<Integer>          nums;
        private final Integer                toPN;
        private final PriorityQueue<Integer> priorityQueue;

        public AddAlltask(List<Integer> nums, Integer toPN, PriorityQueue<Integer> priorityQueue) {
            this.nums = nums;
            this.toPN = toPN;
            this.priorityQueue = priorityQueue;
        }

        @Override
        protected void compute() {
            int size = nums.size();
            if (size > toPN) {
                // 二分法
                int parts = size / 2;
                // 上半部
                List<Integer> leftPart = nums.subList(0, parts);
                Addtask leftTask = new Addtask(leftPart, toPN, priorityQueue);
                // 下半部
                List<Integer> rightPart = nums.subList(parts, size);
                Addtask rightTask = new Addtask(rightPart, toPN, priorityQueue);
                invokeAll(leftTask, rightTask); // fork/join 操作
            } else {// 当前的元素只有一个
                if (size == 0) {// 保护
                    return;
                }
                // 累加
                if (priorityQueue.size() + nums.size() < toPN) {
                    priorityQueue.addAll(nums);
                } else {
                    for (Integer value : nums) {
                        Integer minElement = priorityQueue.peek();
                        if (value.compareTo(minElement) > 0) { // 将新元素与当前堆顶元素比较，保留较小的元素
                            priorityQueue.poll();
                            priorityQueue.add(value);
                        }
                    }
                }
            }
        }
    }

}
