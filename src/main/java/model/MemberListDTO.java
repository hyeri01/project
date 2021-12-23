package model;

public class MemberListDTO {
	private int mem_no;
	private String mem_id;
	private String mem_password;
	private String mem_name;
	private String mem_tel;
	private String mem_email;
	private String mem_bday;
	private String mem_reg_date;
	private int mem_auth;
	
	public MemberListDTO(String mem_id, String mem_password, String mem_name, String mem_tel,
			String mem_email, String mem_bday, String mem_reg_date, int mem_auth) {
		this.mem_id = mem_id;
		this.mem_password = mem_password;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.mem_email = mem_email;
		this.mem_bday = mem_bday;
		this.mem_reg_date = mem_reg_date;
		this.mem_auth = mem_auth;
	}


	public MemberListDTO() {
	}


	public MemberListDTO(String mem_id) {
		super();
		this.mem_id = mem_id;
	}


	public MemberListDTO(String mem_id, String mem_password) {
		super();
		this.mem_id = mem_id;
		this.mem_password = mem_password;
	}

	


	public int getMem_no() {
		return mem_no;
	}


	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}


	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	public String getMem_password() {
		return mem_password;
	}


	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}


	public String getMem_name() {
		return mem_name;
	}


	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}


	public String getMem_tel() {
		return mem_tel;
	}


	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}


	public String getMem_email() {
		return mem_email;
	}


	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}


	public String getMem_bday() {
		return mem_bday;
	}


	public void setMem_bday(String mem_birthday) {
		this.mem_bday = mem_birthday;
	}

	

	public String getMem_reg_date() {
		return mem_reg_date;
	}


	public void setMem_reg_date(String mem_reg_date) {
		this.mem_reg_date = mem_reg_date;
	}


	public int getMem_auth() {
		return mem_auth;
	}


	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}


	@Override
	public String toString() {
		return "MemberListDTO [mem_no=" + mem_no + ", mem_id=" + mem_id + ", mem_password=" + mem_password
				+ ", mem_name=" + mem_name + ", mem_tel=" + mem_tel + ", mem_email=" + mem_email + ", mem_bday="
				+ mem_bday + ", mem_reg_date=" + mem_reg_date + ", mem_auth=" + mem_auth + "]";
	}


	
	

	
	
	
	

	
	
	
	
}
