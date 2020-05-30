/*
 * Copyright 2018-2020 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpack_java.sample_01_lifecycles.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpack_java.R;
import com.example.jetpack_java.sample_01_lifecycles.data.Configs;
import com.example.jetpack_java.sample_01_lifecycles.data.bean.LocationBean;
import com.example.jetpack_java.sample_01_lifecycles.domain.LocationManager;
import com.example.jetpack_java.sample_01_lifecycles.ui.adapter.LocationAdapter;
import com.kunminx.architecture.ui.BaseActivity;

import java.util.List;

/**
 * Create by KunMinX at 19/10/16
 */

public class LifecycleLocationActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private LocationAdapter mLocationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lifecycles_location);

        mRecyclerView = findViewById(R.id.rv);

        mRecyclerView.setAdapter(mLocationAdapter = new LocationAdapter(getApplicationContext(), locationBean -> {
            Intent intent = new Intent();
            intent.putExtra(Configs.LOCATION_RESULT, locationBean.getLocationName());
            setResult(RESULT_OK, intent);
            finish();
        }));

        getLifecycle().addObserver(LocationManager.newInstance());

        LocationManager.newInstance().setILocationCallback(list -> {
            runOnUiThread(() -> mLocationAdapter.setList(list));
        });
    }

}
