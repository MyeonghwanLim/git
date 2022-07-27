package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.BookVO;
import model.CgvVO;
import model.MemberVO;

public class View {

	Scanner sc = new Scanner(System.in);
	int min = 1;
	int max = 4;
	public static int action;
	public static int i;

	public String inputString() {
		System.out.print("문자열 입력) ");
		String str = sc.next();
		System.out.println();
		return str;
	}

	public int inputInt() {
		i = 0;
		while (true) {
			System.out.print("번호 입력) ");
			try {
				i = sc.nextInt();
				System.out.println();

				if (i <= 0) { // 0또는 음수 입력 시
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

	public void loginView() {
		while (true) {
			System.out.println("메뉴 화면입니다");
			System.out.println();
			System.out.println("1.로그인 2.회원가입 3.프로그램 종료");
			System.out.print("입력) ");
			try {
				action = sc.nextInt();
				System.out.println();

				if (1 <= action && action <= 3) {
					break;
				}
				System.out.println("범위 외 입력입니다!");
			} catch (Exception e) {
				sc.nextLine(); // 문자열이 들어올 경우 버퍼를 비우기 위해
				System.out.println("번호를 입력해주세요.");
			}
			System.out.println(min + "에서 " + 3 + "사이를 입력해주세요.");
		}
	}

	public void startView() {

		while (true) {

			System.out.println("====CGV====");
			System.out.println();
			System.out.println("1. 영화목록 2.검색 3.마이페이지 4.로그아웃");
			System.out.print("정수 입력) ");
			try {
				action = sc.nextInt();
				System.out.println();

				if (min <= action && action <= max) {
					break;
				}
				System.out.println("범위 외 입력입니다!");
			} catch (Exception e) {
				sc.nextLine(); // 문자열이 들어올 경우 버퍼를 비우기 위해
				System.out.println("번호를 입력해주세요.");
			}
			System.out.println(min + "에서 " + max + "사이를 입력해주세요.");
		}
	}

	public MemberVO login() { // 로그인 페이지
		System.out.print("아이디 입력: ");
		String mid = sc.next();
		System.out.print("비밀번호 입력: ");
		String mpw = sc.next();
		System.out.println();

		MemberVO vo = new MemberVO();
		vo.setMid(mid);
		vo.setMpw(mpw);
		return vo;
	}

	public void logInF() { // 로그인 페이지
		System.out.println(" 로그인 실패했습니다. ");
		System.out.println();
	}

	public void logInT() {
		System.out.println(" 로그인 성공 :-D ");
		System.out.println();
	}

	public void signF() {
		System.out.println(" 회원가입에 실패했습니다.");
		System.out.println();
	}

	public void signT() {
		System.out.println(" 회원가입 성공 :-D ");
		System.out.println();
	}

	public String logOut() {
		System.out.println(" 정말 종료하시겠습니까..?(Y/N)");
		String answer = sc.next();
		System.out.println();
		return answer.toUpperCase();

	}

	public void logOutS() {
		System.out.println("로그아웃에 성공했습니다. 초기화면으로 이동합니다.");
		System.out.println();
	}

	public MemberVO signUpId() { // 회원가입 아이디 입력값 반환
		System.out.println("=====회원가입====="); // 짜증나 ㅋㅋ
		MemberVO mvo = new MemberVO();
		System.out.print("아이디입력) ");
		String id = sc.next();
		mvo.setMid(id);
		return mvo;
	}

	public MemberVO signUpPw() { // 비밀번호, 이름 입력값 반환
		MemberVO mvo = new MemberVO();
		System.out.print("비밀번호입력) ");
		String pw = sc.next();
		System.out.print("이름입력) ");
		String mname = sc.next();
		System.out.println("회원가입 완료");
		System.out.println();
		mvo.setMpw(pw);
		mvo.setMname(mname);
		mvo.setMbook(null);
		return mvo;
	}

	public void movieList(ArrayList<CgvVO> datas) { // 전체메뉴 출력
		int num = 1;
		for (CgvVO vo : datas) { // 전체메뉴 출력
			System.out.println(num++ + "번 " + vo.getTitle());
			System.out.println("예매 횟수 : " + vo.getBookcnt());
			System.out.println("포스터 : " + vo.getImage());
			System.out.println("장르 : " + vo.getGenre());
			System.out.println();
		}
		System.out.println();
	}

	public void myPageView() {
		System.out.println("1. 내정보  2. 회원탈퇴  3. 예매 취소  4. 이전으로 돌아가기");
	}

	public void myPage(MemberVO vo) {
		System.out.println("===회원 정보===");
		System.out.println();
		System.out.println("회원님의 ID: " + vo.getMid());
		System.out.println(vo.getMname() + "님");
		System.out.println("최근 예매하신 영화 ");
		System.out.println();
	}

	public void cancle(ArrayList<BookVO> datas) { // 예매 취소 
		int num = 1;
		for (BookVO vo : datas) {
			System.out.println(num++ + "번 " + vo.getTitle()); // 출력하기 위해 새로 만듦
			System.out.println("예매 횟수 : " + vo.getBookCnt()); // 조인 후 
			System.out.println("포스터 : " + vo.getImage());
			System.out.println("장르 : " + vo.getGenre());
			System.out.println();
		}
	}

	public boolean deleteMember() {
		System.out.println("회원 탈퇴 메뉴입니다.");
		System.out.println();
		while(true) {
			System.out.println("탈퇴하시겠습니까..?(Y/N)");
			String answer = sc.next().toUpperCase();
			System.out.println();
			if (answer.equals("Y")) {
				return true;
			}
			else if(answer.equals("N")) {
				return false;

			} else {
				System.out.println("Y/N 중 선택해주세요");
				continue;
			}
		}
	}

	public boolean ticket() {
		while(true) {
			System.out.println("예매하시겠습니까..?(Y/N)");
			String answer = sc.next().toUpperCase();
			System.out.println();
			if (answer.equals("Y")) {
				return true;
			}
			else if(answer.equals("N")) {
				return false;

			} else {
				System.out.println("Y/N 중 선택해주세요");
				continue;
			}
		}
	}

	public void deleteT() {
		System.out.println("삭제 성공");
		System.out.println();
	}

	public void deleteF() {
		System.out.println("삭제 취소");
		System.out.println();
	}

	public CgvVO searchView() { // 검색창 , 유효성 검사 때문에 약간 수정했습니다.
		sc.nextLine();

		CgvVO vo = new CgvVO();
		System.out.println("=====검색 메뉴=====");
		System.out.println();
		System.out.println("1) 제목검색 2) 장르검색");
		int num = 0;
		try {
			num = sc.nextInt();
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("정수를 입력해주세요.");
		}
		if (num == 1) {
			sc.nextLine();
			System.out.print("제목 입력) ");
			String title = sc.nextLine();
			System.out.println();
			vo.setTitle(title);
		} else if (num == 2) {
			sc.nextLine();
			System.out.print("장르 입력) ");
			String genre = sc.nextLine();
			System.out.println();
			vo.setGenre(genre);
		}
		return vo;
	}

	public void mSelect() {
		System.out.println("예매할 영화번호");
	}

	public void ticketT() {
		System.out.println("예매 성공");
		System.out.println();
	}

	public void movieX() {
		System.out.println("상영 영화가 존재하지 않습니다.");
		System.out.println();
	}

	public void backpage() {
		System.out.println("이전으로 돌아갑니다.");
		System.out.println();
	}

	public void idOverLap() {
		System.out.println("중복된 아이디입니다.");
		System.out.println();
	}

	public void asdasd() { // ㅋㅋ 짜증나
		System.out.println("1. 예매취소  2. 이전으로 돌아가기");
	}
	public void nomovie() {
		System.out.println(" 예매하신 영화가 없습니다.");
	}

	public void cancleNum() {
		System.out.println("취소할 영화의 번호를 입력해주세요");
	}

	public boolean mcancle() {
		while(true) {
			System.out.println("예매를 취소하시겠습니까..?(Y/N)");
			String answer = sc.next().toUpperCase();
			System.out.println();
			if (answer.equals("Y")) {
				return true;
			}
			else if(answer.equals("N")) {
				return false;

			} else {
				System.out.println("Y/N 중 선택해주세요");
				continue;
			}
		}
	}
	public void midF() {
		System.out.println("아이디가 다릅니다.");
		System.out.println();
	}
	public void mpwF() {
		System.out.println("비밀번호가 다릅니다");
		System.out.println();
	}


}