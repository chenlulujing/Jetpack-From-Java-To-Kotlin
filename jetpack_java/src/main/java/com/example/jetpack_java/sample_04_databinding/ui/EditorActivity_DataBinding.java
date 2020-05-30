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

package com.example.jetpack_java.sample_04_databinding.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.jetpack_java.R;
import com.example.jetpack_java.common_data.Configs;
import com.example.jetpack_java.common_data.bean.Moment;
import com.example.jetpack_java.databinding.ActivityEditorDatabindingBinding;
import com.example.jetpack_java.sample_04_databinding.ui.state.EditorViewModel;
import com.kunminx.architecture.ui.BaseActivity;

/**
 * Create by KunMinX at 19/10/16
 */

public class EditorActivity_DataBinding extends BaseActivity {

    private EditorViewModel mEditorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditorViewModel = getActivityViewModel(EditorViewModel.class);

        ActivityEditorDatabindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_editor_databinding);
        binding.setVm(mEditorViewModel);


    }

}