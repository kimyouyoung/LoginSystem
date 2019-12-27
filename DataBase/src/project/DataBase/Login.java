package project.DataBase;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Login extends JFrame{
	
	JFrame root;
	JTable userInfo;
	JScrollPane sc;
	JButton b_back;
	JButton b_modify;
	JButton b_make;
	JButton b_search;
	JButton b_all;
	JTextField text_search;
	JButton b_delete;
	JButton b_save;
	JPanel root_panel;
	JComboBox<String> search;
	String type = null;
	String b_id;
	
	DefaultTableModel dt;
	
	String[][] data;
	
	JPanel panel_1;
	JPanel panel_2;
	JPanel panel_3;
	JPanel panel_4;
	
	JLabel label;
	JLabel label_2;
	JLabel label_3;
	JLabel hello;
	JLabel help;
	
	JLabel nolabel;
	JLabel p_label;
	JLabel i_label;
	
	JLabel id_label;
	JLabel pw_label;
	
	JTextField id;
	JPasswordField pw;
	
	JTextField add;
	
	JButton btn3 = new JButton();
	JButton btn4;
	JButton back;
	JButton logout;
	JButton out;
	JButton manage;
	JButton modify;
	
	JComboBox<String> month;
	JComboBox<String> email;
	JComboBox<String> hp_f;
	JComboBox<String> major;
	
	JLabel[] labels = new JLabel[9];
	JLabel[] ess = new JLabel[9];
	
	JTextField[] new_text = new JTextField[5];
	JPasswordField ppw = new JPasswordField(20);
	JPasswordField re_pw = new JPasswordField(20);
	JTextField date = new JTextField();
	
	
	JRadioButton man = new JRadioButton("남", false);
	JRadioButton woman = new JRadioButton("여", false);
	
	int overlap = 0;
	int pass_check = 0;
	int essential = 0;
	
	int error;
	String pass = "";
	String re = "";
	int save_id = 0;
	int row;
	
	String p_name;
	String pp_id;
	String pp_pw;
	String p_birth;
	int p_gender;
	String p_major;
	String p_email = null;
	String p_hp;
	
	
	public Login() {
		
		this.setSize(800, 900);
		this.setTitle("Login System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setLayout(null);
		
		label = new JLabel("YOUNG");
		label.setBounds(225, 0, 350, 310);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Britannic", Font.BOLD, 70));
		label.setForeground(new Color(71, 200, 62));
		panel_1.add(label);
		
		id_label = new JLabel("아이디");
		id_label.setBounds(140, 250, 40, 60);
		id_label.setHorizontalAlignment(JLabel.CENTER);
		id_label.setFont(new Font("Britannic", Font.BOLD, 14));
		id_label.setForeground(Color.DARK_GRAY);
		panel_1.add(id_label);
		
		pw_label = new JLabel("비밀번호");
		pw_label.setBounds(130, 350, 60, 60);
		pw_label.setHorizontalAlignment(JLabel.CENTER);
		pw_label.setFont(new Font("Britannic", Font.BOLD, 14));
		pw_label.setForeground(Color.DARK_GRAY);
		panel_1.add(pw_label);
		
		Font font = new Font("Britannic", Font.PLAIN, 17);
		
		
		JButton btn = new JButton("로그인");
		btn.setBounds(200, 470, 400, 80);
		btn.setBackground(new Color(240, 240, 240));
		btn.setFont(font);
		panel_1.add(btn);
		
		JButton btn2 = new JButton("회원가입");
		btn2.setBounds(470, 560, 120, 35);
		btn2.setBackground(new Color(240, 240, 240));
		btn2.setFont(new Font("Britannic", Font.PLAIN, 14));
		panel_1.add(btn2);
		
		id = new JTextField();
		id.setBounds(200, 250, 400, 60);
		panel_1.add(id);
		
		pw = new JPasswordField();
		pw.setBounds(200, 350, 400, 60);
		panel_1.add(pw);
		
		Checkbox cb = new Checkbox("아이디 저장");
		cb.setForeground(Color.black);
		cb.setBounds(190, 550, 100, 40);
		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					save_id = 1;
				else 
					save_id = 0;
				
			}
			
		});
		panel_1.add(cb);
		
		this.add(panel_1);
		
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("로그인")) {
					System.out.println("SUCCESS");
					String p_id = id.getText();
					String p_pw = new String(pw.getPassword());
					
					ReadData readData = new ReadData();
					error = readData.read(p_id, p_pw);
					if(error == 1) {
						JOptionPane.showMessageDialog(null, "가입하지 않은 아이디이거나, 잘못된 비밀번호 입니다.","ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(p_id.equals("root")) {
						root= new JFrame("MANAGEMENT");
						root.setSize(1400, 600);
						root.setDefaultCloseOperation(EXIT_ON_CLOSE);
						root.add(mangement());
						root.setVisible(true);
						
					}
					else {
						getContentPane().removeAll();
						getContentPane().add(Login_Success(p_id));
						revalidate();
						repaint();
					}
				}
			}
		});
		
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(Join());
				revalidate();
				repaint();
			}
			
		});
		
		this.setVisible(true);
		
	}
	public JPanel mangement() {
		
		root_panel = new JPanel();
		root_panel.setBackground(new Color(240, 240, 240));
		root_panel.setLayout(null);
		
		b_back = new JButton("뒤로가기");
		b_back.setBounds(50, 530, 100, 30);
		root_panel.add(b_back);
		b_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				root.setVisible(false);
				id.setText("");
				pw.setText("");
			}
			
		});
		
		b_all = new JButton("전체보기");
		b_all.setBounds(420, 530, 100, 30);
		root_panel.add(b_all);
		b_all.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ReadData read = new ReadData();
				Vector<String[]> all = read.read_Alldata();
				String[][] data = new String[all.size()][9];
				
				String[] table_name = {"NUM", "NAME", "ID", "PASSWORD", "BIRTH", "GENDER", "MAJOR", "EMAIL", "HP"};
				DefaultTableModel dt_2 = new DefaultTableModel(table_name, 0);
				
				
				
				for(int i = 0; i < all.size(); i++) {
					data[i] = all.elementAt(i);
					dt_2.addRow(new Object[] {data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7], data[i][8]});
				}
				
				userInfo.setModel(dt_2);
				
			}
			
		});
		
		b_save = new JButton("저장");
		b_save.setBounds(550, 530, 100, 30);
		root_panel.add(b_save);
		b_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)userInfo.getModel();
                int row = userInfo.getSelectedRow();
                if(row<0) return;  
				String id = (String) model.getValueAt(row, 2);
				int col = userInfo.getSelectedColumn();
				String data = (String) model.getValueAt(row, col);
				WriteData modify = new WriteData();
				modify.modify_part(id, col, data);
				System.out.println(data);
			}
			
		});
		
		
		

		String[] list = {"Num", "Name", "ID", "Password", "Birth", "Gender", "Major", "Email", "Hp"};
		search = new JComboBox<String>();
		search = new JComboBox<String>(list);
		search.setBounds(680, 520, 100, 50);
		search.setSelectedIndex(2);
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = (String)search.getSelectedItem();
			}
		});
		
		root_panel.add(search);
		
		text_search = new JTextField();
		text_search.setBounds(780, 530, 170, 30);
		root_panel.add(text_search);
		
		b_search = new JButton("검색");
		b_search.setBounds(950, 530, 100, 30);
		root_panel.add(b_search);
		b_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				type = (String)search.getSelectedItem();
				int data_num = 2;
				
				if(type.equals("Num")) {
					data_num = 0;
				}else if(type.equals("Name")) {
					data_num = 1;
				}else if(type.equals("ID")) {
					data_num = 2;
				}else if(type.equals("Password")) {
					data_num = 3;
				}else if(type.equals("Birth")) {
					data_num = 4;
				}else if(type.equals("Gender")) {
					data_num = 5;
				}else if(type.equals("Major")) {
					data_num = 6;
				}else if(type.equals("Email")) {
					data_num = 7;
				}else if(type.equals("Hp")) {
					data_num = 8;
				}
				String data_con = text_search.getText();
				
				ReadData read = new ReadData();
				Vector<String[]> all = read.return_part(data_num, data_con);
				String[][] data = new String[all.size()][9];
				
				String[] table_name = {"NUM", "NAME", "ID", "PASSWORD", "BIRTH", "GENDER", "MAJOR", "EMAIL", "HP"};
				DefaultTableModel dt_2 = new DefaultTableModel(table_name, 0);
				
				
				
				for(int i = 0; i < all.size(); i++) {
					data[i] = all.elementAt(i);
					dt_2.addRow(new Object[] {data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7], data[i][8]});
				}
				
				userInfo.setModel(dt_2);
				
			}
			
		});
		
		b_make = new JButton("생성");
		b_make.setBounds(1280, 530, 100, 30);
		root_panel.add(b_make);
		b_make.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(Join());
				revalidate();
				repaint();
				id.setText("");
				pw.setText("");
				ReadData read = new ReadData();
				Vector<String[]> all = read.read_Alldata();
				String[][] data = new String[all.size()][9];
				
				String[] table_name = {"NUM", "NAME", "ID", "PASSWORD", "BIRTH", "GENDER", "MAJOR", "EMAIL", "HP"};
				DefaultTableModel dt_2 = new DefaultTableModel(table_name, 0);
				
				
				
				for(int i = 0; i < all.size(); i++) {
					data[i] = all.elementAt(i);
					dt_2.addRow(new Object[] {data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7], data[i][8]});
				}
				
				userInfo.setModel(dt_2);
			}
			
		});
		
		b_modify = new JButton("수정");
		b_modify.setBounds(1060, 530, 100, 30);
		root_panel.add(b_modify);
		b_modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				type = (String)search.getSelectedItem();
				ReadData read = new ReadData();
				if(!text_search.getText().equals("") && text_search.getText() != null) {
					if(type.equals("Num")) {
						b_id = read.return_id(text_search.getText(), 1);
					}else if(type.equals("Hp")) {
						b_id = read.return_id(text_search.getText(), 2);
					}else {
						b_id = text_search.getText();
					}
					
					getContentPane().removeAll();
					getContentPane().add(Manage(b_id , 1));
					revalidate();
					repaint();
					Vector<String[]> all = read.read_Alldata();
					String[][] data = new String[all.size()][9];
					
					String[] table_name = {"NUM", "NAME", "ID", "PASSWORD", "BIRTH", "GENDER", "MAJOR", "EMAIL", "HP"};
					DefaultTableModel dt_2 = new DefaultTableModel(table_name, 0);
					
					
					
					for(int i = 0; i < all.size(); i++) {
						data[i] = all.elementAt(i);
						dt_2.addRow(new Object[] {data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7], data[i][8]});
					}
					
					userInfo.setModel(dt_2);
				}
			}
			
		});
		
		
		b_delete = new JButton("삭제");
		b_delete.setBounds(1170, 530, 100, 30);
		root_panel.add(b_delete);
		b_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				type = (String)search.getSelectedItem();
				ReadData read = new ReadData();
				if(!text_search.getText().equals("") && text_search.getText() != null) {
					if(type.equals("Num")) {
						b_id = read.return_id(text_search.getText(), 1);
					}else if(type.equals("Hp")) {
						b_id = read.return_id(text_search.getText(), 2);
					}else if(type.equals("ID")){
						b_id = text_search.getText();
					}
				}
				else {
					row = userInfo.getSelectedRow();
					b_id = (String) userInfo.getValueAt(row, 2);
				}
				
				int num = read.return_num(b_id);
				
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					WriteData del = new WriteData();
					del.delete(num);
					dt.removeRow(row);
				}
			}
			
		});
		
		root_panel.add(manage());
		
		return root_panel;
	}
	
	public JScrollPane manage() {
		
		ReadData read = new ReadData();
		Vector<String[]> all = read.read_Alldata();
		data = new String[all.size()][9];
		
		String[] table_name = {"NUM", "NAME", "ID", "PASSWORD", "BIRTH", "GENDER", "MAJOR", "EMAIL", "HP"};
		dt = new DefaultTableModel(table_name, 0);
		
		userInfo = new JTable(dt);
		sc = new JScrollPane(userInfo);
		userInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for(int i = 0; i < all.size(); i++) {
			data[i] = all.elementAt(i);
			dt.addRow(new Object[] {data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], data[i][7], data[i][8]});
		}
		
		
		sc.setBounds(50, 20, 1300, 500);
		
		return sc;
	}
	
	public JPanel Join() {
		
		p_name = "";
		pp_id = "";
		pp_pw = "";
		p_birth = "";
		p_gender = 0;
		p_major = "";
		p_email = null;
		p_hp = "";
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 240, 240));
		panel_2.setLayout(null);
		
		label_2 = new JLabel("YOUNG");
		label_2.setBounds(225, 0, 350, 120);
		label_2.setHorizontalAlignment(JLabel.CENTER);
		label_2.setFont(new Font("Britannic", Font.BOLD, 50));
		label_2.setForeground(new Color(71, 200, 62));
		panel_2.add(label_2);
		
		p_label = new JLabel();
		p_label.setBounds(260, 250, 200, 15);
		p_label.setHorizontalAlignment(JLabel.LEFT);
		p_label.setFont(new Font("Britannic", Font.PLAIN, 13));
		panel_2.add(p_label);
		
		String[] list = {"이름", "아이디", "비밀번호", "비밀번호 재확인", "생년월일", "성별", "이메일(선택)", "휴대전화", "전공"};
		String[] month_list = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] email_list = {"직접입력", "naver.com", "gmail.com", "hanmail.net", "handong.edu"};
		String[] major_list = {"글로벌리더쉽학부", "국제어문학부", "경영경제학부", "법학부", "언론정보문화학부", "공간환경시스템공학부", "기계제어공학부", "콘텐츠융합공학부", "생명과학부", "전산전자공학부", "상담심리사회복지학부", "ICT창업학부", "기타"};
		
		man.setSelected(false);
		woman.setSelected(false);
		
		int j = 0;
		int label_y = 100, text_y = 115;
		for(int i = 0; i < list.length; i++) {
			
			labels[i] = new JLabel(list[i]);
			labels[i].setBounds(205, label_y, 100, 15);
			
			labels[i].setHorizontalAlignment(JLabel.LEFT);
			labels[i].setFont(new Font("Britannic", Font.BOLD, 13));
			labels[i].setForeground(Color.black);
			panel_2.add(labels[i]);
			
			if(i == 2 || i == 3) {
				if(i == 2) {
					ppw.setBounds(200, text_y, 300, 50);
					panel_2.add(ppw);
				}else {
					re_pw.setBounds(200, text_y, 300, 50);
					panel_2.add(re_pw);
				}
				
			}else if(i == 5) {
				man.setBounds(200, text_y, 100, 50);
				man.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						p_gender = 1;
						woman.setSelected(false);
					}				
				});
				woman.setBounds(330, text_y, 100, 50);
				woman.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						p_gender = 2;
						man.setSelected(false);
					}				
				});
				panel_2.add(man);
				panel_2.add(woman);
			}else if(i == 8) {
				major = new JComboBox<String>(major_list);
				major.setBounds(200,  text_y-10, 400, 50);
				panel_2.add(major);
			}else {
				if(i == 1) {
					new_text[j] = new JTextField("");
					new_text[j].setBounds(200, text_y, 300, 50);
				}else if(i == 4) {
					new_text[j] = new JTextField(""); 
					new_text[j].setText(" 년(4자)");
					new_text[j].setBounds(200, text_y, 130, 50);
					
					month = new JComboBox<String>(month_list);
					month.setBounds(350, text_y, 100, 50);
					panel_2.add(month);
					
					date = new JTextField(""); 
					date.setText(" 일");
					date.setBounds(470, text_y, 130, 50);
					panel_2.add(date);
					
				}else if(i == 6) {
					new_text[j] = new JTextField("");
					new_text[j].setBounds(200, text_y, 110, 50);
					JLabel and = new JLabel("@");
					and.setBounds(320, text_y, 20, 50);
					panel_2.add(and);
					
					add = new JTextField("");
					add.setBounds(340, text_y, 160, 50);
					panel_2.add(add);
					
					email = new JComboBox<String>(email_list);
					email.setBounds(510, text_y, 200, 50);
					panel_2.add(email);
					email.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String str = (String)email.getSelectedItem();
							
							if(!str.equals("직접입력"))
								add.setText(str);
						}
						
					});
					
					
				}else {
					new_text[j] = new JTextField("");
					new_text[j].setBounds(200, text_y, 400, 50);
				}
				panel_2.add(new_text[j]);
				j++;
			}
			
			ess[i] = new JLabel();
			if(i == 3)
				ess[i].setBounds(300, label_y, 200, 15);
			else
				ess[i].setBounds(260, label_y, 200, 15);
			
			
			ess[i].setHorizontalAlignment(JLabel.LEFT);
			ess[i].setFont(new Font("Britannic", Font.PLAIN, 13));
			panel_2.add(ess[i]);
			
			label_y += 75;
			text_y += 75;
		}
		
		nolabel = new JLabel();
		nolabel.setBounds(260, 175, 200, 15);
		nolabel.setHorizontalAlignment(JLabel.LEFT);
		nolabel.setFont(new Font("Britannic", Font.PLAIN, 13));
		panel_2.add(nolabel);
		
		
		new_text[0].addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(t.equals("")) {
					ess[0].setText("* 필수 항목입니다.");
					ess[0].setForeground(Color.RED);
					essential = 1;
				}else {
					ess[0].setText("");
					essential = 0;
					p_name = t;
				}
			}
			
		});
		
		
		
		
		new_text[1].addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(t.equals("")) {
					nolabel.setText("* 필수 항목입니다.");
					nolabel.setForeground(Color.RED);
					essential = 1;
				}else {
					nolabel.setText("");
					essential = 0;
					pp_id = t;
				}
			}
			
		});
		
		new_text[2].addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(t.equals("")) {
					ess[4].setText("* 필수 항목입니다.");
					ess[4].setForeground(Color.RED);
					essential = 1;
				}else {
					ess[4].setText("");
					essential = 0;
				}
			}
			
		});
		
		new_text[4].addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(t.equals("")) {
					ess[7].setText("* 필수 항목입니다.");
					ess[7].setForeground(Color.RED);
					essential = 1;
				}else {
					ess[7].setText("");
					essential = 0;
				}
			}
			
		});
		
	
		
		ppw.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				JPasswordField text = (JPasswordField)e.getSource();
				String t = new String(text.getPassword());
				
				if(t.length() < 6) {
					p_label.setText("* 약함");
					p_label.setForeground(Color.RED);
				}
				if(t.length() >= 6) {
					p_label.setText("* 보통");
					p_label.setForeground(new Color(255, 187, 0));
				}
				if(t.length() > 10) {
					p_label.setText("* 강력");
					p_label.setForeground(new Color(29, 219, 22));
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				pass = new String(ppw.getPassword());
			}
			
		});
		
		
		// password check
		
		re_pw.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(!t.equals(pass)) {
					ess[3].setText("* 비밀번호가 일치하지 않습니다.");
					ess[3].setForeground(Color.RED);
					pass_check = 1;
				}else {
					ess[3].setText("* 비밀번호가 일치합니다.");
					ess[3].setForeground(Color.BLUE);
					pass_check = 0;
					re = new String(re_pw.getPassword());
				}
			}
			
		});
		
		back = new JButton("뒤로가기");
		back.setBounds(40, 750, 120, 35);
		back.setFont(new Font("Britannic", Font.PLAIN, 17));
		panel_2.add(back);
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panel_1);
				revalidate();
				repaint();
			}
			
		});
		
		
		btn3 = new JButton("가입하기");
		btn3.setBounds(650, 750, 120, 35);
		btn3.setFont(new Font("Britannic", Font.PLAIN, 17));
		panel_2.add(btn3);
		
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(essential == 1|| pass.equals("") || re.equals("")|| date.getText().equals("") || date.getText().equals(" 일") || new_text[2].getText().equals(" 년(4자)") || p_gender == 0) {
					JOptionPane.showMessageDialog(null, "필수 정보를 모두 입력해주세요.","ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(overlap == 1) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.","ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(pass_check == 1) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(null, "회원가입을 성공하셨습니다!");
				manage();
				getContentPane().removeAll();
				getContentPane().add(panel_1);
				revalidate();
				repaint();
				
				p_hp = new_text[4].getText();
				p_major = (String)major.getSelectedItem();
				String d = date.getText().replace(" ", "");
				p_birth = new_text[2].getText() + (String)month.getSelectedItem() + d;
				if((!new_text[3].getText().equals("")) && (!((String)email.getSelectedItem()).equals("")) && (!((String)email.getSelectedItem()).equals("직접입력"))) {
					p_email = new_text[3].getText() + "@" + (String)email.getSelectedItem();
				}
				pp_pw = re;
				
				ppw = new JPasswordField();
				re_pw = new JPasswordField();
				writeDatabase();
	
			}
			
		});
		
		
		btn4 = new JButton("중복확인");
		btn4.setBounds(510, 195, 90, 40);
		btn4.setFont(new Font("Britannic", Font.PLAIN, 14));
		panel_2.add(btn4);
		
		// id check
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ReadData readid = new ReadData();
				if(!readid.readId(new_text[0].getText())) {
					nolabel.setText("* 이미 존재하는 아이디입니다.");
					nolabel.setForeground(Color.RED);
					overlap = 1;
				}else {
					nolabel.setText("* 사용가능한 아이디입니다.");
					nolabel.setForeground(Color.BLUE);
					overlap = 0;
				}
				
			}
			
		});
		
		return panel_2;
		
	}
	
	public void writeDatabase() {
		WriteData writeData = new WriteData();
		System.out.println(p_name);
		System.out.println(pp_id);
		System.out.println(pp_pw);
		System.out.println(p_birth);
		System.out.println(p_gender);
		System.out.println(p_major);
		System.out.println(p_hp);
		System.out.println(p_email);
		
		writeData.write(p_name, pp_id, pp_pw, p_birth, p_gender, p_major, p_hp, p_email);
	}
	
	public JPanel Login_Success(String p_id) {
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 240, 240));
		panel_3.setLayout(null);
		
		label_3 = new JLabel("YOUNG");
		label_3.setBounds(225, 0, 350, 140);
		label_3.setHorizontalAlignment(JLabel.CENTER);
		label_3.setFont(new Font("Britannic", Font.BOLD, 60));
		label_3.setForeground(new Color(71, 200, 62));
		panel_3.add(label_3);
		
		ReadData read = new ReadData();
		String name = read.return_data(p_id, 1);
		
		hello = new JLabel();
		hello.setBounds(160, 140, 500, 120);
		hello.setFont(new Font("Britannic", Font.CENTER_BASELINE, 30));
		hello.setText("안녕하세요 "+ name + "님 >_$^");
		hello.setHorizontalAlignment(JLabel.CENTER);
		panel_3.add(hello);
		
		help = new JLabel();
		help.setBounds(160, 200, 500, 120);
		help.setFont(new Font("Britannic", Font.CENTER_BASELINE, 30));
		help.setText("무엇을 도와드릴까요?");
		help.setHorizontalAlignment(JLabel.CENTER);
		panel_3.add(help);
		
		logout = new JButton("로그아웃");
		logout.setBounds(50, 750, 120, 50);
		logout.setFont(new Font("Britannic", Font.PLAIN, 17));
		panel_3.add(logout);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panel_1);
				revalidate();
				repaint();
				if(save_id == 1)
					id.setText(p_id);
				else
					id.setText("");
				
				pw.setText("");
			}
			
		});
		
		int num = read.return_num(p_id);
		
		out = new JButton("탈퇴");
		out.setBounds(140, 400, 200, 100);
		out.setFont(new Font("Britannic", Font.PLAIN, 17));
		panel_3.add(out);
		out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					WriteData del = new WriteData();
					del.delete(num);
					getContentPane().removeAll();
					getContentPane().add(panel_1);
					revalidate();
					repaint();
					id.setText("");
					pw.setText("");
				}
			}
			
		});
		
		manage = new JButton("회원정보 수정");
		manage.setBounds(430, 400, 200, 100);
		manage.setFont(new Font("Britannic", Font.PLAIN, 17));
		panel_3.add(manage);
		manage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(Manage(p_id, 0));
				revalidate();
				repaint();
			}
			
		});
		
		return panel_3;
	}
	
	public JPanel Manage(String p_id, int mode) {
		
		essential = 0;
		p_name = "";
		pp_id = "";
		pp_pw = "";
		p_birth = "";
		p_gender = 0;
		p_major = "";
		p_email = null;
		p_hp = "";
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 240, 240));
		panel_4.setLayout(null);
		
		label_2 = new JLabel("YOUNG");
		label_2.setBounds(225, 0, 350, 120);
		label_2.setHorizontalAlignment(JLabel.CENTER);
		label_2.setFont(new Font("Britannic", Font.BOLD, 50));
		label_2.setForeground(new Color(71, 200, 62));
		panel_4.add(label_2);
		
		ReadData read = new ReadData();
		String name = read.return_data(p_id, 1);
		String pww = read.return_data(p_id, 2);
		
		p_label = new JLabel();
		p_label.setBounds(260, 250, 200, 15);
		p_label.setHorizontalAlignment(JLabel.LEFT);
		p_label.setFont(new Font("Britannic", Font.PLAIN, 13));
		panel_4.add(p_label);
		
		String[] list = {"이름", "아이디", "비밀번호", "비밀번호 재확인", "생년월일", "성별", "이메일(선택)", "휴대전화", "전공"};
		String[] month_list = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] email_list = {"직접입력", "naver.com", "gmail.com", "hanmail.net", "handong.edu"};
		String[] major_list = {"글로벌리더쉽학부", "국제어문학부", "경영경제학부", "법학부", "언론정보문화학부", "공간환경시스템공학부", "기계제어공학부", "콘텐츠융합공학부", "생명과학부", "전산전자공학부", "상담심리사회복지학부", "ICT창업학부", "기타"};

		
		int j = 0;
		int label_y = 100, text_y = 115;
		for(int i = 0; i < list.length; i++) {
			
			labels[i] = new JLabel(list[i]);
			labels[i].setBounds(205, label_y, 100, 15);
			
			labels[i].setHorizontalAlignment(JLabel.LEFT);
			labels[i].setFont(new Font("Britannic", Font.BOLD, 13));
			labels[i].setForeground(Color.black);
			panel_4.add(labels[i]);
			
			if(i == 2 || i == 3) {
				if(i == 2) {
					ppw.setBounds(200, text_y, 300, 50);
					if(mode == 1)
						ppw.setText(pww);
					panel_4.add(ppw);
				}else {
					re_pw.setBounds(200, text_y, 300, 50);
					if(mode == 1)
						re_pw.setText(pww);
					panel_4.add(re_pw);
				}
				
			}else if(i == 5) {
				String g = read.return_data(p_id, 4);
				if(g.equals("Male")) {
					man.setSelected(true);
					p_gender = 1;
				}else {
					woman.setSelected(true);
					p_gender = 2;
				}
				man.setBounds(200, text_y, 100, 50);
				man.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						p_gender = 1;
						woman.setSelected(false);
					}				
				});
				woman.setBounds(330, text_y, 100, 50);
				woman.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						p_gender = 2;
						man.setSelected(false);
					}				
				});
				panel_4.add(man);
				panel_4.add(woman);
			}else if(i == 8) {
				major = new JComboBox<String>(major_list);
				String ma = read.return_data(p_id, 5);
				int m = -1;
				for(int l = 0; l < major_list.length; l++) {
					if(major_list[l].equals(ma)) {
						m = l;
						break;
					}
				}
				
				if(m > -1) {
					major.setSelectedIndex(m);
				}
				major.setBounds(200,  text_y-10, 400, 50);
				panel_4.add(major);
			}else {
				if(i == 1) {
					new_text[j] = new JTextField();
					new_text[j].setText(p_id);
					pp_id = p_id;
					new_text[j].setEnabled(false);
					new_text[j].setBounds(200, text_y, 300, 50);
				}else if(i == 4) {
					new_text[j] = new JTextField(); 
					String birth = read.return_data(p_id, 3);
					String year = birth.substring(0, 4);
					new_text[j].setText(year);
					new_text[j].setBounds(200, text_y, 130, 50);
					
					month = new JComboBox<String>(month_list);
					String mon = birth.substring(4, 6);
					int m = Integer.parseInt(mon);
					month.setSelectedIndex(m-1);
					month.setBounds(350, text_y, 100, 50);
					panel_4.add(month);
					
					date = new JTextField(""); 
					String d = birth.substring(6, birth.length());
					date.setText(d);
					date.setBounds(470, text_y, 130, 50);
					panel_4.add(date);
					
				}else if(i == 6) {
					new_text[j] = new JTextField();
					String em = read.return_data(p_id, 6);
					String front, back;
					if(em != null && !em.equals("")) {
						int idx = em.indexOf("@");
						front = em.substring(0, idx);
						back = em.substring(idx+1, em.length());
					}else {
						front = "";
						back = "";
					}
					new_text[j].setText(front);
					new_text[j].setBounds(200, text_y, 110, 50);
					JLabel and = new JLabel("@");
					and.setBounds(320, text_y, 20, 50);
					panel_4.add(and);
					
					add = new JTextField("");
					add.setBounds(340, text_y, 160, 50);
					panel_4.add(add);
					
					email = new JComboBox<String>(email_list);
					int ex = -1;
					for(int l = 0; l < email_list.length; l++) {
						if(email_list[l].equals(back)) {
							ex = l;
							break;
						}
					}
					if(ex > -1) {
						email.setSelectedIndex(ex);
						add.setText(email_list[ex]);
					}
					
					email.setBounds(510, text_y, 200, 50);
					panel_4.add(email);
					email.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String str = (String)email.getSelectedItem();
							
							if(!str.equals("직접입력"))
								add.setText(str);
						}
						
					});
					
					
				}else if(i == 0) {
					new_text[j] = new JTextField();
					new_text[j].setText(name);
					new_text[j].setEnabled(false);
					new_text[j].setBounds(200, text_y, 300, 50);
					p_name = name;
				}else {
					String h = read.return_data(p_id, 7);
					new_text[j] = new JTextField();
					new_text[j].setText(h);
					new_text[j].setBounds(200, text_y, 400, 50);
				}
				panel_4.add(new_text[j]);
				j++;
			}
			
			ess[i] = new JLabel();
			if(i == 3)
				ess[i].setBounds(300, label_y, 200, 15);
			else
				ess[i].setBounds(260, label_y, 200, 15);
			
			
			ess[i].setHorizontalAlignment(JLabel.LEFT);
			ess[i].setFont(new Font("Britannic", Font.PLAIN, 13));
			panel_4.add(ess[i]);
			
			label_y += 75;
			text_y += 75;
		}
		
		nolabel = new JLabel();
		nolabel.setBounds(260, 175, 200, 15);
		nolabel.setHorizontalAlignment(JLabel.LEFT);
		nolabel.setFont(new Font("Britannic", Font.PLAIN, 13));
		panel_4.add(nolabel);
		
		
		
		new_text[2].addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(t.equals("")) {
					ess[4].setText("* 필수 항목입니다.");
					ess[4].setForeground(Color.RED);
					essential = 1;
				}else {
					ess[4].setText("");
					essential = 0;
				}
			}
			
		});
		
		new_text[4].addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(t.equals("")) {
					ess[7].setText("* 필수 항목입니다.");
					ess[7].setForeground(Color.RED);
					essential = 1;
				}else {
					ess[7].setText("");
					essential = 0;
				}
			}
			
		});
		
	
		
		ppw.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				JPasswordField text = (JPasswordField)e.getSource();
				String t = new String(text.getPassword());
				
				if(t.length() < 6) {
					p_label.setText("* 약함");
					p_label.setForeground(Color.RED);
				}
				if(t.length() >= 6) {
					p_label.setText("* 보통");
					p_label.setForeground(new Color(255, 187, 0));
				}
				if(t.length() > 10) {
					p_label.setText("* 강력");
					p_label.setForeground(new Color(29, 219, 22));
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				pass = new String(ppw.getPassword());
			}
			
		});
		
		
		// password check
		
		re_pw.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField text = (JTextField) e.getSource();
				String t = text.getText();
				if(!t.equals(pass)) {
					ess[3].setText("* 비밀번호가 일치하지 않습니다.");
					ess[3].setForeground(Color.RED);
					pass_check = 1;
				}else {
					ess[3].setText("* 비밀번호가 일치합니다.");
					ess[3].setForeground(Color.BLUE);
					pass_check = 0;
					re = new String(re_pw.getPassword());
				}
			}
			
		});
		
		back = new JButton("뒤로가기");
		back.setBounds(40, 750, 120, 35);
		back.setFont(new Font("Britannic", Font.PLAIN, 17));
		panel_4.add(back);
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panel_1);
				revalidate();
				repaint();
				id.setText("");
				pw.setText("");
				ppw.setText("");
				re_pw.setText("");
			}
			
		});
		
		modify = new JButton("수정하기");
		modify.setBounds(650, 750, 120, 35);
		modify.setFont(new Font("Britannic", Font.PLAIN, 17));
		panel_4.add(modify);
		
		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				re = new String(re_pw.getPassword());
				if(essential == 1|| pass.equals("") || re.equals("")|| date.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "필수 정보를 모두 입력해주세요.","ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(pass_check == 1) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(null, "수정을 완료하였습니다!");
				getContentPane().removeAll();
				getContentPane().add(panel_1);
				revalidate();
				repaint();
				if(save_id == 1)
					id.setText(p_id);
				else 
					id.setText("");
				
				pw.setText("");
				
				p_hp = new_text[4].getText();
				p_major = (String)major.getSelectedItem();
				String d = date.getText().replace(" ", "");
				p_birth = new_text[2].getText() + (String)month.getSelectedItem() + d;
				if((!new_text[3].getText().equals("")) && (!((String)email.getSelectedItem()).equals("")) && (!((String)email.getSelectedItem()).equals("직접입력"))) {
					p_email = new_text[3].getText() + "@" + (String)email.getSelectedItem();
				}
				pp_pw = re;
				
				ppw = new JPasswordField();
				re_pw = new JPasswordField();
				modifyData();
	
			}
			
		});
		
		
		return panel_4;
	}
	
	public void modifyData() {
		WriteData writeData = new WriteData();
		System.out.println(p_name);
		System.out.println(pp_id);
		System.out.println(pp_pw);
		System.out.println(p_birth);
		System.out.println(p_gender);
		System.out.println(p_major);
		System.out.println(p_hp);
		System.out.println(p_email);
		
		writeData.modify(pp_id, pp_pw, p_birth, p_gender, p_major, p_hp, p_email);
	}
	
}
