package com.mtq.zqydatabindingdemo;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by zhaoqy on 2018/7/19.
 */

public class User2 {

    public final ObservableField<String> firstName = new ObservableField<>();

    public final ObservableField<String> lastName = new ObservableField<>();

    public final ObservableBoolean isStudent = new ObservableBoolean();

    public final ObservableInt age = new ObservableInt();
}
