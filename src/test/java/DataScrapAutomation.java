import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataScrapAutomation {
    WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void scrapData() throws IOException {
        FileWriter writer = new FileWriter("./src/test/resources/table_data.txt");

        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");
        List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class, 'shares-table')]//tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                writer.write(cell.getText() + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }
    @AfterAll
    public void closeBrowser(){
        driver.quit();
    }
}
