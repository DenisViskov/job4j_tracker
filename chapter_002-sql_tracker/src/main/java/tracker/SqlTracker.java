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
    private final Connection connection;

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method execute initialization new connection to data base postgreSQL
     *
     * @return - Connection
     */
    @Override
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password"));
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
        try (PreparedStatement statement = connection.prepareStatement("insert into Items(name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
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
        if (!validateStatement(idForDB)) {
            throw new NoSuchElementException("That id not found in Data Base");
        }
        try (PreparedStatement statement = connection.prepareStatement("update Items set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, idForDB);
            statement.executeUpdate();
            result = true;
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
        if (!validateStatement(idForDB)) {
            throw new NoSuchElementException("That id not found in Data Base");
        }
        try (PreparedStatement statement = connection.prepareStatement("delete from Items where id = ?")) {
            statement.setInt(1, idForDB);
            statement.executeUpdate();
            result = true;
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
        try (PreparedStatement statement = connection.prepareStatement("select * from Items")) {
            ResultSet resultSet = statement.executeQuery();
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
        try (PreparedStatement statement = connection.prepareStatement("select * from Items where name=?")) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
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
     * Method has looking for Item by ID
     *
     * @param id - id
     * @return - Item
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        int idForDB = Integer.valueOf(id);
        if (!validateStatement(idForDB)) {
            throw new NoSuchElementException("That id not found in Data Base");
        }
        try (PreparedStatement statement = connection.prepareStatement("select * from Items where id = ?")) {
            statement.setInt(1, idForDB);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = new Item(resultSet.getString("name"));
            result.setId(String.valueOf(resultSet.getInt("id")));
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
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * In that method executing validation our sql statement by given ID on existing object in data base
     *
     * @param id - id
     * @return - true/false in dependency of result
     */
    private boolean validateStatement(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("select id from Items where id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1) == id ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
