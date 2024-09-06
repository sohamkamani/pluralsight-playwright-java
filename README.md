# Guided: Playwright Foundations with Java

This is the example repo for my blog post on [Guided: Playwright Foundations with Java](java/playwright)

To run this example:

1. Make sure you have Java, JDK, and Maven installed
2. Clone the repo
3. Run `mvn clean compile assembly:single` - this should give you a JAR file in the `target` folder
4. Run `java -jar target/pluralsight-playwright-java-1.0-SNAPSHOT-jar-with-dependencies.jar` to run the JAR file


Links:
1. https://playwright.dev/java/docs/browsers#install-system-dependencies 
2. 

## Introduction and set up

In this section, we will set up the project and install the necessary dependencies that we need to run Playwright with Java.

Playwright is a Java library that allows you to write end-to-end tests and automation for web applications. 

The Java file you will be working on throughout this guide is `src/main/java/com/pluralsight/App.java`.

To run the application code at any time, execute:

```
mvn clean compile exec:java -Dexec.mainClass="com.pluralsight.App" -Dexec.classpathScope=runtime
```

We will be testing a locally hosted web application that you can view in the Web page tab (second ab). The website is being served on `localhost:8080`. All the HTML files are in the `website` folder, which you can go through to understand the structure of the website.

You can open the web browser tab, and navigate to `localhost:8080/website/catalog.html` to see one of the web pages.



### Task - Create a new Playwright instance

We will need to create a new Playwright instance to interact with the virtual browser.

Create a new Playwright webkit browser, and print its name and version to the console.

