### Android Data Binding（数据绑定）

**1. 环境要求**

- Android Studio版本1.3以上
- gradle版本1.5.0-alpha1以上
- 在Android SDK manager中下载Android Support repository
- 在对应的app Module的build.gradle中添加
```
android {

    ....
    
    dataBinding {
        enabled = true
    }
}
```

**2. Data Binding的使用**

- 创建对象
- 布局
- 绑定数据

**2.1 创建对象**

创建一个User类

```
public class User {

    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
```

**2.2 布局**

activity_main.xml布局
```
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    tools:context=".MainActivity" >

    <data>
        <variable
            name="user"
            type="com.mtq.zqydatabindingdemo.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:gravity="center">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.firstName}"
            android:textSize="20sp"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.lastName}"
            android:textSize="20sp"/>

    </LinearLayout>

</layout>
```

**注意：**
- 根布局为 layout 标签
- 其次是 data & 布局
- data：声明需要用到的user对象，type用于是定路径
- 布局属性参数中的表达式都要使用 "@{}"语法

**2.3 绑定数据**

MainActivity代码：
```
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("zhao", "qingyue");
        binding.setUser(user);
    }
}
```

**3. 更多使用**

**3.1 空指针**

----自动生成的 DataBinding 代码会检查null，避免出现NullPointerException。
例如：在表达式中@{user.firstName}，如果user == null， 则会为user.firstName设置默认值null，而不会导致程序崩溃（基本类型将赋予默认值如int为0，引用类型都会赋值null）

**3.2 导包**

----跟Java中的用法相似，布局文件中支持import的使用

```
<data>
    <variable
        name="user"
        type="com.mtq.zqydatabindingdemo.User" />
</data>

// 使用import
<data>
    <import type="com.mtq.zqydatabindingdemo.User"/>
    
    <variable
        name="user"
        type="User" />
</data>
```

当需要用到一些包时，在Java中可以自动导包；但是在布局文件中，需要使用import导入这些包。如：需要用到View时
```
<data>
    <import type="android.view.View"/>
</data>

...

<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:visibility="@{user.isStudent ? View.VISIBLE : View.GONE}"
    android:textSize="20sp" />
```

**3.3 表达式**

- 三元运算
```
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text='@{user.isStudent? "Student": "Other"}'
    android:textSize="20sp"/>
```

> 注意：需要用到双引号时，外层的双引号改成单引号

也可以这样用
```
<data>
    <import type="android.view.View"/>
</data>


<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@{user.isStudent ? View.VISIBLE : View.GONE}"
    android:textSize="20sp"/>
```

- ？？：null 合并运算符号
```
user.lastName ?? user.firstName

// 相当于
user.lastName != null ? user.lastName : user.firstName
```

