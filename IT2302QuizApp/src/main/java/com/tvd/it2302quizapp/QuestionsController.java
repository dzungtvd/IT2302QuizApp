/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.it2302quizapp;

import com.tvd.pojo.Category;
import com.tvd.pojo.Level;
import com.tvd.services.CategoryServices;
import com.tvd.services.LevelServices;
import com.tvd.utils.JdbcConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionsController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vboxChoices;
    @FXML private TextField txtQuestions;

    private CategoryServices cateServices = new CategoryServices();
    
    private LevelServices levelServices = new LevelServices();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
                        this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
            //B1: Nạp driver
            //Class.forName("com.mysql.cj.jdbc.Driver");

            //B2: Thiết lập kết nối
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");

            //B3: Xử lý truy vấn
            /*
            Statement atm = conn.createStatement();
            ResultSet rs = atm.executeQuery("SELECT * FROM category");
            
            List<Category> cates = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                Category c = new Category(id, name);
                cates.add(c);
            }
             */
            
            
            //B4: Đóng kết nối
            //conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void themLuaChonHandler (ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("main");
        
        RadioButton r = new RadioButton();
        TextField t = new TextField();
        
        h.getChildren().addAll(r, t);
        
        vboxChoices.getChildren().add(h);
    }
    
    public void taoCauHoiHandler (ActionEvent event) {
        
    }
   
}
