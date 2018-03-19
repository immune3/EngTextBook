package com.yellowgreen.engtextbook.Util;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;

import com.yellowgreen.engtextbook.R;

/**
 * Created by mapl0 on 2018-03-13.
 */

public class SoundManager {

    private static SoundManager manager;
    private static MediaPlayer player;

    public Resources res;
    public int[] iamaboy;

    // 사운드를 저장할 클래스, 문장별로 배열에 나누어서 저장해서 사용해야할 듯. 추후에 추가
    public SoundManager() {
        player = new MediaPlayer();
        initVoice();
    }

    public static SoundManager getInstance() {
        if(manager == null) manager = new SoundManager();
        return manager;
    }

    public void initVoice() {
        iamaboy = new int[5];
        iamaboy[0] = R.raw.i;
        iamaboy[1] = R.raw.am;
        iamaboy[2] = -1;
        iamaboy[3] = R.raw.boy;
        iamaboy[4] = R.raw.iamaboy;
    }

//    public void playIamaboy() {
//        player = MediaPlayer.create(res[i], )
//    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setVoice(int res, Context context) {
        try {
            player = MediaPlayer.create(context, res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
