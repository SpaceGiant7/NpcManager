package com.example.npcmanager.Activities.Utilities;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.npcmanager.Activities.Fragments.GenderFragment;
import com.example.npcmanager.Activities.Fragments.LocationFragment;
import com.example.npcmanager.Activities.Fragments.MainFragment;
import com.example.npcmanager.Activities.Fragments.OccupationFragment;
import com.example.npcmanager.Activities.Fragments.OrganizationFragment;
import com.example.npcmanager.Activities.Fragments.RaceFragment;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    private List<String> tabNames = Arrays.asList(
            "Main", "Organization", "Location", "Race", "Occupation", "Gender");
    private List<Supplier<Fragment>> tabFragments = Arrays.asList(
            MainFragment::new,
            OrganizationFragment::new,
            LocationFragment::new,
            RaceFragment::new,
            OccupationFragment::new,
            GenderFragment::new);

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabFragments.get(position).get();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }

    @Override
    public int getCount() {
        return tabNames.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((Fragment) object).getView();
    }


}