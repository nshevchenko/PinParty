# PinParty

This library makes your google map exciting and fun.

It animates your map pins with bounce animation up or down.

<img src="https://github.com/nshevchenko/PinParty/blob/master/pinparty_lib.gif" width="300">

### Include the library

Copy inside your build.gradle the dependendcy line 

```
dependencies {
           ...
           compile 'com.github.nshevchenko:pinparty:1.0'
           ...
}
```

### Usage

Copy the following code, import the missing pieces and pass the required parameters. 

```
MarkerParty.init(SCALE_BOUNCE_DOWN)
           .withMarker(mMarker)
           .withBitmap(getBitmapFromMarker(mPreviousMarker))
           .withDuration(200)
           .withScale(0.75f)
           .start();
```

- **init(Type)** - initialize with a value from the Type enumeration
- **withMarker(Marker)** - Marker object to animate 
- **withBitmap(Bitmap)** - the image of the marker to be animated during the animation.  
- **withDuration(int)** - the duration time in milliseconds
- **withScale(float)** - the final size of the pin to animate to. Provide the final size relative to 1.0 which is 100%. Then 0.8F is 80% or 20% bigger will be 1.2F. 
- **start()** - begin the show. 

### Authors
Mykola Shevchenko
