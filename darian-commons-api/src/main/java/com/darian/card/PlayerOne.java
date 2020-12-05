
package com.darian.card;

import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a>
 * @date 2019/12/24  0:51
 */
public class PlayerOne extends Thread {
    public String playName;

    PlayerOne(String playName) {
        this.playName = playName;
    }

    public PlayerOne                    nextPlayer;
    public LinkedBlockingQueue<Integer> ownCardQueue = new LinkedBlockingQueue<>();
    public Integer                      sum          = 0;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {

                Integer card = ownCardQueue.take();
                sum += card;
                //System.out.println("[" + Thread.currentThread().getName() + "] " + playName + "   sum: [" + sum + "]");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void acceptCard(List<Integer> integerList) {
        while (!ownCardQueue.isEmpty()) {
            if (ownCardQueue.isEmpty()) {
                break;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] " + playName + "  sum: [" + sum + "]");
        if (sum >= 50) {
            System.out.println("[" + Thread.currentThread().getName() + "] " + playName + "  is win , sum: [" + sum + "]");
            //this.interrupt();
            return;
        }
        if (checkCardListisNotNull(integerList)) {
            int index = new Random().nextInt(integerList.size());
            Integer card = integerList.get(index);
            integerList.remove(index);
            System.out.println(
                    "[" + Thread.currentThread().getName() + "] " + " [" + playName + "] get  Data:" + card + "  cardList.size:["
                            + integerList
                            .size() + "]");

            ownCardQueue.add(card);

            nextPlayer.acceptCard(integerList);
        }
    }

    public void setNextPlayer(PlayerOne player) {
        this.nextPlayer = player;
    }

    boolean checkCardListisNotNull(List<Integer> integerList) {
        return integerList != null && !integerList.isEmpty();
    }
}