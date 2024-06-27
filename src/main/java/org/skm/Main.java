package org.skm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class Main {


    public static void main(String[] args) {
        //Frame
        JFrame frame;
        //Panel
        JPanel panel;
        //Labels
        JLabel lblProducto;
        JLabel lblDesde;
        JLabel lblHasta;
        JLabel lblCantidad;
        //TextField
        JTextField tfProducto;
        JTextField tfDesde;
        JTextField tfHasta;
        JTextField tfCantidad;

        //Button
        JButton btnImprimir;

        frame = new JFrame("Generar Etiqueta");
        panel = new JPanel();
        lblProducto = new JLabel("Marca:");
        lblDesde = new JLabel("Desde:");
        lblHasta = new JLabel("Hasta:");
        lblCantidad = new JLabel("Cantidad:");

        tfProducto = new JTextField();
        tfDesde = new JTextField();
        tfHasta = new JTextField();
        tfCantidad = new JTextField();

        btnImprimir = new JButton("Imprimir");

        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(lblProducto);
        panel.add(tfProducto).setPreferredSize(new Dimension(80, 20));
        panel.add(lblDesde);
        panel.add(tfDesde).setPreferredSize(new Dimension(50, 20));
        panel.add(lblHasta);
        panel.add(tfHasta).setPreferredSize(new Dimension(50, 20));
        panel.add(lblCantidad);
        panel.add(tfCantidad).setPreferredSize(new Dimension(30, 20));
        panel.add(btnImprimir);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //cantidad set a 1
        tfCantidad.setText("2");


        //Listener
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int c = 0;
                if (tfProducto.getText().length() + tfDesde.getText().length() + tfHasta.getText().length() > 0) {
                    int ini = Integer.parseInt(tfDesde.getText());
                    int fin = Integer.parseInt(tfHasta.getText());

                    int dilogOption = JOptionPane.showConfirmDialog(null,
                            "Grande?",
                            "Tamaño FUENTE",
                            JOptionPane.YES_NO_OPTION);

                    for (int i = ini; i <= fin; i++) {
                        buildCommand(tfProducto.getText().toUpperCase(Locale.ROOT),
                                String.valueOf(i),
                                Integer.parseInt(tfCantidad.getText()), dilogOption);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Revise los datos ingresados");
                }
            }
        });
    }

    public static void buildCommand (String s1, String s2,int cant, int c){
        String command = "";

        switch (c) {
            case JOptionPane.NO_OPTION:
                command = "^XA\n";
                command += "^FWr,z^FB800,1,0,C,0^CF0,100^FO230,20^FD" + s1 + "^FS\n";
                command += "^FWr,z^FB800,1,0,C,0^CF0,100^FO90,20^FDCAJA " + s2 + "^FS\n";
                command += "^XZ";
                break;
            case JOptionPane.YES_OPTION:
                command = "^XA\n";
                command += "^FWr,z^FB800,1,0,C,0^CF0,170^FO230,20^FD" + s1 + "^FS\n";
                command += "^FWr,z^FB800,1,0,C,0^CF0,170^FO90,20^FDCAJA " + s2 + "^FS\n";
                command += "^XZ";
                break;
            default:
                command = "^XA\n";
                command += "^FWr,z^FB800,1,0,C,0^CF0,170^FO230,20^FD" + s1 + "^FS\n";
                command += "^FWr,z^FB800,1,0,C,0^CF0,170^FO90,20^FDCAJA " + s2 + "^FS\n";
                command += "^XZ";
                break;
        }


        for (int i = 0; i < cant; i++) {
            String port = "LPT1";

            System.out.println(command);

//            try {
//                FileOutputStream os = new FileOutputStream(port);
//                PrintStream ps = new PrintStream(os);
//                ps.println(command);
//                ps.println("\f");
//                ps.close();
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                e.getLocalizedMessage();
//            }
        }

    }


}