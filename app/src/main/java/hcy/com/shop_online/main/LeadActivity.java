package hcy.com.shop_online.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import hcy.com.shop_online.Base.BasePagerAdapter;
import hcy.com.shop_online.Base.BaseActivity;
import hcy.com.shop_online.R;

public class LeadActivity extends BaseActivity implements OnClickListener {
private ImageView[] icons=new ImageView[3];
    private TextView tv_skip;
    private ViewPager viewpager;
    private BasePagerAdapter leadPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("lead_config", Context.MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);
        if (isFirstRun) {
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
        ImageView imageView=null;
        imageView= (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item,null);
        imageView.setImageResource(R.drawable.category_food01);
        leadPagerAdapter.addViewToAdapter(imageView);
        imageView= (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item,null);
        imageView.setImageResource(R.drawable.category_milk01);
        leadPagerAdapter.addViewToAdapter(imageView);
        imageView= (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item,null);
        imageView.setImageResource(R.drawable.category_wash01);
        leadPagerAdapter.addViewToAdapter(imageView);
        //刷新适配器
        leadPagerAdapter.notifyDataSetChanged();
    }

    private void initViewPager() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        leadPagerAdapter = new BasePagerAdapter(this);
        viewpager.setAdapter(leadPagerAdapter);
        viewpager.setOnPageChangeListener(pageChangListerner);
    }
   private ViewPager.OnPageChangeListener pageChangListerner=new ViewPager.OnPageChangeListener() {
       @Override
       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

       }

       @Override
       public void onPageSelected(int position) {
//        到达到最后一个pager时，显示skip文本
           tv_skip.setVisibility(View.INVISIBLE);
           if (position>=2){
               tv_skip.setVisibility(View.VISIBLE);
           }
//           更新下标图标
          for (int i=0;i<icons.length;i++){
              icons[i].setImageResource(R.drawable.adware_style_default);
          }
           icons[position].setImageResource(R.drawable.adware_style_selected);
       }

       @Override
       public void onPageScrollStateChanged(int state) {

       }
   };
    private void initLeadIcon() {
        icons[0]= (ImageView) findViewById(R.id.icon_1);
        icons[1]= (ImageView) findViewById(R.id.icon_2);
        icons[2]= (ImageView) findViewById(R.id.icon_3);
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
    }
}
