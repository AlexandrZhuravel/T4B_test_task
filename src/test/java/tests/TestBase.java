package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;
import static org.openqa.selenium.logging.LogType.*;


class TestBase {

    @BeforeAll
    @Step("Tests setup")
    public static void beforeAll() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.headless = true;
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach(){
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("getBrowserConsoleLogs console logs", getBrowserConsoleLogs());
        closeWebDriver();
    }

    public static String getBrowserConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    public static String getClientConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(CLIENT));
    }

    public static String getDriverConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(DRIVER));
    }

    public static String getPerformanceConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(PERFORMANCE));
    }

    public static String getProfilerConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(PROFILER));
    }
}