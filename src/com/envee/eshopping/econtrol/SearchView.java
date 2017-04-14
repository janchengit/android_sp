package com.envee.eshopping.econtrol;

import com.envee.eshopping.R;
import com.envee.eshopping.SearchActivity;
import com.envee.eshopping.SQLite.SearchSQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchView extends LinearLayout {
	private Context context;

	private TextView tvClear;
	private EditText etSearch;
	private TextView tvTip;
	private ImageView ivSearch;
	private ImageView ivSearchClose;

	private SearchListView listView;
	private BaseAdapter adapter;

	private SearchSQLiteOpenHelper helperSearchHistory;
	private SQLiteDatabase dbSearchHistory;

	private OnServiewClickListener onServiewClickListener;

	public SearchView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public OnServiewClickListener getOnServiewClickListener() {
		return onServiewClickListener;
	}

	public void setOnServiewClickListener(
			OnServiewClickListener onServiewClickListener) {
		this.onServiewClickListener = onServiewClickListener;
	}

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}

	private void init() {

		initView();

		helperSearchHistory = new SearchSQLiteOpenHelper(context);
		queryData("");

		// Close search activity
		ivSearchClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onServiewClickListener != null) {
					onServiewClickListener.onBackclicke();
				}
			}
		});

		// button 'clear dbSearchHistory'
		tvClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				deleteData();
				queryData("");
			}
		});

		// search text input changed call back
		etSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {

				if (s.toString().trim().length() != 0) {
					String tvString = etSearch.getText().toString();
					queryData(tvString);
				}
			}
		});

		// 'Enter' of the keyboard
		etSearch.setOnKeyListener(new View.OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER
						&& event.getAction() == KeyEvent.ACTION_DOWN) {

					// Hide keyboard
					((InputMethodManager) context
							.getSystemService(Context.INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(
									((SearchActivity) (context))
											.getCurrentFocus().getWindowToken(),
									InputMethodManager.HIDE_NOT_ALWAYS);

					String tvString = etSearch.getText().toString().trim();
					queryData(tvString);
					insertInputData(tvString);
					onServiewClickListener.onSearchclicke(tvString);

				}
				return false;
			}
		});

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				TextView textView = (TextView) view
						.findViewById(android.R.id.text1);
				String tvString = textView.getText().toString();
				// Hide keyboard
				((InputMethodManager) context
						.getSystemService(Context.INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(((SearchActivity) (context))
								.getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				etSearch.setText(tvString);
				queryData(tvString);

				onServiewClickListener.onSearchclicke(tvString);
			}
		});

		ivSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// Hide keyboard
				((InputMethodManager) context
						.getSystemService(Context.INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(((SearchActivity) (context))
								.getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);

				String tvString = etSearch.getText().toString().trim();
				queryData(tvString);
				insertInputData(tvString);
				onServiewClickListener.onSearchclicke(tvString);
			}
		});

	}

	private void initView() {
		LayoutInflater.from(context).inflate(R.layout.search_view, this);
		etSearch = (EditText) findViewById(R.id.etSearch);
		tvClear = (TextView) findViewById(R.id.tvClear);
		tvTip = (TextView) findViewById(R.id.tvTip);
		listView = (SearchListView) findViewById(R.id.listView);
		ivSearch = (ImageView) findViewById(R.id.ivSearch);
		ivSearchClose = (ImageView) findViewById(R.id.ivClose);
	}

	private void insertData(String tempName) {
		dbSearchHistory = helperSearchHistory.getWritableDatabase();
		dbSearchHistory.execSQL("insert into records(name) values('" + tempName
				+ "')");
		dbSearchHistory.close();
	}

	private void queryData(String tempName) {

		Cursor cursor = helperSearchHistory.getReadableDatabase().rawQuery(
				"select id as _id,name from records where name like '%"
						+ tempName + "%' order by id desc ", null);

		adapter = new SimpleCursorAdapter(context,
				android.R.layout.simple_list_item_1, cursor,
				new String[] { "name" }, new int[] { android.R.id.text1 },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	private boolean hasData(String tempName) {
		Cursor cursor = helperSearchHistory.getReadableDatabase().rawQuery(
				"select id as _id,name from records where name =?",
				new String[] { tempName });

		return cursor.moveToNext();
	}

	private void deleteData() {
		dbSearchHistory = helperSearchHistory.getWritableDatabase();
		dbSearchHistory.execSQL("delete from records");
		dbSearchHistory.close();
	}

	private void insertInputData(String string) {
		if (!hasData(string)) {
			insertData(string);
		}
	}

	public interface OnServiewClickListener {
		public void onSearchclicke(String string);

		public void onBackclicke();
	}

}
