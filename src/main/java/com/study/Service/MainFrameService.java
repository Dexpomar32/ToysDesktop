package com.study.Service;

import lombok.Getter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Getter
@SuppressWarnings("FieldCanBeLocal")
public class MainFrameService extends JFrame {
    private JPanel tablesPanel;
    private JPanel functionsPanel;
    private JPanel mainPanel;
    public static JMenuBar modelMenuBar;

    public MainFrameService() throws IOException {
        initializeComponents();
        createGUI();
    }

    private void initializeComponents() {
        modelMenuBar = new JMenuBar();
    }

    private void createGUI() throws IOException {
        functionsPanel = new JPanel();
        functionsPanel.setBackground(Color.BLACK);
        functionsPanel.add(new JLabel());
        functionsPanel.setPreferredSize(new Dimension(functionsPanel.getPreferredSize().width, 100));

        tablesPanel = new JPanel();
        tablesPanel.setBackground(Color.BLACK);
        tablesPanel.setPreferredSize(new Dimension(250, tablesPanel.getPreferredSize().height));
        mainPanel = new JPanel(new BorderLayout());

        BufferedImage myPicture = ImageIO.read(new File("src/main/resources/toy.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mainPanel.add(picLabel);

        setLayout(new BorderLayout());
        setJMenuBar(modelMenuBar);
        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void openFrame(JFrame frame) {
        mainPanel.removeAll();
        mainPanel.add(frame.getContentPane(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void addListener(JMenuItem item, ActionListener listener) {
        item.addActionListener(listener);
    }
}