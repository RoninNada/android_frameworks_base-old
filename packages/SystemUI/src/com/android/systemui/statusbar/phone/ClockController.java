/*
 * Copyright (C) 2018 The LineageOS Project
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

package com.android.systemui.statusbar.phone;

import android.util.Log;
import android.provider.Settings;
import android.view.View;

import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.tuner.TunerService;

import lineageos.providers.LineageSettings;

public class ClockController implements TunerService.Tunable {

    private static final String TAG = "ClockController";

    private static final int CLOCK_POSITION_RIGHT = 0;
    private static final int CLOCK_POSITION_CENTER = 1;
    private static final int CLOCK_POSITION_LEFT = 2;
    private static final int CLOCK_POSITION_HIDE = 3;

    private static final String STATUS_BAR_CLOCK =
            "lineagesystem:" + LineageSettings.System.STATUS_BAR_CLOCK;

    private Clock mCenterClock, mLeftClock, mRightClock;

    private int mClockPosition = CLOCK_POSITION_RIGHT;

    public ClockController(View statusBar) {
        mCenterClock = statusBar.findViewById(R.id.clock_center);
        mLeftClock = statusBar.findViewById(R.id.clock_left);
        mRightClock = statusBar.findViewById(R.id.clock);

        Dependency.get(TunerService.class).addTunable(this,
                STATUS_BAR_CLOCK);
    }

    private void updateActiveClock() {
        switch (mClockPosition) {
            case CLOCK_POSITION_CENTER:
                mLeftClock.setClockVisibleByUser(false);
                mRightClock.setClockVisibleByUser(false);
                mCenterClock.setClockVisibleByUser(true);
                break;
            case CLOCK_POSITION_LEFT:
                mCenterClock.setClockVisibleByUser(false);
                mRightClock.setClockVisibleByUser(false);
                mLeftClock.setClockVisibleByUser(true);
                break;
            case CLOCK_POSITION_RIGHT:
            default:
                mLeftClock.setClockVisibleByUser(false);
                mCenterClock.setClockVisibleByUser(false);
                mRightClock.setClockVisibleByUser(true);
                break;
            case CLOCK_POSITION_HIDE:
                mLeftClock.setClockVisibleByUser(false);
                mCenterClock.setClockVisibleByUser(false);
                mRightClock.setClockVisibleByUser(false);
                break;
        }
    }

    @Override
    public void onTuningChanged(String key, String newValue) {
        switch (key) {
            case STATUS_BAR_CLOCK:
                mClockPosition =
                        newValue == null ? CLOCK_POSITION_RIGHT : Integer.valueOf(newValue);
                updateActiveClock();
                break;
            default:
                break;
        }
    }
}
