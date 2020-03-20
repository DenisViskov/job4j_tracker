package tracker;

/**
 * Класс реализующий шаблон Singleton посредством Lazy loading
 *
 * @author Денис Висков
 * @version 1.1
 * @since 13.12.2019
 */
public class TrackerSingleSecond {
    /**
     * Экземпляр класса
     */
    private static TrackerSingleSecond instance;

    private TrackerSingleSecond() {
    }

    /**
     * Метод проверяет поле Instance и если оно ещё не проинициализировано
     * выполняет его инициализацию
     *
     * @return - instance
     */
    public static TrackerSingleSecond getInstance() {
        if (instance == null) {
            instance = new TrackerSingleSecond();
        }
        return instance;
    }
}
