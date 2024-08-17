package hooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {

	 WebDriver driver;
	 Properties p;
     
	 
	 //@Before is called hook.It excutes before executing all steps of step definition
	@Before
    public void setup() throws  IOException, InterruptedException
    {
		
    	driver=BaseClass.initilizeBrowser();//calling this method from Baseclass,it returns driver. We will store that driver here.It initiates browser
    	    	
    	p=BaseClass.getProperties();//get properties file from baseclass.
    	driver.get(p.getProperty("appURL"));//fetch application url from properties file in baseclass  and launch browser
    	
    	driver.manage().window().maximize();
    	Thread.sleep(1000);
    
    			
	}
		
	 //@After is called hook.It excutes after executing all steps of step definition 
    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        	
       driver.quit();
       
    }
    
  //@AfterStep is called hook.It excutes after execution of everystep in step definition.@BeforeStep is also there.we dont use much
    @AfterStep
    public void addScreenshot(Scenario scenario) { //capture screenshot for failed scenario .We will run Scenarios from feature file.
    	//Scenario is predefined class and scenario is object.
        
    	// this is for cucumber junit report
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);//store screenshot in byte array type.
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
        }
      
    }
   
}
