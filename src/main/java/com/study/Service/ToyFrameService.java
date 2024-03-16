package com.study.Service;

import com.study.DAO.DaoImpl.DaoCategoryImpl;
import com.study.DAO.DaoImpl.DaoToyImpl;
import com.study.DAO.DaoModels.DaoCategory;
import com.study.DAO.DaoModels.DaoToy;
import com.study.DAO.Exceptions.DaoException;
import com.study.Model.DataTableModel;
import com.study.Model.ParameterFrameFactory;
import com.study.Model.Toy;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ToyFrameService extends JFrame {
    private DaoToy daoToy;
    private DaoCategory daoCategory;
    private JFrame parameterFrame;

    public ToyFrameService() {
        initializeUI();
    }

    private void initializeUI() {
        daoToy = new DaoToyImpl();
        daoCategory = new DaoCategoryImpl();

        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void findAll() throws DaoException {
        getContentPane().removeAll();
        List<Toy> toyList = daoToy.findAll();
        displayTable(toyList);
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
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceTextField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();
        JLabel countryLabel = new JLabel("Country:");
        JTextField countryTextField = new JTextField();
        JLabel minimAgeLabel = new JLabel("Minim Age:");
        JTextField minimAgeTextField = new JTextField();
        JButton updateButton = new JButton("Update");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(priceLabel);
        panel.add(priceTextField);
        panel.add(quantityLabel);
        panel.add(quantityTextField);
        panel.add(countryLabel);
        panel.add(countryTextField);
        panel.add(minimAgeLabel);
        panel.add(minimAgeTextField);
        panel.add(updateButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Update", new FlowLayout(), panel, 300, 150);

        updateButton.addActionListener(e -> {
            try {
                daoToy.update(codeTextField.getText(),
                        new Toy(codeTextField.getText(),
                                nameTextField.getText(),
                                Double.valueOf(priceTextField.getText()),
                                Integer.valueOf(quantityTextField.getText()),
                                countryTextField.getText(),
                                Integer.valueOf(minimAgeTextField.getText()),
                                daoToy.findByCod(codeTextField.getText()).getCategory()));
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
                daoToy.delete(codeTextField.getText());
                findAll();
                parameterFrame.dispose();
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void insert() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel codeLabel = new JLabel("Code:");
        JTextField codeTextField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceTextField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();
        JLabel countryLabel = new JLabel("Country:");
        JTextField countryTextField = new JTextField();
        JLabel minimAgeLabel = new JLabel("Minim Age:");
        JTextField minimAgeTextField = new JTextField();
        JLabel categoryLabel = new JLabel("Category: ");
        JTextField categoryTextField = new JTextField();
        JButton insertButton = new JButton("Insert");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(priceLabel);
        panel.add(priceTextField);
        panel.add(quantityLabel);
        panel.add(quantityTextField);
        panel.add(countryLabel);
        panel.add(countryTextField);
        panel.add(minimAgeLabel);
        panel.add(minimAgeTextField);
        panel.add(categoryLabel);
        panel.add(categoryTextField);
        panel.add(insertButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        insertButton.addActionListener(e -> {
            try {
                daoToy.insert(
                        new Toy(codeTextField.getText(),
                                nameTextField.getText(),
                                Double.valueOf(priceTextField.getText()),
                                Integer.valueOf(quantityTextField.getText()),
                                countryTextField.getText(),
                                Integer.valueOf(minimAgeTextField.getText()),
                                daoCategory.findByCod(categoryTextField.getText()))
                );
                getOne(codeTextField.getText());
                parameterFrame.dispose();
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void getOne(String cod) {
        try {
            Toy toy = daoToy.findByCod(cod);
            if (toy != null) {
                List<Toy> toyList = new ArrayList<>();
                toyList.add(toy);
                displayTable(toyList);
            } else {
                JOptionPane.showMessageDialog(parameterFrame, "Toy not found!");
            }
        } catch (DaoException ex) {
            throw new RuntimeException();
        }
    }

    private void displayTable(List<Toy> toyList) {
        getContentPane().removeAll();

        List<String> columnNames = new ArrayList<>();
        columnNames.add("Cod");
        columnNames.add("Name");
        columnNames.add("Price");
        columnNames.add("Quantity");
        columnNames.add("Country");
        columnNames.add("MinimAge");
        columnNames.add("Category");

        DataTableModel dataTableModel = new DataTableModel(toyList, columnNames);
        JTable table = new JTable(dataTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }

    public void findExclude() {
        getContentPane().removeAll();
        daoToy.exclude();
        List<Toy> toyList = daoToy.findExclude();
        displayTable(toyList);
    }

    public void expensiveCheap() {
        getContentPane().removeAll();
        List<Toy> toyList = daoToy.expensiveCheap();
        displayTable(toyList);
    }

    public void avgPrice() {
        getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel countryLabel = new JLabel("Country:");
        JTextField countryTextField = new JTextField();
        JButton button = new JButton("Ok");

        panel.add(countryLabel);
        panel.add(countryTextField);
        panel.add(button);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        button.addActionListener(e -> {
            String country = countryTextField.getText();
            parameterFrame.dispose();
            JPanel resultPanel = new JPanel(new GridLayout(1, 2));
            JButton close = new JButton("Close");
            resultPanel.add(new JLabel(daoToy.avgPrice(country)));

            parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), resultPanel, 300, 150);

            close.addActionListener(r -> parameterFrame.dispose());
        });
    }

    public void findMoldova() {
        getContentPane().removeAll();
        daoToy.insertMoldova();
        List<Toy> toyList = daoToy.findMoldova();
        displayTable(toyList);
    }

    public void filters() {
        getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel maxPriceLabel = new JLabel("Max price:");
        JTextField maxPriceTextField = new JTextField();
        JLabel minAgeLabel = new JLabel("Min age:");
        JTextField minAgeTextField = new JTextField();
        JLabel maxAgeLabel = new JLabel("Max age:");
        JTextField maxAgeTextField = new JTextField();
        JButton button = new JButton("Ok");

        panel.add(maxPriceLabel);
        panel.add(maxPriceTextField);
        panel.add(minAgeLabel);
        panel.add(minAgeTextField);
        panel.add(maxAgeLabel);
        panel.add(maxAgeTextField);
        panel.add(button);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        button.addActionListener(e -> {
            List<Toy> toyList = daoToy.filters(Double.parseDouble(maxPriceTextField.getText()),
                    Integer.parseInt(minAgeTextField.getText()), Integer.parseInt(maxAgeTextField.getText()));
            displayTable(toyList);
        });
    }

    public void popular() {
        getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        String result = daoToy.popular();
        JButton button = new JButton("Cancel");

        panel.add(new JLabel(result));
        panel.add(button);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        button.addActionListener(e -> parameterFrame.dispose());
    }
}
