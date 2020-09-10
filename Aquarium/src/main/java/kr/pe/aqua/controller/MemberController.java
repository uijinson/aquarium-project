package kr.pe.aqua.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.AccessoryRepository;
import kr.pe.aqua.model.EquipmentRepository;
import kr.pe.aqua.model.FishRepository;
import kr.pe.aqua.model.Member;
import kr.pe.aqua.model.MemberRepository;
import kr.pe.aqua.model.VillainRepository;

@RestController
@Api(value = "MemberController specification v1")
@RequestMapping(value = "/api")
public class MemberController {

	/* MemberController
	 * 1. 회원가입(id, pw, nickName, money)
	 * 2. 수정하기
	 * 3. 탈퇴하기
	 * 4. 돈관리
	 * 		1. 현재 잔고(select)
	 * 		2. villain 잡을 시 -> 돈이 증가(+ : update)
	 * 		3. 상점 -> 물고기/악세서리/장비 구매시 -> 구매금만큼 돈이 감소(- : update)
	 */

	//singleton
	private final MemberRepository repository;
	@Autowired
	private FishRepository fishrepository;
	
	@Autowired
	private AccessoryRepository accessoryrepository;
	
	@Autowired
	private EquipmentRepository equipmentrepository;
	
	private VillainRepository vilRepository;
	
	public MemberController(MemberRepository repository) {
		this.repository = repository;
	}
	
	//로그인 -> id, pw (웹에서 처리) -> jwt에서 토큰 -> 웹페이선 토큰확인...
	
	//insert : 회원가입 -> id, pw, nickName, money(default:1000)
	@ApiOperation(value = "newMember() 메소드 사용", notes = "신규 회원 가입")
	@PostMapping("/members")
	public boolean newMember(@RequestBody Member member) {
		if (repository.existsById(member.getMemId())) {
			return false;
		}
		repository.save(member);
		return true;
	}

	//update : 수정 -> pw, nickName 수정
	@ApiOperation(value = "replaceMember() 메소드 사용", notes = "기존 회원정보 수정")
	@PutMapping("/members/{memId}")
	public Member updateFish(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId,
			@ApiParam(value = "비밀번호를 입력해주세요", required = true) @RequestParam int pw,
			@ApiParam(value = "어항의 이름을 입력해주세요", required = true) @RequestParam String nickName) {
		Member newMember = Member.builder().memId(memId).pw(pw).nickName(nickName).build();
		return repository.findById(memId).map(member -> {
			member.setPw(newMember.getPw());
			member.setNickName(newMember.getNickName());
			return repository.save(member); 
		}).orElse(null);
	}

	//delete : 탈퇴(delete)
	@ApiOperation(value = "deleteMember() 메소드 사용", notes = "회원 삭제")
	@DeleteMapping("/members/{memId}")
	public void deleteMember(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		repository.deleteById(memId);
	}
	
	
	//돈관리(money) -> 내가 가지고 있는 돈 출력, 돈+- 관리
	//1. 현재 잔고(select)
	@ApiOperation(value = "manageMoney() 메소드 사용", notes = "사용자의 현재 잔고 확인")
	@GetMapping("/money/select")
	public int selectMoney(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId ,@RequestBody Member member) {
		return repository.findMoneyByMemId(memId).getMoney();
	}

	//2. villain -> 잡으면 -> 돈이 증가(+ : update)
	@ApiOperation(value = "vilkillMoney() 메소드 사용", notes = "villain을 잡았을 때, 돈 증가")
	@PutMapping("/money/vilkill")
	public Member vilkillMoney(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId
										, @RequestParam Long vilId) {
		return repository.findById(memId).map(member ->{
			member.setMoney(member.getMoney() + vilRepository.findByVilId(vilId).getVilReward());
			return repository.save(member); 
		}).orElse(null);
	}


	//3. 상점 -> 물고기/악세서리/장비 ????구매하면 -> 구매금만큼 돈이 감소(- : update)
//	@SuppressWarnings("unlikely-arg-type")
//	@ApiOperation(value = "afterBuyMoney() 메소드 사용", notes = "물고기/악세서리/장비 구매 후, 돈 감소")
//	@PutMapping("/money/{table}/{id}")
//	public void afterBuyMoney(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId, 
//			@PathVariable String table, @PathVariable String id, 
//			@RequestBody Fish fish,
//			@RequestBody Member member, 
//			@RequestBody Accessory accessory,
//			@RequestBody Equipment equipment) {
//		
//		if(table.equals("Fish")) {
//			if(id.equals(fish.getFishId())) {
//				member.setMoney(member.getMoney() - fish.getFishPrice());
//				repository.save(member);
//			}
//		}else if(table.equals("Accessory")) {
//			if(id.equals(accessory.getAccId())) {
//				member.setMoney(member.getMoney() - accessory.getAccPrice());
//				repository.save(member);
//			}
//		}else if(table.equals("Equipment")) {
//			if(id.equals(equipment.getEquipId())) {
//				member.setMoney(member.getMoney() - equipment.getEquipPrice());
//				repository.save(member);
//			}
//		}
//
//	}
	
