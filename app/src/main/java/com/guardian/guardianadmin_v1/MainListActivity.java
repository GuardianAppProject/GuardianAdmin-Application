package com.guardian.guardianadmin_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.guardian.guardianadmin_v1.Transmissions.SingleUserDetailed;
import com.guardian.guardianadmin_v1.UserModels.User;
import com.guardian.guardianadmin_v1.UserModels.UserList;
import com.guardian.guardianadmin_v1.UserModels.UserListAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Thread.sleep;

public class MainListActivity extends AppCompatActivity implements UserListAdapter.OnItemListener {


    // Menu
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Adapter
    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        //UserList.updatePhoneNumbers();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // ...From section above...
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView2);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        Button button = (Button) findViewById(R.id.menuButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new UserListAdapter(this, UserList.getAllUsers(), this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        this.setSupportActionBar(toolbar2);
        this.getSupportActionBar().setTitle("");

        Button sortBtn = (Button) findViewById(R.id.sortBtn);
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(adapter.getData(), new Comparator<UserList>() {
                    @Override
                    public int compare(UserList lhs, UserList rhs) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return lhs.getAverage() > rhs.getAverage() ? -1 : (lhs.getAverage() < rhs.getAverage() ) ? 1 : 0;
                    }
                });
                ArrayList<UserList> newList = new ArrayList<>(adapter.getData());
                adapter.updateData(newList);
            }
        });
    }


    public void openDrawer(){
        mDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (item.getItemId() == android.R.id.home) {
            mDrawer.openDrawer(GravityCompat.START);
            return true;
        }

        if (item.getItemId() == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
//        Fragment fragment = null;
//        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.account:
                Intent i = new Intent(MainListActivity.this, MyAccount.class);
                startActivity(i);
                break;
            case R.id.support:
                Intent i2 = new Intent(MainListActivity.this, Support.class);
                startActivity(i2);
                break;
            case R.id.info:
                Intent i3 = new Intent(MainListActivity.this, Info.class);
                startActivity(i3);
                break;
            case R.id.settings:
                Intent i4 = new Intent(MainListActivity.this, Setting.class);
                startActivity(i4);
                break;
        }

//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.flContent,fragment ).commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


        // Highlight the selected item has been done by NavigationView
//        menuItem.setChecked(true);

        // Set action bar title
//        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MainUserActivity.class);
        System.out.println("pos: " + position);
        intent.putExtra("username", adapter.getData().get(position).getPhoneNumber());
        //inja mitonim data ro update konim
        String phoneNumber = adapter.getData().get(position).getPhoneNumber();
        System.out.println(phoneNumber);
        SingleUserDetailed.getUserDetailed(MainActivity.getToken(),phoneNumber);
        //
        try{
            sleep(250);
        }catch (Exception e){

        }

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    public static void updatePage(){
        // in bayad listi ke yaro mibine ro beroz kone ke chizayi ke jadid add shodano bebine
    }


    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_search) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}