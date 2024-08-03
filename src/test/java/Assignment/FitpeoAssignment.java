package Assignment;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class FitpeoAssignment {

	static WebDriver driver = new ChromeDriver();

	public static void main(String[] args) throws InterruptedException {

		//1.Navigate to the FitPeo Homepage
		driver.get("https://www.fitpeo.com/home");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

		//2.Navigate to the Revenue Calculator Page
		driver.findElement(By.xpath("//div[contains(text(),'Revenue')]")).click();
		Thread.sleep(7000);

		//3.Scroll down to Slider section
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
		js.executeScript("arguments[0].scrollIntoView();", slider);

		//4.Adjust the Slider
		Actions actions = new Actions(driver);
		actions.clickAndHold(slider)
		.moveByOffset(100, 0) 
		.release()
		.perform();
		System.out.println("Slider moved to 860");

		//5.Update the Text Field
		String actualCount = "560";
		WebElement input = driver.findElement(By.xpath("//input[@type='number']"));
		input.click();
		js.executeScript("arguments[0].value = '';", input);	
		input.sendKeys(actualCount);		

		//6.Validate Slider Value
		WebElement Totalcount = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-12bch19'])[2]"));
		String expCount = Totalcount.getText();
		Assert.assertEquals(actualCount, expCount);
		System.out.println("Slider value validated");

		//7.Select CPT code
		js.executeScript("window.scrollBy(0,550)"); 
		WebElement Checkbox = driver.findElement(By.xpath("(//input[@data-indeterminate='false'])[1]"));
		Checkbox.click();
		WebElement Checkbox1 = driver.findElement(By.xpath("(//input[@data-indeterminate='false'])[2]"));
		Checkbox1.click();
		WebElement Checkbox2 = driver.findElement(By.xpath("(//input[@data-indeterminate='false'])[3]"));
		Checkbox2.click();
		WebElement Checkbox3 = driver.findElement(By.xpath("(//input[@data-indeterminate='false'])[8]"));
		Checkbox3.click();

		//8.Validate Total Recurring Reimbursement
		js.executeScript("window.scrollBy(0,550)"); 
		WebElement total  = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-hocx5c'])[4]"));
		String totalAmount = total.getText();
		System.out.println("Total Recurring Reimbursement: "+totalAmount);

		//9.Verify the header
		WebElement cpt = driver.findElement(By.xpath("//h4[contains(text(),'Medicare Eligible')]"));
		js.executeScript("arguments[0].scrollIntoView();", cpt);
		input.click();
		js.executeScript("arguments[0].value = '';", input);	
		input.clear();
		input.sendKeys("820");	
		String actualheader = "$110700";
		js.executeScript("window.scrollBy(0,550)"); 
		WebElement header  = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-hocx5c'])[4]"));
		String expheader = total.getText();
		Assert.assertEquals(actualheader, expheader);
		System.out.println("Header validated");
		
		driver.quit();
	}
}