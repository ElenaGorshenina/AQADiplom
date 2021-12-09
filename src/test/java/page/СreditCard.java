package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class СreditCard {
    private SelenideElement formСredit  = $(byText("Кредит по данным карты"));
    private SelenideElement numberCard  = $(byText("Номер карты"));
    private SelenideElement month  = $(byText("Месяц"));
    private SelenideElement year  = $(byText("Год"));
    private SelenideElement cardowner  = $(byText("Владелец"));
    private SelenideElement cvc  = $(byText("CVC/CVV"));
    private SelenideElement button  = $(byText("Продолжить"));

    public СreditCard() {
        formСredit.shouldBe(Condition.visible);
    }
}
