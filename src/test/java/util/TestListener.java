package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        TakesScreenshot driver=(TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
        String screenshot=driver.getScreenshotAs(OutputType.BASE64);
        String htmlimageformat="<img width=700px src='data:image/png;base64,%s' />";
        String finalimage=String.format(htmlimageformat,screenshot);
        Reporter.log(finalimage);


    }
}
