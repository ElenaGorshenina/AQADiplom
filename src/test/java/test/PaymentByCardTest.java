package test;

import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.PaymentByCard;
import page.WebService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.getNumberCardApproved;

public class PaymentByCardTest {
//    private static Connection getConn() {
//        try {
//            return DriverManager.getConnection("jdbc:mysql://185.119.57.9:8080/app", "app", "pass");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Test
    void validCardPaymentTest () {
        var codeSQL = "DELETE *";
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.click();
        paymentByCard.approved();
//проверка записи в бд
    }

}
