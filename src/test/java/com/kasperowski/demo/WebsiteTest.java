package com.kasperowski.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class WebsiteTest {
    WebDriver driver;

    @Before
    public void setupSelenium() {
        // Make sure the version number of your chromedriver matches the version number of your Chrome browser.
        // Visit https://sites.google.com/a/chromium.org/chromedriver/downloads for the latest chromedriver downloads.
        //
        // For the tests to be able to launch chromedriver, it must be in your PATH, or it must be specified via this
        // property.
        System.setProperty("webdriver.chrome.driver", "src/test/bin/macos/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void cleanupSelenium() {
        driver.close();
        driver.quit();
    }

    @Test
    public void junitWorksProperly() {
        // TODO Given the @Before and @After methods, this test launches the Selenium web driver. That's more work than
        // we want, and it duplicates the behavior of the test seleniumWorksProperly.
        assertTrue(true);
    }

    @Test
    public void seleniumWorksProperly() {
        // Calls the @Before and @After methods
    }

    @Test
    public void webServerReturnsAPage() {
        driver.navigate().to("https://kasperowski.com/");
        assertThat(driver.getTitle(), containsString("Richard Kasperowski"));
    }

    @Test
    public void visitorCanContactMe() {
        driver.navigate().to("https://kasperowski.com/");

        // Visitor navigates to the Contact Me page
        WebElement contactMe = driver.findElement(By.linkText("Contact Me"));
        contactMe.click();
        assertThat(driver.getTitle(), containsString("Contact Me"));

        // Visitor types their info
        // TODO In the web service, improve the names of these input fields.
        WebElement name = driver.findElement(By.name("input_1"));
        name.sendKeys("David St. Hubbins");

        WebElement email = driver.findElement(By.name("input_2"));
        email.sendKeys("david.sthubbins@example.com");

        WebElement message = driver.findElement(By.name("input_3"));
        message.sendKeys("Hi, there!");

        // Visitor clicks Submit
        // TODO In the web service, improve the name of the submit button.
        WebElement submit = driver.findElement(By.id("gform_submit_button_1"));
        submit.click();

        // assert that we got the submit result
        assertThat(driver.getPageSource(), containsString("Thank you for contacting me!"));

        // ... and a full end-to-end test would check that the email got sent.
    }

    @Test
    public void findElementByCssSelector() {
        // Wait up to 5 seconds for the page to load before attempting to find elements.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.navigate().to("https://kasperowski.com/contact-me");

        // To find the CSS selector string for an existing web page, in Chrome, click View/Developer/Inspect Elements.
        // Click on the element you care about. In the inspector window, right-click the highlighted text and
        // select Copy/Copy Selector. Paste that string into your cssSelector method.
        WebElement name = driver.findElement(By.cssSelector("#input_1_1"));
        name.sendKeys("My Name");

        WebElement email = driver.findElement(By.cssSelector("#input_1_2"));
        email.sendKeys("myname@example.com");

        WebElement message = driver.findElement(By.cssSelector("#input_1_3"));
        message.sendKeys("myname@example.com");

        WebElement submit = driver.findElement(By.cssSelector("#gform_submit_button_1"));
        submit.click();

        // assert that we got the submit result
        assertThat(driver.getPageSource(), containsString("Thank you for contacting me!"));

        // ... and a full end-to-end test would check that the email got sent.
    }
}
