package kr.pe.aqua.controller;

import java.util.List;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 0f3fdd778ad88b3ef0ac9decedb82e72e2c4855a
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.FishInventory;
import kr.pe.aqua.model.FishInventoryRepository;
import kr.pe.aqua.model.Member;

@RestController
@RequestMapping("/testpage")
public class FishInventoryController {
		
	private final FishInventoryRepository repository;
	
	public FishInventoryController(FishInventoryRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "selectFish() 메소드 사용", notes = "사용자의 물고기 리스트")
<<<<<<< HEAD
	@GetMapping("/fish/select")
	public List<FishInventory> selectFish(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		System.out.println("???? "+memId);
		List<FishInventory> myFish = repository.findFishByMemId(memId);
		System.out.println(myFish);
		return myFish;
=======
	@PostMapping("/fish/select")
	public List<FishInventory> selectFish(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestBody String memberId) {
		System.out.println("????"+memberId);
		List<FishInventory> a = repository.findFishByMemId(memberId);
		System.out.println(a);
		return a;
	
>>>>>>> 0f3fdd778ad88b3ef0ac9decedb82e72e2c4855a
	}
}












