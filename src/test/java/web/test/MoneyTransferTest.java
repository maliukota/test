package web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import web.data.DataHelper;
import web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    private static final String WEBSITE ="http://localhost:9999";

    @Test
    void shouldTransferMoneyBetweenOwnCards(){
        open(WEBSITE);
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val replenishmentPage = dashboardPage.replenishFirstCard();

        val amountOfMoney1 = DataHelper.getAmountOfMoney1000();
        val secondCardInfo = DataHelper.getSecondCardsInfo();
        replenishmentPage.transferAmountFromSecondCard(amountOfMoney1, secondCardInfo);

        dashboardPage.replenishSecondCard();
        replenishmentPage.cleanFields();
        val amountOfMoney2 = DataHelper.getAmountOfMoney5000();
        val firstCardInfo = DataHelper.getFirstCardsInfo();
        replenishmentPage.transferAmountFromFirstCard(amountOfMoney2, firstCardInfo);

        dashboardPage.returnToDashboardPage();
    }
}
