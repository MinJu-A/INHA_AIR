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

public class BaggageInfo extends JFrame implements ActionListener {
	
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
	private JLabel lblBagInfo; // "수하물 안내" 제목 라벨
	private JLabel lblBagInfoCont; //"수하물 안내" 내용
	private JLabel lblBagNotice; //"수하물 준비 방법 및 유의사항"
	private JLabel lblBagPre; //"수하물 준비할 때"
	private Color crNavy = new Color(30,30,170); // 수하물 유의사항 색
	private JLabel lblBagPreCont; //"수하물 준비 할때" 내용
	private JLabel lblItemRest; //"운송 제한 품목" 가기
	private Color crBlue = new Color(20,100,250); //"운송제한품목" 색
	private Font underline; //밑줄 그을때 필요
	private JLabel lblBagChk; //"수하물 수속"
	private JLabel lblBagChkCont; //"수하물 수속" 내용
	private JLabel lblBagTake;//"수하물 수취"
	private JLabel lblBagTakeCont;//"수하물 수취" 내용
	private JLabel lblFeeInfo; //"종가 요금 안내"
	private Component lblFeeInfoCont; //"종가요금 안내" 내용
	private Component lblFeeDet; //"종가 요금 안내 " 자세히 
	private Font underline2;//밑줄 2
	
	public BaggageInfo() {
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
		lblBagInfo = new JLabel("수하물 안내"); //큰 제목
		lblBagInfo.setBounds(40,90 , 200, 50);
		lblBagInfo.setFont(fontNanumGothic30);
		
		lblBagInfoCont = new JLabel("여행에 필요한 짐을 준비하실 때 아래 내용을 참고해 주세요.");
		lblBagInfoCont.setBounds(30,137 ,500, 20);
		lblBagInfoCont.setFont(fontNanumGothic15P);
		
		lblBagNotice = new JLabel("수하물 준비 방법 및 유의사항");// 제목 1
		lblBagNotice.setBounds(40,175 ,500, 30);
		lblBagNotice.setFont(fontNanumGothic25);
		lblBagNotice.setForeground(crNavy);
		
		lblBagPre = new JLabel("수하물 준비할 때"); // 소제목
		lblBagPre.setBounds(40,200,300,60);
		lblBagPre.setFont(fontNanumGothic20);
		
		lblBagPreCont = new JLabel("<html>· 이름, 주소지, 목적지가 잘 보이도록 영문으로 작성한 이름표를 붙입니다."
				+ "<br>· 자전거, 서핑보드 등과 같은 스포츠 용품이나 반려동물 등 특수 수하물은 미리 항공사에 문의해 주세요."
				+ "<br>· 일부 품목은 위탁하거나 휴대할 수 없습니다. "
				+ "운송 제한 품목에 대한 자세한 내용은 아래 링크를 눌러 참고해 주세요.</html>");
		lblBagPreCont.setBounds(25,245 ,1000, 60);
		lblBagPreCont.setFont(fontNanumGothic15P);
		
		lblItemRest = new JLabel("운송 제한 품목 보기 》"); //운송제한품목창으로 넘어감
		lblItemRest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblItemRest.setBounds(40,285 ,200, 60);
		lblItemRest.setFont(fontNanumGothic12P);
		lblItemRest.setForeground(crBlue);

		underline = lblItemRest.getFont(); //밑줄
		Map attributes = underline.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblItemRest.setFont(underline.deriveFont(attributes));
		
		lblBagChk = new JLabel("수하물 수속할 때"); //소제목2
		lblBagChk.setBounds(40, 340 ,500, 20);
		lblBagChk.setFont(fontNanumGothic20);

		lblBagChkCont = new JLabel("<html>· 공항에서 측정한 가방의 무게는 가정에서 측정한 무게와 다를 수 있습니다."
				+ "<br>· 초과 수하물 요금 지불과 관계없이, 일부 국가에서는 32kg/70lb를 초과하는 가방은 위탁할 수 없습니다."
				+ "<br>· 기내 반입 제한 물품이 휴대 수하물에 포함되지 않도록 확인합니다. "
				+ "<br>· 다른 사람의 수하물을 대리 수속할 수 없습니다."
				+ "<br>· 도착 공항에서 수하물을 원활하게 수취하기 위해서는 도착할 때까지 수하물 표를 보관하는 것이 좋습니다.</html>");
		lblBagChkCont.setBounds(25,365 ,1000, 95);
		lblBagChkCont.setFont(fontNanumGothic15P);
		
		lblBagTake = new JLabel("수하물 수취할 때"); //소제목3
		lblBagTake.setBounds(40, 475 ,500, 20);
		lblBagTake.setFont(fontNanumGothic20);
		
		lblBagTakeCont = new JLabel("<html>· 수하물 수취대에서 수하물을 찾은 후 본인 것이 맞는지 수하물 표를 확인합니다."
				+ "<br>· 세관신고 물품이 있는 경우에는 기내에서 배부하는 ‘여행자 휴대품 신고서’에 해당 사항을 작성하여 세관 직원에게 여권과 함께 제출합니다.</html>");
		lblBagTakeCont.setBounds(25,500 ,1000, 40);
		lblBagTakeCont.setFont(fontNanumGothic15P);
		
		lblFeeInfo = new JLabel("종가요금 안내"); //제목2
		lblFeeInfo.setBounds(40,555 ,500, 30);
		lblFeeInfo.setFont(fontNanumGothic25);
		lblFeeInfo.setForeground(crNavy);
		
		lblFeeInfoCont = new JLabel("고가의 수하물을 위탁하는 경우 여행 전 그 가치를 신고함으로써 해당 물품이 파손 혹은 분실되는 경우 추가 보상을 받기 위해 지불하는 요금을 말합니다.");
		lblFeeInfoCont.setBounds(30,590 ,1000, 20);
		lblFeeInfoCont.setFont(fontNanumGothic15P);
		
		lblFeeDet = new JLabel("종가요금 제도 자세히 보기 》");
		lblFeeDet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFeeDet.setBounds(40,595 ,200, 60);
		lblFeeDet.setFont(fontNanumGothic12P);
		lblFeeDet.setForeground(crBlue);
		
		underline2 = lblFeeDet.getFont(); //밑줄 2
		Map attributes2 = underline2.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblFeeDet.setFont(underline2.deriveFont(attributes));
		
		add(lblBagInfo);
		add(lblBagInfoCont);
		add(lblBagNotice);
		add(lblBagPre);
		add(lblBagPreCont);
		add(lblItemRest);
		add(lblBagChk);
		add(lblBagChkCont);
		add(lblBagTake);
		add(lblBagTakeCont);
		add(lblFeeInfo);
		add(lblFeeInfoCont);
		add(lblFeeDet);
		
		
		setVisible(true);
	}

	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new MenuBar();
		add(menubar.getPnTOP());
	}

	public static void main(String[] args) {
		new BaggageInfo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
