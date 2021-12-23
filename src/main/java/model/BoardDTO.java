package model;

public class BoardDTO {
	private int bod_no;
	private String bod_title;
	private String bod_writer;
	private String bod_password;
	private String bod_content;
	private String bod_reg_date;
	private int bod_hits;
	private String com_count;
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BoardDTO(String bod_title, String bod_writer, String bod_password, String bod_content, String bod_reg_date, int bod_hits, String com_count) {
		super();
		this.bod_title = bod_title;
		this.bod_writer = bod_writer;
		this.bod_password = bod_password;
		this.bod_content = bod_content;
		this.bod_reg_date = bod_reg_date;
		this.bod_hits = bod_hits;
		this.com_count = com_count;
	}

	
	

	public int getBod_no() {
		return bod_no;
	}
	public void setBod_no(int bod_no) {
		this.bod_no = bod_no;
	}
	public String getBod_title() {
		return bod_title;
	}
	public void setBod_title(String bod_title) {
		this.bod_title = bod_title;
	}
	public String getBod_writer() {
		return bod_writer;
	}
	public void setBod_writer(String bod_writer) {
		this.bod_writer = bod_writer;
	}
	public String getBod_password() {
		return bod_password;
	}
	public void setBod_password (String bod_password) {
		this.bod_password = bod_password;
	}
	public String getBod_content() {
		return bod_content;
	}
	public void setBod_content(String bod_content) {
		this.bod_content = bod_content;
	}
	public String getBod_reg_date() {
		return bod_reg_date;
	}
	public void setBod_reg_date(String bod_reg_date) {
		this.bod_reg_date = bod_reg_date;
	}
	public int getBod_hits() {
		return bod_hits;
	}
	public void setBod_hits(int bod_hits) {
		this.bod_hits = bod_hits;
	}


	public String getCom_count() {
		return com_count;
	}


	public void setCom_count(String com_count) {
		this.com_count = com_count;
	}


	@Override
	public String toString() {
		return "BoardDTO [bod_no=" + bod_no + ", bod_title=" + bod_title + ", bod_writer=" + bod_writer
				+ ", bod_password=" + bod_password + ", bod_content=" + bod_content + ", bod_reg_date=" + bod_reg_date
				+ ", bod_hits=" + bod_hits + ", com_count=" + com_count + "]";
	}
	
	








	
	
	
	
}
