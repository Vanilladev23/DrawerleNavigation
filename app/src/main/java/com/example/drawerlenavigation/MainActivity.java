package com.example.drawerlenavigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

	Toolbar toolbar;
	DrawerLayout drawerLayout;
	NavigationView navigationView;
	FragmentManager fragmentManager;
	@SuppressLint("NonConstantResourceId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = findViewById(R.id.toolbar);
		drawerLayout = findViewById(R.id.drawer_layout);
		navigationView = findViewById(R.id.navigation_view);
		navigationView.setItemIconTintList(null);
		fragmentManager = getSupportFragmentManager();

		setSupportActionBar(toolbar);
		Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"red\">Home</font>"));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);

		toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(navigationView));

		navigationView.setNavigationItemSelectedListener(item -> {
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			Fragment fragment = null;
			String name = "";
			switch (item.getItemId()) {
				case R.id.item_home:
					fragment = new AndroidFragment();
					name = "Android";
					break;
				case R.id.item_messages:
					fragment = new IOSFragment();
					name = "IOS";
					break;
			}
			assert fragment != null;
			fragmentTransaction.replace(R.id.fragment_container, fragment);
			fragmentTransaction.commit();
			Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"red\">"+ name +
					"</font>"));
			drawerLayout.closeDrawers();
			return true;
		});
	}
}