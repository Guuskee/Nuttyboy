package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // Variabelen
    private final String[] noten = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    private int kruizen = 0;

    // UI-components
    JFrame frame = new JFrame("Nuttyboy");

    JPanel pnlInvoer = new JPanel();
    JPanel pnlUitvoer = new JPanel();
    JPanel pnlRBT = new JPanel();
    JButton btnKlik = new JButton("Klik");

    String strUitvoer = "";
    JLabel lblUitvoer = new JLabel(strUitvoer);
    PianoPaneel pPaneel = new PianoPaneel();

    JComboBox cmbNoten = new JComboBox(noten);
    JComboBox cmbKeuze = new JComboBox(LaddersEnum.values());

    JRadioButton rbtLadders = new JRadioButton("Ladders");
    JRadioButton rbtAkkoorden = new JRadioButton("Akkoorden");
    ButtonGroup grpRBTS = new ButtonGroup();

    GridLayout glRooster = new GridLayout(1, 4);
    GridLayout glRBTS = new GridLayout(2, 1);
    GridLayout glUitvoer = new GridLayout(2, 1);

    public static void main(String[] args) {
        Main main = new Main();
        main.UI();
    }

    private void UI() { // Hoofdmethode verantwoordelijk voor het creëren van de UI
        // frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        frame.setSize(500, 500);
        frame.getContentPane().add(BorderLayout.NORTH, pnlInvoer);
        frame.getContentPane().add(BorderLayout.CENTER, pnlUitvoer);

        // btnKlik
        btnKlik.addActionListener(new BtnListener());

        // RBTgroep
        grpRBTS.add(rbtAkkoorden);
        grpRBTS.add(rbtLadders);
        rbtLadders.setSelected(true);
        pnlRBT.add(rbtLadders);
        pnlRBT.add(rbtAkkoorden);
        pnlRBT.setLayout(glRBTS);
        rbtAkkoorden.addActionListener(new RbtListener());
        rbtLadders.addActionListener(new RbtListener());

        // pnlInvoer
        pnlInvoer.setVisible(true);
        pnlInvoer.setLayout(glRooster);
        pnlInvoer.setSize(500, 100);
        pnlInvoer.add(cmbNoten);
        pnlInvoer.add(cmbKeuze);
        pnlInvoer.add(pnlRBT);
        pnlInvoer.add(btnKlik);

        // pnlUitvoer
        pnlUitvoer.setVisible(true);
        pnlUitvoer.setLayout(glUitvoer);
        pnlUitvoer.add(lblUitvoer);
        lblUitvoer.setLocation(100, 50);
        pnlUitvoer.add(pPaneel);
    }

    class BtnListener implements ActionListener { // Buttonclick event
        public void actionPerformed(ActionEvent event) {
            if (rbtAkkoorden.isSelected()) {    // Als rbtAkkoorden geselecteerd is: gebruik akkoordenloop
                maakAkkoord();
                frame.repaint();
            } else if (rbtLadders.isSelected()) {   // Als rbtLadders geselecteerd is: gebruik ladderloop
                maakLadder();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Kies eerst eens iets, sukkel");
            }
        }
    }

    class RbtListener implements ActionListener {   // RadioButtonListener > luistert naar switch van radiobuttons
        public void actionPerformed(ActionEvent event) {
            if (rbtAkkoorden.isSelected()) {
                cmbKeuze.setModel(new DefaultComboBoxModel(AkkoordenEnum.values()));
            } else if (rbtLadders.isSelected()) {
                cmbKeuze.setModel(new DefaultComboBoxModel(LaddersEnum.values()));
            }
            frame.getContentPane().repaint();
        }
    }

    private void maakAkkoord() {
        Object item = cmbKeuze.getSelectedItem(); // Haalt object op
        int[] notenVanAkkoord = ((AkkoordenEnum) item).getNoten(); // Zet de int[] over van het opgehaalde object
        strUitvoer = "<html>"; // inline HTML voor het toepassen van breaks binnen het label (bespaart het werk van een labelarray maken)
        for (int i = 0; i < notenVanAkkoord.length; i++) { // for loop ter lengte van de int[]
            int x = cmbNoten.getSelectedIndex() + notenVanAkkoord[i]; // startnoot + index ogv. int[]
            if (x >= 12) { // als groter of gelijk aan 12, -12 (ivm hoeveelheid noten obv.)
                x -= 12;
            }
            strUitvoer += (i + 1) + " = " + noten[x] + " <br>"; // Rang in toonladder = noot <br>
        }
        strUitvoer += "</html>"; // sluit de inline HTML af
        lblUitvoer.setText(strUitvoer);
        frame.getContentPane().repaint();
    }

    public void maakLadder() { // Zie maakAkkoord hierboven
        Object item = cmbKeuze.getSelectedItem();
        int[] notenVanLadder = ((LaddersEnum) item).getStappen();
        strUitvoer = "<html>";
        for (int i = 0; i < notenVanLadder.length; i++) {
            int x = cmbNoten.getSelectedIndex() + notenVanLadder[i];
            if (x >= 12) {
                x -= 12;
            }
            strUitvoer += (i + 1) + " = " + noten[x] + " <br>";
        }
        strUitvoer += "</html>";
        lblUitvoer.setText(strUitvoer);
        frame.getContentPane().repaint();
    }

    class PianoPaneel extends JPanel {
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            String[] kruizen = {"C#", "D#", "x", "F#", "G#", "A#"};
            String[] helen = {"C ", "D ", "E ", "F ", "G ", "A ", "B "};
            for (int i = 0; i < 7; i++) {
                g2d.setColor(Color.WHITE);
                g2d.fillRect(100 + (i * 50), 50, 50, 200);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(100 + (i * 50), 50, 50, 200);
            }
            for (int h = 0; h < helen.length; h++) {
                if (strUitvoer.contains(helen[h])) {
                    g2d.setColor(Color.ORANGE);
                    g2d.fillRect(100 + (h * 50), 50, 50, 200);
                    g2d.setColor(Color.RED);
                    g2d.drawRect(100 + (h * 50), 50, 50, 200);
                }
            }
            for (int i = 0; i < 6; i++) {
                if (i == 2) {
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(100 + ((i * 50) + 25), 50, 40, 125);
                }
            }
            for (int k = 0; k < kruizen.length; k++) {
                if (strUitvoer.contains(kruizen[k])) {
                    g2d.setColor(Color.ORANGE);
                    g2d.fillRect(100 + ((k * 50) + 25), 50, 40, 125);
                    g2d.setColor(Color.RED);
                    g2d.drawRect(100 + ((k * 50) + 25), 50, 40, 125);
                }
            }
        }
    }
}
