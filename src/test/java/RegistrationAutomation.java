import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationAutomation {
    WebDriver driver;
    @BeforeAll
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void submitForm() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        driver.findElement(By.id("first_name")).sendKeys("Sumaiya");
        driver.findElement(By.id("user_email")).sendKeys("sumaiyasinthy124@gmail.com");
        driver.findElement(By.id("user_pass")).sendKeys("FU40GH##de");

        WebElement femaleRadio = driver.findElement(By.id("radio_1665627729_Female"));
        femaleRadio.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Actions action = new Actions(driver);
        WebElement birthDate = driver.findElement(By.xpath("//input[@data-label='Date of Birth']"));
        action.click(birthDate).perform();

        WebElement yearInput = driver.findElement(By.xpath("//input[@aria-label='Year']"));
        yearInput.clear();
        yearInput.sendKeys("1997");

        WebElement birthDateToSelect = driver.findElement(By.xpath("//span[@aria-label='July 3, 1997']"));
        birthDateToSelect.click();

        driver.findElement(By.name("input_box_1665629217")).sendKeys("Bangladeshi");
        driver.findElement(By.name("phone_1665627880")).sendKeys("01711067329");

        WebElement dropdown = driver.findElement(By.id("country_1665629257"));
        dropdown.click();
        Thread.sleep(3000);

        for(int i= 0; i < 18; i++) {
            action.sendKeys(Keys.ARROW_DOWN).perform();
        }
        action.sendKeys(Keys.ENTER).perform();


        WebElement emergencyContact = driver.findElement(By.id("phone_1665627865"));
        action.click(emergencyContact).sendKeys("01712986549").perform();

        js.executeScript("window.scrollBy(500,600)");

        WebElement parking = driver.findElement(By.id("radio_1665627931_Yes"));
        parking.click();

        WebElement roomPreference = driver.findElement(By.id("radio_1665627997_Single Room"));
        roomPreference.click();


        WebElement termsCheckbox = driver.findElement(By.id("privacy_policy_1665633140"));

        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        WebElement successMessage = driver.findElement(By.id("ur-submit-message-node")).findElement(By.tagName("ul"));
        System.out.println(successMessage.getText());
        Assertions.assertEquals("User successfully registered.", successMessage.getText());
    }

    @AfterAll
    public void closeBrowser(){
        driver.quit();
    }
}

