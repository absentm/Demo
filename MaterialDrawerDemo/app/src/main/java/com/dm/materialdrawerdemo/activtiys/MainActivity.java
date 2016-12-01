package com.dm.materialdrawerdemo.activtiys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dm.materialdrawerdemo.R;
import com.dm.materialdrawerdemo.fragments.PageFragment;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int barColor = Color.parseColor("#7b1fa2");

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private FloatingActionButton mFloatingActionButton;

    // save our header or result
    private Drawer result = null;
    private Toolbar mToolbar;
    private Bundle mSavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSavedInstanceState = savedInstanceState;

        initView();
        initToolbar();
        initViewPagerAndTabs();
        initMatrialDrawer();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_button);
        mFloatingActionButton.setImageDrawable(new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_vertical_align_top)
                .color(Color.WHITE)
                .sizeDp(18));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    private void initMatrialDrawer() {
        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.header)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_home)
                                .withIcon(FontAwesome.Icon.faw_home)
                                .withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_free_play)
                                .withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_about)
                                .withIcon(FontAwesome.Icon.faw_user)
                                .withIdentifier(11),
                        //add some more items to get a scrolling list
                        new SectionDrawerItem()
                                .withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_settings)
                                .withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_help)
                                .withIcon(FontAwesome.Icon.faw_question)
                                .withEnabled(false),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_open_source)
                                .withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_contact)
                                .withIcon(FontAwesome.Icon.faw_bullhorn),
                        new SectionDrawerItem()
                                .withName(R.string.drawer_item_section_header),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_custom)
                                .withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_custom)
                                .withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_custom)
                                .withIcon(FontAwesome.Icon.faw_eye)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Toast.makeText(MainActivity.this,
                                    ((Nameable) drawerItem).getName().getText(MainActivity.this),
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (drawerItem.getIdentifier() == 11) {
                            startActivity(new Intent(MainActivity.this, AboutAty.class));
                        }
                        return false;
                    }
                })
                .withSavedInstance(mSavedInstanceState)
                .build();

    }

    private void initViewPagerAndTabs() {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(PageFragment.createInstance(45), getString(R.string.tab_1));
        pagerAdapter.addFragment(PageFragment.createInstance(34), getString(R.string.tab_2));
        pagerAdapter.addFragment(PageFragment.createInstance(5), getString(R.string.tab_3));
        pagerAdapter.addFragment(PageFragment.createInstance(28), getString(R.string.tab_4));
        pagerAdapter.addFragment(PageFragment.createInstance(30), getString(R.string.tab_5));
        pagerAdapter.addFragment(PageFragment.createInstance(14), getString(R.string.tab_6));
        pagerAdapter.addFragment(PageFragment.createInstance(8), getString(R.string.tab_7));
        pagerAdapter.addFragment(PageFragment.createInstance(24), getString(R.string.tab_8));
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundColor(barColor);
        setTitle("MateralDrawer - Demo");   // title
//        左侧导航图标
//        mToolbar.setNavigationIcon(new IconicsDrawable(this)
//                .icon(GoogleMaterial.Icon.gmd_menu)
//                .color(Color.WHITE)
//                .sizeDp(18));

//        左侧导航图标点击事件
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,
//                        "NAV!",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        // 右侧菜单, 方法出现在setSupportActionBar()之后失效，使用onCreateOptionsMenu()方法
//        mToolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int menuItemId = item.getItemId();
//                if (menuItemId == R.id.action_settings) {
//                    Toast.makeText(MainActivity.this, R.string.action_settings, Toast.LENGTH_SHORT).show();
//                } else if (menuItemId == R.id.action_about) {
//                    Toast.makeText(MainActivity.this, R.string.action_about, Toast.LENGTH_SHORT).show();
//                }
//
//                return true;
//            }
//        });
    }

    static class PagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this,
                        R.string.action_settings,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                startActivity(new Intent(MainActivity.this, AboutAty.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
