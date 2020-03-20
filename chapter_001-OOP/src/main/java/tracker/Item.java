package tracker;


/**
 * Класс модель Item - описывает бизнес модель заявки
 *
 * @author Денис Висков
 * @version 1.0
 * @since 02.12.2019
 */
public class Item {
    /**
     * Уникальный ключ
     */
    private String id;

    /**
     * Имя
     */
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName();
    }
}
