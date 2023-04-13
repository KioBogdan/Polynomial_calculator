import Model.Operations;
import Model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsTest {

    public String showPoly(Polynomial p1) {
        return p1.getPolynom().entrySet().stream()
                .map(e1 -> (e1.getValue() >= 0 ? "+" + e1.getValue() : e1.getValue()) + "*X^" + e1.getKey())
                .collect(Collectors.joining(""));
    }

    @Test
    public void addTest() throws Exception {
        Polynomial p1 = new Polynomial("X^3+3X^2+2X^1");
        Polynomial p2 = new Polynomial("X^2+2X^1+X^0");
        String pRez = "+1.0*X^0+4.0*X^1+4.0*X^2+1.0*X^3";

        assertEquals(pRez, showPoly(Operations.add(p1, p2)));
    }

    @Test
    public void subTest() throws Exception {
        Polynomial p1 = new Polynomial("X^3+3X^2+2X^1");
        Polynomial p2 = new Polynomial("X^2+2X^1+X^0");
        String pRez = "-1.0*X^0+2.0*X^2+1.0*X^3";

        assertEquals(pRez, showPoly(Operations.sub(p1, p2)));
    }

    @Test
    public void mulTest() throws Exception {
        Polynomial p1 = new Polynomial("X^3+2X^2+3X^1");
        Polynomial p2 = new Polynomial("X^2");
        String pRez = "+3.0*X^3+2.0*X^4+1.0*X^5";

        assertEquals(pRez, showPoly(Operations.multiply(p1, p2)));
    }

    @Test
    public void divTest() throws Exception {
        Polynomial p1 = new Polynomial("X^3-3X^2+2X^1");
        Polynomial p2 = new Polynomial("X^1+2X^0");
        String pRezC = "+12.0*X^0-5.0*X^1+1.0*X^2";
        String pRezR = "-24.0*X^0";

        assertEquals(pRezC, showPoly(Operations.divide(p1, p2).get(0)));
        assertEquals(pRezR, showPoly(Operations.divide(p1, p2).get(1)));
    }

    @Test
    public void derivTest() throws Exception {
        Polynomial p1 = new Polynomial("X^3+3X^2+2X^1");
        String pRez = "+2.0*X^0+6.0*X^1+3.0*X^2";

        assertEquals(pRez, showPoly(Operations.derive(p1)));
    }

    @Test
    public void integTest() throws Exception {
        Polynomial p1 = new Polynomial("X^4+X^3+2X^1");
        String pRez = "+1.0*X^2+0.25*X^4+0.2*X^5";

        assertEquals(pRez, showPoly(Operations.integrate(p1)));
    }

}
