package bean;

import com.google.gson.Gson;

enum identity {
    subscriber,
    manager
}

enum sex {
    unknown,
    man,
    woman
}


public class User {
    String uid;
    String username;
    sex sex;
    String majorClass;
    String tel;
    identity identity;
    long regTime;

    public bean.identity getIdentity() {
        return identity;
    }

    public void setIdentity(bean.identity identity) {
        this.identity = identity;
    }

    public long getRegTime() {
        return regTime;
    }

    public void setRegTime(long regTime) {
        this.regTime = regTime;
    }

    public bean.sex getSex() {
        return sex;
    }

    public void setSex(bean.sex sex) {
        this.sex = sex;
    }

    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
