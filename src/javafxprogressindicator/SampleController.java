/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxprogressindicator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

/**
 *
 * @author nagashayan
 */
public class SampleController implements Initializable {
    
    @FXML 
    private Button start,stop;
    @FXML
    private Label label;
    @FXML
    private ProgressIndicator progress;
    Task worker;
    @FXML
    private void stopAction()
    {
        worker.cancel(true);
         progress.progressProperty().unbind();
         progress.setProgress(0);
        start.setDisable(false);
    }
    @FXML
    private void handleButtonAction() {
        //System.out.println("You clicked me!");
        stop.setDisable(false);
        start.setDisable(true);
        label.setText("Now your program is progressing!!");
        progress.progressProperty().unbind(); 
        progress.setProgress(0);
      
        worker = createWorker();
        progress.progressProperty().bind(worker.progressProperty());
               
                worker.messageProperty().addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        System.out.println(newValue);
                    }
                });
                 new Thread(worker).start();
                
    }
    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(2000);
                    updateMessage("2000 milliseconds");
                    updateProgress(i + 1, 10);
                }
                 worker.cancel(true);
                return true;
            }
        };
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
