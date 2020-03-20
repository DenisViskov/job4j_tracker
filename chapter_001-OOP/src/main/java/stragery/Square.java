package stragery;

import java.util.StringJoiner;

/**
 * Класс реализует функциональность квадрата
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.12.2019
 */
public class Square implements Shape {

    /**
     * Метод отрисовывает квадрат
     *
     * @return - нарисованный квадрат
     */
    @Override
    public String draw() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("**********");
        result.add("**********");
        result.add("**********");
        result.add("**********");
        return result.toString();
    }
}
