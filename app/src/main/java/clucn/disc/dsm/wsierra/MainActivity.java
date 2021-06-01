package clucn.disc.dsm.wsierra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import clucn.disc.dsm.wsierra.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    /**
     * The Logger
     */

    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    /**
     *
     */
    private ActivityMainBinding binding;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the xml
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        // Set toolbar
        this.setSupportActionBar(this.binding.toolbar);
    }

    /**
     *
     */
    @Override
    protected void onStart(){
        super.onStart();
        log.debug("Onstart !!!");
    }


}