package kr.co.sist.cinema.manager_snackstore;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 매점 관리
 * @author sehoon
 *
 */
public class SnackManager {
	
	private static ArrayList<Snack> snacks;
	private static ArrayList<Snack> categories; 
	private static Scanner scan;
	private static UI ui;
	private static int grossSales;
	
	static {
		snacks = new ArrayList<Snack>();
		categories = new ArrayList<Snack>();
		scan = new Scanner(System.in);
		ui = new UI();
//		grossSales =0;
	}
	
	/**
	 * 매점 데이터에서 정보 읽어오기
	 */
	public void load() {
		
		
		try {
			// 메모장에서 Snack 읽어오기
	
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.SNACKSTORE));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				
				Snack snack = new Snack();
				
				snack.setSerialNum(temp[0]);
				snack.setCategory(temp[1]);
				snack.setName(temp[2]);
				snack.setPrice(temp[3]);
				snack.setTotalStock(temp[4]);
				snack.setSalesVolume(temp[5]);
				snack.setIsOnSale(temp[6]);
				snacks.add(snack);
				
				snack.setCategoryNum(snack.getSerialNum().substring(0,3));
				
			}		
		
			// 카테고리 load : snacks를 돌면서 임시 ArrayList에 누적 
			// 중복검사 isSole
			
			for(int i=0; i<snacks.size(); i++) {
				
				String category = snacks.get(i).getCategory();
				
				if (isSole(category, categories)) {
					categories.add(snacks.get(i)); // 해당 아이템의 카테고리가 임시 ArrayList에 없을 때(Valid) 
				} 
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println(                                   e.toString() + " load1");
		}
	}
	
	/**
	 * 매점 정보 데이터에 저장하기
	 */
	public void save() {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.SNACKSTORE));
			
			for (Snack snack : snacks) {
				
			
				writer.write(String.format("%s■%s■%s■%s■%s■%s■%s\r\n"
						, snack.getSerialNum()
						, snack.getCategory()
						, snack.getName()
						, snack.getPrice()
						, snack.getTotalStock()
						, snack.getSalesVolume()
						, snack.getIsOnSale()));
			}
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println(                                   e.toString() + " save");
		}
		
	}
	/**
	 * 관리자가 매점 메뉴 데이터에 품목 추가
	 */
	public void putItem() {
		
		
		Snack snack = new Snack();
		
		// 카테고리를 set 하는 메소드
		setCategory(categories, snack);
		
		System.out.print("                                   품목명 입력 : ");
		snack.setName(scan.nextLine());
		snack.setSerialNum(String.format("%s%03d", snack.getCategoryNum(), snacks.size() + 1));
		
		System.out.print("                                   가격 입력 : ");
		snack.setPrice(scan.nextLine());
		
		System.out.print("                                   수량 입력 : ");
		snack.setTotalStock(scan.nextLine());
		
		snack.setSalesVolume("0");
		snack.setIsOnSale("판매중");
		
		snacks.add(snack);
		
		printItemList();
		
		ui.pause(UI.ADD);
			
	}
	
	/**
	 *  관리자가 보는 매점 품목리스트
	 */
	public void printItemList() {
		
		System.out.println("                                   품목 수 : " + snacks.size() + "카테고리 수 : " + categories.size());
		// for countCategory만큼 출력을 반복
		// for 카테고리가 같은 아이들부터 먼저 출력
		
		ui.title(UI.PRINT);
		System.out.println("                                   [고유번호]\t[이름]\t\t\t\t[가격]\t\t[재고수량]\t[판매수량]\t[판매여부]");
		
		for (int i=0; i<categories.size(); i++) {
			
			String category = categories.get(i).getCategory();
			for (Snack snack : snacks) {
				
				if (snack.getCategory().equals(category)
						&& snack.getIsOnSale().equals("판매중")) {
					
					System.out.printf("                                   %s\t%s\t\t\t\t%s\t\t%s\t%s\t%s\n"
							, snack.getSerialNum()
							, snack.getName()
							, snack.getPrice()
							, snack.getTotalStock()
							, snack.getSalesVolume()
							, snack.getIsOnSale());
				}
			}
			
		}
		
		System.out.println("                                   ==============================");
		ui.pause(UI.PRINT);
	}
	
	/**
	 * 매점 품목 추가시 품목분류 선택 또는 추가
	 * @param categories
	 * @param snack
	 */
	public void setCategory(ArrayList<Snack> categories, Snack snack) {
		ui.title(UI.ADD);
		printCategory(categories);
		
		// 직접 입력 아닐 때는 고른 아이템의 categoryNum을 그대로 가져온다.
		String input = scan.nextLine();
		try {
			int nInput = Integer.parseInt(input);
			if (nInput != 0) {
				
				int index = nInput-1;
				//사용자가 입력한 값으로 임시 ArrayList에서 Snack을 지목
				Snack tempSnack = categories.get(index);
				
				//categoryNum = serialNum의 뒷 3자리>> set.
				snack.setCategoryNum(String.format("%s", tempSnack.getCategoryNum()));
				snack.setCategory(tempSnack.getCategory());
				
				//직접입력일 때
			} else if (nInput == 0) {
				
				System.out.print("                                   품목 분류 입력 (예 : 팝콘) : ");
				snack.setCategory(scan.nextLine());
				snack.setCategoryNum(String.format("%03d", categories.size()+1));
			}
			
		} catch (Exception e) {
			System.out.println("                                   유효하지 않은 값입니다.");
			System.out.println("                                   품목추가로 이동합니다.");
//			putItem();
		}
		
	}
	
	/**
	 * 기존 매점 품목분류 목록
	 * @param categories
	 */
	public void printCategory(ArrayList<Snack> categories) {
		//이미 있는 품목 분류를 중복 검사하여 ArrayList<String>에 담기
		//번호를 달아 불러오기.
		System.out.println("                                   [기존 분류 불러오기]");
		System.out.println("                                   ==============================");
		
		
		for(int i=0; i<snacks.size(); i++) {
			
			if (isSole(snacks.get(i).getCategory(), categories)) {
				categories.add(snacks.get(i)); // 해당 아이템의 카테고리가 임시 ArrayList에 없을 때(Valid) 
				System.out.println(                                   categories.size());
			} 
		}
		
		for (int i=0; i<categories.size(); i++) {
			
			System.out.printf("                                   %d.%s\n", i+1, categories.get(i).getCategory());
		}
		
		System.out.println("                                   0. 직접입력");
		System.out.println("                                   ==============================");
		System.out.print("                                   품목 분류 선택 (번호): ");
		
	}
	
	/**
	 *  카테고리를 검사하여 해당 품목분류가 존재하는지 확인
	 * @param category
	 * @param categories
	 * @return
	 */
	public boolean isSole(String category, ArrayList<Snack> categories) {
		
		boolean isSole = true;
		for (Snack snack : categories) {
			if (category.equals(snack.getCategory())) {
				isSole = false; // 검사 대상 category와 같은 category가 이미 temp에 있을 때 유일하지 않음(false)
			}
		}
		return isSole;
	}
	
	/**
	 * 매점 품목에서 팔지 않는 물품목록을 보여주고 팔 수 있도록 설정 가능 하도록 함
	 */
	public void restoreItem() {
		
		ui.title(ui.RESTORE);
		boolean isContinue = true;
		while (isContinue) {
			System.out.println("                                   삭제된 품목 출력");
			
			System.out.println("                                   [고유번호]\t[이름]\t[가격]\t[재고수량]\t[판매수량]\t[판매여부]");
			for(Snack snack : snacks) {
				if (snack.getIsOnSale().equals("삭제")) {
					
					System.out.printf("                                   %s\t%s\t%s\t%s\t%s\t%s\n"
									, snack.getSerialNum()
									, snack.getName()
									, snack.getPrice()
									, snack.getTotalStock()
									, snack.getSalesVolume()
									, snack.getIsOnSale());
				}	
			}
			
			System.out.print("                                   복구할 품목 고유번호 입력(예: 001002): ");
			String input = scan.nextLine();
			
			for (Snack snack : snacks) {
				
				if (input.equals(snack.getSerialNum())) {
					snack.setIsOnSale("판매중");
				}
			}
			
			System.out.println("                                   품목을 계속 복구 하시려면 엔터를 입력하세요.\n메뉴로 돌아가시려면 \"exit\"을 입력하세요.");
			String input2 = scan.nextLine();
			if (input2.equals("exit")) {
				isContinue = false;
				break;
			}
		}
		
	}
	
	/**
	 *  관리자가 매대에서 아이템을 빼는 기능
	 */
	public void pullItem() {
		
		
		printItemList();
		
		ui.title(UI.REMOVE);
		System.out.print("                                   삭제할 품목 고유번호 입력 : ");
		String input = scan.nextLine();
		
		for (Snack inventory : snacks) {
			if (inventory.getSerialNum().equals(input)) {
				inventory.setIsOnSale("삭제");
			}
		}
		
		System.out.println("                                   [고유번호]\t[이름]\t[가격]\t[재고수량]\t[판매수량]\t[판매여부]");
		for(Snack snack : snacks) {
			if (snack.getIsOnSale().equals("판매중")) {
				
				System.out.printf("                                   %s\t%s\t%s\t%s\t%s\t%s\n"
								, snack.getSerialNum()
								, snack.getName()
								, snack.getPrice()
								, snack.getTotalStock()
								, snack.getSalesVolume()
								, snack.getIsOnSale());
			}	
		}
		
		ui.pause(UI.REMOVE);
	}
	
	/**
	 * 매점 품목 판매 현황
	 */
	public void manageSales() {
		
		ui.title(UI.SALES);
		
		System.out.println("                                   [고유번호]\t[이름]\t[가격]\t[재고수량]\t[판매수량]\t[판매액]");
		for(Snack snack : snacks) {
			if (snack.getIsOnSale().equals("판매중")) {
				
				System.out.printf("                                   %s\t%s\t%s\t%s\t%s\t%d\n"
								, snack.getSerialNum()
								, snack.getName()
								, snack.getPrice()
								, snack.getTotalStock()
								, snack.getSalesVolume()
								, Integer.parseInt(snack.getPrice()) * Integer.parseInt(snack.getSalesVolume()));
				grossSales += Integer.parseInt(snack.getPrice()) * Integer.parseInt(snack.getSalesVolume());
			}	
		}
		System.out.printf("                                   총 판매액 : %d\n", grossSales);
		ui.pause(UI.SALES);
	}

}
