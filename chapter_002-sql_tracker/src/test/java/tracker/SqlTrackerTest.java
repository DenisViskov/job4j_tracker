package tracker;

import org.junit.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SqlTrackerTest {

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = ConnectionRollback.create(init());
    }

    @Test
    public void addTest() {
        SqlTracker tracker = new SqlTracker(connection);
        tracker.add(new Item("name"));
        assertThat(tracker.findByName("name").size(), is(1));
    }

    @Test
    public void replaceTest() {
        SqlTracker tracker = new SqlTracker(connection);
        tracker.add(new Item("name"));
        String id = tracker.findAll().get(0).getId();
        tracker.replace(id, new Item("new Name"));
        assertThat(tracker.findAll().get(0).getName(), is("new Name"));
    }

    @Test
    public void deleteTest() {
        SqlTracker tracker = new SqlTracker(connection);
        tracker.add(new Item("name"));
        String id = tracker.findAll().get(0).getId();
        tracker.delete(id);
        assertThat(tracker.findAll().size(), is(0));
    }

    @Test
    public void findAllTest() {
        SqlTracker tracker = new SqlTracker(connection);
        tracker.add(new Item("first"));
        tracker.add(new Item("second"));
        tracker.add(new Item("third"));
        assertThat(tracker.findAll().size(), is(3));
    }

    @Test
    public void findByNameTest() {
        SqlTracker tracker = new SqlTracker(connection);
        tracker.add(new Item("name"));
        assertThat(tracker.findByName("name").get(0).getName(), is("name"));
    }

    @Test
    public void findByIdTest() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("name");
        tracker.add(item);
        String id = tracker.findAll().get(0).getId();
        assertThat(tracker.findById(id).getName(), is("name"));
    }

    @After
    public void dropConnection() throws SQLException {
        connection.close();
    }

    public static Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}