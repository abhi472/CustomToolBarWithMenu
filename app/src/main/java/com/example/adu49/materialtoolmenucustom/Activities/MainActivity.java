package com.example.adu49.materialtoolmenucustom.Activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.adu49.materialtoolmenucustom.Fragments.Tab_1;
import com.example.adu49.materialtoolmenucustom.Fragments.Tab_2;
import com.example.adu49.materialtoolmenucustom.Fragments.Tab_3;
import com.example.adu49.materialtoolmenucustom.R;

public class MainActivity extends AppCompatActivity {
    View mCustomView, mCustomView2, mCustomView3;
    int page;
    ActionMenuView Amv1, Amv2, Amv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        LayoutInflater mInflater = LayoutInflater.from(this);
        mCustomView = mInflater.inflate(R.layout.tab_1_toolbar, null);
        mCustomView2 = mInflater.inflate(R.layout.tab_2_toolbar, null);
        mCustomView3 = mInflater.inflate(R.layout.tab_3_toolbar, null);
        Amv1 = (ActionMenuView) mCustomView.findViewById(R.id.menu);      //ActionMenuView For the menu icon
        Amv2 = (ActionMenuView) mCustomView2.findViewById(R.id.back);     //ActionMenuView for back button
        Amv3 = (ActionMenuView) mCustomView3.findViewById(R.id.menu3);    //ActionMenuView for menu on 3rd toolbar

        /*      here initialising toolbar for the first time activity is created and fist fragment tab is on */
        inflateR(mCustomView);
        Amv1.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });



       /*tablayout and setting fragments on view pager*/
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        if (viewPager != null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                page = viewPager.getCurrentItem();
                if (page == 0) {
                    /*if first fragment is there*/
                    Amv1.getMenu().clear();
                    MainActivity.this.invalidateOptionsMenu();
                    inflateR(mCustomView);

                } else if (page == 1) {

                    /*if second fragment is there*/
                    MainActivity.this.invalidateOptionsMenu();
                    inflateR(mCustomView2);
                    EditText e = (EditText) mCustomView2.findViewById(R.id.searchBar);
                    e.setHint((Html.fromHtml("<small>" +
                            getString(R.string.search_for_stores_offers_and_cashback) + "</small>")));
                    Amv2.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return onOptionsItemSelected(item);
                        }
                    });
                } else {

                    /*if third fragment is there*/
                    MainActivity.this.invalidateOptionsMenu();
                    inflateR(mCustomView3);
                    Amv2.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return onOptionsItemSelected(item);
                        }
                    });

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        /*FAB*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    /*made a function for setting up actionbars on different slides for code reduction*/
    void inflateR(View view) {

        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        View v = getSupportActionBar().getCustomView();
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        v.setLayoutParams(lp);
    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new Tab_1();
                    break;
                case 1:
                    fragment = new Tab_2();
                    break;
                case 2:
                    fragment = new Tab_3();
                default:
                    break;
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "page " + position;
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        if (page == 0) {

            inflater.inflate(R.menu.menu_2, Amv1.getMenu());
        } else if (page == 1) {
            inflater.inflate(R.menu.menu_main, Amv2.getMenu());
            menu.add(1, 1, 0, "settings").setIcon(R.drawable.ic_close_white_24dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            inflater.inflate(R.menu.menu_2, Amv3.getMenu());
            menu.add(1, 1, 0, "settings").setIcon(R.drawable.ic_replay_white_24dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
