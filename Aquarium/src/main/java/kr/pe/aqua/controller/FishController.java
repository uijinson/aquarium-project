package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.aqua.exception.FishNotFoundException;
import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishRepository;

@RestController
@Api(value="test controller specification v1")
@RequestMapping("/api")
public class FishController {

	private final FishRepository repository;
	public FishController(FishRepository repository) {
		this.repository = repository;
	}
	
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "맞아요!"),
//		@ApiResponse(code = 500, message = "서버 에러"),
//		@ApiResponse(code = 403, message = "금지"),
//		@ApiResponse(code = 404, message = "어디있지?"),
//		@ApiResponse(code = 401, message = "허가노노") })
//	
	
//	@ApiOperation(value="all() 메소드 사용", notes="모든 물고기 조회")
//	@GetMapping("/fishes")
//	public List<Fish> all() {
//		return repository.findAll();
//	}
//	@ApiOperation(value="newFish() 메소드 사용", notes="새로운 물고기 추가")
//	@PostMapping("/fishes")
//	public Fish newFish(@ApiParam(value = "물고기 이름을 지어주세요", required = true) @RequestParam String fishName,
//						@ApiParam(value = "물고기를 소개해주세요", required= true) @RequestParam String fishExplain) {
//		Fish newFish = Fish.builder().fishName(fishName).fishExpain(fishExplain).build();
//		return repository.save(newFish);
//	}
//
//	@ApiOperation(value="Fish one() 메소드 사용", notes="물고기 한 마리 검색")
//	@GetMapping("/fishes/{id}")
//	// @PathVariable Long id
//	public Fish one(@ApiParam(value="fish번호", required = true) @RequestParam Long no) {
//
//		return repository.findById(no).orElseThrow(() -> new FishNotFoundException(no));
//	}
//	
	
	
//	@ApiOperation(value="replaceFish() 메소드 사용", notes="기존 물고기 수정")
//	@PutMapping("/fishes/{no}")
//	public Fish replaceFish(@ApiParam(value = "물고기 이름을 지어주세요", required = true) @RequestParam String fishName,
//							@ApiParam(value = "물고기를 소개해주세요", required= true) @RequestParam String fishExplain,
//							@ApiParam(value = "fish번호", required=true) @RequestParam Long no) {
//		Fish newFish = Fish.builder().fishName(fishName).fishExpain(fishExplain).build();
//		return repository.findById(no).map(fish -> {
////			fish.setFid(newFish.getFid());
////			fish.setPw(newFish.getPw());
//			fish.setFishName(newFish.getFishName());
//			fish.setFishExpain(newFish.getFishExpain());
//			return repository.save(fish);
//		}).orElseGet(() -> {
////			newFish.setNo(no);
//			return repository.save(newFish);
//		});
//	}
//	@ApiOperation(value="deleteFish() 메소드 사용", notes="물고기 삭제")
//	@DeleteMapping("/fishes/{no}")
//	public void deleteFish(@ApiParam(value="fish번호", required = true) @RequestParam Long no) {
//		repository.deleteById(no);
//	}
//	
//	//? 사용자 정의 쿼리 메소드 중 이름이 일치되는 동명 이인들 검색해서 출력해보기
//	@GetMapping("/findbyname/{fishName}")
//	public List<Fish> getFishesName(@PathVariable String fishName) {
//		return repository.findFishByFishName(fishName);
//	}
//	@GetMapping("/findbynamecontaining/{fishName}")
//	public List<Fish> getFishContaining(@PathVariable String fishName) {
//		return repository.findByFishNameContaining(fishName);
//	}
//	
	
}

