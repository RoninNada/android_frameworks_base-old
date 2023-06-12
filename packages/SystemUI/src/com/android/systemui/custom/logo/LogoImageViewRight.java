/*
 * Copyright (C) 2018 crDroid Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.custom.logo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.systemui.R;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.DarkIconDispatcher;
import com.android.systemui.tuner.TunerService;

public class LogoImageViewRight extends ImageView implements
        TunerService.Tunable {

    private Context mContext;

    private boolean mAttached;
    private boolean mCustomLogo;
    private int mCustomLogoColor;
    private int mCustomLogoPosition;
    private int mCustomLogoStyle;
    private int mTintColor = Color.WHITE;

    private static final String STATUS_BAR_LOGO =
            "system:" + Settings.System.STATUS_BAR_LOGO;
    private static final String STATUS_BAR_LOGO_COLOR =
            "system:" + Settings.System.STATUS_BAR_LOGO_COLOR;
    private static final String STATUS_BAR_LOGO_POSITION =
            "system:" + Settings.System.STATUS_BAR_LOGO_POSITION;
    private static final String STATUS_BAR_LOGO_STYLE =
            "system:" + Settings.System.STATUS_BAR_LOGO_STYLE;

    public LogoImageViewRight(Context context) {
        this(context, null);
    }

    public LogoImageViewRight(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoImageViewRight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final Resources resources = getResources();
        mContext = context;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mAttached)
            return;

        mAttached = true;

        Dependency.get(DarkIconDispatcher.class).addDarkReceiver(this);

        Dependency.get(TunerService.class).addTunable(this,
                STATUS_BAR_LOGO,
                STATUS_BAR_LOGO_COLOR,
                STATUS_BAR_LOGO_POSITION,
                STATUS_BAR_LOGO_STYLE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!mAttached)
            return;

        mAttached = false;
        Dependency.get(TunerService.class).removeTunable(this);
        Dependency.get(DarkIconDispatcher.class).removeDarkReceiver(this);
    }

    public void onDarkChanged(Rect area, float darkIntensity, int tint) {
        mTintColor = DarkIconDispatcher.getTint(area, this, tint);
        if (mCustomLogo && mCustomLogoPosition == 1 &&
                mCustomLogoColor == 0xFFFFFFFF) {
            updateCustomLogo();
        }
    }

    public void updateCustomLogo() {
        Drawable drawable = null;

        if (!mCustomLogo || mCustomLogoPosition == 0) {
            setImageDrawable(null);
            setVisibility(View.GONE);
            return;
        } else {
            setVisibility(View.VISIBLE);
        }

        if (mCustomLogoStyle == 0) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_android_logo);
        } else if (mCustomLogoStyle == 1) {
           drawable = mContext.getResources().getDrawable(R.drawable.ic_apple_logo);
        } else if (mCustomLogoStyle == 2) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ios_logo);
        } else if (mCustomLogoStyle == 3) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon);
        } else if (mCustomLogoStyle == 4) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_cool);
        } else if (mCustomLogoStyle == 5) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_dead);
        } else if (mCustomLogoStyle == 6) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_devil);
        } else if (mCustomLogoStyle == 7) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_happy);
        } else if (mCustomLogoStyle == 8) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_neutral);
        } else if (mCustomLogoStyle == 9) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_poop);
        } else if (mCustomLogoStyle == 10) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_sad);
        } else if (mCustomLogoStyle == 11) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_tongue);
        } else if (mCustomLogoStyle == 12) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_blackberry);
        } else if (mCustomLogoStyle == 13) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_cake);
        } else if (mCustomLogoStyle == 14) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_blogger);
        } else if (mCustomLogoStyle == 15) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_biohazard);
        } else if (mCustomLogoStyle == 16) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_linux);
        } else if (mCustomLogoStyle == 17) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_yin_yang);
        } else if (mCustomLogoStyle == 18) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_windows);
        } else if (mCustomLogoStyle == 19) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_robot);
        } else if (mCustomLogoStyle == 20) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ninja);
        } else if (mCustomLogoStyle == 21) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_heart);
        } else if (mCustomLogoStyle == 22) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_flower);
        } else if (mCustomLogoStyle == 23) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ghost);
        } else if (mCustomLogoStyle == 24) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_google);
        } else if (mCustomLogoStyle == 25) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_human_male);
        } else if (mCustomLogoStyle == 26) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_human_female);
        } else if (mCustomLogoStyle == 27) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_human_male_female);
        } else if (mCustomLogoStyle == 28) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_gender_male);
        } else if (mCustomLogoStyle == 29) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_gender_female);
        } else if (mCustomLogoStyle == 30) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_gender_male_female);
        } else if (mCustomLogoStyle == 31) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_guitar_electric);
        } else if (mCustomLogoStyle == 32) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_batman);
        } else if (mCustomLogoStyle == 33) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_deadpool);
        } else if (mCustomLogoStyle == 34) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_decepticons);
        } else if (mCustomLogoStyle == 35) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ironman);
        } else if (mCustomLogoStyle == 36) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_minions);
        } else if (mCustomLogoStyle == 37) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_spiderman);
        } else if (mCustomLogoStyle == 38) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_superman);
        }

        setImageDrawable(null);

        clearColorFilter();

        if (mCustomLogoColor == 0xFFFFFFFF) {
            drawable.setTint(mTintColor);
        } else {
            setColorFilter(mCustomLogoColor, PorterDuff.Mode.SRC_IN);
        }
        setImageDrawable(drawable);
    }

    @Override
    public void onTuningChanged(String key, String newValue) {
        switch (key) {
            case STATUS_BAR_LOGO:
                mCustomLogo = newValue != null && Integer.parseInt(newValue) == 1;
                break;
            case STATUS_BAR_LOGO_COLOR:
                mCustomLogoColor =
                        newValue == null ? 0xFFFFFFFF : Integer.parseInt(newValue);
                break;
            case STATUS_BAR_LOGO_POSITION:
                mCustomLogoPosition =
                        newValue == null ? 0 : Integer.parseInt(newValue);
                break;
            case STATUS_BAR_LOGO_STYLE:
                mCustomLogoStyle =
                        newValue == null ? 0 : Integer.parseInt(newValue);
                break;
            default:
                break;
        }
        updateCustomLogo();
    }
}
