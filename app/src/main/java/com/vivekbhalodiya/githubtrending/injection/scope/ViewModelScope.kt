/*
 * Created by Vivek Bhalodiya on 19/11/19 1:37 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 1:06 AM
 */

package com.vivekbhalodiya.githubtrending.injection.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelScope(val value: KClass<out ViewModel>)
