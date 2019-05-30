package kr.co.sist.cinema.initialscreen;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import kr.co.sist.cinema.manager.MemberManage;

/**
 * 첫 화면 메뉴창
 * 값을 받아 회원데이터에서 관리자인지 일반회원인지 구분하여
 * 관리자메뉴나 일반회원 메뉴로 이동함
 * @author sehoon
 *
 */
public class InitialScreen extends FileRead{
	
	private static String id;
	private String pw;
	private String gendar;
	private String name;
	private String age;
	private String phoneNumber;
	private String address;
	//화면을 바로 넘어가지 않도록 잠시 멈춰주기 위한 변수
	private String stop;
	private int menuNumber;
	
	static {
		id = "";
	}
	
	/**
	 * 기본 생성자
	 */
	public InitialScreen() {
		
		this.pw = "";
		this.gendar = "";
		this.name = "";
		this.age = "";
		this.phoneNumber = "";
		this.address = "";
	}

	/**
	 * 아이디를 돌려주는 메소드
	 * @return
	 */
	public static String getId() {
		return id;
	}

	/**
	 * 메뉴를 입력받아 실행하는 메소드
	 */
	public void initial() {

		try {
			UImemberLogin.begin();
		} catch (IOException e) {
			e.printStackTrace();
		}

		do {
			System.out.print("                                   메뉴 선택(번호) : ");
			this.menuNumber = scan.nextInt();
			scan.nextLine();
		} while (this.menuNumber < 1 || this.menuNumber > 5);

		switch(this.menuNumber) {
			case 1 : 
				loginMenu();
				break;
			case 2 : 
				signIn();
				break;
			case 3 : 
				idFind();
				break;
			case 4 :
				pwFind();
				break;
			case 5 : 
				unlogin();
				break;
				
		}
		
	}
	
	/**
	 * 아이디와 이름을 입력받아 비밀번호를 찾아주는 메소드
	 */
	public void pwFind() {
		System.out.println("                                   비밀번호 찾기 화면입니다.");
		
		initialReader("회원.txt");
		
		System.out.print("                                   아이디 : ");
		this.id = scan.nextLine();
		System.out.print("                                   이름 : ");
		this.name = scan.nextLine();
		
		for(int i = 0; i < list.size(); i++) {
			String[] comparison = list.get(i).split("\\■");
			
			if( comparison[0].equals(this.id) && comparison[3].equals(this.name) ) {
				
				System.out.println("                                   비밀번호는 "+comparison[1] + "입니다.");
				break;
			}
			if(i == (list.size()-1)) {
				System.out.println("                                   등록되지 않은 정보입니다.");
			}
		
		}
		System.out.println("                                   계속 하시려면 Enter를 입력해주세요.");
		stop = scan.nextLine();
		initial();
	}
	
	/**
	 * 이름과 나이, 전화번호를 이용하여 아이디 찾기 메소드
	 */
	public void idFind() {
		System.out.println("                                   아이디 찾기 화면입니다.");
		initialReader("회원.txt");
		
		System.out.print("                                   이름 : ");
		this.name = scan.nextLine();
		System.out.print("                                   나이 : ");
		this.age = scan.nextLine();
		System.out.print("                                   전화번호(-포함) : ");
		this.phoneNumber = scan.nextLine();
		
		for(int i = 0; i < list.size(); i++) {
			String[] comparison = list.get(i).split("\\■");
			if( comparison[3].equals(this.name) && comparison[4].equals(this.age) 
				&& comparison[5].equals(this.phoneNumber)) {
				
				System.out.println("                                   아이디는 "+comparison[0] + "입니다.");
				break;
			}
			if(i == (list.size()-1)) {
				System.out.println("                                   등록되지 않은 정보입니다.");
			}
			
		}
		System.out.println("                                   계속 하시려면 Enter를 입력해주세요.");
		stop = scan.nextLine();
		initial();
		
	}
	
