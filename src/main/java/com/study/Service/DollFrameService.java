package com.study.Service;

import com.study.DAO.DaoImpl.DaoDollImpl;
import com.study.DAO.DaoImpl.DaoToyImpl;
import com.study.DAO.DaoModels.DaoDoll;
import com.study.DAO.DaoModels.DaoToy;
import com.study.DAO.Exceptions.DaoException;
import com.study.Model.DataTableModel;
import com.study.Model.Doll;
import com.study.Model.ParameterFrameFactory;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DollFrameService extends JFrame {
    private DaoDoll daoDoll;
    private DaoToy daoToy;
    private JFrame parameterFrame;

    public DollFrameService() {
        initializeUI();
    }

    private void initializeUI() {
        daoDoll = new DaoDollImpl();
        daoToy = new DaoToyImpl();

        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void findAll() throws DaoException {
        getContentPane().removeAll();
        List<Doll> dollList = daoDoll.findAll();
        displayTable(dollList);
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
        JLabel materialLabel = new JLabel("Material:");
        JTextField materialTextField = new JTextField();
        JLabel heightLabel = new JLabel("Height:");
        JTextField heightTextField = new JTextField();
        JButton updateButton = new JButton("Update");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(materialLabel);
        panel.add(materialTextField);
        panel.add(heightLabel);
        panel.add(heightTextField);
        panel.add(updateButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Update", new FlowLayout(), panel, 300, 150);

        updateButton.addActionListener(e -> {
            try {
                daoDoll.update(codeTextField.getText(),
                        new Doll(codeTextField.getText(),
                                daoDoll.findByCod(codeTextField.getText()).getToy(),
                                materialTextField.getText(),
                                Double.valueOf(heightTextField.getText())
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
                daoDoll.delete(codeTextField.getText());
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
        JLabel materialLabel = new JLabel("Material:");
        JTextField materialTextField = new JTextField();
        JLabel heightLabel = new JLabel("Height:");
        JTextField heightTextField = new JTextField();
        JButton insertButton = new JButton("Insert");

        panel.add(codeLabel);
        panel.add(codeTextField);
        panel.add(toyLabel);
        panel.add(toyTextField);
        panel.add(materialLabel);
        panel.add(materialTextField);
        panel.add(heightLabel);
        panel.add(heightTextField);
        panel.add(insertButton);

        parameterFrame = ParameterFrameFactory.createParameterFrame("Insert", new FlowLayout(), panel, 300, 150);

        insertButton.addActionListener(e -> {
            try {
                daoDoll.insert(
                        new Doll(codeTextField.getText(),
                                daoToy.findByCod(toyTextField.getText()),
                                materialTextField.getText(),
                                Double.valueOf(heightTextField.getText())
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
            Doll doll = daoDoll.findByCod(cod);
            if (doll != null) {
                List<Doll> dollList = new ArrayList<>();
                dollList.add(doll);
                displayTable(dollList);
            } else {
                JOptionPane.showMessageDialog(parameterFrame, "Doll not found!");
            }
        } catch (DaoException ex) {
            throw new RuntimeException();
        }
    }

    private void displayTable(List<Doll> dollList) {
        getContentPane().removeAll();

        List<String> columnNames = new ArrayList<>();
        columnNames.add("Cod");
        columnNames.add("Toy");
        columnNames.add("Material");
        columnNames.add("Height");

        DataTableModel dataTableModel = new DataTableModel(dollList, columnNames);
        JTable table = new JTable(dataTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        validate();
    }

    public void ascending() {
        getContentPane().removeAll();
        List<Doll> dollList = daoDoll.ascending();
        displayTable(dollList);
    }
}
