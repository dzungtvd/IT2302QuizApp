/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.services.questions;

import com.tvd.pojo.Choice;
import com.tvd.pojo.Question;
import com.tvd.services.BaseServices;
import com.tvd.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseQuestionServices extends BaseServices<Question>{
    public abstract String getSQL(List<Object> params);
    
    @Override
    public PreparedStatement getStatement(Connection conn) throws SQLException {
        List<Object> params = new ArrayList<>();
        PreparedStatement stm = conn.prepareCall(this.getSQL(params));
        for (int i = 0; i < params.size(); i++)
            stm.setObject(i+1, params.get(i));
        return stm;
    };

    @Override
    public List<Question> getResults(ResultSet rs) throws SQLException {
        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id,content).build();
            
            questions.add(q);
        }
        return questions;
    };
//    public List<Question> list() throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//        
//        //****
//        List<Object> params = new ArrayList<>();
//        PreparedStatement stm = conn.prepareCall(this.getSQL(params));
//        for (int i = 0; i < params.size(); i++)
//            stm.setObject(i+1, params.get(i));
//        
//        ResultSet rs = stm.executeQuery();
//        
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//            Question q = new Question.Builder(id,content).build();
//            
//            questions.add(q);
//        }
//        return questions;
//    }
    
    
    public List<Choice> getChoicesByQuestionId(int questionID) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, questionID);
        ResultSet rs = stm.executeQuery();
        
        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            boolean correct = rs.getBoolean("is_correct");
            Choice c = new Choice(id, content, correct);
            
            choices.add(c);
        }
        return choices;
    }
}
