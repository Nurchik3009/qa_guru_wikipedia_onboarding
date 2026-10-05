package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackAndroidDriver;
import drivers.EmulationDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        String deviceHost = System.getProperty("deviceHost", "browserstack");

        if (deviceHost.equalsIgnoreCase("emulation")) {
            Configuration.browser = EmulationDriver.class.getName();
        } else {
            Configuration.browser = BrowserstackAndroidDriver.class.getName();
        }

        Configuration.browserSize = null;
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = -1;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);

        Attach.pageSource();
        closeWebDriver();

        String deviceHost = System.getProperty("deviceHost", "browserstack");
        if (deviceHost.equalsIgnoreCase("browserstack")) {
            Attach.addVideo(sessionId);
        }
    }
}