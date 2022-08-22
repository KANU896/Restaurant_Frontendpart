package com.example.myapplication.Main_Screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Main_Screen.Viewpager_Fragment.Recommend_fragment;

public class DR_Adapter extends FragmentStateAdapter {
    private String category;
    public DR_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String category) {
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
