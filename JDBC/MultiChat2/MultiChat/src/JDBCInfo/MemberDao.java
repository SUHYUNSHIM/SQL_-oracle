package JDBCInfo;

public interface MemberDao {
	//Member getMemberByNickName(String nickname); 닉네임으로 정보를 찾는 일은 일단 없을 것 같다.
	Member getMemberByRegion(String region);
	Member getMemberByYear(String birthday);
	Member getMemberByMonth(String birthday);
	
	void createMember();
	void insertMember(Member member);
}
