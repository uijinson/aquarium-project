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

	private final MemberRepository repository;

	@Autowired
	private FishRepository fishrepository;

	@Autowired
	private AccessoryRepository accessoryrepository;

	@Autowired
	private EquipmentRepository equipmentrepository;

	@Autowired
	private VillainRepository vilRepository;

	public MemberController(MemberRepository repository) {
		this.repository = repository;
	}

	// 회원가입 -> id, pw, nickName, money(default:1000)
	@ApiOperation(value = "newMember() 메소드 사용", notes = "신규 회원 가입")
	@PostMapping("/members")
	public boolean newMember(@RequestBody Member member) {
		if (repository.existsById(member.getMemId())) {
			return false;
		}
		repository.save(member);

		return true;
	}

	// update : 수정 -> pw, nickName 수정
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

	// delete : 탈퇴(delete)
	@ApiOperation(value = "deleteMember() 메소드 사용", notes = "회원 삭제")
	@DeleteMapping("/members/{memId}")
	public void deleteMember(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		repository.deleteById(memId);
	}

	// 돈관리(money) -> 내가 가지고 있는 돈 출력, 돈+- 관리
	// 1. 현재 잔고(select)
	@ApiOperation(value = "manageMoney() 메소드 사용", notes = "사용자의 현재 잔고 확인")
	@GetMapping("/money/select")
	public int selectMoney(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		return repository.findMoneyByMemId(memId).getMoney();
	}

	// 2. villain -> 잡으면 -> 돈이 증가(+ : update)
	@ApiOperation(value = "vilkillMoney() 메소드 사용", notes = "villain을 잡았을 때, 돈 증가")
	@PutMapping("/money/vilkill")
	public Member vilkillMoney(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestBody Map<String, String> param) {
		String memId = param.get("memId");

		return repository.findById(memId).map(member -> {
			member.setMoney(member.getMoney() + 100);
			return repository.save(member);
		}).orElse(null);
	}

	// 3-1. 물고기 상점 -> 물고기 구매 -> 돈 감소 & fish inventory -> 팔기 -> 물건의 60% 돈 증가
	@SuppressWarnings("unlikely-arg-type")
	@ApiOperation(value = "afterBuyFishMoney() 메소드 사용", notes = "물고기 구매 후, 돈 감소")
	@PutMapping("/money/fish")
	public boolean afterBuyFishMoney(@RequestBody Map<String, String> param) {
		String memId = param.get("memId");
		Long fishId = Long.parseLong(param.get("fishId"));
		int flag = Integer.parseInt(param.get("flag"));

		int memMoney = repository.findMoneyByMemId(memId).getMoney();
		int fishPrice = fishrepository.findFishPriceByFishId(fishId).getFishPrice();
		Member member = repository.findMoneyByMemId(memId);

		if (flag == 1) {
			if (memMoney - fishPrice >= 0) {
				member.setMoney(memMoney - fishPrice);
				repository.save(member);
				return true;
			}
		} else {
			fishPrice = (int) (0.6 * fishPrice);
			member.setMoney(memMoney + fishPrice);
			repository.save(member);
			return true;
		}

		return false;
	}

	// 3-2. 악세서리 상점 -> 악세서리 구매시 -> 돈 감소 & accessory inventory -> 팔기 -> 물건의 60% 돈 증가
	@SuppressWarnings("unlikely-arg-type")
	@ApiOperation(value = "afterBuyAccMoney() 메소드 사용", notes = "악세사리 구매 후, 돈 감소")
	@PutMapping("/money/acc")
	public boolean afterBuyAccMoney(@RequestBody Map<String, String> param) {
		String memId = param.get("memId");
		Long accId = Long.parseLong(param.get("accId"));
		int flag = Integer.parseInt(param.get("flag"));

		int memMoney = repository.findMoneyByMemId(memId).getMoney();
		int accPrice = accessoryrepository.findAccPriceByAccId(accId).getAccPrice();
		Member member = repository.findMoneyByMemId(memId);

		if (flag == 1) {
			if (memMoney - accPrice >= 0) {
				member.setMoney(memMoney - accPrice);
				repository.save(member);
				return true;
			}
		} else {
			accPrice = (int) (0.6 * accPrice);
			member.setMoney(memMoney + accPrice);
			repository.save(member);
			return true;
		}
		return false;
	}

	// 3-3. 장비 상점 -> 장비 구매시 -> 돈 감소 & equipment inventory -> 팔기 -> 물건의 60% 돈 증가
	@SuppressWarnings("unlikely-arg-type")
	@ApiOperation(value = "afterBuyEquipMoney() 메소드 사용", notes = "물고기 구매 후, 돈 감소")
	@PutMapping("/money/equipment")
	public boolean afterBuyEquipMoney(@RequestBody Map<String, String> param) {

		String memId = param.get("memId");
		Long equipid = Long.parseLong(param.get("equipId"));
		int flag = Integer.parseInt(param.get("flag"));

		int memMoney = repository.findMoneyByMemId(memId).getMoney();
		int equipPrice = equipmentrepository.findEquipPriceByEquipId(equipid).getEquipPrice();
		Member member = repository.findMoneyByMemId(memId);

		if (flag == 1) {
			System.out.println("--멤버찾기" + member);
			if (memMoney - equipPrice >= 0) {
				member.setMoney(memMoney - equipPrice);
				repository.save(member);
				return true;
			}
		} else {
			equipPrice = (int) (0.6 * equipPrice);
			member.setMoney(memMoney + equipPrice);
			repository.save(member);
			return true;
		}

		return false;
	}

}
