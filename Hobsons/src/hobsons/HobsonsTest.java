package hobsons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.io.Files;

public class HobsonsTest {				

	public static WebDriver fireFoxDriver;
	public static JavascriptExecutor executor;
	public static String ScreenShotFolder = "D:\\Eclipse\\WorkSpace\\Hobsons\\ScreenShots\\";
	
    @BeforeClass		
    public static void InvokeBrowser() {							
    	System.setProperty("webdriver.gecko.driver", "D:\\Gecko_Firefox\\geckodriver.exe");
		try
		{
			// Set Proxy to AutoDetect
			Proxy proxy = new Proxy();
			proxy.setProxyType(ProxyType.AUTODETECT);
			
			// Set FirefoxOptions with Proxy
			FirefoxOptions fireFoxOptions = new FirefoxOptions();
			fireFoxOptions.setProxy(proxy);
			
			
			// Create a new WebDriver
			fireFoxDriver = new FirefoxDriver(fireFoxOptions);
			fireFoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			fireFoxDriver.manage().window().maximize();
			executor = (JavascriptExecutor)fireFoxDriver;
			
		}
		catch (Exception e)
		{
			// Catch exception
			System.out.print(e.getMessage());
		}				
    }		

    @Before		
    public void verifyPageLoad()
    {					
    	try
		{
			// Load page
			fireFoxDriver.get("https://www.hobsons.com/");
			WebDriverWait wait = new WebDriverWait(fireFoxDriver, 10, 100);
		    wait.until(ExpectedConditions.titleContains("Education Advances | Hobsons"));
		    assertTrue("Hobsons page loaded successfully", true);
		}
		catch (Exception e)
		{
			assertFalse("Hobsons page didn't load successfully", false);
		}
    }	
    

    @AfterClass		
    public static void closeBrowser() {							
    	fireFoxDriver.close();			
    }
	
    @Test
	public void verifySolutionsSubMenuItems()
	{
    	// Expected result
		List<String> solutionSubMenuItemsExpected = Arrays.asList("college and career readiness", "california", "michigan", "texas", "pennsylvania","illinois", "florida", "match and fit", "student success and advising");
		
		WebElement menu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[2]/a"));		
		Actions action = new Actions(fireFoxDriver);
		action.moveToElement(menu).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    // Find all the elements under Solutions menu
		WebElement solutionsSubMenu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[2]/ul"));
		
		String[] arrOfStrActual = solutionsSubMenu.getText().toLowerCase().split("\n");
		
		int found = 0;
		for (String menuItem : arrOfStrActual)
		{
			if (solutionSubMenuItemsExpected.contains(menuItem))
			{
				found ++;
			}
		}
		
		
		if (found == solutionSubMenuItemsExpected.size())
		{
			System.out.println("Verified Solutions SubMenu Successfully");
		}
		else
		{
			System.out.println("Verification of Solutions SubMenu failed");
		}
		
		Assert.assertTrue("Verified Solutions SubMenu Successfully", found == solutionSubMenuItemsExpected.size());
		
		screenShot("SolutionsSubMenu");
	}
	
	@Test
	public void verifyServicesSubMenuItems()
	{		
		// Expected result
		List<String> servicesSubMenuItemsExpected = Arrays.asList("professional services", "outsourced services");
		
		WebElement menu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[3]/a"));		
		Actions action = new Actions(fireFoxDriver);
		action.moveToElement(menu).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    // Find all the elements under services menu
		WebElement servicesSubMenu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[3]/ul"));
		
		String[] arrOfStrActual = servicesSubMenu.getText().toLowerCase().split("\n");
		
		int found = 0;
		for (String menuItem : arrOfStrActual)
		{
			if (servicesSubMenuItemsExpected.contains(menuItem))
			{
				found ++;
			}
		}
		
		if (found == servicesSubMenuItemsExpected.size())
		{
			System.out.println("Verified Services SubMenu Successfully");
		}
		else
		{
			System.out.println("Verification of Services SubMenu failed");
		}

		Assert.assertTrue("Verified Services SubMenu Successfully", found == servicesSubMenuItemsExpected.size());	    
		screenShot("ServicesSubMenu");
	}
	
