package wild1;

public class UserLogin {
	private String username;
	private String password;
	private String name;
	private int age;
	private String gender;
	
	
	
	@Override
	public String toString() {
		return "UserLogin [Username=" + username + ", password=" + password + ", Uname=" + name + ", Age=" + age+ " , Gender=" + gender +"]";
	}
	public UserLogin(String Username, String password, String name,int age, String gender) {
		super();
		// TODO Auto-generated constructor stub
		this.username=Username;
		this.password=password;
		this.name=name;
		this.age=age;
		this.gender=gender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	

}

