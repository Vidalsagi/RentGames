package controller;
import model.LoginModel;

public class LoginController {
	LoginModel model;

	public LoginController() {
		this.model = new LoginModel();
	}
	public int login(String username,String password,boolean selected) throws Exception
	{
		return model.LoginFunc(username, password, selected);
	}
}


