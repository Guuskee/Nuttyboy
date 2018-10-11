package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // UI-components
    JFrame frame = new JFrame("Nuttyboy");

    // Panels
    JPanel pnlInvoer = new JPanel();
    JPanel pnlUitvoer = new JPanel();
    JPanel pnlRBT = new JPanel();
    PianoPaneel pPaneel = new PianoPaneel();
    GitaarPaneel gPaneel = new GitaarPaneel();

    // Buttons
    JButton btnKlik = new JButton("Klik");

    // Uitvoer
    String strUitvoer = "";
    JLabel lblUitvoer = new JLabel(strUitvoer);

    // Comboboxes
    DefaultComboBoxModel ladders = new DefaultComboBoxModel(LaddersEnum.values());
    DefaultComboBoxModel akkoorden = new DefaultComboBoxModel(AkkoordenEnum.values());
    JComboBox cmbNoten = new JComboBox(Utility.noten);
    JComboBox cmbKeuze = new JComboBox();

    // Radiobuttons
    JRadioButton rbtLadders = new JRadioButton("Ladders");
    JRadioButton rbtAkkoorden = new JRadioButton("Akkoorden");
    ButtonGroup grpRBTS = new ButtonGroup();

    // Layouts
    GridLayout glRooster = new GridLayout(1, 4);
    GridLayout glRBTS = new GridLayout(2, 1);
    BoxLayout boxUitvoer = new BoxLayout(pnlUitvoer, BoxLayout.PAGE_AXIS);

    // GL textfieldarray-uitvoer
    GridLayout glUitvoer = new GridLayout(7, 1);
    JTextField[] txtArray = new JTextField[7];
    JPanel pnlLblUitvoer = new JPanel();

    public static void main(String[] args) {
        Main main = new Main();
        main.UI();
    }

    private void UI() { // Hoofdmethode verantwoordelijk voor het creëren van de UI
        // frame
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(BorderLayout.NORTH, pnlInvoer);
        frame.getContentPane().add(BorderLayout.CENTER, pnlUitvoer);
        frame.getContentPane().add(BorderLayout.WEST, lblUitvoer);

        // btnKlik
        btnKlik.addActionListener(new BtnListener());
        btnKlik.setBackground(Kleuren.bgKleur);
        btnKlik.setBorder(BorderFactory.createBevelBorder(2, Kleuren.borderKleur, Kleuren.bgKleur));
        btnKlik.isDefaultCapable();

        // lblUitvoer
        lblUitvoer.getParent().setBackground(Kleuren.frmKleur);
        lblUitvoer.setBorder(BorderFactory.createLineBorder(Kleuren.borderKleur, 2));
        lblUitvoer.setForeground(Color.WHITE);

        // RBTgroep
        grpRBTS.add(rbtLadders);
        grpRBTS.add(rbtAkkoorden);

        pnlRBT.add(rbtLadders);
        pnlRBT.add(rbtAkkoorden);
        pnlRBT.setLayout(glRBTS);

        rbtAkkoorden.addActionListener(new RbtListener());
        rbtAkkoorden.setBackground(Kleuren.frmKleur);
        rbtAkkoorden.setForeground(Color.WHITE);

        rbtLadders.addActionListener(new RbtListener());
        rbtLadders.setBackground(Kleuren.frmKleur);
        rbtLadders.setForeground(Color.WHITE);
        rbtLadders.setSelected(true);

        // pnlInvoer
        pnlInvoer.setVisible(true);
        pnlInvoer.setLayout(glRooster);
        pnlInvoer.setSize(500, 100);
        pnlInvoer.add(cmbNoten);
        pnlInvoer.add(cmbKeuze);
        pnlInvoer.add(pnlRBT);
        pnlInvoer.add(btnKlik);
        cmbKeuze.setModel(ladders);
        cmbKeuze.setSelectedIndex(0);

        // pnlUitvoer
        pnlUitvoer.setVisible(true);
        pnlUitvoer.setLayout(boxUitvoer);
        pnlUitvoer.add(pPaneel);
        pnlUitvoer.add(gPaneel);
        pnlUitvoer.setBackground(Kleuren.bgKleur);

        pnlLblUitvoer.setLayout(glUitvoer);
        pnlLblUitvoer.setVisible(true);

        for (int i = 0; i < 7; i++) {
            txtArray[i] = new JTextField("      ");
            pnlLblUitvoer.add(txtArray[i], i);
        }
        frame.getContentPane().add(BorderLayout.EAST, pnlLblUitvoer);
    }

    class BtnListener implements ActionListener { // Buttonclick event
        public void actionPerformed(ActionEvent event) {
            if (rbtAkkoorden.isSelected()) {    // Als rbtAkkoorden geselecteerd is: gebruik akkoordenloop
                maakAkkoord2();
                System.out.println("fuck");
            } else if (rbtLadders.isSelected()) {   // Als rbtLadders geselecteerd is: gebruik ladderloop
                maakLadder2();
                System.out.println("shit");
            } else {
                JOptionPane.showMessageDialog(frame, "Kies eerst eens iets, sukkel");
            }
            frame.getContentPane().repaint();
        }
    }

    class RbtListener implements ActionListener {   // RadioButtonListener > luistert naar switch van radiobuttons
        public void actionPerformed(ActionEvent event) {
            if (rbtAkkoorden.isSelected()) {
                cmbKeuze.setModel(akkoorden);
            } else if (rbtLadders.isSelected()) {
                cmbKeuze.setModel(ladders);
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
            strUitvoer += (i + 1) + " = " + Utility.noten[x] + " <br>"; // Rang in toonladder = noot <br>
        }
        strUitvoer += "</html>"; // sluit de inline HTML af
        lblUitvoer.setText(strUitvoer);
        frame.getContentPane().repaint();
    }

    private void maakAkkoord2() {
        Object item = cmbKeuze.getSelectedItem(); // Haalt object op
        int[] notenVanAkkoord = ((AkkoordenEnum) item).getNoten(); // Zet de int[] over van het opgehaalde object
        for (int i = 0; i < 7; i++) {
            txtArray[i].setText("");
        }
        for (int i = 0; i < notenVanAkkoord.length; i++) { // for loop ter lengte van de int[]
            int x = cmbNoten.getSelectedIndex() + notenVanAkkoord[i]; // startnoot + index ogv. int[]
            if (x >= 12) { // als groter of gelijk aan 12, -12 (ivm hoeveelheid noten obv.)
                x -= 12;
            }
            txtArray[i].setText(Utility.noten[x]);
            System.out.println(txtArray[i].getText());
        }
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
            strUitvoer += (i + 1) + " = " + Utility.noten[x] + " <br>";
        }
        strUitvoer += "</html>";
        lblUitvoer.setText(strUitvoer);
        frame.getContentPane().repaint();
    }

    public void maakLadder2() { // Zie maakAkkoord hierboven
        Object item = cmbKeuze.getSelectedItem();
        int[] notenVanLadder = ((LaddersEnum) item).getStappen();
        for (int i = 0; i < 7; i++) {
            txtArray[i].setText("");
        }
        for (int i = 0; i < notenVanLadder.length; i++) {
            int x = cmbNoten.getSelectedIndex() + notenVanLadder[i];
            if (x >= 12) {
                x -= 12;
            }
            txtArray[i].setText(Utility.noten[x]);
            System.out.println(txtArray[i].getText());
        }
        frame.getContentPane().repaint();
    }

    class PianoPaneel extends JPanel { // De inner class verantwoordelijk voor het painten van het pianopaneel
        public void paintComponent(Graphics g) { // xxx.repaint() callt deze methode
            Graphics2D g2d = (Graphics2D) g;
            for (int i = 0; i < 7; i++) { // Creëert de witte toetsen met een zwarte border eromheen
                g2d.setColor(Color.WHITE);
                g2d.fillRect(100 + (i * 50), 50, 50, 200);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(100 + (i * 50), 50, 50, 200);
            }
            for (int h = 0; h < Utility.hele.length; h++) { // Loop hele noten af en check  of deze
                for (int i = 0; i < txtArray.length; i++) {
                    if (Utility.hele[h].equals(txtArray[i].getText())) { // in strUitvoer (gecreëerd door maakLadder()/maakAkkoord())
                        if (i != 0) {
                            g2d.setColor(Kleuren.toetsKleur);              // voorkomen, en maak de bijbehorende toetsen een andere kleur
                        }
                        else if ( i == 0) {
                            g2d.setColor(Color.RED);
                        }
                        g2d.fillRect(100 + (h * 50), 50, 50, 200);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(100 + (h * 50), 50, 50, 200);
                        }
                    }
                }
            for (int i = 0; i < 6; i++) { // creëert de zwarte toetsen
                if (i == 2) {
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(100 + ((i * 50) + 25), 50, 40, 125);
                }
            }
            for (int k = 0; k < Utility.kruizen.length; k++) {
                for (int i = 0; i < txtArray.length; i++) {
                    if (Utility.kruizen[k].equals(txtArray[i].getText())) {
                        g2d.setColor(Kleuren.toetsKleur);
                        g2d.fillRect(100 + ((k * 50) + 25), 50, 40, 125);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(100 + ((k * 50) + 25), 50, 40, 125);
                    }
                }
            }
        }
    }

    class GitaarPaneel extends JPanel {                         // Maakt het fretboard en de juiste noten
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Kleuren.fretKleur);
            g2d.fillRect(50, 0, 840, 200);   // Fretboard
            g2d.setColor(Color.BLACK);                          // Fretboard border
            g2d.drawRect(50, 0, 840, 200);
            g2d.setColor(Color.WHITE);                          // Topkam
            g2d.fillRect(50, 0, 5, 201);

            for (int i = 0; i < 12; i++) {
                g2d.setColor(Color.GRAY);
                g2d.drawLine((i * 70) + 50, 0, (i * 70) + 50, 200);
            }
            for (int s : Utility.stippen) {                              // stippen
                if (s == 11) {                                  // dubbele stip op de 12de fret
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval((s * 70) + 80, 70, 10, 10);
                    g2d.fillOval((s * 70) + 80, 120, 10, 10);
                } else {                                          // enkele stippen op arrayplekken (fret 3, 5, 7, 9, 12)
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval((s * 70) + 80, 95, 10, 10);
                }
            }
            for (int i = 0; i < 6; i++) {                                                // Snaren
                g2d.setColor(Color.BLACK);
                g2d.drawLine(15, (i * 31) + 20, 890, (i * 31) + 20);
                g2d.drawLine(15, 20, 15, 175);
                g2d.drawString(Utility.snaren[i][0], 0, (200 - ((i * 31) + 20)));
            }
            for (int i = 0; i < Utility.snaren.length; i++) {
                for (int j = 0; j < Utility.snaren[i].length; j++) {
                    for (int k = 0; k < txtArray.length; k++) {
                        if (Utility.snaren[i][j].equals(txtArray[k].getText())) {
                            if (k != 0) {
                                g2d.setColor(Kleuren.toetsKleur);              // voorkomen, en maak de bijbehorende toetsen een andere kleur
                            }
                            else if (k == 0) {
                                g2d.setColor(Color.RED);
                            }
                            g2d.fillOval((j * 70) + 10, ((5 - i) * 31) + 15, 10, 10);
                            g2d.setColor(Color.BLACK);
                            g2d.drawOval((j * 70) + 10, ((5 - i) * 31) + 15, 10, 10);
                        }
                    }
                }
            }
        }
    }
}
