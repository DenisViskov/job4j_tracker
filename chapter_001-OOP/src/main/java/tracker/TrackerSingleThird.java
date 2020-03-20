package tracker;

/**
 * Класс реализующий шаблон Singleton посредством Eager loading
 *
 * @author Денис Висков
 * @version 1.1
 * @since 13.12.2019
 */
public class TrackerSingleThird {
    /**
     * Экземпляр класса
     */
    private static final TrackerSingleThird INSTANCE = new TrackerSingleThird();

    private TrackerSingleThird() {
    }

    public static TrackerSingleThird getINSTANCE() {
        return INSTANCE;
    }
}
