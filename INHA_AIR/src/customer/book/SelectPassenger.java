package customer.book;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;


//연우 - 승객선택
public class SelectPassenger extends JFrame implements ActionListener{

	// Title 및 사이즈 설정
	private String title = "승객 선택";
	private int width = 490, height = 540;
	
	//폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);		// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	
	
	private ImageIcon qsIcon = new ImageIcon("imgs/questionMark.png"); //물음표 이미지
	private Image imgQs = qsIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private ImageIcon imgQuestionIcon = new ImageIcon(imgQs);
	
	private ImageIcon plusIcon = new ImageIcon("imgs/plus.png"); //플러스 이미지
	private Image imgPlus = plusIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private ImageIcon imgPlusIcon = new ImageIcon(imgPlus);
	
	
	private ImageIcon minusIcon = new ImageIcon("imgs/minus.png"); //마이너스 이미지
	private Image imgMinus = minusIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private ImageIcon imgMinusIcon = new ImageIcon(imgMinus);
	
	
	private JPanel jpTitle; //제목패널
	private JLabel lblTitle; //제목라벨
	private JPanel jpAge; //나이 패널
	private JPanel jpNumSelect; //인원선택패널
	private JPanel jpAgeCal; //나이계산기 패널
	private JLabel lblAdult; //성인나이
	private JLabel lblInfant; //소아나이
	private JLabel lblChild; //유아나이
	private JButton btnAdult; //성인버튼
	private JButton btnInfant; //소아버튼
	private JButton btnChild; //유아버튼
	private JButton btnPlusAdult; //성인 인원 추가 버튼
	private JButton btnMinusAdult; //성인 인원 감소 버튼
	private JButton btnPlusInfant; //소아 인원 추가 버튼
	private JButton btnMinusInfant; //소아 인원 감소 버튼
	private JButton btnPlusChild; //유아 인원 추가 버튼
	private JButton btnMinusChild; //유아 인원 감소 버튼
	private JLabel lblNumAdult; //성인라벨
	private JLabel lblNumInfant; //소아라벨
	private JLabel lblNumChild; //유아라벨
	private JLabel lblAgeCal; //나이계산기 라벨
	private JLabel lblAgeGuide; //나이계산기 예시 라벨
	private JFormattedTextField tfAge; //나이계산기 포맷텍스트필드
	private JButton btnCalculate; //나이계산 버튼
	private JButton btnOk; //확인 버튼
	private BookForm bookForm;
	
	
	//성인, 소아, 유아, 총인원 값
	private int numAdult = 0;
	private int numInfant = 0;
	private int numChild = 0;
	private int numTotal = 0;
	private JPanel jpTotal;
	
	private JLabel lblTotal; //총인원 라벨
	private JLabel lblTotalNum; //총인원 값 라벨
	
	
	private String goDay = ""; //가는날 값
	private String birthday; //생년월일 값
	private Date dtGoday; //가는 날 date 값
	private Date dtBirthday; //오는 날 date 값
	private int compare; //비교
	private int birthYear; //생년월일 년도
	private int birthMonthDay; //생년월일 월
	private int goDayYear; //가는날 년도
	private int goMonthDay; //가는날 일
	
