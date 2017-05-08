package com.glory.bianyitong.ui.adapter;

import android.content.Context;


import com.google.gson.internal.LinkedHashTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.util.ViewHolders;

import java.util.List;

/**
 * Created by lucy on 2016/11/10.
 */
public class VillageNameAdapter extends CommonAdapter<LinkedHashTreeMap<String, Object>> {
    public VillageNameAdapter(Context context, List<LinkedHashTreeMap<String, Object>> mDatas) {
        super(context, mDatas, R.layout.popspn_item);
    }

    @Override
    public void convert(ViewHolders holder, LinkedHashTreeMap<String, Object> map) {
        holder.setText(R.id.tv_villageName, map.get("text").toString());

    }
}
