package com.flightreservationpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {
    WebDriver driver;

    @FindBy(id="go-to-flights-search")
    private WebElement goToFlightSearch_btn;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);


    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearch_btn));
        return this.goToFlightSearch_btn.isDisplayed();
    }

    public void clickGoToFlightSearch_btn() {
       this.goToFlightSearch_btn.click();
    }
}