	/**
	 * 아이디와 비밀번호를 입력받아 관리자 또는 일반회원 메뉴로 이동시키는 메소드
	 */
	public void loginMenu(){
		
		
		System.out.println("                                   로그인 화면입니다.");
		initialReader("회원.txt");
		boolean flag;
		String idEnter = "                                   아이디 : ";
		
		System.out.print(idEnter);
		do {
			this.id = scan.nextLine();
			flag = true;
			for(int i = 0; i < list.size(); i++) {
				String[] comparison = list.get(i).split("\\■");
				if( comparison[0].equals(this.id) ) {
					flag = false;
					break;
				}
			}
			idEnter = "                                   등록되지 않은 아이디입니다.";
			if(flag) {
				System.out.println(idEnter);
				System.out.println();
				System.out.println("                                   0. 메뉴로 돌아가기(번호로입력)");
				System.out.println("                                   1. 계속하기(번호로입력)");
				System.out.print("                                   번호를 입력해주세요 : ");
				String select = scan.nextLine();
				if( select.equals("0")) {
					initial();
				}
				if( select.equals("1") ) {
					loginMenu();
				}
			}
		}while(flag);
		
		String isMember = "";
		
		String pwEnter = "                                   비밀번호 : ";
		
		do {
			System.out.print(pwEnter);
			this.pw = scan.nextLine();
			flag = true;
			for(int i = 0; i < list.size(); i++) {
				String[] comparison = list.get(i).split("\\■");
				if( comparison[0].equals(this.id) && comparison[1].equals(this.pw) ) {
						isMember = comparison[10];
						flag = false;
						break;
				}
			}
			pwEnter = "                                   비밀번호가 틀렸습니다.";
			if(flag) {
				System.out.println(pwEnter);
				System.out.println();
				System.out.println("                                   0. 메뉴로 돌아가기(번호로입력)");
				System.out.println("                                   1. 계속하기(번호로입력)");
				System.out.print("                                   번호를 입력해주세요 : ");
				String select = scan.nextLine();
				if( select.equals("0")) {
					initial();
				}
				if( select.equals("1") ) {
					pwEnter = "                                   비밀번호 : ";
					continue;
				}
			}
		}while(flag);
		
		//회원일때 실행
		if(isMember.equals("false")) {
			GeneralMenu generalmenu = new GeneralMenu(this.id);
			generalmenu.generalMenuSelect();
		}
		// 관리자일때 실행
		else {
			try {
				ManagerMenu managerMenu = new ManagerMenu();
				managerMenu.ManagerInitialMenu();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 비회원 접속, 아이디가 없음
	 */
	public void unlogin() {
		GeneralMenu generalmenu = new GeneralMenu(this.id);
		generalmenu.generalMenuSelect();
	}
	
	/**
	 * 회원가입 기능 메소드
	 * 
	 */
	public void signIn() {
		System.out.println("                                   회원가입 화면입니다.");
		initialReader("회원.txt");
		boolean flag;
		String idEnter = "                                   아이디";
		
		do {
			System.out.print(idEnter + " : ");
			this.id = scan.nextLine();
			flag = false;
			for(int i = 0; i < list.size(); i++) {
				String[] comparison = list.get(i).split("\\■");
				if( comparison[0].equalsIgnoreCase(this.id) ) {
					flag = true;
					idEnter = "                                   중복된 아이디입니다. 다시입력해주세요";
					break;
				}
			}
			
		}while(flag);
		list.clear();
		
		list.add(this.id + "■");
		System.out.print("                                   비밀번호 : ");
		this.pw = scan.nextLine();
		list.add(this.pw + "■");
		do {
		System.out.print("                                   성별(1.남 2.여)번호로 입력해주세요 : ");
		this.gendar = scan.nextLine();
		}while( !(this.gendar.equals("1")) && !(this.gendar.equals("2")) );
		list.add(this.gendar + "■");
		System.out.print("                                   이름 : ");
		this.name = scan.nextLine();
		list.add(this.name + "■");
		System.out.print("                                   나이 : ");
		this.age = scan.nextLine();
		list.add(this.age + "■");
		System.out.print("                                   전화번호('-'포함) : ");
		this.phoneNumber = scan.nextLine();
		list.add(this.phoneNumber + "■");
		System.out.print("                                   주소 : ");
		this.address = scan.nextLine();
		list.add(this.address + "■");
		// 저번달 관람횟수, 이번달 관람횟수, 포인트
		list.add("0" + "■");
		list.add("0" + "■");
		list.add("0" + "■");
		// 회원가입은 일반 회원을 대상으로 하므로 관리자인지 구분하는 항목은 false로 고정
		list.add("false" + "■");
		list.add("0" + "■");
		list.add("0" + "■");
		list.add("일반" + "■");
		list.add("false" + "■");
		list.add("0" + "■");
		list.add("0" + "■");
		
		initialWriter("회원.txt");
		System.out.println("                                   계속 하시려면 Enter를 입력해주세요.");
		stop = scan.nextLine();
		initial();
		
	}
	
	
	/**
	 * 회원데이터 파일로 정보 내보내기, 회원정보 등록
	 * @param fileName
	 */
	public void initialWriter(String fileName) {
		
		try(
				BufferedWriter writer = new BufferedWriter(new FileWriter(PATH+fileName,true));
			)
		{
			for(int i = 0; i < this.list.size(); i++) {
				writer.append(this.list.get(i));
			}
			writer.append("\r\n");
			
		}
		catch(FileNotFoundException e) {
			System.out.println("경로 x");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
