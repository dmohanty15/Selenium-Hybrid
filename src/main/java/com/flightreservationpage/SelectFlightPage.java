package com.flightreservationpage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectFlightPage extends AbstractPage{

    @FindBy(name="departure-flight")
    private List<WebElement> departure_radiobtn;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrival_radiobtn;

    @FindBy(id="confirm-flights")
    private WebElement confirm_btn;

    public SelectFlightPage(WebDriver driver) {

        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirm_btn));
        return this.confirm_btn.isDisplayed();
    }
    public void selectflights()
    {
        int random= ThreadLocalRandom.current().nextInt(0,departure_radiobtn.size());
        this.departure_radiobtn.get(random).click();
       // this.arrival_radiobtn.get(random).click();

    }

   public void clickConfirmbutton()
   {
       this.wait.until(ExpectedConditions.elementToBeClickable(this.confirm_btn));
     //  this.confirm_btn.click();
       JavascriptExecutor js = (JavascriptExecutor)this.driver;

               js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
       js.executeScript("arguments[0].click()",this.confirm_btn);
   }
}
