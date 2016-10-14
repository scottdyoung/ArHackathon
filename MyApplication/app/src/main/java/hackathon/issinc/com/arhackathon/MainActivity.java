package hackathon.issinc.com.arhackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.vuforia.Vuforia;

import hackathon.issinc.com.arhackathon.activities.CameraActivity;
import hackathon.issinc.com.arhackathon.activities.GameActivity;

public final class MainActivity extends AppCompatActivity {
    private InitVuforiaTask initVuforiaTask;
    private Activity cameraActivity;
    private MainActivity self;
    final private Object mutex = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.cameraActivity = new CameraActivity();
        this.self = this;
        try {
            this.initVuforiaTask = new InitVuforiaTask();
            this.initVuforiaTask.execute();
        } catch (final Exception e) {
            String logMessage = "Initializing Vuforia SDK failed";
            Log.e(this.getLocalClassName(), logMessage);
        }
    }

    /**
     * callback for when the user clicks the start button
     * @param view the initial view when the app starts
     */
    public void startGame(final View view) {

        final Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private class InitVuforiaTask extends AsyncTask<Void, Integer, Boolean> {
        // Initialize with invalid value:
        private int progressValue = -1;

        protected Boolean doInBackground(Void... params) {
            // Prevent the onDestroy() method to overlap with initialization:
            synchronized (mutex) {
                Vuforia.setInitParameters(self, Vuforia.GL_20, "AQOG8UP/////AAAAGYbFD+JNXkDLpgIJ3DgnYqRrUgnooI/972OQKoO6fmjLGKLrA3NgqjlDUiJvqkIaGd3UnHe60rzo5kzO0nOBqjKd3u7JozUYf+xhwv5P+JUmlMMObJPR93hURugw71mFFvXMiiI6g7WUtD8VzeVUFspQQrSBx5unXX6u9wBiPS1M5RkJak9qrRZ58/LGI5K+TcHXpUQXNTC/PP/q7b2XKx15g2Tkzbx7IN/5h/9J61vwdw8rtPRYzr7OBdm5bEvn3Ug7xxVdfMNrOVEMvN6F3AJB45Ems5BxhXdfR7JUTb8P4pgsAwzA7JzxyacvAt7bfL140Ds7s/iJymo8HoDoKqYGuI3Vc0hHGlC06bD3+Gun");
                do {
                    progressValue = Vuforia.init();
                } while (!isCancelled() && progressValue >= 0
                        && progressValue < 100);

                return (progressValue > 0);
            }
        }


        protected void onPostExecute(Boolean result) {
            Log.i(this.getClass().getName(), "POST EXECUTE: " + result);
        }
    }
}
