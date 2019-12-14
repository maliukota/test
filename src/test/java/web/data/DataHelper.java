package web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo{
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode{
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class FirstCardsInfo{
        private String firstCard;
    }
    public static FirstCardsInfo getFirstCardsInfo(){
        return new FirstCardsInfo("5559000000000001");
    }

    @Value
    public static class SecondCardsInfo{
        private String secondCard;
    }
    public static SecondCardsInfo getSecondCardsInfo(){
        return new SecondCardsInfo("5559000000000002");
    }

    @Value
    public static class AmountOfMoney1000{
        private String amountOfMoney1000;
    }
    public static AmountOfMoney1000 getAmountOfMoney1000(){
        return new AmountOfMoney1000("1000");
    }

    @Value
    public static class AmountOfMoney5000{
        private String amountOfMoney5000;
    }
    public static AmountOfMoney5000 getAmountOfMoney5000(){
        return new AmountOfMoney5000("5000");
    }
}
