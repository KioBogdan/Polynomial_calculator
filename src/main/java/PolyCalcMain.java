import Controller.Controller;
import Model.Operations;
import View.GUI;

public class PolyCalcMain {
    public static void main(String[] args) {
        Operations operationsModel = new Operations();
        GUI viewGui = new GUI();
        Controller cont1 = new Controller(operationsModel, viewGui);
    }

    /*
    exemple de polinoame:
        ex1: X^3-3X^2+2X^1
             X^1+2X^0

        ex2*/
}
