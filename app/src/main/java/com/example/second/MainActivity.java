package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView coordinator;
    private float x;
    private float y;
    private String sDown;
    private String sMove;
    private String sUp;

    Random random = new Random();
    private final float xCat = random.nextFloat() * 400.0f + 100.0f;
    private final float yCat = random.nextFloat() * 400.0f + 100.0f;
    private final float deltaCat = 50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinator = findViewById(R.id.coordinator);

        coordinator.setOnTouchListener(listener);


    }
    private View.OnTouchListener listener = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent){
            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    sDown = "Нажатие: координата X = " + x + ", координата Y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE:
                    sMove = "Движение: координата X = " + x + ", координата Y = " + y;
                    if(x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (yCat + deltaCat) && y > (yCat - deltaCat)) {
                        // создать новый LinearLayout с фиксированными размерами
                        LinearLayout toastLayout = new LinearLayout(getApplicationContext());
                        toastLayout.setOrientation(LinearLayout.HORIZONTAL);
                        toastLayout.setLayoutParams(new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        ));
                        toastLayout.setPadding(16, 16, 16, 16);

// создать новый ImageView и добавить его в LinearLayout
                        ImageView cat = new ImageView(getApplicationContext());
                        cat.setImageResource(R.drawable.cat);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                100, // задать ширину ImageView в пикселях
                                100 // задать высоту ImageView в пикселях
                        );
                        toastLayout.addView(cat, params);

// создать новый TextView и добавить его в LinearLayout
                        TextView textView = new TextView(getApplicationContext());
                        textView.setText(R.string.successful_search);
                        toastLayout.addView(textView);

// создать новый Toast и установить LinearLayout как его представление
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.LEFT, (int) xCat, (int) yCat);
                        toast.setView(toastLayout);
                        toast.show();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = "";
                    sUp = "Отпускание: координата X = " + x + ", координата Y = " + y;
                    break;
            }

            coordinator.setText(sDown + "\n" + sMove + "\n" + sUp);

            return true;
        }
    };

}