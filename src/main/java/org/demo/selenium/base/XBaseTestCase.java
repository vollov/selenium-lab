package org.demo.selenium.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class XBaseTestCase {
	
	public WebDriver driver;
	public Region region;
	
	@Before
	public void initWebDriver() {
		System.out.println("TeigBaseTestSuit -- initWebDriver()");
		Properties prop = new Properties();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("test.properties");
		try {
			prop.load(inputStream);

			String region_string = prop.getProperty("region");
			String browser_string = prop.getProperty("browser");

			RegionManager regionManager = XStreamUtils.getRegionManager();

			region = regionManager.getRegion(region_string);

			if (browser_string.equals("ie")) {
				
				// if using IE point to Internet Option/Settings/Use automatic
				// configuration script if need
				Proxy proxy = new Proxy();
				proxy.setProxyAutoconfigUrl("http://webproxy.wlb2.nam.nsroot.net:8080");
				DesiredCapabilities capabilities = DesiredCapabilities
						.internetExplorer();
				capabilities.setCapability(CapabilityType.PROXY, proxy);
				
				
				//DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				System.getProperties().put("webdriver.ie.driver", prop.getProperty("ie_driver_path"));
				driver = new InternetExplorerDriver(capabilities);
			}
			
			if (browser_string.equals("chrom")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				System.getProperties().put("webdriver.chrome.driver", prop.getProperty("chrom_driver_path"));
				driver = new ChromeDriver(options);
			}

			if (browser_string.equals("firefox")) {
//				FirefoxBinary binary = new FirefoxBinary();
//				File firefoxProfileFolder = new File(prop.getProperty("firefoxprofile"));
//				FirefoxProfile profile = new FirefoxProfile(firefoxProfileFolder);
//				profile.setAcceptUntrustedCertificates(true);

				driver = new FirefoxDriver();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void login(){
		
		System.out.println("login host=" + region.getUrl());
		
        driver.get(region.getUrl());

        //Maximize the Browser window
        driver.manage().window().maximize();

        if(region.getName().equals("local")){
        	driver.findElement(By.name("userName")).clear();
        	driver.findElement(By.name("userName")).sendKeys("xys");
        	driver.findElement(By.name("submit")).click();
        } 
        
 
	}
	
//	public static String region;
//	public static String host;
//	public static String password;
//	public static String username;
//    @Before
//    public void setUp() {
//    	System.out.println("test method setup");
////    	Properties p = new Properties();
////    	p.load(new FileReader(new File("config.properties")));
//    	
//    }
//
//    @After
//    public void tearDown() {
//    	System.out.println("test method teardown");
//    }
}
