package com.app.wallet.app;

import com.app.wallet.controller.Controller;

public class App {

	public static void main(String[] args) {
		try {
			Class.forName("com.app.wallet.util.DBUtil");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		Controller controller = new Controller();
		controller.run();
	}
}
