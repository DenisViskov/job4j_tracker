package tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * Класс реализует функционал поиска элемента по имени
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.12.2019
 */
public class FindByName implements UserAction {
    /**
     * @return - имя действия
     */
    @Override
    public String name() {
        return "=== Find items by name ====";
    }

    /**
     * Метод реализует поиск элементов с заданным именем
     *
     * @param input   - Объект Input
     * @param tracker - обьект tracker
     */
    @Override
    public boolean execute(Input input, MemTracker tracker, Consumer<String> consumer) {
        String name = input.askStr("Enter Name: ");
        List<Item> result = tracker.findByName(name);
        for (Item item : result) {
            consumer.accept("Item ID: " + item.getId() + " Item name: " + item.getName());
        }
        if (result.size() == 0) {
            consumer.accept("If you see this message it mean your entered Name it was not found");
            consumer.accept("Try again");
        }
        return true;
    }
}
