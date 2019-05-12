package work.boku.comservice.Utils;

public class RegexUtil {
    /**
     * 手机号的正则表达式，可以根据实际情况修改
     */
    private static final String PHONE_REGEX = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    /**
     * 邮箱正则表达式，可以根据实际情况修改
     */
    private static final String EMAIL_REGEX = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";


    /**
     * 判断手机号是否符合规范
     *
     * @param value
     * @return
     */
    public static boolean isPhone(String value) {
        return value.matches(PHONE_REGEX);
    }

    /**
     * 判断邮箱是否符合规范
     *
     * @param value
     * @return
     */
    public static boolean isEmail(String value) {
        return value.matches(EMAIL_REGEX);
    }

}
