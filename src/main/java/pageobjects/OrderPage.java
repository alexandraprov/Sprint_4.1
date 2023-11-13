package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class OrderPage {
    public static String orderPageURL = "https://qa-scooter.praktikum-services.ru/order";
    private WebDriver driver;
    public  OrderPage (WebDriver driver)
    {
        this.driver = driver;
    }

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By rentDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");


    public void setName(String name)
    {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setSurname(String surname)
    {
        driver.findElement(surnameField).sendKeys(surname);
    }
    public void setAddress(String address)
    {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setPhone(String phone)
    {
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void fillPersonalInformation(String name, String surname, String address, String phoneNumber)
    {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setPhone(phoneNumber);
        driver.findElement(By.xpath(".//input[@placeholder='* Станция метро']")).click();
        driver.findElement(By.xpath(".//div[@class='select-search__select']/ul/li[@data-value='2']")).click();
    }
    public void clickNextButton () { //переходим на следующий этап
        driver.findElement(By.className("Button_Middle__1CSJM")).click();
    }



    public void setRentDate (String date){
        driver.findElement(rentDate).sendKeys(date);
        driver.findElement(By.xpath(".//div[@tabindex='0' and @role='button']")).click();
    }
    public void setCommentForCourier (String comment){
        driver.findElement(commentForCourier).sendKeys(comment);
    }
    public void setRentalPeriod (){
        driver.findElement(By.xpath(".//div[@class='Dropdown-control']")).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div['2']")).click();
    }
    public void fillRentInformation(String date, String courierComment) {
        setRentDate(date);
        setRentalPeriod();
        driver.findElement(By.xpath(".//input[@id='black']")).click();
        setCommentForCourier(courierComment);
        driver.findElement(By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']")).click();
        driver.findElement(By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']")).click();
        Duration duration =  Duration.ofSeconds(5);
        new WebDriverWait(driver, duration).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath(".//div[@class='Order_Text__2broi']")), "Номер заказа"));

    }
    public String resultMessage()
    {
        return driver.findElement(By.xpath(".//div[@class='Order_Text__2broi']")).getText();
    }

}
