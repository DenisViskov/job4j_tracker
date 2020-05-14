package tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Class has realizes connect tracker to postgreSql
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.05.2020
 */
public class SqlTracker implements Store {
    /**
     * Connection
     */
    private Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public SqlTracker() {
    }

    /**
     * Method execute initialization new connection to data base postgreSQL
     */
    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Method add new Item to data base
     *
     * @param item - item
     * @return - Item
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = cn.prepareStatement("insert into Items(name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.executeUpdate();
            int id;
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                id = resultSet.getInt(1);
            }
            item.setId(String.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Method replace item in data base by given id
     *
     * @param id   - id
     * @param item - item
     * @return - true/false in dependency of result
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        int idForDB = Integer.valueOf(id);
        try (PreparedStatement statement = cn.prepareStatement("update Items set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, idForDB);
            int replacedCount = statement.executeUpdate();
            result = replacedCount > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method has realizes delete item from data base by given ID
     *
     * @param id - id
     * @return - true/false in dependency of result
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int idForDB = Integer.valueOf(id);
        try (PreparedStatement statement = cn.prepareStatement("delete from Items where id = ?")) {
            statement.setInt(1, idForDB);
            int replacedCount = statement.executeUpdate();
            result = replacedCount > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method returns list of Items from data base
     *
     * @return - List of Items
     */
    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from Items");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"));
                item.setId(String.valueOf(resultSet.getInt("id")));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method has looking for Item by given name in parameters
     *
     * @param key - name
     * @return - List of Items
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from Items where name=?")) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(resultSet.getString("name"));
                    item.setId(String.valueOf(resultSet.getInt("id")));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method has looking for Item by ID
     *
     * @param id - id
     * @return - Item
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        int idForDB = Integer.valueOf(id);
        try (PreparedStatement statement = cn.prepareStatement("select * from Items where id = ?")) {
            statement.setInt(1, idForDB);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                result = new Item(resultSet.getString("name"));
                result.setId(String.valueOf(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method overrides close method from AutoClosable Interface for correctly close stream
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
