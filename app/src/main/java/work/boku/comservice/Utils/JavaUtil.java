package work.boku.comservice.Utils;

import android.text.TextUtils;

import work.boku.comservice.classes.ResidentBean;

public class JavaUtil {
    // 判断居民输入阶段是否有空值
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

    // 判断字符串是否为纯数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // 判断登录输入阶段是否输入正确
    public static int isValidLogin(String un, String pw) {
        if (TextUtils.isEmpty(un)) {
            return 1; // 用户名为空
        }
        if (TextUtils.isEmpty(pw)) {
            return 2; // 密码为空
        }
        if (!TextUtils.isEmpty(un) && !isNumeric(un)) {
            return 3; // 用户名输入错误（不是纯数字）
        }
        return 0;
    }
}
