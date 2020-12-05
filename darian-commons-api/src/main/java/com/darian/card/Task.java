
package com.darian.card;

import java.util.ArrayList;
import java.util.List;
/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a>
 * @date 2019/12/24  0:51
 */
public class Task {
    private final PlayerOne playerOne;

    public Task() {
        this.playerOne = new PlayerOne("player-One");
        PlayerOne playerTwo = new PlayerOne("player-Two");
        PlayerOne playerThree = new PlayerOne("player-Three");

        playerOne.setNextPlayer(playerTwo);
        playerTwo.setNextPlayer(playerThree);
        playerThree.setNextPlayer(playerOne);

        playerOne.start();
        playerTwo.start();
        playerThree.start();

    }

    public static void main(String[] args) {
        List<Integer> cardList = Task.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                20, 20);

        Task task = new Task();
        task.doPlay(cardList);
    }

    public void doPlay(List<Integer> integerList) {
        playerOne.acceptCard(integerList);
    }

    public static List<Integer> of(Integer... cards) {
        List<Integer> cardList = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            cardList.add(cards[i]);
        }
        return cardList;
    }
}