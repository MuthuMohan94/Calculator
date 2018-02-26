package calc;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.JOptionPane;


public class PostFixEvaluator extends Calculator{
    private List<String> expr = new ArrayList<String>();
    private Deque<Double> expStack = new ArrayDeque<Double>();

    public PostFixEvaluator(List<String> postFixExp) {
    	expr = postFixExp;
    }
    public BigDecimal result() {
        for(int i = 0; i != expr.size(); ++i) {
            if(Character.isDigit(expr.get(i).charAt(0))) {
                expStack.addLast(Double.parseDouble(expr.get(i)));
            }
            else {
                double tempResult = 0;
                double temp;
                switch(expr.get(i)) {
                    case "+": temp = expStack.removeLast();
                              tempResult = expStack.removeLast() + temp;
                              break;
                    case "-": temp = expStack.removeLast();
                              tempResult = expStack.removeLast() - temp;
                              break;
                    case "*": temp = expStack.removeLast();
                              tempResult = expStack.removeLast() * temp;
                              break;
                    case "/": temp = expStack.removeLast();
                    		  tempResult = expStack.removeLast() / temp;
                              break;
                    case "^": temp = expStack.removeLast();
                    		  tempResult = Math.pow(expStack.removeLast(), temp);
                }
                expStack.addLast(tempResult);
            }
        }
        return new BigDecimal(expStack.removeLast()).setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}