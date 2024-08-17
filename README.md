# Guided: Playwright Foundations with Java

This is the example repo for my blog post on [Guided: Playwright Foundations with Java](java/playwright)

To run this example:

1. Make sure you have Java, JDK, and Maven installed
2. Clone the repo
3. Run `mvn clean compile assembly:single` - this should give you a JAR file in the `target` folder
4. Run `java -jar target/pluralsight-playwright-java-1.0-SNAPSHOT-jar-with-dependencies.jar` to run the JAR file


## Introduction and set up

In this section, we will set up the project and install the necessary dependencies that we need to run Playwright with Java.

Playwright is a Java library that allows you to write end-to-end tests and automation for web applications. 

The file you will be working on throughout this guide is `src/main/java/com/pluralsight/RunPlaywright.java`.

We will be testing a locally hosted web application that you can view in the Web page tab (second ab). The webpage is being served on `localhost:8000` by a simple python http server that is running in the third tab (You don't need to modify or make changes to this tab).

To run the application, execute:

```
mvn exec:java -Dexec.mainClass="com.sohamkamani.App" -Dexec.classpathScope=runtime
```

>The first time you run this command, it will take a while to download the dependencies. Subsequent runs will be faster.


### Task - Create a new Playwright instance

We will need to create a new Playwright instance to interact with the virtual browser.

Create a new Playwright webkit browser, and print its name and version to the console.

Hint: You can see the official Playwright documentation for Java [here](https://playwright.dev/java/docs/intro#first-script). Once you create a new `Browser` instance, you can call the `name()` and `version()` methods to get the browser name and version.