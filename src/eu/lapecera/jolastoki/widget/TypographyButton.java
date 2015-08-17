package eu.lapecera.jolastoki.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import eu.lapecera.jolastoki.R;

public class TypographyButton extends Button {

	public TypographyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	public TypographyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init (AttributeSet attrs) {
		if(!isInEditMode()){
			String ttfName;
			TypedArray a = getContext().getTheme().obtainStyledAttributes(
					attrs,
					R.styleable.Typography,
					0, 0);

			try {
				ttfName = a.getString(R.styleable.Typography_ttf_name);
			} finally {
				a.recycle();
			}
			if (ttfName != null) {
				Typeface font = Typeface.createFromAsset(getContext().getAssets(), ttfName);
				super.setTypeface(font);
			}
		}
	}
}
