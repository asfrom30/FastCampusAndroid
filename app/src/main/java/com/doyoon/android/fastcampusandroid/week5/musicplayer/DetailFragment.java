package com.doyoon.android.fastcampusandroid.week5.musicplayer;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week5.musicplayer.domain.Music;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.R.attr.duration;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public static final int CHANGE_SEEKBAR = 99;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_SEEKBAR:
                    viewHolder.setSeekBarPosition(msg.arg1);
                    break;
            }
        }
    };

    static final public String ARG1 = "position";

    private ViewHolder viewHolder = null;
    private int position = -1; /* null값이 없어서 position을 -1로 설정 */

    public static DetailFragment newInstance(int position) {
        //TODO 여기를 싱글턴으로 바꿔도 될거 같은데... Bundle도 마찬가지
        DetailFragment detailFragment = new DetailFragment();
        /* 생성될때 프래그먼트에 포지션을 넘겨준다. */
        Bundle bundle = new Bundle(); /* 프래그먼트는 번들을 받을 수 있다. */

        bundle.putInt(ARG1, position);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail2, container, false);
        Bundle bundle = getArguments();
        position = bundle.getInt(ARG1);
        // Inflate the layout for this fragment
        viewHolder = new ViewHolder(view, position);
        Log.e("TAG", "프래그먼트가 새로 생성되었습니다.");
        return view;
    }

    public Set<Music.Item> getMusicSet() {
        /* 뷰페이저가 먼저 호출될수도 있고, 뮤직 리스트가 먼저 호출될 수도 있으므로 Domain 로딩을 반드시 해야 한다. */
        Music music = Music.getInstance();
        music.loader(getContext());

        return music.getItemSet();
    }

    // View Pager의 View가 된다.
    public class ViewHolder implements View.OnClickListener {
        ViewPager viewPager;
        RelativeLayout layoutController;
        ImageButton btnPlay, btnNext, btnPrev;
        SeekBar seekbarVolume;
        TextView tvCurrent, tvDuration;


        public ViewHolder(View view, int position) {
            viewPager = (ViewPager) view.findViewById(R.id.musicplayer_viewpager_albumart);
            layoutController = (RelativeLayout) view.findViewById(R.id.musicPlyaer_controller_layout);

            btnPlay = (ImageButton) view.findViewById(R.id.btn_musicplayer_play);
            btnNext = (ImageButton) view.findViewById(R.id.btn_musicplayer_next);
            btnPrev = (ImageButton) view.findViewById(R.id.btn_musicplayer_prev);

            seekbarVolume = (SeekBar) view.findViewById(R.id.musicPlayer_seekbar_volume);
            tvCurrent = (TextView) view.findViewById(R.id.musicplayer_tv_current);
            tvDuration = (TextView) view.findViewById(R.id.musicplayer_tv_duration);

            setOnClickListener();
            // setViewPager();  // 이전코드
            setViewPager(position);
        }

        private void setViewPager(int position) {

            /* 아답터를 생성하고 아답터를 넘겨준다. */
            DetailAdapter adapter = new DetailAdapter(getMusicSet());
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(position);

            // deprecated
            // viewPager.setOnPageChangeListener(viewPageChangeListener);
            viewPager.addOnPageChangeListener(viewPageChangeListener);


        }

        public void setOnClickListener() {
            btnPlay.setOnClickListener(this);
            btnNext.setOnClickListener(this);
            btnPrev.setOnClickListener(this);
        }

        public void setSeekBarPosition(int seekBarPosition) {
            seekbarVolume.setProgress(seekBarPosition);
        }

        public void setDuration() {
            this.tvDuration.setText(duration + "");
        }

        private String msToString(int mSecond) {
            long min = mSecond / 1000 / 60;
            long sec = mSecond / 1000 % 60;

            /* 십진수 두자리로 만들어준다. */
            return String.format("%02d", min) + " : " + String.format("%02d", sec);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_musicplayer_next:
                    break;
                case R.id.btn_musicplayer_play:
                    getMusicSet();
                    List<Music.Item> musicList = new ArrayList<>(getMusicSet()); /* 이렇게하면 안된다. 자료형을 ListSet으로 변경하자 */
                    Player.play(musicList.get(position).getMusicUri(), v.getContext());

                    new SeekBarThread(handler).start();

                    // seekbar의 최대 길이를 지정
                    seekbarVolume.setMax(Player.getEndDuration());


                    break;
                case R.id.btn_musicplayer_prev:

                    break;
            }
        }

    }

    /* 페이지의 변경사항을 체크해서 현재 페이지 값을 알려준다. */
    ViewPager.OnPageChangeListener viewPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /* 페이지의 변경사항을 체크해서 현재 페이지 값을 알려준다. */
        @Override
        public void onPageSelected(int position) {
        /* 포지션에서 뭐가 넘어오는거지?? */


        /* 현재 페이지가 변경된 후 호출된다. */
            //Player.init(musicUri, context);

            //int musicDuration = 10;

            //musicInit();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
//    public void musicInit(){
//
//    }

