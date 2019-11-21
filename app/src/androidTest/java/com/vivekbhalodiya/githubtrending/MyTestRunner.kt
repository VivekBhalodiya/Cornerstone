/*
 * Created by Vivek Bhalodiya on 21/11/19 6:33 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 6:33 PM
 */

package com.vivekbhalodiya.githubtrending

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * Created by Vivek Patel on 2019-11-21.
 */
class MyTestRunner: AndroidJUnitRunner() {
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application {
        // replace Application class with mock one
        return super.newApplication(classLoader, MyTestApplication::class.java.name, context)
    }
}