package kr.pe.aqua.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
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

public class Fish {
	@Id
	@GeneratedValue
	private Long no; //pk
	//추가
	@Column(unique=true, nullable=false)
	private String fid;//중복불허
	private int pw;
	
	private String fishName;
	private String fishExpain;
	
	
	@Builder
	public Fish(Long no, String fid, int pw, String fishName, String fishExpain) {
		super();
		this.no = no;
		this.fid = fid;
		this.pw = pw;
		this.fishName = fishName;
		this.fishExpain = fishExpain;
	}

	
	
}
