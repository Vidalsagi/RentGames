package model;

public class Person extends DBConnector {
	public int ID = 0;
	public String UserName = "";
	public String Password = "";
	public String City = "";
	public String Address = "";
	public String FirstName = "";
	public String LastName = "";
	public int Age = 0;
	public String isManager = "false";

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return UserName;
	}

	public void setUsername(String username) {
		UserName = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public void setisManager(String isManager) {
		this.isManager = isManager;
	}

	@Override//in DBConnector
	public String toString() {
		return "Person [ID=" + ID + ", Username=" + UserName + ", Password=" + Password + ", City=" + City
				+ ", Address=" + Address + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Age=" + Age
				+ ", isManager=" + isManager + "]";
	}
}
