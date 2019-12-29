package com.temwa.tech.trendy.util;

import android.net.Uri;

import com.temwa.tech.trendy.R;
import com.temwa.tech.trendy.model.TrendyDeal;

public class TrendyDeals {
    public TrendyDeal[] DEALS = {
            Latitude15, RadisonBlu, NuMetro, Granddaddys
    };


    public static final TrendyDeal Latitude15 = new TrendyDeal(Uri.parse("android.resource://com.temwa.tech.trendy/" + R.drawable.latitude15).toString(),
            "Latitude 15 Hotel", "5 stars","23 reviews",  "Avg: K230 per meal");

    public static final TrendyDeal RadisonBlu = new TrendyDeal(Uri.parse("android.resource://com.temwa.tech.trendy/" + R.drawable.radissonblu).toString(),
            "Radisson Blu Hotel", "5 stars","23 reviews", "Avg: K230 per meal");

    public static final TrendyDeal NuMetro = new TrendyDeal(Uri.parse("android.resource://com.temwa.tech.trendy/" + R.drawable.numetro).toString(),
            "NuMetro Cinemas", "5 stars","23 reviews", "Avg: K50 per ticket");

    public static final TrendyDeal Granddaddys = new TrendyDeal(Uri.parse("android.resource://com.temwa.tech.trendy/" + R.drawable.granddaddys).toString(),
            "Granddaddys Shoka Nyama", "5 stars","23 reviews",  "Avg: K20 per drink");
}
