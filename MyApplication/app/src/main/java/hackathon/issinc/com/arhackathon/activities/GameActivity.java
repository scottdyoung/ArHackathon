package hackathon.issinc.com.arhackathon.activities;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vuforia.CameraCalibration;
import com.vuforia.CameraDevice;
import com.vuforia.Renderer;
import com.vuforia.Tool;
import com.vuforia.Vec2I;
import com.vuforia.VideoBackgroundConfig;
import com.vuforia.VideoMode;
import com.vuforia.Vuforia;

import hackathon.issinc.com.arhackathon.R;

public final class GameActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("test");

        ViewGroup layout = (ViewGroup) findViewById(R.id.test_message_container);
        layout.addView(textView);

        this.startAR(CameraDevice.CAMERA_DIRECTION.CAMERA_DIRECTION_DEFAULT);
    }



    public void startAR(int camera) {
        addContentView(null, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        LayoutInflater inflater = LayoutInflater.from(this);
        final ViewGroup mUILayout = (RelativeLayout) inflater.inflate(R.layout.camera_overlay,
                null, false);

        mUILayout.setVisibility(View.VISIBLE);
        mUILayout.setBackgroundColor(Color.BLACK);
        // Sets the UILayout to be drawn in front of the camera
        mUILayout.bringToFront();

        // Hides the Loading Dialog
//        loadingDialogHandler
//                .sendEmptyMessage(LoadingDialogHandler.HIDE_LOADING_DIALOG);

        // Sets the layout background to transparent
        mUILayout.setBackgroundColor(Color.TRANSPARENT);

        addContentView(mUILayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        // Configure the rendering of the video background
        configureVideoBackground();

        setProjectionMatrix();
    }

    private void configureVideoBackground() {
        CameraDevice cameraDevice = CameraDevice.getInstance();
        VideoMode vm = cameraDevice.getVideoMode(CameraDevice.MODE.MODE_DEFAULT);

        VideoBackgroundConfig config = new VideoBackgroundConfig();
        config.setEnabled(true);
        config.setPosition(new Vec2I(0, 0));

        Point size = new Point();
        this.getWindowManager().getDefaultDisplay().getRealSize(size);
        int xSize = size.x;
        int ySize = (int) (vm.getHeight() * (size.y / (float) vm
                .getWidth()));;

        if (ySize < size.y)
        {
            xSize = (int) (size.y * (vm.getWidth() / (float) vm
                    .getHeight()));
            ySize = size.y;
        }

        config.setSize(new Vec2I(xSize, ySize));

        // The Vuforia VideoBackgroundConfig takes the position relative to the
        // centre of the screen, where as the OpenGL glViewport call takes the
        // position relative to the lower left corner
//        mViewport = new int[4];
//        mViewport[0] = ((size.x - xSize) / 2) + config.getPosition().getData()[0];
//        mViewport[1] = ((size.y - ySize) / 2) + config.getPosition().getData()[1];
//        mViewport[2] = xSize;
//        mViewport[3] = ySize;

        Renderer.getInstance().setVideoBackgroundConfig(config);
    }

    // Method for setting / updating the projection matrix for AR content
    // rendering
    public void setProjectionMatrix()
    {
        CameraCalibration camCal = CameraDevice.getInstance()
                .getCameraCalibration();
//        mProjectionMatrix = Tool.getProjectionGL(camCal, 1.0f, 5000.0f);
    }

}
