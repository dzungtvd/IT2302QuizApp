/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tvd.utils.theme;

import com.tvd.it2302quizapp.App;

/**
 *
 * @author admin
 */
public class DarkFactory implements ThemeFactory {

    @Override
    public String getStylesheet() {
        return App.class.getResource("dark.css").toExternalForm();
    }

}
