package pages;

import com.mifmif.common.regex.Generex;
import common.*;
import org.openqa.selenium.*;
import org.testng.*;
import java.util.*;
import java.util.ResourceBundle;
import static common.Utility.*;
import static common.Utility.waitClickEl;
import static java.util.ResourceBundle.getBundle;

public class LoginPage extends TestBase {
    private final ResourceBundle resourceBundle;
    public static String url_prod;
    public static String url_qa;
    public static String createAccBtn;
    public static String createUrl;
    public static String emailField;
    public static String firstNameField;
    public static String lastNameField;
    public static String createPwdField;
    public static String createPwdConfirmField;
    public static String refferedField;
    public static String phoneNumField;
    public static String streetField;
    public static String cityField;
    public static String zipField;
    public static String stateDropDown;
    public static String stateCaDropDown;
    public static String investorNoBtn;
    public static String termsAgreeBtn;
    public static String termsSecAgreeBtn;
    public static String captchaIframe;
    public static String captchaBtn;
    public static String signUpBtn;
    public static String congratsAlertsTxt;
    public static String completeMyProfileBtn;

    public LoginPage() {
        resourceBundle = getBundle("pages.LoginPage");
        url_prod = this.getResourceBundle().getString("url_prod");
        url_qa = this.getResourceBundle().getString("url_qa");
        createAccBtn = this.getResourceBundle().getString("createAccBtn");
        createUrl = this.getResourceBundle().getString("createUrl");
        emailField = this.getResourceBundle().getString("emailField");
        firstNameField = this.getResourceBundle().getString("firstNameField");
        lastNameField = this.getResourceBundle().getString("lastNameField");
        createPwdField = this.getResourceBundle().getString("createPwdField");
        createPwdConfirmField = this.getResourceBundle().getString("createPwdConfirmField");
        refferedField = this.getResourceBundle().getString("refferedField");
        phoneNumField = this.getResourceBundle().getString("phoneNumField");
        streetField = this.getResourceBundle().getString("streetField");
        cityField = this.getResourceBundle().getString("cityField");
        zipField = this.getResourceBundle().getString("zipField");
        stateDropDown = this.getResourceBundle().getString("stateDropDown");
        stateCaDropDown = this.getResourceBundle().getString("stateCaDropDown");
        investorNoBtn = this.getResourceBundle().getString("investorNoBtn");
        termsAgreeBtn = this.getResourceBundle().getString("termsAgreeBtn");
        termsSecAgreeBtn = this.getResourceBundle().getString("termsSecAgreeBtn");
        captchaIframe = this.getResourceBundle().getString("captchaIframe");
        captchaBtn = this.getResourceBundle().getString("captchaBtn");
        signUpBtn = this.getResourceBundle().getString("signUpBtn");
        congratsAlertsTxt = this.getResourceBundle().getString("congratsAlertsTxt");
        completeMyProfileBtn = this.getResourceBundle().getString("completeMyProfileBtn");
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void verifyCreatingNewAccount()throws Exception {
        waitClickEl(createAccBtn, 10);
        String pwd = newUserCredentials(true).get(1);
        Assert.assertTrue(driver.getCurrentUrl().contains(createUrl));
        waitInsertEl(emailField,newUserCredentials(true).get(0),5);
        waitInsertEl(lastNameField,"Mask",5);
        waitInsertEl(firstNameField,"Elon",5);
        waitInsertPassword(pwd);
        waitInsertEl(refferedField,"Bill Gates Test",5);
        waitInsertEl(phoneNumField,"3109000000",5);
        waitInsertEl(streetField,"S New Success Ave",5);
        waitInsertEl(cityField,"Los Angeles",5);
        waitClickEl(stateDropDown,10);
        waitClickEl(stateCaDropDown,10);
        waitInsertEl(zipField,"90210",5);
        waitClickEl(investorNoBtn,10);
        waitClickEl(termsAgreeBtn,10);
        waitClickEl(termsSecAgreeBtn,10);
        clickCaptcha();
        waitClickEl(signUpBtn,10);
        Assert.assertTrue(driver.findElement(By.xpath(congratsAlertsTxt)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(completeMyProfileBtn)).isDisplayed());
    }

    public static void clickCaptcha(){
        WebElement iFrame = driver.findElement(By.xpath(captchaIframe));
        driver.switchTo().frame(iFrame);
        WebElement element = driver.findElement(By.xpath(captchaBtn));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        driver.switchTo().defaultContent();
    }

    public static List<String> newUserCredentials(Boolean createHTML) throws Exception{
        List<String> usercred = new LinkedList<>();
        usercred.add(new Generex("\\w{10}\\@test\\.com").random());
        usercred.add(new Generex("\\w{5}\\.!\\T0est").random());
        System.out.println("Email is: "+usercred.get(0));
        System.out.println("Password is: "+usercred.get(1));
        if (createHTML==true){
            Utility.writeUserCredentialsToHtml(usercred);}
        return usercred;
    }
}
