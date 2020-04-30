package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StepDefinitions {

    static ChromeDriver driver;

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("The home page is opened")
    public void theHomePageIsOpened() {
        driver.get("http://automationpractice.com/");
    }

    @And("The Sign In Link clicked")
    public void theSignInLinkClicked() {
        driver.findElement(By.className("login")).click();
    }

    @Given("The Sign In button is clicked")
    public void theSignInButtonIsClicked() {
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Then("An email address required error message is shown")
    public void anEmailAddressRequiredErrorMessageIsShown() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals("An email address required.", elements.get(0).getText());
    }

    @Given("The {string} is filled with {string}")
    public void theFieldIsFilledWithValue(String field, String value) {
        driver.findElement(By.id(field)).sendKeys(value);
    }

    @Then("An {string} error is shown")
    public void anMsgErrorIsShown(String msg) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals(msg, elements.get(0).getText());
    }

    @Given("The credentials are filled correctly")
    public void theCredentialsAreFilledCorrectly() {
        driver.findElement(By.id("email")).sendKeys("nealder@freemail.hu");
        driver.findElement(By.id("passwd")).sendKeys("qwe123");
    }

    @Then("Login is successful")
    public void loginIsSuccessful() {
        Assert.assertEquals("MY ACCOUNT", driver.findElement(By.className("page-heading")).getText());
    }

    @And("Sign Out button is clicked")
    public void signOutButtonIsClicked() {
        driver.findElement(By.className("logout")).click();
    }

    @Then("Login Page is shown")
    public void loginPageIsShown() {
        Assert.assertEquals("AUTHENTICATION", driver.findElement(By.className("page-heading")).getText());
    }

    @And("The {string} button is clicked")
    public void theBtnButtonIsClicked(String btn) {
        driver.findElement(By.xpath(String.format("//a[@title='%s']", btn))).click();
    }

    @Then("The {string} is shown")
    public void theContentIsShown(String content) {
        String pageHead = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText();


        Assert.assertEquals(content, pageHead);

        signOutButtonIsClicked();
        loginPageIsShown();
    }

    @And("The other {string} is shown")
    public void theOtherContentIsShown(String content) {
        String pageHead = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/h1")).getText();

        Assert.assertEquals(content, pageHead);

        signOutButtonIsClicked();
        loginPageIsShown();
    }

    @Given("An empty search string")
    public void anEmptySearchString() {
        driver.findElement(By.id("search_query_top")).sendKeys(Keys.ENTER);
    }

    @Then("Zero result page is shown")
    public void zeroResultPageIsShown() {
        Assert.assertEquals("Please enter a search keyword", driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText());
    }

    @Given("Actual search term entered")
    public void actualSearchTermEntered() {
        driver.findElement(By.id("search_query_top")).sendKeys("Faded" + Keys.ENTER);
    }

    @And("The search result is found")
    public void theSearchResultIsFound() {
        //String element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2]")).getText();
        String element = driver.findElement(By.className("heading-counter")).getText();

        Assert.assertNotEquals("0 result has been found.", element);
    }

    @And("The search result is clicked")
    public void theSearchResultIsClicked() {
        driver.findElement(By.xpath("//img[@title=\"Faded Short Sleeve T-shirts\"]")).click();
    }

    @Then("The item is shown")
    public void theItemIsShown() {
        String content = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1")).getText();

        Assert.assertEquals("Faded Short Sleeve T-shirts", content);
    }

    @Then("Add item to wish")
    public void addItemToWish() {
        driver.findElement(By.xpath("//*[@id=\"wishlist_button\"]")).click();
    }

    @Then("Check item in wish")
    public void checkItemInWish() {
        String quantity = driver.findElement(By.xpath("//*[@id=starts-with(., \"wishlist_\")]/td[2]")).getText();
        Assert.assertNotEquals(0, quantity);

        signOutButtonIsClicked();
    }

    @Then("Click account")
    public void clickAccount() {
        driver.findElement(By.xpath("//a[@title=\"View my customer account\"]")).click();
    }

    @Then("Click wishlist")
    public void clickWishlist() {
        driver.findElement(By.xpath("//a[@title=\"My wishlists\"]")).click();
    }

    @Then("Delete item from wish")
    public void deleteItemFromWish() {
        driver.findElement(By.xpath("//*[@id=starts-with(., \"wishlist_\")]/td[6]/a/i")).click();
    }

    @Given("Contact us is clicked")
    public void contactUsIsClicked() {
        driver.findElement(By.xpath("//a[@title=\"Contact Us\"]")).click();
    }

    @Then("Contact form appears")
    public void contactFormAppears() {
        //*[@id="center_column"]/h1
        Assert.assertEquals("CUSTOMER SERVICE - CONTACT US", driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText());
    }

    @Given("Home navigation button clicked")
    public void homeNavigationButtonClicked() {
        driver.findElement((By.xpath("//*[@id=\"columns\"]/div[1]/a"))).click();
    }

    @Given("Forgott password clicked")
    public void forgottPasswordClicked() {
        driver.findElement(By.xpath("//*[@id=\"login_form\"]/div/p[1]/a")).click();
        String header = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/h1")).getText();

        Assert.assertEquals("FORGOT YOUR PASSWORD?", header);
    }
}
