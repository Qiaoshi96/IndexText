package com.example.mylibrary;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author qiaoshi
 * @name IndexText
 * @class name：com.example.mylibrary
 * @time 2019/10/12 14:40
 */
public class mySlantedTextView extends View {
    public static final int TAG_LEFT = 0x00000000;
    public static final int TAG_Right = 0x00000001;
    public static final int TAG_LEFT_BOTTOM = 0x00000002;
    public static final int TAG_RIGHT_BOTTOM = 0x00000003;
    public static final int TAG_LEFT_BAR = 0x00000004;
    public static final int TAG_RIGHT_BAR = 0x00000005;
    public static final int TAG_LEFT_BOTTOM_BAR = 0x00000006;
    public static final int TAG_RIGHT_BOTTOM_BAR = 0x00000007;
    public static final int TAG_LEFT_ANGLE = 0x00000008;


    private int myBackgroundColor = Color.WHITE;//默认白色
    private int TAG_Mode = TAG_LEFT;
    private Paint mPaint;//绘制背景色

    private TextPaint mTextPaint;
    private String myText = "";
    private float myTextSize = 14;
    private float mySlantedHeight = 40;//倾斜高度
    private int myTextColor = Color.BLACK;
    private int ROTATE_ANGLE = 45;//默认倾斜角度
    private float PADDING_TOP = 0;//全角到底部距离


    public mySlantedTextView(Context context) {
        super(context);
    }

    public mySlantedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public mySlantedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public mySlantedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    /**
     * 初始化相关方法
     */
    private void init(AttributeSet attrs) {
        //加载默认属性
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.mySlantedTextView);
        myBackgroundColor = array.getColor(R.styleable.mySlantedTextView_myBackgroundColor, myBackgroundColor);
        myTextSize = array.getDimension(R.styleable.mySlantedTextView_myTextSize, myTextSize);
        myTextColor = array.getColor(R.styleable.mySlantedTextView_myTextColor, myTextColor);
        PADDING_TOP = array.getDimension(R.styleable.mySlantedTextView_myPaddingTop, PADDING_TOP);
        mySlantedHeight = array.getDimension(R.styleable.mySlantedTextView_mySlantedHeight, mySlantedHeight);

        if (array.hasValue(R.styleable.mySlantedTextView_tagModel))//判断用户是否设置该方法
            TAG_Mode = array.getInt(R.styleable.mySlantedTextView_tagModel, TAG_LEFT);
        if (array.hasValue(R.styleable.mySlantedTextView_myText))
            myText = array.getString(R.styleable.mySlantedTextView_myText);

        array.recycle();//设置完回收
        //初始化背景颜色画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));//设置绘制模式这里表示显示在上面
        mPaint.setAntiAlias(true);
        mPaint.setColor(myBackgroundColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(myTextColor);
        mTextPaint.setTextSize(myTextSize);
        mTextPaint.setAntiAlias(true);


    }

    /**
     * 绘制字体颜色和背景颜色
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackgroundColor(canvas);
        drawText(canvas);
    }

    //绘制背景颜色
    private void drawBackgroundColor(Canvas canvas) {
        //通过path绘制路径
        Path path = new Path();
        int mWidth = getWidth();
        int mHeight = getHeight();
        if (mWidth != mHeight) throw new IllegalStateException("设置的布局高和宽度必须一致");
        switch (TAG_Mode) {//设置具体模式
            case TAG_LEFT:
                path.lineTo(0, mHeight);
                path.lineTo(mWidth, 0);
                break;
            case TAG_Right:
                path.lineTo(mWidth, 0);
                path.lineTo(mWidth, mHeight);
                break;
            case TAG_LEFT_BOTTOM:
                path.lineTo(mWidth, mHeight);
                path.lineTo(0, mHeight);
                break;
            case TAG_RIGHT_BOTTOM:
                path.moveTo(0, mHeight);//移动坐标原点位置
                path.lineTo(mWidth, mHeight);
                path.lineTo(mWidth, 0);
                break;
            case TAG_LEFT_BAR:
                path.moveTo(mWidth, 0);
                path.lineTo(0, mHeight);
                path.lineTo(0, mHeight - mySlantedHeight);
                path.lineTo(mWidth - mySlantedHeight, 0);
                break;
            case TAG_RIGHT_BAR:
                path.lineTo(mWidth, mHeight);
                path.lineTo(mWidth, mHeight - mySlantedHeight);
                path.lineTo(mySlantedHeight, 0);
                break;
            case TAG_LEFT_BOTTOM_BAR:
                path.lineTo(mWidth, mHeight);
                path.lineTo(mWidth - mySlantedHeight, mHeight);
                path.lineTo(0, mySlantedHeight);
                break;
            case TAG_RIGHT_BOTTOM_BAR:
                path.moveTo(0, mHeight);
                path.lineTo(mySlantedHeight, mHeight);
                path.lineTo(mWidth, mySlantedHeight);
                path.lineTo(mWidth, 0);
                break;
            case TAG_LEFT_ANGLE:
                //绘制两个角度
             /*   path.reset();
                path.moveTo(mWidth-pointWidth,0);
                path.lineTo(mWidth,pointHeight);
                path.lineTo(mWidth-pointWidth-26,pointHeight);*/

