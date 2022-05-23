package view;

import javax.swing.*;
import java.awt.*;

public class Caculator extends JFrame{
	private static Color gray = new Color(240, 240, 240);
	private static Color teal = new Color(74, 170, 169);
	private static Color deepGray = new Color(94, 94, 94);
	private static Color lightGray = new Color(200, 200, 200);
	private static Color orange = new Color(252, 165, 60);
	private static Font defaultFont = new Font("Arial", Font.BOLD, 30);
	private static Font answerFont = new Font("Arial", Font.PLAIN, 18);
	
	public Caculator() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// 계산식 입력란
		JTextArea text = new JTextArea("1234+123141");
		text.setBackground(gray);
		text.setFont(defaultFont);
		text.setForeground(deepGray);
		text.setLineWrap(true);
		text.setRows(3);
		text.setAlignmentY(RIGHT_ALIGNMENT);
		text.setEditable(false);

		// (0,0)칸에 가중치 1:1로 삽입
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		contentPane.add(text, gbc);
		
		// 중간 답 출력란
		JTextField answer = new JTextField("answer");
		answer.setBackground(gray);
		answer.setFont(answerFont);
		answer.setForeground(lightGray);
		answer.setBorder(null);
		answer.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		answer.setEditable(false);
		
		// (1,0)칸에 가중치 1:0.2로 삽입
		gbc.weightx = 1.0;
		gbc.weighty = 0.2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 10);
		
		contentPane.add(answer, gbc);
		
		// 넘버 패드 
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
		buttonPanel.setBackground(gray);
		buttonPanel.setSize(400, 500);
		
		String[] buttonLabels = {"C", "(", ")", "/", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
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
		
		// (2,0)칸에 가중치 1:1로 삽입
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 10, 10);
		
		contentPane.add(buttonPanel, gbc);
		
		setSize(400, 700);
		setLocation(700, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Caculator();
	}

}
