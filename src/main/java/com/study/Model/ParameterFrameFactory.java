package com.study.Model;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParameterFrameFactory {
    public static JFrame createParameterFrame(String title, LayoutManager layoutManager, JPanel panel, int width, int height) {
        JFrame parameterFrame = new JFrame(title);
        parameterFrame.setLayout(layoutManager);

        parameterFrame.add(panel);

        parameterFrame.setSize(width, height);
        parameterFrame.setVisible(true);
        parameterFrame.setLocationRelativeTo(null);

        return parameterFrame;
    }
}