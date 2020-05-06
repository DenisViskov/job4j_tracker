package tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует работу пользовательского интерфейса
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.12.2019
 */
public class StartUI {

    /**
     * Метод реализует пользовательский интерфейс
     *
     * @param input   - объект input
     * @param tracker - трекер
     * @param actions - действия
     */
    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker, System.out::println);
        }
    }

    /**
     * Метод реализует отображение поьзовательского меню
     *
     * @param actions - действия
     */
    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = List.of(new CreateAction());
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
