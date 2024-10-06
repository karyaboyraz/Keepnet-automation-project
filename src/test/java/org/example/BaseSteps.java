package org.example;

import Methods.TwoFactorAuthHelper;
import com.thoughtworks.gauge.Step;
import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class BaseSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));


    @Step("Check element existence <ElementName>")
    public WebElement createWebElement(String ElementName) {
        String xpathTemplate;
        if (ElementName.toLowerCase().contains("xpath.")) {
            xpathTemplate = ElementName.replaceAll("xpath.", "");
        } else {
            xpathTemplate = MessageFormat.format("//*[text() = ''{0}'']/../input | //*[contains(@placeholder , ''{1}'')]", ElementName, ElementName);
        }
        logger.info(xpathTemplate);
        WebElement mainElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathTemplate)));
        Assert.assertNotNull(mainElement, "Element is not found: %s".formatted(ElementName));
        logger.info(mainElement.getText());
        logger.info("Element is found: %s".formatted(ElementName));

        return mainElement;
    }

    @Step("Check element existence <ElementName> must be <status>")
    public void checkElementVisibility(String ElementName, Visibility status) {
        boolean shouldBeVisible = status == Visibility.visible;
        boolean isVisible;

        try {
            WebDriverWait waitVisibility = new WebDriverWait(driver, Duration.ofSeconds(5));
            isVisible = waitVisibility.until(driver -> {
                try {
                    WebElement element = createWebElement(ElementName);
                    return element.isDisplayed();
                } catch (NoSuchElementException e) {
                    return false;
                }
            });
        } catch (TimeoutException e) {
            isVisible = false;
        }
        Assert.assertEquals(isVisible, shouldBeVisible, "Element visibility mismatch: %s".formatted(ElementName));
    }

    public enum Visibility {
        visible,
        hidden
    }

    @Step("Enter 2FA Code")
    public void MFACode() {
        TwoFactorAuthHelper twoFactorAuthHelper = new TwoFactorAuthHelper();
        WebElement generatedElement = createWebElement("Verification Code");
        int code = twoFactorAuthHelper.GetCode();
        clearAndSendKeys(generatedElement, String.valueOf(code));
    }

    @Step("Click button <ElementName>")
    public void clickButton(String ElementName) {
        String path = "xpath." + "//button/.//*[contains(normalize-space(.), '"+ElementName+"')]";
        createWebElement(path).click();
        logger.info("Clicked on the button: %s".formatted(ElementName));
    }

    @Step("Click icon button <ElementName>")
    public void clickIconButton(String ElementName) {
        String path = "xpath." + "//i[contains(@class, '"+ElementName+"')]/../..";
        createWebElement(path).click();
        logger.info("Clicked on the icon button: %s".formatted(ElementName));
    }

    @Step("Click Element <ElementName>")
    public void clickElement(String ElementName) {
        String path = "xpath." + "//*[text() = '"+ ElementName +"']";
        createWebElement(path).click();
        logger.info("Clicked on the element: %s".formatted(ElementName));
    }

    @Step("Click Menu Button <ElementName>")
    public void clickMenuButton(String ElementName) {
        String path = "xpath." + "//a[contains(@class, 'v-list-item__title')] | //*[text() = '"+ ElementName +"']";
        createWebElement(path).click();
        logger.info("Clicked on the menu button: %s".formatted(ElementName));
    }

    @Step("Click Tab Button <ElementName>")
    public void clickTabButton(String ElementName) {
        String path = "xpath." + "//div[@role = 'tablist']//*[contains(normalize-space(text()), '"+ElementName+"')]";
        createWebElement(path).click();
        logger.info("Clicked on the menu button: %s".formatted(ElementName));
    }

    @Step("Click Checkbox on Table <ElementName>")
    public void clickCheckboxOnTable(String ElementName) {
        String path = "xpath." + "//tr[.//span[text()='"+ElementName+"']]//input[@type='checkbox']/..//span";
        WebElement checkbox = createWebElement(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", checkbox);

        logger.info("Clicked on Checkbox: %s".formatted(ElementName));
    }

    @Step("Open Base URL <url>")
    public void goToUrl(String url) {
        driver.get(url);
    }

    @Step("Write <text> to Element <element>")
    public void writeTextToInputArea(String text , String element ) {
        WebElement generatedElement = createWebElement(element);
        clearAndSendKeys(generatedElement, text);
        logger.info("This element written in a relative area: %s".formatted(text));
    }

    private void clearAndSendKeys(WebElement element, String text) {
        if (!element.getText().isEmpty()) {
            element.clear();
        }
        element.click();
        element.sendKeys(text);
    }
}