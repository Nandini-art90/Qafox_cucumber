package stepDefinitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;


public class RegistrationSteps {

	 WebDriver driver;
     HomePage hp;
     LoginPage lp;
     AccountRegistrationPage regpage;
     
	@Given("the user navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
	
		hp=new HomePage(BaseClass.getDriver());
    	hp.clickMyAccount();
        hp.clickRegister();
                   
	}

	@When("the user enters the details into below fields")
	public void user_enters_the_details_into_below_fields(DataTable dataTable) { //datatable is 2d array contians row and colmns(keys and values).Import datatable from cucmber class.
		
		Map<String, String> dataMap = dataTable.asMap(String.class,String.class);//asMap method covert datatable into Hashmap/map.(data is in keys and value pair .both of datatype string).
	    
		regpage=new AccountRegistrationPage(BaseClass.getDriver());//account registration page object
		regpage.setFirstName(dataMap.get("firstName"));//dataMap.get("firstName")=retrieve the value of first name. data is assigned from registration file to Registrationsteps java file.
		regpage.setLastName(dataMap.get("lastName"));//dataMap.get("lastName")=retrieve the value of lastName . data is assigned from registration file to Registrationsteps java file.
		regpage.setEmail(BaseClass.randomAlphaNumeric()+"@gmail.com");// data is assigned from registration file to Registrationsteps java file.
		regpage.setTelephone(dataMap.get("telephone"));//dataMap.get("telephone")=retrieve the value of telephone . data is assigned from registration file to Registrationsteps java file.
		regpage.setPassword(dataMap.get("password"));//dataMap.get("password")=retrieve the value of password . data is assigned from registration file to Registrationsteps java file.
		regpage.setConfirmPassword(dataMap.get("password"));//dataMap.get("password")=retrieve the value of password(should be same as above) . data is assigned from registration file to Registrationsteps java file
		
	}

	@When("the user selects Privacy Policy")
	public void user_selects_privacy_policy() {
		regpage.setPrivacyPolicy();
	}

	@When("the user clicks on Continue button")
	public void user_clicks_on_continue_button() {
		regpage.clickContinue();
	}

	@Then("the user account should get created successfully")
	public void user_account_should_get_created_successfully() {
		
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
 }
