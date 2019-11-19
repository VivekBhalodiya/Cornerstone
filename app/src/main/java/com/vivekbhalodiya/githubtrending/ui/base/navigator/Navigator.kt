/*
 * Created by Vivek Bhalodiya on 19/11/19 11:36 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 25/10/19 5:12 PM
 */

package com.vivekbhalodiya.githubtrending.ui.base.navigator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

abstract class Navigator {
    abstract fun getActivity(): AppCompatActivity

    fun startActivity(activityClass: Class<out Activity>) {
        val (activity, intent) = getActivityIntent(activityClass)
        activity.startActivity(intent)
    }

    fun startActivityForResult(
        activityClass: Class<out Activity>,
        requestCode: Int
    ) {
        val (activity, intent) = getActivityIntent(activityClass)
        activity.startActivityForResult(intent, requestCode)
    }

    fun startActivityClearStack(activityClass: Class<out Activity>) {
        val (activity, intent) = getActivityIntent(activityClass)
        intent.flags =
            (Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
        activity.startActivity(intent)
        activity.finishAffinity()
    }

    fun startActivityWithData(
        activityClass: Class<out Activity>,
        bundle: Bundle
    ) {
        val (activity, intent) = getActivityIntent(activityClass)
        intent.putExtras(bundle)
        activity.startActivity(intent)
    }

    fun startActivityWithDataForResult(
        activityClass: Class<out Activity>,
        bundle: Bundle,
        requestCode: Int
    ) {
        val (activity, intent) = getActivityIntent(activityClass)
        intent.putExtras(bundle)
        activity.startActivityForResult(intent, requestCode)
    }

    private fun getActivityIntent(activityClass: Class<out Activity>): Pair<AppCompatActivity, Intent> {
        val activity = getActivity()
        val intent = Intent(activity, activityClass)
        return Pair(activity, intent)
    }

    fun addFragmentWithData(
        containerId: Int,
        fragment: Fragment,
        fragmentToHide: Fragment,
        bundle: Bundle
    ) {
        fragment.arguments = bundle
        addFragment(containerId, fragment, fragmentToHide)
    }

    fun addFragment(
        containerId: Int,
        fragment: Fragment,
        fragmentToHide: Fragment
    ) {
        getActivity().supportFragmentManager.beginTransaction()
            .add(containerId, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .hide(fragmentToHide)
            .commit()
    }

    fun replaceFragmentWithData(
        containerId: Int,
        fragment: Fragment,
        bundle: Bundle
    ) {
        fragment.arguments = bundle
        replaceFragment(containerId, fragment)
    }

    fun replaceFragment(
        containerId: Int,
        fragment: Fragment,
        addToBackStack: Boolean = false
    ) {
        getActivity().supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .apply { if (addToBackStack) addToBackStack(fragment::class.java.simpleName) }
            .commit()
    }

    fun goBack() {
        val fm = getActivity().supportFragmentManager
        if (fm.backStackEntryCount > 0)
            fm.popBackStack()
        else
            getActivity().finish()
    }

    fun popFragmentClearStack(tag: String = "") {
        getActivity().supportFragmentManager.popBackStack(
            if (tag.isEmpty()) null else tag, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }
    //Add more methods here... like add with animation

    fun addFragmentWithAnim(
        containerId: Int,
        fragment: Fragment,
        fragmentToHide: Fragment
    ) {
        getActivity().supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .add(containerId, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .hide(fragmentToHide)
            .commit()
    }

    fun replaceFragmentWithAnim(
        containerId: Int,
        fragment: Fragment,
        enterAnimId: Int,
        exitAnimId: Int,
        addToBackStack: Boolean = false
    ) {
        val transaction = getActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(enterAnimId, 0, 0, exitAnimId)
            .replace(containerId, fragment)
        if (addToBackStack) transaction.addToBackStack(fragment::class.java.simpleName)
        transaction.commit()

    }

    @SuppressLint("CommitTransaction")
    fun showDialogFragment(fragment: DialogFragment) {
        val transaction = getActivity().supportFragmentManager.beginTransaction()
        val prev = getActivity().supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            transaction.remove(prev)
        }
        transaction.addToBackStack(null)
        fragment.show(getActivity().supportFragmentManager, "dialog")
    }
}