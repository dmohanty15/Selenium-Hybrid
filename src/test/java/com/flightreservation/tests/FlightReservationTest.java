package com.flightreservation.tests;

import com.flightreservation.model.FlightReservationTestData;
import com.flightreservationpage.*;
import com.vendorportal.tests.AbstractTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import util.ConfigUtilReader;
import util.Constants;
import util.JsonUtil;

import java.io.IOException;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testdata;
    private RegistrationPage registrationPage;
    private RegistrationConfirmationPage registrationConfirmationPage;

    @BeforeSuite
    public void initialize() throws IOException {
        ConfigUtilReader.init();
    }

    @Parameters("testpath")
    @BeforeTest
    public void setUp(String path) throws IOException {

        this.testdata= JsonUtil.getTestdata(path,FlightReservationTestData.class);
        this.registrationPage = new RegistrationPage(driver);


    }

   @AfterTest
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void userregistration() throws InterruptedException {
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
               // this.driver.get("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enteruserdetails(testdata.firstname(),testdata.lastname());
        registrationPage.usercreds(testdata.email(),testdata.password());
        registrationPage.enteruserAddressdetails(testdata.street(),testdata.city(),testdata.zip());
        Thread.sleep(5000);
        ((JavascriptExecutor) this.driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        registrationPage.clickRegisterBtn();;
    }

    @Test(dependsOnMethods = "userregistration")
    public void registrationConfirmation()
    {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.clickGoToFlightSearch_btn();

    }

    @Test(dependsOnMethods ="registrationConfirmation")
    public void flightsearchMethod() throws InterruptedException {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        String count=String.valueOf(testdata.passengercount());
        flightSearchPage.selectPassengers(count);
        ((JavascriptExecutor) this.driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        flightSearchPage.clickSearchFlights();
    }

    @Test(dependsOnMethods ="flightsearchMethod")
    public void flightSelection()
    {
        SelectFlightPage selectFlightPage = new SelectFlightPage(driver);
        Assert.assertTrue(selectFlightPage.isAt());
        selectFlightPage.selectflights();
      //  ((JavascriptExecutor) this.driver).executeScript("scroll(100, 1000)");
        selectFlightPage.clickConfirmbutton();

    }

    @Test(dependsOnMethods = "flightSelection")
    public void flightselectionconfirmation()
    {
        FlightConfirmationPage flightconfirmationpage=new FlightConfirmationPage(driver);
        Assert.assertTrue(flightconfirmationpage.isAt());
        Assert.assertEquals(flightconfirmationpage.getPrice(),testdata.expectedprice());
    }
}
