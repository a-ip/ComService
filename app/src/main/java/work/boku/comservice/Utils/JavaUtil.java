package work.boku.comservice.Utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import work.boku.comservice.classes.ResidentBean;

public class JavaUtil {

    // 判断字符串是否为纯数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // 登陆阶段：判断登录信息输入是否正确
    public static int isValidLogin(String un, String pw) {
        if (TextUtils.isEmpty(un)) {
            return 1; // 用户名为空
        }
        if (TextUtils.isEmpty(pw)) {
            return 2; // 密码为空
        }
        if (!isNumeric(un)) {
            return 3; // 用户名输入错误（不是纯数字）
        }
        return 0;
    }

    // 用户输入阶段：判断是否有重复的用户，以身份证号来判别

    /**
     * 1：身份证号相同
     * 2：社区编号相同
     */
    public static int isRepetitiveInfo(ResidentBean rb, ArrayList<ResidentBean> rbList) {
        for (ResidentBean bean : rbList
        ) {
            if (rb.getIdentity_number().equals(bean.getIdentity_number())) {
                return 1;
            } else if (rb.getCommunity_id() == bean.getCommunity_id()) {
                return 2;
            }
        }
        return 0;
    }

    // 用户输入阶段：判断居民输入是否有空值
    public static int isValidInsert(ResidentBean rb) {
        if (TextUtils.isEmpty(rb.getIdentity_number())) {
            return 1;
        }
        if (TextUtils.isEmpty(rb.getResident_name())) {
            return 2;
        }
        if (TextUtils.isEmpty(rb.getPhone_number())) {
            return 3;
        }
        return 0;
    }

    // 用户修改阶段：判断居民输入是否有空值
    public static int isValidUpdate(ResidentBean rb) {
        if (TextUtils.isEmpty(String.valueOf(rb.getCommunity_id()))) {
            return 1;
        }
        if (TextUtils.isEmpty(rb.getPasswd())) {
            return 2;
        }
        if (TextUtils.isEmpty(rb.getIdentity_number())) {
            return 3;
        }
        if (TextUtils.isEmpty(rb.getResident_name())) {
            return 4;
        }
        if (TextUtils.isEmpty(rb.getPhone_number())) {
            return 5;
        }
        return 0;
    }

    // 获取当前时间并转为String类型
    public static String getNow() {
        String timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(System.currentTimeMillis());
        return timeNow;
    }
}
