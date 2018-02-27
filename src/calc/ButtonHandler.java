package calc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonHandler implements ActionListener {
	
	private String expr;
	public static BigDecimal prevAns;
	
	JTextField inputTextfield;
	JTextField outputTextfield;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	JButton btn5;
	JButton btn6;
	JButton btn7;
	JButton btn8;
	JButton btn9;
	JButton btn0;
	JButton btnDot;
	JButton btnClear;
	JButton btnPlus;
	JButton btnCaret;
	JButton btnSin;
	JButton btnMinus;
	JButton btnSqrRoot;
	JButton btnCos;
	JButton btnForwardSlash;
	JButton btne;
	JButton btnTan;
	JButton btnAsterisk;
	JButton btnPi;
	JButton btnNaturalLog;
	JButton btnAnswer;
	JButton btnEnter;
	JButton btnParaStart;
	JButton btnParaEnd;
	JTextField xField;
	JTextField yField;
	JPanel graphDisplayPanel;
	
	ButtonHandler(JTextField inputTextfield, JTextField outputTextField,JButton bt1, JButton bt2, JButton bt3, JButton bt4, JButton bt5, JButton bt6, JButton bt7, JButton bt8, JButton bt9, JButton bt0
			, JButton btDot, JButton btClr, JButton btPlus, JButton btCaret, JButton btSin, JButton btMinus, JButton btSqrrt, JButton btCos, JButton btDivide, JButton btE, JButton btTan, JButton btMultiply
			, JButton btPi, JButton btln, JButton btAns, JButton btEnt, JButton paraStart, JButton paraEnd, JTextField xRangeField, JTextField yRangeField, JPanel graphDisplayPanel) {
		this.inputTextfield=inputTextfield;
		this.outputTextfield = outputTextField;
		this.btn1 = bt1;
		this.btn2 = bt2;
		this.btn3 = bt3;
		this.btn4 = bt4;
		this.btn5 = bt5;
		this.btn6 = bt6;
		this.btn7 = bt7;
		this.btn8 = bt8;
		this.btn9 = bt9;
		this.btn0 = bt0;
		this.btnDot = btDot;
		this.btnClear = btClr;
		this.btnPlus = btPlus;
		this.btnCaret = btCaret;
		this.btnSin = btSin;
		this.btnMinus = btMinus;
		this.btnSqrRoot = btSqrrt;
		this.btnCos = btCos;
		this.btnForwardSlash = btDivide;
		this.btne = btE;
		this.btnTan = btTan;
		this.btnAsterisk = btMultiply;
		this.btnPi = btPi;
		this.btnNaturalLog = btln;
		this.btnAnswer = btAns;
		this.btnEnter = btEnt;
		this.btnParaEnd = paraEnd;
		this.btnParaStart = paraStart;
		this.xField = xRangeField;
		this.yField = yRangeField;
		this.graphDisplayPanel = graphDisplayPanel;
	}
	
	public static BigDecimal getPrevAns() {
		return prevAns;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn1) {
			inputTextfield.setText(inputTextfield.getText().concat("1"));
		} else if(e.getSource() == btn2) {
			inputTextfield.setText(inputTextfield.getText().concat("2"));
		} else if(e.getSource() == btn3) {
			inputTextfield.setText(inputTextfield.getText().concat("3"));
		} else if(e.getSource() == btn4) {
			inputTextfield.setText(inputTextfield.getText().concat("4"));
		} else if(e.getSource() == btn5) {
			inputTextfield.setText(inputTextfield.getText().concat("5"));
		} else if(e.getSource() == btn6) {
			inputTextfield.setText(inputTextfield.getText().concat("6"));
		} else if(e.getSource() == btn7) {
			inputTextfield.setText(inputTextfield.getText().concat("7"));
		} else if(e.getSource() == btn8) {
			inputTextfield.setText(inputTextfield.getText().concat("8"));
		} else if(e.getSource() == btn9) {
			inputTextfield.setText(inputTextfield.getText().concat("9"));
		} else if(e.getSource() == btn0) {
			inputTextfield.setText(inputTextfield.getText().concat("0"));
		} else if(e.getSource() == btnPlus) {
			inputTextfield.setText(inputTextfield.getText().concat("+"));
		} else if(e.getSource() == btnMinus) {
			inputTextfield.setText(inputTextfield.getText().concat("-"));
		} else if(e.getSource() == btnAsterisk) {
			inputTextfield.setText(inputTextfield.getText().concat("*"));
		} else if(e.getSource() == btnForwardSlash) {
			inputTextfield.setText(inputTextfield.getText().concat("/"));
		} else if(e.getSource() == btnCaret) {
			inputTextfield.setText(inputTextfield.getText().concat("^"));
		} else if(e.getSource() == btnSqrRoot) {
			inputTextfield.setText(inputTextfield.getText().concat("sqrt("));
		} else if(e.getSource() == btnSin) {
			inputTextfield.setText(inputTextfield.getText().concat("sin("));
		} else if(e.getSource() == btnCos) {
			inputTextfield.setText(inputTextfield.getText().concat("cos("));
		} else if(e.getSource() == btnTan) {
			inputTextfield.setText(inputTextfield.getText().concat("tan("));
        } else if(e.getSource() == btnPi) {
		    inputTextfield.setText(inputTextfield.getText().concat("pi"));
        } else if(e.getSource() == btne) {
		    inputTextfield.setText(inputTextfield.getText().concat("e"));
        } else if(e.getSource() == btnNaturalLog) {
		    inputTextfield.setText(inputTextfield.getText().concat("ln("));
		} else if(e.getSource() == btnDot) {
            inputTextfield.setText(inputTextfield.getText().concat("."));
		} else if(e.getSource() == btnParaStart) {
            inputTextfield.setText(inputTextfield.getText().concat("("));
        } else if(e.getSource() == btnParaEnd) {
            inputTextfield.setText(inputTextfield.getText().concat(")"));
        } else if(e.getSource() == btnEnter) {
        try { 
        	Parser parse = new Parser(inputTextfield.getText(), prevAns);
		    expr = parse.handleTrigFunctions();
        } catch(Exception ex) {
            outputTextfield.setText("Error: Invalid Expression or Infinity");
        }
        Infix2PostFix pc = new Infix2PostFix(expr); 
        PostFixEvaluator calc = new PostFixEvaluator(pc.getPostfixList());
        try {
        	calc.result();
        } catch(Exception ex) {
        	outputTextfield.setText("Error: Invalid or Divide by Zero");
        	JOptionPane.showMessageDialog(null, "Error: Invalid or Divide by Zero");
        }
        prevAns = calc.result();
        outputTextfield.setText(String.valueOf(prevAns));
		} else if(e.getSource() == btnAnswer) {
			inputTextfield.setText(inputTextfield.getText()+"ANSWER");
		} else if(e.getSource()==btnClear) {
			Graphics g = graphDisplayPanel.getGraphics();
			g.clearRect(0, 0, 600, 600);
			int x = Integer.valueOf(this.xField.getText());
			int y = Integer.valueOf(this.yField.getText());
			drawGrid(g,x,y);
			inputTextfield.setText("");
            outputTextfield.setText("");
        }
	}
	
	public void drawGrid(Graphics g, int xSize, int ySize) {
		// Draw Vertical tic lines
				int j;
				int k;
				g.setColor(Color.gray);
				for(int i=0;i<300/ySize;i++) {
					
				}
				
				// Vertical Line
				g.setColor(Color.RED);
				g.drawLine(300, 0, 300, 600);
				
				// Horizontal Line
				g.drawLine(0,300, 600, 300);
				g.drawString("X Axis", 1, 299);
				g.drawString("Y Axis", 301, 10);
	}
}
