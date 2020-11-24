package bean;

/**
 * 工单数据
 */
public class Order {
    String oid;
    String uid;
    String type;
    String desc;
    String imgSrcs;
    long submitTime;
    String state;
    String resp;
    long respTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgSrcs() {
        return imgSrcs;
    }

    public void setImgSrcs(String imgSrcs) {
        this.imgSrcs = imgSrcs;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public long getRespTime() {
        return respTime;
    }

    public void setRespTime(long respTime) {
        this.respTime = respTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
