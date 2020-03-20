package tracker;

import java.util.function.Consumer;

/**
 * Интерфейс реализации действий пользователя
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.12.2019
 */
public interface UserAction {
    /**
     * Метод который должен возвращать имя действия
     *
     * @return - имя
     */
    String name();

    /**
     * Метод отвечающий за реализацию описания действия системы
     *
     * @param input   - входящий параметр
     * @param tracker - трекер
     * @return - true или false в зависимости от того вышел пользователь или нет
     */
    boolean execute(Input input, Tracker tracker, Consumer<String> consumer);
}

