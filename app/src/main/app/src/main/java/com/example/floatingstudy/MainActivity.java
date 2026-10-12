
package com.example.floatingstudy;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.Settings;
import android.net.Uri;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 48, 32, 32);
        layout.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("Floating Study");
        title.setTextSize(26);
        layout.addView(title);

        Button start = new Button(this);
        start.setText("Открыть плавающее окно");
        layout.addView(start);

        start.setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(this)) {
                Intent i = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())
                );
                startActivity(i);
            } else {
                startService(new Intent(
                    this, FloatingService.class
                ));
            }
        });

        setContentView(layout);
    }
}
