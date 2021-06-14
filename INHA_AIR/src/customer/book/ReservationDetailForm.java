package customer.book;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import DataBase.databaseClass;
import Management.Form.HintTextField;
import customer.start.MainMenuForm;

public class ReservationDetailForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 폰트
	Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
	Font fontNanumGothicP15 = new Font("NanumGothic", Font.PLAIN, 15);
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);
	
	// 색상
	Color colorLogo = new Color(24, 62, 111);	
	Color colorBtn = new Color(10,90,150);
	
	// 컴포넌트
	private JButton btnMainMenu;
	private JPanel jpSet;
	
	// 컴포넌트
	private JPanel jpInputInfor, jpBtns;
	private JLabel lblInformation, lblFamilyNameKor, lblNameKor, lblFamilyNameEng, lblNameEng, 
					lblSex, lblPassport, lblTel, lblEmail, lblBirth;
	private HintTextField tfFamilyNameKor, tfNameKor, tfFamilyNameEng, tfNameEng,
							tfPassport, tfTel, tfEmail, tfBirth;
	private ButtonGroup bgSex;
	private JRadioButton rbWoman, rbMan;
	private JCheckBox cbAgree;
	private JButton btnOK, btnBaggage;
	
	
	// Forms
	private MainMenuForm mainMenuForm;
	private SelectBaggageForm baggageFrom;
	private SelectPaymentForm paymentForm;
	
	// 예매 정보 - reserveNum을 이용해 reservation 테이블에서 가져옴
	private String reserveNum = "test001010";			// 예매 번호(테스트값)
	private String GOscheduleNo = "";
	private String COMscheduleNo = "";
	private int people = 0; // 총 인원
	private int adult = 0;	// 성인 인원
	private int child = 0;	// 소아 인원
	private int infant = 0;	// 유아 인원
	private int pay = 0;	// 금액
	
	// 입력된 승객 수 카운트
	private int count = 0;
	
	// 데이터 저장용
	private String id;
	private String nameKOR;		// 한글이름
	private String nameENG;		// 영문이름
	private String sex;				// 성별(남 또는 여)
	private String passport;			// 여권번호
	private String birth;				// 생년월일
	private String tel;			// 연락처
	private String email;				// 이메일
	private int agree = 0;			// 수신동의
	private String baggage = "0";			// 추과수하물 (기본값: 0)

	public void setAddHydrate(String baggage) {
		this.baggage = baggage;
	}

	// 예원 - 시작 화면
	public ReservationDetailForm(String reserveNum, String id) {
		this.reserveNum = reserveNum;
		this.id = id;
		
		// DB 정보 - 테스트 소스
		String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
		String dbID="inhaair";
		String dbPassword="1234";
		// 데이터베이스 연결 - 테스트 소스
		databaseClass.connect(dbURL, dbID, dbPassword);
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		// 레이아웃 설정
		setLayout(null);
		
		// 시작 버튼
		btnMainMenu = new JButton("INHA AIR");
		btnMainMenu.setSize(200, 50);
		btnMainMenu.setLocation(10, 10);
		btnMainMenu.setFont(fontArial30);
		btnMainMenu.setForeground(colorLogo);
		btnMainMenu.setBorderPainted(false);
		btnMainMenu.setBackground(Color.WHITE);
		
		// 예매창 패널
		jpSet = new JPanel(new BorderLayout(10, 35));
		jpSet.setSize(1000, 600);
		jpSet.setLocation(50, 90);
		jpSet.setBackground(Color.WHITE);
		
		// 선택한 항공편 정보 받아오기
		selectReserve();
		
		// 정보 입력 레이아웃
		setInput();
		
		// 버튼 레이아웃
		setBtns();
		
		// 리스너
		btnMainMenu.addActionListener(this);
		
		// 컴포넌트 붙이기
		add(btnMainMenu);
		add(jpSet);
		
		setVisible(true);
		
	}

	// 선택한 항공편 정보 받아오기
	private void selectReserve() {
		// 선택한 항공편 정보 받아오기
		String sql = "SELECT * FROM reservation WHERE reserveNum = '" + reserveNum + "'";
		System.out.println(sql);
		
		ResultSet rs = databaseClass.select(sql);
		try {
			while(rs.next()) {
				GOscheduleNo = rs.getString("GOscheduleNo");
				COMscheduleNo = rs.getString("COMscheduleNo");
				adult = Integer.parseInt(rs.getString("adult"));
				child = Integer.parseInt(rs.getString("child"));
				infant = Integer.parseInt(rs.getString("infant"));
				pay = Integer.parseInt(rs.getString("pay"));
			}
			
			people = adult + child;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	// 버튼 레이아웃
	private void setBtns() {
		String str = "";
		if( people > 1) {
			str = "다음 승객 입력하기";
		} else {
			str = "결제하기";
		}
		
		
		jpBtns = new JPanel(new GridLayout(1, 3, 10, 10));
		jpBtns.setBackground(Color.WHITE);
		
		// 버튼
		btnBaggage = new JButton("초과수하물");
		btnBaggage.setFont(fontNanumGothic20);
		btnBaggage.setBackground(colorBtn);
		btnBaggage.setForeground(Color.WHITE);

		btnOK = new JButton(str);
		btnOK.setFont(fontNanumGothic20);
		btnOK.setBackground(colorBtn);
		btnOK.setForeground(Color.WHITE);
		
		// 리스너
		btnBaggage.addActionListener(this);
		btnOK.addActionListener(this);
		
		// 컴포넌트 붙이기
		jpBtns.add(btnBaggage);
		jpBtns.add(btnOK);
		
		jpSet.add(jpBtns, BorderLayout.SOUTH);
	}


	// 정보 입력 레이아웃
	private void setInput() {
		
		jpInputInfor = new JPanel(new GridLayout(12, 1, 10, 10));
		jpInputInfor.setBackground(Color.WHITE);
		
		// 라벨
		String label = "";
		if(people > 1) {
			label = "승객1 정보 입력";
		} else {
			label = "승객 정보 입력";
		}
		lblInformation = new JLabel(label);
		lblInformation.setFont(fontNanumGothic25);
		
		// 한글 이름 라벨
		JPanel jpLblNameKor = new JPanel(new GridLayout(1, 2, 10, 10));
		jpLblNameKor.setBackground(Color.WHITE);
		lblFamilyNameKor = new JLabel("한글 성");
		lblFamilyNameKor.setFont(fontNanumGothic15);
		lblNameKor = new JLabel("한글 이름");
		lblNameKor.setFont(fontNanumGothic15);
		jpLblNameKor.add(lblFamilyNameKor);
		jpLblNameKor.add(lblNameKor);
		
		// 한글 이름 택스트박스
		JPanel jpTfNameKor = new JPanel(new GridLayout(1, 2, 10, 10));
		jpTfNameKor.setBackground(Color.WHITE);
		tfFamilyNameKor = new HintTextField("예: 이");
		tfFamilyNameKor.setFont(fontNanumGothicP15);
		tfNameKor = new HintTextField("예: 은선");
		tfNameKor.setFont(fontNanumGothicP15);
		jpTfNameKor.add(tfFamilyNameKor);
		jpTfNameKor.add(tfNameKor);
		
		// 영문 이름 라벨
		JPanel jpLblNameEng = new JPanel(new GridLayout(1, 2, 10, 10));
		jpLblNameEng.setBackground(Color.WHITE);
		lblFamilyNameEng = new JLabel("영문 성");
		lblFamilyNameEng.setFont(fontNanumGothic15);
		lblNameEng = new JLabel("영문 이름");
		lblNameEng.setFont(fontNanumGothic15);
		jpLblNameEng.add(lblFamilyNameEng);
		jpLblNameEng.add(lblNameEng);
		
		// 영문 이름 텍스트필드
		JPanel jpTfNameEng = new JPanel(new GridLayout(1, 2, 10, 10));
		jpTfNameEng.setBackground(Color.WHITE);
		tfFamilyNameEng = new HintTextField("예: LEE");
		tfFamilyNameEng.setFont(fontNanumGothicP15);
		tfNameEng = new HintTextField("예: EUNSEON");
		tfNameEng.setFont(fontNanumGothicP15);
		jpTfNameEng.add(tfFamilyNameEng);
		jpTfNameEng.add(tfNameEng);
		
		// 성별 라벨
		lblSex = new JLabel("성별");
		lblSex.setFont(fontNanumGothic15);
		
		// 성별 라디오버튼
		JPanel jpSex = new JPanel(new GridLayout(1, 2, 10, 10));
		jpSex.setBackground(Color.WHITE);
		bgSex = new ButtonGroup();
		rbMan = new JRadioButton("남자", true);
		rbMan.setFont(fontNanumGothic15);
		rbMan.setBackground(Color.WHITE);
		rbWoman = new JRadioButton("여자", false);
		rbWoman.setFont(fontNanumGothic15);
		rbWoman.setBackground(Color.WHITE);
		bgSex.add(rbMan);
		bgSex.add(rbWoman);
		jpSex.add(rbMan);
		jpSex.add(rbWoman);
		
		// 여권번호/생년월일 라벨
		JPanel jpLblPss = new JPanel(new GridLayout(1, 2, 10, 10));
		jpLblPss.setBackground(Color.WHITE);
		lblPassport = new JLabel("여권 번호");
		lblPassport.setFont(fontNanumGothic15);
		lblBirth = new JLabel("생년월일");
		lblBirth.setFont(fontNanumGothic15);
		jpLblPss.add(lblPassport);
		jpLblPss.add(lblBirth);
		
		// 여권번호/생년월일 텍스트필드
		JPanel jpTfPss = new JPanel(new GridLayout(1, 2, 10, 10));
		jpTfPss.setBackground(Color.WHITE);
		tfPassport = new HintTextField("예: M46910832");
		tfPassport.setFont(fontNanumGothicP15);
		tfBirth = new HintTextField("예: 20010414");
		tfBirth.setFont(fontNanumGothicP15);
		jpTfPss.add(tfPassport);
		jpTfPss.add(tfBirth);
		
		// 연락처 라벨
		JPanel jpLblTel = new JPanel(new GridLayout(1, 2, 10, 10));
		jpLblTel.setBackground(Color.WHITE);
		lblTel = new JLabel("연락처");
		lblTel.setFont(fontNanumGothic15);
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(fontNanumGothic15);
		jpLblTel.add(lblTel);
		jpLblTel.add(lblEmail);
		
		// 연락처 택스트필드
		JPanel jptfTel = new JPanel(new GridLayout(1, 2, 10, 10));
		jptfTel.setBackground(Color.WHITE);
		tfTel = new HintTextField("예: 01092032796");
		tfTel.setFont(fontNanumGothicP15);
		tfEmail = new HintTextField("예: oow214@gmail.com");
		tfEmail.setFont(fontNanumGothicP15);
		jptfTel.add(tfTel);
		jptfTel.add(tfEmail);
		
		// 동의 
		cbAgree = new JCheckBox("예약 여정의 정보를 이메일과 SMS로 수신하는 것에 동의합니다.");
		cbAgree.setFont(fontNanumGothic15);
		cbAgree.setBackground(Color.WHITE);
		
		// 컴포넌트 붙이기
		jpInputInfor.add(lblInformation);
		jpInputInfor.add(jpLblNameKor);
		jpInputInfor.add(jpTfNameKor);
		jpInputInfor.add(jpLblNameEng);
		jpInputInfor.add(jpTfNameEng);
		jpInputInfor.add(lblSex);
		jpInputInfor.add(jpSex);
		jpInputInfor.add(jpLblPss);
		jpInputInfor.add(jpTfPss);
		jpInputInfor.add(jpLblTel);
		jpInputInfor.add(jptfTel);
		jpInputInfor.add(cbAgree);
		
		
		jpSet.add(jpInputInfor);
	}



	public static void main(String[] args) {
		new ReservationDetailForm("test001010", "test1");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMainMenu) {
			
			clickMain();
			
		} else if(obj == btnOK) {
			
			clickOK();
			
			
		} else if(obj == btnBaggage) {
			nameKOR = tfFamilyNameKor.getText().toString() + tfNameKor.getText().toString();
			baggageFrom = new SelectBaggageForm(this, nameKOR);
			
		} 
	}

	
	private void clickMain() {
		// 정보 입력된 승객이 있는지 확인
		String sql = "SELECT COUNT(*) FROM reservationDetail WHERE reserveNum = '" + reserveNum + "'";
		ResultSet rs = databaseClass.select(sql);
					
		//rouCount 에 해당 예약번호로 예약 진행중인 승객 수 저장
		int rouCount = 0;
		try {
			while(rs.next()) {
			rouCount = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
					
		int resdel = 0;
		if(rouCount == 0) {
			// 정보 입력된 승객이 없으면 
			
			// reservation 삭제
			resdel = delReservation();
			
			// 첫화면으로 이동
			mainMenuForm = new MainMenuForm();
			this.setVisible(false);
						
		} else {
			// 정보 입력된 승객이 있으면 승객 정보 삭제 
			sql = "DELETE FROM reservationDetail WHERE reserveNum = '" + reserveNum + "'";
						
			int result = databaseClass.delete(sql);
			if(result == 1) {
				// reservationDetail 테이블에 해당 예매 삭제 성공시
				// reservation 테이블에서 해당 예매 삭제
				resdel = delReservation();
							
				if(resdel == 1) {
					// reservation 테이블에 해당 예매 삭제 성공시
					// 첫 화면으로 이동
					mainMenuForm = new MainMenuForm();
					mainMenuForm.setId(id);
					this.setVisible(false);
				} else {
					// 삭제 실패시 다이얼로그 띄움
					JOptionPane.showMessageDialog(null, "첫 화면으로 이동할 수 없습니다.", "오류 안내", JOptionPane.WARNING_MESSAGE);
				}
				
			} else {
				// 삭제 실패시 다이얼로그 띄움
				JOptionPane.showMessageDialog(null, "첫 화면으로 이동할 수 없습니다.", "오류 안내", JOptionPane.WARNING_MESSAGE);
			}
		}
					
	}



	private void clickOK() {
		if(cbAgree.isSelected()) {
			// 수신 동의 했을 경우
			// 편도인지 왕복인지 확인
			if(COMscheduleNo == "") {
				// 편도일 경우
				// 정규식 확인
				boolean rs = checkType();
				
				if(rs) {
					insertInformationData(GOscheduleNo, 0);
				}

			} else {
				// 왕복일 경우
				// 동의 하고 좌석도 선택한 경우 정보 insert
				// 가는날 정보 insert
				boolean rs = checkType();
				
				if(rs) {
					insertInformationData(GOscheduleNo, 1);
				}
			}

		} else {
			// 수신 동의 하지 않았을 경우 안내 표시
			JOptionPane.showMessageDialog(null, "이메일과 SMS 수신 동의해주십시오.", "동의 안내", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}


	// 정규식확인
	private boolean checkType() {
		boolean status = false;
		
		status = checkKor(tfFamilyNameKor.getText().toString());
		if(status) {
			status = checkKor(tfNameKor.getText().toString());
			if(status) {
				status = checkEng(tfFamilyNameEng.getText().toString());
				if(status) {
					status = checkEng(tfNameEng.getText().toString());
					if(status) {
						status = checkPassport(tfPassport.getText().toString());
						if(status) {
							status = checkNum(tfBirth.getText().toString());
							if(status) {
								if(tfBirth.getText().toString().length() == 8) {
									status = checkTel(tfTel.getText().toString());
									if(status) {
										if(tfTel.getText().toString().length() == 11) {
											status = checkEmail(tfEmail.getText().toString());
											if(status) {
												return status;
											} else {
												JOptionPane.showMessageDialog(null, "이메일을 확인해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
											}
										} else {
											JOptionPane.showMessageDialog(null, "전화번호를 확인해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);	
										}
										
									} else {
										JOptionPane.showMessageDialog(null, "전화번호를 확인해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "생년월일을 8자리로 입력해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "생년월일을 숫자로 입력해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "여권번호를 영문과 숫자로 입력해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "영문 이름을 영문으로 입력해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "영문 성을 영문으로 입력해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "한글 이름을 한글로 입력해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "한글 성을 한글로 입력해주십시오.", "입력 안내", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return status;
	}

	// reservation 테이블에서 해당 예매 삭제
	private int delReservation() {
		int resdel = 0;
		
		String sql = "DELETE FROM reservation WHERE reserveNum = '" + reserveNum + "'";
		
		resdel = databaseClass.delete(sql);
		
		return resdel;
		
	}


	// 예매 정보 insert
	private void insertInformationData(String schedule, int flag) {
		
		// reservationDetail 테이블에 insert 하는 sql문
		String sql = "INSERT INTO reservationDetail "
				+ "(reserveNum, scheduleNo, nameKOR, nameENG, sex, passport, birth, tel, email, agree, baggage) "
				+ "VALUES('";
		
		nameKOR = tfFamilyNameKor.getText().toString() + tfNameKor.getText().toString();
		nameENG = tfFamilyNameEng.getText().toString() + tfNameEng.getText().toString();
		
		if(rbMan.isSelected()) {
			sex = "남";
		} else if(rbWoman.isSelected()) {
			sex = "여";
		}
		
		passport = tfPassport.getText().toString();
		birth = tfBirth.getText().toString();
		tel = tfTel.getText().toString();
		email = tfEmail.getText().toString();
		if(cbAgree.isSelected()) {
			agree = 1;
		}
		
		
		sql += reserveNum + "', '" + schedule + "', '" + nameKOR + "', '" + nameENG + "', '" + sex + "', '" + passport + "', '" + birth + "', '"
			+ tel + "', '" + email + "', " + agree + ", " + baggage + ")";
		
		System.out.println(sql);
		
		// sql문 수행
		int result = databaseClass.insert(sql);
		if(result == 1) {
			
			// flag - 0 : 가는날, 1 : 오는날
			if(flag == 1) {
				// 오는날 정보 insert
				insertInformationData(COMscheduleNo, 0);
			}
			else {
				
				count++;
				
				// 다음 승객 있는지 확인해서 결제로 넘어가기
				goNext();
			}
			
			
		} else {
			// insert 실패시 안내 다이얼로그
			JOptionPane.showMessageDialog(null, "입력한 정보를 확인해주십시오.", "예매 안내", JOptionPane.WARNING_MESSAGE);
		}
	}

	// 다음 승객 있는지 확인해서 결제로 넘어가기
	private void goNext() {
		
		// payment 테이블에 가격 업데이트
		int rs = upPay();
		
		if(rs == 1) {
			// 업데이트 성공시
			if(count == people) {
				// 결제 확인 화면으로 이동
				paymentForm = new SelectPaymentForm(reserveNum, id);
				this.setVisible(false);
			} else {
				// 라벨 변경
				lblInformation.setText("승객" + (count+1) + " 정보 입력");
				
				// 입력된 값 다 지우기
				tfReset();
				
				if((count+1) == people) {
					// 마지막 승객 일 경우 버튼을 결제하기로 변경
					btnOK.setText("결제 하기");
				}
			}
		} else {
			// 업데이트 실패시
			JOptionPane.showMessageDialog(null, "다시 시도해주세요.", "오류 안내", JOptionPane.WARNING_MESSAGE);
		}
	}


	// 금액 update
	private int upPay() {
		
		int baggageAddPay = 0;
		if(COMscheduleNo == "0") {
			// 편도일 경우
			baggageAddPay = (Integer.parseInt(baggage) *  50000);
		}
		else {
			// 왕복일 경우
			// 수하물 가격 2배
			baggageAddPay = (Integer.parseInt(baggage) *  50000) * 2;
		}
		
		// 가격 = 원래 가격 + 추가수하물 가격
		pay += baggageAddPay;
		
		// reservation 에 가격 업데이트
		String sql = "UPDATE reservation SET pay=" + pay + " WHERE reserveNum='" + reserveNum + "'";
		
		int rs = databaseClass.update(sql);
		
		return rs;
	}


	// 입력된 값 다 지우기
	private void tfReset() {
		// 변수 초기화
		nameKOR = "";
		nameENG = "";
		sex = "";
		passport = "";
		birth = "";
		tel = "";
		email = "";
		agree = 0;
		baggage = "0";
		
		// textField 초기화
		tfFamilyNameKor.setText("");
		tfNameKor.setText("");
		tfFamilyNameEng.setText("");
		tfNameEng.setText("");
		tfPassport.setText("");
		tfTel.setText("");
		tfEmail.setText("");
		tfBirth.setText("");
		cbAgree.setSelected(false);
	}

	// 한글 확인
	private boolean checkKor(String str) {
		String checkStr = "^[ㄱ-ㅎ가-힣]*$";
		boolean result = Pattern.matches(checkStr, str);
		return result;
	}
	
	// 영문 확인
	private boolean checkEng(String str) {
		String checkStr = "^[a-zA-Z]*$";
		boolean result = Pattern.matches(checkStr, str);
		return result;
	}
	
	// 숫자 확인
	private boolean checkNum(String str) {
		String checkStr = "^[0-9]*$";
		boolean result = Pattern.matches(checkStr, str);
		return result;
	}
	
	// 여권번호
	private boolean checkPassport(String str) {
		String checkStr = "^[0-9a-zA-Z]*$";
		boolean result = Pattern.matches(checkStr, str);
		return result;
	}
	
	// 이메일 확인
	private boolean checkEmail(String str) {
		String checkStr = "[0-9a-zA-Z]+(.[_a-z0-9]+)*@(?:\\w+\\.)+\\w+$";
		boolean result = Pattern.matches(checkStr, str);
		return result;
	}
	
	// 전화번호 확인
	private boolean checkTel(String str) {
		String checkStr = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
		boolean result = Pattern.matches(checkStr, str);
		return result;
	}
}
