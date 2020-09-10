package kr.pe.aqua.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(value = "test controller specification v1")
@RequestMapping("/api")
public class ShopController {

	/*
	private final ShopRepository repository;

	public ShopController(ShopRepository repository) {
		this.repository = repository;
	}

	@ApiOperation(value = "newProduct() 메소드 사용", notes = "새로운 상품 추가")
	@GetMapping("/products")
	public Shop newProduct(@ApiParam(value = "상품명을 입력하세요.", required = true) @RequestParam String productName,
			@ApiParam(value = "상품 설명을 입력하세요.", required = true) @RequestParam String productExplain) {
		Shop newProduct = Shop.builder().productName(productName).productExplain(productExplain).build();
		return repository.save(newProduct);
	}

	@ApiOperation(value = "oneProduct() 메소드 사용", notes = "상품 한 개 검색")
	@GetMapping("/products/{productId}")
	public Shop oneProduct(@ApiParam(value = "productId", required = true) @RequestParam Long productId) {

		return repository.findById(productId).orElseThrow(() -> new FishNotFoundException(productId));
	}

	@ApiOperation(value = "replaceShop() 메소드 사용", notes = "기존 물고기 수정")
	@PutMapping("/products/{productId}")
	public Optional<Object> replaceFish(
			@ApiParam(value = "상품명을 입력하세요.", required = false) @RequestParam String productName,
			@ApiParam(value = "상품 설명을 입력하세요.", required = false) @RequestParam String productExplain,
			@ApiParam(value = "productId 번호", required = true) @RequestParam Long productId) {
		Shop newProduct = Shop.builder().productName(productName).productExplain(productExplain).build();
		return repository.findById(productId).map(shop -> {
			shop.setProductName(newProduct.getProductName());
			shop.setProductExplain(newProduct.getProductExplain());
			return repository.save(shop);
		});
	}
	
	@ApiOperation(value="deleteShop() 메소드 사용", notes="상품 삭제")
	@DeleteMapping("/products/{productId}")
	public void deleteShop(@ApiParam(value="productId", required = true) @RequestParam Long productId) {
		repository.deleteById(productId);
	}
	*/
}