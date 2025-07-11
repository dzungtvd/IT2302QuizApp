/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tvd.it2302quizapp;

import com.tvd.pojo.Category;
import com.tvd.pojo.Level;
import com.tvd.pojo.Question;
import com.tvd.services.FlyweightFactory;
import com.tvd.services.questions.BaseQuestionServices;
import com.tvd.services.questions.CategoryQuestionDecorator;
import com.tvd.services.questions.LevelQuestionDecorator;
import com.tvd.services.questions.LimitQuestionDecorator;
import com.tvd.services.questions.QuestionServices;
import com.tvd.utils.Configs;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {
    @FXML private TextField txtNum;
    @FXML private VBox vboxChoices;
    @FXML private Text txtContent;
    @FXML private ComboBox<Category> cbCategorys;
    @FXML private ComboBox<Level> cbLevels;
    private static final QuestionServices questionServices = new QuestionServices();
    private List<Question> questions;
    private int currentQuestions = 0;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCategorys.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateServices, "categories")));
            this.cbLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "levels")));
 
        } catch (SQLException ex) {

        }
    }

    public void handleStart(ActionEvent event) {
        try {
            BaseQuestionServices s = Configs.questionServices;
            
            Category c = this.cbCategorys.getSelectionModel().getSelectedItem();
            if (c != null)
                s = new CategoryQuestionDecorator(s, c);
            
            Level l = this.cbLevels.getSelectionModel().getSelectedItem();
            if (l != null)
                s = new LevelQuestionDecorator(s, l);
            
            s = new LimitQuestionDecorator(s, Integer.parseInt(this.txtNum.getText()));
            
            this.questions = s.list();
            loadQuestions();
        } catch (SQLException ex) {

        }
    }
    
    public void handleNext(ActionEvent event) {
        if (this.currentQuestions < this.questions.size() - 1) {
            this.currentQuestions++;
            this.loadQuestions();
        }
    }
    
    public void handleCheck(ActionEvent event) {
        Question q = this.questions.get(this.currentQuestions);
        for(int i=0; i<q.getChoices().size(); i++) {
            if(q.getChoices().get(i).isIs_correct()) {
                RadioButton rd = (RadioButton)this.vboxChoices.getChildren().get(i);
                this.txtContent.getStyleClass().clear();
                if (rd.isSelected()) {
                    this.txtContent.setText("Correct! :)");
                }
                else {
                    this.txtContent.setText("Try again! :(");
                }
                break;
            }
        }
    }
    
    private void loadQuestions() {
        Question q = this.questions.get(this.currentQuestions);
        Text txt = new Text(q.getContent());
        this.vboxChoices.getChildren().clear();
        this.vboxChoices.getChildren().add(txt);
        
        ToggleGroup t = new ToggleGroup();
        for (var c: q.getChoices()) {
            RadioButton r = new RadioButton(c.getContent());
            r.setToggleGroup(t);
            this.vboxChoices.getChildren().add(r);
        }
    }
}
