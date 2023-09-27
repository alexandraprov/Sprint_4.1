package ru.yandex.praktikum.tests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.After;



@RunWith(Parameterized.class)
public class OrderPageTest{
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String comment;
    private final String date;

    public OrderPageTest(String name, String surname, String address, String phoneNumber, String comment, String date) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getPersonInformation() {
        return new Object[][]{
                {0, "Марина", "Столярова", "ул. Артековская, д.6, кв. 27", "79050909090", "17.09.2023", "без комментариев"},
                {1, "Ангелина", "Ильченко", "ул. Котовская, д.90, кв. 454", "79674657676", "16.09.2023", "без комментариев"},
                {2, "Борис", "Изюмов", "ул. Которовская, д.56, кв. 7", "79093456789", "01.01.01", "без комментариев"},
        };
    }

    WebDriver driver = new FirefoxDriver();


    @Test
    public void checkOrderPageFlowUsingTopOrderButton() {
        driver.get(MainPage.mainPageURL);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickTopOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillPersonalInformation(name, surname, address, phoneNumber);
        objOrderPage.clickNextButton();
        objOrderPage.fillRentInformation(date, comment);
        assertThat(objOrderPage.resultMessage(), containsString("Номер заказа"));
    }

    @Test
    public void checkOrderPageFlowUsingMiddleOrderButton() {
        driver.get(MainPage.mainPageURL);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.scrollToMiddleOrderButton();
        objMainPage.clickMiddleOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillPersonalInformation(name, surname, address, phoneNumber);
        objOrderPage.clickNextButton();
        objOrderPage.fillRentInformation(date, comment);
        assertThat(objOrderPage.resultMessage(), containsString("Номер заказа"));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
