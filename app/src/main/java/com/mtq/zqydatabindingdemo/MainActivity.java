package com.mtq.zqydatabindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mtq.zqydatabindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // 注意：该变量必须是public static
    public static String text = "按钮";

    ActivityMainBinding binding;
    User user;

    User2 user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        user = new User("zhao", "qingyue", true);
        binding.setUser(user);
        delay();

        binding.setImageUrl("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");

        binding.setPresenter(new Presenter());


        user2 = new User2();
        binding.setUser2(user2);
        user2.firstName.set("Mr");
        user2.lastName.set("Bean");
        user2.age.set(20);
        user2.isStudent.set(false);

    }

    /**
     * 两秒后改变firstName
     */
    private void delay() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                user.setFirstName("Zhou");
                binding.setUser(user);
            }
        }, 2000);
    }

}
