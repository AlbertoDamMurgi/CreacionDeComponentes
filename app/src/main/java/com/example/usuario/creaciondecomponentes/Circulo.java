package com.example.usuario.creaciondecomponentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by usuario on 11/01/18.
 */

public class Circulo extends View {

    private float radioCirculo;
    private int colorCirculo;
    private Paint mCirculoPaint, mShadowpaint;
    private int centroX,centroY;

    public Circulo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Circulo,0,0
        );

        try {
            radioCirculo = a.getDimension(R.styleable.Circulo_radioCirculo,150);
            colorCirculo = a.getColor(R.styleable.Circulo_colorCirculo,getResources().getColor(R.color.colorPrimary));
        }finally {
            a.recycle();
        }

        init();


    }

    private void init() {

        mCirculoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirculoPaint.setStyle(Paint.Style.FILL);
        mCirculoPaint.setColor(getColorCirculo());





    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Draw the shadow


        int pl = getPaddingLeft();
        int pr = getPaddingRight();
        int pt = getPaddingTop();
        int pb = getPaddingBottom();

        int usableWidth = w - (pl + pr);
        int usableHeight = h - (pt + pb);

       radioCirculo = Math.min(usableWidth, usableHeight) / 2;
       centroX = pl + (usableWidth / 2);
       centroY = pt + (usableHeight / 2);


    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);



        canvas.drawCircle(centroX,centroY,getRadioCirculo(),mCirculoPaint);


    }


    public float getRadioCirculo() {
        return radioCirculo;
    }

    public void setRadioCirculo(float radioCirculo) {
        this.radioCirculo = radioCirculo;
        invalidate();
        requestLayout();
    }

    public int getColorCirculo() {
        return colorCirculo;
    }

    public void setColorCirculo(int colorCirculo) {
        this.colorCirculo = colorCirculo;
        invalidate();
        requestLayout();
    }
}
