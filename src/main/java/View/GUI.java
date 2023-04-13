package View;

import Controller.Controller;
import Model.Operations;

import javax.swing.*;

public class GUI {
    private JLabel label, label1, label2, label3;
    private JTextField firstPolyText, secondPolyText, resultPolyText;
    private JButton buttonAdd, buttonSub, buttonMul, buttonDiv, buttonDeriv, buttonIntegrate;

    private Operations operationsModel;

    public GUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);
        //head label
        label = new JLabel("Polynomial Calculator");
        label.setBounds(100, 20, 150, 50);
        panel.add(label);

        //first polynom label
        label1 = new JLabel("First Polynom");
        label1.setBounds(45, 65, 100, 30);
        panel.add(label1);

        //first polynom text field
        firstPolyText = new JTextField();
        firstPolyText.setBounds(145, 65, 150, 30);
        firstPolyText.setVisible(true);
        panel.add(firstPolyText);

        //second polynom label
        label2 = new JLabel("Second Polynom");
        label2.setBounds(45, 100, 100, 30);
        panel.add(label2);

        //second polynom text field
        secondPolyText = new JTextField();
        secondPolyText.setBounds(145, 100, 150, 30);
        panel.add(secondPolyText);

        //third polynom label
        label3 = new JLabel("Result Polynom");
        label3.setBounds(45, 135, 100, 30);
        panel.add(label3);

        //third polynom text field
        resultPolyText = new JTextField();
        resultPolyText.setBounds(145, 135, 150, 30);
        //resultPolyText.setEditable(false);
        panel.add(resultPolyText);

        //first button
        buttonAdd = new JButton("+");
        buttonAdd.setBounds(45, 175, 125, 50);
        panel.add(buttonAdd);

        //second button
        buttonSub = new JButton("-");
        buttonSub.setBounds(175, 175, 125, 50);
        panel.add(buttonSub);

        //third button
        buttonMul = new JButton("*");
        buttonMul.setBounds(45, 230, 125, 50);
        panel.add(buttonMul);

        //fourth button
        buttonDiv = new JButton("/");
        buttonDiv.setBounds(175, 230, 125, 50);
        panel.add(buttonDiv);

        //fifth button
        buttonDeriv = new JButton("'");
        buttonDeriv.setBounds(45, 285, 125, 50);
        panel.add(buttonDeriv);

        //sixth button
        buttonIntegrate = new JButton("∫");
        buttonIntegrate.setBounds(175, 285, 125, 50);
        panel.add(buttonIntegrate);

    }

    public void addAdditionListener(Controller.AdditionListener add1) {
        buttonAdd.addActionListener(add1);
    }

    public void addSubstitutionListener(Controller.SubstitutionListener sub1) {
        buttonSub.addActionListener(sub1);
    }

    public void addMultiplyListener(Controller.MultiplyListener mul1) {
        buttonMul.addActionListener(mul1);
    }

    public void addDivideListener(Controller.DivideListener div1) {
        buttonDiv.addActionListener(div1);
    }

    public void addDerivationListener(Controller.DerivationListener der1) {
        buttonDeriv.addActionListener(der1);
    }

    public void addIntegrationListener(Controller.IntegrationListener int1) {
        buttonIntegrate.addActionListener(int1);
    }

    public String getFirstPolynom() {
        return firstPolyText.getText();
    }

    public String getSecondPolynom() {
        return secondPolyText.getText();
    }

    public void setResultPolyText(String rez) {
        resultPolyText.setText(rez);
    }

    public void setRemainderSecondText(String rem) {
        secondPolyText.setText("Remainder: " + rem);
    }
}
