package view;

public class Home {
Menu menu = new Menu();
	public static void main(String[] args) {
		Home home = new Home();
		home.init();

	}
	private void init() {
		System.out.println("");
		menu.init();
	}

}
