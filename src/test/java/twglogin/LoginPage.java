package twglogin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pom.pagefactory;

import java.util.ArrayList;

public class LoginPage {

    WebDriver driver=null;
    pagefactory page=null;

    @BeforeTest
    public void startbrowser()
    {
        //driver=WebDriverManager.chromiumdriver().create();
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();


    }
    @Test
    public void loginpage() throws InterruptedException {
        driver.get("https://thosewoofguys.com/my-account/");
        driver.manage().window().maximize();
        page=new pagefactory(driver);

        ArrayList<String[]> logindetails=new ArrayList<>();
        logindetails.add(new String[]{"pritam.sanyal@yopmail.com","Sanyal88888@@"});
        logindetails.add(new String[]{"smith_doe@yopmail.com","Testing$$$123"});

        for (String [] credentials:logindetails)
        {
            String email= credentials[0];
            String pass= credentials[1];
            page.username(email);
            page.password(pass);
            page.loginbutton();
            page.verifydashboard();
            page.shop();
            page.productlistaddtocart();
            page.cartdetails();
            page.validatedcart();
            page.deleteitem();
            page.myaccount();
            page.logout();
        }

    }

    @AfterTest
    public void quit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }



}