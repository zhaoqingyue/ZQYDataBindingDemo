package com.mtq.zqydatabindingdemo;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zhaoqy on 2018/7/18.
 */

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
