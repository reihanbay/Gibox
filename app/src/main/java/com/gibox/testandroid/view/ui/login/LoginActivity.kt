/*
 * Created by Muhamad Syafii
 * Monday, 04/04/2022
 * Copyright (c) 2022 by Gibox Digital Asia.
 * All Rights Reserve
 */

package com.gibox.testandroid.view.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.gibox.testandroid.R
import com.gibox.testandroid.core.data.auth.source.remote.request.LoginRequest
import com.gibox.testandroid.core.db.PrefUtils
import com.gibox.testandroid.databinding.ActivityLoginBinding
import com.gibox.testandroid.util.constant.ACCESS_TOKEN
import com.gibox.testandroid.util.constant.FIELD_REQUIRED
import com.gibox.testandroid.util.constant.KEY_LOGIN
import com.gibox.testandroid.util.openActivity
import com.gibox.testandroid.view.ui.viewmodel.MainViewModel
import com.gibox.testandroid.view.ui.listuser.ListUserActivity
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginActivity : AppCompatActivity() {


    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel : MainViewModel by viewModel()
    private val pref by lazy { PrefUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initAction()
        initObservable()
    }
    private fun initView() {
        binding.apply {
            fieldEmail.edt.hint = getString(R.string.txt_email)
            fieldEmail.edt.setText("eve.holt@reqres.in")
            fieldEmail.edt.imeOptions = EditorInfo.IME_ACTION_DONE
            fieldPass.edt.setText("cityslicka")
            fieldPass.edt.hint = getString(R.string.txt_password)
            fieldPass.edt.imeOptions = EditorInfo.IME_ACTION_DONE
        }
    }

    private fun initAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.fieldEmail.edt.text.toString()
            val pass = binding.fieldPass.edt.text.toString()
            when {
                email.isNullOrEmpty() -> binding.fieldEmail.edt.error = FIELD_REQUIRED
                pass.isNullOrEmpty() -> binding.fieldPass.edt.error = FIELD_REQUIRED
                else -> viewModel.requestLogin(LoginRequest(email, pass))
            }
        }
    }

    private fun initObservable() {
        Timber.d("initObservable: A")
        viewModel.dataRequestLogin.observe(this) {
            pref.saveString(ACCESS_TOKEN, it.token)
            pref.saveBoolean(KEY_LOGIN, true)
            openActivity(ListUserActivity::class.java)
        }
        viewModel.isLoadingRequestLogin.observe(this) {
            when(it) {
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
            }
        }
    }
}