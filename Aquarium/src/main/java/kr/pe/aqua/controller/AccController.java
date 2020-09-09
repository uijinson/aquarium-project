package kr.pe.aqua.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.exception.NotFoundException;
import kr.pe.aqua.model.Accessory;
import kr.pe.aqua.model.AccessoryRepository;

@RestController
@Api(value = "AccController")
@RequestMapping("/api")
public class AccController {

	private final AccessoryRepository repository;

	public AccController(AccessoryRepository repository) {
		this.repository = repository;
	}

	// 전체 악세서리 리스트
	@ApiOperation(value="allAcc() 메소드 사용", notes="전체 물고기 리스트")
	@GetMapping("/allAccessories")
	List<Accessory> allAcc() {
		return repository.findAll();
	}

	//악세서리 추가
	@ApiOperation(value="newAcc() 메소드 사용", notes="새로운 악세서리 추가")
	@GetMapping("/newAcc")
	public Accessory newAcc(@ApiParam(value="악세서리의 이름을 입력하세요.", required=true) @RequestParam String accName,
							@ApiParam(value="악세서리의 설명을 입력하세요.", required=true) @RequestParam String accExplain,
							@ApiParam(value="악세서리의 가격을 입력하세요.", required=true) @RequestParam int accPrice,
							@ApiParam(value="악세서리의 accImg을 입력하세요.", required=false) @RequestParam String accImg,
							@ApiParam(value="악세서리의 accSrc을 입력하세요.", required=false) @RequestParam String accSrc) {
		Accessory newAcc = Accessory.builder().accName(accName).accExplain(accExplain).accPrice(accPrice).accImg(accImg).accSrc(accSrc).build();
		return repository.save(newAcc);
	}
	
	//악세서리 한 개 검색
	@ApiOperation(value="oneAcc() 메소드 사용", notes="악세서리 한 개 검색")
	@GetMapping("/allAccessories/{accId}")
	public Accessory oneAcc(@ApiParam(value="악세서리 번호", required = true) @RequestParam Long accId) {
		return repository.findById(accId).orElseThrow(() -> new NotFoundException(accId));
	}
	
	//악세서리 정보 수정
	@ApiOperation(value="updateAcc() 메소드 사용", notes="악세서리 정보 수정")
	@PutMapping("/updateAccessory/{accId}")
	public Optional<Object> updateAcc(	@ApiParam(value="악세서리의 번호를 입력하세요.", required=false) @RequestParam Long accId,
										@ApiParam(value="악세서리의 이름을 입력하세요.", required=true) @RequestParam String accName,
										@ApiParam(value="악세서리의 설명을 입력하세요.", required=true) @RequestParam String accExplain,
										@ApiParam(value="악세서리의 가격을 입력하세요.", required=true) @RequestParam int accPrice,
										@ApiParam(value="악세서리의 accImg을 입력하세요.", required=false) @RequestParam String accImg,
										@ApiParam(value="악세서리의 accSrc을 입력하세요.", required=false) @RequestParam String accSrc){
		
		Accessory newAcc = Accessory.builder().accName(accName).accExplain(accExplain).accPrice(accPrice).accImg(accImg).accSrc(accSrc).build();
		return repository.findById(accId).map(acc -> { 		
			acc.setAccName(newAcc.getAccName());
			acc.setAccExplain(newAcc.getAccExplain());
			acc.setAccPrice(newAcc.getAccPrice());
			acc.setAccImg(newAcc.getAccImg());
			acc.setAccSrc(newAcc.getAccSrc());
			return repository.save(acc);
		});
	}
	
	//악세서리 삭제
	@ApiOperation(value="deleteAccessory() 메소드 사용", notes="악세서리 삭제")
	@DeleteMapping("deleteAccessory/{accId}")
	public void deleteAccessory(@ApiParam(value="악세서리 번호", required = true) @RequestParam Long accId) {
		repository.deleteById(accId);
	}
}
