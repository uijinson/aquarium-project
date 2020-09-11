package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.Fish;
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
	@GetMapping("/fish/select")
	public List<FishInventory> selectFish(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		List<FishInventory> myFish = repository.findFishByMemId(memId);
		System.out.println(myFish);
		
		return myFish;
	}

	
	@ApiOperation(value = "buyFish() 메소드 사용", notes = "물고기 구매")
	@GetMapping("/fish/buyFish")
	public FishInventory buyFish(@ApiParam(value = "물고기 Id를 입력하세요.", required = true) @RequestParam Long fishId,
								@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		
		Fish fishId1 = Fish.builder().fishId(fishId).build();
		Member memId1 = Member.builder().memId(memId).build();
		FishInventory buyFish = FishInventory.builder().fishId(fishId1).memId(memId1).build();
		
		return repository.save(buyFish);
   }

   //FishInventory에 담겨있는 것 중에 삭제
   @ApiOperation(value = "deleteFish() 메소드 사용", notes = "fishInventory에 담겨있는 물고기 삭제")
   @DeleteMapping("/deleteFish")
   public void deleteEquip(@ApiParam(value = "물고기 No를 입력하세요.", required = true) @RequestParam Long fishNo) {
      repository.deleteById(fishNo); 
   }
}