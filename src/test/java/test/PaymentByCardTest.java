package test;

import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.WebService;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.getNumberCardApproved;

public class PaymentByCardTest {

    @Test
    void validCardPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var numberCard = DataHelper.getNumberCardApproved();

    }

}
