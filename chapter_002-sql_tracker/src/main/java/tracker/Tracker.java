package tracker;

import java.util.List;

/**
 * Interface for behavior tracker
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.05.2020
 */
public interface Tracker {

    /**
     * Method should adds new item
     *
     * @param item - item
     * @return - item
     */
    Item add(Item item);

    /**
     * Method should replace item by id
     *
     * @param id   - id
     * @param item - item
     * @return - true or false in dependency of result
     */
    boolean replace(String id, Item item);

    /**
     * Method should delete item by given id
     *
     * @param id - id
     * @return - true or false in dependency of result
     */
    boolean delete(String id);

    /**
     * Method should looking for all elements
     *
     * @return - List of items
     */
    List<Item> findAll();

    /**
     * Method should looking for item by given key
     *
     * @param key - name
     * @return - List of items
     */
    List<Item> findByName(String key);

    /**
     * Method should looking for item by given id
     *
     * @param id - id
     * @return - item
     */
    Item findById(String id);
}
