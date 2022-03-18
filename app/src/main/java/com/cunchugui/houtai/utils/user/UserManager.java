/**
 *
 */
package com.cunchugui.houtai.utils.user;

import android.content.Context;

import com.cunchugui.houtai.model.LoginUser;


/**
 * 登陆用户信息管理类
 */
public class UserManager {

    private static UserManager mUserManager;
    private static Context mContext;

    private UserManager(Context ctx) {
        mContext = ctx;
    }

    public static UserManager getManager(Context ctx) {
        if (mUserManager == null) {
            mUserManager = new UserManager(ctx);
        }
        return mUserManager;
    }

    public String getAppToken() {
        return PreferenceHelper.getInstance(mContext).getString("app_token", "");
    }

    public String getUserId() {
        return PreferenceHelper.getInstance(mContext).getString("of_user_id", "");
    }

    public String getUserName() {
        return PreferenceHelper.getInstance(mContext).getString("user_name", "");
    }

    public String getUserImg() {
        return PreferenceHelper.getInstance(mContext).getString("user_img", "");
    }

    public String getInstId() {
        return PreferenceHelper.getInstance(mContext).getString("inst_id", "");
    }

    public String getSubsystemId() {
        return PreferenceHelper.getInstance(mContext).getString("subsystem_id", "");
    }

    //保存用户信息
    public void saveUser(LoginUser.DataBean user) {
        if (user != null) {
            PreferenceHelper.getInstance(mContext).putString("of_user_id", user.getOf_user_id());
            PreferenceHelper.getInstance(mContext).putString("app_token", user.getApp_token());
            PreferenceHelper.getInstance(mContext).putString("user_name", user.getUser_name());
            PreferenceHelper.getInstance(mContext).putString("user_img", user.getUser_img());
            PreferenceHelper.getInstance(mContext).putString("inst_id", user.getInst_id());
            PreferenceHelper.getInstance(mContext).putString("subsystem_id", user.getSubsystem_id());
        }
    }

    /**
     * 删除用户信息
     */
    public void removeUser() {
        PreferenceHelper.getInstance(mContext).removeKey("of_user_id");
        PreferenceHelper.getInstance(mContext).removeKey("app_token");
        PreferenceHelper.getInstance(mContext).removeKey("user_name");
        PreferenceHelper.getInstance(mContext).removeKey("user_img");
        PreferenceHelper.getInstance(mContext).removeKey("inst_id");
        PreferenceHelper.getInstance(mContext).removeKey("subsystem_id");
    }
}