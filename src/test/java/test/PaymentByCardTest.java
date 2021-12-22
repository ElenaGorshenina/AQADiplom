package test;

import data.BDclass;
import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.PaymentByCard;
import page.WebService;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.getNumberCardApproved;

public class PaymentByCardTest {

    //1. Валидная оплата по карте
    @Test
    void validCardPaymentTest () {
        //BDclass.deleteTable();
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.approved();
//проверка записи в бд
    }

    //2. Отправка формы "Оплата по карте" с вводом невалидного номера карты.
    @Test
    void noValidCardPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardDeclined();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.errorCard();
//проверка записи в бд
    }

    //3. Отправка формы "Оплата по карте" с неверным форматом полей (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardSymbol();
        var setMonth = DataHelper.getMonthSymbol();
        var setYear = DataHelper.getYearSymbol();
        var setCardowner = DataHelper.getCardownerSymbol();
        var setCvc = DataHelper.getCvcSymbol();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.numberCardRequiredToFill();
        paymentByCard.monthSubRequiredToFill();
        paymentByCard.yearSubRequiredToFill();
        paymentByCard.cardownerSubRequiredToFill();
        paymentByCard.cvcSubRequiredToFill();
    }

    //4. Отправка формы "Оплата по карте" с неверным форматом полей (количество символов меньше)
    @Test
    void wrongFormatLessQuantityPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardLessQuantity();
        var setMonth = DataHelper.getMonthLessQuantity();
        var setYear = DataHelper.getYearLessQuantity();
        var setCardowner = DataHelper.getCardownerLessQuantity();
        var setCvc = DataHelper.getCvcLessQuantity();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.numberCardWrongFormat();
        paymentByCard.monthSubWrongFormat();
        paymentByCard.yearSubWrongFormat();
        paymentByCard.cardownerSubWrongFormat();
        paymentByCard.cvcSubWrongFormat();
    }

    //5. Отправка формы "Оплата по карте" с неверным форматом полей (количество символов больше)
    @Test
    void wrongFormatMoreQuantityPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardMoreQuantity();
        var setMonth = DataHelper.getMonthMoreQuantity();
        var setYear = DataHelper.getYearMoreQuantity();
        var setCardowner = DataHelper.getCardownerMoreQuantity();
        var setCvc = DataHelper.getCvcMoreQuantity();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.numberCardMoreQuantity();
    }

    //6. Отправка формы "Оплата по карте" с истекшим сроком карты по месяцу.
    @Test
    void PaymentByCardExpiredMonthTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        //var setMonth = DataHelper.getMonthNoVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        //paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.expiredDate();
    }

    //7. Отправка формы "Оплата по карте" с истекшим сроком карты по году.
    @Test
    void PaymentByCardExpiredYearTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearNoVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.expiredDate();
    }

    //8. Отправка формы "Оплата по карте" с неверным форматом месяца (ввод нулей)
    @Test
    void MonthZeroPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthZero();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //9. Отправка формы "Оплата по карте" с неверным форматом года (ввод нулей)
    @Test
    void YearZeroPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearZero();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.expiredDate();
    }

    //10. Отправка формы "Оплата по карте" с пустым полем "Номер карты"
    @Test
    void NumberCardEmptyPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardEmpty();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.obligatory();
    }

    //11. Отправка формы "Оплата по карте" с пустым полем "Месяц"
    @Test
    void MonthEmptyPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthEmpty();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.obligatory();
    }

    //12. Отправка формы "Оплата по карте" с пустым полем "Год"
    @Test
    void YearEmptyPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearEmpty();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.obligatory();
    }

    //13. Отправка формы "Оплата по карте" с пустым полем "Владелец"
    @Test
    void CardownerEmptyPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerEmpty();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.obligatory();
    }

    //14. Отправка формы "Оплата по карте" с пустым полем "CVC/CVV"
    @Test
    void CvcEmptyPaymentTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcEmpty();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.obligatory();
    }
}
