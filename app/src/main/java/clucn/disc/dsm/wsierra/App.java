/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 *  The Main app.
 */
public final class App extends Application {

    /**
     * Initialize The Application.
     */
    @Override
    public void onCreate(){
        super.onCreate();
        Fresco.initialize(this);
    }

}
