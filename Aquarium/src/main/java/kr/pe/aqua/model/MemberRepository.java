package kr.pe.aqua.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	
	//1. 현재 잔고(select)
	//select money from fish where mem_id=?;
	public Member findMoneyByMemId(String memId);
	
	
	
//	public List<Fish> findFishByFishName(String fishName);
	
	//select * from member where memId=? and pw=?
	public Member findMemberByMemIdAndPw(String memId, int pw);
	
	//delete
	public void deleteById(String memId);


}
