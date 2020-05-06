package tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс реализует функционал обёртки над массивом
 *
 * @author Денис Висков
 * @version 1.1
 * @since 02.12.2019
 */
public class MemTracker implements Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Метод выполняет замену элемента в массиве по его ID
     * Возвращает true в случае положительного результата замены и false в противоположном случае
     *
     * @param id   - Id
     * @param item - Item
     * @return - true или false
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = this.findIndexByID(id);
        if (index != -1) {
            item.setId(this.items.get(index).getId());
            this.items.set(index, item);
            result = true;
        }
        return result;
    }

    /**
     * Метод выполняет удаление элемента в ячейке по его ID и выполняет смещение элементов на одну ячейку влево
     * Вернёт true или false в зависимости от того выполнена операция или нет
     *
     * @param id - ID элемента
     * @return true или false
     */
    public boolean delete(String id) {
        boolean result = false;
        int index = this.findIndexByID(id);
        if (index != -1) {
            this.items.remove(index);
            result = true;
        }
        return result;
    }

    /**
     * Метод реализует возврат нового массива без Null элементов
     *
     * @return - новый массив без Null элементов
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод реализует возврат всех элементов массива с именами совпадающими в переданном параметре
     *
     * @param key - имя
     * @return - массив с совпадающими именами
     */
    public List<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(key)) {
                result.add(this.items.get(i));
            }
        }
        return result;
    }

    /**
     * Метод осуществляет поиск в массиве по заданному ID
     *
     * @param id - ID
     * @return - возвращает Item если элемент найден и Null если не найден
     */
    public Item findById(String id) {
        Item result = null;
        int index = this.findIndexByID(id);
        if (index != -1) {
            result = this.items.get(index);
        }
        return result;
    }

    /**
     * Метод выполняет поиск индекса по заданному id элемента
     *
     * @param id - id
     * @return - индекс элемента
     */
    private int findIndexByID(String id) {
        int result = -1;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i) != null && this.items.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
