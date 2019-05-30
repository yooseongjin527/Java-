package kr.co.sist.cinema.manager;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.ManagerMenu;

/**
 * 회원관리
 * @author sehoon
 *
 */
public class MemberManage extends FileRead {
	
	/**
	 * 회원관리 실행할 번호를 입력받아 실행
	 */
	public void MemberManageStart() {
		
		//업무
		boolean loop = true;

//		numberViews(); //관람횟수 수정메소드
		
		while (loop) {
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			try {
				UImember.begin();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				memberView();
				pause();
			}else if (input.equals("2")) {
				memberSearch();
			}else if (input.equals("3")) {
				memberDelete();
			}else if (input.equals("0")) {
				
				try {
					ManagerMenu managerMenu = new ManagerMenu();
					managerMenu.ManagerInitialMenu();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				loop = false;
			}else {
				System.out.println("                                   잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
		}
	}
	
	/**
	 * 회원 삭제
	 */
	public void memberDelete() {
		
		ArrayList<String> list2 = new ArrayList<String>();// 회원 정보를 새로 넣을 배열
		
		System.out.println("                                   [회원 정보 삭제]");
		
		System.out.print("                                   삭제할 아이디 : ");
		String id = scan.nextLine();
		
		initialReader("회원.txt");
		
		String noId = "";
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			
			if(id.equals(division[0]) ) {
				noId = division[0];
			}
			else {
				list2.add(list.get(i));
			}
		}
	
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "회원.txt"));	
			)
		{
			
			for(int i = 0; i < list2.size(); i++) {
				writer.write(list2.get(i) + "\r\n");
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("회원.txt 파일 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		if( !(noId.equals("")) ) {
			
			System.out.println("                                   [삭제완료]");
		}else {
			System.out.println("                                   [삭제 실패]해당 아이디가 없습니다.");
		}
		
	}//delete

	/**
	 * 회원목록 읽어오기
	 */
	public void memberView() {
		System.out.println();
		System.out.println("                                   [회원 정보 보기]");
		
		System.out.println("[아이디]\t[패스워드]\t[성별]\t[이름]\t[나이]\t[전화]\t\t[주소]"
				+ "\t\t\t\t[저번달 관람횟수]\t[이번달 관람횟수]\t[보유 포인트]\t[멤버십 카드번호]\t"
				+ "\t[가입일]\t[등급]\t[멤버십 등록유무]\t[멤버십 카드 발급일]\t[멤버십 카드명]"
				);
		
		initialReader("회원.txt");
		
		for(int i = 0; i < list.size(); i++) {
			
			String[] division = list.get(i).split("\\■");
			
			System.out.printf("%s\t%s\t%s\t%s\t%6d\t"
					+ "%s\t%s\t%17d\t%17d\t\t%8d\t"
					+ "%s\t\t%s\t%s\t%s\t\t\t"
					+ "%s\t\t%s\t\r\n"
					, division[0], division[1], division[2].equals("1")?"남자":"여자", division[3]
							, Integer.parseInt(division[4]), division[5], division[6]
							, Integer.parseInt(division[7]), Integer.parseInt(division[8])
							, Integer.parseInt(division[9]), division[11], division[12], division[13]
							, division[14], division[15], division[16]
							);
		}
		
	}//reading
	
	/**
	 * 회원검색
	 */
	public void memberSearch() {
		
		System.out.print("                                   검색할 아이디 : ");
		String sId = scan.nextLine();
		
		initialReader("회원.txt");
		
		String noId = "";
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if(division[0].equals(sId)) {
				System.out.println("[아이디]\t[패스워드]\t[성별]\t[이름]\t[나이]\t[전화]\t\t[주소]"
						+ "\t\t\t\t[저번달 관람횟수]\t[이번달 관람횟수]\t[보유 포인트]\t[멤버십 카드번호]\t"
						+ "\t[가입일]\t[등급]\t[멤버십 등록유무]\t[멤버십 카드 발급일]\t[멤버십 카드명]"
						);
				
				System.out.printf("%s\t%s\t%s\t%s\t%6d\t"
						+ "%s\t%s\t%17d\t%17d\t\t%8d\t"
						+ "%s\t\t%s\t%s\t%s\t\t\t"
						+ "%s\t\t%s\t\r\n"
						, division[0], division[1], division[2].equals("1")?"남자":"여자", division[3]
								, Integer.parseInt(division[4]), division[5], division[6]
								, Integer.parseInt(division[7]), Integer.parseInt(division[8])
								, Integer.parseInt(division[9]), division[11], division[12], division[13]
								, division[14], division[15], division[16]
								);
				noId = sId;
			}
		}
		if( !(noId.equals("")) ) {
			
			System.out.println("                                   [검색완료]");
		}else {
			System.out.println("                                   [검색 실패]해당 아이디가 없습니다.");
		}
		pause();
			
	}//search
	
	/**
	 * 이번달 관람횟수를 저번달관람횟수로 이동 및 이번달관람횟수 0으로 초기화
	 */
	public void numberViews() {
		
		String space = "0";
		String grade = "normal";
		Calendar now = Calendar.getInstance();
		
		initialReader("회원.txt");
		
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "회원.txt"));
			)
		{
			for(int i = 0; i < list.size(); i++) {
				String[] division = list.get(i).split("\\■");
				
				if( (now.get(Calendar.MONTH) == 0) && (now.get(Calendar.DATE) == 1) ) {
					
					if(Integer.parseInt(division[8]) > 3 ) {
						grade = "Vip";
					}else {
						grade = "normal";
					}
					
					writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■\r\n"
							, division[0], division[1], division[2], division[3], division[4], division[5]
							, division[6], division[8], space, space, division[10], division[11]
							, division[12], grade, division[14], division[15], division[16]
							));
				}
				
				else if(now.get(Calendar.DATE) == 1) { 
					
					if(Integer.parseInt(division[8]) > 3 ) {
						grade = "Vip";
					}else {
						grade = "normal";
					}
					
					writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■\r\n"
							, division[0], division[1], division[2], division[3], division[4], division[5]
							, division[6], division[8], space, division[9], division[10], division[11]
							, division[12], grade, division[14], division[15], division[16]
							));
				} // 오늘 날짜가 1일일 경우 이번달의 데이터를 저번달로 옮기고 이번달은 0으로 초기화한다.
				else {
					writer.write(list.get(i) + "\r\n");
				}
				
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("회원.txt 파일 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}//numberViews
	
	 //pause
}//
