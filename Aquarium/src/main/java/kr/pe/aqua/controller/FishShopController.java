package kr.pe.aqua.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(value = "FishBowlController specification v1")
@RequestMapping("/api")
public class FishShopController {
	/*
	private final FishShopRepository repository;

	public FishShopController(FishShopRepository repository) {
		this.repository = repository;
	}

	// 나의 어항 속의 물고기 리스트
	@ApiOperation(value = "oneMemProducts() 메소드 사용", notes = "멤버한명 검색")
	@GetMapping("/fishshop/{fishShopId}")
	public TotalFishShop oneMemProducts(@ApiParam(value = "memberId 번호", required = true) @RequestParam Long fishShopId) {
		return repository.findById(fishShopId).orElseThrow(() -> new FishNotFoundException(fishShopId));
	}
	*/
}