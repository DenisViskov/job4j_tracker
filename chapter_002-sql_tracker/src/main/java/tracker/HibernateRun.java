package tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 09.09.2020
 */
public class HibernateRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            ItemExample example = new ItemExample("Name");
            example.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            example.setDescription("Description");
            ItemExample item = create(example, sf);
            System.out.println(item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static ItemExample create(ItemExample item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public static void update(ItemExample item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        ItemExample item = new ItemExample(null);
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public static List<ItemExample> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from tracker.ItemExample").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static ItemExample findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        ItemExample result = session.get(ItemExample.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
