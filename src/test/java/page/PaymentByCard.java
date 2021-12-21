package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static data.DataHelper.*;

@Data
public class PaymentByCard {
    private SelenideElement formPayment = $(byText("Оплата по карте"));
    private SelenideElement numberCard = $(By.cssSelector("input[placeholder='0000 0000 0000 0000']"));
    private SelenideElement month = $(By.cssSelector("input[placeholder='08']"));
    private SelenideElement year = $(By.cssSelector("input[placeholder='22']"));
    private SelenideElement cardowner = $(By.xpath("//span[text()='Владелец']/..//input"));
    private SelenideElement cvc = $(By.cssSelector("input[placeholder='999']"));
    private SelenideElement button = $(By.xpath("//span[text()='Продолжить']/..//"));
    private SelenideElement approved = $(byText("Успешно"));
    private SelenideElement errorWindow = $(byText("Успешно"));

    public PaymentByCard() {
        formPayment.shouldBe(Condition.visible);
    }

    public void fillingFormPayment(DataHelper.NumberCardClass setNumberCard, String setMonth, String setYear, String setCardowner, String setCvc) {
        numberCard.setValue(setNumberCard.getNumberCard());
        month.setValue(setMonth);
        year.setValue(setYear);
        cardowner.setValue(setCardowner);
        cvc.setValue(setCvc);
        button.click();
    }

    private SelenideElement numberCardSub = $(By.cssSelector("[placeholder='0000 0000 0000 0000'] .input__sub"));
    private SelenideElement monthSub = $(By.cssSelector("[placeholder='08'] .input__sub"));
    private SelenideElement yearSub = $(By.cssSelector("[placeholder='22'] .input__sub"));
    private SelenideElement cardownerSub = $(By.xpath("//span[text()='Владелец']/.input__sub//"));
    private SelenideElement cvcSub = $(By.cssSelector("[placeholder='999'] .input__sub"));

    public void click() {
        button.click();
    }

    public void approved() {
        approved.shouldHave(exactText("Операция одобрена Банком."), appear);
    }
    public void errorCard() {
        errorWindow.shouldHave(exactText("Ошибка! Банк отказал в проведении операции."), appear);
    }

    public void numberCardRequiredToFill () {
        numberCardSub.shouldHave(exactText("Поле обязательно для заполнения"));
    }
    public void monthSubRequiredToFill () {
        monthSub.shouldHave(exactText("Поле обязательно для заполнения"));
    }
    public void yearSubRequiredToFill () {
        yearSub.shouldHave(exactText("Поле обязательно для заполнения"));
    }
    public void cardownerSubRequiredToFill () {
        cardownerSub.shouldHave(exactText("Поле обязательно для заполнения"));
    }
    public void cvcSubRequiredToFill () {
        cvcSub.shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
