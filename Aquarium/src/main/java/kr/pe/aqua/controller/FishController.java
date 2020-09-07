package kr.pe.aqua.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(value="FishController specification v1")
@RequestMapping("/api")
public class FishController {
	
	
	private final FishRepository repository;
	public FishController(FishRepository repository) {
		this.repository = repository;
	}
	
	@ApiResponses({
		@ApiResponse(code = 200, message = "맞아요!"),
		@ApiResponse(code = 500, message = "서버 에러"),
		@ApiResponse(code = 403, message = "금지"),
		@ApiResponse(code = 404, message = "어디있지?"),
		@ApiResponse(code = 401, message = "허가노노") })
	
	@GetMapping("/allfish")
	List<Fish> all() {
		return repository.findAll();
	}
	
	@ApiOperation(value="newFish() 메소드 사용", notes="새로운 물고기 추가")
	@GetMapping("/fishes")
	public Fish newFish(@ApiParam(value = "물고기 이름을 지어주세요", required = true) @RequestParam String fishName,
						@ApiParam(value = "물고기를 소개해주세요", required= true) @RequestParam String fishExplain) {
		Fish newFish = Fish.builder().fishName(fishName).fishExplain(fishExplain).build();
		return repository.save(newFish);
	}

	@ApiOperation(value="Fish one() 메소드 사용", notes="물고기 한 마리 검색")
	@GetMapping("/fishes/{fishId}")
	public Fish oneFish(@ApiParam(value="fish번호", required = true) @RequestParam Long fishId) {

		return repository.findById(fishId).orElseThrow(() -> new FishNotFoundException(fishId));
	}
	
	@ApiOperation(value="replaceFish() 메소드 사용", notes="기존 물고기 수정")
	@PutMapping("/fishes/{fishId}")
	public Optional<Object> replaceFish(@ApiParam(value = "물고기 이름을 지어주세요", required = false) @RequestParam String fishName,
							@ApiParam(value = "물고기를 소개해주세요", required= false) @RequestParam String fishExplain,
							@ApiParam(value = "fish번호", required=true) @RequestParam Long fishId) {
		Fish newFish = Fish.builder().fishName(fishName).fishExplain(fishExplain).build();
		return repository.findById(fishId).map(fish -> {
			fish.setFishName(newFish.getFishName());
			fish.setFishExplain(newFish.getFishExplain());
			return repository.save(fish);
		});
	}
	
	@ApiOperation(value="deleteFish() 메소드 사용", notes="물고기 삭제")
	@DeleteMapping("/fishes/{fishId}")
	public void deleteFish(@ApiParam(value="fish번호", required = true) @RequestParam Long fishId) {
		repository.deleteById(fishId);
	}
	
}

