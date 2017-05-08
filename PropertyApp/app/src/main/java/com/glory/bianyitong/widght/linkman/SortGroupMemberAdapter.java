package com.glory.bianyitong.widght.linkman;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;

import java.util.List;

public class SortGroupMemberAdapter extends BaseAdapter implements SectionIndexer {
    private Handler handler;
    private List<ContactBean> list = null;
    private Context mContext;

    public SortGroupMemberAdapter(Context mContext, List<ContactBean> list, Handler handler) {
        this.mContext = mContext;
        this.list = list;
        this.handler = handler;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<ContactBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final ContactBean mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.contact_list_item, null);
            viewHolder.alpha = (TextView) view.findViewById(R.id.alpha);
            viewHolder.name = (TextView) view.findViewById(R.id.contact_name);
            viewHolder.number = (TextView) view.findViewById(R.id.contact_number);
            viewHolder.ll_linkman = (LinearLayout) view.findViewById(R.id.ll_linkman);
            viewHolder.contact_list_line = (TextView) view.findViewById(R.id.contact_list_line);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.alpha.setVisibility(View.VISIBLE);
            viewHolder.alpha.setText(mContent.getPinyin());
        } else {
            viewHolder.alpha.setVisibility(View.GONE);
        }
        if(position == list.size() - 1){
            viewHolder.contact_list_line.setVisibility(View.GONE);
        }
        if(list != null && list.get(position) != null){
            if(list.get(position).getDesplayName()!=null){ //名称
                viewHolder.name.setText(this.list.get(position).getDesplayName());
            }
            if(list.get(position).getPhoneNum()!=null){ //电话
                viewHolder.number.setText(this.list.get(position).getPhoneNum());
            }

            viewHolder.ll_linkman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getPhoneNum() != null) {
                        // 电话 , 姓名
                        Database.contact_phone_name = list.get(position).getPhoneNum()+","+list.get(position).getDesplayName();
                    }
                    handler.sendEmptyMessage(0);
                }
            });
        }

        return view;

    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getPinyin().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getPinyin();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    final static class ViewHolder {
        TextView alpha;
        TextView name;
        TextView number;
        LinearLayout ll_linkman;
        TextView contact_list_line;
    }
}