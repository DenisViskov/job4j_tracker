package tracker;

import java.util.List;

/**
 * Interface of example Hibernate work with ItemExample
 *
 * @author Денис Висков
 * @version 1.0
 * @since 20.09.2020
 */
public interface TrackerExample extends AutoCloseable {
    /**
     * Method should adds new item
     *
     * @param item - item
     * @return - item
     */
    ItemExample add(ItemExample item);

    /**
     * Method should replace item by id
     *
     * @param id   - id
     * @param item - item
     * @return - true or false in dependency of result
     */
    boolean replace(int id, ItemExample item);

    /**
     * Method should delete item by given id
     *
     * @param id - id
     * @return - true or false in dependency of result
     */
    boolean delete(int id);

    /**
     * Method should looking for all elements
     *
     * @return - List of items
     */
    List<ItemExample> findAll();

    /**
     * Method should looking for item by given key
     *
     * @param key - name
     * @return - List of items
     */
    List<ItemExample> findByName(String key);

    /**
     * Method should looking for item by given id
     *
     * @param id - id
     * @return - item
     */
    ItemExample findById(int id);
}
