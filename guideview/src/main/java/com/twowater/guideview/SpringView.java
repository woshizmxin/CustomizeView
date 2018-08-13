package com.twowater.guideview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class SpringView extends View {

    private Paint paint;
    private Path path;

    private Point headPoint;
    private Point footPoint;

    public SpringView(Context context) {
        this(context, null);
    }

    public SpringView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        headPoint = new Point();
        footPoint = new Point();

        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
    }

    private void makePath() {

        double angle = Math.atan(
                (footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()));
        float headOffsetX = (float) (headPoint.getRadius() * Math.sin(angle));
        float headOffsetY = (float) (headPoint.getRadius() * Math.cos(angle));

        float footOffsetX = (float) (footPoint.getRadius() * Math.sin(angle));
        float footOffsetY = (float) (footPoint.getRadius() * Math.cos(angle));

        float x1 = headPoint.getX() - headOffsetX;
        float y1 = headPoint.getY() + headOffsetY;

        float x2 = headPoint.getX() + headOffsetX;
        float y2 = headPoint.getY() - headOffsetY;

        float x3 = footPoint.getX() - footOffsetX;
        float y3 = footPoint.getY() + footOffsetY;

        float x4 = footPoint.getX() + footOffsetX;
        float y4 = footPoint.getY() - footOffsetY;

        float anchorX = (footPoint.getX() + headPoint.getX()) / 2;
        float anchorY = (footPoint.getY() + headPoint.getY()) / 2;

        path.reset();
        path.moveTo(x1, y1);
        path.quadTo(anchorX, anchorY, x3, y3);
        path.lineTo(x4, y4);
        path.quadTo(anchorX, anchorY, x2, y2);
        path.lineTo(x1, y1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        makePath();
        canvas.drawPath(path, paint);
        canvas.drawCircle(headPoint.getX(), headPoint.getY(), headPoint.getRadius(), paint);
        canvas.drawCircle(footPoint.getX(), footPoint.getY(), footPoint.getRadius(), paint);
        super.onDraw(canvas);
    }

    public Point getHeadPoint() {
        return headPoint;
    }

    public Point getFootPoint() {
        return footPoint;
    }

    public void setIndicatorColor(int color) {
        paint.setColor(color);
    }

    public int getIndicatorColor() {
        return paint.getColor();
    }
}
