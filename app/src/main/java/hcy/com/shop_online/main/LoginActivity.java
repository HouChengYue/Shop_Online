package hcy.com.shop_online.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import hcy.com.shop_online.R;
import hcy.com.shop_online.Util.UtilLoder;

public class LoginActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        image = (ImageView) findViewById(R.id.iv_welcom);

        UtilLoder.ImageLoder(this,"http://192.168.1.103:8080/OkhttpDemoss/image/Welcom/Welcome.png",image);
    }
}
