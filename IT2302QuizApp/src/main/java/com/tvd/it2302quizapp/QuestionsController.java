/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.it2302quizapp;

import com.tvd.pojo.Category;
import com.tvd.pojo.Choice;
import com.tvd.pojo.Level;
import com.tvd.pojo.Question;
import com.tvd.services.CategoryServices;
import com.tvd.services.LevelServices;
import com.tvd.services.QuestionServices;
import com.tvd.utils.JdbcConnector;
import com.tvd.utils.MyAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionsController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vboxChoices;
    @FXML private TextField txtQuestions;
    @FXML private TableView<Question> tbQuestions;
    @FXML private TextField txtSearch;

    @FXML private ToggleGroup toggleChoice;
    
    private CategoryServices cateServices = new CategoryServices();
    private LevelServices levelServices = new LevelServices();
    private QuestionServices questionServices = new QuestionServices();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
            
            this.loadColumns();
            this.loadQuestions(questionServices.getQuestions());
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
        
        this.txtSearch.textProperty().addListener((e) -> {
            try {
                this.loadQuestions(questionServices.getQuestions(this.txtSearch.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
    }

    public void themLuaChonHandler (ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("main");
        
        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);
        TextField t = new TextField();
        
        h.getChildren().addAll(r, t);
        
        vboxChoices.getChildren().add(h);
    }
    
    public void taoCauHoiHandler (ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtQuestions.getText(), 
                this.cbCates.getSelectionModel().getSelectedItem(), 
                this.cbLevels.getSelectionModel().getSelectedItem());
        
            for (var x: this.vboxChoices.getChildren()) {
                HBox h = (HBox) x;
                
                Choice c = new Choice(((TextField)h.getChildren().get(1)).getText(), 
                        ((RadioButton)h.getChildren().get(0)).isSelected());
                
                b.addChoice(c);
            }
            questionServices.addQuestion(b.build());
            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
            
            //Bổ sung
            this.tbQuestions.getItems().add(0, b.build());
        }
        catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu không hợp lệ");
        }
    }
    
    private void loadColumns() {
        TableColumn colID = new TableColumn("Id");
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colID.setPrefWidth(100);
        
        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(350);
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((e) -> {
            TableCell cell = new TableCell();
            
            Button btn = new Button("Xóa");
            btn.setOnAction((event) -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMsg("Xóa câu hỏi thì tất cả các lựa chọn sẽ bị xóa. Bạn có muốn tiếp tục?", 
                        Alert.AlertType.CONFIRMATION);
                if(t.isPresent() && t.get().equals(ButtonType.OK)) {
                    //Xóa dữ liệu
                    try {
                        Question q = (Question)cell.getTableRow().getItem();
                        questionServices.deleteQuestions(q.getId());
                        
                        this.tbQuestions.getItems().remove(q);
                        MyAlert.getInstance().showMsg("Xóa dữ liệu thành công!");
                    }
                    catch (SQLException ex) {
                        MyAlert.getInstance().showMsg("Xóa dữ liệu thất bại!");
                    }
                }
            });
            
            cell.setGraphic(btn);
            
            return cell;
        });
        
        this.tbQuestions.getColumns().addAll(colID, colContent, colAction);
    }
    
    private void loadQuestions(List<Question> questions) {
        this.tbQuestions.setItems(FXCollections.observableList(questions));
    }
}
