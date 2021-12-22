package data;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDclass {

    private static Connection getConn() {
        try {
            return DriverManager.getConnection("jdbc:mysql://185.119.57.9:8080/app", "app", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public static void deleteTable(){
        var delBD = "DELETE FROM *";
        var runner = new QueryRunner();
//        try (var conn = getConn()) {
//            var del = runner.query(conn, delBD, ?);
//        }
//        catch (SQLException exception) {
//            exception.printStackTrace();
//        }
    }
}
