package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private String loginInputXpath = "/html/body/div[1]/div/div[2]/div/div[2]/div/div[2]/div/div/form/div[1]/label/input";
    private String passwordInputXpath = "//*[@name=\"passwd\"]";
    private String submitButtonXpath = "/html/body/div[1]/div/div[2]/div/div[2]/div/div[2]/div/div/form/div[4]/button[1]/span/span";

    public void enterLogin(String username) {
        writeText(By.xpath(loginInputXpath), username);
    }

    public void enterPassword(String password) {
        writeText(By.xpath(passwordInputXpath), password);

    }

    public void submit() {
        click(By.xpath(submitButtonXpath));
    }


}
