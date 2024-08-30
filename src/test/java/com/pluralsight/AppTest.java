package com.pluralsight;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;


public class AppTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }


  @Test
  void task1ShouldPrintBrowserNameAndVersion() {
    App.main(new String[0]);
    assertTrue(outContent.toString().contains("webkit"), "Doesn't print browser type webkit");
    assertTrue(outContent.toString().contains("17.4"), "Doesn't print browser version 17.4");
  }

  @Test
  void task2ShouldPrintPageTitle() {
    App.main(new String[0]);
    assertTrue(outContent.toString().contains("Globoticket"),
        "Doesn't print page title 'Globoticket'" + outContent.toString());
  }

  @Test
  void task3ShouldPrintEventNames() {
    App.main(new String[0]);
    assertTrue(outContent.toString().contains("Alexander Lemtov Live"),
        "Doesn't print event name 'Alexander Lemtov Live'" + outContent.toString());
    assertTrue(outContent.toString().contains("To The Moon And Back"),
        "Doesn't print event name 'To The Moon And Back'" + outContent.toString());
    assertTrue(outContent.toString().contains("The State Of Affairs: Mariam Live!"),
        "Doesn't print event name 'The State Of Affairs: Mariam Live!'" + outContent.toString());
  }

  @Test
  void task4ShouldPrintPageUrl() {
    App.main(new String[0]);
    assertTrue(
        outContent.toString().contains("http://localhost:8080/website/details.html"),
        "Doesn't print page url 'http://localhost:8080/website/details.html'"
            + outContent.toString());
  }

  @Test
  void task5ShouldPrintPageUrl() {
    App.main(new String[0]);
    assertTrue(
        outContent.toString().contains("http://localhost:8080/website/basket.html"),
        "Doesn't print page url 'http://localhost:8080/website/basket.html'"
            + outContent.toString());
  }

  @Test
  void task6ShouldAssertVisibility() {
    try (Playwright playwright = Playwright.create()) {
      // task 1
      Browser browser = playwright.webkit().launch();
      // Assert that an exception is thrown
      assertThrows(AssertionFailedError.class,
          () -> App.checkElementVisibility(browser, "http://localhost:8080/"));
      assertDoesNotThrow(
          () -> App.checkElementVisibility(browser, "http://localhost:8080/website/basket.html"));
    }
  }

  @Test
  void task7ShouldAssertBackgroundColor() {
    try (Playwright playwright = Playwright.create()) {
      // task 1
      Browser browser = playwright.webkit().launch();
      // Assert that an exception is thrown
      assertThrows(AssertionFailedError.class,
          () -> App.checkElementBackgroundColor(browser, "blue"));
      assertDoesNotThrow(
          () -> App.checkElementBackgroundColor(browser, "rgb(0, 0, 0)"));

    }
  }

  @Test
  void task8ShouldTakeScreenshot() throws Exception {
    // Run App.main and then compare that ./basket.png exists, and that its contents are the same as
    // test/basket.png
    App.main(new String[0]);

    File screenshotFile = new File("./basket.png");
    assertTrue(screenshotFile.exists(), "Screenshot file does not exist");

    File expectedScreenshotFile = new File("src/test/basket.png");
    assertTrue(areFilesEqual(screenshotFile, expectedScreenshotFile),
        "Screenshot file does not match expected screenshot file");
  }

  public static boolean areFilesEqual(File file1, File file2) throws IOException {
    if (file1.length() != file2.length()) {
      return false;
    }

    try (FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2)) {

      byte[] buffer1 = new byte[1024];
      byte[] buffer2 = new byte[1024];

      int bytesRead1;
      int bytesRead2;

      while ((bytesRead1 = fis1.read(buffer1)) != -1) {
        bytesRead2 = fis2.read(buffer2);

        if (bytesRead1 != bytesRead2 || !Arrays.equals(buffer1, buffer2)) {
          return false;
        }
      }
    }

    return true;
  }
}
