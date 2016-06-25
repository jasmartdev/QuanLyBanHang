package bk.danang.quanlybanhang.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import bk.danang.quanlybanhang.R;

/**
 * Created by voqua on 6/25/2016.
 */
public class ImageUrlView extends ImageView {
    public ImageUrlView(Context context) {
        super(context);
    }

    public ImageUrlView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageUrlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUrl(final String link){
        new Thread(new Runnable() {
            public void run() {
                try{
                    URL url = new URL(link);
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    ((Activity)ImageUrlView.this.getContext()).runOnUiThread(new Runnable() {
                        public void run() {
                            setImageBitmap(bmp);
                        }
                    });

                }catch (Exception e) {
                    ((Activity)ImageUrlView.this.getContext()).runOnUiThread(new Runnable() {
                        public void run() {
                            setImageResource(R.drawable.nophoto);
                        }
                    });
                }
            }
        }).start();
    }
}
