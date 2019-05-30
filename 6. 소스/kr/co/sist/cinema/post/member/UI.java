package kr.co.sist.cinema.post.member;

import java.util.Scanner;
/**
 * 포스트 메뉴 UI
 * @author sehoon
 *
 */
public class UI {
   
   public final static int WRITE = 1;
   public final static int MY = 2;
   public final static int EDIT = 3;
   public final static int PRINT = 4;
   public final static int DELETE = 5;
   
   /**
    * 무비포스트 시작 메세지
    */
   public void begin() {
      System.out.println("                                       [무비포스트]");
   }
   /**
    * 무비포스트 종료 메세지
    */
   public void end() {
      System.out.println("                                       [무비포스트 종료]");
   }
   /**
    * 무비포스트 메뉴
    */
   public void menu() {
	   System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
      System.out.println("                                       1. 포스트 작성하기");
      System.out.println("                                       2. 내 포스트 ");
      System.out.println("                                       0. 종료");
      System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
      System.out.print("                                       선택(번호) : ");
   }
   /**
    * 내 무비포스트 메뉴
    */
   public void myPostMenu() {
	   System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
      System.out.println("                                       1. 내 포스트 열람하기");
      System.out.println("                                       2. 내 포스트 수정하기");
      System.out.println("                                       3. 내 포스트 삭제하기");
      System.out.println("                                       0. 돌아가기");
      System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
      System.out.print("                                       선택(번호) : ");
   }
   /**
    * 선택한 메뉴에 대한 이름
    * @param n
    */
   public void title(int n) {
      
      if (n == WRITE) {
         System.out.println("                                       [포스트 작성하기]");
      } else if (n == MY) {
         System.out.println("                                       [마이 포스트]");
      } else if (n == EDIT) {
         System.out.println("                                       [마이 포스트 수정하기]");
      } else if (n == PRINT) {
         System.out.println("                                       [마이포스트 열람하기]");
      } else if (n == DELETE) {
         System.out.println("                                       [마이포스트 삭제하기]");
      }
   }
   
   /**
    * 각 기능별로 다음출력문으로 바로 넘어가지 않게 잠시 멈춤
    * @param n
    */
   public void pause(int n) {
      
      Scanner scan = new Scanner(System.in);
      if (n == WRITE) {
         System.out.println("                                       ========================================포스트 작성 완료========================================\n"
         					+ "                                       계속하시려면 엔터를 입력하세요");
      } else if (n == MY) {
         System.out.println("                                       ========================================마이포스트 종료=========================================\n"
         					+ "                                       계속하시려면 엔터를 입력하세요");
      } else if (n == EDIT) {
         System.out.println("                                       ======================================마이포스트 수정 완료========================================\n"
         					+ "                                       계속하시려면 엔터를 입력하세요");
      } else if (n == PRINT) {
         System.out.println("                                       =======================================마이포스트 열람 완료========================================\n"
         					+ "                                       계속하시려면 엔터를 입력하세요");
      } else if (n == DELETE) {
         System.out.println("                                       ======================================마이포스트 수정 완료========================================\n"
         					+ "                                       계속하시려면 엔터를 입력하세요");
      }
      
      scan.nextLine();
   }
}