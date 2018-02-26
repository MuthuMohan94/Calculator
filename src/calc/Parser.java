package calc;

import java.math.BigDecimal;

public class Parser extends Calculator {
	static private String expression;
	static private String previousAnswer;

	Parser(String expr, BigDecimal prevAns) {
	        expression = expr;
	        previousAnswer = String.valueOf(prevAns);
	}
	
	public String handleTrigFunctions() throws Exception {
        int paraEnd=0, paraStart=0,trigstart=0;
		String expr = expression;
		expr = expr.replaceAll("pi", String.valueOf(Math.PI));
		expr = expr.replaceAll("e", String.valueOf(Math.E));
		expr = expr.replaceAll("ANSWER", String.valueOf(previousAnswer));
		char[] car = expr.toCharArray();
	    String test="";
	    String trigFunc="";
	    String answer="";
	    String match = "default";
	    int numStartPara=0, numEndPara=0;
	    
	    for(char c : expr.toCharArray()) {
	    	if(c == '(') {
	    		numStartPara++;
	    	} else if(c == ')') {
	    		numEndPara++;
	    	}
	    }
	    if(numStartPara != numEndPara) {
	    	throw new Exception("Invalid");
	    }
	    
	    for (int i = 0; i < car.length; i++) {
	    	if (expr.contains("sin") && car[i]=='s' && car[i+1] =='i') {
	    		match = "sin";
	    	} else if(expr.contains("cos") && car[i]=='c'){
	    		match = "cos"; 
	    	} else if(expr.contains("tan") && car[i]=='t'){
	    		match = "tan";
	    	} else if(expr.contains("ln") && car[i]=='l'){
	    		match = "ln";
	    	} else if(expr.contains("sqrt") && car[i]=='s'){
	    		match = "sqrt";
	    	} else {
	    		match ="default";
	    	}
	    	if(checkIfSpecialFunction(car, i)) {
	    		trigstart = i;
	    		for(int j=i+2; j<car.length; j++) {
	    			if(car[j] == '(') {
	    				paraStart = j;
	    			}
	    			if(car[j] == ')') {
	    				paraEnd = j;
	    				break;
	    			}
	    		}
	    		if(paraEnd != 0) {
	    			trigFunc = new String(car, trigstart, (paraEnd+1)-trigstart);
	    			test = new String(car, paraStart+1, (paraEnd-1)-paraStart);
	    			int j=1;
	    			while(j<100) {
	    				if(test.contains(Integer.valueOf(j)+"*"+String.valueOf(Math.PI)+"/2") && j%2 != 0 && match == "tan") {
		    				throw new Exception("Invalid");
		    			}
	    				j=j+2;
	    			}
	    			Infix2PostFix pc = new Infix2PostFix(test); 
	    	        PostFixEvaluator calc = new PostFixEvaluator(pc.getPostfixList());
	    	        BigDecimal value = calc.result();
	    	        test= String.valueOf(value.setScale(3, BigDecimal.ROUND_HALF_UP));
	    	    }
	    		switch (match) {
	    		case "sin":
	    			answer = String.valueOf(new BigDecimal(Math.sin(Double.valueOf(test))).setScale(3, BigDecimal.ROUND_HALF_UP));
	    			break;
	    		case "cos":
	    			answer = String.valueOf(new BigDecimal(Math.cos(Double.valueOf(test))).setScale(3, BigDecimal.ROUND_HALF_UP));
	    			break;
	    		case "tan":
	    			answer = String.valueOf(new BigDecimal(Math.tan(Double.valueOf(test))).setScale(3, BigDecimal.ROUND_HALF_UP));
	    			break;
	    		case "ln":
	    			answer = String.valueOf(new BigDecimal(Math.log(Double.valueOf(test))).setScale(3, BigDecimal.ROUND_HALF_UP));
	    			break;
	    		case "sqrt":
	    			answer = String.valueOf(new BigDecimal(Math.sqrt(Double.valueOf(test))).setScale(3, BigDecimal.ROUND_HALF_UP));
	    			break;
	    		case "default":
	    			break;
	    		}
	    		expr = expr.replace(trigFunc, answer);
	    	}
	    }
        return expr;
	}
	
	private boolean checkIfSpecialFunction(char[] car, int i) {
		return (car[i] == 's' && car[i+1] == 'i') || (car[i] == 'c' && car[i+1] == 'o') || (car[i] == 't' && car[i+1] == 'a') || (car[i] == 'l' && car[i+1] == 'n') 
				|| (car[i] == 's' && car[i+1] == 'q');
	}
}