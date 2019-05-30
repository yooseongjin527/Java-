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
 * 공지사항 및 FAQ
 * @author sehoon
 *
 */
public class NoticeBoard extends FileRead{
	
	Calendar calendar;
	
	/**
	 * 기본 생성자
	 */
	public NoticeBoard() {
		calendar = Calendar.getInstance();
	}
	
	/**
	 * 공지사항 및 FAQ 메뉴에서 번호를 입력받아 실행
	 */
	public void NoticeBoardstart() {
		
		//업무
		boolean loop = true;
		
		while (loop) {
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			try {
				UInotice.begin();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				notice();
				pause();
			}else if (input.equals("2")) {
				questions();
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
				System.out.println("                                       잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
		}
		
	}
	
	/**
	 * 공지사항 메뉴에서 번호를 입력받아 실행
	 */
	public void notice() {
		
		//업무
		boolean loop = true;
		
		while (loop) {
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			UInotice.menu();
			
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				System.out.println("                                       공지사항 글쓰기를 시작합니다.");
				noticeBoardWriting("공지사항.dat");
				pause();
			}else if (input.equals("2")) {
				System.out.println("                                       [공지사항 글 삭제하기]");
				noticeBoardDelete("공지사항.dat");
				pause();
			}else if (input.equals("3")) {
				System.out.println("                                       [공지사항 글 수정하기]");
				noticeBoardModify("공지사항.dat");
				pause();
			}else if (input.equals("4")) {
				noticeView();
			}else if (input.equals("0")) {
				loop = false;
			}else {
				System.out.println("                                       잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
		}
		
	}//notice
	
	/**
	 * FAQ메뉴에서 번호를 입력받아 실행
	 */
	public void questions() {
		
		//업무
		boolean loop = true;
		
		while (loop) {
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			
			UInotice.menu();
			
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				System.out.println("                                       [문의사항 글쓰기를 시작합니다.]");
				noticeBoardWriting("FAQ.dat");
				pause();
			}else if (input.equals("2")) {
				System.out.println("                                       [문의사항 글 삭제하기]");
				noticeBoardDelete("FAQ.dat");
				pause();
			}else if (input.equals("3")) {
				System.out.println("                                       [문의사항 글 수정하기]");
				noticeBoardModify("FAQ.dat");
				pause();
			}else if (input.equals("4")) {
				questionView();
			}else if (input.equals("0")) {
				loop = false;
				pause();
			}else {
				System.out.println("                                       잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
		}

	}//questions
	
	/**
	 * 공지사항 목록 보기
	 */
	public void noticeView() {
		
		System.out.println("[식별코드]\t[공지제목]\t\t[공지내용]\t\t[작성일]\t[작성시간]");
		
		initialReader("공지사항.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			System.out.printf("%s\t%s\t : \t%s\t%s\t%s\r\n", division[0], division[1], division[2], division[3], division[4]);
		}
		
		pause();
		
	}//공지사항 글 목록 보기
	
	/**
	 * FAQ 목록 보기
	 */
	public void questionView() {
		
		System.out.println("[식별코드]\t[문의정보제목]\t\t[문의정보내용]\t\t[작성일]\t[작성시간]");
		
		initialReader("FAQ.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			System.out.printf("%s\t%s\t : \t%s\t%s\t%s\r\n", division[0], division[1], division[2], division[3], division[4]);
		}
		
		pause();
		
	}//문의사항 글목록 보기
	
	/**
	 * 해당 데이터가 있는 파일명.확장자를 매개변수로 받아 공지사항 또는 FAQ 글 작성
	 * @param fileName
	 */
	public void noticeBoardWriting(String fileName) {
		
		int noticeIndex = 0;
		
		initialReader(fileName);
		
			String[] division = list.get(0).split("\\■");
			noticeIndex = Integer.parseInt(division[0]);
			
		noticeIndex++;
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName));
			
			String[] wrt = new String[2];
			
			System.out.print("                                       제목 입력 : ");
			wrt[0] = scan.nextLine();
			
			System.out.print("                                       내용 입력 : ");
			wrt[1] = scan.nextLine();
			
			writer.write(String.format("%03d■%s■%s■%s■%s\r\n", noticeIndex, wrt[0], wrt[1], String.format("%tF", calendar), String.format("%tT", calendar) ));
			
			for(int i = 0; i < list.size(); i++) {
				writer.write(list.get(i) + "\r\n");
			}
			
			System.out.println("                                       글쓰기 완료");
			writer.close();
	
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " 경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}//공지사항 글쓰기 메소드
	
	/**
	 * 해당 데이터가 있는 파일명.확장자를 매개변수로 받아 공지사항 또는 FAQ 글 수정
	 * @param fileName
	 */
	public void noticeBoardModify(String fileName) {
		
		ArrayList<String> list2 = new ArrayList<String>();
		
		initialReader(fileName);
		
		String[] tmp = list.get(0).split("\\■");
		int noticeIndex = Integer.parseInt(tmp[0]);
		
		noticeIndex++;
		
		System.out.print("                                       수정하려는 글의 식별코드 : ");
		int modify = scan.nextInt();
		scan.nextLine(); // 엔터없애기

		int k = 0; // 글 수정이 됬는지 확인
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName));
			
			for(int i = 0; i < list.size(); i++) {
				String[] division = list.get(i).split("\\■");
				
				if(!division[0].equals(String.format("%03d", modify))) {
					list2.add(list.get(i));
					
					
				}else if(division[0].equals(String.format("%03d", modify))){
					k++;
					System.out.print("                                       수정할 내용 : ");
					String modifyContent = scan.nextLine();
					
					writer.write( String.format("%03d", noticeIndex)+"■"+ division[1]+"■"+modifyContent+"■"+String.format("%tF", calendar)+"■"+ String.format("%tT", calendar)+"■\r\n");
					
					System.out.println("                                       내용수정 완료");
				}
			}
			
			for( int i = 0; i < list2.size(); i++) {
				writer.write(list2.get(i) + "\r\n");
			}
			if(k == 0 )
				System.out.println("                                       수정할 식별코드가 없습니다. 취소합니다.");
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " 경로 없음");
		}
		catch(IOException e )
		{
			e.printStackTrace();
		}
	}//공지사항 글 수정 메소드

	/**
	 * 해당 데이터가 있는 파일명.확장자를 매개변수로 받아 공지사항 또는 FAQ 글 삭제
	 * @param fileName
	 */
	public void noticeBoardDelete(String fileName) {
		
		ArrayList<String> modify = new ArrayList<String>();
		
		initialReader(fileName);
		
		System.out.print("                                       삭제할 글의 식별코드 : ");
		int number = scan.nextInt();
		scan.nextLine(); // 엔터값 없애기위함
		
		System.out.print("                                       정말로 삭제하시겠습니까?(y/n) : "); //삭제할지 한번더 묻는 확인작업
		String check = scan.nextLine();
		
		while(!(check.equalsIgnoreCase("y")) && !(check.equalsIgnoreCase("n")) ) {
			System.out.println("                                       잘못 입력하셨습니다.");
			System.out.print("                                       정말로 삭제하시겠습니까?(y/n) : "); //삭제할지 한번더 묻는 확인작업
			check = scan.nextLine();
		}
		
		if( check.equalsIgnoreCase("y") ) {
				
			for(int i = 0; i < list.size(); i++) {
				String[] temp = list.get(i).split("\\■");
				if (!(temp[0].equals(String.format("%03d", number)) )) {
					modify.add( list.get(i) );
				}
			}
			
			try(
				BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName));	
				)
			{
				for(int i = 0; i < modify.size(); i++) {
					writer.write(modify.get(i) + "\r\n");
				}
			}
			catch(FileNotFoundException e) {
				System.out.println(fileName + " 경로 없음");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("                                       삭제 완료");
			
		}else if(check.equals("n")) {
			System.out.println("                                       삭제를 취소합니다."); // 삭제를 취소하고 원래 데이터를 다시 집어넣음
		}
		
	}// 공지사항 글 삭제 메소드

	
	
}
