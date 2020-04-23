package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接mysql数据库
 */
public final class DbConn {

    public static Connection getconn(){
        Connection conn = null;

        String user = "root";
        String password = "Aa1183787376";
        String url = "jdbc:mysql://localhost:3306/shoppingms?serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
