package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private int count = 0;
    private final Text text = new Text(Integer.toString(count));
    private final Button button = new Button("버튼");
    
    
    private void incrementCount() {
    	count++;
//        button.setOnMouseClicked(MouseEvent -> {
    		
        	if (count < 20) {
        		text.setText("망했어");        		
        	} else if (count < 40) {
        		text.setText("개망했어");
        	} else if (count < 60) {
        		text.setText("핵망했어");
        	} else {
        		text.setText("잣망");
        	}
        	
//        });
    }
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getChildren().add(text);
        root.getChildren().add(button);
        StackPane.setMargin(button, new Insets(0, 200, 0, 0));

        Scene scene = new Scene(root, 200, 200);

        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        incrementCount();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                    //button.setOnMouseClicked(MouseEvent -> {
                	text.setText("방해닷");        		
                    //});
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}