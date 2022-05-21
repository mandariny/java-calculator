package calculator;

import javax.swing.*;
import java.awt.*;

public class Caculator extends JFrame{
	private static Color gray = new Color(240, 240, 240);
	private static Color teal = new Color(74, 170, 169);
	private static Color deepGray = new Color(94, 94, 94);
	private static Color orange = new Color(252, 165, 60);
	private static Font defaultFont = new Font("Arial", Font.BOLD, 30);
	
	public Caculator() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JTextArea text = new JTextArea(3, 0);
		text.setSize(400, 300);
		text.setBackground(gray);
		text.setFont(defaultFont);
		text.setForeground(deepGray);
		text.setLineWrap(true);
		text.setRows(3);
		text.setMaximumSize(new Dimension(4, 2));
		text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		// (0,0)칸에 가중치 1:1로 삽입
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		contentPane.add(text, gbc);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
		buttonPanel.setBackground(gray);
		buttonPanel.setSize(400, 500);
		
		// 넘버 패드 
		String[] buttonLabels = {"C", "(", ")", "%", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
		int len = buttonLabels.length;
		JButton[] buttons = new JButton[len];
		
		for(int i=0; i<len; i++) {
			buttons[i] = new JButton(buttonLabels[i]);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setFont(defaultFont);
			buttons[i].setForeground(deepGray);
			buttons[i].setBorderPainted(false);
			
			if(i == len - 1) {
				buttons[i].setBackground(teal);
				buttons[i].setForeground(Color.WHITE);
			}else if(i == 0) {
				buttons[i].setForeground(orange);
			}
			
			buttonPanel.add(buttons[i]);
		}
		
		// (1,0)칸에 가중치 1:1로 삽입
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		contentPane.add(buttonPanel, gbc);
		
		setSize(400, 700);
		setLocation(700, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Caculator();
	}

}
