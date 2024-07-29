package com.flightreservationpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage{
 private static final Logger log= LoggerFactory.getLogger(FlightConfirmationPage.class);
    @FindBy(xpath="//div[contains(text(),'Total Price')]//following-sibling::div")
    private WebElement totalPrice;

    @FindBy(xpath="//div[contains(text(),'Flight Confirmation #')]//following-sibling::div")
    private WebElement flightConfirmation;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isAt()
    {
        this.wait.until(ExpectedConditions.visibilityOf(this.totalPrice));
        return this.totalPrice.isDisplayed();
    }



    public String getPrice()
    {
        String confirmation= this.flightConfirmation.getText();
        String price=this.totalPrice.getText();
        log.info("flight confirmation :{}"+confirmation);
        log.info("price of flights :{}"+price);
        return price;
            }

}
