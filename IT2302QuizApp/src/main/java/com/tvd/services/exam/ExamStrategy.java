/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.services.exam;

import com.tvd.pojo.Exam;
import com.tvd.pojo.Question;
import com.tvd.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class ExamStrategy {
    public abstract List<Question> getQuestions() throws SQLException;
    
    public void saveExam(List<Question> questions) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        
        Exam e = new Exam(questions);
        
        String sql = "INSERT INTO exam(title, created_date) VALUES(?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, e.getTitle());
        stm.setString(2, e.getCreatedDate().toString());
        
        if (stm.executeUpdate() > 0) {
            int exId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) {
                exId = r.getInt(1);
            }
            sql = "INSERT INTO exam_question(exam_id, question_id) VALUES(?,?)";
            stm = conn.prepareCall(sql);
            for (var q:questions) {
                stm.setInt(1, exId);
                stm.setInt(2, q.getId());
                stm.executeUpdate();
            }
            conn.commit();
        } else {
            conn.rollback();
        }
    }
}
