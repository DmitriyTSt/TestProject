package ru.dmitriyt.testproject.presentation;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;

import ru.dmitriyt.testproject.R;
import ru.dmitriyt.testproject.databinding.ActivityMainBinding;
import ru.dmitriyt.testproject.presentation.documentList.DocumentListFragment;
import ru.dmitriyt.testproject.presentation.documentList.adapters.DocumentAdapter;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final ArrayList<String> titles = new ArrayList<>(Arrays.asList("Все", "Разделы", "Избранное"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tabs.setupWithViewPager(binding.viewpager);
        binding.viewpager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return new DocumentListFragment();
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}


