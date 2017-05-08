package com.glory.bianyitong.ui.activity;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.widght.linkman.CharacterParser;
import com.glory.bianyitong.widght.linkman.ClearEditText;
import com.glory.bianyitong.widght.linkman.ContactBean;
import com.glory.bianyitong.widght.linkman.PinyinComparator;
import com.glory.bianyitong.widght.linkman.SideBar;
import com.glory.bianyitong.widght.linkman.SortGroupMemberAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * 联系人列表
 * 
 * @author Administrator
 * 
 */
public class ContactListActivity extends Activity implements SectionIndexer {

	RelativeLayout left_return_btn;
	TextView title_ac_text;
	TextView iv_title_text_right;

	private SortGroupMemberAdapter adapter;
	private ListView contactList;
	private List<ContactBean> SourceDateList;
	private AsyncQueryHandler asyncQueryHandler; // 异步查询数据库类对象

	private TextView fast_position;

	private Map<Integer, ContactBean> contactIdMap = null;

	private ClearEditText mClearEditText;

	private TextView tvNofriends;
	private SideBar sideBar;
	private Handler cc_handler;

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list_view);
		left_return_btn = (RelativeLayout) findViewById(R.id.left_return_btn);
		title_ac_text = (TextView) findViewById(R.id.title_ac_text);
		iv_title_text_right = (TextView) findViewById(R.id.iv_title_text_right);
		iv_title_text_right.setVisibility(View.GONE);
		title_ac_text.setText(getString(R.string.contacts));//通讯录
		left_return_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ContactListActivity.this.finish();
			}
		});

		contactList = (ListView) findViewById(R.id.contact_list);

		// 实例化
		asyncQueryHandler = new MyAsyncQueryHandler(getContentResolver());
		init();
		initview();
		cc_handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
					case 0:
						ContactListActivity.this.finish();
						break;
				}
			}
		};
	}
	private void initview(){
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
;
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});


	}
	/**
	 * 初始化数据库查询参数
	 */
	private void init() {
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI; // 联系人Uri；
		// 查询的字段
		String[] projection = { ContactsContract.CommonDataKinds.Phone._ID,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.DATA1, "sort_key",
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
				ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
				ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY };
		// 按照sort_key升序查詢
		asyncQueryHandler.startQuery(0, null, uri, projection, null, null,
				"sort_key COLLATE LOCALIZED asc");

	}

	private void setAdapter(List<ContactBean> list) {

		tvNofriends = (TextView) this.findViewById(R.id.title_layout_no_friends);
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		sideBar = (SideBar) findViewById(R.id.sidrbar);

		fast_position = (TextView) findViewById(R.id.fast_position);
		sideBar.setTextView(fast_position);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					contactList.setSelection(position);
				}

			}
		});
		SourceDateList = filledData(list);
		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortGroupMemberAdapter(this, SourceDateList, cc_handler);
		contactList.setAdapter(adapter);


	}

	/**
	 * 为ListView填充数据
	 *
	 * @param --date
	 * @return
	 */
	private List<ContactBean> filledData(List<ContactBean> date) { //     ArrayList<String> date
		List<ContactBean> mSortList = new ArrayList<>();

		for (int i = 0; i < date.size(); i++) {
			ContactBean sortModel = new ContactBean();
			sortModel.setDesplayName(date.get(i).getDesplayName());
			sortModel.setPhoneNum(date.get(i).getPhoneNum());
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date.get(i).getDesplayName()); //     date.get(i)
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setPinyin(sortString.toUpperCase());
			} else {
				sortModel.setPinyin("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 *
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<ContactBean> filterDateList = new ArrayList<>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
			tvNofriends.setVisibility(View.GONE);
		} else {
			filterDateList.clear();
			for (ContactBean sortModel : SourceDateList) {
				String name = sortModel.getDesplayName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
						filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
		if (filterDateList.size() == 0) {
			tvNofriends.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		if (SourceDateList != null && SourceDateList.get(position) != null &&
				SourceDateList.get(position).getPinyin()!=null) {
			return SourceDateList.get(position).getPinyin().charAt(0);
		}
		return 0;
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < SourceDateList.size(); i++) {
			String sortStr = SourceDateList.get(i).getPinyin();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	/**
	 *
	 * @author Administrator
	 *
	 */
	private class MyAsyncQueryHandler extends AsyncQueryHandler {

		public MyAsyncQueryHandler(ContentResolver cr) {
			super(cr);
		}

		@Override
		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
			if (cursor != null && cursor.getCount() > 0) {
				contactIdMap = new HashMap<Integer, ContactBean>();
				SourceDateList = new ArrayList<ContactBean>();
				cursor.moveToFirst(); // 游标移动到第一项
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					String name = cursor.getString(1);
					String number = cursor.getString(2);
					String sortKey = cursor.getString(3);
					int contactId = cursor.getInt(4);
					Long photoId = cursor.getLong(5);
					String lookUpKey = cursor.getString(6);

					if (contactIdMap.containsKey(contactId)) {
						// 无操作
					} else {
						// 创建联系人对象
						ContactBean contact = new ContactBean();
						contact.setDesplayName(name);
						contact.setPhoneNum(number);
						contact.setSortKey(sortKey);
						contact.setPhotoId(photoId);
						contact.setLookUpKey(lookUpKey);
						SourceDateList.add(contact);

						contactIdMap.put(contactId, contact);
					}
				}
				if (SourceDateList.size() > 0) {
					setAdapter(SourceDateList);
				}
			}

			super.onQueryComplete(token, cookie, cursor);
		}

	}

}
