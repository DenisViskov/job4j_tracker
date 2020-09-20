package tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class is an HBM tracker
 *
 * @author Денис Висков
 * @version 1.0
 * @since 20.09.2020
 */
public class HbmTracker implements TrackerExample {
    /**
     * Registry
     */
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    /**
     * Session factory
     */
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * Method add item to DB through Hibernate
     *
     * @param item - item
     * @return item
     */
    @Override
    public ItemExample add(ItemExample item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    /**
     * Method replace item by given ID through Hibernate
     *
     * @param id   - id
     * @param item - item
     * @return boolean
     */
    @Override
    public boolean replace(int id, ItemExample item) {
        boolean result = false;
        if (findById(id) != null) {
            item.setId(id);
            Session session = sf.openSession();
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            session.close();
            result = true;
        }
        return result;
    }

    /**
     * Method delete item from DB by given ID through Hibernate
     *
     * @param id - id
     * @return boolean
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        if (findById(id) != null) {
            Session session = sf.openSession();
            session.beginTransaction();
            ItemExample item = new ItemExample();
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
            session.close();
            result = true;
        }
        return result;
    }

    /**
     * Method returns all items from DB through Hibernate
     *
     * @return list
     */
    @Override
    public List<ItemExample> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from tracker.ItemExample").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Method returns all items with given name through Hibernate
     *
     * @param key - name
     * @return list
     */
    @Override
    public List<ItemExample> findByName(String key) {
        return findAll().stream()
                .filter(item -> item.getName().equals(key))
                .collect(Collectors.toList());
    }

    /**
     * Method looking for item with given id
     *
     * @param id - id
     * @return item
     */
    @Override
    public ItemExample findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        ItemExample result = session.get(ItemExample.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Method of close registry service
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
