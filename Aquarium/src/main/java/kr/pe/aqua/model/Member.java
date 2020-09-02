package kr.pe.aqua.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
	@Id
	private String memId;//중복불허
	private int pw;
	private String bowlName;
	
	@Builder
	public Member(String memId, int pw, String bowlName) {
		super();
		this.memId = memId;
		this.pw = pw;
		this.bowlName = bowlName;
	}
	
	
}
