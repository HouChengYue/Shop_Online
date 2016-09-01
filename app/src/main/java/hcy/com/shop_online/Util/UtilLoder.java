package hcy.com.shop_online.Util;


import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * 资源加载工具
 * Created by Administrator on 2016/8/30.
 */
public class UtilLoder {
    /**
     * 图片加载方法
     * @param context
     * @param source
     * @param imageView
     */
        public  static  void ImageLoder(Context context, String source, ImageView imageView){
            Picasso.with(context).load(source).into(imageView);//运用第三方框架毕加索 Picosso
        }
}
