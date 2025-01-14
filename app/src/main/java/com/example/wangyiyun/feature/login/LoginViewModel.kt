package com.example.wangyiyun.feature.login
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.wangyiyun.R
import com.example.wangyiyun.core.data.repository.SessionRepository
import com.example.wangyiyun.core.model.User
import com.example.wangyiyun.core.exception.localException
import com.example.wangyiyun.core.result.asResult
import com.example.wangyiyun.feature.base.BaseViewModel
import com.example.wangyiyun.util.StringUtil
import com.example.wangyiyun.util.SuperRegularUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
//    private val userDataRepository: UserDataRepository,
) : BaseViewModel() {
    val uiState = MutableStateFlow<LoginUiState>(LoginUiState.None)
//
//fun onLoginClick(username: String, password: String) {}

    fun onLoginClick(username: String, password: String) {

        //参数校验
        if (username.isBlank()) {
//            uiState.value = LoginUiState.ErrorRes(R.string.enter_phone_or_email)
            setUiStateErrorRes(R.string.enter_phone_or_email)
            return
        }

//        如果用户名
//        不是手机号也不是邮箱
//        就是格式错误
        if (!(SuperRegularUtil.isPhone(username) || SuperRegularUtil.isEmail(username))) {
            setUiStateErrorRes(R.string.error_username_format)
            return
        }

        if (TextUtils.isEmpty(password)) {
            setUiStateErrorRes(R.string.enter_password)
            return
        }

        //判断密码格式
        if (!StringUtil.isPassword(password)) {
            setUiStateErrorRes(R.string.error_password_format)
            return
        }

        val param = User(
            phone = if (SuperRegularUtil.isPhone(username)) username else "",
            email = if (SuperRegularUtil.isEmail(username)) username else "",
            password = password
        )
        login(param)
    }

    private fun setUiStateErrorRes(res: Int) {
        uiState.value = LoginUiState.ErrorRes(res)
    }

    private fun login(param: User) {
        viewModelScope.launch {
            sessionRepository.login(param)
                .asResult()
                .collectLatest {
                    if (it.isSuccess) {
                        Log.d("TAG", "login: ${it.getOrThrow().data}")

                        val result = it.getOrThrow()

                        //保存登录信息
//                        val sessionPreferences = result.data!!.toPreferences()
//                        userDataRepository.setSession(sessionPreferences)
//                        userDataRepository.setUser(result.data.user.toPreferences())

//                        MyApplication.instance.initAfterLogin(sessionPreferences!!)

                        uiState.value = LoginUiState.Success
                    } else {
                        uiState.value = LoginUiState.Error(it.exceptionOrNull()!!.localException())
                    }
                }
        }
    }

    fun resetUiState() {
        uiState.value = LoginUiState.None
    }

}