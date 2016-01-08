package com.slavafleer.musicalarm;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

// Holder for recyclerViewTones.
public class ToneHolder extends RecyclerView.ViewHolder {

    private Activity mActivity;
    private TextView mTextViewToneName;

    public ToneHolder(Activity activity, View itemView) {
        super(itemView);

        mActivity = activity;

        mTextViewToneName = (TextView) itemView.findViewById(R.id.textViewToneName);
    }

    // Enter the object data into the UI.
    public void bindTone(Tone tone) {

        mTextViewToneName.setText(tone.toString());
    }
}
