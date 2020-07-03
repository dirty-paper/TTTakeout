package model;

import java.sql.Date;

public class BeanKnight_info {
	private String knight_id;
	private String knight_name;
	private Date knight_join;
	private String knight_level;
	public String getKnight_id() {
		return knight_id;
	}
	public void setKnight_id(String knight_id) {
		this.knight_id = knight_id;
	}
	public String getKnight_name() {
		return knight_name;
	}
	public void setKnight_name(String knight_name) {
		this.knight_name = knight_name;
	}
	public Date getKnight_join() {
		return knight_join;
	}
	public void setKnight_join(Date knight_join) {
		this.knight_join = knight_join;
	}
	public String getKnight_level() {
		return knight_level;
	}
	public void setKnight_level(String knight_level) {
		this.knight_level = knight_level;
	}
	
}
