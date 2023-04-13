package Model;

import java.util.*;

public class Operations {

    //
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial pRez = new Polynomial();
        TreeMap<Integer, Double> pTree1 = new TreeMap<Integer, Double>(p1.getPolynom());
        TreeMap<Integer, Double> pTree2 = new TreeMap<Integer, Double>(p2.getPolynom());
        double coeff2;

        for (Map.Entry<Integer, Double> entry1 : pTree1.entrySet()) { //values from the first polynomial that are in the second one as well
            int exp1 = entry1.getKey();
            if (pTree2.get(exp1) != null) {
                coeff2 = pTree2.get(exp1);
                if (entry1.getValue() + coeff2 != 0)
                    pRez.getPolynom().put(exp1, coeff2 + entry1.getValue());
            } else pRez.getPolynom().put(exp1, entry1.getValue());
        }

        for (Map.Entry<Integer, Double> entry2 : pTree2.entrySet()) { //values from the second polynomial that are not in the first one
            int exp2 = entry2.getKey();

            if (pTree1.get(exp2) == null) {
                if (entry2.getValue() != 0)
                    pRez.getPolynom().put(exp2, entry2.getValue());
            }
        }
        return pRez;
    }

    public static Polynomial sub(Polynomial p1, Polynomial p2) {
        Polynomial pRez = new Polynomial();
        TreeMap<Integer, Double> pTree1 = new TreeMap<>(p1.getPolynom());
        TreeMap<Integer, Double> pTree2 = new TreeMap<>(p2.getPolynom());
        double coeff2;

        for (Map.Entry<Integer, Double> entry1 : pTree1.entrySet()) { //values from the first polynomial that are in the second one as well
            int exp1 = entry1.getKey();
            if (pTree2.get(exp1) != null) {
                coeff2 = pTree2.get(exp1);
                if (entry1.getValue() - coeff2 != 0)
                    pRez.getPolynom().put(exp1, entry1.getValue() - coeff2);
            } else pRez.getPolynom().put(exp1, entry1.getValue());
        }

        for (Map.Entry<Integer, Double> entry2 : pTree2.entrySet()) { //values from the second polynomial that are not in the first one
            int exp2 = entry2.getKey();

            if (pTree1.get(exp2) == null) {
                if (entry2.getValue() != 0)
                    pRez.getPolynom().put(exp2, -entry2.getValue());
            }
        }

        return pRez;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial pRez = new Polynomial();
        TreeMap<Integer, Double> pTree1 = new TreeMap<>(p1.getPolynom());
        TreeMap<Integer, Double> pTree2 = new TreeMap<>(p2.getPolynom());

        for (Map.Entry<Integer, Double> entry : pTree1.entrySet()) {
            int exp1 = entry.getKey();
            double coef1 = entry.getValue();

            for (Map.Entry<Integer, Double> entry1 : pTree2.entrySet()) {
                int exp2 = entry1.getKey();
                double coef2 = entry1.getValue();

                //if(exp1!=0 && exp2!=0) {
                boolean find = false;
                for (Map.Entry<Integer, Double> entry2 : pRez.getPolynom().entrySet()) {
                    int exp3 = entry2.getKey();
                    if (exp3 == exp1 + exp2) {
                        find = true;
                    }
                }
                if (find) {
                    if (pRez.getPolynom().get(exp1 + exp2) != null) {
                        double value = pRez.getPolynom().get(exp1 + exp2);
                        pRez.getPolynom().replace(exp1 + exp2, value, value + coef1 * coef2);
                    }
                } else pRez.getPolynom().put(exp1 + exp2, coef1 * coef2);
            }
            //}
        }
        return pRez;
    }

    public static List<Polynomial> divide(Polynomial p1, Polynomial p2) throws Exception {
        Polynomial poliQuo = new Polynomial();
        Polynomial poliRez = new Polynomial();
        Polynomial poliPart, poliCurr;
        Polynomial poliNext = new Polynomial();
        List<Polynomial> polyList = new ArrayList<>();

        boolean available = true;
        TreeMap<Integer, Double> pTree1 = new TreeMap<>(Collections.reverseOrder());
        TreeMap<Integer, Double> pTree2 = new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<Integer, Double> entry1 : p1.getPolynom().entrySet()) {
            pTree1.put(entry1.getKey(), entry1.getValue());
        }

        for (Map.Entry<Integer, Double> entry1 : p2.getPolynom().entrySet()) {
            pTree2.put(entry1.getKey(), entry1.getValue());
        }

        Map.Entry<Integer, Double> keyP2 = pTree2.entrySet().stream().findFirst().get();

        poliCurr = p1;
        while (available) {
            Map.Entry<Integer, Double> keyP1 = pTree1.entrySet().stream().findFirst().get(); //get first entry in divisor(2 nd polynomial and dividend (1st polynomial)
            if (keyP2.getKey() <= keyP1.getKey()) {
                if (keyP2.getValue() != 0) {
                    poliQuo.getPolynom().put(keyP1.getKey() - keyP2.getKey(), keyP1.getValue() / keyP2.getValue()); //put in the quotient polynomial the partial result
                    poliRez.getPolynom().put(keyP1.getKey() - keyP2.getKey(), keyP1.getValue() / keyP2.getValue()); //retain value
                    poliPart = multiply(p2, poliRez); //multypling the partial result with the divisor
                    poliNext = sub(poliCurr, poliPart); //substract the value of the dividend

                    pTree1.clear();

                    for (Map.Entry<Integer, Double> entry1 : poliNext.getPolynom().entrySet()) {
                        if (entry1.getKey() != 0 || poliNext.getPolynom().size() == 1)//retain remainder as new result
                            pTree1.put(entry1.getKey(), entry1.getValue());
                    }
                } else {
                    throw new Exception("Division by zero!");
                }
            } else
                available = false;

            if (pTree1.isEmpty())
                available = false;
        }
        //optional: print the remainder somewhere!
        polyList.add(poliQuo);
        polyList.add(poliNext);
        return polyList;
    }

    public static Polynomial derive(Polynomial p1) {
        Polynomial pRez = new Polynomial();
        TreeMap<Integer, Double> pTree1 = new TreeMap<>(p1.getPolynom());
        for (Map.Entry<Integer, Double> entry : pTree1.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            if (exponent == 0) {
                pRez.getPolynom().put(0, 0.0);
            }
            if (coefficient != 0) {
                pRez.getPolynom().put(exponent - 1, coefficient * exponent);
            }
        }
        return pRez;
    }

    public static Polynomial integrate(Polynomial p1) {
        Polynomial pRez = new Polynomial();
        TreeMap<Integer, Double> pTree1 = new TreeMap<>(p1.getPolynom());
        for (Map.Entry<Integer, Double> entry : pTree1.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            if (coefficient != 0) {
                pRez.getPolynom().put(exponent + 1, coefficient / (exponent + 1));
            }
        }
        return pRez;
    }
}
