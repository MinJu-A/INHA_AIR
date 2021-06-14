package customer.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import DataBase.databaseClass;

//연우 - 회원가입
public class SignUpForm extends JFrame implements ActionListener{

	//데이터베이스 관련
	static String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
	static String dbID="inhaair";
	static String dbPassword="1234";
	
	private String title="INHA AIR - 회원가입";
	private int width = 800, height = 700;
	
	// 폰트
	Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
	Font fontNanumGothic11 = new Font("NanumGothic", Font.BOLD, 11);
	Font fontNanumGothic13 = new Font("NanumGothic", Font.BOLD, 13);
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 18
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontGothic = new Font("Gothic", Font.BOLD, 13);
	Font fontNanumGothic = new Font("NanumGothic", Font.BOLD, 20);

	
	private JPanel jpTitle, jpInfL, jpInfR; //제목패널, 왼쪽정보패널, 오른쪽정보패널
	private JCheckBox chkNewsLetter, chkPromotion, chkSMS; //체크박스 - 뉴스레터, 프로모션, SMS
	private JButton btnchk, btnSignUp; //아이디 중복체크 버튼, 회원가입 버튼
	private JLabel lblTitle, lblLNEng, lblLNKor, lblID, lblPW, lblPWchk;	//제목, 영어이름, 한국어이름, 아이디, 비밀번호, 비밀번호확인 라벨
	private JLabel lblBirth, lblSex, lblEmail, lblPhone, lblAdd; //생일, 성별, 이메일, 핸드폰번호, 정보동의 라벨
	private JLabel  lblEmailEx, lblPhoneEx, lblPassportEx, lblBirthEx, lblPassport; //이메일 예시, 핸드폰번호 예시, 여권번호 예시, 생년월일 예시, 여권번호 라벨
	
	private JTextField tfID, tfEmail, tfLNEng, tfLNKor; //아이디, 이메일, 한글이름, 영어이름 텍스트필드
	private JFormattedTextField tfBirth, tfPhone, tfPassport; //생일, 핸드폰번호, 여권번호 입력형식 설정
	private JPasswordField tfPW, tfPWchk; //비밀번호, 비밀번호확인 라벨
	private ButtonGroup rg; //성별 라디오그룹
	private JRadioButton rbMan, rbWoman; //남성 라디오버튼, 여성 라디오버튼
	private String sex, birthday; //데이터베이스에 입력될 성별, 생일 값
	private int news, pro, sms; //데이터베이스에 입력될 뉴스레터, 프로모션, SMS 여부
	private boolean telVCheck, emailVCheck, passportVCheck, idVCheck; //전화번호, 이메일, 여권번호, 아이디 중복여부 확
	private boolean korVCheck, engVCheck, emaVCheck, idsetVCheck; //한글이름 텍스트 확인, 영어이름 텍스트 확인
	
	

	private String kor = "^[가-힣]+$"; //정규표현식 - 한글
	private String eng = "^[a-zA-Z]+$"; //정규표현식 - 영어
	private String idset = "^[a-zA-Z0-9]"; //정규표현식 - 아이디
	private String ema = "^[a-zA-Z0-9._%+-]+@"; //정규표현식 - 이메일

	
			
			
	public SignUpForm() {
		
		//화면 설정
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		setLayout(null);
		
		setSignUpForm();
		
		setVisible(true);
	}


