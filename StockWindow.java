package stock.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StockWindow extends JFrame implements ActionListener{

	String [] columnNames = {"no", "종목명","보유종목수","매입금액","현재금액",
			"총 매입금액","총 평가금액","수익률"};	
	
//	주식 현황판 => North
	DefaultTableModel model = new DefaultTableModel(columnNames,0);
	JTable table = new JTable(model);
	JScrollPane jScrollPane =new JScrollPane(table);
	
//	텍스트 영역 및 버튼들 => South
	JPanel inputPanel = new JPanel(new BorderLayout());
	
//		 0 => 종목명 , 1 => 주식 수, 2 => 구매가, 3 => 현재가
	JLabel [] labels = new JLabel[4];
	JPanel labelPanel = new JPanel(new GridLayout(4,1));
	
//		0 => 입력 , 1 => 보기, 2=> 수정, 3=> 삭제
	JButton [] buttons = new JButton[4];
	String [] buttonNames = {"입력","보기","수정","삭제"};
//		0 => 종목명 , 1 => 주식 수, 2 => 구매가, 3 => 현재가
	JTextField [] textFields = new JTextField[4];
	JPanel textPanel = new JPanel(new GridLayout(4,1));
	JPanel buttonPanel = new JPanel(new GridLayout(1,4));
	
	
	
	public StockWindow() {
		setTitle("My Asset");
		setBounds(150,100,850,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//	Layout
		
//		#1 North
		jScrollPane.setPreferredSize(new Dimension(850, 400));
		jScrollPane.setBackground(Color.black);
		add(jScrollPane, BorderLayout.NORTH);
		
		
//		#2 Center
		inputPanel.setPreferredSize(new Dimension(850, 100));
		labelPanel.setPreferredSize(new Dimension(100,200));
		textPanel.setPreferredSize(new Dimension(getSize().width - labelPanel.getSize().width,200));
		
//			textLabel 선언
		Font font = new Font("나눔고딕", Font.BOLD, 15);
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(columnNames[i+1]);
			labels[i].setBackground(Color.blue);
			labels[i].setForeground(Color.WHITE);
			labels[i].setOpaque(true);
			labels[i].setHorizontalAlignment(JTextField.CENTER);
			labels[i].setFont(font);
			labelPanel.add(labels[i]);
		}
		
//			textFidels 선언
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new JTextField();
			textPanel.add(textFields[i]);
		}
		
		inputPanel.add(textPanel, BorderLayout.CENTER);
		inputPanel.add(labelPanel , BorderLayout.WEST);
		
		add(inputPanel);
		
//		# South
		buttonPanel.setPreferredSize(new Dimension(850 , 50));
//			Buttons 선언
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			buttonPanel.add(buttons[i]);
		}
		add(buttonPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
