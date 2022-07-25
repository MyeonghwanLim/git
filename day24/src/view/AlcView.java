package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.AlcVO;
import model.CategoryVO;

public class AlcView {
   public int action;
   public int age;
   Scanner sc = new Scanner(System.in);
   int min = 1;
   int max = 4; // 메뉴 추가 시 max 값 변경해주세요.

   public boolean userAge() { // 나이인증 및 유효성검사
      while (true) {
         System.out.println("안녕하세요.");
         System.out.println("귀하의 나이를 입력해주세요.");
         System.out.print("나이 입력) ");
         try {
            age = sc.nextInt();
            if (age == 1234) {
               return false;
            }
            if (age < 20 || age > 100) {
               System.out.println("인증에 실패하였습니다.");
               System.out.println(" ");
               continue;
            } else if (age >= 20 && age <= 100) {
               System.out.println("인증에 성공하였습니다.");
               System.out.println(" ");
               return true;
            }
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("숫자를 입력해주세요.");
         }
      }
   }

   public void user_startView() { // 인증 성공 후 자판기 메뉴실행
      while (true) {
         System.out.println("===========주류 자판기===========");
         System.out.println("[ 1.메뉴확인 2.구매 3.검색 4.종료 ]");
         System.out.print("정수 입력) ");
         try {
            action = sc.nextInt();
            System.out.println();

            if (min <= action && action <= max) { // 유지보수 min, max사용하여 자판기 실행번호 설정
               break;
            }
            System.out.println("범위 외 입력입니다!");
         } catch (Exception e) {
            sc.nextLine(); // 문자열이 들어올 경우 버퍼를 비우기 위해  
            System.out.println("숫자를 입력해주세요.");
         }
         System.out.println(min + "에서 " + max + "사이를 입력해주세요.");
      }
   }

   public void menu(ArrayList<AlcVO> datas) { // 전체메뉴 출력
      int num = 1;
      if (datas.size() == 0) { //datas에 아무것도 존재 하지 않을 때
         System.out.println("해당 종류의 술이 존재하지 않습니다.");
      }
      for (AlcVO vo : datas) { // 전체메뉴 출력
         System.out.println(num++ + "번 " + vo.getCategory() + " " + vo.getName() + " " + vo.getPrice() + "원 "
               + vo.getCnt() + "개 " + vo.getAbv() + "도 ");
      }
      System.out.println();
   }

   public int categoryMenu(ArrayList<CategoryVO> cate) { // 대분류 카테고리 출력
      while(true) {

         for(int i=0; i<cate.size();i++) {
            System.out.println(i+1+"." + cate.get(i).getName()); 
         }

         System.out.println("원하시는 카테고리를 입력해주세요.");
         System.out.print("번호 입력) ");
         try {
            int num=sc.nextInt();
            System.out.println();

            if(num<=0 || num>cate.size()) { // 유효성검사 > 0이하 또는 카테고리 개수보다 높은 값을 입력하면 다시 입력 받기
               System.out.println("1부터 "+cate.size()+"까지 입력해주세요.");
               continue;
            }
            return num;
         }catch (InputMismatchException e) {
            sc.nextLine(); // 문자열이 들어올 경우 버퍼를 비우기 위해  
            System.out.println("숫자를 입력해주세요.");
            System.out.println(" ");
         }

      }
   }

   public void sool(ArrayList<AlcVO> datas, CategoryVO vo) { // 대분류 카테고리 선택 후 보여질 소분류 출력 메서드
      System.out.println("========== "+vo.getName()+" =========="); // 카테고리vo(대분류)의 이름 가져온다.
      int num = 1; // 넘버링
      for (int i = 0; i < datas.size(); i++) {
         if (datas.get(i).getCategory().equals(vo.getName())) { // AlcVO의 요소 중 카테고리이름과 CategoryVO의 요소의 이름이 같으면  
            System.out.println(num++ + "." + datas.get(i).getName() + " 가격(" + datas.get(i).getPrice() + ") 재고("
                  + datas.get(i).getCnt() + ") 도수(" + datas.get(i).getAbv() + ")");
         }
      }
      return;
   }


   public String inputString() {
      System.out.print("이름 입력) ");
      String str = sc.next();
      System.out.println();
      return str;
   }

   public int inputInt() {
      int i = 0;
      while (true) {
         System.out.print("정수 입력) ");
         try {
            i = sc.nextInt();
            System.out.println();
            if (i<=0) { // 0또는 음수 입력 시
               System.out.println("양수만 입력해주세요");
               continue;
            }

         } catch (InputMismatchException e) { // 문자열 입력 시
            sc.nextLine();
            System.out.println("정수만 입력해주세요.");
            continue;
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("또다른 오류 발생");
            continue;
         }
         return i;
      }
   }


   public void buyName() {
      System.out.println("구매하실 술 이름을 입력해주세요.");
   }
   public void buyNameFail() {
      System.out.println("술의 이름을 다시 한번 확인해주세요.");
   }

   public void buyCnt() {
      System.out.println("구매하실 수량을 입력해주세요.");
   }

   public void buyCntFail() {
      System.out.println("재고가 부족합니다. 현재 재고를 다시 한번 확인해주세요.");
   }

