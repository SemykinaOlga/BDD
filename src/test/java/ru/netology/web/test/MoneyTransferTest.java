package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CheckBalance;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void shouldVerify() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldSuccessfulTransferToFirstCard() {
        val dashboardPage = new DashboardPage();

        int startBalanceOfFirstCard = dashboardPage.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = dashboardPage.getBalanceOfSecondCard();

        val transferPage = dashboardPage.plusBalanceFirstCard();
        val transferFrom2To1Card = DataHelper.getCardInfoSecond();
        int transfer = 5000;
        transferPage.transfer(transferFrom2To1Card, transfer);
        val balanceFirstCard = CheckBalance.getBalanceCardPlus(startBalanceOfFirstCard, transfer);
        val balanceSecondCard = CheckBalance.getBalanceCardMinus(startBalanceOfSecondCard, transfer);


        assertEquals(balanceFirstCard, dashboardPage.getBalanceOfFirstCard());
        assertEquals(balanceSecondCard, dashboardPage.getBalanceOfSecondCard());
        val transferPage1 = dashboardPage.plusBalanceSecondCard();
        val transferFrom1To2Card = DataHelper.getCardInfoFirst();
        transferPage1.transfer(transferFrom1To2Card, transfer);
    }

    @Test
    void shouldSuccessfulTransferToSecondCard() {
        val dashboardPage = new DashboardPage();
        int startBalanceOfFirstCard = dashboardPage.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = dashboardPage.getBalanceOfSecondCard();

        val transferPage = dashboardPage.plusBalanceSecondCard();
        val transferFrom1To2Card = DataHelper.getCardInfoFirst();
        int transfer = 4000;
        transferPage.transfer(transferFrom1To2Card, transfer);
        val balanceSecondCard = CheckBalance.getBalanceCardPlus(startBalanceOfSecondCard, transfer);
        val balanceFirstCard = CheckBalance.getBalanceCardMinus(startBalanceOfFirstCard, transfer);


        assertEquals(balanceFirstCard, dashboardPage.getBalanceOfFirstCard());
        assertEquals(balanceSecondCard, dashboardPage.getBalanceOfSecondCard());
        val transferPage1 = dashboardPage.plusBalanceFirstCard();
        val transferFrom2To1Card = DataHelper.getCardInfoSecond();
        transferPage1.transfer(transferFrom2To1Card, transfer);
    }
}
