package wild1;

public class Pojo {
	
	String username;
	String uname;
	String password;
	int age;
	String gender;
	
	public Pojo(String username, String uname, int age, String gender) 
	{
		this.username=username;
		this.uname=uname;
		this.age=age;
		this.gender=gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "[username=" + username + ", uname=" + uname + ", age=" + age + ", gender=" + gender + "]";
	}
	

}
