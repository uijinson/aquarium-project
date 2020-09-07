package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.Accessory;
import kr.pe.aqua.model.AccessoryRepository;
import kr.pe.aqua.model.FishRepository;

@RestController
@Api(value="AccController")
@RequestMapping("/api")
public class AccController {
	
	/* AccController
	 * 1. 전체 Acc 출력
	 * 2. 
	 */
	
	private final AccessoryRepository repository;
	
	public AccController(AccessoryRepository repository) {
		this.repository = repository;
	}
	
	//전체 악세서리 조회
	@GetMapping("/allAccessories")
	List<Accessory> all() {
		return repository.findAll();
	}
	
//	@ApiOperation(value="newAcc() 메소드 사용", notes="새로운 악세서리 추가")
//	@GetMapping("/newAcc")
//	public Accessory newAcc(@ApiParam(value="악세서리 이름을 입력하세요.", required=true) @RequestParam String accName)
//	
}
