package com.example.adu49.materialtoolmenucustom.Activities;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
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
import android.widget.LinearLayout;

import com.example.adu49.materialtoolmenucustom.Adapter.ViewPagerAdapter;
import com.example.adu49.materialtoolmenucustom.R;

public class MainActivity extends AppCompatActivity {
    View mCustomView, mCustomView2, mCustomView3, mCustomView4, mCustomView5;
    int page;
    LinearLayout footer;
    ActionMenuView Amv1, Amv2, Amv3;

    @SuppressLint("InflateParams")
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
        mCustomView4 = mInflater.inflate(R.layout.tab_1_footer, null);
        mCustomView5 = mInflater.inflate(R.layout.tab_2_footer, null);


        Amv1 = (ActionMenuView) mCustomView.findViewById(R.id.menu);      //ActionMenuView For the menu icon
        Amv2 = (ActionMenuView) mCustomView2.findViewById(R.id.back);     //ActionMenuView for back button
        Amv3 = (ActionMenuView) mCustomView3.findViewById(R.id.menu3);
        //ActionMenuView for menu on 3rd toolbar


       /*setting up footer over here inside LinearLayout present in activity_main using addview()*/
        footer = (LinearLayout) findViewById(R.id.footer);
        footer.addView(mCustomView4);
        View v = mCustomView4;
        layoutparam(v);     //method defined below


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
                viewPager.setCurrentItem(tab.getPosition());
                page = viewPager.getCurrentItem();
                if (page == 0) {
                    /*if first fragment is there*/
                    MainActivity.this.invalidateOptionsMenu();
                    inflateR(mCustomView);

                    /*setting tab_2_toolbar ActionMenuView*/
                    Amv1.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return onOptionsItemSelected(item);
                        }
                    });

                    /*setting up footer fragmentwise*/
                    footer.removeAllViews();//removing existing layouts which are in LinearLayout
                    footer.addView(mCustomView4);
                    View v = mCustomView4;
                    layoutparam(v);     //method defined below used to set the width of the customView



                } else if (page == 1) {

                    /*if second fragment is there*/
                    MainActivity.this.invalidateOptionsMenu();
                    inflateR(mCustomView2);

                   /*setting edittext hint size */
                    EditText e = (EditText) mCustomView2.findViewById(R.id.searchBar);
                    e.setHint((Html.fromHtml("<small>" +
                            getString(R.string.search_for_stores_offers_and_cashback) + "</small>")));


                    /*setting tab_2_toolbar ActionMenuView*/
                    Amv2.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return onOptionsItemSelected(item);
                        }
                    });

                    /*setting up footer fragmentwise*/
                    footer.removeAllViews();
                    footer.addView(mCustomView5);
                    View v = mCustomView5;
                    layoutparam(v);     //method defined below

                } else {

                    /*if third fragment is there*/
                    MainActivity.this.invalidateOptionsMenu();
                    inflateR(mCustomView3);
                    Amv3.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
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
        layoutparam(v);
    }

    void layoutparam(View view)
    {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(lp);
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
