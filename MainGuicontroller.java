package application;

import java.net.URL;
import java.util.ResourceBundle;
import org.openqa.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome;
import org.openqa.selenium.chrome.ChromeDriver;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainGuicontroller implements Initializable {

	@FXML
	private Button buttonTest;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonTest.setOnMouseClicked(event -> {

			System.setProperty("webdriver.chrome.driver", "D:\\D\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("http://www.google.com");
			// driver.quit();
		});

	}

}
