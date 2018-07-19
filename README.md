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

所有支持的操作符如下：
- 数学运算符 + - / * %
- 字符串拼接 +
- 一元运算符 + - ! ~
- 三元运算符 ? :
- 逻辑运算 && ||
- 二进制运算 & | ^
- 位运算符 >> >>> <<
- 比较运算符 == > < >= <=
- instanceof
- Grouping ()
- 文字 - character, String, numeric, null
- 类型转换 cast
- 方法调用 methods call
- 字段使用 field access
- 数组使用 [] Arrary access

**3.4 显示图片**

----BindingAdapter注解

- 创建显示图片的类
```
public class ImageUtil {

    /**
     * 使用Glide显示图片
     * @param imageView
     * @param url
     *
     * 用bind声明一个image自定义属性
     * @BindingAdapter({"bind:image"})
     *
     * @BindingAdapter({"bind:image"}) 改成 @BindingAdapter({"image"}) 不会有警告
     */
    @BindingAdapter({"image"})
    public static void imageLoader(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
```

- 布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_main"
    tools:context=".MainActivity" >

    <data>

        <variable
            name="imageUrl"
            type="String"/>
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="10dp">

        <ImageView
            android:layout_width="match_parent"
            android:layout_height="150dp"
            app:image = "@{imageUrl}"/>

    </LinearLayout>

</layout>
```

- 在Activity绑定数据
```
binding.setImageUrl("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");
```

**3.5 点击事件**

- 定义点击事件
```
public class Presenter {

    // 参数View必须有，必须是public，参数View不能改成对应的控件，只能是View，否则编译不通过
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "点击事件", Toast.LENGTH_LONG).show();
    }
}
```

- 布局文件中添加点击事件
```
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_main"
    tools:context=".MainActivity" >

    <data>

        <variable
            name="presenter"
            type="com.mtq.zqydatabindingdemo.Presenter"/>
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="10dp">

        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textAllCaps="false"
            android:onClick="@{presenter.onClick}"
            android:text="Button" />

    </LinearLayout>

</layout>
```

- 在Activity中调用
```
binding.setPresenter(new Presenter());
```

**3.6 调用Activity中的变量**

- 在ctivity中定义变量
```
// 注意：该变量必须是public static
public static String text = "按钮";
```

- 在布局中调用
```
<variable
    name="mainActivity"
    type="com.mtq.zqydatabindingdemo.MainActivity"/>
    
<Button
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textAllCaps="false"
    android:text="@{mainActivity.text}" />
```

**3.7 数据改变时更新UI**

```
user = new User("zhao", "qingyue", true);
binding.setUser(user);
delay();


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
```

**3.8 BaseObservable**

----官方提供更简单的更新UI方式

使User继承BaseObservable