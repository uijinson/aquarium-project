package kr.pe.aqua.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(value = "FishBowlController specification v1")
@RequestMapping("/api")
public class FishBowlController {
	/*
	private final FishBowlRepository repository;

	public FishBowlController(FishBowlRepository repository) {
		this.repository = repository;
	}

	// 나의 어항 속의 물고기 리스트
	@ApiOperation(value = "oneMemFish() 메소드 사용", notes = "멤버한명 검색")
	@GetMapping("/fishBowl/{fishBowlId}")
	public TotalFishBowl oneMemFishes(@ApiParam(value = "memberId 번호", required = true) @RequestParam Long fishBowlId) {
		return repository.findById(fishBowlId).orElseThrow(() -> new FishNotFoundException(fishBowlId));
	}
	*/
}