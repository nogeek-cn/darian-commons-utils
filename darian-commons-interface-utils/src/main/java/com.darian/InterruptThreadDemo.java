package com.darian;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * interrupt() 对 WAITING、TIMED_WAITING 的线程都为抛出异常吗？
 * 否定的，
 */
public class InterruptThreadDemo {
    public static void main(String[] args) {

        // 对 park 住的线程执行 interrupt 方法
        interruptParkThread();
        sleep_second(1);
        System.out.println();

        // 对 wait 中的线程执行 interrupt 方法
        interruptWaitThread();
        sleep_second(1);
        System.out.println();

        // 对 sleep 中的线程执行 interrupt 方法
        interruptSleepThread();
        sleep_second(1);
        System.out.println();

        // 对 join 中的线程执行 interrupt 方法
        interruptJoinThread();

    }

    static void interruptThread(Thread thread) {
        thread.start();
        // 保证线程的状态已经 run 之后
        sleep_second(1);
        System.out.println(thread.getState());
        thread.interrupt();

    }

    static void sleep_second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void interruptParkThread() {
        System.out.println("interruptParkThread");
        interruptThread(getParkThread());
    }

    static void interruptWaitThread() {
        System.out.println("interruptWaitThread");
        interruptThread(getWaitThread());
    }

    static void interruptSleepThread() {
        System.out.println("interruptSleepThread");
        interruptThread(getSleepThread());
    }

    static void interruptJoinThread() {
        System.out.println("interruptJoinThread");
        interruptThread(getJoinThread());
    }

    static Thread getParkThread() {
        return new Thread(() -> {
            try {
                LockSupport.park();
            } catch (Exception e) {
                System.out.println("\t[interruptParkThread][error]" + e);
            }
        });
    }

    static Thread getWaitThread() {
        return new Thread(() -> {
            Object lock = new Object();
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (Exception e) {
                    System.out.println("\t[interruptWaitThread][error]" + e);
                }
            }
        });
    }

    static Thread getJoinThread() {
        Thread sleepThread = getSleepThread();
        sleepThread.start();
        return new Thread(() -> {
            try {
                sleepThread.join();
            } catch (InterruptedException e) {
                System.out.println("\t[interruptJoinThread][error]" + e);
            }

        });
    }

    static Thread getSleepThread() {
        return new Thread(() -> {
            try {
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("\t[interruptSleepThread][error]" + e);
            }
        });
    }
}