package com.pluralsight;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            System.out.println(browser.browserType().name());
            // Page page = browser.newPage();
            // page.navigate("https://playwright.dev/");
            // page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        }
    }
}
