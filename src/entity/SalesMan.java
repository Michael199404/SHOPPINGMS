package entity;

/**
 * SalesMan 售货员实体
 * @author zhangchaochao
 */
public class SalesMan {
    private int sId;
    private String sName;
    private String sPassword;

    /**
     * 验证用户登录
     */
    public SalesMan(int sId, String sPassword) {
        this.sId = sId;
        this.sPassword = sPassword;
    }

    /**
     * 添加售货员
     * @return
     */
    public SalesMan(String sName, String sPassword){
        this.sName = sName;
        this.sPassword = sPassword;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
}
