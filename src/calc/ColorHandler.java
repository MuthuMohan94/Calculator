package calc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ColorHandler implements ActionListener {
	JComboBox colorBox;
	JTextField inputTextField;
	
	ColorHandler(JComboBox colorBox, JTextField inputField) {
		this.colorBox = colorBox;
		this.inputTextField = inputField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String color = colorBox.getSelectedItem().toString();
		switch(color) {
			case "BLACK": inputTextField.setForeground(Color.BLACK);
					  	  break;
			case "BLUE": inputTextField.setForeground(Color.BLUE);
		  			  	 break;
			case "RED": inputTextField.setForeground(Color.RED);
					    break;
			case "GREEN": inputTextField.setForeground(Color.GREEN);
					      break;
		}		
	}
}
