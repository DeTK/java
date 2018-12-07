package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainGuicontroller implements Initializable {

	@FXML
	private Button buttonTest;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonTest.setOnMouseClicked(event -> {
			System.out.println("test");
		});

	}

}
