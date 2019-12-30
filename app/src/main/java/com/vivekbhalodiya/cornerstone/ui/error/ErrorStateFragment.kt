/*
 * Created by Vivek Bhalodiya on 20/11/19 11:03 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 20/11/19 11:03 PM
 */

package com.vivekbhalodiya.cornerstone.ui.error

import android.os.Bundle
import android.view.View
import com.vivekbhalodiya.cornerstone.R
import com.vivekbhalodiya.cornerstone.databinding.FragmentErrorStateBinding
import com.vivekbhalodiya.cornerstone.ui.base.BaseFragment
import com.vivekbhalodiya.cornerstone.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment_error_state.button_retry

class ErrorStateFragment : BaseFragment<FragmentErrorStateBinding, BaseViewModel>() {
  override fun getViewModelClass() = BaseViewModel::class.java

  override fun layoutId() = R.layout.fragment_error_state

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setupRetryButton()
  }

  private fun setupRetryButton() {
    button_retry.setOnClickListener {
    }
  }
}
