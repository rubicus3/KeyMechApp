package com.rubicus.keymechapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rubicus.keymechapp.fragments.AccountPageFragment;
import com.rubicus.keymechapp.fragments.CatalogPageFragment;
import com.rubicus.keymechapp.fragments.HomePageFragment;
import com.rubicus.keymechapp.helper.ProductType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.navigation_catalog) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_navigation_layout, CatalogPageFragment.class, null)
                        .addToBackStack("catalog")
                        .commit();
            }
            else if (itemId == R.id.navigation_home) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_navigation_layout, HomePageFragment.class, null)
                        .addToBackStack("home")
                        .commit();
            }
            else if (itemId == R.id.navigation_account) {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_navigation_layout, AccountPageFragment.class, null)
                        .addToBackStack("account")
                        .commit();
            }
            else {
                fragmentManager.beginTransaction()
                        .replace(R.id.main_navigation_layout, HomePageFragment.class, null)
                        .addToBackStack("home")
                        .commit();
            }
            return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        findViewById(R.id.top_button_back).setOnClickListener(v -> {
            fragmentManager.popBackStack();

        });
    }
}