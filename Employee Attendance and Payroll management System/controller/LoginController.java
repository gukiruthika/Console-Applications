package controller;

public class LoginController {
	public boolean checkAdminCredentials(String userName, String password) {
		return userName.equals("admin") && password.equals("ZSGS");
	}

	public boolean checkEmployeeCredentials(String userName, String password) {
		if (userName.equals("Emp0001") && password.equals("zsgs1"))
			return true;
		else if (userName.equals("Emp0002") && password.equals("zsgs2"))
			return true;
		else if (userName.equals("Emp0003") && password.equals("zsgs3"))
			return true;
		else if (userName.equals("Emp0004") && password.equals("zsgs4"))
			return true;
		else if (userName.equals("Emp0005") && password.equals("zsgs5"))
			return true;
		else if (userName.equals("Emp0006") && password.equals("zsgs6"))
			return true;
		else if (userName.equals("Emp0007") && password.equals("zsgs7"))
			return true;
		else if (userName.equals("Emp0008") && password.equals("zsgs8"))
			return true;
		else if (userName.equals("Emp0009") && password.equals("zsgs9"))
			return true;
		else if (userName.equals("Emp0010") && password.equals("zsgs10"))
			return true;
		else
			return false;
	}

	public int checkUser(String user) {
		if (user.equalsIgnoreCase("admin"))
			return 1;
		else if (user.equalsIgnoreCase("employee"))
			return 0;
		else
			return -1;
	}
}
