package qaTestProject.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


public class KeyIndicatorsPage {

    private SelenideElement yuanRate    = $x("//div[text()='Юань']/ancestor::tr/td[last()]");
    private SelenideElement euroRate    = $x("//div[text()='Евро']/ancestor::tr/td[last()]");
    private SelenideElement dollarUsaRate    = $x("//div[text()='Доллар США']/ancestor::tr/td[last()]");

    public Double getYuan() {
        return Double.valueOf(yuanRate.getText().replace(',','.'));
    }

    public Double getEuro() {
        return Double.valueOf(euroRate.getText().replace(',','.'));
    }
    public Double getDollarUsa(){
        return Double.valueOf(dollarUsaRate.getText().replace(',','.'));
    }
}

