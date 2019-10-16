# IndexText
斜角标示

| API名称 | 介绍    |
|:--------:| -------------:|
| myBackgroundColor | 斜角背景颜色 |
|mySlantedHeight |斜角高度 |
| myText | 字体展示 |
| tagModel | 样式模式共八种 |
| myTextSize | 字体大小 |
| myTextColor | 字体颜色 |

[我的博客](https://blog.csdn.net/qiaoshi96_bk/article/details/102579957 "悬停显示")

#效果图
![我的博客](https://img-blog.csdnimg.cn/20191016104815930.jpg)

#使用方法
```bash
 testView.setText("打折中")
                .setMode(TAG_LEFT_BAR)
                .setBackground(Color.parseColor("#ff6677"))
                .setTextColor(Color.parseColor("#000000"))
                .setSlantedHeight(50)
                .setTextSize(29);
                或直接在布局中
                <com.example.mylibrary.mySlantedTextView
                    android:layout_width="80dp"
                    android:layout_height="80dp"
                    android:layout_alignParentTop="true"
                    android:layout_alignParentRight="true"
                    app:myBackgroundColor="#667fff"
                    app:mySlantedHeight="30dp"
                    app:myText="热卖中"
                    app:myTextColor="#ffffff"
                    app:myTextSize="16sp"
                    app:tagModel="right_bar" />
```
