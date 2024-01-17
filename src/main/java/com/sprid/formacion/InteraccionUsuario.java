package com.sprid.formacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteraccionUsuario {

    JFrame frame;
    JButton button;
    JTextField textField;
    JLabel label;

    public InteraccionUsuario() {
        frame = new JFrame("Interacción Usuario");
        button = new JButton("Haz clic en mí");
        textField = new JTextField(15);
        label = new JLabel();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = textField.getText();
                label.setText("Ingresaste: " + texto);
            }
        });

        frame.getContentPane().add(button);
        frame.getContentPane().add(textField);
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
}
