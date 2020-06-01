package testInitialization;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import page.TresgenAgeGatePage;

public class TresgenAgeGate {

	TresgenAgeGatePage tpage;

	@Test(priority = 0, description = "To verify Successful Launch of URL")
	public void UILaunch() throws InterruptedException, IOException {
		this.tpage = new TresgenAgeGatePage();
		this.tpage.launchTresgen();
		Reporter.log("Launch URL Successfully");

	}
	
	
	@Test(priority = 1, description = "To verify valid age gate text")
	public void ValidAgeGateText() throws InterruptedException, IOException {
		this.tpage = new TresgenAgeGatePage();
		this.tpage.goToAgeGatePopUpPage();
		Reporter.log("Valid AgeGate TextVerified");

	}
}