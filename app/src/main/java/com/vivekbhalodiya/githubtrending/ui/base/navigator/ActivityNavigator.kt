/*
 * Created by Vivek Bhalodiya on 19/11/19 11:36 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 25/10/19 5:12 PM
 */

package com.vivekbhalodiya.githubtrending.ui.base.navigator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object ActivityNavigator {

    fun startActivity(
        activityClass: Class<out AppCompatActivity>,
        activity: AppCompatActivity,
        finishAfter: Boolean = false,
        withoutAnimation: Boolean = false
    ) {
        val intent = Intent(activity, activityClass)
        activity.startActivity(intent)
        if (finishAfter) {
            finishActivityWithAnimation(activity = activity)
        }
        if (!withoutAnimation) {
            activity.overridePendingTransition(0, 0)
        }
    }

    fun startActivityWithData(
        activityClass: Class<out AppCompatActivity>,
        bundle: Bundle,
        activity: AppCompatActivity
    ) {
        val intent = Intent(activity, activityClass)
        intent.putExtras(bundle)
        activity.startActivity(intent)
    }

    fun startActivityWithAnimation(
        activityClass: Class<out AppCompatActivity>,
        inAnimation: Int,
        outAnimation: Int,
        activity: AppCompatActivity
    ) {
        val intent = Intent(activity, activityClass)
        activity.startActivity(intent)
        activity.overridePendingTransition(inAnimation, outAnimation)
    }

    fun startActivityWithDataAndAnimation(
        activityClass: Class<out AppCompatActivity>,
        bundle: Bundle,
        inAnimation: Int,
        outAnimation: Int,
        activity: AppCompatActivity
    ) {
        val intent = Intent(activity, activityClass)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(inAnimation, outAnimation)
    }

    fun addFragment(
        containerId: Int,
        fragment: Fragment,
        activity: AppCompatActivity
    ) {
        activity.supportFragmentManager.beginTransaction()
            .add(containerId, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    fun replaceFragment(
        containerId: Int,
        fragment: Fragment,
        activity: AppCompatActivity,
        addToBackStack: Boolean = false
    ) {
        var transaction = activity.supportFragmentManager.beginTransaction()
            .replace(containerId, fragment, fragment.javaClass.name)
        if (addToBackStack) {
            transaction = transaction.addToBackStack(fragment.javaClass.name)
        }
        transaction.commit()
    }

    fun finishActivityWithAnimation(
        inAnimation: Int? = null,
        outAnimation: Int? = null,
        activity: AppCompatActivity
    ) {
        activity.finish()
        inAnimation?.let {
            outAnimation?.let { it1 ->
                activity.overridePendingTransition(
                    it,
                    it1
                )
            }
        }
    }

    fun startActivityWithDataForResult(
        activityClass: Class<out AppCompatActivity>,
        bundle: Bundle,
        activity: AppCompatActivity,
        requestCode: Int
    ) {
        val intent = Intent(activity, activityClass)
        intent.putExtras(bundle)
        activity.startActivityForResult(intent, requestCode)
    }


    fun startActivityWithDataForResult(
        activityClass: Class<out AppCompatActivity>,
        bundle: Bundle,
        fragment: Fragment,
        requestCode: Int
    ) {
        val intent = Intent(fragment.context, activityClass)
        intent.putExtras(bundle)
        fragment.startActivityForResult(intent, requestCode)
    }
}