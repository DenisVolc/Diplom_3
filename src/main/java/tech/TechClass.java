package tech;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TechClass {
    public WebDriver getWebDriver(String browserName){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        switch(browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            default:
                return null;
        }
    }
    public static String getRandomIndex(){
        return String.valueOf((int)(Math.random()*100000000));
    }

}
