package kr.pe.aqua.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity(name="member")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
	@Id
	@Column(name="memId", unique=true, nullable=false)
	private String memId;// 중복불허
	
	@Column(name="pw")
	private int pw;
	
	@Column(name="nickName")
	private String nickName;
	
	@Column(name="money")
	@ColumnDefault("1000")
	private int money;
	
	@Builder
	public Member(String memId, int pw, String nickName, int money) {
		super();
		this.memId = memId;
		this.pw = pw;
		this.nickName = nickName;
		this.money = money;
	}

}
