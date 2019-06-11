package wild1;

public enum Response {
	
	SUCCESS("user is added"),
	DISPLAY("dislplaying a list"),
	NULL("NULL POINTER EXCEPTION"),
	FAILURE("USERNAME OR PASSWORD IS INCORRECT"),
	MISSING("FIELDS CAN'T BE EMPTY"),
	LOGIN("LOGIN SUCCESSFULLY");
	 private String message;

	public String getMessage() {
		return message;
	}

	private Response(String message) {
		this.message = message;
	}

	 
}
