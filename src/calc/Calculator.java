package calc;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.*; 
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Calculator extends JPanel {

	JTextField inputTextfield = new JTextField();
	
	JPanel mainCalc = new JPanel();
	JPanel graphPanel = new JPanel();
	JPanel buttonArray = new JPanel();
	JPanel answerArray = new JPanel();
	JPanel outputPanel = new JPanel();
	JPanel inputPanel = new JPanel();

	JButton btn1 = new JButton("1");
	JButton btn2 = new JButton("2");
	JButton btn3 = new JButton("3");
	JButton btn4 = new JButton("4");
	JButton btn5 = new JButton("5");
	JButton btn6 = new JButton("6");
	JButton btn7 = new JButton("7");
	JButton btn8 = new JButton("8");
	JButton btn9 = new JButton("9");
	JButton btn0 = new JButton("0");
	JButton btnDot = new JButton(".");
	JButton btnClear = new JButton("C");
	JButton btnPlus = new JButton("+");
	JButton btnCaret = new JButton("^");
	JButton btnSin = new JButton("sin");
	JButton btnMinus = new JButton("-");
	JButton btnSqrRoot = new JButton("sqrt");
	JButton btnCos = new JButton("cos");
	JButton btnForwardSlash = new JButton("/");
	JButton btne = new JButton("e");
	JButton btnTan = new JButton("tan");
	JButton btnAsterisk = new JButton("*");
	JButton btnPi = new JButton("pi");
	JButton btnNaturalLog = new JButton("ln");
	JButton btnAnswer = new JButton("Answer");
	JButton btnEnter = new JButton("Enter");
	JButton btnParaStart = new JButton("(");
	JButton btnParaEnd = new JButton(")");
	
	JLabel colorSelectLabel = new JLabel("Select Color:-");
	JLabel lblOutput = new JLabel("OUTPUT:   ");

	Graphics g;
	
	JLabel lblHistory = new JLabel("History:-");
	JButton loadButton = new JButton("LOAD");
	JScrollPane scrollPane = new JScrollPane();

	JComboBox colorComboBox = new JComboBox();

	JButton graphButton = new JButton("PLOT");
	JButton eraseButton = new JButton("ERASE");

	JTextArea equationBox = new JTextArea();
	JFrame frame = new JFrame();

	JTextField outputTextfield = new JTextField();
	JLabel lblYfx = new JLabel("INPUT: y=f(x):");
	JLabel lblNewLabel = new JLabel("X Axis Range:");
	JLabel lblYAxisRange = new JLabel("Y Axis Range:");
	JTextField xRangeField = new JTextField();
	JTextField yRangeField = new JTextField();
	JPanel graphDisplayPanel = new Grapher();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 1024, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainCalc, BorderLayout.CENTER);
		
		// Creating all handlers
		HistoryHandler historyHandler = new HistoryHandler(equationBox, loadButton, inputTextfield, graphButton, eraseButton, xRangeField, yRangeField, graphDisplayPanel);
		ColorHandler colorHandler = new ColorHandler(colorComboBox, inputTextfield);
		ButtonHandler buttonHandler = new ButtonHandler(inputTextfield, outputTextfield, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnClear, 
				btnPlus, btnCaret, btnSin, btnMinus, btnSqrRoot, btnCos, btnForwardSlash, btne, btnTan, btnAsterisk, btnPi, btnNaturalLog, btnAnswer, btnEnter, btnParaStart, btnParaEnd, 
				xRangeField, yRangeField, graphDisplayPanel);
		
		// Creating all Panels
		createGraphPanel();
		createButtonArray(buttonHandler);
		addComponentsToMainPanel();
		
		// Creates the graphics to draw the grid on the graph panel
		g = graphDisplayPanel.getGraphics();
		
		graphButton.addActionListener(historyHandler);
		loadButton.addActionListener(historyHandler);
		eraseButton.addActionListener(historyHandler);
		
		
		
		// Create components for main calc
		colorSelectLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		colorSelectLabel.setBounds(724, 106, 78, 15);
		colorComboBox.setBounds(812, 104, 102, 20);
		colorComboBox.addActionListener(colorHandler);
		colorComboBox.setModel(new DefaultComboBoxModel(new String[] {"BLACK", "BLUE", "RED", "GREEN"}));
		
		eraseButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		eraseButton.setBounds(729, 129, 73, 32);
		graphButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		graphButton.setBounds(812, 129, 73, 32);
		loadButton.setBounds(637, 331, 123, 27);
		scrollPane.setBounds(637, 185, 361, 150);
		scrollPane.setViewportView(equationBox);
		equationBox.setEditable(false);
		equationBox.addMouseListener(historyHandler);
		lblHistory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHistory.setBounds(637, 154, 73, 20);
		answerArray.setBounds(637, 561, 361, 50);
		answerArray.setLayout(new GridLayout(2, 2, 5, 5));
		outputPanel.setBounds(636, 369, 362, 32);
		outputTextfield.setColumns(25);
		outputTextfield.setBackground(Color.WHITE);
		outputTextfield.setEditable(false);
		inputPanel.setBounds(637, 11, 361, 89);
		inputPanel.setLayout(null);
		// Create and add components for input panel
		lblYfx.setBounds(36, 14, 73, 14);
		inputTextfield.setBounds(115, 11, 206, 20);
		inputTextfield.setColumns(25);
		lblNewLabel.setBounds(36, 39, 73, 14);
		lblYAxisRange.setBounds(36, 64, 73, 14);
		xRangeField.setColumns(25);
		xRangeField.setBounds(115, 36, 206, 20);
		yRangeField.setColumns(25);
		yRangeField.setBounds(115, 61, 206, 20);
		
	}


	private void addComponentsToMainPanel() {
		mainCalc.setLayout(null);
		mainCalc.add(graphPanel);
		mainCalc.add(colorSelectLabel);
		mainCalc.add(colorComboBox);
		mainCalc.add(eraseButton);
		mainCalc.add(graphButton);
		mainCalc.add(scrollPane);
		mainCalc.add(loadButton);
		mainCalc.add(lblHistory);
		mainCalc.add(buttonArray);
		mainCalc.add(answerArray);
		mainCalc.add(outputPanel);
		mainCalc.add(inputPanel);
		answerArray.add(btnParaEnd);
		answerArray.add(btnParaStart);
		answerArray.add(btnAnswer);
		answerArray.add(btnEnter);
		outputPanel.add(lblOutput);
		outputPanel.add(outputTextfield);
		inputPanel.add(lblYfx);
		inputPanel.add(inputTextfield);
		inputPanel.add(lblNewLabel);
		inputPanel.add(lblYAxisRange);
		inputPanel.add(xRangeField);
		inputPanel.add(yRangeField);
	}

	private void createButtonArray(ButtonHandler buttonHandler) {
		buttonArray.setBounds(637, 412, 361, 138);
		buttonArray.setLayout(new GridLayout(4, 6, 2, 10));
		
		
		// Adding buttons to button panel
		buttonArray.add(btn9);
		buttonArray.add(btn8);
		buttonArray.add(btn7);
		buttonArray.add(btnPlus);
		buttonArray.add(btnCaret);
		buttonArray.add(btnSin);
		buttonArray.add(btn6);
		buttonArray.add(btn5);
		buttonArray.add(btn4);
		buttonArray.add(btnMinus);
		buttonArray.add(btnSqrRoot);
		buttonArray.add(btnCos);
		buttonArray.add(btn3);
		buttonArray.add(btn2);
		buttonArray.add(btn1);
		buttonArray.add(btnForwardSlash);
		buttonArray.add(btne);
		buttonArray.add(btnTan);
		buttonArray.add(btnDot);
		buttonArray.add(btn0);
		buttonArray.add(btnClear);
		buttonArray.add(btnAsterisk);
		buttonArray.add(btnPi);
		buttonArray.add(btnNaturalLog);
		
		// Adding handlers for buttons
		btn9.addActionListener(buttonHandler);
		btn8.addActionListener(buttonHandler);
		btn7.addActionListener(buttonHandler);
		btn5.addActionListener(buttonHandler);
		btnPlus.addActionListener(buttonHandler);
		btnCaret.addActionListener(buttonHandler);
		btnSin.addActionListener(buttonHandler);
		btn6.addActionListener(buttonHandler);
		btn4.addActionListener(buttonHandler);
		btn2.addActionListener(buttonHandler);
		btn3.addActionListener(buttonHandler);
		btnMinus.addActionListener(buttonHandler);
		btnSqrRoot.addActionListener(buttonHandler);
		btnCos.addActionListener(buttonHandler);
		btn1.addActionListener(buttonHandler);
		btnForwardSlash.addActionListener(buttonHandler);
		btne.addActionListener(buttonHandler);
		btnTan.addActionListener(buttonHandler);
		btnDot.addActionListener(buttonHandler);
		btn0.addActionListener(buttonHandler);
		btnClear.addActionListener(buttonHandler);
		btnAsterisk.addActionListener(buttonHandler);
		btnPi.addActionListener(buttonHandler);
		btnNaturalLog.addActionListener(buttonHandler);
		btnEnter.addActionListener(buttonHandler);
		btnAnswer.addActionListener(buttonHandler);
		btnParaStart.addActionListener(buttonHandler);
		btnParaEnd.addActionListener(buttonHandler);
		
		// Setting color for buttons
		btnTan.setBackground(Color.ORANGE);
		btnTan.setForeground(Color.WHITE);
		btnCos.setBackground(Color.ORANGE);
		btnCos.setForeground(Color.WHITE);
		btnSin.setBackground(Color.ORANGE);
		btnSin.setForeground(Color.WHITE);
		btnNaturalLog.setBackground(Color.ORANGE);
		btnNaturalLog.setForeground(Color.WHITE);
	}
	
	protected void createGraphPanel() {
		graphPanel.setLayout(null);
		graphPanel.setBackground(Color.white);
		graphPanel.setBounds(5, 5, 600, 600);
		graphPanel.setVisible(true);
		addToGraphPanel();
	}
	
	protected void addToGraphPanel() {
		// Creates a new panel to draw the graph in and adds it to the graphPanel
		graphDisplayPanel.setVisible(true);
		graphDisplayPanel.setBackground(Color.WHITE);
		graphDisplayPanel.setLayout(null);
		graphDisplayPanel.setBounds(0, 0, 600, 600);
		graphPanel.add(graphDisplayPanel);
	}
}
