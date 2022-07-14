/*
 * Created by Muhamad Syafii
 * Monday, 04/04/2022
 * Copyright (c) 2022 by Gibox Digital Asia.
 * All Rights Reserve
 */

package com.gibox.testandroid.view.ui.listuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gibox.testandroid.core.db.PrefUtils
import com.gibox.testandroid.databinding.ActivityListUserBinding
import com.gibox.testandroid.util.constant.ACCESS_TOKEN
import com.gibox.testandroid.view.ui.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class ListUserActivity : AppCompatActivity() {

    private val binding by lazy { ActivityListUserBinding.inflate(layoutInflater) }
    private val viewModel : MainViewModel by viewModel()
    private val pref by lazy { PrefUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.requestListUser()

    }
}