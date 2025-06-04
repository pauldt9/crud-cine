package utils;

import lib.TextPrompt;

import javax.swing.*;
import java.awt.*;

public class CreateComponents {
    private static Color fgCol = new Color(0x2C3E50);

    public static JLabel createJLabel(String title, int fontSize, boolean bold){
        JLabel label = new JLabel(title);
        if (bold){
            label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        } else {
            label.setFont(new Font("Helvetica Neue", Font.PLAIN, fontSize));
        }
        label.setForeground(fgCol);
        return label;
    }

    public static JButton createButton(String buttonName, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(w, h));
        button.setMinimumSize(new Dimension(w, h));
        button.setMaximumSize(new Dimension(w, h));
        return button;
    }

    public static JPanel createEmptyPanel(int w, int h){
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setPreferredSize(new Dimension(w, h));
        return empty;
    }

    public static JTextField createTextField(String placeHolder, int w, int h){
        Font font = new Font("Helvetica Neue", Font.PLAIN, 16);
        JTextField textField = new JTextField();

        textField.setFont(font);
        textField.setForeground(new Color(30, 30 , 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1, true));
        textField.setMaximumSize(new Dimension(w, h));

        TextPrompt ph = new TextPrompt(placeHolder, textField);
        ph.setForeground(new Color(0x999999));
        ph.setFont(font);

        return textField;
    }

    public static JPasswordField createPasswordField(String placeHolder, int w, int h){
        JPasswordField pf = new JPasswordField();
        Font font = new Font("Helvetica Neue", Font.PLAIN, 16);

        pf.setForeground(new Color(30, 30, 30));
        pf.setMaximumSize(new Dimension(w, h));
        pf.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1, true));
        pf.setFont(font);

        TextPrompt ph = new TextPrompt(placeHolder, pf);
        ph.setForeground(new Color(100, 100, 100));
        ph.setFont(font);

        return pf;
    }

    public static JPanel createButtonsPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 130));
        return panel;
    }
}
