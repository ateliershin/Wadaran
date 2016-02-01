package com.wadaran.wadaran;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.Window;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(), 8000);
    }

    class splashHandler implements Runnable {
        public void run() {
            Intent inte = new Intent(getApplication(), SituationActivity.class);
            startActivity(inte);
            SplashActivity.this.finish();
        }
    }
}