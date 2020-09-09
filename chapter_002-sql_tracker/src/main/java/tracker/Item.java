package tracker;


import javax.persistence.*;

/**
 * Класс модель Item - описывает бизнес модель заявки
 *
 * @author Денис Висков
 * @version 1.0
 * @since 02.12.2019
 */
@Entity
@Table(name = "items")
public class Item {
    /**
     * Уникальный ключ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * Имя
     */
    private String name;

    public Item() {
    }

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
