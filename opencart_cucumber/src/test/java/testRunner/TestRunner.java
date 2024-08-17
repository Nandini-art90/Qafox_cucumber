package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
					//features= {".//Features/"},      
					//features= {".//Features/Login.feature"},
					//features= {".//Features/Registration.feature"},
		
					//features= {".//Features/LoginDDTExcel.feature"},
					features= {".//Features/Login.feature",".//Features/Registration.feature"},//run two feature files (login feature and registration feature
					//features= {"@target/rerun.txt"},  //Executes only failed scenarios
					glue={"stepDefinitions","hooks"}, //as hook java file contins methos to execute before and after setps.
					
					//We will specify report part here. 
					plugin= {
							    //To generate cucumber report
								"pretty", "html:reports/myreport.html",   //pretty=parameter(key), html:reports/myreport.html=value.Generate report under reports folder with name myreport.html.
								
								//To generate extent report.Extent report info(like location,screenshots,date format) are specified in extent.properties file under resources folder.
								"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",//it will generate report based on above details in extent.properties file under resources folder.
								"rerun:target/rerun.txt", //failesd file will be stored in target folder with name rerun.txt(This filename and folder name are hardcoded,we should not change).Once after the failed test
								//scenario is passed,this file becomes empty.
							
							},
							
					dryRun=false,    // checks mapping between scenario steps and step definition methods.It wont execute any scenarios.It will show whether step method  is there for evey scenario
					monochrome=true,    // to avoid junk characters in console output
					publish=true  // to publish report in cucumber server
					//tags="@sanity"  // this will execute all  scenarios tagged with @sanity in  feature file.
					//tags="@regression"
					//tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression..and is a keyword.
					//tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
					//tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression
		)
public class TestRunner {

}


