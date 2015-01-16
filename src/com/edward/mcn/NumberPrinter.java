package com.edward.mcn;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.edward.mcn.util.Conversion;

public class NumberPrinter {
	private JFrame frmNumberPrinter;
	private JLabel lblTop;
	private JLabel lblDisplay;
	private JFormattedTextField txtNumber;
	private JButton btnConvertToEnglish;
	private JButton btnConvertToRoman;
	private JPanel panel;
	private JPanel pnlButtons;
	private JPanel pnlText;
	private JPanel panel_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumberPrinter window = new NumberPrinter();
					window.frmNumberPrinter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NumberPrinter() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		this.frmNumberPrinter = new JFrame();
		this.frmNumberPrinter.setTitle("NUMBER PRINTER");
		this.frmNumberPrinter.setResizable(false);
		this.frmNumberPrinter.setBounds(100, 100, 450, 220);
		this.frmNumberPrinter.setDefaultCloseOperation(3);

		JPanel gridPanel = new JPanel();
		gridPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		this.frmNumberPrinter.getContentPane().add(gridPanel, "Center");
		gridPanel.setLayout(new GridLayout(4, 1, 0, 0));

		this.pnlText = new JPanel();
		gridPanel.add(this.pnlText);

		this.txtNumber = new JFormattedTextField(NumberFormat.getNumberInstance());
		this.txtNumber.setHorizontalAlignment(0);
		this.pnlText.add(this.txtNumber);
		this.txtNumber.setColumns(5);

		this.pnlButtons = new JPanel();
		this.pnlButtons.setBorder(new EmptyBorder(0, 70, 0, 70));
		gridPanel.add(this.pnlButtons);
		this.pnlButtons.setLayout(new GridLayout(1, 2, 10, 20));

		this.btnConvertToEnglish = new JButton("ENGLISH");
		this.btnConvertToEnglish.setFont(new Font("Calibri Light", 0, 12));
		this.pnlButtons.add(this.btnConvertToEnglish);

		this.btnConvertToRoman = new JButton("ROMAN NUMERAL");
		this.btnConvertToRoman.setFont(new Font("Calibri Light", 0, 12));
		this.pnlButtons.add(this.btnConvertToRoman);
		this.btnConvertToRoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number = NumberPrinter.this.validate(NumberPrinter.this.txtNumber.getText());
				if (number == 0) {
					return;
				}
				String romanNumeral = Conversion.convertToRomanNumeral(number);
				if (romanNumeral.contains("THAT")) {
					NumberPrinter.this.lblDisplay.setForeground(Color.RED);
				}
				NumberPrinter.this.lblDisplay.setText(romanNumeral);
			}
		});
		this.btnConvertToEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number = NumberPrinter.this.validate(NumberPrinter.this.txtNumber.getText());
				if (number == 0) {
					return;
				}
				String englishWord = Conversion.convertToEnglish(number);
				if (englishWord.contains("THAT")) {
					NumberPrinter.this.lblDisplay.setForeground(Color.RED);
				}
				NumberPrinter.this.lblDisplay.setText(englishWord);
			}
		});
		this.lblDisplay = new JLabel();
		this.lblDisplay.setFont(new Font("Calibri Light", 0, 18));
		this.lblDisplay.setHorizontalAlignment(0);
		gridPanel.add(this.lblDisplay);

		this.panel_1 = new JPanel();
		gridPanel.add(this.panel_1);

		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(10, 0, 0, 0));
		this.frmNumberPrinter.getContentPane().add(this.panel, "North");

		this.lblTop = new JLabel("ENTER A NUMBER TO CONVERT!");
		this.lblTop.setFont(new Font("Calibri Light", 0, 14));
		this.panel.add(this.lblTop);
		this.lblTop.setHorizontalAlignment(0);
	}

	private int validate(Object text) {
		if ((text == null) || (text.toString().equals(""))) {
			this.lblDisplay.setForeground(Color.RED);
			this.lblDisplay.setText("HEY! YOU NEED TO ENTER A NUMBER...");
			return 0;
		}
		this.lblDisplay.setForeground(Color.BLACK);
		return ((Number) this.txtNumber.getValue()).intValue();
	}
}