	// 3-1. 물고기 상점 -> 물고기 구매시 -> 구매금만큼 돈이 감소( - : update)
	@SuppressWarnings("unlikely-arg-type")
	@ApiOperation(value = "afterBuyFishMoney() 메소드 사용", notes = "물고기 구매 후, 돈 감소")
	@PutMapping("/money/fish")
	public boolean afterBuyFishMoney(@RequestBody Map<String,String> param) {
		String memId = param.get("memId");
		Long fishId = Long.parseLong(param.get("fishId"));
	
		System.out.println("풋매핑"+memId);
		System.out.println("----"+fishId);
			
		
		int memMoney = repository.findMoneyByMemId(memId).getMoney();
		int fishPrice = fishrepository.findFishPriceByFishId(fishId).getFishPrice();
		Member member = repository.findMoneyByMemId(memId);
		System.out.println("--멤버찾기"+member);
		if (memMoney-fishPrice>=0) {
			member.setMoney(memMoney-fishPrice);
			repository.save(member);
			System.out.println(member);
			return true;
		}
		
		return false;
		
	     //System.out.println("--여기는 피시머니"+repository.findMoneyByMemId(memId).getMoney());
	     //fishrepository.findAll()
	     //fishrepository.findFishPriceByFishId(fid).getFishPrice();
		 //System.out.println("머니머니="+fishrepository.findById(fid));
	     //System.out.println("머니="+fishrepository.findFishPriceByFishId(fid).getFishPrice());
	      
//	      if (memId.equals(member.getMemId())) {
//	         member.setMoney(member.getMoney() - fish.getFishPrice());
//	         repository.save(member);
//	      }
	   }

	   // 3-2. 악세서리 상점 -> 장비 구매시 -> 구매금만큼 돈이 감소
	   @SuppressWarnings("unlikely-arg-type")
	   @ApiOperation(value = "afterBuyAccMoney() 메소드 사용", notes = "악세사리 구매 후, 돈 감소")
	   @PutMapping("/money/acc")
	   public boolean afterBuyAccMoney( @RequestBody Map<String,String> param) {
		   	String memId = param.get("memId");
			Long accId = Long.parseLong(param.get("accId"));
		
			System.out.println("풋매핑"+memId);
			System.out.println("----"+accId);
			
			int memMoney = repository.findMoneyByMemId(memId).getMoney();
			int accPrice = accessoryrepository.findAccPriceByAccId(accId).getAccPrice();
			Member member = repository.findMoneyByMemId(memId);
			System.out.println("--멤버찾기"+member);
			if (memMoney-accPrice>=0) {
				member.setMoney(memMoney-accPrice);
				repository.save(member);
				System.out.println(member);
				return true;
			}
			return false;
	   }

	   // 3-3. 장비 상점 -> 장비 구매시 -> 구매금만큼 돈이 감소
	   @SuppressWarnings("unlikely-arg-type")
	   @ApiOperation(value = "afterBuyEquipMoney() 메소드 사용", notes = "물고기 구매 후, 돈 감소")
	   @PutMapping("/money/equipment")
	   public boolean afterBuyEquipMoney(@RequestBody Map<String,String> param) {

		   	String memId = param.get("memId");
			Long equipid = Long.parseLong(param.get("equipId"));
			
			System.out.println("풋매핑"+memId);
			System.out.println("----"+equipid);

			int memMoney = repository.findMoneyByMemId(memId).getMoney();
			int equipPrice = equipmentrepository.findEquipPriceByEquipId(equipid).getEquipPrice();
			
			Member member = repository.findMoneyByMemId(memId);
			
			System.out.println("--멤버찾기"+member);
			if (memMoney-equipPrice>=0) {
				member.setMoney(memMoney-equipPrice);
				repository.save(member);
				System.out.println(member);
				return true;
			}
			return false;
	   }

}
