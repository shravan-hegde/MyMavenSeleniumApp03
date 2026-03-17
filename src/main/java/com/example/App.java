package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        // Set Chromium Driver Path
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium-browser");

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        try {

            // Open Website
            driver.get("https://automationexercise.com/");

            // Wait until page loads
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

            // Go to Products Page
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/products']"))).click();

            // Search Product
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("search_product"))).sendKeys("Tshirt");

            driver.findElement(By.id("submit_search")).click();

            // Add First Product
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[contains(text(),'Add to cart')])[1]"))).click();

            // Click View Cart in Popup
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//u[contains(text(),'View Cart')]"))).click();

            Thread.sleep(5000);

            System.out.println("Product added and cart opened successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
