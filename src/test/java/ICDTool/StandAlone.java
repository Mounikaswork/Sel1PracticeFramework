package ICDTool;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		FileExtract fe = new FileExtract();
		ArrayList<String> fileData = fe.getData("CHG No.");
		ArrayList<String> data = new ArrayList<>();
		for(int j=0;j<fileData.size();j++)
		{
//			System.out.println(data.get(j));
			if (fileData.get(j).length() > 0)
				data.add(fileData.get(j));
			
		
		}

						
		WebDriverManager.edgedriver().setup();
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
		
		WebElement RunReports = driver.findElement(By.xpath("//span[text()='Run Reports']"));
		
		RunReports.click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//a[text()='ESR ICD Software Changes']")).click();
		int j;
		
		for(j=1;j<data.size();j++)
		{

		driver.findElement(By.id("m6579486c-ta")).sendKeys(data.get(j));
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
        while(j==1)
        {
        // Wait until two tabs are open
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driverInstance -> driverInstance.getWindowHandles().size() == 2);
         break;
        }

		Set<String> windows = driver.getWindowHandles()	;
		
		Iterator<String> it = windows.iterator();
		String Parent = it.next();
		String Child = it.next();
		
		driver.switchTo().window(Child);
		System.out.println("Switched to second tab: " + driver.getTitle());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//driver.switchTo().frame(driver.findElement(By.id("exportReportDialogiframe")));
		
		WebElement exportButton = driver.findElement(By.xpath("//input[@name='exportReport']"));
		//exportButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", exportButton);
        Thread.sleep(4000); // allow scroll to settle
        exportButton = driver.findElement(By.xpath("//input[@name='exportReport']"));
        js.executeScript("arguments[0].click();", exportButton);
		
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='exportReport']")));
		element1.click();*/
		
        
		System.out.println("Clicked on export button ");
		
		/*WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='exportReportDialogokButton'] //input[@title='OK']")));
		element.click();*/
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='exportReportDialogokButton'] //input[@title='OK']")));

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", element);
        Thread.sleep(4000); // allow scroll to settle
        element = driver.findElement(By.xpath("//div[@id='exportReportDialogokButton'] //input[@title='OK']"));
        js1.executeScript("arguments[0].click();", element);
		
		
		//driver.findElement(By.xpath("//div[@id='exportReportDialogokButton'] //input[@title='OK']")).click();
        Thread.sleep(5000); // Wait for download to complete
					   
		driver.switchTo().window(Parent);
		driver.findElement(By.xpath("//a[text()='ESR ICD Software Changes']")).click();
		}
	
	}

	

}
