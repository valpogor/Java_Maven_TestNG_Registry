package common;

import org.apache.poi.hssf.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.LoginPage;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utility extends LoginPage {

    public static String getUrl(String env) {
        String url="";
        if(env.equalsIgnoreCase("prod")){
            url=url_prod;
        }
        else{url=url_qa;}
        return url;
    }

    public static void waitClickEl(String element, int time) {
        WebElement el = new WebDriverWait(driver, time)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
        el.click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    public static void waitInsertEl(String element, String insert, int time) {
        WebElement el = new WebDriverWait(driver, time)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
        el.sendKeys(insert);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    public static void waitInsertPassword(String password) throws Exception{
        waitInsertEl(createPwdField, password,5);
        Thread.sleep(3000);
        waitInsertEl(createPwdConfirmField, password,5);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void writeUserCredentialsToHtml(List<String> userCred) throws Exception {
        File path = new File(System.getProperty("user.dir"), "/Reports/");
        File textFile = new File(path, getDate()+"_UserCredentials.html");
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        out.write("<a > Email is: "+userCred.get(0)+"</a><br/>");
        out.write("<a > Password is: "+userCred.get(1)+"</a><br/>");
        out.close();
    }
}

