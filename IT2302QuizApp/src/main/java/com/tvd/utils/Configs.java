/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.utils;

import com.tvd.services.CategoryServices;
import com.tvd.services.LevelServices;
import com.tvd.services.questions.QuestionServices;
import com.tvd.services.UpdateQuestionServices;
import com.tvd.services.questions.BaseQuestionServices;

/**
 *
 * @author admin
 */
public class Configs {

    public static BaseQuestionServices questionServices = new QuestionServices();
    public static final CategoryServices cateServices = new CategoryServices();
    public static final LevelServices levelServices = new LevelServices();
    public static final UpdateQuestionServices uQServices = new UpdateQuestionServices();

        
}
