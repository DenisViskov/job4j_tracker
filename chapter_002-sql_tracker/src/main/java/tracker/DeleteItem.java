package tracker;

import java.util.function.Consumer;

/**
 * Класс реализует функционал удаления элементов
 *
 * @author Денис Висков
 * @version 1.0
 * @since 10.12.2019
 */
public class DeleteItem implements UserAction {
    /**
     * @return - имя действия
     */
    @Override
    public String name() {
        return "=== Delete item ====";
    }

    /**
     * Метод реализует удаление элемента
     *
     * @param input   - Объект Input
     * @param tracker - обьект tracker
     */
    @Override
    public boolean execute(Input input, MemTracker tracker, Consumer<String> consumer) {
        String id = input.askStr("Enter ID: ");
        if (tracker.delete(id)) {
            consumer.accept("Item was been deleted");
        } else {
            consumer.accept("Entered ID is not found");
            consumer.accept("Try again");
        }
        return true;
    }
}
