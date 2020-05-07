package tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.05.2020
 */
public class SqlTracker implements Store {

    private Connection cn;

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

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = cn.prepareStatement("insert into Items(name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
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

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        int idForDB = Integer.valueOf(id);
        if (!validateStatement(idForDB)) {
            throw new NoSuchElementException("That id not found in Data Base");
        }
        try (PreparedStatement statement = cn.prepareStatement("update Items set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, idForDB);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int idForDB = Integer.valueOf(id);
        if (!validateStatement(idForDB)) {
            throw new NoSuchElementException("That id not found in Data Base");
        }
        try (PreparedStatement statement = cn.prepareStatement("delete from Items where id = ?")) {
            statement.setInt(1, idForDB);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = null;
        int firstID = 1;
        if (!validateStatement(firstID)) {
            throw new NoSuchElementException("That id not found in Data Base");
        }
        try (PreparedStatement statement = cn.prepareStatement("select * from Items")) {
            ResultSet resultSet = statement.executeQuery();
            result = new ArrayList<>();
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

    @Override
    public List<Item> findByName(String key) {
        return null;
    }

    @Override
    public Item findById(String id) {
        return null;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    private boolean validateStatement(int id) {
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement("select id from Items where id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1) > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
