package sort;

import java.util.Arrays;

/**
 * Класс реализует функционал работы вендингового аппарата
 *
 * @author Денис Висков
 * @version 1.0
 * @since 29.12.2019
 */
public class Machine {
    /**
     * Монеты
     */
    private final int[] coins = {10, 5, 2, 1};

    /**
     * Метод возвращает сдачу монетами
     *
     * @param money - деньги
     * @param price - стоимость
     * @return - сдача
     */
    public int[] change(int money, int price) {
        int[] rsl = new int[100];
        int size = 0;
        int difference = money - price;
        if (difference != 0) {
            for (int i = 0; i < this.coins.length; i++) {
                if (difference - this.coins[i] >= 0) {
                    difference -= this.coins[i];
                    rsl[size] = this.coins[i];
                    size = rsl[size] > 0 ? ++size : size;
                }
            }
        }
        return Arrays.copyOf(rsl, size);
    }
}