	private void setSignUpForm() {
		
		//회원가입 제목
		jpTitle = new JPanel();
		jpTitle.setLayout(null);
		jpTitle.setSize(350, 40);
		jpTitle.setLocation(40, 20);
		jpTitle.setBackground(Color.white);
		
		lblTitle = new JLabel("회원가입");
		lblTitle.setFont(fontNanumGothic25);
		lblTitle.setSize(150, 35);
		lblTitle.setLocation(0, 10);
		jpTitle.add(lblTitle);
		
		//좌측 정보 창
		jpInfL = new JPanel();
		jpInfL.setLayout(null);
		jpInfL.setSize(350, 420);
		jpInfL.setLocation(40, 80);
		jpInfL.setBackground(Color.white);
		
		lblLNKor= new JLabel("한글 이름");
		lblLNKor.setSize(300, 30);
		lblLNKor.setLocation(0, 0);
		lblLNKor.setFont(fontNanumGothic18);
		jpInfL.add(lblLNKor);
		
		tfLNKor = new JTextField();
		tfLNKor.setSize(300, 30);
		tfLNKor.setLocation(0, 30);
		jpInfL.add(tfLNKor);
		
		lblLNEng = new JLabel("영문 이름");
		lblLNEng.setSize(350, 30);
		lblLNEng.setLocation(0, 70);
		lblLNEng.setFont(fontNanumGothic18);
		jpInfL.add(lblLNEng);
		

		tfLNEng = new JTextField();
		tfLNEng.setSize(300, 30);
		tfLNEng.setLocation(0, 100);
		jpInfL.add(tfLNEng);
		
		lblID = new JLabel("회원 아이디");
		lblID.setSize(350, 30);
		lblID.setLocation(0, 140);
		lblID.setFont(fontNanumGothic18);
		jpInfL.add(lblID);
		
		tfID = new JTextField();
		tfID.setSize(190, 30);
		tfID.setLocation(0, 170);
		jpInfL.add(tfID);
		
		btnchk = new JButton("중복체크");
		btnchk.setSize(100, 30);
		btnchk.setLocation(200, 170);
		btnchk.setBackground(new Color(10,90,150));
		btnchk.setForeground(Color.white); //버튼 폰트 색 변경
		btnchk.setFont(fontNanumGothic13);
		btnchk.addActionListener(this);
		jpInfL.add(btnchk);
		
		lblPW = new JLabel("비밀번호");
		lblPW.setSize(350, 30);
		lblPW.setLocation(0, 210);
		lblPW.setFont(fontNanumGothic18);
		jpInfL.add(lblPW);
		
		tfPW = new JPasswordField();
		tfPW.setSize(300, 30);
		tfPW.setLocation(0, 240);
		jpInfL.add(tfPW);
		
		lblPWchk = new JLabel("비밀번호 확인");
		lblPWchk.setSize(300, 30);
		lblPWchk.setLocation(0, 280);
		lblPWchk.setFont(fontNanumGothic18);
		jpInfL.add(lblPWchk);
		
		tfPWchk = new JPasswordField();
		tfPWchk.setSize(300, 30);
		tfPWchk.setLocation(0, 310);
		jpInfL.add(tfPWchk);
		
		lblPassport = new JLabel("여권번호");
		lblPassport.setSize(300, 30);
		lblPassport.setLocation(0, 350);
		lblPassport.setFont(fontNanumGothic18);
		jpInfL.add(lblPassport);
		
		lblPassportEx = new JLabel("예) M00000000 (여권번호 9자)");
		lblPassportEx.setSize(300, 30);
		lblPassportEx.setLocation(100, 350);
		lblPassportEx.setFont(fontNanumGothic11);
		lblPassportEx.setForeground(Color.gray);
		jpInfL.add(lblPassportEx);
		
		try {
			MaskFormatter format = new MaskFormatter("*********");
			tfPassport = new JFormattedTextField(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfPassport.setSize(300, 30);
		tfPassport.setLocation(0, 380);
		jpInfL.add(tfPassport);
		
		
		//우측 정보 창
		jpInfR = new JPanel();
		jpInfR.setLayout(null);
		jpInfR.setSize(350, 400);
		jpInfR.setLocation(410, 80);
		jpInfR.setBackground(Color.white);
		
		lblBirth = new JLabel("생년월일");
		lblBirth.setSize(300, 30);
		lblBirth.setLocation(0, 0);
		lblBirth.setFont(fontNanumGothic18);
		jpInfR.add(lblBirth);
		
		lblBirthEx = new JLabel("예) 20020214");
		lblBirthEx.setSize(300, 30);
		lblBirthEx.setLocation(100, 0);
		lblBirthEx.setFont(fontNanumGothic11);
		lblBirthEx.setForeground(Color.gray);
		jpInfR.add(lblBirthEx);
		
		try {
			MaskFormatter format = new MaskFormatter("########");
			tfBirth = new JFormattedTextField(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfBirth.setSize(300, 30);
		tfBirth.setLocation(0, 30);
		tfBirth.setBackground(Color.white);
		jpInfR.add(tfBirth);
		
		lblSex = new JLabel("성별");
		lblSex.setSize(300, 30);
		lblSex.setLocation(0, 70);
		lblSex.setFont(fontNanumGothic18);
		jpInfR.add(lblSex);
		
		rg = new ButtonGroup();
		rbMan = new JRadioButton("남");
		rbMan.setSelected(true);
		rbMan.setSize(50, 30);
		rbMan.setLocation(0, 100);
		rbMan.setBackground(Color.white);
		rbWoman = new JRadioButton("여");
		rbWoman.setSize(50, 30);
		rbWoman.setLocation(80, 100);
		rbWoman.setBackground(Color.white);
		rbWoman.setSelected(false);
		rg.add(rbMan);
		rg.add(rbWoman);
		jpInfR.add(rbMan);
		jpInfR.add(rbWoman);
		
		lblEmail = new JLabel("이메일 주소");
		lblEmail.setSize(300, 30);
		lblEmail.setLocation(0, 140);
		lblEmail.setFont(fontNanumGothic18);
		jpInfR.add(lblEmail);
		
		lblEmailEx = new JLabel("예) kyeonw1109@gmail.com");
		lblEmailEx.setSize(300, 30);
		lblEmailEx.setLocation(120, 140);
		lblEmailEx.setFont(fontNanumGothic11);
		lblEmailEx.setForeground(Color.gray);
		jpInfR.add(lblEmailEx);
		
		tfEmail = new JTextField();
		tfEmail.setSize(300, 30);
		tfEmail.setLocation(0, 170);
		jpInfR.add(tfEmail);
		
		lblPhone = new JLabel("휴대전화 번호");
		lblPhone.setSize(300, 30);
		lblPhone.setLocation(0, 210);
		lblPhone.setFont(fontNanumGothic18);
		jpInfR.add(lblPhone);
		
		lblPhoneEx = new JLabel("예) 01012345678");
		lblPhoneEx.setSize(300, 30);
		lblPhoneEx.setLocation(140, 210);
		lblPhoneEx.setFont(fontNanumGothic11);
		lblPhoneEx.setForeground(Color.gray);
		jpInfR.add(lblPhoneEx);
		
		
		try {
			MaskFormatter format = new MaskFormatter("###########");
			tfPhone = new JFormattedTextField(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfPhone.setSize(300, 30);
		tfPhone.setLocation(0, 240);
		jpInfR.add(tfPhone);
		
		
		lblAdd = new JLabel("마케팅 및 광고 활용 동의");
		lblAdd.setSize(300, 30);
		lblAdd.setLocation(0, 280);
		lblAdd.setFont(fontNanumGothic18);
		jpInfR.add(lblAdd);
		
		chkNewsLetter = new JCheckBox("뉴스레터(회원 소식지 등)");
		chkNewsLetter.setSize(300, 30);
		chkNewsLetter.setLocation(0, 310);
		chkNewsLetter.setBackground(Color.white);
		chkNewsLetter.setSelected(false);
		jpInfR.add(chkNewsLetter);
		
		chkPromotion = new JCheckBox("프로모션 정보(이벤트, 할인 쿠폰)");
		chkPromotion.setSize(300, 30);
		chkPromotion.setLocation(0, 340);
		chkPromotion.setBackground(Color.white);
		chkPromotion.setSelected(false);
		jpInfR.add(chkPromotion);

		chkSMS = new JCheckBox("SMS 수신 동의");
		chkSMS.setSize(300, 30);
		chkSMS.setLocation(0, 370);
		chkSMS.setBackground(Color.white);
		chkSMS.setSelected(false);
		jpInfR.add(chkSMS);
		
		btnSignUp = new JButton("회원가입");
		btnSignUp.setBackground(new Color(10,90,150));
		btnSignUp.setSize(200, 40);
		btnSignUp.setLocation(280, 550);
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(fontNanumGothic18);
		btnSignUp.addActionListener(this);
		
	
		add(jpTitle);
		add(jpInfL);
		add(jpInfR);
		add(btnSignUp);
	}


	public static void main(String[] args) {
		new SignUpForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if (obj == btnchk && !(tfID.getText().isEmpty())) {
			if (tfID.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			} 
			if (!idVCheck) {
				if (idCheck(tfID.getText())) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
				} else {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
					btnchk.setText("다시입력");
					tfID.setEnabled(false);
				}
			} else {
				tfID.setText("");
				tfID.setEnabled(true);
				btnchk.setText("중복체크");
				idVCheck = false;
			}
		}
		else if (obj == btnSignUp) {
			
			
			if (tfLNKor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "한글 이름을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfLNKor.requestFocus();
			} else if (tfLNEng.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "영문 이름을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfLNEng.requestFocus();
			} else if(tfID.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "회원 아이디를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfID.requestFocus();
			} else if (tfPW.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPW.requestFocus();
			} else if (tfPWchk.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPWchk.requestFocus();
			} else if (tfPassport.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "여권번호를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPassport.requestFocus();
			} else if (tfBirth.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfBirth.requestFocus();
			} else if (tfEmail.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "이메일 주소를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfEmail.requestFocus();
			} else if (tfPhone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "휴대전화 번호를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPhone.requestFocus();
			} else if(!tfPW.getText().equals(tfPWchk.getText())) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "회원가입오류", JOptionPane.ERROR_MESSAGE);
			} else if(idVCheck == false) {
				JOptionPane.showMessageDialog(null, "아이디 중복체크를 해주세요.", "회원가입오류", JOptionPane.ERROR_MESSAGE);
			} else {
				if (!korVCheck) {
					if (!korCheck(tfLNKor.getText())) {
						JOptionPane.showMessageDialog(null, "한글이름에 한글만 입력해주세요");
					}
				} else if (!engVCheck) {
					if (!engCheck(tfLNEng.getText())) {
						JOptionPane.showMessageDialog(null, "영어이름에 영어만 입력해주세요");
					}
				} else if (!emaVCheck) {
					if (!emaCheck(tfEmail.getText())) {
						JOptionPane.showMessageDialog(null, "이메일을 제대로 입력해주세요");
					}
				} else if (!idSetCheck) {
					if (!idSetCheck(tfID.getText())) {
						JOptionPane.showMessageDialog(null, "아이디는 영어와 숫자로만 작성해주세요");
					}
				}
				else if (!telVCheck) {
					if (telCheck(tfPhone.getText())) {
						JOptionPane.showMessageDialog(null, "중복된 전화번호입니다.");
					}
				} else if (!emailVCheck) {
					if (emailCheck(tfEmail.getText())) {
						JOptionPane.showMessageDialog(null, "중복된 이메일입니다.");
					}
				} else if (!passportVCheck) {
					if (passportCheck(tfPassport.getText()))
						JOptionPane.showMessageDialog(null, "중복된 여권번호입니다.");
				}
				else {
					
					if (rbMan.isSelected()) {
						sex = rbMan.getText();
					} else {
						sex = rbWoman.getText();
					}
					
					if(chkNewsLetter.isSelected()) {
						news = 1;
					} else {
						news = 0;
					}
					if (chkPromotion.isSelected()) {
						pro = 1;
					} else {
						pro = 0;
					}
					if (chkSMS.isSelected()) {
						sms = 1;
					} else {
						sms = 0;
					}
					birthday = tfBirth.getText();
					
					
					try {
						LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
						
						String sql = "INSERT INTO user(ID, password, nameKOR, nameENG, sex, passport, birth, tel, email, newsletter, promotion, sms) VALUES('" +
								tfID.getText() + "', '" +
								tfPW.getText() + "', '" +
								tfLNKor.getText() + "', '" +
								tfLNEng.getText().toUpperCase() + "', '" +
								sex + "', '" +
								tfPassport.getText() + "', '" +
								tfBirth.getText() + "', '" +
								tfPhone.getText() + "', '" +
								tfEmail.getText() + "', " +
								news + ", " +
								pro + ", " +
								sms + ")";
				
						try {
							databaseClass.insert(sql);
							JOptionPane.showMessageDialog(null, "회원가입되었습니다.", "회원가입완료", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						} catch (Exception e2) {
							//e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다. 정보를 다시 확인해주세요.", "회원가입오류", JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (DateTimeParseException e2) {
						JOptionPane.showMessageDialog(null, "날짜를 제대로 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
					}
					
					

					
				}
				
			}
		}
		
	}
	
	
	//아이디 중복 체크
	private boolean idCheck(String id) {

		String sql = "SELECT ID FROM user WHERE ID IN('" + id + "')";
		ResultSet rs = databaseClass.select(sql);
		
		try {
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		idVCheck = true;
		return false;
	}

	//핸드폰번호 중복 체크
	private boolean telCheck(String tel) {
		
		String sql = "SELECT tel FROM user WHERE tel IN('" + tel + "')";
		ResultSet rs = databaseClass.select(sql);
		
		try {
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		telVCheck = true;
		return false;
	}

	
	//이메일 중복 체크
	private boolean emailCheck(String email) {
		
		String sql = "SELECT email FROM user WHERE email IN('" + email + "')";
		ResultSet rs = databaseClass.select(sql);
		
		try {
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		emailVCheck = true;
		return false;
	}

	
	//여권번호 중복 체크
	private boolean passportCheck(String passport) {
		
		String sql = "SELECT passport FROM user WHERE passport IN('" + passport + "')";
		ResultSet rs = databaseClass.select(sql);
		
		try {
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		passportVCheck = true;
		return false;
	}
	
	//한글이름 한글확인
	private boolean korCheck(String korV) {
		return korVCheck = korV.matches(kor);
	}
	
	//영어이름 한글확인
	private boolean engCheck(String engV) {
		return engVCheck = engV.matches(eng);
	}

	//이메일필드에 @ 들어가는지 확인
	private boolean emaCheck(String emaV) {
		return emaVCheck = emaV.matches(idset);
	}

	//아이디 영어+숫자만인지 확인(특수문자 없어야함)
	private boolean idSetCheck(String idSet) {
		return idsetVCheck = idSet.matches(ema);
	}
	
}
