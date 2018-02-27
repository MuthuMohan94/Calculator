package calc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultEditorKit;

public class HistoryHandler implements MouseListener, ActionListener {
	
	JTextArea textArea;
	JButton loader;
	JButton graphButton;
	JButton eraseButton;
	JTextField inputTextField;
	Action selectLine;
	JTextField xField;
	JTextField yField;
	JPanel graphDisplayPanel;
	private LinkedList<String> eqList = new LinkedList();
	int xSize = 30;
	int ySize = 30;
	
	BigDecimal prevAns = ButtonHandler.getPrevAns();
	
	HistoryHandler(JTextArea textbox, JButton loadButton, JTextField textField, JButton graphbutton, JButton erasebutton, JTextField xRangeField, JTextField yRangeField, JPanel graphDisplayPanel) {
		this.loader = loadButton;
		this.textArea = textbox;
		this.inputTextField = textField;
		this.graphButton = graphbutton;
		this.eraseButton = erasebutton;
		selectLine = getAction(DefaultEditorKit.selectLineAction);
		this.xField = xRangeField;
		this.yField = yRangeField;
		this.graphDisplayPanel = graphDisplayPanel;
	}
	
	public void drawGrid(Graphics g, int xSize, int ySize) {
		// Draw Vertical tic lines
				int j;
				int k;
				g.setColor(Color.gray);
				int xIncrement = 250/ySize;
				int yIncrement = 250/xSize;
				
//				for(int i=0;i<=ySize;i++) {
//					j=(i*xIncrement)+50;
//					g.drawString(String.valueOf(Math.abs(10-i)), j, 299);
//					g.drawLine(j, 50, j, 550);
//				}
//				for(int i=0;i<=ySize;i++) {
//					j=(i*xIncrement)+300;
//					g.drawString(String.valueOf(i), j, 299);
//					g.drawLine(j, 50, j, 550);
//				}
//				for(int i=0;i<=xSize;i++) {
//					k=(i*yIncrement)+50;
//					g.drawString(String.valueOf(i), 299,k);
//					g.drawLine(50, k, 550, k);
//				}
//				for(int i=0;i<=xSize;i++) {
//					k=(i*yIncrement)+300;
//					g.drawString(String.valueOf(i), 299, k);
//					g.drawLine(50, k, 550, k);
//				}
//				
				// Vertical Line
				g.setColor(Color.RED);
				g.drawLine(300, 0, 300, 600);				
				// Horizontal Line
				g.drawLine(0,300, 600, 300);
				g.drawString("-"+String.valueOf(xSize), 1, 299);
				g.drawString(String.valueOf(xSize), 585, 299);
				g.drawString(String.valueOf(ySize), 301, 10);
				g.drawString("-"+String.valueOf(ySize), 301, 600);
	}
	
	private Action getAction(String name) {
		Action action = null;
		Action[] actions = textArea.getActions();
 
		for (int i = 0; i < actions.length; i++) {
			if (name.equals(actions[i].getValue(Action.NAME).toString())) {
				action = actions[i];
				break;
			}
		}
		return action;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ( SwingUtilities.isLeftMouseButton(e)  && e.getClickCount() == 1) {
            selectLine.actionPerformed(null);
        }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loader) {
			inputTextField.setText(textArea.getSelectedText());
		} else if(e.getSource() == graphButton) {
			Graphics g = graphDisplayPanel.getGraphics();
			g.clearRect(0, 0, 600, 600);
			drawGrid(g,Integer.valueOf(xField.getText()),Integer.valueOf(yField.getText()));
			Parser parse = new Parser(inputTextField.getText(), prevAns);
			this.xSize = Integer.valueOf(xField.getText());
			this.ySize = Integer.valueOf(yField.getText());
			int xScaled = 300/xSize;
			int yScaled = 300/ySize;
		    try {
				String expr = parse.handleTrigFunctions();
				String cloneExpr = parse.handleTrigFunctions();
				List<Integer> arrx = new ArrayList<Integer>();
				List<Integer> arry = new ArrayList<Integer>();
				for(int i=0; i<xSize+1;i++) {
					int j=i*xScaled;
					expr = expr.replaceAll("x", String.valueOf(i));
					Infix2PostFix pc = new Infix2PostFix(expr); 
			        PostFixEvaluator calc = new PostFixEvaluator(pc.getPostfixList());
			        BigDecimal y = calc.result();
			        int yValue = y.intValue()*yScaled;
			        arrx.add(j+300);
			        arry.add(300-yValue);
			        g.drawOval(j+300,(300-yValue), 1, 1);
			        expr = cloneExpr;
			        //System.out.println(expr);   
				}
				for(int i=0;i<xSize;i++) {
					g.drawLine(arrx.get(i), arry.get(i), arrx.get(i+1), arry.get(i+1));
				}
				
		        
		        
		        
		       
				for(int i=0; i<10;i++) {
					
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
			
			
			eqList.add(inputTextField.getText()+"\n");
			textArea.setText("");
			for(int i=0; i<eqList.size(); i++) {
					textArea.append(eqList.get(i));
			}
		} else if(e.getSource() == eraseButton) {
			String searchEq = textArea.getSelectedText();
			LinkedList<String> equationFinalList = new LinkedList<String>();
			textArea.setText("");
			for(int i=0; i<eqList.size(); i++) {
				if(eqList.get(i).trim().equals(searchEq)) {
					continue;
				} else {
					equationFinalList.add(eqList.get(i));
				}
			}
			eqList=equationFinalList;
			for(int i=0; i<eqList.size(); i++) {
				textArea.append(eqList.get(i));
			}
		}
		
	}

}
