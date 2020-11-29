package bean.user;

import com.google.gson.Gson;

/**
 * 用户数据
 */
public class User {
	String uid;
	String username;
	UserSex sex;
	String tel;
	String email;
	UserIdentity identity;
	long regTime;

	public UserIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(UserIdentity identity) {
		this.identity = identity;
	}

	public long getRegTime() {
		return regTime;
	}

	public void setRegTime(long regTime) {
		this.regTime = regTime;
	}

	public UserSex getSex() {
		return sex;
	}

	public void setSex(UserSex sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
