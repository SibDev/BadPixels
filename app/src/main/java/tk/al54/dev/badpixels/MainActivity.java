package tk.al54.dev.badpixels;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    FrameLayout mainBG;
    TextView tvn, tvi, tvv;
    int i=0;
    int j=0;
    boolean start = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        final GestureDetector gestureDetector = new GestureDetector(new DetectGesture());
        mainBG = (FrameLayout) findViewById(R.id.mainBG);
//        mainBG.setBackgroundColor(getResources().getColor(R.color.black));
        mainBG.setOnClickListener(this);
        mainBG.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        tvn = (TextView) findViewById(R.id.appname);
        tvi = (TextView) findViewById(R.id.appinfo);
        tvv = (TextView) findViewById(R.id.appversion);
        tvv.setText(getString(R.string.version) + " " + versionName + " ("+getString(R.string.build)+" "+versionCode+")");
        j = 1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainBG:
//                mainBG.setBackgroundColor(getResources().getColor(R.color.navy));
                i++;
                changeColor();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    void changeColor() {
        if (i > 7) i = 0;
        if (i < 0) i = 7;
        if (j > 0) {
            tvn.setVisibility(View.GONE);
            tvi.setVisibility(View.GONE);
            tvv.setVisibility(View.GONE);
        }
//        System.out.println("I: " + i);
//        Toast.makeText(getApplicationContext(), "I: "+i, Toast.LENGTH_SHORT).show();
        switch (i) {
            case 0:
                mainBG.setBackgroundColor(getResources().getColor(R.color.black));
                break;
            case 1:
                mainBG.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case 2:
                mainBG.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case 3:
                mainBG.setBackgroundColor(getResources().getColor(R.color.blue));
                break;
            case 4:
                mainBG.setBackgroundColor(getResources().getColor(R.color.cyan));
                break;
            case 5:
                mainBG.setBackgroundColor(getResources().getColor(R.color.magenta));
                break;
            case 6:
                mainBG.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case 7:
                mainBG.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            default:
                break;
        }
    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 50;
    private class DetectGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                i++;
                changeColor();
//                Toast.makeText(getApplicationContext(), "Right to left", Toast.LENGTH_SHORT).show();
//                System.out.println("Right to left");
                return true;
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                i--;
                changeColor();
//                Toast.makeText(getApplicationContext(), "Left to right", Toast.LENGTH_SHORT).show();
//                System.out.println("Left to right");
                return true;
            }
            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                i++;
                changeColor();
//                Toast.makeText(getApplicationContext(), "Bottom to top", Toast.LENGTH_SHORT).show();
//                System.out.println("Bottom to top");
                return true;
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                i--;
                changeColor();
//                Toast.makeText(getApplicationContext(), "Top to bottom", Toast.LENGTH_SHORT).show();
//                System.out.println("Top to bottom");
                return true;
            }
            return false;
        }
    }

}
