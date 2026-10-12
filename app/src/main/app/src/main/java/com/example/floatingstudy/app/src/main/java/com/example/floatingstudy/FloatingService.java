
package com.example.floatingstudy;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatingService extends Service {

    private WindowManager manager;
    private WindowManager.LayoutParams params;
    private LinearLayout panel;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        manager = (WindowManager)
            getSystemService(WINDOW_SERVICE);

        panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(20, 16, 20, 16);
        panel.setBackgroundColor(0xFF303030);

        TextView title = new TextView(this);
        title.setText("Floating Study");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(20);
        panel.addView(title);

        EditText input = new EditText(this);
        input.setHint("Введи учебный вопрос");
        input.setTextColor(0xFFFFFFFF);
        panel.addView(input);

        Button answer = new Button(this);
        answer.setText("Показать вопрос");
        panel.addView(answer);

        TextView result = new TextView(this);
        result.setTextColor(0xFFFFFFFF);
        panel.addView(result);

        answer.setOnClickListener(v ->
            result.setText(input.getText().toString())
        );

        Button close = new Button(this);
        close.setText("Закрыть");
        panel.addView(close);
        close.setOnClickListener(v -> stopSelf());

        params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 20;
        params.y = 150;

        title.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                return true;
            }
            return false;
        });

        manager.addView(panel, params);
    }

    @Override
    public void onDestroy() {
        if (panel != null) {
            manager.removeView(panel);
            panel = null;
        }
        super.o
          nDestroy();
    }
}
