package work.boku.comservice.classes;

/**
 * ResidentBean：居民类
 * community id：社区编号，整型，作用户名，在数据库中默认由系统自动分配
 * passwd：密码，字符型，输入时隐藏，默认为123456
 * identity number：身份证号，字符串型
 * resident name：居民姓名，字符串型
 * phone number：手机号码，字符串型
 * permission level：用户权限等级
 * 用户权限详解：
 * 0：普通用户
 * 1：用户管理员
 * 2：系统管理员
 * <p>
 * 普通用户可以查看自有信息、修改部分自有信息、查看社区通知
 * 用户管理员除了拥有普通用户的所有权限外，可以查看他人部分自有信息（除密码）、添加新用户、全修改社区通知
 * 系统管理员除了拥有用户管理员的所有权限外，可以修改他人部分自有信息（除密码）、删除用户
 */

public class ResidentBean {

    // private static String TAG = "ResidentBean";

    private int community_id;// 社区编号

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

    // 构造方法2，生成任意权限的新用户
    public ResidentBean(int num) {
        this.passwd = "123456";
        this.permission_level = num;
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
