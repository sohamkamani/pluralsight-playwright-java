package com.pluralsight;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // your code here
        }
    }

    public static void checkElementVisibility(Browser browser, String url) {
        // your code here
    }

    public static void checkElementBackgroundColor(Browser browser, String expectedColor) {
        // your code here
    }
}
