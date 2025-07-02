package qaTestProject.tests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import qaTestProject.base.BaseTest;
import qaTestProject.pages.HomePage;
import qaTestProject.pages.KeyIndicatorsPage;
import qaTestProject.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class SampleTest extends BaseTest {
    @Test()
    public void testCurrencyRates() throws IOException {
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.goToAllRatesPage();
        KeyIndicatorsPage keyIndicatorsPage = new KeyIndicatorsPage();
        ObjectNode rateJson = JsonUtils.createRateJson(keyIndicatorsPage.getYuan(),keyIndicatorsPage.getEuro(), keyIndicatorsPage.getDollarUsa());
        ObjectNode changedRateJson = JsonUtils.changeRateJson(rateJson);
        JsonUtils.writeJsonToFile(changedRateJson);
        ObjectNode currentRateJson = JsonUtils.createRateJson(keyIndicatorsPage.getYuan(),keyIndicatorsPage.getEuro(), keyIndicatorsPage.getDollarUsa());  // currentRateJson - текушее значение на сайте
        ObjectNode rateFromFileJson = JsonUtils.readJsonFromFile(); // rateFromFile эталонное значение
        Assert.assertTrue(JsonUtils.ratesDiffPrint(currentRateJson, rateFromFileJson).isEmpty(), JsonUtils.ratesDiffPrint(currentRateJson, rateFromFileJson));

    }
}
