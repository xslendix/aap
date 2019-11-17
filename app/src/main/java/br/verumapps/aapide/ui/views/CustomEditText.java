package br.verumapps.aapide.ui.views;

import br.verumapps.aapide.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;
import br.verumapps.aapide.R;
import android.graphics.Typeface;
import android.content.ContentUris;
import androidx.core.content.res.ResourcesCompat;
import android.view.ViewStructure;

public class CustomEditText extends EditText {
    private Rect rect;
    private Paint paint;
    private Paint paint2;

    private Context ctx;

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx=context;
        rect = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.lineEditColor));
        paint.setTextSize(60);

        paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(getResources().getColor(R.color.lineEditBgColor));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int nr = 0;
        int n = getLineCount();
        while (n!=0) {
            nr++;
            n=n/10;
        }
        int baseline = getBaseline();

        canvas.drawRect(rect.left,0,40*nr+4, 346744, paint2);

        for (int i = 0; i < getLineCount(); i++) {
            canvas.drawText("" + (i+1), rect.left+2, baseline, paint);
            baseline += getLineHeight();
        }

        this.setPadding(40*nr+4,0,0,0);


        super.onDraw(canvas);
    }
}
