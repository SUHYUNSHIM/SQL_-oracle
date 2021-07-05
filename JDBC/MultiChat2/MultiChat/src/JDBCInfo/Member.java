package JDBCInfo;

public class Member {
	private String nickname;
	private String region;
	private String birthday;
	
	public Member(String nickname, String region, String birthday) {
		setNickName(nickname);
		setRegion(region);
		setBirthday(birthday);
	}
	public Member(String nickname) {
		setNickName(nickname);
	}
	

	public String getNickName() {
		return nickname;
	}

	public void setNickName(String nickname) {
		this.nickname = nickname;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
