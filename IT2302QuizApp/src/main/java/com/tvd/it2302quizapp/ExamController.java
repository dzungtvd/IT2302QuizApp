/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tvd.it2302quizapp;

import com.tvd.pojo.Choice;
import com.tvd.pojo.Question;
import com.tvd.services.exam.ExamStrategy;
import com.tvd.services.exam.ExamTypes;
import com.tvd.services.exam.FixedExamStrategy;
import com.tvd.services.exam.SpecificExamStrategy;
import com.tvd.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
public class ExamController implements Initializable {
    @FXML private ComboBox<ExamTypes> cbExamTypes;
    @FXML private TextField txtNum;
    @FXML private ListView<Question> lvQuestions;
    
    private ExamStrategy s;
    private List<Question> questions;
    private Map<Integer, Choice> results = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        this.txtNum.setVisible(false);
        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
                this.txtNum.setVisible(true);
            else
                this.txtNum.setVisible(false);
        });
    }    
    
    public void handleStart (ActionEvent event) throws SQLException {
        if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
            s = new SpecificExamStrategy(Integer.parseInt(this.txtNum.getText()));
        else
            s = new FixedExamStrategy();
        
        questions = s.getQuestions();
        this.lvQuestions.setItems(FXCollections.observableList(this.questions));
        
        this.lvQuestions.setCellFactory(params -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty);
                
                if (q == null || empty) 
                    this.setGraphic(null);
                else {
                    results.clear();
                    VBox v = new VBox(5);
                    v.setStyle("-fx-border-width:1; -fx-border-color:gray; -fx-padding:3;");
                    
                    Text t = new Text(q.getContent());
                    v.getChildren().add(t);
                    ToggleGroup tg = new ToggleGroup();
                    for (var c: q.getChoices()) {
                        RadioButton rb = new RadioButton(c.getContent());
                        rb.setToggleGroup(tg);
                        
                        //update UI
                        if(results.get(q.getId()) == c)
                            rb.setSelected(true);
                        
                        rb.setOnAction(e -> {
                            if (rb.isSelected())
                                results.put(q.getId(),c);
                        });
                        
                        v.getChildren().add(rb);
                    }
                    
                    this.setGraphic(v);
                }
            }
        });
    }
    
    public void handleFinish (ActionEvent event) {
        if (!results.isEmpty()) {
            int count = 0;
            for (var c: results.values())
                if (c.isIs_correct() == true)
                    count++;
            
            MyAlert.getInstance().showMsg(String.format("Bạn làm đúng %d/%d câu!", count, questions.size()), Alert.AlertType.INFORMATION);
        }
        else 
            MyAlert.getInstance().showMsg("Hãy làm bài ít nhất 1 câu!", Alert.AlertType.WARNING);
    }
    
    public void handleSave (ActionEvent event) {
        if (questions == null || questions.isEmpty())
            MyAlert.getInstance().showMsg("Không có câu hỏi để lưu!", Alert.AlertType.WARNING);
        else {
            try {
                s.saveExam(questions);
                MyAlert.getInstance().showMsg("Lưu đề thi thành công!", Alert.AlertType.INFORMATION);
            } catch (SQLException ex) {
                MyAlert.getInstance().showMsg("Hệ thống bị lỗi: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
}
