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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FixedExamStrategy extends ExamStrategy{

    @Override
    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        
        for(int i=0; i<Configs.RATES.length; i++) {
            BaseQuestionServices s = new LimitQuestionDecorator(new LevelQuestionDecorator(Configs.questionServices, i + 1), (int) (Configs.RATES[i]*Configs.NUM_OF_QUESTION));
            questions.addAll(s.list());
        }
        
        return questions;
    }
    
    
}
