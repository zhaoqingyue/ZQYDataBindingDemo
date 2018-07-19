package com.mtq.zqydatabindingdemo;

import android.view.View;
import android.widget.Toast;

/**
 * Created by zhaoqy on 2018/7/18.
 */

public class Presenter {

    // 参数View必须有，必须是public，参数View不能改成对应的控件，只能是View，否则编译不通过
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "点击事件", Toast.LENGTH_LONG).show();
    }
}
