package com.vendorportal.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.pages.vendorportal.Dashboard;
import com.pages.vendorportal.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.ConfigUtilReader;
import util.Constants;
import util.TestListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public class AbstractTest {
    protected WebDriver driver;


    @BeforeTest
    public void beforeTest(ITestContext ctx) throws MalformedURLException {

        if(Boolean.getBoolean("selenium.grid.enabled"))
        {
            this.driver=getRemoteDriver();

        }else {
            this.driver= getLocalDriver();
        }
        ctx.setAttribute(Constants.DRIVER,this.driver);
    }


    private WebDriver  getRemoteDriver() throws MalformedURLException {
        Capabilities cap;
        if(System.getProperty("browser").equalsIgnoreCase("chrome"))
        {
            cap=new ChromeOptions();
        }
        else {
            cap=new FirefoxOptions();
        }
       // String Urlformat=ConfigUtilReader.get(Constants.Grid_URLFORMAT);
       // String hubhost=ConfigUtilReader.get(Constants.GRID_HUB_HOST);
    //   String url= String.format(hubhost,Urlformat);
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
    }

    private WebDriver getLocalDriver()
    {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();


    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    @AfterMethod
    public void sleep()
    {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }

}
