package page;

import java.io.IOException;

import org.openqa.selenium.By;import helper.Base;

public class TresgenAgeGatePage extends Base {

	String baseURL = "https://www.tresgeneraciones.com/";

	// *********Web Elements*********
	By WelcomeText = By.xpath("//span[@class='nav-line-1'][text()='Hello. Sign in']");
	By LegalText = By.xpath("//h2[contains(text(),'~ You must be of legal purchase age to Enter This')]");

	
	
	
	public void launchTresgen() {
		driver.get(baseURL);
		report("Website URL Launched Successfully");
	}
	
//	public void goToAgeGatePopUpPage() throws InterruptedException, IOException {
//		
//		assertions(LegalText, propertyFile("VAgeText"));
//		report("ValidAgeTextVerified");
//	}
	
	
	public void goToAgeGatePopUpPage() throws InterruptedException, IOException {
		
		assertions(LegalText, CSVfile().get("Shrey"));
		report("ValidAgeTextVerified");
	}
	
}