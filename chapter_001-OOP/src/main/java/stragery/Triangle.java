package stragery;

import java.util.StringJoiner;

/**
 * Класс реализует функционал треугольника
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.12.2019
 */
public class Triangle implements Shape {

    /**
     * Метод отрисовывает треугольник
     *
     * @return - нарисованный треугольник
     */
    @Override
    public String draw() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("    *    ");
        result.add("   * *   ");
        result.add("  *   *  ");
        result.add(" * * * * ");
        return result.toString();
    }
}
