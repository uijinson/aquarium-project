package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.AccInventory;
import kr.pe.aqua.model.AccInventoryRepository;

@RestController
@RequestMapping("/testpage")
public class AccInventoryController {
	
	private final AccInventoryRepository repository;
	
	public AccInventoryController(AccInventoryRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "selectAcc() 메소드 사용", notes = "사용자의 악세사리 리스트")
	@GetMapping("/accessory/select")
	public List<AccInventory> selectAcc(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		System.out.println("------AccInven-------"+memId);
		List<AccInventory> myAcc = repository.findAccByMemId(memId);
		System.out.println(myAcc);
		return myAcc;
	}
}
