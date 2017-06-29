package com.doyoon.android.fastcampusandroid.week5.musicplayer;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week5.musicplayer.ListFragment.OnListFragmentInteractionListener;
import com.doyoon.android.fastcampusandroid.week5.musicplayer.domain.Music;
import com.doyoon.android.fastcampusandroid.week5.musicplayer.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.doyoon.android.fastcampusandroid.week5.musicplayer.Player.playerStatus;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context = null;
    private final OnListFragmentInteractionListener mListener;

    // 데이터 저장소
    /* 1번 방법 */
    private final Set<Music.Item> mValues;
    /* 2번 방법 */
    private final Music.Item items[]; // 여기서는 datas
    /* 3번 방법 */
    private final List<Music.Item> musicList;



    public  ListAdapter(Set<Music.Item> itemSet, OnListFragmentInteractionListener listener) {

        /* set에서 데이터 꺼내기 */
        /* 1번 데이터 저장 방법 */
        this.mValues = itemSet;
        /* 2번 데이터 저장 방법 */
        this.items = mValues.toArray(new Music.Item[mValues.size()]);
        /* 3번  데이터 저장 방법 */
        this.musicList = new ArrayList<>(itemSet);
            /* 껍데기만 캐스팅 된다. */
            // this.items = (Music.Item[]) mValues.toArray();

        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        /* WOW !!! 여기서 Context를 꺼내서 담아두어도 된다. */
        if (context == null) {
            context = parent.getContext();
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // datas 저장소에 들어가 있는 Music Item 한개를 꺼낸다.
        // holder.mItem = items[position];

        /* 성능상의 차이가 별로 없다. */
        // 이렇게 담아서 사용하나 아니면 바로 호출하나 성능상의 차이가 별로 없다.
        // holder.mItem = musicList.get(position);
        holder.position = position;
        holder.musicUri = musicList.get(position).getMusicUri();

        holder.mIdView.setText(musicList.get(position).getId());
        holder.mContentView.setText(musicList.get(position).getTitle());

        /* 여기가 글라이드로 변해야 한다. 이미지를 그대로 올리면... 메모리가 많이 쓰인다.
        * 이미지를 축소해서 로드해줌. */
        // holder.imgAlbum.setImageURI(musicList.get(position).getAlbumArtUri());   // 이건 Default 설정이 안되어 있네
        Glide
                .with(this.context /* onCreate에서 담아둔다. */)
                .load(musicList.get(position).getAlbumArtUri()) /* 인터넷 URI를 써도 된다. */
                .placeholder(R.mipmap.mp3_icon)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.imgAlbum);

        if(musicList.get(position).itemClicked){
            holder.btnPause.setVisibility(View.VISIBLE);
        } else {
            holder.btnPause.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        /* 플레이어라는 객체로 분리를 해야 한다. */
        public int position;
        public Uri musicUri;    /* TODO final을 지워줬나?? */

        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView imgAlbum;
        public final ImageButton btnPause;


        /* mvc를 위해서 삭제*/
        // public Music.Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.music_activity_id);
            mContentView = (TextView) view.findViewById(R.id.music_activity_title);
            imgAlbum = (ImageView) view.findViewById(R.id.imgAlbum);
            btnPause = (ImageButton) view.findViewById(R.id.btnPause);

            btnPause.setOnClickListener(this);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        // 플레이
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnPause:
                     /* 이제 플레이어의 상태를 체크할 수 있게 되었다. */
                    switch (playerStatus) {
                        case Player.PLAY:
                            Player.pause();
                            btnPause.setImageResource(android.R.drawable.ic_media_play); /* 플레이가 되면서 버튼의 아이콘이 바뀐다. */
                            break;
                        case Player.PAUSE:
                            Player.replay();
                            btnPause.setImageResource(android.R.drawable.ic_media_pause);
                            break;
                    }
                    break;
                default:
                     /* Presenter로 치환할 수 있을것 같은데... */
                    // presenter.play(position);
                    // ListAdapter.this.play(position);
                    Player.play(musicUri, mView.getContext());
                    btnPause.setImageResource(android.R.drawable.ic_media_pause);  /* 뷰를 세팅하는 것은 여기에 남겨둔다. */
                    setItemClicked(position);
                    break;
            }
        }

        // 상세보기로 이동.
        @Override
        public boolean onLongClick(View v) {
            mListener.goDetailInteraction(position);
            return true;
            // return false; /* true 로 바꾸지 않으면 온클릭도 실행되어 버린다.*/
        }
    }

    /* presenter */
    public void goDetail(int position){
        mListener.goDetailInteraction(position);/* ListFragment를 거치지 않고 바로 Activity에 호출할 수 있다. */
    }

    /* 두가지 전략이 있을 수 있다.
    * 현재 경우는 작업이 간단하기 때문에 for문을 모두 돌아서 작성하지만
    * 작업이 복잡하거나, 데이터의 개수가 많은 경우 이전 상태를 저장해서 그 작업만 찝어서 해줘야 한다.
    * 이렇게 되면 트랜잭션을 사용하고, Commit을 사용해서 안전하게 처리한다. */

    public void setItemClicked(int position) {

        for (Music.Item item : musicList) {
            item.itemClicked = false;
        }
        musicList.get(position).itemClicked = true;

        notifyDataSetChanged();

    }
}
