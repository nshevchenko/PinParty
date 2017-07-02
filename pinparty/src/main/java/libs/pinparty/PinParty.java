package libs.pinparty;

/**
 * Created by nikolaishevchenko on 02/07/2017.
 */

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by nikolaishevchenko on 02/07/2017.
 */
public class PinParty {

    private final Interpolator mBounceInterpolator = new BounceInterpolator();

    private float   mScale;

    private Handler mHandler;

    private long    mStart;

    private int     mDuration;

    private Bitmap  mBitmap;

    private Marker  mMarker;

    private Type    mType;

    public PinParty(Type type) {
        mType = type;
    }

    public static PinParty init(Type type) {
        return new PinParty(type);
    }

    public PinParty withScale(float scale) {
        mScale = scale;
        return this;
    }

    public PinParty withMarker(Marker marker) {
        mMarker = marker;
        return this;
    }

    public PinParty withDuration(int duration) {
        mDuration = duration;
        return this;
    }

    public PinParty withBitmap(Bitmap bitmapFromMarker) {
        mBitmap = bitmapFromMarker;
        return this;
    }

    public void start() {
        mStart = SystemClock.uptimeMillis();
        mHandler = new Handler();
        mHandler.post(getRunnable());
    }

    public Runnable getRunnable() {
        switch (mType) {
            case SCALE_BOUNCE_UP:
                return mRunnableScaleUp;
            case SCALE_BOUNCE_DOWN:
                return mRunnableScaleDown;
            default:
                return mRunnableScaleDown;
        }
    }

    private Runnable mRunnableScaleUp   = new Runnable() {
        long elapsed;

        @Override
        public void run() {
            elapsed = SystemClock.uptimeMillis() - mStart;
            float t = mBounceInterpolator.getInterpolation((float) elapsed / mDuration);
            t = 1 + t * (mScale - 1);
            Bitmap target = Bitmap.createScaledBitmap(mBitmap, (int) (mBitmap.getWidth() * t), (int) (mBitmap.getHeight() * t), false);
            mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(target));
            if (t <= mScale) {
                mHandler.postDelayed(this, 16);
            }
        }
    };

    private Runnable mRunnableScaleDown = new Runnable() {
        long elapsed;

        @Override
        public void run() {
            elapsed = SystemClock.uptimeMillis() - mStart;
            float t = mBounceInterpolator.getInterpolation((float) elapsed / mDuration);
            t = 1 - t * (1.0f - mScale);
            Bitmap target = Bitmap.createScaledBitmap(mBitmap, (int) (mBitmap.getWidth() * t), (int) (mBitmap.getHeight() * t), false);
            mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(target));
            if (t >= mScale) {
                mHandler.postDelayed(this, 16);
            }
        }
    };
}

