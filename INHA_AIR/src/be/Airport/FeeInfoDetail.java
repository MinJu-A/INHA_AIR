package be.Airport;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Window;

public class FeeInfoDetail extends JFrame{
	
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic15P = new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 15, 보통
	Font fontNanumGothic12P = new Font("NanumGothic", Font.PLAIN, 12);	// 나눔고딕 15, 보통
	
	private JLabel lblFee; //"종가요금"
	private JLabel lblFeeCont;//"종가요금" 내용
	private JLabel lblFeeTg;// "종가요금 대상"
	private JLabel lblFeeTgCont;// 종가요금 대상" 내용
	private JLabel lblHighPr;// "고가품 신고"
	private JLabel lblHighPrCont; // "고가품 신고" 내용
	private JLabel lblEtc; //기타 유의 사항

		public FeeInfoDetail(String title, int width, int height) {
		setTitle(title);
		setResizable(false);
		setSize(width, height);
		setLocation(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		lblFee = new JLabel("종가요금");
		lblFee.setBounds(30,35 , 200, 50);
		lblFee.setFont(fontNanumGothic20);
		
		lblFeeCont = new JLabel("<html>고가의 수하물을 위탁하는 경우 여행 전 그 가치를 신고함으로써 해당 물품이 파손 혹은 분실되는 경우 추가 보상을"
				+ "  받기 위해 지불하는 요금을 말합니다.</html>");
		lblFeeCont.setBounds(30,75 ,730, 50);
		lblFeeCont.setFont(fontNanumGothic15P);
		
		lblFeeTg = new JLabel("종가요금 대상");
		lblFeeTg.setBounds(30,125 , 200, 50);
		lblFeeTg.setFont(fontNanumGothic15);
		
		lblFeeTgCont = new JLabel("<html>대한항공에서는 대한항공 운항구간인 경우, 소정의 추가 요금을 지불하면 최대 USD 2,500까지 보상하는 종가요금 제도를 운영하고 있습니다.</html>");
		lblFeeTgCont.setBounds(30,160 ,730, 50);
		lblFeeTgCont.setFont(fontNanumGothic15P);
		
		lblHighPr = new JLabel("고가품 신고 시 부과되는 요금");
		lblHighPr.setBounds(30,205 , 200, 50);
		lblHighPr.setFont(fontNanumGothic15);
		
		lblHighPrCont = new JLabel("<html>고가품 신고 시 신고 금액 USD100당 USD 0.5가 부과되며, 물품 가격을 증명할 수 있는 서류를 제시해야 합니다.</html>");
		lblHighPrCont.setBounds(30,245 ,730, 20);
		lblHighPrCont.setFont(fontNanumGothic15P);
		
		lblEtc = new JLabel("<html>⊙ 기타 자세한 사항은 수하물 접수 시 직원에게 문의해 주세요.</html>");
		lblEtc.setBounds(30,275 ,660, 50);
		lblEtc.setFont(fontNanumGothic12P);
		
		add(lblFee);
		add(lblFeeCont);
		add(lblFeeTg);
		add(lblFeeTgCont);
		add(lblHighPr);
		add(lblHighPrCont);
		add(lblEtc);
		
		setVisible(true);
		}
		
	public static void main(String[] args) {
		new FeeInfoDetail("종가요금 안내",800,450);
	}

}
