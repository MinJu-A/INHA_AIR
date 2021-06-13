package be.Airport;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.main.MainForm;
import be.menu.MenuBar;

public class RestrictionInfo extends JFrame implements ActionListener {
	
	//폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);	// 나눔고딕 18
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 18
	Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 22
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 30
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	Color crNavy = new Color(30,30,170); // 수하물 유의사항 색
	private Color crBlue = new Color(20,100,250); //"운송제한품목" 색

	Font fontNanumGothic9P = new Font("NanumGothic", Font.PLAIN, 9);	// 나눔고딕 9, 보통
	Font fontNanumGothic12P = new Font("NanumGothic", Font.PLAIN, 12);	// 나눔고딕 12, 보통
	Font fontNanumGothic15P = new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 15, 보통
	Font fontNanumGothic18P = new Font("NanumGothic", Font.PLAIN, 18);	// 나눔고딕 18, 보통
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 폰트 사용 위함
//	private MainForm main;
	
	// 상단 메뉴바
	private be.menu.MenuBar menubar;
	private JLabel lblRestrict; // "수하물 안내" 제목 라벨
	private JLabel lblRestrictCont; //"수하물 안내" 내용
	private JLabel lblBanned; //
	private JLabel lblFlame; //"수하물 준비할 때"
	private JLabel lblFlameCont; //"수하물 준비 할때" 내용
	private JLabel lblBannedDetail; //"운송 제한 품목" 가기
	private JLabel lblBannedCont;//
	private JLabel lblGas;
	private JLabel lblGasCont;
	private JLabel lblWeapon;
	private JLabel lblWeaponCont;
	private JLabel lblDanger;
	private JLabel lblDangerCont;
	private Font underline, underline2;
	private JLabel lblAble;
	private JLabel lblAbleCont;
	private JLabel lblAbleDetail;
	private Font underline3;
	private Component lblConsignment;
	private Component lblConsignmentCont;
	private Component lblConsignmentDetail;
	private Component lblNotice;
	private Component lblNoticeCont;
	private Font underline4;
	private Component lblPdfFile;
	
	public RestrictionInfo() {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 레이아웃 설정
		setLayout(null);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
	
		// 상단 메뉴바
		setUpMenu();
		
		//시작
		lblRestrict = new JLabel("운송 제한 물품"); //큰 제목
		lblRestrict.setBounds(40,90 , 200, 50);
		lblRestrict.setFont(fontNanumGothic30);
		
		lblRestrictCont = new JLabel("안전한 항공 여행을 위해 반입이 제한되는 물품이 있습니다.");
		lblRestrictCont.setBounds(30,140 ,500, 20);
		lblRestrictCont.setFont(fontNanumGothic15P);
		
		lblBanned = new JLabel("항공기 반입금지 물품");// 제목 1
		lblBanned.setBounds(40,180 ,500, 30);
		lblBanned.setFont(fontNanumGothic25);
		lblBanned.setForeground(crNavy);
		
		lblBannedCont = new JLabel("<html>발화성 물질, 고압 가스 용기, 무기 및 폭발물 등은 휴대 수하물로 기내 반입하거나 위탁 수하물로 운송하는 것이 금지되어 있습니다.(휴대 X, 위탁 X)</html>");
		lblBannedCont.setBounds(30,215 ,1000, 20);
		lblBannedCont.setFont(fontNanumGothic15P);
		
		lblBannedDetail = new JLabel("항공기 반입 금지 물품 자세히 보기 》");
		lblBannedDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBannedDetail.setBounds(40,240 ,200, 20);
		lblBannedDetail.setFont(fontNanumGothic12P);
		lblBannedDetail.setForeground(crBlue);

		underline = lblBannedDetail.getFont(); //밑줄
		Map attributes = underline.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblBannedDetail.setFont(underline.deriveFont(attributes));
		
		lblAble = new JLabel("제한적으로 기내 반입 가능한 물품"); //큰 제목
		lblAble.setBounds(40,270 , 500, 50);
		lblAble.setFont(fontNanumGothic25);
		lblAble.setForeground(crNavy);
		
		lblAbleCont = new JLabel("액체류, 의약품 등은 기내로 소량 반입할 수 있습니다. (휴대 △, 위탁 O)");
		lblAbleCont.setBounds(30,315 ,500, 20);
		lblAbleCont.setFont(fontNanumGothic15P);
		
		lblAbleDetail = new JLabel("제한적으로 기내 반입이 가능한 물품 자세히 보기 》");
		lblAbleDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAbleDetail.setBounds(40,340 ,300, 20);
		lblAbleDetail.setFont(fontNanumGothic12P);
		lblAbleDetail.setForeground(crBlue);

		underline2 = lblAbleDetail.getFont(); //밑줄
		Map attributes2 = underline.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblAbleDetail.setFont(underline.deriveFont(attributes));
		//---
		
		lblConsignment = new JLabel("위탁 수하물 운송 제한"); //큰 제목
		lblConsignment.setBounds(40,370 , 500, 50);
		lblConsignment.setFont(fontNanumGothic25);
		lblConsignment.setForeground(crNavy);
		
		lblConsignmentCont = new JLabel("파손되기 쉬운 물품, 전자제품, 고가품 및 귀중품, 배터리 장착 전자기기 등은 기내로 소량 반입할 수 있습니다. (휴대 △, 위탁 O)");
		lblConsignmentCont.setBounds(30,415 ,1000, 20);
		lblConsignmentCont.setFont(fontNanumGothic15P);
		
		lblConsignmentDetail = new JLabel("위탁 수하물 운송 제한 자세히 보기 》");
		lblConsignmentDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConsignmentDetail.setBounds(40,440 ,300, 20);
		lblConsignmentDetail.setFont(fontNanumGothic12P);
		lblConsignmentDetail.setForeground(crBlue);
		
		underline3 = lblConsignmentDetail.getFont(); //밑줄
		Map attributes3 = underline3.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblConsignmentDetail.setFont(underline3.deriveFont(attributes));
		
		lblNotice = new JLabel("유의사항");
		lblNotice.setBounds(30,480 , 200, 20);
		lblNotice.setFont(fontNanumGothic15);
		
		lblNoticeCont = new JLabel("<html>각 국가/지역의 보안검색 절차 및 기준에 따라 차이가 있을 수 있습니다.</html>");
		lblNoticeCont.setBounds(30,490 ,500, 55);
		lblNoticeCont.setFont(fontNanumGothic15P);
		
		lblPdfFile = new JLabel("항공사 반입금지 물품 안내.pdf"); //pdf 파일 첨부/ 대한항공 사이트에 있음
		lblPdfFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPdfFile.setBounds(500,505 ,300, 20);
		lblPdfFile.setFont(fontNanumGothic12P);
		lblPdfFile.setForeground(crBlue);
		
		underline4 = lblPdfFile.getFont(); //밑줄
		Map attributes4 = underline4.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblPdfFile.setFont(underline4.deriveFont(attributes));
		
		//-----
		add(lblRestrict);
		add(lblRestrictCont);
		add(lblBanned);
		add(lblBannedCont);
		add(lblBannedDetail);
		add(lblAble);
		add(lblAbleCont);
		add(lblAbleDetail);
		add(lblConsignment);
		add(lblConsignmentCont);
		add(lblConsignmentDetail);
		add(lblNotice);
		add(lblNoticeCont);
		add(lblPdfFile);
		
		
		setVisible(true);
	}

	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new MenuBar();
		add(menubar.getPnTOP());
	}

	public static void main(String[] args) {
		new RestrictionInfo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
