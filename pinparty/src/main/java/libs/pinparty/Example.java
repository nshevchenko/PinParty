package libs.pinparty;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.Marker;

import static libs.pinparty.Type.SCALE_BOUNCE_DOWN;
import static libs.pinparty.Type.SCALE_BOUNCE_UP;

/**
 * Created by nikolaishevchenko on 02/07/2017.
 */
public class Example {

    private static Marker mMarker;

    private static Bitmap mBitmap;

    public static void use() {
        PinParty.init(SCALE_BOUNCE_DOWN)
                .withMarker(mMarker)
                .withBitmap(mBitmap)
                .withDuration(200)
                .withScale(0.75f)
                .start();

        PinParty.init(SCALE_BOUNCE_UP)
                .withMarker(mMarker)
                .withBitmap(mBitmap)
                .withDuration(400)
                .withScale(1.3f)
                .start();
    }
}
