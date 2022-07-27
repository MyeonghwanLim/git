package client;

import controller.Controller;

public class Client {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller conn = new Controller();
		conn.startApp();
	}
}
// 회원탈퇴 시 로그아웃 / 장르 null 출력 X / 예매 취소 시 안없어짐 // 
// 