Hint: You can see the official Playwright documentation for Java [here](https://playwright.dev/java/docs/intro#first-script). Once you create a new `Browser` instance, you can call the `Browser.browserType().name()` and `Browser.version()` methods to get the browser name and version.

## Locating elements

When writing end-to-end tests, you will need to find and locate particular elements on the web page. 
This could be to verify their content, interact with them, or check their attributes. 

Playwright allows you to locate elements using an already-familiar CSS selector syntax. For example:

```java
page.navigate("http://localhost:8080/website/catalog.html");
List<Locator> elements = page.locator(".some-class").all()
```

This code snippet will navigate to the `catalog.html` page and locate all elements with the class `some-class`, which will be stored in the `elements` list.


>You can see the [documentation on locators](https://playwright.dev/java/docs/locators#locate-by-css-or-xpath) for more details on the different ways you can locate elements.

### Task 2 - Locate title on the page

Navigate to the `localhost:8080/website/catalog.html` page, and print out the page title.

### Task 3 - Locate elements on the page

Navigate to the `localhost:8080/website/catalog.html` page, and print out the inner text of all the elements with the class `event-name`.

## Interacting with elements

Once you have located an element, you can interact with it in various ways. In most cases, when you interact with an element, the state of the page will change. For example, clicking a button might open a modal, or typing into an input field might trigger a search.

Playwright allows you to interact with elements in the same way a user would, so you can simulate user interactions in your tests:

```java
// click on the first link on the page
page.locator("a").first().click();
// fill in the input field with the label "Birth date"
page.getByLabel("Birth date").fill("2020-02-02");
```

You can see the [documentation on actions](https://playwright.dev/docs/input) to know more about the different kinds of interactions you can perform.

### Task 4 - Click on an element

We want to simulate a user purchasing a ticket for an event. 

Navigate to the `localhost:8080/website/catalog.html` page, and click on the link (`a` element) within the first element of the `.event-purchase-button` class, and print the URL of the page that we navigate to.

For reference, you can see the `website > catalog.html` file to understand the structure of the page, and where the `a` element is located, then use the `page.locator` method to locate the element in your Java code, and click on it.

### Task 5 - Fill in an input field

On the details page, we want to select the quantity of tickets we want to purchase, and click on "Place Order"

Navigate to the `localhost:8080/website/details.html` page, and fill in the input field with the `filter-dropdown` name attribute with the value "2". Then click on the link (`a` element) within the `.event-purchase-button` class. Finally, print the URL of the page that we navigate to.

For reference, you can see the `website > details.html` file to understand the structure of the page.

## Making Assertions

When writing tests, you will need to verify that the application behaves as expected. This could be checking the content of a page, the state of an element, or the URL of a page after an interaction.

Playwright allows you to make assertions on the state of the page, elements, and the browser. For example:

```java
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

// ...

// check if the page title is "Home"
assertThat(page.title()).isEqualTo("Home");
// check if the element with the class "some-class" is visible
assertThat(page.locator(".some-class").first().isVisible()).isTrue();
```

An assertion will throw an exception if the condition is not met. For this reason, assertions are mainly used in tests to verify that the application behaves as expected.

To see this in action, let's add some assertions to our code. We'll make use of assertions to check the visibility of an element in the browser, and the appearance of an element through its CSS properties.

### Task 6 - Assert the visibility of an element

In this task, we will need to complete the code for the `public static boolean checkElementVisibility(Browser browser, String url)` method.

Navigate to the provided `url`, and assert that the event name is visible on the page.

The event name is located within the `.event-name` class. You can use the [`isVisible()` assertion](https://playwright.dev/java/docs/api/class-locatorassertions#locator-assertions-to-be-visible) to check if the element is visible. 


### Task 7 - Assert the background color of an element

We want to complete the code for the `public static void checkElementBackgroundColor(Browser browser, String expectedColor)` method.

Navigate to the `localhost:8080/website/basket.html` page, and assert that the background color of the header element is the same as the provided `expectedColor` argument.

We want to check the `background-color` CSS property of the `header` element. You can use the [hasCSS locator assertion](https://playwright.dev/java/docs/api/class-locatorassertions#locator-assertions-to-have-css) to check the CSS property of an element. 

## Taking screenshots

When writing tests, you might want to take screenshots of the page at different points in the test. 

This can be useful for debugging, or to visually verify that the page looks as expected. This is especially useful when running end-to-end tests in a CI/CD pipeline, to ensure that a webpage looks as expected.

Playwright allows you to take screenshots of the page, elements, or the browser window. For example:

```java
// take a screenshot of the page
page.screenshot(new Page.ScreenshotOptions().setPath("screenshot.png"));
```

You can see the [documentation on screenshots](https://playwright.dev/java/docs/screenshots) for more details on the different ways you can take screenshots.

### Task 8 - Take a screenshot of the page

Navigate to the `localhost:8080/website/basket.html` page, and take a screenshot of the page. Save the screenshot as `basket.png` within the workspace directory.

## Commands

/usr/lib/x86_64-linux-gnu
ll /home/ps-user/.cache/ms-playwright/	

sudo tar -czf librariesv2.tar.gz /usr/lib/x86_64-linux-gnu

cd /home/ps-user/.m2
sudo tar -czf /home/ps-user/.cache/mvn/libraries.tar.gz ./

```
cd /home/ps-user/.cache/ms-playwright/      
tar -xf playwright-setup.tar.gz -C ./     
sudo tar -xf /home/ps-user/.cache/libraries/librariesv2.tar.gz -C /
```

```
 mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"

 mvn exec:java -Dexec.mainClass="com.pluralsight.App" -Dexec.classpathScope=runtime








  dictionaries-common fonts-freefont-ttf fonts-ipafont-gothic fonts-noto-color-emoji fonts-tlwg-loma-otf fonts-unifont fonts-wqy-zenhei glib-networking
  glib-networking-common glib-networking-services gstreamer1.0-libav gstreamer1.0-plugins-bad gstreamer1.0-plugins-base gstreamer1.0-plugins-good hunspell-en-us
  libaa1 libaspell15 libcdparanoia0 libdbus-glib-1-2 libdca0 libdv4 libdvdnav4 libdvdread8 libenchant-2-2 libevent-2.1-7 libfaad2 libffi7 libfluidsynth3
  libfreeaptx0 libgles2 libgssdp-1.2-0 libgstreamer-gl1.0-0 libgstreamer-plugins-bad1.0-0 libgstreamer-plugins-base1.0-0 libgstreamer-plugins-good1.0-0
  libgupnp-1.2-1 libgupnp-igd-1.0-4 libhunspell-1.7-0 libhyphen0 libinstpatch-1.0-2 libjson-glib-1.0-0 libjson-glib-1.0-common libkate1 libldacbt-enc2 libltc11
  libmanette-0.2-0 libmjpegutils-2.1-0 libmodplug1 libmpcdec6 libmpeg2encpp-2.1-0 libmplex2-2.1-0 libnice10 libnotify4 libopenh264-6 libopenni2-0 liborc-0.4-0
  libproxy1v5 libqrencode4 libsbc1 libsecret-1-0 libsecret-common libshout3 libsoundtouch1 libsoup-3.0-0 libsoup-3.0-common libsoup2.4-1 libsoup2.4-common
  libspandsp2 libsrtp2-1 libtag1v5 libtag1v5-vanilla libv4l-0 libv4lconvert0 libvisual-0.4-0 libvo-aacenc0 libvo-amrwbenc0 libwavpack1
  libwebrtc-audio-processing1 libwildmidi2 libwoff1 libzbar0 libzxingcore1 timgm6mb-soundfont xfonts-cyrillic
The following packages will be upgraded:
  gir1.2-gdkpixbuf-2.0 libcups2 libgdk-pixbuf-2.0-0 libgdk-pixbuf-2.0-dev libgdk-pixbuf2.0-bin libgdk-pixbuf2.0-common libglib2.0-0 libglib2.0-bin libglib2.0-dev
  libglib2.0-dev-bin libgtk-3-0 libgtk-3-common libnspr4 libnss3 xserver-common xvfb
  ```

  ```
  cd /home/ps-user/.cache/ms-playwright/
sudo cp setup/playwright-setup.tar.gz ./      
sudo tar -xf playwright-setup.tar.gz -C ./     
sudo tar -xf /home/ps-user/.cache/libraries/librariesv2.tar.gz -C /
cd ~/workspace
```


# Solution

```java
package com.pluralsight;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.Paths;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // task 1
            Browser browser = playwright.webkit().launch();
            System.out.println(browser.browserType().name() + browser.version());

            // checkElementBackgroundColor(browser, "rgb(0, 0, 0)");
            // takeScreenshot(browser);
            fillInputFieldAndClickLink(browser);
            takeScreenshot(browser);
            // getEventNames(browser);
            //
            // Page page = browser.newPage();
            // page.navigate("https://playwright.dev/");
            // page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
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

        assertThat(page.locator("header")).hasCSS("background-color", expectedColor);
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

```