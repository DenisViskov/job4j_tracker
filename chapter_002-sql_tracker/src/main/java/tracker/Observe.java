package tracker;

/**
 * Interface of observe
 * Especially for reactive programming
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public interface Observe<T> {
    void receive(T model);
}