   public void searchName() {
      System.out.println("검색할 술 이름을 입력해주세요.");
   }

   public void success() {
      System.out.println("구매에 성공하였습니다!");
   }

   public void fail() {
      System.out.println("구매에 실패하였습니다...");
   }

   public void total(int total) {
      System.out.println("총 결제하실 금액은 " + total + "원 입니다. ");
   }



   public void money() {
      System.out.println("투입 금액을 입력해주세요. ");
   }
   public void moneyAdd() {
      System.out.println("추가로 투입할 금액을 입력해주세요.");
   }
   // 누적 금액 추가
   public void moneyIn(int money) {
      System.out.println("누적 금액 : " + money);
   }

   public void balT(int balance) { // 잔돈출력
      System.out.println("잔돈이 " + -1*balance + "원 있습니다.");
   }

   public void balF(int balance) {
      System.out.println("금액이 " + -1*balance + "원 부족합니다.");
   }

   public void off() {
      System.out.println("이용이 종료됩니다.");
   }

   public boolean receipt() { // 영수증 출력
      while (true) {
         try {
            System.out.println("영수증을 받으시겠습니까?");
            System.out.println("1.YES 2.No");
            System.out.print("입력) ");
            action = sc.nextInt();
            System.out.println(" ");
            if (action == 1) {
               return true;
            } else if (action == 2) {
               return false;
            } else {
               System.out.println("범위 외 입력입니다. 1.(Yes)/2.(No) 만 입력가능");
               continue;
            }
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("숫자를 입력해주세요.");
         }
      }
   }

   public void show_receipt(AlcVO vo) { // 영수증 모양
      System.out.println("============영수증============");
      System.out.println("품명 : " + vo.getName());
      System.out.println("가격 : " + vo.getPrice());
      System.out.println("구매수량 : " + vo.getCnt());
      System.out.println("총 구매가격: " + vo.getPrice() * vo.getCnt());  
      System.out.println("반환금액 : " + vo.getNum());
      System.out.println("=============================");
      System.out.println(" ");
   }
   public void thanks() { 
      System.out.println("이용해주셔서 감사합니다!");
      System.out.println(" ");
   }
   public void admin_startView() { // 관리자모드
      while (true) {
         System.out.println("=====관리자모드=====");
         System.out.println("1.술 종류 추가 2.재고추가 3.술 삭제 4.관리자모드 종료");
         System.out.print("정수 입력) ");
         try {
            action = sc.nextInt();
            if (1 <= action && action <= 4) {
               break;
            }
            System.out.println("범위 외 입력입니다!");
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("숫자를 입력해주세요.");
         }
      }
   }
   public void admin_success() {
      System.out.println("수행성공");
   }
   public void admin_fail() {
      System.out.println("수행실패");
   }
   public void addDrink() { // 술 추가
      System.out.println("술 추가 메뉴입니다.");
   }
   public void category() { // 관리자1 : 종류 추가
      System.out.println("추가하실 술의 종류를 입력해주세요.");
   }
   public void cnt() { // 관리자2 : 재고 설정 
      System.out.println("추가하실 수량을 입력해주세요.");
   }
   public void delete() { // 관리자3 : 음료삭제
      System.out.println("음료를 삭제합니다.");
   }
   public void soolpk() { // 재고추가/삭제할 술 번호(pk) 입력
      System.out.println("술의 번호를 입력해주세요.");
   }
   public void name() { // 술 이름
      System.out.println("술의 이름을 입력해주세요.");
   }
   public int price() { // 술 가격 + 유효성검사
      System.out.println("술의 가격을 입력해주세요.");
      System.out.print("가격 입력) ");
      while (true) {
         try {
            int price = sc.nextInt();
            System.out.println();

            if (price >= 1000) {
               return price;
            } else {
               System.out.println("1000원 이상부터 입력해주세요.");
               System.out.print("가격 입력) ");
               continue;
            }
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("숫자를 입력해주세요.");
            continue;
         }
      }
   }
   public double abv() { // 술 도수 + 유효성검사
      System.out.println("술의 도수를 입력해주세요.");
      System.out.print("도수 입력) ");
      while (true) {
         try {
            double abv = sc.nextDouble();
            System.out.println();
            if (abv > 0 && abv < 100) {
               return abv;
            } else {
               System.out.println("0~100사이의 값을 입력해주세요");
               System.out.print("도수 입력) ");
               continue;
            }
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("숫자를 입력해주세요.");
            continue;
         }
      }
   }


   public void admin_off() {
      System.out.println("관리자모드 종료합니다.");
   }

   public boolean buyAgain() {
      while (true) {
         try {
            System.out.println("추가 구매 하시겠습니까?");
            System.out.println("1.YES 2.NO");
            System.out.print("정수 입력) ");
            action = sc.nextInt();
            if (action == 1) {
               return true;
            } else if (action == 2) {
               return false;
            } else {
               System.out.println("범위 외 입력입니다.");
               continue;
            }

         } catch (Exception e) {
            sc.nextLine();
            System.out.println("숫자를 입력해주세요.");
         }
      }
   }
}