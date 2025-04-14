package twglogin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pom.pagefactory;

import java.util.HashMap;
import java.util.Map;


public class IneerPageAddtoCart {
    WebDriver driver = null;
    pagefactory page = null;

    @BeforeTest
    public void browserstartup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


    }

    @Test

    public void loginproductindetailsaddtocart() throws InterruptedException {
        driver.get("https://thosewoofguys.com/my-account/");
        driver.manage().window().maximize();
        page = new pagefactory(driver);
        HashMap<String, String> map = new HashMap<>();
        map.put("pritam.sanyal@yopmail.com", "Sanyal88888@@");
        //map.put("smith_doe@yopmail.com","Testing$$$123");
        for (Map.Entry<String, String> logincred : map.entrySet()) {
            String usernamefield = logincred.getKey();
            String passwordfield = logincred.getValue();
            page.username(usernamefield);
            page.password(passwordfield);
            page.loginbutton();
            page.verifydashboard();
            page.shop();
            page.productineeraddtocart();
            page.cartdetails();
            page.validatedcart();
            page.deleteitem();
            page.myaccount();
            page.logout();


        }
        // ArrayList<String[]> logincred=new ArrayList<>();
        // logincred.add(new String[] {"pritam.sanyal@yopmail.com","Sanyal88888@@"});
        // logincred.add(new String[] {"smith_doe@yopmail.com","Sanyal88888@@"});
    }

    @AfterTest
    public void quit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
