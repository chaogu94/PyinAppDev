package com.pinyin.ui.fragment;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pinyin.adapter.MyInfoAdapter;
import com.pinyin.liusirui.pyinappdev.R;
import com.pinyin.model.MyInfo;
import com.pinyin.ui.utils.FastBlurUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfoFragment extends Fragment {
    private ImageView imageView;
    private CircleImageView circleImageView;
    private ArrayList<MyInfo> list;
    private ListView listView;

    public MyInfoFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        //set title user name
        TextView textView = (TextView)view.findViewById(R.id.title);
        textView.setText("瑞之");
        int scaleRatio = 20;
        int blurRadius = 8;
        //mipmap to bitmap and aero glass
        BitmapDrawable originDrawable = (BitmapDrawable)getResources().getDrawable(R.mipmap.head);
        Bitmap originBitmap = originDrawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originBitmap,
                originBitmap.getWidth() / scaleRatio,
                originBitmap.getHeight() / scaleRatio,
                false);
        Bitmap blurBitmap = FastBlurUtil.doBlur(scaledBitmap, blurRadius, true);

        imageView = (ImageView)view.findViewById(R.id.profile_image_bg);
        circleImageView = (CircleImageView)view.findViewById(R.id.profile_image);
        //set image
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(blurBitmap);
        circleImageView.setImageBitmap(originBitmap);

        list = new ArrayList<>();
        prepareList(list);
        listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(new MyInfoAdapter(getActivity().getApplicationContext(),list));


        return view;
    }
    public void prepareList(List<MyInfo> data){
        String[] strings = {"我的资产","我发布的","我拼过的","我评论的","优惠券","null","帮助与反馈","设置"};
        int[] icon = {R.mipmap.icon_item1,R.mipmap.icon_item2,R.mipmap.icon_item3,R.mipmap.icon_item4,
                R.mipmap.icon_item5,R.mipmap.icon_item6,R.mipmap.icon_item6,R.mipmap.icon_item7};
        for(int i=0;i<strings.length;i++){
            data.add(new MyInfo(strings[i],icon[i],R.mipmap.icon_item_arrow));
        }
    }
}
