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

package com.kunminx.jetpack_java.sample_06_room.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.BaseFragment;
import com.kunminx.jetpack_java.R;
import com.kunminx.jetpack_java.common_data.Configs;
import com.kunminx.jetpack_java.common_data.bean.Moment;
import com.kunminx.jetpack_java.databinding.FragmentListRoomBinding;
import com.kunminx.jetpack_java.sample_04_databinding.ui.adapter.DataBindingMomentAdapter;
import com.kunminx.jetpack_java.sample_05_navigation.ui.callback.SharedViewModel;
import com.kunminx.jetpack_java.sample_06_room.ui.state.ListViewModel;

/**
 * Create by KunMinX at 2020/5/30
 */
public class RoomListFragment extends BaseFragment {

    private ListViewModel mListViewModel;
    private SharedViewModel mSharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListViewModel = getFragmentViewModel(ListViewModel.class);
        mSharedViewModel = getActivityViewModel(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_room, container, false);
        FragmentListRoomBinding binding = FragmentListRoomBinding.bind(view);
        binding.setLifecycleOwner(this);
        binding.setVm(mListViewModel);
        binding.setClick(new ClickProxy());

        DataBindingMomentAdapter adapter = new DataBindingMomentAdapter(mActivity.getApplicationContext());
        adapter.setOnItemClickListener(((item, position) -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Configs.THIS_MOMENT, item);
            nav().navigate(R.id.action_listFragment_Navigation_to_detailFragment_Navigation, bundle);
        }));
        binding.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), moments -> {
            mListViewModel.list.setValue(moments);
        });

        mSharedViewModel.moment.observe(getViewLifecycleOwner(), momentEvent -> {
            Moment moment = momentEvent.getContent();
            if (moment != null) {
                mListViewModel.list.getValue().add(0, moment);
                mListViewModel.list.setValue(mListViewModel.list.getValue());
            }
        });

        mListViewModel.requestList();
    }

    public class ClickProxy {
        public void fabClick() {
            nav().navigate(R.id.action_listFragment_Navigation_to_editorFragment_Navigation);
        }
    }
}