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
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="PRODUCT_SEQ_GEN")
	private Long productId;
	
	@Column(unique=true, nullable = false)
	private String productName;
	@Column(unique=true, nullable = false)
	private String productExplain;
	
	@Builder
	public Shop(Long productId, String productName, String productExplain) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productExplain = productExplain;
	}
}