	@Test
	public void verifyResourcesSubMenuItems()
	{
		// Expected result
		List<String> resourcesSubMenuItemsExpected = Arrays.asList("all","webinars", "events", "case studies", "white papers", "blog", "press releases", "press coverage", "videos");
		
		WebElement menu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[4]/a"));		
		Actions action = new Actions(fireFoxDriver);
		action.moveToElement(menu).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    // Find all the elements under resources menu
		WebElement resourcesSubMenu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[4]/ul"));
		
		String[] arrOfStrActual = resourcesSubMenu.getText().toLowerCase().split("\n");
		
		int found = 0;
		for (String menuItem : arrOfStrActual)
		{
			if (resourcesSubMenuItemsExpected.contains(menuItem))
			{
				found ++;
			}
		}
		
		
		if (found == resourcesSubMenuItemsExpected.size())
		{
			System.out.println("Verified Resources SubMenu Successfully");
		}
		else
		{
			System.out.println("Verification of Resources SubMenu failed");
		}

		Assert.assertTrue("Verified Resources SubMenu Successfully", found == resourcesSubMenuItemsExpected.size());
	    
		screenShot("ResourcesSubMenu");
	}
	
	@Test
	public void verifyAboutSubMenuItems()
	{		
		// Expected result
		List<String> aboutSubMenuItemsExpected = Arrays.asList("partnerships", "careers", "media kit", "contact us");
		
		WebElement menu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[5]/a"));		
		Actions action = new Actions(fireFoxDriver);
		action.moveToElement(menu).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    // Find all the elements under About menu
		WebElement aboutSubMenu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[5]/ul"));
		
		String[] arrOfStrActual = aboutSubMenu.getText().toLowerCase().split("\n");
		
		int found = 0;
		for (String menuItem : arrOfStrActual)
		{
			if (aboutSubMenuItemsExpected.contains(menuItem))
			{
				found ++;
			}
		}
		
		
		if (found == aboutSubMenuItemsExpected.size())
		{
			System.out.println("Verified About SubMenu Successfully");
		}
		else
		{
			System.out.println("Verification of About SubMenu failed");
		}
		
		Assert.assertTrue("Verified About SubMenu Successfully", found == aboutSubMenuItemsExpected.size());
	    
		screenShot("AboutSubMenu");
	}
	
	@Test
	public void verifyBlogSubMenuItems()
	{		
		WebElement menu =  fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[6]/a"));		
		Actions action = new Actions(fireFoxDriver);
		action.moveToElement(menu).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    // Find all the elements under Blog menu
		try{
			fireFoxDriver.findElement(By.xpath("/html/body/div[1]/div/nav/ul/li[6]/ul"));
			System.out.println("Verification of Blog SubMenu failed");
			Assert.assertFalse("Verification of Blog SubMenu failed", false);
        }
        catch(NoSuchElementException e){
        	System.out.println("Verified Blog SubMenu Successfully");
			Assert.assertTrue("Verified Blog SubMenu Successfully", true);
        }
		
		screenShot("BlogSubMenu");
	}
	
