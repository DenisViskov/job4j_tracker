package tracker;

import java.util.function.Consumer;

/**
 * Класс реализует функционал добавления нового элемента
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.12.2019
 */
public class CreateAction implements UserAction {
    /**
     * @return - имя действия
     */
    @Override
    public String name() {
        return "=== Create a new Item ====";
    }

    /**
     * Метод реализует создание и добавление нового элемента
     *
     * @param input   - Объект Input
     * @param tracker - обьект tracker
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> consumer) {
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        consumer.accept("New Item was been added");
        return true;
    }
}
