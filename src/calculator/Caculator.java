package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Caculator extends JFrame{
	// 글자 및 컴포넌트에 사용하는 색상
	private static Color gray = new Color(240, 240, 240);
	private static Color teal = new Color(74, 170, 169);
	private static Color deepGray = new Color(94, 94, 94);
	private static Color lightGray = new Color(200, 200, 200);
	private static Color orange = new Color(252, 165, 60);
	
	// 주로 사용하는 폰트 설정
	private static Font defaultFont = new Font("Arial", Font.BOLD, 30);
	private static Font answerFont = new Font("Arial", Font.PLAIN, 18);
	
	// 내부 클래스에서 접근할 수 있는 컴포넌트
	private static Container contentPane;
	private static JTextArea text;
	private static JTextField answer;
	
	private Caculator() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		contentPane.addKeyListener(new MyKeyListener());
		
		// 계산식 입력란
		text = new JTextArea();
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
		answer = new JTextField("answer");
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
		
		String[] buttonLabels = {"C", "(", ")", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "<-", "0", ".", "="};
		int len = buttonLabels.length;
		JButton[] buttons = new JButton[len];
		
		for(int i=0; i<len; i++) {
			buttons[i] = new JButton(buttonLabels[i]);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setFont(defaultFont);
			buttons[i].setForeground(deepGray);
			buttons[i].setBorderPainted(false);
			buttons[i].addActionListener(new MyActionListener());
			
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
		
		contentPane.setFocusable(true);
		contentPane.requestFocus();
	}
	
	// 마지막 글자 검사
	private int checkInputString(String msg, char btn) {
		// 부호가 입력된 경우
		if(btn == '+' || btn == '-' || btn == '/' || btn == '*') {
			// 첫 문자가 부호인 경우
			if(msg.length() == 0) 
				return -1;
			
			char last = msg.charAt(msg.length() - 1);

			// 연속으로 부호가 입력되는 경우
			if(last == '+' || last == '-' || last == '/' || btn == '*') {
				// 부호가 같은 경우
				if(last == btn) 
					return -1;
				
				// 부호가 다른 경우
				return 0;
			}
		}
		
		return 1;
	}
	
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			String pressedBtn = btn.getText();
			
			String msg = text.getText();
			
			// 0 : 부호만 바뀜. 추가되지 않음
			// 1 : 입력된 텍스트 추가
			// -1 : 텍스트에 변화 없음
			int input = checkInputString(msg, pressedBtn.charAt(0));
			
			if(input == 0) {
				text.setText(msg.substring(0, msg.length() - 1) + pressedBtn);
			}else if(input == 1) {
				if(pressedBtn == "=") {
					// 계산 요청
				}else if(pressedBtn == "C"){ //전체 삭제
					text.setText("");
				}else if(pressedBtn == "<-") { //마지막 문자 삭제
					msg = text.getText();
					int len = msg.length();
					
					// IndexOutOfBoundsException 방지
					if(len > 0)
						text.setText(msg.substring(0, len - 1));
					
				}else {
					msg = text.getText();
					text.setText(msg + pressedBtn);
				}
			}
			
			// 버튼에 맞춰진 focus를 contentPane으로 돌리기
			// 마우스 클릭과 키 입력 자유롭게 이동 가능
			contentPane.requestFocus();
		}
	}
	
	private class MyKeyListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			char keyChar = e.getKeyChar();
			int keyCode = e.getKeyCode();
			
			String msg = text.getText();
			
			// 0 : 부호만 바뀜. 추가되지 않음
			// 1 : 입력된 텍스트 추가
			// -1 : 텍스트에 변화 없음
			int input = checkInputString(msg, keyChar);
			
			if(input == 0) {
					text.setText(msg.substring(0, msg.length() - 1) + keyChar);
			}else if(input == 1) {
				// 키 입력에 따른 처리 
				// 8 = backspace, 10 = enter
				if((keyChar >= '0' && keyChar <='9') || keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/' || keyChar == '(' || keyChar == ')') {
					msg = text.getText() + keyChar;
					text.setText(msg);
				}else if(keyCode == 8){
					msg = text.getText();
					int len = msg.length();
					
					// IndexOutOfBoundsException 방지
					if(len > 0)
						text.setText(msg.substring(0, len - 1));
				}else if(keyCode == 10) {
					// 계산 요청
				}
			}
		}
	}

	public static void main(String[] args) {
		new Caculator();
	}

}