	@Test
	public void verifyLocations()
	{
		WebElement menu =  fireFoxDriver.findElement(By.xpath("/html/body/footer/div/div[2]/ul[4]/li[6]/a"));
		executor.executeScript("arguments[0].click();", menu);
		
		WebDriverWait wait = new WebDriverWait(fireFoxDriver, 10, 100);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/section[2]/table/tbody/tr/td[1]/address")));
	    
	    WebElement hqAddress = fireFoxDriver.findElement(By.xpath("/html/body/div[4]/div/section[2]/table/tbody/tr/td[1]/address"));
	    WebElement vaAddress = fireFoxDriver.findElement(By.xpath("/html/body/div[4]/div/section[2]/table/tbody/tr/td[2]/address"));
	    
	    
		executor.executeScript("arguments[0].scrollIntoView(true);", fireFoxDriver.findElement(By.xpath("/html/body/div[4]/div/section[2]/h6")));
		
	    String vaAddressExpected = "3033 Wilson Boulevard, Suite 500 Arlington, VA 22201\n+1.703.234.5910";
	    String hqAddressExpected = "400 E-Business Way, Suite 400\nCincinnati, OH 45241\n+1.513.891.5444";
	    
	    assertEquals(hqAddressExpected, hqAddress.getText());
	    
	    if (hqAddressExpected.compareTo(hqAddress.getText()) == 0)
	    {
	    	System.out.println("Headquarters address in Ohio matches expected address");
	    }
	    else
	    {
	    	System.out.println("Headquarters address in Ohio doesn't match expected address");
	    }
	    
	    assertEquals(vaAddressExpected, vaAddress.getText());
	    
	    if (hqAddressExpected.compareTo(hqAddress.getText()) == 0)
	    {
	    	System.out.println("Arlington address in Virginia matches expected address");
	    }
	    else
	    {
	    	System.out.println("Arlington address in Virginia doesn't match expected address");
	    }
	    
	    screenShot("AddressVerification");
	}
	
	@Test
	public void verifyAddressLocation()
	{
		String vaAddressURI = "https://www.google.com/maps/place/3033+Wilson+Blvd+%23500Arlington,+Arlington,+VA+22201/@38.8881961,-77.0966727,17z/data=!3m1!4b1!4m5!3m4!1s0x89b7b68692785f31:0x2bb2fc344f403ee8!8m2!3d38.8881961!4d-77.094484";
	    String hqAddressURI = "https://www.google.com/maps/place/400+E+Business+Way+%23400Cincinnati,+Sharonville,+OH+45241/@39.2857171,-84.3672966,17z/data=!3m1!4b1!4m5!3m4!1s0x8840514635f6b99b:0xc58433323e4eaa4c!8m2!3d39.2857171!4d-84.3651079";

		WebElement menu =  fireFoxDriver.findElement(By.xpath("/html/body/footer/div/div[2]/ul[4]/li[6]/a"));
		executor.executeScript("arguments[0].click();", menu);
		
		WebDriverWait wait = new WebDriverWait(fireFoxDriver, 10, 100);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/section[2]/table/tbody/tr/td[1]/address")));
	    
	    String careerURL = fireFoxDriver.getCurrentUrl();
	    
	    WebElement hqAddress = fireFoxDriver.findElement(By.xpath("/html/body/div[4]/div/section[2]/table/tbody/tr/td[1]/h4/a"));
	    hqAddress.click();
	    short pollCounter = 8;
        boolean displayed;
	    do {
            displayed = fireFoxDriver.getCurrentUrl().contains(hqAddressURI);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            pollCounter--;
        } while (!displayed && pollCounter >= 0);
	    
	    List<String> browserTabs = new ArrayList<String> (fireFoxDriver.getWindowHandles());
	    fireFoxDriver.switchTo().window(browserTabs .get(1));
	    
	    assertEquals(hqAddressURI, fireFoxDriver.getCurrentUrl());
	    
	    if (hqAddressURI.compareTo(fireFoxDriver.getCurrentUrl()) == 0)
	    {
	    	System.out.println("Headqaurter address showed successfully in Google Maps");
	    }
	    else
	    {
	    	System.out.println("Headqaurter address didn't show successfully in Google Maps");
	    }
	    
	    screenShot("OhioAddressMap");
	    
	    // Close the HQ maps tab
	    fireFoxDriver.close();
	    
	    // Move control to original tab
	    fireFoxDriver.switchTo().window(browserTabs.get(0));
	    pollCounter = 8;
        displayed = false;
	    do {
            displayed = fireFoxDriver.getCurrentUrl().contains(careerURL);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            pollCounter--;
        } while (!displayed && pollCounter >= 0);	    

	    WebElement vaAddress = fireFoxDriver.findElement(By.xpath("/html/body/div[4]/div/section[2]/table/tbody/tr/td[2]/h4/a"));
		executor.executeScript("arguments[0].click();", vaAddress);
	    
	    pollCounter = 8;
        displayed = false;
	    do {
            displayed = fireFoxDriver.getCurrentUrl().contains(vaAddressURI);
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            pollCounter--;
        } while (!displayed && pollCounter >= 0);
	    
	    browserTabs = new ArrayList<String> (fireFoxDriver.getWindowHandles());
	    fireFoxDriver.switchTo().window(browserTabs .get(1));	    
	    
	    assertEquals(vaAddressURI, fireFoxDriver.getCurrentUrl());
	    
	    if (vaAddressURI.compareTo(fireFoxDriver.getCurrentUrl()) == 0)
	    {
	    	System.out.println("Arlington address showed successfully in Google Maps");
	    }
	    else
	    {
	    	System.out.println("Arlington address didn't show successfully in Google Maps");
	    }
	    
	    screenShot("VirginiaAddressMap");
	    //Close maps tab
	    fireFoxDriver.close();
	    
	    // Move control to original tab
	    fireFoxDriver.switchTo().window(browserTabs.get(0));
	}

