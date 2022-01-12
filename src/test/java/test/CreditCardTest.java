package test;

import data.BDclass;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.WebService;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {

    @BeforeEach
    void setUp(){
        open("http://185.119.57.9:8080");
    }

    @BeforeEach
    void deleteTable() {
        BDclass.deleteTable();
    }

    //1. Валидная покупка в кредит
    @Test
    void validCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
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
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardDeclined();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.errorCard();
//проверка записи в бд
    }

    //3. Отправка формы "Кредит по данным карты" с неверным форматом поля "Номер карты" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardSymbol();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //4. Отправка формы "Кредит по данным карты" с неверным форматом поля "Номер карты" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardLessQuantity();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //5. Отправка формы "Кредит по данным карты" с неверным форматом поля "Номер карты" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityCardCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardMoreQuantity();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.errorCard();
    }

    //6. Отправка формы "Кредит по данным карты" с истекшим сроком карты по месяцу.
    @Test
    void CreditByCardExpiredMonthTest () {
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
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
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcEmpty();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //15. Отправка формы "Кредит по данным карты" с неверным форматом поля "Месяц" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthSymbol();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.obligatory();
    }

    //16. Отправка формы "Кредит по данным карты" с неверным форматом поля "Год" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearSymbol();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //17. Отправка формы "Кредит по данным карты" с неверным форматом поля "Владелец" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolCardownerCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerSymbol();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //18. Отправка формы "Кредит по данным карты" с неверным форматом поля "CVC/CVV" (ввод букв, цифр, символов)
    @Test
    void wrongFormatSymbolCvcCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcSymbol();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //19. Отправка формы "Кредит по данным карты" с неверным форматом поля "Месяц" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthLessQuantity();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //20. Отправка формы "Кредит по данным карты" с неверным форматом поля "Год" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearLessQuantity();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //21. Отправка формы "Кредит по данным карты" с неверным форматом поля "Владелец" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityCardownerCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerLessQuantity();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }

    //22. Отправка формы "Кредит по данным карты" с неверным форматом поля "CVC/CVV" (количество символов меньше)
    @Test
    void wrongFormatLessQuantityCvcCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcLessQuantity();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.wrongFormat();
    }

    //23. Отправка формы "Кредит по данным карты" с неверным форматом поля "Месяц" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityMonthCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthMoreQuantity();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }

    //24. Отправка формы "Кредит по данным карты" с неверным форматом поля "Год" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityYearCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearMoreQuantity();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }

    //25. Отправка формы "Кредит по данным карты" с неверным форматом поля "Владелец" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityCardownerCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerMoreQuantity();
        var setCvc = DataHelper.getCvcVal();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.numberCardMoreQuantity();
    }

    //26. Отправка формы "Кредит по данным карты" с неверным форматом поля "CVC/CVV" (количество символов больше)
    @Test
    void wrongFormatMoreQuantityCvcCreditTest () {
        var webService = new WebService();
        var creditCard = webService.creditCard();
        var setNumberCard = DataHelper.getNumberCardApproved();
        var setMonth = DataHelper.getMonthVal();
        var setYear = DataHelper.getYearVal();
        var setCardowner = DataHelper.getCardownerValid();
        var setCvc = DataHelper.getCvcMoreQuantity();
        creditCard.fillingFormCredit(setNumberCard, setMonth, setYear, setCardowner, setCvc);
        creditCard.approved();
    }
}
