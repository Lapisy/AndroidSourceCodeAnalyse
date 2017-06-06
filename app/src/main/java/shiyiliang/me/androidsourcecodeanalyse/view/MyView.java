package shiyiliang.me.androidsourcecodeanalyse.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;


/**
 * Created by Administrator on 2017-06-06.
 */

public class MyView extends View {
    private Context mContext;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getPaddingBottom() + getPaddingTop();
        int width = getPaddingRight() + getPaddingLeft();
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int exceptionValue = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, 20, mContext.getResources().getDisplayMetrics());
        System.out.println(height + "-->" + exceptionValue );

        canvas.drawColor(Color.parseColor("#636344"));
    }
}
