package com.example.usuario.creaciondecomponentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by usuario on 11/01/18.
 */

public class Circulo extends View {

    private float radioCirculo,radioArco;
    private int colorCirculo;
    private Paint mCirculoPaint,mArcPaint,mTexto;
    private int centroX,centroY;
    private int porcentaje;
    private int colorArco;
    private RectF oval,ovalprueba;


    public Circulo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Circulo,0,0
        );

        try {
            radioCirculo = a.getDimension(R.styleable.Circulo_radioCirculo,150);
            colorCirculo = a.getColor(R.styleable.Circulo_colorCirculo,getResources().getColor(R.color.circulo));
            porcentaje = a.getInteger(R.styleable.Circulo_porcentaje,0);
            colorArco = a.getColor(R.styleable.Circulo_colorArco,getResources().getColor(R.color.arco));
        }finally {
            a.recycle();
        }

        init();


    }

    private void init() {

        mCirculoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirculoPaint.setStyle(Paint.Style.FILL);
        mCirculoPaint.setColor(getColorCirculo());


        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setColor(getColorArco());

        mTexto = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTexto.setColor(Color.WHITE);
        mTexto.setTextSize(16);

        oval = new RectF();
        ovalprueba = new RectF();
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

       radioCirculo = (Math.min(usableWidth, usableHeight) / 2);


       centroX = pl + (usableWidth / 2);
       centroY = pt + (usableHeight / 2);



       oval.set(pl+(centroX-radioCirculo),pt+(centroY-radioCirculo),pr+(centroX+radioCirculo),pb+(centroY+radioCirculo));

       radioCirculo= (float) (radioCirculo*0.9);

       mTexto.setTextSize(radioCirculo/2);


       //ovalprueba.set(pl+0F,pt+0F,usableWidth-pr,usableWidth-pb);


    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        int xPos = (int) (canvas.getWidth() / 2.5);
        int yPos = (int) ((canvas.getHeight() / 2) - ((mTexto.descent() + mTexto.ascent()) / 2)) ;

        canvas.drawArc(oval,270,360,true,mArcPaint);
        //canvas.drawArc(ovalprueba,270,360,true,mCirculoPaint);

        canvas.drawCircle(centroX,centroY,radioCirculo,mCirculoPaint);

        canvas.drawText(getPorcentaje()+"%",xPos,yPos,mTexto);
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

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
        invalidate();
        requestLayout();
    }

    public int getColorArco() {
        return colorArco;
    }

    public void setColorArco(int colorArco) {
        this.colorArco = colorArco;
        invalidate();
        requestLayout();
    }
}
