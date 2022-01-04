package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDclass {

//    private static Connection getConn() {
//        try {
//            return DriverManager.getConnection("jdbc:mysql://185.119.57.9:3306/app", "app", "pass");
//           // return DriverManager.getConnection("jdbc:postgresql://185.119.57.9:5432/app", "app", "pass");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(
                System.getProperty("url"),
                System.getProperty("db.login"),
                System.getProperty("db.password")
        );
    }

    @BeforeEach
    public static void deleteTable(){
        var delPaymentByCard = "DELETE FROM payment_entity";
        var delCreditCard = "DELETE FROM credit_request_entity";
        var delOrder = "DELETE FROM order_entity";
        var runner = new QueryRunner();
        try (var conn = getConn()) {
            runner.update(conn, delPaymentByCard);
            runner.update(conn, delCreditCard);
            runner.update(conn, delOrder);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @SneakyThrows
    public static String statusPaymentByCard() {
        try (var conn = getConn();
             var countStmt = conn.createStatement()) {
            var status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
            var resultSet = countStmt.executeQuery(status);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        }
        return null;
    }

    @SneakyThrows
    public static String statusCreditCard() {
        var status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (var conn = getConn()){
            String statusCredit = runner.query(conn, status, new ScalarHandler<>());
        }
        return null;
    }
}
