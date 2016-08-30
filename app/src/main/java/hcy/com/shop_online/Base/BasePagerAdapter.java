package hcy.com.shop_online.Base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 滑动页面的实现
 * 基础页面适配器　BasePagerAdapter
 *
 */
public class BasePagerAdapter extends PagerAdapter {

    protected Context context;
    private ArrayList<View> viewList = new ArrayList<View>();
    private ArrayList<String> tabtitleList = new ArrayList<String>();

    /**
     * 构造方法
     * @param context
     */
    public BasePagerAdapter(Context context) {
        this.context = context;
    }

    /**
     * 得到List
     * @return
     */
    public ArrayList<View> getViewList() {
        return viewList;
    }

    /**
     * 添加view
     * @param view
     */
    public void addViewToAdapter(View view) {
        viewList.add(view);
    }

    /**
     * 添加title
     * @param title
     */
    public void addTabToAdapter(String title) {
        tabtitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Auto-generated method stub
        return tabtitleList.get(position);
    }

    /**
     * 销毁条目
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = viewList.get(position);
        container.removeView(view);
    }

    /**
     * 获取View
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        container.addView(view);
        return view;
    }

    /**
     * 得到条目
     * @return
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return viewList.size();
    }

    /**
     * 判断是否是当期页面
     * @param arg0
     * @param arg1
     * @return
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

}
