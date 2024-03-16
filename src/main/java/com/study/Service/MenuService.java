package com.study.Service;

import lombok.Getter;

import javax.swing.*;

import static com.study.Service.MainFrameService.modelMenuBar;

@Getter
@SuppressWarnings("FieldCanBeLocal")
public class MenuService {
    private JMenu crudMenu;
    private JMenu filterMenu;
    private JMenuItem selectMenuItem;
    private JMenuItem getOneMenuItem;
    private JMenuItem insertMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem updateMenuItem;
    private JMenuItem toyMenuItem;
    private JMenuItem dollMenuItem;
    private JMenuItem saleMenuItem;

    public MenuService() {
        initializeComponents();
    }

    private void initializeComponents() {
        crudMenu = new JMenu("CRUD");
        filterMenu = new JMenu("Filter");

        selectMenuItem = new JMenuItem("Get All");
        getOneMenuItem = new JMenuItem("Get One");
        insertMenuItem = new JMenuItem("Insert");
        deleteMenuItem = new JMenuItem("Delete");
        updateMenuItem = new JMenuItem("Update");

        crudMenu.add(selectMenuItem);
        crudMenu.add(getOneMenuItem);
        crudMenu.add(insertMenuItem);
        crudMenu.add(deleteMenuItem);
        crudMenu.add(updateMenuItem);

        toyMenuItem = new JMenuItem("Toy");
        dollMenuItem = new JMenuItem("Doll");
        saleMenuItem = new JMenuItem("Sale");

        filterMenu.add(toyMenuItem);
        filterMenu.add(dollMenuItem);
        filterMenu.add(saleMenuItem);

        modelMenuBar.add(crudMenu);
        modelMenuBar.add(filterMenu);
    }
}