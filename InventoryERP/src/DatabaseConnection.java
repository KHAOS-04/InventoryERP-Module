import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Kia
 */

public class DatabaseConnection {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:inventory_database.db");
        config.setDriverClassName("org.sqlite.JDBC");
        config.setMaximumPoolSize(1);
        config.setPoolName("InventoryConnectionPool");

        // SQLite does not enforce FOREIGN KEY rules unless this is turned on,
        // so I run this one line every time a physical connection opens. This
        // is what makes the category_id FK in the items table actually mean
        // something instead of just being decoration.
        config.setConnectionInitSql("PRAGMA foreign_keys = ON;");

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}