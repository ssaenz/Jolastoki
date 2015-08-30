package eu.lapecera.jolastoki.util;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import android.view.View.DragShadowBuilder;

public class DragRotatedShadowBuilder extends DragShadowBuilder {

	View view;
	private int width;
	private int height;
	public DragRotatedShadowBuilder(View view) {
		super(view);
		this.view = view;
		double rotationRad = Math.toRadians(view.getRotation());
		int w = (int) (view.getWidth() * view.getScaleX());
		int h = (int) (view.getHeight() * view.getScaleY());
		double s = Math.abs(Math.sin(rotationRad));
		double c = Math.abs(Math.cos(rotationRad));
		this.width = (int) (w * c + h * s);
		this.height = (int) (w * s + h * c);
	}

	@Override
	public void onDrawShadow(Canvas canvas) {
		canvas.scale(view.getScaleX(), view.getScaleY(), width / 2, height / 2);
		canvas.rotate(view.getRotation(), width / 2, height / 2);
		canvas.translate((width - view.getWidth()) / 2, (height - view.getHeight()) / 2);
		super.onDrawShadow(canvas);
	}

	@Override
	public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
		shadowSize.set(width, height);
		shadowTouchPoint.set(shadowSize.x / 2, shadowSize.y / 2);
	}
}
