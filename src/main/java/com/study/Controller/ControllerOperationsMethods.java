package com.study.Controller;

import com.study.DAO.Exceptions.DaoException;
import com.study.Service.*;

public class ControllerOperationsMethods {
    private final MainOperationsFrame mainOperationsFrame;
    private final ToyFrameService toyFrameService = new ToyFrameService();
    private final CategoryFrameService categoryFrameService = new CategoryFrameService();
    private final DollFrameService dollFrameService = new DollFrameService();
    private final SaleFrameService saleFrameService = new SaleFrameService();

    public ControllerOperationsMethods(MainOperationsFrame mainOperationsFrame) {
        this.mainOperationsFrame = mainOperationsFrame;
    }

    public void getMethod(String className, String method) {
        switch (className) {
            case "Toy":
                try {
                    switch (method) {
                        case "getall" -> toyFrameService.findAll();
                        case "getone" -> {
                            toyFrameService.findAll();
                            toyFrameService.findByCod();
                        }
                        case "insert" -> {
                            toyFrameService.findAll();
                            toyFrameService.insert();
                        }
                        case "update" -> {
                            toyFrameService.findAll();
                            toyFrameService.update();
                        }
                        case "delete" -> {
                            toyFrameService.findAll();
                            toyFrameService.delete();
                        }
                    }

                    mainOperationsFrame.openFrame(toyFrameService);
                } catch (DaoException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            case "Category":
                try {
                    switch (method) {
                        case "getall" -> categoryFrameService.findAll();
                        case "getone" -> {
                            categoryFrameService.findAll();
                            categoryFrameService.findByCod();
                        }
                        case "insert" -> {
                            categoryFrameService.findAll();
                            categoryFrameService.insert();
                        }
                        case "update" -> {
                            categoryFrameService.findAll();
                            categoryFrameService.update();
                        }
                        case "delete" -> {
                            categoryFrameService.findAll();
                            categoryFrameService.delete();
                        }
                    }
                        mainOperationsFrame.openFrame(categoryFrameService);
                } catch (DaoException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            case "Doll":
                try {
                    switch (method) {
                        case "getall" -> dollFrameService.findAll();
                        case "getone" -> {
                            dollFrameService.findAll();
                            dollFrameService.findByCod();
                        }
                        case "insert" -> {
                            dollFrameService.findAll();
                            dollFrameService.insert();
                        }
                        case "update" -> {
                            dollFrameService.findAll();
                            dollFrameService.update();
                        }
                        case "delete" -> {
                            dollFrameService.findAll();
                            dollFrameService.delete();
                        }
                    }
                    mainOperationsFrame.openFrame(dollFrameService);
                } catch (DaoException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Sale":
                try {
                    switch (method) {
                        case "getall" -> saleFrameService.findAll();
                        case "getone" -> {
                            saleFrameService.findAll();
                            saleFrameService.findByCod();
                        }
                        case "insert" -> {
                            saleFrameService.findAll();
                            saleFrameService.insert();
                        }
                        case "update" -> {
                            saleFrameService.findAll();
                            saleFrameService.update();
                        }
                        case "delete" -> {
                            saleFrameService.findAll();
                            saleFrameService.delete();
                        }
                    }
                    mainOperationsFrame.openFrame(saleFrameService);
                } catch (DaoException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid className: " + className);
        }
    }
}
