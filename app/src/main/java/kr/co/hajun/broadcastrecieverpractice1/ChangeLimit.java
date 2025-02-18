package kr.co.hajun.broadcastrecieverpractice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amplitude.api.Amplitude;

import java.text.DecimalFormat;

public class ChangeLimit extends AppCompatActivity {
    ImageButton close,done;
    EditText changeLimit;
    TextView nowLimit;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Amplitude.getInstance().initialize(this, "45b00de2cf11098cf68104782fcb322a").enableForegroundTracking(getApplication());
        Amplitude.getInstance().logEvent("enter__change_limit");

        setContentView(R.layout.activity_change_limit);
        close = findViewById(R.id.close);
        done = findViewById(R.id.done);
        changeLimit = findViewById(R.id.change);
        nowLimit = findViewById(R.id.limit);

        sharedPreferences = getSharedPreferences("spendLimit", Context.MODE_PRIVATE);
        int now = sharedPreferences.getInt("limit",30000);
        DecimalFormat df = new DecimalFormat("###,###");
        nowLimit.setText(df.format(now));
        changeLimit.setText("");
        changeLimit.requestFocus();
    }

    public void doneBtn(View view){
        try{
            Amplitude.getInstance().initialize(this, "45b00de2cf11098cf68104782fcb322a").enableForegroundTracking(getApplication());
            Amplitude.getInstance().logEvent("click__change_limit");

            String str =changeLimit.getText().toString().trim();
            int rt = Integer.parseInt(str);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("limit",rt);
            editor.commit();

            Amplitude.getInstance().initialize(this, "45b00de2cf11098cf68104782fcb322a").enableForegroundTracking(getApplication());
            Amplitude.getInstance().logEvent("complete__change_limit");

            finish();
        }catch (NumberFormatException e){
            Toast.makeText(this,"숫자만 입력하세요",Toast.LENGTH_SHORT).show();
        }
    }

    public void closeBtn(View view){
        finish();
    }
}