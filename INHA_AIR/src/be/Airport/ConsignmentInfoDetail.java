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

public class ConsignmentInfoDetail extends JFrame{
	
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic15P = new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 15, 보통
	Font fontNanumGothic12P = new Font("NanumGothic", Font.PLAIN, 12);	// 나눔고딕 15, 보통
	Color crBlue = new Color(20,100,250); //"운송제한품목" 색

	private JLabel lblConsignmentTitle;
	private JLabel lblConsignmentTitleCont;
	private JLabel lblFragile;
	private JLabel lblFragileCont;
	private JLabel lblPrecious;
	private JLabel lblPreciousCont;
	private Container lblElectronic;
	private Container lblElectronicCont;
	private Container lblBattery;
	private Container lblBatteryCont;
	private JLabel lblLinkTraffic;
	private Font underline;
	private JLabel lblLithium;
	private Container lblLithiumCont;
	
	
		public ConsignmentInfoDetail(String title, int width, int height) {
		setTitle(title);
		setResizable(false);
		setSize(width, height);
		setLocation(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		lblConsignmentTitle = new JLabel("위탁 수하물 운송 제한");
		lblConsignmentTitle.setBounds(30,10 , 200, 50);
		lblConsignmentTitle.setFont(fontNanumGothic20);
		
		lblConsignmentTitleCont = new JLabel("<html>다음 품목은 수하물로 위탁할 수 없으므로, 직접 휴대해 주세요. (휴대 O, 위탁 X)</html>");
		lblConsignmentTitleCont.setBounds(30,55 , 730, 20);
		lblConsignmentTitleCont.setFont(fontNanumGothic15P);
		
		lblFragile = new JLabel("파손 또는 손상되기 쉬운 물품");
		lblFragile.setBounds(30,85 , 300, 20);
		lblFragile.setFont(fontNanumGothic15);
		
		lblFragileCont = new JLabel("<html>도자기, 액자, 유리제품 등</html>");
		lblFragileCont.setBounds(30,105 ,350, 20);
		lblFragileCont.setFont(fontNanumGothic15P);
		
		lblPrecious = new JLabel("고가품 및 귀중품");
		lblPrecious.setBounds(30,135 , 200, 20);
		lblPrecious.setFont(fontNanumGothic15);
		
		lblPreciousCont = new JLabel("<html>화폐, 보석, 현금, 유가증권, 견본, 서류 등</html>");
		lblPreciousCont.setBounds(30,155 ,350, 20);
		lblPreciousCont.setFont(fontNanumGothic15P);
		
		
		lblElectronic = new JLabel("전자제품");
		lblElectronic.setBounds(30,185 , 200, 20);
		lblElectronic.setFont(fontNanumGothic15);
		
		lblElectronicCont = new JLabel("<html>노트북, 카메라, 휴대전화 등 고가의 전자제품</html>");
		lblElectronicCont.setBounds(30,205 ,350, 20);
		lblElectronicCont.setFont(fontNanumGothic15P);
		
		lblBattery = new JLabel("보조/여분 리튬배터리 및 리튬배터리 장착 전자기기");
		lblBattery.setBounds(410,85 , 400, 20);
		lblBattery.setFont(fontNanumGothic15);
		
		lblBatteryCont = new JLabel("<html>· 배터리 용량이 160Wh 이하이며 단락 방지 포장된 여분/보조 배터리(100Wh 이하 배터리 최대 20개+100Wh 초과 160Wh 이하 배터리 최대 2개 휴대 가능 (해외 출발편의 경우 공항/국가별 별도 강화된 규정 적용 가능)) "
				+"<br>"+ "· 배터리 용량이 100Wh 이하인 전자담배(기내에서 충전 및 사용은 엄격히 금지됩니다.)</html>");
		lblBatteryCont.setBounds(410,105 ,350, 130);
		lblBatteryCont.setFont(fontNanumGothic15P);
		
		lblLinkTraffic = new JLabel("한국도로안전공단 홈페이지 》");
		lblLinkTraffic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLinkTraffic.setBounds(410,220 ,200, 60);
		lblLinkTraffic.setFont(fontNanumGothic12P);
		lblLinkTraffic.setForeground(crBlue);

		underline = lblLinkTraffic.getFont(); //밑줄
		Map attributes = underline.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblLinkTraffic.setFont(underline.deriveFont(attributes));
		
//
		add(lblConsignmentTitle);
		add(lblConsignmentTitleCont);
		add(lblFragile);
		add(lblFragileCont);
		add(lblPrecious);
		add(lblPreciousCont);
		add(lblElectronic);
		add(lblElectronicCont);
		add(lblBattery);
		add(lblBatteryCont);
		add(lblLinkTraffic);

		
		setVisible(true);
		}
		
	public static void main(String[] args) {
		new ConsignmentInfoDetail("위탁 수하물 운송 제한",800,450);
	}

}
