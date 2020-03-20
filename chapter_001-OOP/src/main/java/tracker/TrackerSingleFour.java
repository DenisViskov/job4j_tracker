package tracker;

/**
 * Класс реализующий шаблон Singleton посредством Lazy loading
 *
 * @author Денис Висков
 * @version 1.1
 * @since 13.12.2019
 */
public class TrackerSingleFour {
    private TrackerSingleFour() {
    }

    /**
     * @return - поле instance класса Holder
     */
    public static TrackerSingleFour getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Статический вложенный класс Holder реализующий демонстрацию шаблона Singleton
     */
    private static final class Holder {
        /**
         * Экземпляр класса
         */
        private static final TrackerSingleFour INSTANCE = new TrackerSingleFour();
    }
}
