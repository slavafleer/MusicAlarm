package com.slavafleer.musicalarm.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.slavafleer.musicalarm.R;
import com.slavafleer.musicalarm.Tone;

// Holder for recyclerViewTones.
public class ToneHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private Activity mActivity;
    private TextView mTextViewToneName;
    private Tone mTone;

    public ToneHolder(Activity activity, View itemView) {
        super(itemView);

        mActivity = activity;

        mTextViewToneName = (TextView) itemView.findViewById(R.id.textViewToneName);

        mTextViewToneName.setOnClickListener(this);
    }

    // Enter the object data into the UI.
    public void bindTone(Tone tone) {

        mTone = tone;

        mTextViewToneName.setText(tone.toString());
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(v.getContext(), mTone.toString(), Toast.LENGTH_LONG ).show();

        SharedPreferences sharedPreferences = mActivity.getSharedPreferences(
                "MainActivity", mActivity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("toneId", mTone.getId() + "");

        editor.commit();
    }
}
