package test;

import data.BDclass;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CreditCard;
import page.PaymentByCard;
import page.WebService;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {

    @BeforeEach
    void setUp(){
        open("http://localhost:8080");
    }

    @BeforeEach
    static void deleteTable() {
        BDclass.deleteTable();
    }

    //1. Валидная покупка в кредит
    @Test
    void validCardCreditTest () {
//        BDclass.deleteTable();
//        var webService = open("http://185.119.57.9:8080", WebService.class);
//        var creditCard = webService.creditCard();
        var webService = new WebService();
        var creditCard = new CreditCard();
        webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
        var creditStatus = BDclass.statusCreditCard();
        assertEquals("APPROVED", creditStatus);
    }

    //2. Отправка формы "Кредит по данным карты" с вводом невалидного номера карты.
    @Test
    void noValidCardCreditTest () {
//        BDclass.deleteTable();
//        var webService = open("http://185.119.57.9:8080", WebService.class);
//        var creditCard = webService.creditCard();
        var webService = new WebService();
        var creditCard = new CreditCard();
        webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardDeclined();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.errorCard();
//проверка записи в бд
    }

/*    //3. Отправка формы "Кредит по данным карты" с неверным форматом полей (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardSymbol();
        var setMonth = DataHelper.getMonthSymbol();
        var setYear = DataHelper.getYearSymbol();
        var setCardowner = DataHelper.getCardownerSymbol();
        var setCvc = DataHelper.getCvcSymbol();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.numberCardRequiredToFill();
        creditCard.monthSubRequiredToFill();
        creditCard.yearSubRequiredToFill();
        creditCard.cardownerSubRequiredToFill();
        creditCard.cvcSubRequiredToFill();
    }

    //4. Отправка формы "Кредит по данным карты" с неверным форматом полей (количество символов меньше)
    @Test
    void wrongFormatLessQuantityCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardLessQuantity();
        var setMonth = DataHelper.getMonthLessQuantity();
        var setYear = DataHelper.getYearLessQuantity();
        var setCardowner = DataHelper.getCardownerLessQuantity();
        var setCvc = DataHelper.getCvcLessQuantity();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.numberCardWrongFormat();
        creditCard.monthSubWrongFormat();
        creditCard.yearSubWrongFormat();
        creditCard.cardownerSubWrongFormat();
        creditCard.cvcSubWrongFormat();
    }

    //5. Отправка формы "Кредит по данным карты" с неверным форматом полей (количество символов больше)
    @Test
    void wrongFormatMoreQuantityCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardMoreQuantity();
        var setMonth = DataHelper.getMonthMoreQuantity();
        var setYear = DataHelper.getYearMoreQuantity();
        var setCardowner = DataHelper.getCardownerMoreQuantity();
        var setCvc = DataHelper.getCvcMoreQuantity();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.numberCardMoreQuantity();
    }

    //6. Отправка формы "Кредит по данным карты" с истекшим сроком карты по месяцу.
    @Test
    void CreditByCardExpiredMonthTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthNoVal();
        var setYear = DataHelper.getYearNow();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.expiredDate();
    }

    //7. Отправка формы "Кредит по данным карты" с истекшим сроком карты по году.
    @Test
    void CreditByCardExpiredYearTest () {
        BDclass.deleteTable();
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearNoVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.expiredDate();
    }

    //8. Отправка формы "Кредит по данным карты" с неверным форматом месяца (ввод нулей)
    @Test
    void MonthZeroCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthZero();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //9. Отправка формы "Кредит по данным карты" с неверным форматом года (ввод нулей)
    @Test
    void YearZeroCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearZero();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.expiredDate();
    }

    //10. Отправка формы "Кредит по данным карты" с пустым полем "Номер карты"
    @Test
    void NumberCardEmptyCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardEmpty();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //11. Отправка формы "Кредит по данным карты" с пустым полем "Месяц"
    @Test
    void MonthEmptyCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthEmpty();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //12. Отправка формы "Кредит по данным карты" с пустым полем "Год"
    @Test
    void YearEmptyCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearEmpty();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //13. Отправка формы "Кредит по данным карты" с пустым полем "Владелец"
    @Test
    void CardownerEmptyCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerEmpty();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //14. Отправка формы "Кредит по данным карты" с пустым полем "CVC/CVV"
    @Test
    void CvcEmptyCreditTest () {
        var webService = open("http://185.119.57.9:8080", WebService.class);
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcEmpty();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    } */
}
