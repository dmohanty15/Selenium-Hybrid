package com.flightreservationpage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage{

    @FindBy(id="firstName")
    private WebElement firstname_input;

    @FindBy(id="lastName")
    private WebElement lastname_input;

    @FindBy(id="email")
    private WebElement email_input;

    @FindBy(id="password")
    private WebElement password_input;

    @FindBy(name="street")
    private WebElement street_input;

    @FindBy(name="city")
    private WebElement city_input;

    @FindBy(name="zip")
    private WebElement zip_input;

    @FindBy(id="register-btn")
    private WebElement register_btn;

    public RegistrationPage(WebDriver driver) {
      super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstname_input));
     return   this.firstname_input.isDisplayed();

    }

    public void   goTo(String url)
    {
        this.driver.get(url);
    }
    public void enterFirstname(String firstname) {
        firstname_input.sendKeys(firstname);
    }

    public void enteruserdetails(String firstname, String lastname) {
         firstname_input.sendKeys(firstname);
         lastname_input.sendKeys(lastname);
    }

    public void usercreds(String email, String password) {
        email_input.sendKeys(email);
        password_input.sendKeys(password);
    }

    public void enteruserAddressdetails(String street, String city, String zip) {
        street_input.sendKeys(street);
        city_input.sendKeys(city);
        zip_input.sendKeys(zip);

    }
    public void clickRegisterBtn() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.register_btn));
        //register_btn.click();
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",this.register_btn);
    }
}
