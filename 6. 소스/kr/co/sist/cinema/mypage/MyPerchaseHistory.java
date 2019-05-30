package kr.co.sist.cinema.mypage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.InitialScreen;

/**
 * 해당 회원의 물품 구매 목록
 * @author sehoon
 *
 */
public class MyPerchaseHistory {
	public Scanner scan;
	public static final String PATH = "D:\\class\\java\\cinema\\data\\";
	public static ArrayList<PerchaseData> pDatas;
	
	/**
	 * 기본 생성자
	 */
	public MyPerchaseHistory() {
		scan = new Scanner(System.in);
	}
	static { 
		pDatas = new ArrayList<PerchaseData>();
	}
	
	//----------------------------------------------밑에서부터 MyPerchaseHistory의 메소드들

	/**
	 * 물품결제 데이터에서 데이터를 읽어옴
	 */
	public void perchaseLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH + "물품결제데이터.dat"));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("\\■");
				PerchaseData data = new PerchaseData();
				
				data.setId(temp[0]);
				data.setSerialNum(temp[1]);
				data.setProductName(temp[2]);
				data.setPrice(temp[3]);
				data.setProductNum(temp[4]);
				data.setDate(temp[5]);
				data.setPointUse(temp[6]);
				data.setBranchOffice(temp[7]);
				
				pDatas.add(data);
				
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString() + "빈값");
		}
		
	}//로드
	
	/**
	 * 해당 회원의 물품구매 내역 보여줌
	 */
	public void perchaseResult() {
		
		System.out.println();
		System.out.println();
		System.out.printf("                                       %s님의 [스토어 구매내역]\n", InitialScreen.getId());
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                       품목명           가격    수량    구매날짜          포인트사용량  지점");
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		
		
		for(PerchaseData data : pDatas) {
			
			String memberId = data.getId();
			if(InitialScreen.getId().equals(memberId)) {
				System.out.printf("                                       %s\t%s\t%s\t%s\t%s\t%s\r\n", data.getProductName(), data.getPrice(), data.getProductNum()
																, data.getDate(), data.getPointUse(), (data.getBranchOffice().equals("1") ? "강남" 
																		: data.getBranchOffice().equals("2") ? "강북"
																				: data.getBranchOffice().equals("3") ? "관악"
																				: data.getBranchOffice().equals("4") ? "잠실" : "홍대"));
			}
		}
		System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
	}//아이디 구매내역
	
}
