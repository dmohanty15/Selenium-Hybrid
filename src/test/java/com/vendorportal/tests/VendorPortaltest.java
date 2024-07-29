package com.vendorportal.tests;

import com.pages.vendorportal.Dashboard;
import com.pages.vendorportal.LoginPage;
import com.vendorportal.vendorportaltestdata.VendorPortalTestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.ConfigUtilReader;
import util.Constants;
import util.JsonUtil;

import java.io.IOException;

public class VendorPortaltest extends AbstractTest {
    private LoginPage loginPage;
    private Dashboard dashboard;
    private VendorPortalTestData vendorPortalTestData;
private static final Logger log= LoggerFactory.getLogger(VendorPortaltest.class);


    @BeforeTest
    @Parameters("datapath")
    public void setpageObjects(String path) throws IOException {
        this.loginPage = new LoginPage(driver);
        this.dashboard = new Dashboard(driver);
        this.vendorPortalTestData= JsonUtil.getTestdata(path,VendorPortalTestData.class);
    }
    @Test
    public void loginTest() throws InterruptedException {
       // LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html#");
        Assert.assertTrue(loginPage.isAt());
        loginPage.logintoVendorportal(vendorPortalTestData.username(), vendorPortalTestData.password());

    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() throws InterruptedException {
       // Dashboard dashboard = new Dashboard(driver);
        Assert.assertTrue(dashboard.isAt());

        Assert.assertEquals(dashboard.getMonthlyEarning(),vendorPortalTestData.monthlyearning());
        Assert.assertEquals(dashboard.getAnnualEarning(),vendorPortalTestData.annualearnings());
        Assert.assertEquals(dashboard.getProfitMargin(),vendorPortalTestData.profitmargin());
        Assert.assertEquals(dashboard.getAvailableInventory(),vendorPortalTestData.availableinventory());

        dashboard.searchorderhistory(vendorPortalTestData.keywordtoentry());
        int count=dashboard.getSearchresultcount();
        Assert.assertEquals(count,vendorPortalTestData.searchcount());

    }
    @Test(dependsOnMethods = "dashboardTest")
     public void logout() throws InterruptedException {
        dashboard.logoout();
        Assert.assertTrue(loginPage.isAt());

}

}
