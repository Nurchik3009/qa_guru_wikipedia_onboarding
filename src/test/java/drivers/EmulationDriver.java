package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class EmulationDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setPlatformVersion("17");
        options.setDeviceName("emulator-5554");

        options.setAppPackage("org.wikipedia");
        options.setAppActivity("org.wikipedia.DefaultIcon");

        try {
            return new AndroidDriver(
                    new URL("http://localhost:4723/wd/hub"),
                    options
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
