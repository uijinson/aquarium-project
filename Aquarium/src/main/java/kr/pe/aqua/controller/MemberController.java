package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.Member;
import kr.pe.aqua.model.MemberRepository;

@RestController
@Api(value = "member controller specification v1")
@RequestMapping(value="/api")
public class MemberController {
	private final MemberRepository repository;

	public MemberController(MemberRepository repository) {
		this.repository = repository;
	}

	@ApiOperation(value = "all() 메소드 사용", notes = "모든 회원 조회")
	@GetMapping("/members")
	@RequestMapping(value="/api/members", method=RequestMethod.GET)
	public List<Member> all() {
		return repository.findAll();
	}
	
	/*
	 * @ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam(value ="memId") String memId,
			@ApiParam(value = "비밀번호를 입력해주세요", required = true) @RequestParam(value ="pw") int pw,
			@ApiParam(value = "어항의 이름을 입력해주세요", required = false) @RequestParam(value ="bowlName") String bowlName
	 */

	@ApiOperation(value = "newMember() 메소드 사용", notes = "신규 회원 가입")
	@PostMapping("/members")
	//@RequestMapping(value="/api/members", method=RequestMethod.POST)
	public Member newMember(@RequestBody Member member) {
		Member newMember = Member.builder().memId(member.getMemId()).pw(member.getPw()).bowlName(member.getBowlName()).build();
		System.out.println(member);
		return repository.save(newMember);
	}
	

	@ApiOperation(value = "replaceMember() 메소드 사용", notes = "기존 회원정보 수정")
	@PutMapping("/members/{memId}")
	public Member replaceFish(
			@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId,
			@ApiParam(value = "비밀번호를 입력해주세요", required = true) @RequestParam int pw,
			@ApiParam(value = "어항의 이름을 입력해주세요", required = true) @RequestParam String bowlName) {
		Member newMember = Member.builder().memId(memId).pw(pw).bowlName(bowlName).build();
		return repository.findById(memId).map(member -> {
			member.setMemId(newMember.getMemId());
			member.setPw(newMember.getPw());
			member.setBowlName(newMember.getBowlName());
			return repository.save(member);
		}).orElseGet(() -> {
			newMember.setMemId(memId);
			return repository.save(newMember);
		});
	}

	@ApiOperation(value = "deleteMember() 메소드 사용", notes = "회원 삭제")
	@DeleteMapping("/members/{memId}")
	public void deleteMember(@ApiParam(value = "회원 이메일", required = true) @RequestParam String memId) {
		repository.deleteById(memId);
	}
}
