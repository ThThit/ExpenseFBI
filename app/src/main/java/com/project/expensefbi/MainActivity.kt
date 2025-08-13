package com.project.expensefbi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.expensefbi.fragments.AddExpenseFragment
import com.project.expensefbi.fragments.ExpenseOverviewFragment
import com.project.expensefbi.fragments.ExpenseStatisticFragment
import com.project.expensefbi.fragments.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, ExpenseOverviewFragment())
                .commit()
            bottomNavigationView.selectedItemId = R.id.nav_expense
        }

        // fragment selection
        bottomNavigationView.setOnItemSelectedListener {
            val fragment = when (it.itemId){
                R.id.nav_expense -> ExpenseOverviewFragment()
                R.id.nav_setting -> SettingFragment()
                R.id.nav_statistic -> ExpenseStatisticFragment()
                R.id.nav_add_expense -> AddExpenseFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            true
        }
    }
}