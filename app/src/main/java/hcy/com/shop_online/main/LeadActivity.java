package hcy.com.shop_online.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;
import java.util.Map;

import hcy.com.shop_online.Base.BaseActivity;
import hcy.com.shop_online.Base.BasePagerAdapter;
import hcy.com.shop_online.R;
import hcy.com.shop_online.Util.VolleyLoadPicture;

public class LeadActivity extends BaseActivity implements OnClickListener {
    private ImageView[] icons = new ImageView[3];
    private ImageView image;
    private TextView tv_skip;
    private ViewPager viewpager;
    private BasePagerAdapter leadPagerAdapter;
    private VolleyLoadPicture volleyLoadPicture;
    List<Map<String,Object>> datas;
    protected ImageLoader imageLoader;
//    DisplayImageOptions options;
//http://blog.csdn.net/u013200864/article/details/46831359 Viewpager加载网络图片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Volly图片加载
        volleyLoadPicture = new VolleyLoadPicture(this, image);//图片加载
        SharedPreferences preferences = getSharedPreferences("lead_config", Context.MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);
        if (!isFirstRun) {
            startActivity(MainActivity.class);
            finish();
        } else {
            setContentView(R.layout.activity_lead);
            initLeadIcon();//初始化引导界面小图标+skip(5个小图标+1个skip文字)
            initViewPager();//初始化viwpager
            initPagerData();// 初始化引导页面ViewPager
        }

    }

    private void initPagerData() {
        image = null;
        image = (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item, null);
        volleyLoadPicture.getmImageLoader().get("http://192.168.1.114:8080/OkhttpDemoss/category_diaper01.png", volleyLoadPicture.getOne_listener());
        leadPagerAdapter.addViewToAdapter(image);
        image = (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item, null);
        volleyLoadPicture.getmImageLoader().get("http://192.168.1.114:8080/OkhttpDemoss/category_diaper01.png", volleyLoadPicture.getOne_listener());
        leadPagerAdapter.addViewToAdapter(image);
        image = (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item, null);
        volleyLoadPicture.getmImageLoader().get("http://192.168.1.114:8080/OkhttpDemoss/category_diaper01.png", volleyLoadPicture.getOne_listener());
        leadPagerAdapter.addViewToAdapter(image);
        //刷新适配器
        leadPagerAdapter.notifyDataSetChanged();
    }

    private void initViewPager() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        leadPagerAdapter = new BasePagerAdapter(this);
        viewpager.setAdapter(leadPagerAdapter);
        viewpager.setOnPageChangeListener(pageChangListerner);
    }

    private ViewPager.OnPageChangeListener pageChangListerner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//        到达到最后一个pager时，显示skip文本
            tv_skip.setVisibility(View.INVISIBLE);
            if (position >= 2) {
                tv_skip.setVisibility(View.VISIBLE);
            }
//           更新下标图标
            for (int i = 0; i < icons.length; i++) {
                icons[i].setImageResource(R.drawable.adware_style_default);
            }
            icons[position].setImageResource(R.drawable.adware_style_selected);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initLeadIcon() {
        icons[0] = (ImageView) findViewById(R.id.icon_1);
        icons[1] = (ImageView) findViewById(R.id.icon_2);
        icons[2] = (ImageView) findViewById(R.id.icon_3);
        icons[0].setImageResource(R.drawable.adware_style_selected);
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        tv_skip.setVisibility(View.INVISIBLE);
        tv_skip.setOnClickListener(this);
    }

    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences("lead_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstRun", false);
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        // 编辑保存配置信息(不再是第一次运行此引导页面)
        savePreferences();
        startActivity(MainActivity.class);
        finish();
    }
}
