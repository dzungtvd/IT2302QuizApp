/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.services.questions;

import com.tvd.pojo.Category;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryQuestionDecorator extends QuestionDecorator{
    private Category cate;
    
    public CategoryQuestionDecorator(BaseQuestionServices decorator, Category c) {
        super(decorator);
        this.cate = c;
    }
    
    public CategoryQuestionDecorator(BaseQuestionServices decorator, int cateId) {
        super(decorator);
        this.cate = new Category(cateId);
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND category_id=?";
        params.add(this.cate.getId());
        
        return sql;
    }
    
}