                // path.reset();
               /* path.moveTo(0,mHeight-pointWidth);
                path.lineTo(pointHeight,mHeight);
                path.lineTo(pointHeight,mHeight-pointWidth-26);*/
                /*
                path.moveTo(mWidth - 100 - pointWidth, 0);
                path.lineTo(mWidth - pointWidth, 0);
                path.lineTo(0, mHeight - pointWidth);
                path.lineTo(0, mHeight - 100 - pointWidth);*/
                break;
        }
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.save();
    }

    //绘制字体（从中间开始设置字体)
    private void drawText(Canvas canvas) {
        int w = (int) (canvas.getWidth() - mySlantedHeight / 2);
        int h = (int) (canvas.getHeight() - mySlantedHeight / 2);
        float[] xy = calculateXY(w, h);
        float toX = xy[0];
        float toY = xy[1];
        float centerX = xy[2];
        float centerY = xy[3];
        float angle = xy[4];
        canvas.rotate(angle, centerX, centerY);
        canvas.drawText(myText, toX, toY + PADDING_TOP, mTextPaint);
    }

    private float[] calculateXY(int w, int h) {
        float[] xy = new float[5];
        Rect rect;
        RectF rectF;
        int offset = (int) (mySlantedHeight / 2);
        //根据模式进行绘制
        switch (TAG_Mode) {
            case TAG_LEFT:
            case TAG_LEFT_BAR:


                rect = new Rect(0, 0, w, h);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(myText, 0, myText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();//Ascent： 字符顶部到baseLine的距离  Descent： 字符底部到baseLine的距离
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2;
                xy[3] = h / 2;
                xy[4] = -ROTATE_ANGLE;
                break;
            case TAG_Right:
            case TAG_RIGHT_BAR:

                rect = new Rect(offset, 0, w + offset, h);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(myText, 0, myText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2 + offset;
                xy[3] = h / 2;
                xy[4] = ROTATE_ANGLE;
                break;
            case TAG_LEFT_BOTTOM:
            case TAG_LEFT_BOTTOM_BAR:

                rect = new Rect(0, offset, w, h + offset);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(myText, 0, myText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2;
                xy[3] = h / 2 + offset;
                xy[4] = ROTATE_ANGLE;
                break;

            case TAG_RIGHT_BOTTOM:
            case TAG_RIGHT_BOTTOM_BAR:
                rect = new Rect(offset, offset, w + offset, h + offset);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(myText, 0, myText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2 + offset;
                xy[3] = h / 2 + offset;
                xy[4] = -ROTATE_ANGLE;
                break;
        }
        return xy;
    }

    /**
     * 暴露设置的方法
     */
    public mySlantedTextView setText(String text) {
        myText = text;
        postInvalidate();
        return this;
    }

    public mySlantedTextView setText(int res) {
        String str = getResources().getString(res);
        if (str != null) {
            setText(str);
        }
        return this;
    }

    public String getText() {
        return myText;
    }

    // font color
    public mySlantedTextView setTextColor(int color) {
        myTextColor = color;
        mTextPaint.setColor(myTextColor);
        postInvalidate();
        return this;
    }

    // font size

    public mySlantedTextView setTextSize(int size) {
        this.myTextSize = size;
        mTextPaint.setTextSize(myTextSize);
        postInvalidate();
        return this;

    }

    //背景颜色
    public mySlantedTextView setBackground(int color) {
        myBackgroundColor = color;
        mPaint.setColor(myBackgroundColor);
        postInvalidate();
        return this;

    }


    public mySlantedTextView setMode(int mode) {
        if (TAG_Mode > TAG_RIGHT_BOTTOM_BAR || TAG_Mode < 0)
            throw new IllegalArgumentException(mode + "不存该模式");
        this.TAG_Mode = mode;
        postInvalidate();
        return this;
    }

    public int getMode() {
        return TAG_Mode;
    }

    public mySlantedTextView setSlantedHeight(int length) {
        mySlantedHeight = length;
        postInvalidate();
        return this;
    }

    public mySlantedTextView setPadding(int padding) {
        PADDING_TOP = padding;
        postInvalidate();
        return this;
    }


}
