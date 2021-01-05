package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
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

        val startBalanceOfFirstCard = dashboardPage.getBalanceOfFirstCard() * 100;
        val startBalanceOfSecondCard = dashboardPage.getBalanceOfSecondCard() * 100;

        val transferPage = dashboardPage.plusBalanceFirstCard();
        val transferFrom2To1Card = DataHelper.getCardInfoSecond();
        val transfer = 5000_00;
        transferPage.transfer(transferFrom2To1Card, transfer / 100);
        val balanceFirstCard = DataHelper.getBalanceCardPlus(startBalanceOfFirstCard, transfer);
        val balanceSecondCard = DataHelper.getBalanceCardMinus(startBalanceOfSecondCard, transfer);


        assertEquals(balanceFirstCard, dashboardPage.getBalanceOfFirstCard() * 100);
        assertEquals(balanceSecondCard, dashboardPage.getBalanceOfSecondCard() * 100);
        val transferPage1 = dashboardPage.plusBalanceSecondCard();
        val transferFrom1To2Card = DataHelper.getCardInfoFirst();
        transferPage1.transfer(transferFrom1To2Card, transfer / 100);
    }

    @Test
    void shouldSuccessfulTransferToSecondCard() {
        val dashboardPage = new DashboardPage();
        val startBalanceOfFirstCard = dashboardPage.getBalanceOfFirstCard() * 100;
        val startBalanceOfSecondCard = dashboardPage.getBalanceOfSecondCard() * 100;

        val transferPage = dashboardPage.plusBalanceSecondCard();
        val transferFrom1To2Card = DataHelper.getCardInfoFirst();
        val transfer = 4000_00;
        transferPage.transfer(transferFrom1To2Card, transfer / 100);
        val balanceSecondCard = DataHelper.getBalanceCardPlus(startBalanceOfSecondCard, transfer);
        val balanceFirstCard = DataHelper.getBalanceCardMinus(startBalanceOfFirstCard, transfer);


        assertEquals(balanceFirstCard, dashboardPage.getBalanceOfFirstCard() * 100);
        assertEquals(balanceSecondCard, dashboardPage.getBalanceOfSecondCard() * 100);
        val transferPage1 = dashboardPage.plusBalanceFirstCard();
        val transferFrom2To1Card = DataHelper.getCardInfoSecond();
        transferPage1.transfer(transferFrom2To1Card, transfer / 100);
    }

    @Test
    void shouldSuccessfulTransferWithPenny() {
        val dashboardPage = new DashboardPage();
        val startBalanceOfFirstCard = dashboardPage.getBalanceOfFirstCard() * 100;
        val startBalanceOfSecondCard = dashboardPage.getBalanceOfSecondCard() * 100;

        val transferPage = dashboardPage.plusBalanceSecondCard();
        val transferFrom1To2Card = DataHelper.getCardInfoFirst();
        val transfer = 2050;
        transferPage.transfer(transferFrom1To2Card, transfer / 100);
        val balanceSecondCard = DataHelper.getBalanceCardPlus(startBalanceOfSecondCard, transfer);
        val balanceFirstCard = DataHelper.getBalanceCardMinus(startBalanceOfFirstCard, transfer);


        assertEquals(balanceFirstCard, dashboardPage.getBalanceOfFirstCard() * 100);
        assertEquals(balanceSecondCard, dashboardPage.getBalanceOfSecondCard() * 100);
        val transferPage1 = dashboardPage.plusBalanceFirstCard();
        val transferFrom2To1Card = DataHelper.getCardInfoSecond();
        transferPage1.transfer(transferFrom2To1Card, transfer / 100);
    }

    @Test
    void shouldTransferSumMoreBalance() {
        val dashboardPage = new DashboardPage();
        val startBalanceOfFirstCard = dashboardPage.getBalanceOfFirstCard() * 100;
        val startBalanceOfSecondCard = dashboardPage.getBalanceOfSecondCard() * 100;

        val transferPage = dashboardPage.plusBalanceSecondCard();
        val transferFrom1To2Card = DataHelper.getCardInfoFirst();
        val transfer = 30000_00;
        transferPage.transfer(transferFrom1To2Card, transfer / 100);
        val balanceSecondCard = DataHelper.getBalanceCardPlus(startBalanceOfSecondCard, transfer);
        val balanceFirstCard = DataHelper.getBalanceCardMinus(startBalanceOfFirstCard, transfer);


        assertEquals(balanceFirstCard, dashboardPage.getBalanceOfFirstCard() * 100);
        assertEquals(balanceSecondCard, dashboardPage.getBalanceOfSecondCard() * 100);
        val transferPage1 = dashboardPage.plusBalanceFirstCard();
        val transferFrom2To1Card = DataHelper.getCardInfoSecond();
        transferPage1.transfer(transferFrom2To1Card, transfer / 100);
    }
}
