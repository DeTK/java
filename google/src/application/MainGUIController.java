package application;
import java.awt.Toolkit;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;

import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

//import com.sun.jna.Pointer;
//import com.sun.jna.platform.win32.WinDef.HWND;
//import com.sun.jna.platform.win32.WinDef.RECT;
//import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;


public class MainGUIController implements Initializable {

	String text = "";

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Label label1;

	User32 user32;
	ChromeOptions options;
	WebDriver driver;
	StringBuilder sb;

	private Task<Void> task;
	private boolean stop;

	public void taskStart() {
//		Thread t = new Thread(() -> {
//            for (int i = 0; i <= 5 ; i++) {
//                final String text = "Count: "+i ;
//                Platform.runLater(() -> label1.setText(text));
//                try {
//                    Thread.sleep(250);
//                } catch (InterruptedException exc) {
//                    exc.printStackTrace();
//                }
//            }
//        });
//        t.start();
	}

	public void msg() {
		
		//this.t.interrupt();
	}




	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("처음 한번만 실행됨");

		user32 = User32.INSTANCE;
		System.setProperty("webdriver.chrome.driver", "D:\\D\\chromedriver.exe");
		options = new ChromeOptions();
		//		options.addArguments("headless");
		driver = new ChromeDriver(options);
		sb = new StringBuilder();
		button1.setOnMouseClicked(event -> button1click(event));



		button2.setOnMouseClicked(event -> button2click(event));

	}

	public void button1click(MouseEvent event) {
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(clipboard);

		if(contents != null)
		{
			try {
				String clip = (String)contents.getTransferData(DataFlavor.stringFlavor);
				sb.setLength(0);
				if (clip.length() > 500) {
					this.text = "500자 넘음...";
					taskStart();
					stop = false;
					sb.append(clip.substring(0, 500));        		
				} else {
					this.text = "안넘음...";
					taskStart();
					stop = false;
					sb.append(clip);
				}
			} catch (Exception e) {}
		}
		this.text = "드라이버실행중...";
		
		//taskStart();
		stop = false;
		driver.get("https://translate.google.co.kr/?hl=ko#view=home&op=translate&sl=en&tl=ko&text=" + sb.toString());

		while (true) {
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements s = doc.select("body > div.frame > div.page.tlid-homepage.homepage.translate-text > div.homepage-content-wrap > div.tlid-source-target.main-header > div.source-target-row > div.tlid-results-container.results-container > div.tlid-result.result-dict-wrapper > div.result.tlid-copy-target > div.text-wrap.tlid-copy-target > div > span.tlid-translation.translation > span");

			if (s.text().length() != 0) {
				StringSelection ss = new StringSelection(s.text());
				clipboard.setContents(ss, null);
				this.text = "복사됨...";					
				taskStart();
				stop = false;
				break;
			}
		}

	}
	public void button2click(MouseEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		driver.quit();
		System.exit(0);
	}

}