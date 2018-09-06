package com.vaannila.domain;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver; 
//Java imports
import java.util.concurrent.TimeUnit;
import java.util.List;

public class UserTest {
	private WebDriver driver;
	private String baseUrl;
  
 @BeforeTest
 public void beforeTest() throws Exception {
	 //giving the location where selenium chrome driver is located in my file system
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sai Aditya\\Downloads\\chromedriver_win32\\chromedriver.exe");
	 //If chrome driver has to work on a CI server, then we have to download xvfb, set perspective ratios and reboot the server. So, we are going to use chrome in a headless (sans GUI) mode.
	 ChromeOptions co = new ChromeOptions();
	 co.addArguments("--headless");
	 co.addArguments("--start-maximized");
	 //initializing a chrome driver with driver object
	 driver = new ChromeDriver(co);
	 //asking the chrome driver to implicitly wait for 5 seconds before moving on
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 //setting the value of baseUrl variable
	 baseUrl = "http://localhost:9999/myuser/userRegistration.htm";
	 }	
  
 @Test
  public void userreg() throws InterruptedException{
	//this step opens up chrome browser with the aforementioned application
			driver.get(baseUrl);
			
			//getting web elements of name and password. Then we send info to those via sendKeys()
			WebElement name = driver.findElement(By.id("name"));
			WebElement password = driver.findElement(By.id("password"));
			name.sendKeys("aditya");
			password.sendKeys("aditya123");
			System.out.println("fields set...");
			
			//getting radio element buttons in our user registration webpage
			WebElement radio1 = driver.findElement(By.id("gender1"));
			WebElement radio2 = driver.findElement(By.id("gender2"));
			WebElement radio3 = driver.findElement(By.id("gender3"));
			
			//toggling radio buttons
			radio1.click();
			System.out.println("Radio 1 button is selected");
			
			radio2.click();
			System.out.println("Radio 2 button is selected");
			
			radio3.click();
			System.out.println("Radio 3 button is selected");
			
			//Selecting country from Drop down ( Used Id to identify the element )
			Select options = new Select(driver.findElement(By.id("country")));
			//selecting India option via select by visible text
			options.selectByVisibleText("India");
			//Using sleep command so that the change is visible
			Thread.sleep(2000);
			
			//selecting USA via select by index mechanism
			options.selectByIndex(2);
			//using sleep command so that the change is visible
			Thread.sleep(2000);
			
			//Print all the options for the selected drop down and select one option of your choice
			// Get the size of the Select element
			List<WebElement> optionsize = options.getOptions();
			int iListSize = optionsize.size();
			
			//using for loop to go through all options in drop down
			for(int i=0;i<iListSize;i++) {
				//storing the value of an option sValue variable
				String sValue = options.getOptions().get(i).getText();
				//printing out the variable
				System.out.println(sValue);
				// Putting a check on each option that if any of the option is equal to 'USA' then select it
				if(sValue.equals("India")) {
					options.selectByIndex(i);
					break;
				}
			}
			
			//Getting the textarea element of our webpage
			WebElement textbox = driver.findElement(By.id("aboutYou"));
			textbox.sendKeys("I am a good boy who is looking to learn java");
			
			//getting checkbox elements of our webpage and toggling them
			WebElement option1c = driver.findElement(By.id("community1"));
			option1c.click();
			
			if(option1c.isSelected()) {
				System.out.println("Checkbox is toggled on");
			} else {
				System.out.println("Checkbox is toggled off");
			}
			
			//selecting another element (mailing list option) in our webpage
			WebElement mail = driver.findElement(By.id("mailingList1"));
			mail.click();
			
			if(mail.isSelected()) {
				System.out.println("Checkbox is toggled on");
			} else {
				System.out.println("Checkbox is toggled off");
			}
			
			//getting the submit option of our webpage
			WebElement query = driver.findElement(By.cssSelector("input"));
			query.click();
			System.out.println("done submitting...");
			
			//clearing all the fields of our webpage
			name.clear();
			password.clear();
			textbox.clear();
			System.out.println("Fields cleared...");
  }

  @AfterTest
  public void afterTest() throws Exception {
	  driver.close();
  }

}
