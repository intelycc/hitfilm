package com.example.android.hitfilm;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.hitfilm.config.TheMovieDbConfig;
import com.example.android.hitfilm.data.FilmInfo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HitFilmAdapter extends RecyclerView.Adapter<HitFilmAdapter.HitFilmAdapterViewHolder>{

    private List<FilmInfo> filmInfoList;
    private Context context;

    private final FilmAdapterOnClickHandler mClickHandler;


    HitFilmAdapter(FilmAdapterOnClickHandler filmAdapterOnClickHandler){
        mClickHandler = filmAdapterOnClickHandler;
    }

    public interface FilmAdapterOnClickHandler {
        void onClick(FilmInfo filmInfo);
    }

    public void setFilmInfoList(List<FilmInfo> filmInfoList) {
        this.filmInfoList = filmInfoList;
        notifyDataSetChanged();
    }


    @Override
    public HitFilmAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.hitfilm_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new HitFilmAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HitFilmAdapterViewHolder holder, int position) {
        FilmInfo filmInfo = filmInfoList.get(position);
        String posterPath = filmInfo.getPosterPath();
        Uri imageUri = Uri.parse(TheMovieDbConfig.IMAGE_BASE_URL+posterPath);
        Picasso.with(context).load(imageUri.toString()).into(holder.filmImageView);

        holder.filmTitleTextView.setText(filmInfo.getOriginalTitle());
    }

    @Override
    public int getItemCount() {
        if(filmInfoList == null){
            return 0;
        }else{
            return filmInfoList.size();
        }
    }


    public class HitFilmAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView filmImageView;
        public TextView filmTitleTextView;

        public HitFilmAdapterViewHolder(View view) {
            super(view);
            filmImageView = (ImageView) view.findViewById(R.id.iv_film_image);
            filmTitleTextView = (TextView)view.findViewById(R.id.tv_film_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            FilmInfo clickFilmInfo = filmInfoList.get(adapterPosition);
            mClickHandler.onClick(clickFilmInfo);

        }
    }
}
