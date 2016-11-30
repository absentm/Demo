package com.dm.materialdrawerdemo.activtiys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.dm.materialdrawerdemo.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import static com.dm.materialdrawerdemo.R.id.toolbar;


public class MainActivity extends AppCompatActivity {
    private int barColor = Color.parseColor("#7b1fa2");

    //save our header or result
    private Drawer result = null;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withToolbar(mToolbar)
                .withHeader(R.layout.header)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_home)
                                .withIcon(FontAwesome.Icon.faw_home)
                                .withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_free_play).
                                withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_custom)
                                .withIcon(FontAwesome.Icon.faw_eye),
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
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundColor(barColor);
        setTitle("MateralDrawer - Demo");
//        mToolbar.setNavigationIcon(new IconicsDrawable(this)
//                .icon(GoogleMaterial.Icon.gmd_menu)
//                .color(Color.WHITE)
//                .sizeDp(18));
//
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,
//                        "NAV!",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

//        mToolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int menuItemId = item.getItemId();
//                if (menuItemId == R.id.action_search) {
//                    Toast.makeText(ToolBarActivity.this , R.string.menu_search , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_notification) {
//                    Toast.makeText(ToolBarActivity.this , R.string.menu_notifications , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_item1) {
//                    Toast.makeText(ToolBarActivity.this , R.string.item_01 , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_item2) {
//                    Toast.makeText(ToolBarActivity.this , R.string.item_02 , Toast.LENGTH_SHORT).show();
//
//                }
//                return true;
//            }
//        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

}
