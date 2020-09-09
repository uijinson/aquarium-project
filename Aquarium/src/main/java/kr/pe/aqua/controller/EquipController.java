package kr.pe.aqua.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.exception.NotFoundException;
import kr.pe.aqua.model.Equipment;
import kr.pe.aqua.model.EquipmentRepository;

@RestController 
@Api(value="EquipController specification v1")
@RequestMapping("/api")
public class EquipController {
	private final EquipmentRepository repository;

	public EquipController(EquipmentRepository repository) {
		this.repository = repository;
	}

	// 전체 장비 리스트
	@ApiOperation(value = "allEquip() 메소드 사용", notes = "전체 장비 리스트")
	@GetMapping("/allEquipments")
	List<Equipment> allEquip() {
		return repository.findAll();
	}

	// 장비 추가
	@ApiOperation(value = "newEquip() 메소드 사용", notes = "새로운 장비 추가")
	@GetMapping("/newEquip")
	public Equipment newEquip(@ApiParam(value = "장비의 이름을 입력하세요.", required = true) @RequestParam String equipName,
			@ApiParam(value = "장비의 설명을 입력하세요.", required = true) @RequestParam String equipExplain,
			@ApiParam(value = "장비의 가격을 입력하세요.", required = true) @RequestParam int equipPrice,
			@ApiParam(value = "장비의 EquipImg을 입력하세요.", required = false) @RequestParam String equipImg,
			@ApiParam(value = "장비의 EquipSrc을 입력하세요.", required = false) @RequestParam String equipSrc) {
		Equipment newEquip = Equipment.builder().equipName(equipName).equipExplain(equipExplain).equipPrice(equipPrice)
				.equipImg(equipImg).equipSrc(equipSrc).build();
		return repository.save(newEquip);
	}
	
	//장비 한 개 검색
	@ApiOperation(value = "oneEquip() 메소드 사용", notes = "악세사리 한 개 검색")
	@GetMapping("/allEquipments/{equipId}")
	public Equipment oneEquip(@ApiParam(value = "장비 번호", required=true) @RequestParam Long equipId) {
		return repository.findById(equipId).orElseThrow(() -> new NotFoundException(equipId));
	}
	
	@ApiOperation(value = "update6Equip() 메소드 사용", notes = "새로운 장비 추가")
	@GetMapping("/updateEquip/{equipId}")
	public Optional<Object> updateEquip(@ApiParam(value = "장비의 번호을 입력하세요.", required = false) @RequestParam Long equipId,
			@ApiParam(value = "장비의 이름을 입력하세요.", required = true) @RequestParam String equipName,
			@ApiParam(value = "장비의 설명을 입력하세요.", required = true) @RequestParam String equipExplain,
			@ApiParam(value = "장비의 가격을 입력하세요.", required = true) @RequestParam int equipPrice,
			@ApiParam(value = "장비의 EquipImg을 입력하세요.", required = false) @RequestParam String equipImg,
			@ApiParam(value = "장비의 EquipSrc을 입력하세요.", required = false) @RequestParam String equipSrc) {
		Equipment newEquip = Equipment.builder().equipId(equipId).equipName(equipName).equipExplain(equipExplain).equipPrice(equipPrice)
				.equipImg(equipImg).equipSrc(equipSrc).build();
		return repository.findById(equipId).map(equip -> { 		
			equip.setEquipName(newEquip.getEquipName());
			equip.setEquipExplain(newEquip.getEquipExplain());
			equip.setEquipPrice(newEquip.getEquipPrice());
			equip.setEquipImg(newEquip.getEquipImg());
			equip.setEquipSrc(newEquip.getEquipSrc());
			return repository.save(equip);
		});
	}
	
	//장비 삭제
	@ApiOperation(value="deleteEquipment() 메소드 사용", notes="장비 삭제")
	@DeleteMapping("deleteEquipment/{equipId}")
	public void deleteEquipment(@ApiParam(value="장비 번호", required = true) @RequestParam Long equipId) {
		repository.deleteById(equipId);
	}
	
	
	
}
