package work.boku.comservice.classes;

import work.boku.comservice.activities.BaseActivity;

public class ResidentBean extends BaseActivity {

    // private static String TAG = "ResidentBean";

    private int community_id;// 社区ID

    private String passwd;// 用户密码

    private String identity_number;// 身份证号

    private String resident_name;// 姓名


    private String phone_number;// 手机号码

    private int permission_level;// 权限等级

    // 构造方法
    public ResidentBean() {
        this.passwd = "123456"; // 默认密码为123456
        this.permission_level = 0; // 默认权限为0
    }

    // getter和setter
    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getResident_name() {
        return resident_name;
    }

    public void setResident_name(String resident_name) {
        this.resident_name = resident_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getPermission_level() {
        return permission_level;
    }

    public void setPermission_level(int permission_level) {
        this.permission_level = permission_level;
    }
}
