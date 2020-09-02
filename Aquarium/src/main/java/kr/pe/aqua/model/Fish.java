package kr.pe.aqua.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

public class Fish {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="FISH_SEQ_GEN")
	private Long fishId; //pk
	@Column(unique = true, nullable = false)
	private String fishName;
	@Column(unique = true, nullable = false)
	private String fishExpain;
	
	@Builder
	public Fish(Long fishId, String fishName, String fishExpain) {
		super();
		this.fishId = fishId;
		this.fishName = fishName;
		this.fishExpain = fishExpain;
	}
}
