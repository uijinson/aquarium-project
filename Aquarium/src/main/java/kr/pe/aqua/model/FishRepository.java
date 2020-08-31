package kr.pe.aqua.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
	//이름으로 검색하는 메소드를 추가
	//pk는 id로 되어있음. 따라서 이름은 중복 가능.
	//반환 타입? 
	//select * from fish where name=?;
	public List<Fish> findFishByFishName(String fishName);
	
	//like %이름% - containing
	public List<Fish> findByFishNameContaining(String fishName);
	
	//select * from fish where fid=? and pw=?
	public Fish findFishByFidAndPw(String fid, int pw);

//	public Optional<Fish> findByNo(Long no);
//
//	public void deleteByNo(Long no);
}