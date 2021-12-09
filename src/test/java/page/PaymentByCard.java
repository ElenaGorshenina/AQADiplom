package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static data.DataHelper.getNumberCardApproved;

@Data
public class PaymentByCard {
    private SelenideElement formPayment  = $(byText("Оплата по карте"));
    //private SelenideElement numberCard  = $(byText("Номер карты"));
    private SelenideElement numberCard  = $(byText("Номер карты")).doubleClick();
    private SelenideElement month  = $(byText("Месяц"));
    private SelenideElement year  = $(byText("Год"));
    private SelenideElement cardowner  = $(byText("Владелец"));
    private SelenideElement cvc  = $(byText("CVC/CVV"));
    private SelenideElement button  = $(byText("Продолжить"));

    public PaymentByCard() {
        formPayment.shouldBe(Condition.visible);
    }

    public PaymentByCard validNumberCard(DataHelper getNumberCardApproved) {
        numberCard.setValue(getNumberCardApproved());
        return new PaymentByCard();
    }
}
