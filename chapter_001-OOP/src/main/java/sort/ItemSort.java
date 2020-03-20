package sort;

import tracker.Item;

import java.util.Collections;
import java.util.Comparator;

/**
 * Класс реализующий простую и обратную сортировку Item
 *
 * @author Денис Висков
 * @version 1.0
 * @since 02.01.2020
 */
public class ItemSort {
    public Comparator<Item> order() {
        return new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

    public Comparator<Item> reverse() {
        return new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o2.getName().compareTo(o1.getName());
            }
        };
    }
}
