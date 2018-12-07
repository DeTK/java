package application;
import java.net.URL;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
 
public class MainGUIController  implements Initializable {
    
	
	final static ChromeOptions options = new ChromeOptions();
	// options.addArguments("headless");
	final static WebDriver driver = new ChromeDriver();
	
	@FXML
    private Button buttonTest;
	@FXML
	private Button buttons;
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonTest.setOnMouseClicked(event -> {
        	System.setProperty("webdriver.chrome.driver", "D:\\D\\chromedriver.exe");
        	
        	driver.get("https://translate.google.co.kr/?hl=ko&tab=wT");
        	
            
        });
        buttons.setOnMouseClicked(event -> {
        	
        	Document doc = Jsoup.parse(driver.getPageSource());
        	Elements s = doc.select("body > div.frame > div.page.tlid-homepage.homepage.translate-text > div.homepage-content-wrap > div.tlid-source-target.main-header > div.source-target-row > div.tlid-results-container.results-container > div.tlid-result.result-dict-wrapper > div.result.tlid-copy-target > div.text-wrap.tlid-copy-target > div > span.tlid-translation.translation > span");
        	System.out.println(s.text());
        });
    }
 
}