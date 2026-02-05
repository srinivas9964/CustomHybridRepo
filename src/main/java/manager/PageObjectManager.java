package manager;
import pages.*;

public class PageObjectManager {
	private LoginPage loginPage;
	private HomePage homePage;
	private PIMPage pimPage;
	private TimePage timePage;
	
	 public LoginPage getLoginPage() {
	        try {
	        	if (loginPage == null) {
		            loginPage = new LoginPage();
		        }
		        return loginPage;
	        }
	        catch(Exception e) {
	        	System.out.println(e.getMessage());
	        	return null;
	        }
	    }
	 
	 public HomePage getHomePage() {
	       try {
	    	   if (homePage == null) {
		            homePage = new HomePage();
		        }
		        return homePage;
	       }
	       catch(Exception e) {
	    	   System.out.println(e.getMessage());
	    	   return null;
	       }
	    }
	 
	 public PIMPage getPIMPage() {
	        try {
	        	if (pimPage == null) {
		            pimPage = new PIMPage();
		        }
		        return pimPage;
	        }
	        catch(Exception e) {
	        	System.out.println(e.getMessage());
	        	return null;
	        }
	    }

	 public TimePage getTimePage() {
	        try {
	        	if (timePage == null) {
	        		timePage = new TimePage();
		        }
		        return timePage;
	        }
	        catch(Exception e) {
	        	System.out.println(e.getMessage());
	        	return null;
	        }
	    }
}
