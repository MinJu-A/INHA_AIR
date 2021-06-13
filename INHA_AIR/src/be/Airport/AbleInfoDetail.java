package be.Airport;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.font.TextAttribute;
import java.util.Map;

public class AbleInfoDetail extends JFrame{
	
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic15P = new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 15, 보통
	Font fontNanumGothic12P = new Font("NanumGothic", Font.PLAIN, 12);	// 나눔고딕 15, 보통
	Color crBlue = new Color(20,100,250); //"운송제한품목" 색

	private JLabel lblAbleTitle;
	private JLabel lblAbleTitleCont;
	private JLabel lblLiquid;
	private JLabel lblLiquidCont;
	private JLabel lblMedicine;
	private JLabel lblMedicineCont;
	private Container lblMacBook;
	private Container lblMacBookCont;
	private Container lblEtc;
	private Container lblEtcCont;
	private JLabel lblLinkTraffic;
	private Font underline;
	private JLabel lblLithium;
	private Container lblLithiumCont;
	private JLabel lblMacBookDetail;
	private Font underline2;
	
	
		public AbleInfoDetail(String title, int width, int height) {
		setTitle(title);
		setResizable(false);
		setSize(width, height);
		setLocation(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		lblAbleTitle = new JLabel("제한적 기내 반입 가능 물품");
		lblAbleTitle.setBounds(30,10 , 300, 50);
		lblAbleTitle.setFont(fontNanumGothic20);
		
		lblAbleTitleCont = new JLabel("<html>다음 품목은 기내로 소량 반입할 수 있습니다. (휴대 △, 위탁 O)</html>");
		lblAbleTitleCont.setBounds(30,55 , 730, 20);
		lblAbleTitleCont.setFont(fontNanumGothic15P);
		
		lblLiquid = new JLabel("액체류 (국제선 출발, 환승에 한함)");
		lblLiquid.setBounds(30,85 , 300, 20);
		lblLiquid.setFont(fontNanumGothic15);
		
		lblLiquidCont = new JLabel("<html>· 음료, 식품, 화장품 등 액체류(스프레이) 및 젤류(젤 또는 크림) 물품"+"<br>"+"· 개별 용기당 100ml 이하로 1인당 총 1L 용량의 비닐 지퍼백 1개</html>");
		lblLiquidCont.setBounds(30,105 ,350, 75);
		lblLiquidCont.setFont(fontNanumGothic15P);
		
		lblMedicine = new JLabel("의약품");
		lblMedicine.setBounds(410,85 , 200, 20);
		lblMedicine.setFont(fontNanumGothic15);
		
		lblMedicineCont = new JLabel("<html>여행 중 필요한 개인용 의약품. 의사가 처방한 의약품은 의사소견서 혹은 처방전 등을 제시할 수 있어야 합니다.</html>");
		lblMedicineCont.setBounds(410,105 ,350, 55);
		lblMedicineCont.setFont(fontNanumGothic15P);
		
		
		lblMacBook = new JLabel("MacBook 배터리 리콜 대상");
		lblMacBook.setBounds(410,185 , 200, 20);
		lblMacBook.setFont(fontNanumGothic15);
		
		lblMacBookCont = new JLabel("<html>배터리 화재 위험이 있는 MacBook Pro (Retina, 15-inch, Mid 2015) 중 리콜하여 수리되지 않은 일부 제품은 국가/공항에 따라 항공기 운송(휴대/위탁) 금지 또는 휴대만 가능</html>");
		lblMacBookCont.setBounds(410,205 ,350,75);
		lblMacBookCont.setFont(fontNanumGothic15P);
		
		lblEtc = new JLabel("기타");
		lblEtc.setBounds(30,190 , 200, 20);
		lblEtc.setFont(fontNanumGothic15);
		
		lblEtcCont = new JLabel("<html>· 1인당 1개 이하의 라이터 및 안전성냥(중국 출발편 경우 휴대/위탁 모두 불가)"
					+"<br>" + "· 1인당 2.5kg 이하의 드라이아이스"
					+"<br>" + "· 항공사의 승인을 받은 의료용품"
					+"<br>" + "· 1인당 12oz(350ml) 이하의 파우더류 물품 (미국 출도착편 및 호주 출발편)</html>");
		lblEtcCont.setBounds(30,215 ,350, 105);
		lblEtcCont.setFont(fontNanumGothic15P);
		
		lblLinkTraffic = new JLabel("한국도로안전공단 홈페이지 》"); //링크 : 한국교통안전공단
		lblLinkTraffic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLinkTraffic.setBounds(30,308 ,200, 60);
		lblLinkTraffic.setFont(fontNanumGothic12P);
		lblLinkTraffic.setForeground(crBlue);

		underline = lblLinkTraffic.getFont(); //밑줄
		Map attributes = underline.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblLinkTraffic.setFont(underline.deriveFont(attributes));
		
		lblMacBookDetail = new JLabel("대상 제품 확인하기 》"); //링크 : https://support.apple.com/ko-kr/15-inch-macbook-pro-battery-recall 로 이동
		lblMacBookDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMacBookDetail.setBounds(410,265 ,200, 60);
		lblMacBookDetail.setFont(fontNanumGothic12P);
		lblMacBookDetail.setForeground(crBlue);
		
		underline2 = lblMacBookDetail.getFont(); //밑줄
		Map attributes2 = underline2.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblMacBookDetail.setFont(underline2.deriveFont(attributes));
		
		
		add(lblAbleTitle);
		add(lblAbleTitleCont);
		add(lblLiquid);
		add(lblLiquidCont);
		add(lblMedicine);
		add(lblMedicineCont);
		add(lblMacBook);
		add(lblMacBookCont);
		add(lblEtc);
		add(lblEtcCont);
		add(lblLinkTraffic);
		add(lblMacBookDetail);
		
		setVisible(true);
		}
		
	public static void main(String[] args) {
		new AbleInfoDetail("제한적 기내 반입 가능 물품",800,450);
	}

}
