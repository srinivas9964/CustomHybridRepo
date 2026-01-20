package manager;
import pages.*;

public class PageObjectManager {
	private LoginPage loginPage;
	
	 public LoginPage getLoginPage() {
	        if (loginPage == null) {
	            loginPage = new LoginPage();
	        }
	        return loginPage;
	    }
   

}
