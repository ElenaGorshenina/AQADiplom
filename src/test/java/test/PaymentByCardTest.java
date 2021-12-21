package test;

import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.PaymentByCard;
import page.WebService;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.getNumberCardApproved;

public class PaymentByCardTest {

    @Test
    void validCardPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.click();
        paymentByCard.approved();
//проверка записи в бд
    }

}
