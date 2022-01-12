package test;

import data.BDclass;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.WebService;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentByCardTest {

    @BeforeEach
    void setUp(){
        open("http://185.119.57.9:8080");
    }

    @BeforeEach
    void deleteTable() {
        BDclass.deleteTable();
    }

    //1. Валидная оплата по карте
    @Test
    void validCardPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.approved();
        var paymentStatus = BDclass.statusPaymentByCard();
        assertEquals("APPROVED", paymentStatus);
    }

    //2. Отправка формы "Оплата по карте" с вводом невалидного номера карты.
    @Test
    void noValidCardPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardDeclined();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.errorCard();
        var paymentStatus = BDclass.statusPaymentByCard();
        assertEquals("DECLINED", paymentStatus);
    }

    //3. Отправка формы "Оплата по карте" с неверным форматом поля "Номер карты" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolCardPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardSymbol();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

   //4. Отправка формы "Оплата по карте" с неверным форматом поля "Номер карты" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityCardPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardLessQuantity();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //5. Отправка формы "Оплата по карте" с неверным форматом поля "Номер карты" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityCardPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardMoreQuantity();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.errorCard();
    }

   //6. Отправка формы "Оплата по карте" с истекшим сроком карты по месяцу.
    @Test
    void PaymentByCardExpiredMonthTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthNoVal();
        var setYear = DataHelper.getYearNow();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.expiredDate();
    }

    //7. Отправка формы "Оплата по карте" с истекшим сроком карты по году.
    @Test
    void PaymentByCardExpiredYearTest () {
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcEmpty();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.obligatory();
    }

    //15. Отправка формы "Оплата по карте" с неверным форматом поля "Месяц" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolMonthPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthSymbol();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //16. Отправка формы "Оплата по карте" с неверным форматом поля "Год" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolYearPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearSymbol();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //17. Отправка формы "Оплата по карте" с неверным форматом поля "Владелец" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolCardownerPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerSymbol();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //18. Отправка формы "Оплата по карте" с неверным форматом поля "CVC/CVV" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolCvcPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcSymbol();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //19. Отправка формы "Оплата по карте" с неверным форматом поля "Месяц" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityMonthPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthLessQuantity();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //20. Отправка формы "Оплата по карте" с неверным форматом поля "Год" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityYearPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearLessQuantity();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //21. Отправка формы "Оплата по карте" с неверным форматом поля "Владелец" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityCardownerPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerLessQuantity();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //22. Отправка формы "Оплата по карте" с неверным форматом поля "CVC/CVV" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityCvcPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcLessQuantity();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.wrongFormat();
    }

    //23. Отправка формы "Оплата по карте" с неверным форматом поля "Месяц" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityMonthPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthMoreQuantity();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.approved();
    }

    //24. Отправка формы "Оплата по карте" с неверным форматом поля "Год" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityYearPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearMoreQuantity();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.approved();
    }

    //25. Отправка формы "Оплата по карте" с неверным форматом поля "Владелец" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityCardownerPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerMoreQuantity();
        var setCvc = DataHelper.getCvcVal();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.numberCardMoreQuantity();
    }

    //26. Отправка формы "Оплата по карте" с неверным форматом поля "CVC/CVV" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityCvcPaymentTest () {
        var webService = new WebService();
        var paymentByCard = webService.paymentByCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcMoreQuantity();
        paymentByCard.fillingFormPayment(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        paymentByCard.approved();
    }
}
