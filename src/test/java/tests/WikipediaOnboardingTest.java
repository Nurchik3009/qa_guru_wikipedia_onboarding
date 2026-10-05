package tests;

import helpers.Attach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class WikipediaOnboardingTest extends TestBase {

    @Test
    void successfulSearchTest() {

        step("Пройти первый экран онбординга", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"All the world's knowledge\")"
            )).shouldBe(visible);

            Attach.screenshotAs("Screenshot№1");

            $(className("android.widget.Button")).click();
        });

        step("Пройти второй экран онбординга", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"Data & Privacy\")"
            )).shouldBe(visible);

            Attach.screenshotAs("Screenshot№2");

            $(className("android.widget.Button")).click();
        });

        step("Пройти третий экран онбординга", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"English\")"
            )).shouldBe(visible);

            Attach.screenshotAs("Screenshot№3");

            $(className("android.widget.Button")).click();
        });

        step("Пройти четвертый экран онбординга", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"Follow your curiosity\")"
            )).shouldBe(visible);

            Attach.screenshotAs("Screenshot№4");

            $(className("android.widget.Button")).click();
        });

        step("Нажать на вкладку «Поиск»", () -> {
            $(accessibilityId("Search"))
                    .shouldBe(visible)
                    .click();
        });

        step("Закрыть модальное окно", () -> {
            $(accessibilityId("Close"))
                    .shouldBe(visible)
                    .click();
        });

        step("Нажать на поле «Search Wikipedia»", () -> {
            $(accessibilityId("Search Wikipedia"))
                    .shouldBe(visible)
                    .click();
        });

        step("Ввести в поиск запрос «Peggy Gou»", () -> {
            $(id("org.wikipedia:id/search_src_text"))
                    .shouldBe(visible)
                    .sendKeys("Peggy Gou");

            $(androidUIAutomator(
                    "new UiSelector().text(\"Peggy Gou\")"
            )).shouldBe(visible);
        });

        step("Кликнуть по первому сниппету Peggy Gou", () -> {
            $(androidUIAutomator(
                    "new UiSelector().className(\"android.view.View\").instance(2)"
            )).shouldBe(visible).click();
        });

        step("Закрыть модальное окно статьи", () -> {
            $(accessibilityId("Close"))
                    .shouldBe(visible)
                    .click();
        });

        step("Проверить, что статья Peggy Gou открылась", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"Peggy Gou\")"
            )).shouldBe(visible);
        });

        step("Закрыть подсказку кликом по заголовку", () -> {
            $(androidUIAutomator(
                    "new UiSelector().text(\"Peggy Gou\")"
            )).click();
        });

        step("Проверить, что фотография статьи отображается", () -> {
            $(id("org.wikipedia:id/view_page_header_image"))
                    .shouldBe(visible);
        });

        step("Открыть фотографию Peggy Gou", () -> {
            $(androidUIAutomator(
                    "new UiSelector().resourceId(\"org.wikipedia:id/view_page_header_image\")"
            )).shouldBe(visible)
                    .click();
        });

        step("Проверить, что фотография открылась на весь экран", () -> {
            $(androidUIAutomator(
                    "new UiSelector().resourceId(\"org.wikipedia:id/imageView\")"
            )).shouldBe(visible, java.time.Duration.ofSeconds(5));

            sleep(1000);

            Attach.screenshotAs("Screenshot№6");
        });
    }
}