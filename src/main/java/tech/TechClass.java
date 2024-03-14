package tech;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TechClass {
    public WebDriver getWebDriver(String browserName){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        switch(browserName){
            case "chrome":
                System.out.println("Chrome browser selected.");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            default:
                System.out.println("Wrong browser name!");
                System.out.println("Print \"chrome\" or \"yandex\".");
                return null;
        }
    }
    public static String getRandomIndex(){
        return String.valueOf((int)(Math.random()*100000000));
    }

}
