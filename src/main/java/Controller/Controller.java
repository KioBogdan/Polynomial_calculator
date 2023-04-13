package Controller;

import Model.Operations;
import Model.Polynomial;
import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Operations operationsModel;
    private GUI gui;

    public Controller(Operations operations, GUI gui) {
        this.operationsModel = operations;
        this.gui = gui;

        gui.addAdditionListener(new AdditionListener());
        gui.addSubstitutionListener(new SubstitutionListener());
        gui.addMultiplyListener(new MultiplyListener());
        gui.addDivideListener(new DivideListener());
        gui.addDerivationListener(new DerivationListener());
        gui.addIntegrationListener(new IntegrationListener());
    }

    public String showPoly(Polynomial p1) {
        return p1.getPolynom().entrySet().stream()
                .map(e1 -> (e1.getValue() >= 0 ? "+" + e1.getValue() : e1.getValue()) + "*X^" + e1.getKey())
                .collect(Collectors.joining(""));
    }

    public class AdditionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial poli1 = null;
            Polynomial poli2 = null;
            try {
                poli1 = new Polynomial(gui.getFirstPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                poli2 = new Polynomial(gui.getSecondPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Polynomial poli3 = operationsModel.add(poli1, poli2);
            gui.setResultPolyText(showPoly(poli3));
        }
    }

    public class SubstitutionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial poli1 = null;
            Polynomial poli2 = null;
            try {
                poli1 = new Polynomial(gui.getFirstPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                poli2 = new Polynomial(gui.getSecondPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Polynomial poli3 = operationsModel.sub(poli1, poli2);
            gui.setResultPolyText(showPoly(poli3));
        }
    }

    public class MultiplyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial poli1 = null;
            Polynomial poli2 = null;
            try {
                poli1 = new Polynomial(gui.getFirstPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                poli2 = new Polynomial(gui.getSecondPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Polynomial poli3 = operationsModel.multiply(poli1, poli2);

            gui.setResultPolyText(showPoly(poli3));
        }
    }

    public class DivideListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial poli1 = null;
            Polynomial poli2 = null;
            Polynomial poli3 = null;
            List<Polynomial> pRez = new ArrayList<>();
            try {
                poli1 = new Polynomial(gui.getFirstPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                poli2 = new Polynomial(gui.getSecondPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                pRez = operationsModel.divide(poli1, poli2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            gui.setResultPolyText(showPoly(pRez.get(0)));
            gui.setRemainderSecondText(showPoly(pRez.get(1)));
        }
    }

    public class DerivationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial poli1 = null;
            try {
                poli1 = new Polynomial(gui.getFirstPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Polynomial poli2 = operationsModel.derive(poli1);

            gui.setResultPolyText(showPoly(poli2));
        }
    }

    public class IntegrationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Polynomial poli1 = null;
            try {
                poli1 = new Polynomial(gui.getFirstPolynom());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Polynomial poli2 = operationsModel.integrate(poli1);

            gui.setResultPolyText(showPoly(poli2));
        }
    }


}

