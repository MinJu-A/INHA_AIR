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
//	private String idset = "^[a-zA-Z0-9]+$"; //정규표현식 - 아이디
//	private String ema = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}+$"; //정규표현식 - 이메일
	
			
			
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
		
		lblTitle = new JLabel("회원가입");			//회원가입 제목 라벨
		lblTitle.setFont(fontNanumGothic25);
		lblTitle.setSize(150, 35);
		lblTitle.setLocation(0, 10);
		jpTitle.add(lblTitle);
		
		//좌측 정보 창
		jpInfL = new JPanel();	//좌측 패널
		jpInfL.setLayout(null);
		jpInfL.setSize(350, 420);
		jpInfL.setLocation(40, 80);
		jpInfL.setBackground(Color.white);
		
		lblLNKor= new JLabel("한글 이름");		//한글이름 라벨
		lblLNKor.setSize(300, 30);
		lblLNKor.setLocation(0, 0);
		lblLNKor.setFont(fontNanumGothic18);
		jpInfL.add(lblLNKor);
		
		tfLNKor = new JTextField();		//한글이름 텍스트필드
		tfLNKor.setSize(300, 30);
		tfLNKor.setLocation(0, 30);
		jpInfL.add(tfLNKor);
		
		lblLNEng = new JLabel("영문 이름");	//영문이름 라벨
		lblLNEng.setSize(350, 30);
		lblLNEng.setLocation(0, 70);
		lblLNEng.setFont(fontNanumGothic18);
		jpInfL.add(lblLNEng);
		

		tfLNEng = new JTextField();		//영문이름 텍스트필드
		tfLNEng.setSize(300, 30);
		tfLNEng.setLocation(0, 100);
		jpInfL.add(tfLNEng);
		
		lblID = new JLabel("회원 아이디");	//회원아이디 라벨
		lblID.setSize(350, 30);
		lblID.setLocation(0, 140);
		lblID.setFont(fontNanumGothic18);
		jpInfL.add(lblID);
		
		tfID = new JTextField();	//회원아이디 텍스트필드
		tfID.setSize(190, 30);
		tfID.setLocation(0, 170);
		jpInfL.add(tfID);
		
		btnchk = new JButton("중복체크");		//아이디 중복체크 버튼
		btnchk.setSize(100, 30);
		btnchk.setLocation(200, 170);
		btnchk.setBackground(new Color(10,90,150));
		btnchk.setForeground(Color.white); //버튼 폰트 색 변경
		btnchk.setFont(fontNanumGothic13);
		btnchk.addActionListener(this);
		jpInfL.add(btnchk);
		
		lblPW = new JLabel("비밀번호");		//비밀번호 라벨
		lblPW.setSize(350, 30);
		lblPW.setLocation(0, 210);
		lblPW.setFont(fontNanumGothic18);
		jpInfL.add(lblPW);
		
		tfPW = new JPasswordField();		//비밀번호 패스워드필드
		tfPW.setSize(300, 30);
		tfPW.setLocation(0, 240);
		jpInfL.add(tfPW);
		
		lblPWchk = new JLabel("비밀번호 확인");		//비밀번호확인 라벨
		lblPWchk.setSize(300, 30);
		lblPWchk.setLocation(0, 280);
		lblPWchk.setFont(fontNanumGothic18);
		jpInfL.add(lblPWchk);
		
		tfPWchk = new JPasswordField();		//비밀번호확인 패스워드필드
		tfPWchk.setSize(300, 30);
		tfPWchk.setLocation(0, 310);
		jpInfL.add(tfPWchk);
		
		lblPassport = new JLabel("여권번호");	//여권번호 라벨
		lblPassport.setSize(300, 30);
		lblPassport.setLocation(0, 350);
		lblPassport.setFont(fontNanumGothic18);
		jpInfL.add(lblPassport);
		
		lblPassportEx = new JLabel("예) M00000000 (여권번호 9자)");		//여권번호예시 라벨
		lblPassportEx.setSize(300, 30);
		lblPassportEx.setLocation(100, 350);
		lblPassportEx.setFont(fontNanumGothic11);
		lblPassportEx.setForeground(Color.gray);
		jpInfL.add(lblPassportEx);
		
		try {
			MaskFormatter format = new MaskFormatter("*********");	//여권번호 포맷형식 지정
			tfPassport = new JFormattedTextField(format);			//여권번호 포맷텍스트필드 설정
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
		
		lblBirth = new JLabel("생년월일");		//생년월일 라벨
		lblBirth.setSize(300, 30);
		lblBirth.setLocation(0, 0);
		lblBirth.setFont(fontNanumGothic18);
		jpInfR.add(lblBirth);
		
		lblBirthEx = new JLabel("예) 20020214");		//생년월일 예시 라벨
		lblBirthEx.setSize(300, 30);
		lblBirthEx.setLocation(100, 0);
		lblBirthEx.setFont(fontNanumGothic11);
		lblBirthEx.setForeground(Color.gray);
		jpInfR.add(lblBirthEx);
		
		try {
			MaskFormatter format = new MaskFormatter("########");	//생년월일 포맷형식 지정
			tfBirth = new JFormattedTextField(format);				//생년월일 포맷텍스트필드
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfBirth.setSize(300, 30);
		tfBirth.setLocation(0, 30);
		tfBirth.setBackground(Color.white);
		jpInfR.add(tfBirth);
		
		lblSex = new JLabel("성별");		//성별 라벨
		lblSex.setSize(300, 30);
		lblSex.setLocation(0, 70);
		lblSex.setFont(fontNanumGothic18);
		jpInfR.add(lblSex);
		
		rg = new ButtonGroup();	//성별 버튼그룹
		rbMan = new JRadioButton("남");	//남성 라디오버튼
		rbMan.setSelected(true);
		rbMan.setSize(50, 30);
		rbMan.setLocation(0, 100);
		rbMan.setBackground(Color.white);
		rbWoman = new JRadioButton("여");	//여성 라디오버튼
		rbWoman.setSize(50, 30);
		rbWoman.setLocation(80, 100);
		rbWoman.setBackground(Color.white);
		rbWoman.setSelected(false);
		rg.add(rbMan);
		rg.add(rbWoman);
		jpInfR.add(rbMan);
		jpInfR.add(rbWoman);
		
		lblEmail = new JLabel("이메일 주소");	//이메일주소 라벨
		lblEmail.setSize(300, 30);
		lblEmail.setLocation(0, 140);
		lblEmail.setFont(fontNanumGothic18);
		jpInfR.add(lblEmail);
		
		lblEmailEx = new JLabel("예) kyeonw1109@gmail.com");	//이메일주소 예시라벨
		lblEmailEx.setSize(300, 30);
		lblEmailEx.setLocation(120, 140);
		lblEmailEx.setFont(fontNanumGothic11);
		lblEmailEx.setForeground(Color.gray);
		jpInfR.add(lblEmailEx);
		
		tfEmail = new JTextField();		//이메일주소 텍스트필드
		tfEmail.setSize(300, 30);
		tfEmail.setLocation(0, 170);
		jpInfR.add(tfEmail);
		
		lblPhone = new JLabel("휴대전화 번호");	//휴대전화번호 라벨
		lblPhone.setSize(300, 30);
		lblPhone.setLocation(0, 210);
		lblPhone.setFont(fontNanumGothic18);
		jpInfR.add(lblPhone);
		
		lblPhoneEx = new JLabel("예) 01012345678");	//휴대전화번호 예시라벨
		lblPhoneEx.setSize(300, 30);
		lblPhoneEx.setLocation(140, 210);
		lblPhoneEx.setFont(fontNanumGothic11);
		lblPhoneEx.setForeground(Color.gray);
		jpInfR.add(lblPhoneEx);
		
		
		try {
			MaskFormatter format = new MaskFormatter("###########");	//휴대전화번호 포맷형식 지정
			tfPhone = new JFormattedTextField(format);	//휴대전화번호 포맷텍스트필드
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfPhone.setSize(300, 30);
		tfPhone.setLocation(0, 240);
		jpInfR.add(tfPhone);
		
		
		lblAdd = new JLabel("마케팅 및 광고 활용 동의");	//정보동의라벨
		lblAdd.setSize(300, 30);
		lblAdd.setLocation(0, 280);
		lblAdd.setFont(fontNanumGothic18);
		jpInfR.add(lblAdd);
		
		chkNewsLetter = new JCheckBox("뉴스레터(회원 소식지 등)");	//뉴스레터 체크박스
		chkNewsLetter.setSize(300, 30);
		chkNewsLetter.setLocation(0, 310);
		chkNewsLetter.setBackground(Color.white);
		chkNewsLetter.setSelected(false);
		jpInfR.add(chkNewsLetter);
		
		chkPromotion = new JCheckBox("프로모션 정보(이벤트, 할인 쿠폰)");	//프로모션 체크박스
		chkPromotion.setSize(300, 30);
		chkPromotion.setLocation(0, 340);
		chkPromotion.setBackground(Color.white);
		chkPromotion.setSelected(false);
		jpInfR.add(chkPromotion);

		chkSMS = new JCheckBox("SMS 수신 동의");	//SMS 체크박스
		chkSMS.setSize(300, 30);
		chkSMS.setLocation(0, 370);
		chkSMS.setBackground(Color.white);
		chkSMS.setSelected(false);
		jpInfR.add(chkSMS);
		
		btnSignUp = new JButton("회원가입");	//회원가입 버튼
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
		
		//아이디 확인
		if (obj == btnchk && !(tfID.getText().isEmpty())) {	
			if (tfID.getText().isEmpty()) {	//아이디 입력 되어있는가 확인
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			} 
			if (!idVCheck) {
				if (idCheck(tfID.getText())) {	//아이디 중복확인
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");	//중복 알림
				} else {	//중복 아닐 때
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");	//사용가능아이디 알림
					btnchk.setText("다시입력");	//버튼 텍스트 변경
					tfID.setEnabled(false);		//아이디 텍스트필드 비활성화
				}
			} else {	//아이디 중복확인 완료 후 버튼의 텍스트가 "다시입력"일 경우 
				tfID.setText("");	//아이디 필드 초기화
				tfID.setEnabled(true);	//아이디 텍스트필드 활성화
				btnchk.setText("중복체크");	//버튼 텍스트변경
				idVCheck = false;	//아이디 중복확인여부 미완료로 설정
			}
		}
		else if (obj == btnSignUp) {
			
			
			if (tfLNKor.getText().isEmpty()) {	//한글이름 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "한글 이름을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfLNKor.requestFocus();
			} else if (tfLNEng.getText().isEmpty()) { //영문이름 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "영문 이름을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfLNEng.requestFocus();
			} else if(tfID.getText().isEmpty()) { //회원아이디 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "회원 아이디를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfID.requestFocus();
			} else if (tfPW.getText().isEmpty()) { //비밀번호 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPW.requestFocus();
			} else if (tfPWchk.getText().isEmpty()) { //비밀번호확인 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPWchk.requestFocus();
			} else if (tfPassport.getText().isEmpty()) { //여권번호 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "여권번호를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPassport.requestFocus();
			} else if (tfBirth.getText().isEmpty()) { //생년월일 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfBirth.requestFocus();
			} else if (tfEmail.getText().isEmpty()) { //이메일주소 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "이메일 주소를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfEmail.requestFocus();
			} else if (tfPhone.getText().isEmpty()) { //휴대전화번호 필드 비어있을경우 팝업창 띄우고 포커스 설정
				JOptionPane.showMessageDialog(null, "휴대전화 번호를 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
				tfPhone.requestFocus();
			} else if(!tfPW.getText().equals(tfPWchk.getText())) { //비밀번호, 비밀번호 확인필드 같은지 확인
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "회원가입오류", JOptionPane.ERROR_MESSAGE);
			} else if(idVCheck == false) {	//아이디 중복체크 했는지 확인
				JOptionPane.showMessageDialog(null, "아이디 중복체크를 해주세요.", "회원가입오류", JOptionPane.ERROR_MESSAGE);
			} else {
				if (!korVCheck) {	//한글이름필드에 한글만 적었는지 확인
					if (!korCheck(tfLNKor.getText())) {
						JOptionPane.showMessageDialog(null, "한글이름에 한글만 입력해주세요");
					}
				} else if (!engVCheck) { //영어이름 필드에 영어만 적었는지 확인
					if (!engCheck(tfLNEng.getText())) {
						JOptionPane.showMessageDialog(null, "영어이름에 영어만 입력해주세요");
					}
				}
//					else if (!emaVCheck) { //이메일 필드 형식 확인
//					if (!emaCheck(tfEmail.getText())) {
//						JOptionPane.showMessageDialog(null, "이메일을 제대로 입력해주세요");
//					}
//				} else if (!idsetVCheck) {	//아이디필드 형식 확인
//					if (!idSetCheck(tfID.getText())) {
//						JOptionPane.showMessageDialog(null, "아이디는 영어와 숫자로만 작성해주세요");
//					}
//				}
				else if (!telVCheck) {	//핸드폰번호 중복확인
					if (telCheck(tfPhone.getText())) {
						JOptionPane.showMessageDialog(null, "중복된 전화번호입니다.");
					}
				} else if (!emailVCheck) { //이메일 중복확인
					if (emailCheck(tfEmail.getText())) {
						JOptionPane.showMessageDialog(null, "중복된 이메일입니다.");
					}
				} else if (!passportVCheck) { //여권번호 중복확인
					if (passportCheck(tfPassport.getText()))
						JOptionPane.showMessageDialog(null, "중복된 여권번호입니다.");
				}
				else {
					
					if (rbMan.isSelected()) {	//라디오버튼 값 여성 남성 구하기
						sex = rbMan.getText();
					} else {
						sex = rbWoman.getText();
					}
					
					if(chkNewsLetter.isSelected()) { //뉴스레터 선택 값 구하기
						news = 1;
					} else {
						news = 0;
					}
					if (chkPromotion.isSelected()) { //프로모션 선택 값 구하기
						pro = 1;
					} else {
						pro = 0;
					}
					if (chkSMS.isSelected()) { //SMS 선택 값 구하기
						sms = 1;
					} else {
						sms = 0;
					}
					
					birthday = tfBirth.getText();	//생년월일 값 받아오기
					
					
					try {
						LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd")); //생년월일 값 포맷설정
						
						//SQL문 설정
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
				
						try { //회원가입 완료시
							databaseClass.insert(sql); //sql문 설정
							JOptionPane.showMessageDialog(null, "회원가입되었습니다.", "회원가입완료", JOptionPane.INFORMATION_MESSAGE); //회원가입 완료 팝업창 띄우기
							setVisible(false); //창 닫기
						} catch (Exception e2) {	//sql문 설정 중 오류났을 때
							//e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다. 정보를 다시 확인해주세요.", "회원가입오류", JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (DateTimeParseException e2) {	//생년월일 값 입력받을 때 오류나면 생년월일 값 제대로 입력 안 한 것이므로 오류메세지 띄우기
						JOptionPane.showMessageDialog(null, "날짜를 제대로 입력해주세요", "회원가입오류", JOptionPane.OK_CANCEL_OPTION);
					}
					
					

					
				}
				
			}
		}
		
	}
	
	
	//아이디 중복 체크
	private boolean idCheck(String id) {

		String sql = "SELECT ID FROM user WHERE ID IN('" + id + "')";	//sql문으로 검색
		ResultSet rs = databaseClass.select(sql); //sql문으로 확인
		
		try {
			while(rs.next()) {
				return true;	//값이 있으면 idCheck에 true 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		idVCheck = true; //아이디 중복확인 값 설정
		return false;
	}

	//핸드폰번호 중복 체크
	private boolean telCheck(String tel) {
		
		String sql = "SELECT tel FROM user WHERE tel IN('" + tel + "')"; //sql문으로 검색
		ResultSet rs = databaseClass.select(sql); 
		
		try {
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		telVCheck = true; //아이디 중복확인 설정
		return false;
	}

	
	//이메일 중복 체크
	private boolean emailCheck(String email) {
		
		String sql = "SELECT email FROM user WHERE email IN('" + email + "')"; //sql문으로 검색
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
		
		String sql = "SELECT passport FROM user WHERE passport IN('" + passport + "')"; //sql문으로 검색
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
		return korVCheck = korV.matches(kor); //정규표현식과 비교
	}
	
	//영어이름 한글확인
	private boolean engCheck(String engV) {
		return engVCheck = engV.matches(eng); //정규표현식과 비교
	}

//	//이메일필드에 @ 들어가는지 확인
//	private boolean emaCheck(String emaV) {
//		return emaVCheck = emaV.matches(ema); //정규표현식과 비교
//	}
//
//	//아이디 영어+숫자만인지 확인(특수문자 없어야함)
//	private boolean idSetCheck(String idSet) {
//		return idsetVCheck = idSet.matches(idset); //정규표현식과 비교
//	}
	
}
