package com.study.Service;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter
public class MainOperationsFrame {
    private JFrame frame;
    private JPanel contentPanel;
    private JPanel tablePanel;
    private JButton button;
    private JComboBox<String> tableComboBox;

    private int choice;

    public MainOperationsFrame() {
        initializeComponents();
        frame.setLayout(new BorderLayout());
    }

    public void initializeComponents() {
        String[] components = {"Toy", "Category", "Doll", "Sale"};
        frame = new JFrame();
        contentPanel = new JPanel();
        tablePanel = new JPanel();
        button = new JButton();
        tableComboBox = new JComboBox<>(components);
        contentPanel.add(tableComboBox);
        contentPanel.add(button);
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

    public void getAll() {
        tablePanel.removeAll();
        button.setText("Get All");
        setComponents();
        choice = 1;
    }

    public void getOne() {
        tablePanel.removeAll();
        button.setText("Get One");
        setComponents();
        choice = 2;
    }

    public void insert() {
        tablePanel.removeAll();
        button.setText("Insert");
        setComponents();
        choice = 3;
    }

    public void update() {
        tablePanel.removeAll();
        button.setText("Update");
        setComponents();
        choice = 4;
    }

    public void delete() {
        tablePanel.removeAll();
        button.setText("Delete");
        setComponents();
        choice = 5;
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