	public SelectPassenger(BookForm bookForm) {
		
		//this.mainForm = mainForm;
		this.bookForm = bookForm; //bookForm에 대한 정보
		
		//다시 열었을 때 값 설정
		this.numAdult = bookForm.getNumAdult();
		this.numInfant = bookForm.getNumInfant();
		this.numChild = bookForm.getNumChild();
		this.numTotal = bookForm.getNumTotal();
		this.goDay = bookForm.getGoDay();
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		Container c = getContentPane();
		c.setBackground(Color.white);
		setLayout(null);
		
		setPassenger();
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				tfAge.requestFocus();
			}
		});
		
		
		lblNumAdult.setText(Integer.toString(numAdult));
		lblNumInfant.setText(Integer.toString(numInfant));
		lblNumChild.setText(Integer.toString(numChild));
		lblTotalNum.setText(numTotal + "명");
		
		
		setVisible(true);
		
	}
	
	
	
	
	private void setPassenger() {
		
		//제목패널
		jpTitle = new JPanel();
		jpTitle.setLayout(null);
		jpTitle.setSize(200, 60);
		jpTitle.setLocation(20, 20);
		jpTitle.setBackground(Color.white);
		
		lblTitle = new JLabel("승객 선택"); 		//제목라벨
		lblTitle.setFont(fontNanumGothic20);
		lblTitle.setSize(150, 40);
		lblTitle.setLocation(0, 10);
		jpTitle.add(lblTitle);
		
		
		//총인원 패널
		jpTotal = new JPanel();
		jpTotal.setLayout(null);
		jpTotal.setSize(130, 60);
		jpTotal.setLocation(290, 20);
		jpTotal.setBackground(Color.white);
		
		lblTotal = new JLabel("총인원"); 		//총인원 라벨
		lblTotal.setFont(fontNanumGothic20);
		lblTotal.setSize(150, 40);
		lblTotal.setLocation(0, 10);
		
		lblTotalNum = new JLabel(numTotal + " 명"); 		//명 라벨
		lblTotalNum.setFont(fontNanumGothic20);
		lblTotalNum.setSize(150, 40);
		lblTotalNum.setLocation(70, 10);
		jpTotal.add(lblTotal);
		jpTotal.add(lblTotalNum);
		
		//연령패널
		jpAge = new JPanel();
		jpAge.setLayout(null);
		jpAge.setSize(90, 200);
		jpAge.setLocation(20, 90);
		jpAge.setBackground(Color.white);
		jpAge.setLayout(new GridLayout(3, 2));
		
		lblAdult = new JLabel("성인"); 	//성인라벨
		lblAdult.setFont(fontNanumGothic18);
		btnAdult = new JButton(imgQuestionIcon); //국제선 만 12세 이상, 국내선 만 13세 이상
		btnAdult.setBorderPainted(false); //버튼 윤곽선 제거
		btnAdult.setContentAreaFilled(false); //버튼배경 제거
		btnAdult.addActionListener(this);
		
		lblInfant = new JLabel("소아"); //소아라벨
		lblInfant.setFont(fontNanumGothic18);
		btnInfant = new JButton(imgQuestionIcon); //국제선 만 12세 미만, 국내선 만 13세 미만
		btnInfant.setBorderPainted(false); //버튼 윤곽선 제거
		btnInfant.setContentAreaFilled(false); //버튼배경 제거
		btnInfant.addActionListener(this);
		
		lblChild = new JLabel("유아"); //유아라벨
		lblChild.setFont(fontNanumGothic18);
		btnChild = new JButton(imgQuestionIcon); //만 2세 미만
		btnChild.setBorderPainted(false); //버튼 윤곽선 제거
		btnChild.setContentAreaFilled(false); //버튼배경 제거
		btnChild.addActionListener(this);
		
		jpAge.add(lblAdult);
		jpAge.add(btnAdult);
		jpAge.add(lblInfant);
		jpAge.add(btnInfant);
		jpAge.add(lblChild);
		jpAge.add(btnChild);

		
		//인원선택 패널
		jpNumSelect = new JPanel();
		jpNumSelect.setLayout(null);
		jpNumSelect.setSize(200, 200);
		jpNumSelect.setLocation(250, 90);
		jpNumSelect.setBackground(Color.white);
		jpNumSelect.setLayout(new GridLayout(3, 3));
		
		btnPlusAdult = new JButton(imgPlusIcon); //성인 인원 +버튼
		btnPlusAdult.setBorderPainted(false); //버튼 윤곽선 제거
		btnPlusAdult.setContentAreaFilled(false); //버튼배경 제거
		btnPlusAdult.addActionListener(this);
		
		lblNumAdult = new JLabel("0"); //성인인원
		lblNumAdult.setFont(fontNanumGothic18);
		lblNumAdult.setHorizontalAlignment(JLabel.CENTER);
		
		btnMinusAdult = new JButton(imgMinusIcon); //성인인원 -버튼
		btnMinusAdult.setBorderPainted(false); //버튼 윤곽선 제거
		btnMinusAdult.setContentAreaFilled(false); //버튼배경 제거
		btnMinusAdult.addActionListener(this);
		
		btnPlusInfant = new JButton(imgPlusIcon); //소아인원 +버튼
		btnPlusInfant.setBorderPainted(false); //버튼 윤곽선 제거
		btnPlusInfant.setContentAreaFilled(false); //버튼배경 제거
		btnPlusInfant.addActionListener(this);
		
		lblNumInfant = new JLabel("0"); //소아인원 라벨
		lblNumInfant.setFont(fontNanumGothic18);
		lblNumInfant.setHorizontalAlignment(JLabel.CENTER);
		
		btnMinusInfant = new JButton(imgMinusIcon); //소아인원 -버튼
		btnMinusInfant.setBorderPainted(false); //버튼 윤곽선 제거
		btnMinusInfant.setContentAreaFilled(false); //버튼배경 제거
		btnMinusInfant.addActionListener(this);
		
		btnPlusChild = new JButton(imgPlusIcon); //유아 인원 +버튼
		btnPlusChild.setBorderPainted(false); //버튼 윤곽선 제거
		btnPlusChild.setContentAreaFilled(false); //버튼배경 제거
		btnPlusChild.addActionListener(this);
		
		lblNumChild = new JLabel("0"); //유아 인원 라벨
		lblNumChild.setFont(fontNanumGothic18);
		lblNumChild.setHorizontalAlignment(JLabel.CENTER);
		
		btnMinusChild = new JButton(imgMinusIcon); //유아 인원 -버튼
		btnMinusChild.setBorderPainted(false); //버튼 윤곽선 제거
		btnMinusChild.setContentAreaFilled(false); //버튼배경 제거
		btnMinusChild.addActionListener(this);

		jpNumSelect.add(btnMinusAdult);
		jpNumSelect.add(lblNumAdult);
		jpNumSelect.add(btnPlusAdult);
		jpNumSelect.add(btnMinusInfant);
		jpNumSelect.add(lblNumInfant);
		jpNumSelect.add(btnPlusInfant);
		jpNumSelect.add(btnMinusChild);
		jpNumSelect.add(lblNumChild);
		jpNumSelect.add(btnPlusChild);
		
		//나이계산기 패널  및 확인버튼
		jpAgeCal = new JPanel();
		jpAgeCal.setLayout(null);
		jpAgeCal.setSize(430, 150);
		jpAgeCal.setLocation(20, 300);
		jpAgeCal.setBackground(Color.white);
		
		lblAgeCal = new JLabel("나이계산기"); //나이계산기 라벨
		lblAgeCal.setFont(fontNanumGothic12);
		lblAgeCal.setSize(150, 40);
		lblAgeCal.setLocation(10, 20);
		lblAgeGuide = new JLabel("생년월일(예: 20020214)"); //나이계산기 예시
		lblAgeGuide.setFont(fontNanumGothic12);
		lblAgeGuide.setSize(150, 40);
		lblAgeGuide.setLocation(80, 20);
		lblAgeGuide.setForeground(Color.gray);
		
		try {
			MaskFormatter format = new MaskFormatter("########"); //생년월일 포맷
			tfAge = new JFormattedTextField(format); //생년월일 입력 필드
		} catch (ParseException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "값을 제대로 입력해주세요", "나이계산기", JOptionPane.OK_CANCEL_OPTION);
		}
		tfAge.setFont(fontNanumGothic12);
		tfAge.setSize(300, 30);
		tfAge.setLocation(10, 50);
		
		btnCalculate = new JButton("계산하기"); //계산하기 버튼
		btnCalculate.setFont(fontNanumGothic12);
		btnCalculate.setSize(100, 30);
		btnCalculate.setLocation(315, 50);
		btnCalculate.setBorder(new LineBorder(Color.gray,1));
		//btnCalculate.setContentAreaFilled(false); //버튼배경 제거
		btnCalculate.setBackground(Color.white);
		btnCalculate.addActionListener(this);
		
		btnOk = new JButton("확인"); //확인버튼
		btnOk.setFont(fontNanumGothic15);
		btnOk.setForeground(Color.white);
		btnOk.setSize(100, 30);
		btnOk.setLocation(150, 100);
		btnOk.setBorderPainted(false); //버튼 윤곽선 제거
		btnOk.setBackground(new Color(10,90,150));
		btnOk.addActionListener(this);
		
		jpAgeCal.add(lblAgeCal);
		jpAgeCal.add(lblAgeGuide);
		jpAgeCal.add(tfAge);
		jpAgeCal.add(btnCalculate);
		jpAgeCal.add(btnOk);
		
		add(jpTitle);
		add(jpTotal);
		add(jpAge);
		add(jpNumSelect);
		add(jpAgeCal);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		
		
		if (obj == btnAdult) { //성인 나이 기준 설명
			JOptionPane.showMessageDialog(null, "국제선 만 12세 이상, 국내선 만 13세 이상", "성인나이", JOptionPane.OK_CANCEL_OPTION);
		} else if (obj == btnInfant) { //소아 나이 기준 설명
			JOptionPane.showMessageDialog(null, "국제선 만 12세 미만, 국내선 만 13세 미만", "소아나이", JOptionPane.OK_CANCEL_OPTION);
		} else if (obj == btnChild) { //유아 나이 기준 설명
			JOptionPane.showMessageDialog(null, "2세 미만", "유아나이", JOptionPane.OK_CANCEL_OPTION);
		}
		
		
		if (numAdult+numInfant<9) {
			
			if (obj == btnPlusAdult) { //성인라벨 인원 추가
				numAdult++;
				lblNumAdult.setText(Integer.toString(numAdult));
			}  else if (obj == btnPlusInfant) { //소아라벨 인원추가
				numInfant++;
				lblNumInfant.setText(Integer.toString(numInfant));
			} 
		}
		
		if(numChild<numAdult) {
			if (obj == btnPlusChild) { //유아라벨 인원추가
				numChild++;
				lblNumChild.setText(Integer.toString(numChild));
			} 	
		}
		
		if (obj == btnMinusAdult) { //성인라벨 인원 감소
			if (numAdult>0)
				numAdult--;
			lblNumAdult.setText(Integer.toString(numAdult));
		}
		else if (obj == btnMinusInfant) { //소아라벨 인원감소
			if (numInfant>0)
				numInfant--;
			lblNumInfant.setText(Integer.toString(numInfant));
		}
		else if (obj == btnMinusChild) { //유아라벨 인원감소
			if (numChild>0)
				numChild--;
			lblNumChild.setText(Integer.toString(numChild));
		}
		
		if (obj == btnCalculate) {		//나이계산기 버튼 눌렀을 때
			
			if (goDay.isEmpty()) { //탑승일 선택 안 했을 경우
				JOptionPane.showMessageDialog(null, "탑승일 선택 후 나이계산이 가능합니다.", "나이계산", JOptionPane.OK_CANCEL_OPTION);
			} else { //탑승일 선택 했으면
				
				birthday = tfAge.getText(); //생년월일 값 받아오기
				
				
				try {
					
					LocalDate parsedBirthDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd")); //생년월일 값 date에 넣어보기
					birthYear = parsedBirthDate.getYear();
					birthMonthDay = Integer.parseInt(birthday.substring(5,8));
					
					LocalDate parsedgoDay = LocalDate.parse(goDay, DateTimeFormatter.ofPattern("yyyyMMdd")); //생년월일 값 date에 넣어보기
					goDayYear = parsedgoDay.getYear();
					goMonthDay = Integer.parseInt(goDay.substring(5,8));
					
					int age = goDayYear - birthYear;	//나이 구하기
					
					
					if (age == 19) { //19세일 경우 생일 지났으면 그대로 후 성인, 안 지났으면 1살 줄이고 미성년
						if (birthMonthDay <= goMonthDay) {
							JOptionPane.showMessageDialog(null, "가는 날(" + goDay + ") 기준 만" + age + "세 성인입니다.", "나이계산", JOptionPane.OK_CANCEL_OPTION);
						} else {
							JOptionPane.showMessageDialog(null, "가는 날(" + goDay + ") 기준 만" + (age-1) + "세 미성년입니다.", "나이계산", JOptionPane.OK_CANCEL_OPTION);
						}
					} else if(age > 19){ //19세 이상일 경우 성인, 생일 안 지났으면 1살 줄이기
						if (birthMonthDay <= goMonthDay) {
							JOptionPane.showMessageDialog(null, "가는 날(" + goDay + ") 기준 만" + age + "세 성인입니다.", "나이계산", JOptionPane.OK_CANCEL_OPTION);
						} else {
							JOptionPane.showMessageDialog(null, "가는 날(" + goDay + ") 기준 만" + (age-1) + "세 성인입니다.", "나이계산", JOptionPane.OK_CANCEL_OPTION);
						}
					} else { //19살 이하일 경우 미성년, 생일 안 지났으면 1살 줄이기
						if (birthMonthDay <= goMonthDay) {
							JOptionPane.showMessageDialog(null, "가는 날(" + goDay + ") 기준 만" + age + "세 미성년입니다.", "나이계산", JOptionPane.OK_CANCEL_OPTION);
						} else {
							JOptionPane.showMessageDialog(null, "가는 날(" + goDay + ") 기준 만" + (age-1) + "세 미성년입니다.", "나이계산", JOptionPane.OK_CANCEL_OPTION);
						}
					}
					
				} catch (DateTimeParseException e2) { //생년월일 값 date에 넣었을 때 오류나면 잘못 입력한 것이므로 설정
					JOptionPane.showMessageDialog(null, "값을 제대로 입력해주세요", "나이계산기", JOptionPane.OK_CANCEL_OPTION);
				}
				
			}
			
		}
		
		numTotal = numAdult + numInfant + numChild; //총인원 구하기
		lblTotalNum.setText(numTotal + "명");
		
		if (obj == btnOk) { //확인버튼 눌렀을 때
			
			if (numTotal > 0) { //총인원 값이 1명 이상이면 값 입력하기
				int result = JOptionPane.showConfirmDialog(null, "성인 " + numAdult + "명, 소아 " + numInfant + "명, 유아 " + numChild + "명으로 총 " + numTotal + "명이 맞습니까?", "탑승인원 선택", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION) {
					
					bookForm.setNumAdult(numAdult);
					bookForm.setNumInfant(numInfant);
					bookForm.setNumChild(numChild);
					bookForm.setNumTotal(numTotal);
					bookForm.setPassenger();
					
					setVisible(false);
					
				}
			} else { //인원 선택 안 했으면 선택하라고 띄우기
				JOptionPane.showMessageDialog(null, "1명 이상으로 선택해주세요", "인원선택", JOptionPane.OK_CANCEL_OPTION);
			}
		}
	}

}