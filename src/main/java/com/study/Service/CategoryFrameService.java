package com.study.Service;

import com.study.DAO.DaoImpl.DaoCategoryImpl;
import com.study.DAO.DaoModels.DaoCategory;
import com.study.DAO.Exceptions.DaoException;
import com.study.Model.Category;
import com.study.Model.DataTableModel;
import com.study.Model.ParameterFrameFactory;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryFrameService extends JFrame {
    private DaoCategory daoCategory;
    private List<JButton> buttons;
    private JButton findAll;
    private JButton findByCod;
    private JButton update;
    private JButton delete;
    private JButton insert;
    private JFrame parameterFrame;

    public CategoryFrameService() {
        initializeUI();
    }

    private void initializeUI() {
        daoCategory = new DaoCategoryImpl();

        buttons = new ArrayList<>();
        buttons.add(findAll = new JButton("Find All"));
        buttons.add(findByCod = new JButton("Get One"));
        buttons.add(update = new JButton("Update"));
        buttons.add(delete = new JButton("Delete"));
        buttons.add(insert = new JButton("Insert"));

        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void findAll() throws DaoException {
        getContentPane().removeAll();
        List<Category> categoryList = daoCategory.findAll();
        displayTable(categoryList);
    }

    public void findByCod() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel codeLabel = new JLabel("Code:");
        JTextField codeTextField = new JTextField();
        JButton findButton = new JButton("Find");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(findButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Get One", new FlowLayout(), panel, 300, 150);

        findButton.addActionListener(e -> {
            getOne(codeTextField.getText());
            parameterFrame.dispose();
        });
    }

    public void update() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel codeLabel = new JLabel("Code:");
        JTextField codeTextField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField();
        JButton updateButton = new JButton("Update");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(updateButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Update", new FlowLayout(), panel, 300, 150);

        updateButton.addActionListener(e -> {
            try {
                daoCategory.update(codeTextField.getText(), new Category(codeTextField.getText(), nameTextField.getText()));
                getOne(codeTextField.getText());
                parameterFrame.dispose();
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void delete() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel codeLabel = new JLabel("Code: ");
        JTextField codeTextField = new JTextField();
        JButton deleteButton = new JButton("Delete");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(deleteButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Delete", new FlowLayout(), panel, 300, 150);

        deleteButton.addActionListener(e -> {
            try {
                daoCategory.delete(codeTextField.getText());
                findAll();
                parameterFrame.dispose();
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void insert() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel codeLabel = new JLabel("Code: ");
        JTextField codeTextField = new JTextField();
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField();
        JButton insertButton = new JButton("Insert");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(insertButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        insertButton.addActionListener(e -> {
            try {
                String cod = codeTextField.getText();
                String name = nameTextField.getText();
                daoCategory.insert(new Category(cod, name));
                getOne(codeTextField.getText());
                parameterFrame.dispose();
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void getOne(String cod) {
        try {
            Category foundCategory = daoCategory.findByCod(cod);
            if (foundCategory != null) {
                List<Category> categoryList = new ArrayList<>();
                categoryList.add(foundCategory);
                displayTable(categoryList);
            } else {
                JOptionPane.showMessageDialog(parameterFrame, "Category not found!");
            }
        } catch (DaoException ex) {
            throw new RuntimeException();
        }
    }

    private void displayTable(List<Category> categoryList) {
        getContentPane().removeAll();

        List<String> columnNames = new ArrayList<>();
        columnNames.add("Cod");
        columnNames.add("Name");

        DataTableModel dataTableModel = new DataTableModel(categoryList, columnNames);
        JTable table = new JTable(dataTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }
}