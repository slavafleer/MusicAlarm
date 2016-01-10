package com.slavafleer.musicalarm.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slavafleer.musicalarm.R;
import com.slavafleer.musicalarm.Tone;

import java.util.ArrayList;

// Adapter for recyclerViewTones.
public class ToneAdapter extends RecyclerView.Adapter<ToneHolder>{

    private Activity mActivity;
    private ArrayList<Tone> mTones;

    public ToneAdapter(Activity activity, ArrayList<Tone> tones) {

        mActivity = activity;
        mTones = tones;
    }

    @Override
    public ToneHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater)mActivity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View linearLayout = layoutInflater.inflate(R.layout.item_tone, null);

        return new ToneHolder(mActivity, linearLayout);
    }

    @Override
    public void onBindViewHolder(ToneHolder holder, int position) {

        Tone tone = mTones.get(position);

        holder.bindTone(tone);
    }

    @Override
    public int getItemCount() {

        return mTones.size();
    }
}
