package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private Map<Integer, Double> polynom = new HashMap<>();

    public Polynomial() {
    }

    public Polynomial(String e1) throws Exception {
        Pattern pattern = Pattern.compile("([+-])?([0-9]*)(X\\^)([0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(e1);
        int exponent;
        double coeff;

        if (Pattern.matches("(([+-])?([0-9]*)(X\\^)([0-9]+))+", e1)) { //matches at least once
            while (matcher.find()) {
                if (matcher.group(2).isEmpty()) {
                    coeff = 1.0;
                    if (matcher.group(1) != null)
                        if (matcher.group(1).compareTo("-") == 0)
                            coeff = -coeff;
                } else {
                    coeff = Double.parseDouble(matcher.group(2));
                    if (matcher.group(1) != null)
                        if (matcher.group(1).compareTo("-") == 0)
                            coeff = -coeff;
                }
                exponent = Integer.parseInt(matcher.group(4));
                polynom.put(exponent, coeff); // add in a resulting polynom
            }
        } else {
            throw new Exception("Introduceti un polinom valid");
        }

        for (Map.Entry<Integer, Double> entry : polynom.entrySet()) {
            System.out.println("Exponential " + entry.getKey() + " with coefficient: " + entry.getValue());
        }
    }

    public Map<Integer, Double> getPolynom() {
        return polynom;
    }
}
