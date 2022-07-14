/*
 * Created by Muhamad Syafii
 * Monday, 04/04/2022
 * Copyright (c) 2022 by Gibox Digital Asia.
 * All Rights Reserve
 */

package com.gibox.testandroid.view.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import com.gibox.testandroid.R
import com.gibox.testandroid.core.data.auth.source.remote.request.LoginRequest
import com.gibox.testandroid.databinding.ActivityLoginBinding
import com.gibox.testandroid.util.constant.FIELD_REQUIRED
import com.gibox.testandroid.util.hideKeyboard
import com.gibox.testandroid.util.openActivity
import com.gibox.testandroid.util.showToast
import com.gibox.testandroid.view.ui.viewmodel.MainViewModel
import com.gibox.testandroid.view.ui.listuser.ListUserActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {


    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        val data = LoginRequest("eve.holt@reqres.in",
        "cityslicka")
        viewModel.requestLogin(data)

    }
    private fun initView() {
        binding.apply {
            fieldEmail.edt.hint = getString(R.string.txt_email)
            fieldEmail.edt.imeOptions = EditorInfo.IME_ACTION_DONE
            fieldPass.edt.hint = getString(R.string.txt_password)
            fieldPass.edt.imeOptions = EditorInfo.IME_ACTION_DONE
        }
    }

    private fun initAction() {}
}