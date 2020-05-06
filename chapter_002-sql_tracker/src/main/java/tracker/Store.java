package tracker;

import java.util.List;

/**
 * Interface for realizes database hold
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.05.2020
 */
public interface Store extends AutoCloseable {
    /**
     * Method should do initialization new database
     */
    void init();

    /**
     * Method should adds new item to database
     *
     * @param item - item
     * @return - item
     */
    Item add(Item item);

    /**
     * Method should replace item by id in database
     *
     * @param id   - id
     * @param item - item
     * @return - true or false in dependency of result
     */
    boolean replace(String id, Item item);

    /**
     * Method should delete item from database by given id
     *
     * @param id - id
     * @return - true or false in dependency of result
     */
    boolean delete(String id);

    /**
     * Method should looking for all elements in database
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
     * Method should looking for item in database by given id
     *
     * @param id - id
     * @return - item
     */
    Item findById(String id);
}
