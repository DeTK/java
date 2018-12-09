package application;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
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
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MainGUIController  implements Initializable {


	final static ChromeOptions options = new ChromeOptions().addArguments("headless");
	final static WebDriver driver = new ChromeDriver(options);

	@FXML
	private Button buttonTest;
	@FXML
	private Button buttons;
	
	StringBuilder sb = new StringBuilder();
	static Document doc;
	static Elements s;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\D\\chromedriver.exe");

		buttonTest.setOnMouseClicked(event -> {
			
			
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(clipboard);

			if(contents != null)
			{
				try {
					sb.append((String)(contents.getTransferData(DataFlavor.stringFlavor)));
					if (sb.length() > 500) {
						System.out.println("500자 넘어감");
						sb.setLength(0);
						sb.append(sb.substring(0, 500));        		
					}
				} catch (Exception e) {}
			}        	

			driver.get("https://translate.google.co.kr/?hl=ko#view=home&op=translate&sl=auto&tl=ko&text=" + sb.toString());
			sb.setLength(0);
			while (true) {
				doc = Jsoup.parse(driver.getPageSource());
				s = doc.select("body > div.frame > div.page.tlid-homepage.homepage.translate-text > div.homepage-content-wrap > div.tlid-source-target.main-header > div.source-target-row > div.tlid-results-container.results-container > div.tlid-result.result-dict-wrapper > div.result.tlid-copy-target > div.text-wrap.tlid-copy-target > div > span.tlid-translation.translation > span");

				if (s.text().length() != 0) {
					StringSelection ss = new StringSelection(s.text());
					clipboard.setContents(ss, null);
					break;
				}
			}
		});
		
		
		buttons.setOnMouseClicked(event -> {
			((Node)event.getSource()).getScene().getWindow().hide();
			driver.quit();
			System.exit(0);
		});
	}

}