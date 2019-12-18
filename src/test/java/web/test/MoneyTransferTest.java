package web.test;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import web.data.DataHelper;
import web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferTest {
    private static final String WEBSITE ="http://localhost:9999";

    @ParameterizedTest
    @CsvFileSource(resources= "/TransferAmountData.csv",numLinesToSkip=1)
    void shouldTransferMoneyBetweenOwnCards(int transferAmount){
        open(WEBSITE);
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);

        val firstCardFirstBalance = dashboardPage.getBalanceFirstCard();
        val replenishmentPage = dashboardPage.replenishFirstCard();
        val amountOfMoney = DataHelper.getTransferAmount(transferAmount);
        val secondCardInfo = DataHelper.getSecondCardInfo();
        replenishmentPage.transferAmountFromSecondCard(transferAmount, secondCardInfo);
        val finalCardFirstBalance = dashboardPage.getBalanceFirstCard();
        val difference = finalCardFirstBalance - firstCardFirstBalance;
        assertEquals(transferAmount, difference);





        dashboardPage.replenishSecondCard();
        replenishmentPage.cleanFields();
        val amountOfMoney2 = DataHelper.getAmountOfMoney5000();
        val firstCardInfo = DataHelper.getFirstCardInfo();
        replenishmentPage.transferAmountFromFirstCard(amountOfMoney2, firstCardInfo);

        dashboardPage.checkReturnToDashboardPage();
    }
}