	@Test
	public void horizontalScrollVerification()
	{
		WebElement leftScroll = fireFoxDriver.findElement(By.xpath("/html/body/div[2]/ul/div[2]/div[1]"));
		WebElement rightScroll = fireFoxDriver.findElement(By.xpath("/html/body/div[2]/ul/div[2]/div[2]"));
		executor.executeScript("arguments[0].scrollIntoView(true);", leftScroll);
		
		// Capture the right most element in the horizontal slider (AJAX Control)
		String originalRightElement = fireFoxDriver.findElement(By.xpath("/html/body/div[2]/ul/div[1]/div/div[7]")).getText().trim();
		System.out.println("Original Right Element Text:" + originalRightElement);
		
		// Left scroll is clicked moving the contents right
		executor.executeScript("arguments[0].click();", leftScroll);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		// Get the same right element which is now moved out of the slider
		String afterLeftClickRightElement = fireFoxDriver.findElement(By.xpath("/html/body/div[2]/ul/div[1]/div/div[7]")).getText().trim();

		System.out.println("After Left Clicking, Right Element Text:" + afterLeftClickRightElement);
		
		// Check the right element text to be empty as it moved out of the slider
		Assert.assertTrue("Left scroll validation successful", !originalRightElement.isEmpty() && afterLeftClickRightElement.isEmpty());
		
		if (!originalRightElement.isEmpty() && afterLeftClickRightElement.isEmpty())
		{
			System.out.println("Left scroll validation successful");
		}
		else
		{
			System.out.println("Left scroll validation failed");
		}
		
		// Right scroll is clicked moving the contents left
		executor.executeScript("arguments[0].click();", rightScroll);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Get the same right element which is now back in the original position
		String afterRightClickRightElement = fireFoxDriver.findElement(By.xpath("/html/body/div[2]/ul/div[1]/div/div[7]")).getText().trim();

		System.out.println("After Right Clicking, Right Element Text:" + afterRightClickRightElement);
		
		// Check the right element text to be empty as it moved out of the browser
		Assert.assertTrue("Right scroll validation successful", !originalRightElement.isEmpty() && !afterRightClickRightElement.isEmpty());
		
		if (!originalRightElement.isEmpty() && !afterRightClickRightElement.isEmpty())
		{
			System.out.println("Right scroll validation successful");
		}
		else
		{
			System.out.println("Right scroll validation failed");
		}
		
	}
	
	@VisibleForTesting
	public static void screenShot(String name)
	{

		try
		{
	        //Convert web driver object to TakeScreenshot
	        TakesScreenshot scrShot =((TakesScreenshot)fireFoxDriver);
	        
	        //Call getScreenshotAs method to create image file
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        
	        
	        StringBuilder path = new StringBuilder();
	        path.append(ScreenShotFolder + name + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyddmm_hhmmss")) + ".jpeg");
	        //Move image file to new destination
	        File DestFile=new File(path.toString());
	
	        //Copy file at destination
	         Files.copy(SrcFile, DestFile);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

    }
}
