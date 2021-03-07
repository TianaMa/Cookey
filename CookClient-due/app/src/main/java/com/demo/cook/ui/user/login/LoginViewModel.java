package com.demo.cook.ui.user.login;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import com.demo.baselib.design.BaseViewModel;

import com.demo.basecode.ui.ToastyUtils;
import com.demo.cook.R;
import com.demo.cook.base.http.HttpCallback;
import com.demo.cook.base.http.HttpConfig;
import com.demo.cook.base.local.Storage;
import com.demo.cook.ui.user.model.HttpUserApi;
import com.demo.cook.ui.user.model.data.UserInfo;


public class LoginViewModel extends BaseViewModel {

    private HttpUserApi userApi = HttpConfig.getHttpServe(HttpUserApi.class);

    public String username;

    public String password;


    public void onLoginClick() {

        if(TextUtils.isEmpty(username)){
            ToastyUtils.show(R.string.text_login_user_hint);
            return;
        }

        if(TextUtils.isEmpty(password)){
            ToastyUtils.show(R.string.text_login_password_hint);
            return;
        }

        showLoading(R.string.text_loading_login);
        userApi.login(username,password).enqueue(new HttpCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo data) {
                Storage.setUserInfo(data);
                uiChange.loginSuccess.postValue(true);
            }

            @Override
            public void finallyCall() {
                super.finallyCall();
                closeLoading();
            }
        });
    }


    public ObservableUIChange uiChange =new ObservableUIChange();

    class ObservableUIChange{
        MutableLiveData<Boolean> loginSuccess=new MutableLiveData<>();

    }


}
