package com.study.Controller;

import com.study.Service.*;

public class ControllerFiltersMethods {
    private final FiltersFrame filtersFrame;
    private final ToyFrameService toyFrameService = new ToyFrameService();
    private final DollFrameService dollFrameService = new DollFrameService();
    private final SaleFrameService saleFrameService = new SaleFrameService();

    public ControllerFiltersMethods(FiltersFrame filtersFrame) {
        this.filtersFrame = filtersFrame;
    }

    public void getMethod(String className, String method) {
        switch (className) {
            case "Toy":
                switch (method) {
                    case "exclude" -> toyFrameService.findExclude();
                    case "expensiveCheap" -> toyFrameService.expensiveCheap();
                    case "avgPrice" -> toyFrameService.avgPrice();
                    case "insertMoldova" -> toyFrameService.findMoldova();
                    case "filters" -> toyFrameService.filters();
                    case "popular" -> toyFrameService.popular();
                }
                filtersFrame.openFrame(toyFrameService);
                break;

            case "Sale":
                if (method.equals("sales")) {
                    saleFrameService.sales();
                }
                filtersFrame.openFrame(saleFrameService);
                break;

            case "Doll":
                if (method.equals("ascending")) {
                    dollFrameService.ascending();
                }
                filtersFrame.openFrame(dollFrameService);
                break;
        }
    }
}
