package com.tvd.it2302quizapp;

import com.tvd.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {
    public void QuanLyCauHoi (ActionEvent event) throws IOException {
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("questions.fxml")).load());
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quiz App");
        stage.showAndWait();
    }
    
    public void LuyenTap (ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }
    
    public void DangNhap (ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }
    
    public void DangKy (ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }
    
    public void Thoat (ActionEvent event) {
       
    }
}
