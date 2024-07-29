package com.pages.vendorportal;

import com.flightreservationpage.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Dashboard extends AbstractPage {
private static final Logger log= LoggerFactory.getLogger(Dashboard.class);
    @FindBy(id="monthly-earning")
    private WebElement monthlyEarning;

    @FindBy(id="annual-earning")
    private WebElement annualEarning;

    @FindBy(id="profit-margin")
    private WebElement profitMargin;

    @FindBy(id="available-inventory")
    private WebElement availableInventory;

    @FindBy(xpath="//div[@id='dataTable_filter']//input")
    private WebElement filter;

    @FindBy(id="dataTable_info")
    private WebElement info;

    @FindBy(xpath="//span[starts-with(@class,'mr')]//following-sibling::img")
    private WebElement image;

    @FindBy(xpath="//a[contains(text(),'Logout')]")
    private WebElement logout;

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.annualEarning));
        return this.annualEarning.isDisplayed();
        }

        public String getMonthlyEarning(){

        return this.monthlyEarning.getText();
        }

        public String getAnnualEarning(){
        return this.annualEarning.getText();
        }
        public String getProfitMargin(){
        return this.profitMargin.getText();
        }

        public String getAvailableInventory(){
        return this.availableInventory.getText();
        }

        public void searchorderhistory(String keyword)
        {
            this.filter.sendKeys(keyword);

        }

        public int getSearchresultcount()
        {
           String input= this.info.getText();
          String[] strarrayinfo= input.split(" ");
          int entry=Integer.parseInt(strarrayinfo[5]);
          log.info("the no of entries are "+entry);

          return entry;

        }

        public void logoout()
        {
            this.wait.until(ExpectedConditions.visibilityOf(this.image));
           // this.image.click();
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",this.image);
            this.wait.until(ExpectedConditions.visibilityOf(this.logout));
            jse.executeScript("arguments[0].click();",this.logout);
            this.wait.until(ExpectedConditions.visibilityOf(this.logout));
            jse.executeScript("arguments[0].click();",this.logout);
        }
}
