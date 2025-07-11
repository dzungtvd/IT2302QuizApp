/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.services.questions;

import com.tvd.pojo.Level;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelQuestionDecorator extends QuestionDecorator{
    private Level level;

    public LevelQuestionDecorator(BaseQuestionServices decorator, Level lvl) {
        super(decorator);
        this.level = lvl;
    }

    public LevelQuestionDecorator(BaseQuestionServices decorator, int lvlID) {
        super(decorator);
        this.level = new Level(lvlID);
    }
    
    
    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND level_id=?";
        params.add(this.level.getId());
        
        return sql;
    }
    
}
