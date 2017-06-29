package com.doyoon.android.fastcampusandroid.week5.musicplayer;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week5.musicplayer.domain.Music;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by DOYOON on 6/16/2017.
 */

public class DetailAdapter extends PagerAdapter {
    List<Music.Item> musicList = null;

    public DetailAdapter(Set<Music.Item> itemSet) {
        this.musicList = new ArrayList<>(itemSet);
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    /* RecyclerView의 onBindViewholder와 같은 역할 */

    /* 주의!! Object가 return 되므로 받는 쪽에서 instanceOf를 사용해야 한다.*/
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_fragment_albumart, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.musicplayer_iv_albumart);
        TextView textView = (TextView) view.findViewById(R.id.musicplayer_detail_tv_title);

        Glide.with(container.getContext())
                .load(musicList.get(position).getAlbumArtUri())
                .into(imageView);

        textView.setText(musicList.get(position).getTitle());
        container.addView(view);

        return view;
    }

    // 화면에서 사라진 뷰를 메모리에서 제거하는 역할도 있따.


    /* ViewPager는 항상 세개만 기억한다. prev, current, next */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewInLayout((View) object);
    }

    // instantiateItem에서 리턴한 Object가 View가 맞는지를 확인한다.
    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
}
