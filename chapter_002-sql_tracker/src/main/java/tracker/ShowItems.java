package tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * Класс реализует функционал отображения всеъ элементов
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.12.2019
 */
public class ShowItems implements UserAction {
    /**
     * @return - имя действия
     */
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    /**
     * Метод реализует отображение всех элементов Items
     *
     * @param input   - Объект Input
     * @param tracker - обьект tracker
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> consumer) {
        List<Item> result = tracker.findAll();
        if (result.size() == 0) {
            consumer.accept("Items list is empty");
        }
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) != null) {
                consumer.accept("Item ID: " + result.get(i).getId() + " Item name: " + result.get(i).getName());
            } else {
                consumer.accept("Items list is empty");
            }
        }
        return true;
    }
}
