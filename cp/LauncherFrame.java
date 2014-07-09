package cp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LauncherFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2793240863460599905L;
	//
	private JLabel labelInput, labelOutput;
	private JTextField mUserInput;
	private JPanel panelInput, panelOutput, panelButton;
	private JTextArea mOutput;
	private JScrollPane pane;
	private JButton btnCalc, btnClear, btnRandom;
	//
	private CastorschePaar paar = new CastorschePaar();
	private BtnListener listener = new BtnListener();
	private StringBuilder builder = new StringBuilder();
	//
	private int z = 0;
	private int randomZ = 0;
	private int n, x, y;	
	private int intV;
	private int calcX, calcY, calcN;
	private boolean checkX, checkY, checkN;

	public LauncherFrame(String titel) {
		super(titel);
		initGUI();
	}
	
	private void initGUI() {
		/*
		 * Panel Input
		 */
		panelInput = new JPanel(new BorderLayout(10, 10));
		//
		labelInput = new JLabel("Eingabe von Z:");
		mUserInput = new JTextField(20);
		mUserInput.addKeyListener(tfKeyListener);
		//
		panelInput.add(labelInput, BorderLayout.WEST);
		panelInput.add(mUserInput, BorderLayout.CENTER);
		/*
		 * Panel Output
		 */
		panelOutput = new JPanel(new BorderLayout(10, 10));
		//
		labelOutput = new JLabel("Ausgabe: ");
		mOutput = new JTextArea();
		pane = new JScrollPane(mOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(new Dimension(400, 300));
		//
		panelOutput.setPreferredSize(new Dimension(400, 300));
		panelOutput.add(labelOutput, BorderLayout.NORTH);
		panelOutput.add(pane, BorderLayout.CENTER);
		/*
		 * Panel Button
		 */
		panelButton = new JPanel(new BorderLayout(10, 10));
		//
		btnCalc = new JButton("Berechnung");
		btnCalc.setActionCommand("calc");
		btnCalc.addActionListener(listener);
		//
		btnClear = new JButton("Löschen");
		btnClear.setActionCommand("clear");
		btnClear.addActionListener(listener);
		//
		btnRandom = new JButton("Random Z");
		btnRandom.setActionCommand("random");
		btnRandom.addActionListener(listener);
		//
		panelButton.add(btnCalc, BorderLayout.NORTH);
		panelButton.add(btnClear, BorderLayout.CENTER);
		panelButton.add(btnRandom, BorderLayout.SOUTH);
		/*
		 * JFrame Settings
		 */
		setLayout(new BorderLayout(20, 20));
		//
		add(panelInput, BorderLayout.NORTH);
		add(panelOutput, BorderLayout.CENTER);
		add(panelButton, BorderLayout.SOUTH);
		//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setPreferredSize(new Dimension(400, 500));
		setResizable(false);
		setLocationByPlatform(true);
		setVisible(true);
	}
	
	/*
	 * Buttoneventlistener
	 */
	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("calc")) {
				z = getUserInput();
				//
				berechnung(z);
			}
			
			if (e.getActionCommand().equals("clear")) {
				mOutput.setText(" ");
				builder.delete(0, builder.length());
				randomZ = 0;
				z = 0;
				pack();
			}
			
			if (e.getActionCommand().equals("random")) {
				randomZ = randomZ();
				//
				berechnung(randomZ);
			}
		}
	}
	
	/*
	 * UserInput
	 */
	private int getUserInput() {
		int tmpZ = 0;
		//
		if (!mUserInput.getText().isEmpty()) {
			try {
				tmpZ = Integer.parseInt(mUserInput.getText().toString());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(LauncherFrame.this, "falscher Input Wert");
			}
		} else {
			JOptionPane.showMessageDialog(LauncherFrame.this, "fehlender Input");
		}
		//
		return tmpZ;
	}
	
	/*
	 * Random Z - Wert
	 */
	private int randomZ() {
		return (int) (Math.random() * 100000);
	}
	
	/*
	 * Enter Taste
	 */
	KeyListener tfKeyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            	z = getUserInput();
            	//
            	berechnung(z);
            }
        }
    };
	
    /*
     * alle Berechnungen
     */
	private void berechnung(int z) {
		//
		n = (int) paar.n(z);
		x = (int) paar.e(z);
		y = (int) paar.f(z);	
		intV = (int) paar.inverseZ(z);
		calcX = (int) paar.calcXvalue(x, y);
		calcY = (int) paar.calcYvalue(x, y);
		calcN = (int) paar.calcNvalue(z);
		checkX = paar.checkXvalue(x, calcX);
		checkY = paar.checkYvalue(y, calcY);
		checkN = paar.checkNvalue(n, calcN);
		//
		mOutput.setText(outputBuilder());
		//
		mUserInput.setText("");
		//
		pack();
	}
	
	/*
	 * Output Erzeugung
	 */
	private String outputBuilder() {
		//
		builder.append("Castorsche Paar:");
		builder.append("\n");
		if (z == 0) {
			builder.append("Gegebene Zahl: " + randomZ);
			builder.append("\n");
		} else {
			builder.append("Gegebene Zahl: " + z);
			builder.append("\n");
		}
		builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		builder.append("\n");
		builder.append("Berechneter n - Wert von z: \t" + n);
		builder.append("\n");
		builder.append("........................................");
		builder.append("\n");
		builder.append("Berechneter x - Wert von z: \t" + x);
		builder.append("\n");
		builder.append("Berechneter y - Wert von z: \t" + y);
		builder.append("\n");
		builder.append("........................................");
		builder.append("\n");
		builder.append("Berechnete Inverse von z: \t" + intV);
		builder.append("\n");
		builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		builder.append("\n");
		builder.append("Test x - Wert: \t" + checkX);
		builder.append("\n");
		builder.append("Test y - Wert: \t" + checkY);
		builder.append("\n");
		builder.append("Test z - Wert: \t" + checkN);
		builder.append("\n");
		builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		builder.append("\n");
		//
		return builder.toString();
	}
}