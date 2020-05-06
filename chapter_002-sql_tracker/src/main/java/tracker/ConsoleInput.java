package tracker;

import java.util.Scanner;

/**
 * Класс реализует ввод данных с консоли через реализацию интерфейса Input
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.12.2019
 */
public class ConsoleInput implements Input {
    /**
     * Объект scanner
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод выводит на консоль вопрос и возвращает ответ
     *
     * @param question - вопрос
     * @return - ответ
     */
    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Метод спрашивает пользователя и получает ответ
     *
     * @param question - вопрос
     * @return - ответ
     */
    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    /**
     * Метод реализует функционал вопроса с валидацией
     * при невалидном ответе выбросит IllegalStateException
     *
     * @param question - вопрос
     * @param max      - максимальное значение
     * @return - ответ
     */
    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select < 0 || select > max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}
