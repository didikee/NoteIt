package com.inno.noteit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.inno.noteit.R;
import com.inno.noteit.adapter.TopShowAdapter;
import com.inno.noteit.db.DBHelper;
import com.inno.noteit.db.entity.FishOrMouse;
import com.inno.noteit.fragment.TopShowFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView mRecyclerView;
    private TopShowAdapter mAdapter;
    private List<Model> mData;

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initView();
    }



    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "hahah", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,LikeHateActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initTopShow();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        //test
        List<Model> datas = DBHelper.queryAll(FishOrMouse.class);
            mData=datas;
        //add adapter
//        List<String> data=new ArrayList<String>();
//        data.add("22");
//        data.add("22");
//        data.add("22");
//        data.add("22");
//        data.add("22");
//        data.add("22");
//        data.add("22");
//        data.add("22");
//        data.add("22");
        mAdapter = new TopShowAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        //add layoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //add divierLine
//        mRecyclerView.addItemDecoration(new );
        //add item anametion
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initTopShow() {

        TopShowFragment frag_top=new TopShowFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_top_show,frag_top,"frag_top").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {
            testDataMaker();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void testDataMaker(){
        ActiveAndroid.beginTransaction();
        Random random = new Random();
        int nextInt = random.nextInt(10);

        try {
            for (int i = 0; i < nextInt; i++) {
                FishOrMouse item=new FishOrMouse();
                item.content="content :"+i;
                item.title="title :"+i;
                item.date=System.currentTimeMillis();
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }

        //read data ,and refresh listview
        List<Model> datas = DBHelper.queryAll(FishOrMouse.class);
        if (mData==null){
            mData=datas;
        }else {
            mData.addAll(datas);
        }
        mAdapter.notifyDataSetChanged();
    }
}
