package com.ithaha.expandabletextview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text1;
    private ImageButton expand;
    private Button main;
    private Button second;
    private Button thrid;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        text1 = (TextView) findViewById(R.id.text1);
        expand = (ImageButton) findViewById(R.id.expand);
        expand.setOnClickListener(this);

        // 限制一开始的高度
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) text1.getLayoutParams();
        layoutParams.height = getShortMeasureHeight();
        text1.setLayoutParams(layoutParams);

        main = (Button) findViewById(R.id.main);
        main.setOnClickListener(this);
        second = (Button) findViewById(R.id.second);
        second.setOnClickListener(this);
        thrid = (Button) findViewById(R.id.thrid);
        thrid.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.second:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.thrid:
                intent = new Intent(this, ThridActivity.class);
                startActivity(intent);
                break;
            case R.id.expand:
                int startHeight;
                int targetHeight;
                if(!flag){
                    flag=true;
                    startHeight=getShortMeasureHeight();
                    targetHeight=getLongMeasureHeight();
                }else{
                    flag=false;
                    startHeight=getLongMeasureHeight();
                    targetHeight=getShortMeasureHeight();
                }
                final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) text1.getLayoutParams();
                ValueAnimator animator=ValueAnimator.ofInt(startHeight,targetHeight);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value=(Integer) animation.getAnimatedValue();
                        layoutParams.height=value;
                        text1.setLayoutParams(layoutParams);
                    }
                });
                animator.addListener(new Animator.AnimatorListener() {  // 监听动画执行
                    //当动画开始执行的时候调用
                    @Override
                    public void onAnimationStart(Animator arg0) {
                        // TODO Auto-generated method stub

                    }
                    @Override
                    public void onAnimationRepeat(Animator arg0) {

                    }
                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        if(flag){
                            expand.setImageResource(R.mipmap.ic_expand_less_black_12dp);
                        }else{
                            expand.setImageResource(R.mipmap.ic_expand_more_black_12dp);
                        }
                    }
                    @Override
                    public void onAnimationCancel(Animator arg0) {

                    }
                });
                animator.setDuration(500);//设置动画持续时间
                animator.start();
                break;
        }
    }

    //onMeasure()  制定测量的规则
    // measure() 实际测量

    /**
     * 获取控件实际的高度
     */
    public int getLongMeasureHeight() {
        int width = text1.getMeasuredWidth();  //  由于宽度不会发生变化  宽度的值取出来
        text1.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;//  让高度包裹内容

        //    参数1  测量控件mode    参数2  大小
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);  //  mode+size
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);// 我的高度 最大是1000
        // 测量规则 宽度是一个精确的值width, 高度最大是1000,以实际为准
        text1.measure(widthMeasureSpec, heightMeasureSpec); // 通过该方法重新测量控件

        return text1.getMeasuredHeight();
    }

    /**
     * 获取3行的高度
     *
     * @return
     */
    public int getShortMeasureHeight() {
        // 复制一个新的TextView 用来测量,最好不要在之前的TextView测量 有可能影响其它代码执行
        TextView textView = new TextView(this);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);//设置字体大小14dp
        textView.setMaxLines(3);
        textView.setLines(3);// 强制有3行
        int width = text1.getMeasuredWidth(); // 开始宽度

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }
}
