package tracker;

import java.util.function.Consumer;

/**
 * Класс реализует функционал редактирования элементов
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.12.2019
 */
public class EditItem implements UserAction {
    /**
     * @return - имя действия
     */
    @Override
    public String name() {
        return "=== Edit item ====";
    }

    /**
     * Метод реализует редактирование элементов
     *
     * @param input   - Объект Input
     * @param tracker - обьект tracker
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> consumer) {
        String id = input.askStr("Enter ID: ");
        String name = input.askStr("Enter new Name: ");
        Item result = new Item(name);
        if (tracker.replace(id, result)) {
            consumer.accept("Edit complete");
        } else {
            consumer.accept("Entered ID is not found");
            consumer.accept("Try again");
        }
        return true;
    }
}
