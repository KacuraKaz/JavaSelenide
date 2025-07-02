package qaTestProject.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


public class HomePage {

    private SelenideElement allRates    = $x("//a[@class='main-indicator_rates-link']");

    public HomePage openPage() {
        open("https://cbr.ru/");
        return this;
    }

    public void goToAllRatesPage() {
        allRates.click();
    }
}


