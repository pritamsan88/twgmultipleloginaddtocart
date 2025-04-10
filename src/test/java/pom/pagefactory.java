package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.*;


public class pagefactory {

   WebDriver driver=null;
    JavascriptExecutor js=null;
    @FindBy(id="username")
    WebElement usernamefield;

    @FindBy(id="password")
    WebElement passwordfield;

    @FindBy(xpath="//button[@name='login']")
    WebElement submitbutton;

    @FindBy(xpath="//h2[contains(text(),'Dashboard')]")
    WebElement verifydashboard;

    @FindBy(xpath="//a[contains(text(),'Log out')]")
    WebElement logout1;

    @FindBy(xpath="//a[(@href='javascript:void(0);')]")
    List<WebElement> listofcart;

    @FindBy(xpath="//a[(@href='javascript:void(0);')]")
    WebElement innerpagecart;
    @FindBy(xpath="//a[@href='https://thosewoofguys.com/shop/']")
    WebElement shoppage1;

    @FindBy(xpath="//a[@href='https://thosewoofguys.com/my-account/']")
    WebElement myaccount1;
    @FindBy (xpath="//a[@href='https://thosewoofguys.com/cart/']")
    WebElement cartpage;
    @FindBy(css="a.wc-block-components-product-name")
    List<WebElement> cartindetails;
    @FindBy(css = " td.wc-block-cart-item__total > div > span > span")
    List<WebElement> price;
    @FindBy(css = " button.wc-block-cart-item__remove-link")
    List<WebElement> cartitemdelete;

    @FindBy(css="a.btn_card.dtls_btn")
    List<WebElement> detailsinlist;

    @FindBy (id="cart-count")
    WebElement cartcount1;

    @FindBy(css=" div.product_detl_qty > select")
    WebElement size;

   public pagefactory(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);


    }
    public void username(String username)
    {
        usernamefield.sendKeys(username);
    }
    public void password(String password)
    {
        passwordfield.sendKeys(password);
    }
    public void loginbutton()
    {
        js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",submitbutton);
        js.executeScript("arguments[0].click();",submitbutton);
        //submitbutton.click();
    }
    public void verifydashboard()
    {
        String text=verifydashboard.getText().trim();
        if(text.contains("Dashboard"))
        {
            System.out.println("Welcome dashboard ");
        }
        else {
            System.out.println("You are not enter dashboard");
        }


    }
    public void logout()
    {
        logout1.click();
    }
     public void shop()
     {
         shoppage1.click();

     }
    public void myaccount() throws InterruptedException {
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView();", myaccount1);
        js.executeScript("arguments[0].click();",myaccount1);

    }

    public void cartdetails() throws InterruptedException {
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView();", cartpage);
        js.executeScript("arguments[0].click();",cartpage);
        Thread.sleep(2000);
       /* for (WebElement productdetails:cartindetails)
        {
            String details=productdetails.getText();
            //System.out.println("Added product in the cart"+details);
            for(WebElement cartprice:price){
                String pricedetails1=cartprice.getText();
                System.out.println("details of cart "+details.concat(pricedetails1));
            }


        }*/
        if(cartindetails.size()==price.size())
        {
            for(int i=0;i<cartindetails.size();i++)
            {
                String details = cartindetails.get(i).getText();
                String pricedetails1 = price.get(i).getText();

                System.out.println("Product:- " + details + " | Price:- " + pricedetails1);
            }

        } else {
            System.out.println("Mismatch in product and price counts! Check the locators.");
        }

   }

   public  void deleteitem() throws InterruptedException {
          for (WebElement delete : cartitemdelete) {
             // delete.click();
              js.executeScript("arguments[0].scrollIntoView();", delete);
              Thread.sleep(2000);
              js.executeScript("arguments[0].click();", delete);
          }

      }
      public void validatedcart()
      {
          String cartdigit=cartcount1.getText();
          System.out.println(" number of product added in thr cart :"+cartdigit);
          int  carticon=Integer.parseInt(cartdigit);
          if(carticon>0)
          {
              System.out.println(" : product added in thr cart :");

          }
          else
          {
              System.out.println(" : not added in the cart :");
          }


      }

      public void productlistaddtocart() throws InterruptedException {
        /*for(WebElement cart:listofcart)
        {
            js=(JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", cart);
            Thread.sleep(2000);
            js.executeScript("arguments[0].click();", cart);
        }*/

        for (int i=5;i<=8;i++)
        {
            WebElement item=listofcart.get(i);
            Thread.sleep(2000);
            js.executeScript("arguments[0].scrollIntoView();", item);
            Thread.sleep(2000);
            js.executeScript("arguments[0].click();", item);
        }
    }
         public void productineeraddtocart() throws InterruptedException {
        /*for(WebElement cart:listofcart)
        {
            js=(JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", cart);
            Thread.sleep(2000);
            js.executeScript("arguments[0].click();", cart);
        }*/

        for (int i=28;i<detailsinlist.size();i++)
        {
            WebElement item=detailsinlist.get(i);
            Thread.sleep(2000);
            js.executeScript("arguments[0].scrollIntoView();", item);
            Thread.sleep(2000);
            js.executeScript("arguments[0].click();", item);
            Thread.sleep(2000);

            Select select= new Select(size);
            List<WebElement> listofqty=select.getOptions();
            int sizecount=listofqty.size();
            if(sizecount>1)
            {
                int randomsizeindex= new Random().nextInt(listofqty.size());
                select.selectByIndex(randomsizeindex);
            }else {
                // No options in the dropdown
                System.out.println("Dropdown has no options to select.");
            }
            System.out.println("Total qty of the list :" + sizecount);

            js.executeScript("arguments[0].click();", innerpagecart);
            Thread.sleep(2000);
            driver.navigate().back();

        }
    }
}
