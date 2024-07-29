package com.flightreservationpage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage{

    @FindBy(id="passengers")
    private WebElement passengers_drpdown;

    @FindBy(id="search-flights")
    private WebElement searchFlights_btn;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengers_drpdown));
        return this.passengers_drpdown.isDisplayed();
    }

    public void selectPassengers(String noofpassengers){
       Select select = new Select(this.passengers_drpdown);
       select.selectByValue(noofpassengers);
    }

    public void clickSearchFlights(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.searchFlights_btn));
      //  this.searchFlights_btn.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", this.searchFlights_btn);

    }
}
