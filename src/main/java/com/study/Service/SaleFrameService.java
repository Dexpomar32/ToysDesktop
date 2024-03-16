package com.study.Service;

import com.study.DAO.DaoImpl.DaoSaleImpl;
import com.study.DAO.DaoImpl.DaoToyImpl;
import com.study.DAO.DaoModels.DaoSale;
import com.study.DAO.DaoModels.DaoToy;
import com.study.DAO.Exceptions.DaoException;
import com.study.Model.DataTableModel;
import com.study.Model.ParameterFrameFactory;
import com.study.Model.Sale;
import com.toedter.calendar.JDateChooser;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SaleFrameService extends JFrame {
    private DaoSale daoSale;
    private DaoToy daoToy;
    private JFrame parameterFrame;

    public SaleFrameService() {
        initializeUI();
    }

    private void initializeUI() {
        daoSale = new DaoSaleImpl();
        daoToy = new DaoToyImpl();

        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void findAll() throws DaoException {
        getContentPane().removeAll();
        List<Sale> saleList = daoSale.findAll();
        displayTable(saleList);
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
        JLabel saleDateLabel = new JLabel("Sale Date:");
        JDateChooser saleDateChooser = new JDateChooser();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();
        JButton updateButton = new JButton("Update");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(saleDateLabel);
        panel.add(saleDateChooser);
        panel.add(quantityLabel);
        panel.add(quantityTextField);
        panel.add(updateButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Update", new FlowLayout(), panel, 300, 150);

        updateButton.addActionListener(e -> {
            try {
                java.util.Date saleDate = saleDateChooser.getDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = simpleDateFormat.format(saleDate);
                Date sqlDate = Date.valueOf(dateString);

                daoSale.update(codeTextField.getText(),
                        new Sale(codeTextField.getText(),
                                daoSale.findByCod(codeTextField.getText()).getToy(),
                                sqlDate,
                                Integer.valueOf(quantityTextField.getText())
                        ));
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
                daoSale.delete(codeTextField.getText());
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
        JLabel toyLabel = new JLabel("Toy:");
        JTextField toyTextField = new JTextField();
        JLabel saleDateLabel = new JLabel("Sale Date:");
        JDateChooser saleDateChooser = new JDateChooser();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();
        JButton insertButton = new JButton("Insert");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(toyLabel);
        panel.add(toyTextField);
        panel.add(saleDateLabel);
        panel.add(saleDateChooser);
        panel.add(quantityLabel);
        panel.add(quantityTextField);
        panel.add(insertButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        insertButton.addActionListener(e -> {
            try {
                java.util.Date saleDate = saleDateChooser.getDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = simpleDateFormat.format(saleDate);
                Date sqlDate = Date.valueOf(dateString);

                daoSale.insert(
                        new Sale(codeTextField.getText(),
                                daoToy.findByCod(toyTextField.getText()),
                                sqlDate,
                                Integer.valueOf(quantityTextField.getText())
                        ));
                getOne(codeTextField.getText());
                parameterFrame.dispose();
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void getOne(String cod) {
        try {
            Sale sale = daoSale.findByCod(cod);
            if (sale != null) {
                List<Sale> saleList = new ArrayList<>();
                saleList.add(sale);
                displayTable(saleList);
            } else {
                JOptionPane.showMessageDialog(parameterFrame, "Sale not found!");
            }
        } catch (DaoException ex) {
            throw new RuntimeException();
        }
    }

    private void displayTable(List<Sale> saleList) {
        getContentPane().removeAll();

        List<String> columnNames = new ArrayList<>();
        columnNames.add("Cod");
        columnNames.add("Toy");
        columnNames.add("Sale Date");
        columnNames.add("Quantity");

        DataTableModel dataTableModel = new DataTableModel(saleList, columnNames);
        JTable table = new JTable(dataTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }

    public void sales() {
        getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel saleDateLabel = new JLabel("Sale Date:");
        JDateChooser saleDateChooser = new JDateChooser();
        JButton button = new JButton("Ok");

        panel.add(saleDateLabel);
        panel.add(saleDateChooser);
        panel.add(button);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        button.addActionListener(e -> {
            java.util.Date saleDate = saleDateChooser.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = simpleDateFormat.format(saleDate);
            Date sqlDate = Date.valueOf(dateString);

            parameterFrame.dispose();
            JPanel resultPanel = new JPanel(new GridLayout(1, 2));
            JButton close = new JButton("Close");
            resultPanel.add(new JLabel(daoSale.sales(sqlDate)));
            resultPanel.add(close);

            parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), resultPanel, 300, 150);

            close.addActionListener(r -> parameterFrame.dispose());
        });
    }
}
