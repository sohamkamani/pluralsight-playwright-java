package com.pluralsight;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.Paths;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // task 1
            Browser browser = playwright.webkit().launch();

            System.out.println(browser.browserType().name() + browser.version());

            getTitle(browser);
            getEventNames(browser);
            clickFirstButton(browser);
            fillInputFieldAndClickLink(browser);
            takeScreenshot(browser);
        }
    }

    // task 2 - Navigate to the `localhost:8080/website/catalog.html` page, and print out the page
    // title.
    private static void getTitle(Browser browser) {
        Page page = browser.newPage();
        page.navigate("http://localhost:8080/website/catalog.html");
        System.out.println(page.title());
    }

    // task 3 - get all text within all elements in the .event-name class
    private static void getEventNames(Browser browser) {
        Page page = browser.newPage();
        page.navigate("http://localhost:8080/website/catalog.html");

        page.locator(".event-name").all()
                .forEach(element -> System.out.println(element.innerText()));
    }

    // task 4 - click on the first button within the .event-purchase-button class
    private static void clickFirstButton(Browser browser) {
        Page page = browser.newPage();
        page.navigate("http://localhost:8080/website/catalog.html");

        page.locator(".event-purchase-button > a").first().click();

        // now print the url of the page
        System.out.println(page.url());
    }


    // task 5 - Navigate to the `localhost:8080/website/details.html` page, and fill in the input
    // field with the `filter-dropdown` name attribute with the value "2". Then click on the link
    // (`a` element) within the `.event-purchase-button` class. Finally, print the URL of the page
    // that we navigate to.
    private static void fillInputFieldAndClickLink(Browser browser) {
        Page page = browser.newPage();
        page.navigate("http://localhost:8080/website/details.html");

        page.locator("select[name='filter-dropdown']").selectOption("2");
        page.locator(".event-purchase-button > a").first().click();

        // now print the url of the page
        System.out.println(page.url());
    }

    // Task 6 - Assert the visibility of an element
    // Navigate to the `localhost:8080/website/basket.html` page, and assert that the event name is
    // visible.
    // The event name is located within the `.event-name` class. You can use the `isVisible()`
    // method to check if the element is visible.
    public static void checkElementVisibility(Browser browser, String url) {
        Page page = browser.newPage();
        page.navigate(url);

        assertThat(page.locator(".event-name").first()).isVisible();
    }

    // Task 7 - Assert the background color of an element
    // Navigate to the `localhost:8080/website/basket.html` page, and assert that the background
    // color of the header element is the same as the provided `expectedColor` argument.
    public static void checkElementBackgroundColor(Browser browser, String expectedColor) {
        Page page = browser.newPage();
        page.navigate("http://localhost:8080/website/basket.html");

        assertThat(page.locator("header").first()).hasCSS("background-color", expectedColor);
    }

    // Task 8 - Take a screenshot of the page
    // Navigate to the `localhost:8080/website/basket.html` page, and take a screenshot of the page.
    // Save the screenshot as `basket.png` within the workspace directory.
    private static void takeScreenshot(Browser browser) {
        Page page = browser.newPage();
        page.navigate("http://localhost:8080/website/basket.html");

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("basket.png")));
    }
}
