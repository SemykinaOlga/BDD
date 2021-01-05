package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.security.Key;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement cardFromInput = $("[data-test-id=from] input");
    private SelenideElement buttonTransferAction = $("[data-test-id=action-transfer]");

    public TransferPage() {
        amountInput.shouldBe(visible);
    }

    public void transfer(DataHelper.CardInfo info, int transfer) {
        amountInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        amountInput.sendKeys(Keys.BACK_SPACE);
        amountInput.setValue(String.valueOf(transfer));
        cardFromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        cardFromInput.sendKeys(Keys.BACK_SPACE);
        cardFromInput.setValue(info.getNumberOfCard());
        buttonTransferAction.click();
    }
}