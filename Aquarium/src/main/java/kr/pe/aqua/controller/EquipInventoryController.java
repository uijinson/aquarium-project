package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.EquipInventory;
import kr.pe.aqua.model.EquipInventoryRepository;

@RestController
@RequestMapping("/testpage")
public class EquipInventoryController {
	
	private final EquipInventoryRepository repository;
	
	public EquipInventoryController(EquipInventoryRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "selectEquip() 메소드 사용", notes = "사용자의 장비 리스트")
	@GetMapping("/equipment/select")
	public List<EquipInventory> selectEquip(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		System.out.println("----EquipInven------"+memId);
		List<EquipInventory> myEquip = repository.findEquipByMemId(memId);
		System.out.println(myEquip);
		return myEquip;
		
	}
}
