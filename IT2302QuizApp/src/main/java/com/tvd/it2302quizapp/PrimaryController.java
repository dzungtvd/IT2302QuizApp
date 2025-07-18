package com.tvd.it2302quizapp;

import com.tvd.utils.MyAlert;
import com.tvd.utils.MyStage;
import com.tvd.utils.theme.Theme;
import com.tvd.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    @FXML private ComboBox<Theme> cbThemes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    
    public void ChonThemes (ActionEvent event) {
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
        /*
        switch (this.cbThemes.getSelectionModel().getSelectedItem()) {
            case DEFAULT:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
                break;
            case DARK:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("dark.css").toExternalForm());
                break;
            case WHITE:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("white.css").toExternalForm());
                break;
        }
        */
    }
    
    public void QuanLyCauHoi (ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("questions.fxml");
        //Scene scene = new Scene(new FXMLLoader(App.class.getResource("questions.fxml")).load());
        
        //Stage stage = new Stage();
        //stage.setScene(scene);
        //stage.setTitle("Quiz App");
        //stage.showAndWait();
    }
    
    public void LuyenTap (ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("practice.fxml");
    }
    
    public void Thi (ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("exam.fxml");
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
