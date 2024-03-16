package com.study.Controller;

import com.study.Service.*;

public class MainFrameController {
    public MainFrameController(MainFrameService mainFrameService) {
        MenuService menuService = new MenuService();
        MainOperationsFrame mainOperationsFrame = new MainOperationsFrame();
        FiltersFrame filtersFrame = new FiltersFrame();
        ControllerOperationsMethods controllerOperationsMethods = new ControllerOperationsMethods(mainOperationsFrame);
        ControllerFiltersMethods controllerFiltersMethods = new ControllerFiltersMethods(filtersFrame);

        mainFrameService.addListener(menuService.getSelectMenuItem(), e -> {
            mainOperationsFrame.getAll();
            mainFrameService.openFrame(mainOperationsFrame.getFrame());
        });

        mainFrameService.addListener(menuService.getGetOneMenuItem(), e -> {
            mainOperationsFrame.getOne();
            mainFrameService.openFrame(mainOperationsFrame.getFrame());
        });

        mainFrameService.addListener(menuService.getInsertMenuItem(), e -> {
            mainOperationsFrame.insert();
            mainFrameService.openFrame(mainOperationsFrame.getFrame());
        });

        mainFrameService.addListener(menuService.getUpdateMenuItem(), e -> {
            mainOperationsFrame.update();
            mainFrameService.openFrame(mainOperationsFrame.getFrame());
        });

        mainFrameService.addListener(menuService.getDeleteMenuItem(), e -> {
            mainOperationsFrame.delete();
            mainFrameService.openFrame(mainOperationsFrame.getFrame());
        });

        mainFrameService.addListener(menuService.getToyMenuItem(), e -> {
            filtersFrame.toy();
            mainFrameService.openFrame(filtersFrame.getFrame());
        });

        mainFrameService.addListener(menuService.getDollMenuItem(), e -> {
            filtersFrame.doll();
            mainFrameService.openFrame(filtersFrame.getFrame());
        });

        mainFrameService.addListener(menuService.getSaleMenuItem(), e -> {
            filtersFrame.sale();
            mainFrameService.openFrame(filtersFrame.getFrame());
        });

        mainOperationsFrame.addListener(mainOperationsFrame.getButton(), e -> {
            if (mainOperationsFrame.getChoice() == 1) {
                controllerOperationsMethods.getMethod(String.valueOf(mainOperationsFrame.getTableComboBox().getSelectedItem()), "getall");
            }

            if (mainOperationsFrame.getChoice() == 2) {
                controllerOperationsMethods.getMethod(String.valueOf(mainOperationsFrame.getTableComboBox().getSelectedItem()), "getone");
            }

            if (mainOperationsFrame.getChoice() == 3) {
                controllerOperationsMethods.getMethod(String.valueOf(mainOperationsFrame.getTableComboBox().getSelectedItem()), "insert");
            }

            if (mainOperationsFrame.getChoice() == 4) {
                controllerOperationsMethods.getMethod(String.valueOf(mainOperationsFrame.getTableComboBox().getSelectedItem()), "update");
            }

            if (mainOperationsFrame.getChoice() == 5) {
                controllerOperationsMethods.getMethod(String.valueOf(mainOperationsFrame.getTableComboBox().getSelectedItem()), "delete");
            }
        });

        filtersFrame.addListener(filtersFrame.getButton(), e -> {
            if (filtersFrame.getChoice() == 1) {
                controllerFiltersMethods.getMethod("Toy", String.valueOf(filtersFrame.getTableComboBox().getSelectedItem()));
            }

            if (filtersFrame.getChoice() == 2) {
                controllerFiltersMethods.getMethod("Doll", String.valueOf(filtersFrame.getTableComboBox().getSelectedItem()));
            }

            if (filtersFrame.getChoice() == 3) {
                controllerFiltersMethods.getMethod("Sale", String.valueOf(filtersFrame.getTableComboBox().getSelectedItem()));
            }
        });
    }
}