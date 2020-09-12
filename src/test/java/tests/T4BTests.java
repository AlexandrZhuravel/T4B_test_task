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

import static com.codeborne.selenide.Selectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.link;
import static io.qameta.allure.Allure.step;

@Owner("zhuravel")
public class T4BTests extends TestBase {
    private String baseUrl = "https://t4b.com";
    private String chinaUrl = "http://t4b.cn/";
    private String rusUrl = "https://t4b.com/ru/";

    private String infoSkype = "tools4brokers.support",
            infoEmail = "support@t4b.com",
            infoTelegram = "@t4b_support",
            infoPhoneUK = "+44-20-8133-8385 UK",
            infoPhoneHK = "+852-8125-7512 HK";

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
            $(".header-langs").$("li", 2).click();
            switchTo().window(1);
        });

        step("Сheck the transition to Chinese interface", () -> {
            assertEquals(chinaUrl, getCurrentPageUrl());
            $(".ann-slbl").closest(".content-wrapper").shouldHave(text("10 周年纪念"));
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
            $(".header-langs").$("li", 1).click();
        });

        step("Сheck the transition to Russian interface", () -> {
            assertEquals(rusUrl, getCurrentPageUrl());
            $(".mp-lbl").shouldHave(text("Технологические решения для брокеров"));
        });
    }

    @Test
    @Story("Chat with a manager on the Russian page")
    @DisplayName("3. Should open a chat window with a manager")
    void shouldOpenChatWindowWithManager() {
        link("T4B Service", baseUrl);

        step("Open service main page", () -> {
            open(baseUrl);
        });

        step("Click the 'RU' link", () -> {
            $(".header-langs").$("li", 1).click();
        });
        step("Click the 'Контакты' link", () -> {
            $(".header-menu").$(byText("Контакты")).click();
        });
        step("Click the 'Чат с менеджером' link", () -> {
            $(".apply-row").$(".apply-row-btn").scrollIntoView(true).click();
        });

        step("Check the 'Чат с менеджером' window", () -> {
            $("#chat-widget").shouldBe(enabled);
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
            $(".header-menu").$(byText("About Us")).click();
        });
        step("Click the 'KNOWLEDGE BASE' link", () -> {
            $(".solutions-btn", 1).click();
        });
        step("Click the 'View more' button in the 'BBI Ideology' section", () -> {
            $(".news-cnt", 2).$(".news-more").click();
        });

        step("Сheck the header in the 'BBI Ideology' article", () -> {
            $(".company-news-lbl").shouldHave(text("BBI Ideology"));
        });
    }

    @Test
    @Story("Checking contact information")
    @DisplayName("5. Сontact information should be true")
    void contactInformationShouldBeTrue() {
        link("T4B Service", baseUrl);

        step("Open service main page", () -> {
            open(baseUrl);
        });

        step("Click the 'CONTACT US' link", () -> {
            $(".header-menu").$(byText("Contact Us")).click();
        });

        step("Сheck contact information in the 'SUPPORT' section", () -> {
            $(".cnt-row-item-r", 0).shouldHave(text(infoSkype));
            $(".cnt-row-item-r", 1).shouldHave(text(infoEmail));
            $(".cnt-row-item-r", 2).shouldHave(text(infoTelegram));
            $(".cnt-row-item-r", 3).shouldHave(text(infoPhoneUK + " " + infoPhoneHK));
        });
    }
}
