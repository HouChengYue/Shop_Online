package hcy.com.shop_online.main;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import hcy.com.shop_online.Base.BaseActivity;
import hcy.com.shop_online.R;
import hcy.com.shop_online.Util.UtilLoder;

public class LoginActivity extends BaseActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        image = (ImageView) findViewById(R.id.iv_welcom);
        UtilLoder.ImageLoder(this,
                "http://192.168.1.103:8080/OkhttpDemoss/image/Welcom/Welcome.png",image);
//        加载网络login图片
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        animation.setFillEnabled(true);
        image.setAnimation(animation);
        animation.setAnimationListener(animationlistener);
        animation.start();
    }
    private Animation.AnimationListener animationlistener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(MainActivity.class);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

}
