/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.services.exam;

import com.tvd.pojo.Question;
import com.tvd.services.questions.BaseQuestionServices;
import com.tvd.services.questions.LevelQuestionDecorator;
import com.tvd.services.questions.LimitQuestionDecorator;
import com.tvd.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificExamStrategy extends ExamStrategy{
    private int num;

    public SpecificExamStrategy(int num) {
        this.num = num;
    }
    
    @Override
    public List<Question> getQuestions() throws SQLException {
        BaseQuestionServices s = new LimitQuestionDecorator(Configs.questionServices, this.num);
        return s.list();
    }
}
