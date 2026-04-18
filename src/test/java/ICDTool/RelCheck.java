package ICDTool;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RelCheck {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		FileExtract fe = new FileExtract();
		ArrayList<String> data = fe.getData("CHG No.");
		for(int j=0;j<data.size();j++)
		{
			System.out.println(data.get(j));
		}

		
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://servicedesk.esr.nhs.uk/maximo/webclient/login/login.jsp?welcome=true");
		
		driver.findElement(By.id("username")).sendKeys("chadalavadam");
		driver.findElement(By.id("password")).sendKeys("Rabbit594$");
		driver.findElement(By.id("loginbutton")).click();
		
			
		driver.findElement(By.id("gotobutton_image")).click();
		
		WebElement Change = driver.findElement(By.xpath("//span[text()='Change']"));
		WebElement Changes = driver.findElement(By.xpath("//span[text()='Changes']"));
				
		Actions a = new Actions(driver);
		a.moveToElement(Change).build().perform();
		Changes.click();
		
		for(int j=1;j<data.size();j++)
		{
			
			System.out.println(data.get(j));
			String currentCR = data.get(j);
			if (currentCR.length() == 0)
				break;
			
			WebElement CCR = driver.findElement(By.xpath("//input[@id='m6a7dfd2f_tfrow_[C:1]_txt-tb']"));
		    
			CCR.sendKeys(Keys.CONTROL + "a");
		    CCR.sendKeys(Keys.DELETE);
		    CCR.sendKeys(currentCR);
		    CCR.sendKeys(Keys.ENTER);
		    
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    wait.until(ExpectedConditions.textToBePresentInElementLocated(
		            By.xpath("//a[@id='m6a7dfd2f_tdrow_[C:1]_ttxt-lb[R:0]']"),
		            currentCR
		    ));
		
		    String CR = driver.findElement(By.xpath("//a[@id='m6a7dfd2f_tdrow_[C:1]_ttxt-lb[R:0]']")).getText();
		
	    	String Owner = driver.findElement(By.xpath("//td[@id='m6a7dfd2f_tdrow_[C:11]-c[R:0]']")).getText();
		
		    if(Owner.equals("IBMREL"))
		    {
			   System.out.println("Change " +CR+ " is moved to " +Owner);
		    }
		    else		    	
		    {
		    	System.out.println("Change " +CR+ " is not moved to " +Owner);
		    }
		}
		
		/*for (int j = 1; j < data.size(); j++) {
		    String currentCR = data.get(j);
		    System.out.println("Iteration " + j + ": Searching for CR - " + currentCR);
		    WebElement searchBox = driver.findElement(By.xpath("//input[@id='m6a7dfd2f_tfrow_[C:1]_txt-tb']"));
		    searchBox.sendKeys(Keys.CONTROL + "a");
		    searchBox.sendKeys(Keys.DELETE);
		    searchBox.sendKeys(currentCR);
		    searchBox.sendKeys(Keys.ENTER);
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    wait.until(ExpectedConditions.textToBePresentInElementLocated(
		            By.xpath("//a[@id='m6a7dfd2f_tdrow_[C:1]_ttxt-lb[R:0]']"),
		            currentCR
		    ));
		    
		    String CR = driver.findElement(By.xpath("//a[@id='m6a7dfd2f_tdrow_[C:1]_ttxt-lb[R:0]']")).getText();
		    String Owner = driver.findElement(By.xpath("//td[@id='m6a7dfd2f_tdrow_[C:11]-c[R:0]']")).getText();
		    if (Owner.equals("IBMREL")) {
		        System.out.println(":white_check_mark: Iteration " + j + ": Change " + CR + " is moved to " + Owner);
		    } else {
		        System.out.println(":x: Iteration " + j + ": Change " + CR + " is not moved to " + Owner);
		    }
		    Thread.sleep(1000);
		}*/
	}

}
