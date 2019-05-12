package work.boku.comservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import work.boku.comservice.MainActivity;
import work.boku.comservice.R;
import work.boku.comservice.Utils.Constants;
import work.boku.comservice.Utils.RegexUtil;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button bt_login = findViewById(R.id.bt_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        bt_login.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
        }
    }

    public void login() {
        //获取用户输入的邮箱，密码，做校验
        String username = et_username.getText().toString();

        //判断是否输入了邮箱
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, R.string.username_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        //通过正则表达式判断邮箱格式是否正确
        if (!RegexUtil.isEmail(username)) {
            Toast.makeText(this, R.string.username_error, Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString();

        //判断是否输入了密码
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.password_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        //判断密码长度是否为6~15位
        if (password.length() < 6 || password.length() > 15) {
            Toast.makeText(this, R.string.password_error, Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO 在这里就调用调用服务端的登录接口
        //我们这里就简单实现，将密码和用户名都写到本地了
        if (Constants.USERNAME.equals(username) && Constants.PASSWORD.equals(password)) {
            //TODO 通常软件的做法是，这里登录完成后保存一个标致，下次就不用在登录了
            sp.setLogin(true);

            //登录成功，进入首页
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //关闭当前界面
            finish();
        } else {
            //登录失败，进行提示
            Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show();
        }
    }

}
