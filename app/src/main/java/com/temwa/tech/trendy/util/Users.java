package com.temwa.tech.trendy.util;

import android.net.Uri;

import com.temwa.tech.trendy.R;
import com.temwa.tech.trendy.model.User;

public class Users {
    public User[] USERS = {
            TrendyTester
    };


    private static final User TrendyTester = new User(Uri.parse("android.resource://com.temwa.tech.trendy/" + R.drawable.user_account_avatar).toString(),
            "Trendy Tester", "0","0",  "Drinks, Eating", "New Member");

}
