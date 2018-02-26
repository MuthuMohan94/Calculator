package calc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class Infix2PostFix extends Calculator {
    private String infix; 
    private Deque<Character> stack = new ArrayDeque<Character>();
    private List<String> postfix = new ArrayList<String>(); 

    public Infix2PostFix(String expr) {
        infix = expr;
        convertExpr();
    }

    private void convertExpr() {
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i != infix.length(); ++i) {           
            if(Character.isDigit(infix.charAt(i))) {
                temp.append(infix.charAt(i));
                while((i+1) != infix.length() && (Character.isDigit(infix.charAt(i+1)) || infix.charAt(i+1) == '.')) {
                    temp.append(infix.charAt(++i));
                }
                postfix.add(temp.toString());
                temp.delete(0, temp.length());
            } else
                converExprToStack(infix.charAt(i));
        }
        clrStk();
    }

    private void converExprToStack(char input) {
        if(stack.isEmpty() || input == '(')
            stack.addLast(input);
        else {
            if(input == ')') {
                while(!stack.getLast().equals('(')) {
                    postfix.add(stack.removeLast().toString());
                }
                stack.removeLast();
            }
            else {
                if(stack.getLast().equals('('))
                    stack.addLast(input);
                else {
                    while(!stack.isEmpty() && !stack.getLast().equals('(') && checkPrecedence(input) <= checkPrecedence(stack.getLast())) {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.addLast(input);
                }
            }
        }
    }

    private int checkPrecedence(char op) {
         if (op == '+' || op == '-')
                return 1;
         else if (op == '*' || op == '/')
                return 2;
         else if (op == '^')
                return 3;
         else return 0;
    }


    private void clrStk() {
        while(!stack.isEmpty()) {
            postfix.add(stack.removeLast().toString());
        }
    }

    public List<String> getPostfixList() {
        return postfix;
    }
}