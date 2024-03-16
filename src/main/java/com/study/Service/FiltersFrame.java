package com.study.Service;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter
public class FiltersFrame {
    private JFrame frame;
    private JPanel contentPanel;
    private JPanel tablePanel;
    private JButton button;
    private JComboBox<String> tableComboBox;

    private int choice;

    public FiltersFrame() {
        initializeComponents();
        frame.setLayout(new BorderLayout());
    }

    public void initializeComponents() {
        frame = new JFrame();
        contentPanel = new JPanel();
        tablePanel = new JPanel();
        button = new JButton("Go");
        contentPanel.setLayout(new FlowLayout());
        tablePanel.setLayout(new BorderLayout());

        frame.add(contentPanel, BorderLayout.PAGE_START);
        frame.add(tablePanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setComponents() {
        frame.add(contentPanel, BorderLayout.PAGE_START);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void toy() {
        tablePanel.removeAll();
        contentPanel.removeAll();
        String[] components = {"exclude", "expensiveCheap", "avgPrice", "insertMoldova", "filters", "popular"};
        tableComboBox = new JComboBox<>(components);
        contentPanel.add(tableComboBox);
        contentPanel.add(button);
        setComponents();
        choice = 1;
    }

    public void doll() {
        tablePanel.removeAll();
        contentPanel.removeAll();
        String[] components = {"ascending"};
        tableComboBox = new JComboBox<>(components);
        contentPanel.add(tableComboBox);
        contentPanel.add(button);
        setComponents();
        choice = 2;
    }

    public void sale() {
        tablePanel.removeAll();
        contentPanel.removeAll();
        String[] components = {"sales"};
        tableComboBox = new JComboBox<>(components);
        contentPanel.add(tableComboBox);
        contentPanel.add(button);
        setComponents();
        choice = 3;
    }

    public void openFrame(JFrame frame) {
        tablePanel.removeAll();
        tablePanel.add(frame.getContentPane(), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public void addListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }
}
