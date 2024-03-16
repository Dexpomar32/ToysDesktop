package com.study;

import com.study.Controller.MainFrameController;
import com.study.Service.MainFrameService;
import java.io.IOException;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws IOException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        MainFrameService mainFrameService = new MainFrameService();
        new MainFrameController(mainFrameService);
    }
}