package kr.pe.aqua.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class TotalFishShop {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PER_SHOP_SEQ_GEN")
	private Long perShopNo; // 번호

	// 전체어항상점리스트에서 멤버(회원)를 참조
	@ManyToOne
	@JoinColumn(name = "fishShopId")
	private Member member;

	@OneToOne
	@JoinColumn(name = "productId")
	private Shop shop;

	@Builder
	public TotalFishShop(Long perShopNo, Member member, Shop shop) {
		super();
		this.perShopNo = perShopNo;
		this.member = member;
		this.shop = shop;
	}

}
