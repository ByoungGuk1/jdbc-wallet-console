package com.app.wallet.app;

import com.app.wallet.controller.Controller;

/**
 * Project Wallet Console 실행 클래스
 */
public class App {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.run();
	}
}