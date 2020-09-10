package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.AccInventory;
import kr.pe.aqua.model.Accessory;
import kr.pe.aqua.model.EquipInventory;
import kr.pe.aqua.model.EquipInventoryRepository;
import kr.pe.aqua.model.Equipment;
import kr.pe.aqua.model.Member;

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
	
	@ApiOperation(value = "buyEquip() 메소드 사용", notes = "장비 구매")
	@GetMapping("/fish/buyEquip")
	public EquipInventory buyEquip(@ApiParam(value = "악세러리 Id를 입력하세요.", required = true) @RequestParam Long equipId,
                        @ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
      
      Equipment EQUIP = new Equipment();
      Equipment equipId1 = Equipment.builder().equipId(equipId).build();
      
      Member Member = new Member();
      Member memId1 = Member.builder().memId(memId).build();
      
      EquipInventory buyEquip = EquipInventory.builder().equipId(equipId1).memId(memId1).build();
      return repository.save(buyEquip);
   }
}
