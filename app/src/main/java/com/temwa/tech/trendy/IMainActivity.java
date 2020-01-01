package com.temwa.tech.trendy;

import com.temwa.tech.trendy.model.AppNotification;
import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.model.User;

public interface IMainActivity {
    void inflateViewProfileFragment(User user);

    void inflateViewTrendyDealFragment(TrendyDeal message);

    void onNotificationSelected(AppNotification message);
}
