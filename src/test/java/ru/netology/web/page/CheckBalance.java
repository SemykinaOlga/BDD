package ru.netology.web.page;


public class CheckBalance {
    public static int getBalanceCardPlus(int balance, int transfer) {
        int cardBalancePlus = balance + transfer;
        return cardBalancePlus;
    }

    public static int getBalanceCardMinus(int balance, int transfer) {
        int cardBalanceMinus = balance - transfer;
        if (cardBalanceMinus < 0) {
            return balance;
        }
        return cardBalanceMinus;
    }
}
