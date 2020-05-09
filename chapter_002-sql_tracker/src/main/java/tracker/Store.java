package tracker;

import java.util.List;

/**
 * Interface for realizes database hold
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.05.2020
 */
public interface Store extends AutoCloseable, Tracker {
    /**
     * Method should do initialization new database
     */
    void init();
}
