package com.example.myapplication.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Fragment.ViewpagerFragment.Recommend_fragment;

public class DayRecommend_Adapter extends FragmentStateAdapter {
    private String category;
    public DayRecommend_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String category) {
        super(fragmentManager, lifecycle);
        this.category = category;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment= new Recommend_fragment();;

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("category", category);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
