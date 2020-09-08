package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.by;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.link;
import static io.qameta.allure.Allure.step;

@Owner("zhuravel")
public class T4BTests extends TestBase {
    private String baseUrl = "https://t4b.com";
    private String chinaUrl = "http://t4b.cn";
    private String rusUrl = "https://t4b.com/ru/";

    @BeforeAll
    public static void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));


    }

    @AfterEach
    public void closeDriver() {
        closeWebDriver();
    }

    public static String getCurrentPageUrl() {
        String currentUrl = WebDriverRunner.url();
        return currentUrl;
    }

    @Test
    @Story("Сhange the interface language to Chinese")
    @DisplayName("1. Should change the interface language to Chinese")
    void shouldChangeInterfaceLanguageToChinese() {
        link("T4B Service", baseUrl);

        step("Open service main page", () -> {
        open(baseUrl);
        });

        step("Click the 'CN' link", () -> {
           $x("/html/body/div[2]/header/div/div[1]/ul/li[3]/a").click();
            closeWindow();
        });

        step("Сheck the transition to Chinese interface", () -> {
            $(".main-begin-c").shouldHave(text("10 周年纪念"));
            assertEquals(chinaUrl, getCurrentPageUrl());

        });
    }

    @Test
    @Story("Сhange the interface language to Russian")
    @DisplayName("2. Should change the interface language to Russian")
    void shouldChangeInterfaceLanguageToRussian() {
        link("T4B Service", baseUrl);

        step("Open service main page", () -> {
            open(baseUrl);
        });

        step("Click the 'RU' link", () -> {

            $x("/html/body/div[2]/header/div/div[1]/ul/li[2]/a").click();

        });

        step("Сheck the transition to Russian interface", () -> {
//            assertEquals(rusUrl, getCurrentPageUrl());
            $(".mp-lbl").shouldHave(text("Технологические решения для брокеров"));
        });
    }

    @Test
    @Story("Go to articles of the 'KNOWLEDGE BASE' section")
    @DisplayName("4. Should go to the article 'BBI Ideology'")
    void shouldGoToArticleBBIIdeology() {
        link("T4B Service", baseUrl);

        step("Open service main page", () -> {
            open(baseUrl);
        });

        step("Click the 'About Us' link", () -> {
            $(".header-menu").$("li", 4).click();
        });
        step("Click the 'KNOWLEDGE BASE' link", () -> {
            $(".solutions-btn", 1).click();
        });
            step("Click the 'View more' button in the 'BBI Ideology' section", () -> {
            $(".news-cnt", 1).$(".news-more").click();
        });

        step("Сheck the header in the 'BBI Ideology' article", () -> {
            $(".company-news-lbl").shouldHave(text("BBI Ideology"));
        });
    }


}
