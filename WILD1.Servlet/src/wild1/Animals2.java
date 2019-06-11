package wild1;

public class Animals2 {

	private String name;
	
	 public Integer id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Animals2(Integer id, String name) {
		super();
		this.name = name;
		this.id = id;
	}
	@Override
	public String toString() {
		return "Animals2 [name=" + name + ", id=" + id + "]";
	}

	
}
