package ru.netology.web.data;

import lombok.Value;
import lombok.val;

import java.math.BigDecimal;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {

        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {

        private String numberOfCard;
    }

    public static CardInfo getCardInfoFirst() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getCardInfoSecond() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static long getBalanceCardPlus(long balance, long transfer) {
        long cardBalancePlus = balance + transfer;
        return cardBalancePlus;
    }

    public static long getBalanceCardMinus(long balance, long transfer) {
        long cardBalanceMinus = balance - transfer;
        if (cardBalanceMinus < 0) {
            return balance;
        }
        return cardBalanceMinus;
    }
}