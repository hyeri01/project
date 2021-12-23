package model;

public class CommentDTO {
	private	String com_no;
	private String com_writer;
	private String com_content;
	private String com_reg_date;
	private int b_no;
	
	public CommentDTO() {
	}

	public CommentDTO(String com_no, String com_writer, String com_content, String com_reg_date, int b_no) {
		super();
		this.com_no = com_no;
		this.com_writer = com_writer;
		this.com_content = com_content;
		this.com_reg_date = com_reg_date;
		this.b_no = b_no;
	}

	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}

	public String getCom_writer() {
		return com_writer;
	}

	public void setCom_writer(String com_writer) {
		this.com_writer = com_writer;
	}

	public String getCom_content() {
		return com_content;
	}

	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}

	public String getCom_reg_date() {
		return com_reg_date;
	}

	public void setCom_reg_date(String com_reg_date) {
		this.com_reg_date = com_reg_date;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	@Override
	public String toString() {
		return "CommentDTO [com_no=" + com_no + ", com_writer=" + com_writer + ", com_content=" + com_content
				+ ", com_reg_date=" + com_reg_date + ", b_no=" + b_no + "]";
	}

	
	
	
	
}
