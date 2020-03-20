package tracker;

/**
 * Интерфейс ввода- вывода
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.12.2019
 */
public interface Input {

    /**
     * Реализация вопроса
     *
     * @param question - вопрос
     * @return - ответ
     */
    String askStr(String question);

    /**
     * Реализация вопроса
     *
     * @param question - вопрос
     * @return - ответ
     */
    int askInt(String question);

    /**
     * Реализация валидации вопроса
     *
     * @param question - вопрос
     * @param max      - максимальное значение
     * @return - ответ
     */
    int askInt(String question, int max);
}
