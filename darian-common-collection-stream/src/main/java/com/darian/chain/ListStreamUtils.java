package com.darian.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/***
 * 2核4线程 8G
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午2:32
 */
public class ListStreamUtils {


    public static void main(String[] args) {

        int count = 10000;
        List<Integer> intList1 = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            intList1.add(i);
        }
        long start = System.currentTimeMillis();
        intList1.parallelStream().filter(integer -> new TwoFilter().test(integer)).collect(Collectors.toList());
        System.out.println("parallelStream cost: " + (System.currentTimeMillis() - start) + " ms");

        List<Integer> intList2 = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            intList2.add(i);
        }
        start = System.currentTimeMillis();
        intList2.stream().filter(integer -> new TwoFilter().test(integer)).collect(Collectors.toList());
        System.out.println("stream cost: " + (System.currentTimeMillis() - start) + " ms");
    }

    public static class TwoFilter implements Predicate<Integer> {

        @Override
        public boolean test(Integer integer) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return integer % 2 == 0;
        }
    }
}
