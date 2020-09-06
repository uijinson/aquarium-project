package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.EquipInventory;
import kr.pe.aqua.model.EquipInventoryRepository;

public class EquipInventoryController {
	
	private final EquipInventoryRepository repository;
	
	public EquipInventoryController(EquipInventoryRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "selectFish() 메소드 사용", notes = "사용자의 물고기 리스트")
	@GetMapping("/fish/select")
	public List<EquipInventory> selectFish(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memberId) {
		
		return repository.findequipByMemId(memberId);
		
	}
}
