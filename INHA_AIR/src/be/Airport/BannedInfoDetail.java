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

public class BannedInfoDetail extends JFrame{
	
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic15P = new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 15, 보통
	Font fontNanumGothic12P = new Font("NanumGothic", Font.PLAIN, 12);	// 나눔고딕 15, 보통
	Color crBlue = new Color(20,100,250); //"운송제한품목" 색

	private JLabel lblBannedTitle;
	private JLabel lblBannedTitleCont;
	private JLabel lblFlame;
	private JLabel lblFlameCont;
	private JLabel lblGas;
	private JLabel lblGasCont;
	private Container lblWeapon;
	private Container lblWeaponCont;
	private Container lblDanger;
	private Container lblDangerCont;
	private JLabel lblLinkTraffic;
	private Font underline;
	private JLabel lblLithium;
	private Container lblLithiumCont;
	
	
		public BannedInfoDetail(String title, int width, int height) {
		setTitle(title);
		setResizable(false);
		setSize(width, height);
		setLocation(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		lblBannedTitle = new JLabel("항공기 반입 금지 물품");
		lblBannedTitle.setBounds(30,10 , 200, 50);
		lblBannedTitle.setFont(fontNanumGothic20);
		
		lblBannedTitleCont = new JLabel("<html>아래 품목은 휴대 수하물로 기내 반입하거나 위탁 수하물로 운송하는 것이 금지되어 있습니다.</html>");
		lblBannedTitleCont.setBounds(30,55 , 730, 20);
		lblBannedTitleCont.setFont(fontNanumGothic15P);
		
		lblFlame = new JLabel("발화성/인화성 물질");
		lblFlame.setBounds(30,85 , 200, 20);
		lblFlame.setFont(fontNanumGothic15);
		
		lblFlameCont = new JLabel("<html>휘발유, 페인트, 라이터용 연료 등 발화성/인화성 물질</html>");
		lblFlameCont.setBounds(30,105 ,350, 20);
		lblFlameCont.setFont(fontNanumGothic15P);
		
		lblGas = new JLabel("고압가스 용기");
		lblGas.setBounds(410,85 , 200, 20);
		lblGas.setFont(fontNanumGothic15);
		
		lblGasCont = new JLabel("<html>산소캔, 부탄가스캔 등 고압가스 용기</html>");
		lblGasCont.setBounds(410,105 ,350, 20);
		lblGasCont.setFont(fontNanumGothic15P);
		
		
		lblWeapon = new JLabel("무기 및 폭발물 종류");
		lblWeapon.setBounds(410,135 , 200, 20);
		lblWeapon.setFont(fontNanumGothic15);
		
		lblWeaponCont = new JLabel("<html>총기, 폭죽 등 무기 및 폭발물 종류</html>");
		lblWeaponCont.setBounds(410,155 ,350, 20);
		lblWeaponCont.setFont(fontNanumGothic15P);
		
		lblDanger = new JLabel("기타 위험 물질");
		lblDanger.setBounds(30,135 , 200, 20);
		lblDanger.setFont(fontNanumGothic15);
		
		lblDangerCont = new JLabel("<html>소화기, 에어로졸(살충제 등), 락스, 파마약 등 탑승객 및 항공기에 위험이될 가능성이 있는 물질</html>");
		lblDangerCont.setBounds(30,155 ,350, 40);
		lblDangerCont.setFont(fontNanumGothic15P);
		
		lblLinkTraffic = new JLabel("한국도로안전공단 홈페이지 》");
		lblLinkTraffic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLinkTraffic.setBounds(30,180 ,200, 60);
		lblLinkTraffic.setFont(fontNanumGothic12P);
		lblLinkTraffic.setForeground(crBlue);

		underline = lblLinkTraffic.getFont(); //밑줄
		Map attributes = underline.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblLinkTraffic.setFont(underline.deriveFont(attributes));
		
//	
		
		lblLithium  = new JLabel("리튬 배터리 장착 전자기기"); // 소제목
		lblLithium.setBounds(30,230,300,20);
		lblLithium.setFont(fontNanumGothic15);
		
		lblLithiumCont = new JLabel("<html>· 배터리 용량이 160Wh를 초과하는 리튬 배터리가 장착된 전자기기. 전동휠체어 등 보행 보조기구는 예외"
				+ "<br>· 배터리 용량이 160Wh를 초과하는 보조/여분의 리튬 배터리. "
				+ "<br>· 배터리 분리가 불가한 헤어컬(고데기). 일본 출발편 한정. "
				+ "<br>· 리튬 배터리가 분리되지 않는 전동 휠, 스마트 가방. 배터리를 분리할 수 있으며 용량이 160Wh 이내인 경우는 배터리 분리 후 배터리는 휴대, 휠/가방 등은  휴대 또는 위탁 할 수 있습니다."
				+ "</html>");
		lblLithiumCont.setBounds(30,250 ,730, 95);
		lblLithiumCont.setFont(fontNanumGothic15P);
		
		
		add(lblBannedTitle);
		add(lblBannedTitleCont);
		add(lblFlame);
		add(lblFlameCont);
		add(lblGas);
		add(lblGasCont);
		add(lblWeapon);
		add(lblWeaponCont);
		add(lblDanger);
		add(lblDangerCont);
		add(lblLinkTraffic);
		add(lblLithium);
		add(lblLithiumCont);
		
		setVisible(true);
		}
		
	public static void main(String[] args) {
		new BannedInfoDetail("항공기 반입 금지 물품",800,450);
	}

}
