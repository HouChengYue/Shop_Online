package hcy.com.shop_online.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hcy.com.shop_online.Base.BaseActivity;
import hcy.com.shop_online.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.iv_welco);
        textView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view==textView){
            startActivity(LoginActivity.class);
        }
    }
}
