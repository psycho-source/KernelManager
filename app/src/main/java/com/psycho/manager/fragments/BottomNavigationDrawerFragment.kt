package com.psycho.manager.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.psycho.manager.R
import com.psycho.manager.activities.Choice
import com.psycho.manager.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_drawer.*

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    var oldChoice = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drawer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        oldChoice = Choice.choice

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Choice.choice = 0
                }
                R.id.nav_profile -> {
                    Choice.choice = 1
                }
                R.id.nav_set -> {
                    Choice.choice = 2
                }
            }
            dismiss()
            true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (oldChoice != Choice.choice)
            (activity as MainActivity).fetchChoice(oldChoice)
    }

}