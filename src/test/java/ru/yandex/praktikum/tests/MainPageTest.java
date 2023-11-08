package ru.yandex.praktikum.tests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobgect.MainPage;
import org.junit.After;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainPageTest {
    String accordeonMessage;
    int messageNumber;
    public MainPageTest (int messageNumber, String accordeonMessage)
    {
        this.messageNumber = messageNumber;
        this.accordeonMessage = accordeonMessage;
    }
    @Parameterized.Parameters
    public static Object[][] getAccordeonMessage() {
        return new Object[][]
                {
                        {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                        {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                        {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                        {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                        {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                        {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                        {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                        {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}

                };
    }
    WebDriver driver = new FirefoxDriver();

    @Test
    public void checkMainPage() {
        driver.get(MainPage.mainPageURL);
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToAccordionMenu();
        mainPage.waitForAccoardeonPannelToBeClickable(messageNumber);
        assertEquals("Текст не соответствует ожидаемому", accordeonMessage, mainPage.getAccordeonText(messageNumber));
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}