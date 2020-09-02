package kr.pe.aqua.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
//	public List<Fish> findFishByFishName(String fishName);
	
	//select * from member where memId=? and pw=?
	public Member findMemberByMemIdAndPw(String memId, int pw);
	
	
	//delete
	public void deleteById(String memId);
}
