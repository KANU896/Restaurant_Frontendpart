package com.example.myapplication.Main_Screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Main_Screen.DayRecommend_Data.DR_Datastore;
import com.example.myapplication.Main_Screen.Viewpager_Fragment.Food_recommend;

import java.util.ArrayList;

public class DR_Adapter extends FragmentStateAdapter {
    public DR_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Food_recommend();
        ArrayList<DR_Datastore> responseData = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        //bundle.putSerializable("responseData", responseData);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
