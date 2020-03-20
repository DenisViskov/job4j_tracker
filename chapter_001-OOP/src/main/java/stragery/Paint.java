package stragery;

/**
 * Класс реализующий функционал отрисовки в зависимости от передаваемого объекта
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.12.2019
 */
public class Paint {

    /**
     * Метод выводит на экран картринку в зависимости от переданного объекта
     *
     * @param shape - Объект shape
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
