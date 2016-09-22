package com.htt.clockview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Administrator on 2016/9/22.
 * 时钟控件
 */
public class ClockView extends View {
    /**时钟背景颜色*/
    @ColorInt
    protected static final int CLOCK_BACKGROUND_COLOR= 0xFFF0F0F0;
    /**时钟圆环颜色*/
    @ColorInt
    protected static final int CLOCK_RING_COLOR=0xFFF8F8F8;
    /**字体颜色*/
    @ColorInt
    protected static final int TEXT_COLOR = 0xFF141414;
    /**时钟和分钟的颜色*/
    protected static final int HOUR_MINUTE_COLOR = 0xFF5B5B5B;
    /**秒钟的颜色*/
    @ColorInt
    private static final int SECOND_COLOR = 0xFFB55050;
    /**时钟最小尺寸*/
    private static final int CLOCK_MIN_SIZE=200;
    /**时*/
    private int hour;
    /**分*/
    private int minute;
    /**秒*/
    private int second;
    /**绘制时钟的Paint*/
    private Paint hourPaint;
    /**绘制分钟的Paint*/
    private Paint minutePaint;
    /**绘制秒钟的Paint*/
    private Paint secondPaint;
    /**圆环的宽度*/
    private int clockRingWidth=10;
    /**时钟大小*/
    private int clockSize;
    /**绘制时钟的Paint*/
    private Paint clockPaint;
    /**绘制时钟圆环的Paint*/
    private Paint clockRingPaint;

    public ClockView(Context context) {
        super(context);
        initView();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    protected void initView(){
        clockPaint=new Paint();
        clockPaint.setColor(CLOCK_BACKGROUND_COLOR);
        clockPaint.setAntiAlias(true);

        clockRingPaint=new Paint();
        clockRingPaint.setColor(CLOCK_RING_COLOR);
        clockRingPaint.setStrokeWidth(dp2px(clockRingWidth));
        clockRingPaint.setStyle(Paint.Style.STROKE);
        clockRingPaint.setAntiAlias(true);
        //添加阴影 0x80000000
        clockRingPaint.setShadowLayer(4, 2, 2, 0x80000000);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        clockSize=dp2px(CLOCK_MIN_SIZE);
        if(clockSize>width){
            width=clockSize;
        }else{
            clockSize=width;
        }
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w!=oldw||h!=oldh){
            clockSize=w;
        }
        int minSize=dp2px(CLOCK_MIN_SIZE);
        if(clockSize<minSize){
            clockSize=minSize;
        }
    }

    /**
     * 将 dp 转换为 px
     *
     * @param dp 需转换数
     * @return 返回转换结果
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
