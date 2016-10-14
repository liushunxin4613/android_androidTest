package dialog;

import com.leo.androidtest.R;

import base.adapter.DialogItemAdapter;
import base.dialog.BaseDialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 单选对话框
 * 
 * @author HeJie
 *
 */
public class SingleChoiceDialog extends BaseDialog {

	private ListView listView;

	private DialogItemAdapter adapter;

	private int checkedItem;

	private String[] itemStr;

	private OnItemSelectedListener onItemSelectedListener;

	private static final String KEY_CHOICE_ITEM = "choiceItem";
	private static final String KEY_CHECKED_ITEM = "checkedItemStr";

	public SingleChoiceDialog(Context context, String title, int checkedItem,
			int itemsId) {
		super(context, R.style.Style_Dialog_Center);
		this.checkedItem = checkedItem;
		this.title = title;
		this.itemStr = context.getResources().getStringArray(itemsId);
		this.theme = R.style.DialogTheme_Center;
	}

	public SingleChoiceDialog(Context context, String title, int checkedItem,
			int itemsId, String posText, String nevText) {
		super(context, R.style.Style_Dialog_Center);
		this.checkedItem = checkedItem;
		this.title = title;
		this.itemStr = context.getResources().getStringArray(itemsId);
		this.positiveText = posText;
		this.nevigativeText = nevText;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			itemStr = savedInstanceState.getStringArray(KEY_CHOICE_ITEM);
			checkedItem = savedInstanceState.getInt(KEY_CHECKED_ITEM);
		}
		setContentView(R.layout.dialog_single_choice);
		listView = (ListView) findViewById(R.id.dialog_listview);
		titleView = (TextView) findViewById(R.id.dialog_title);
		LinearLayout buttonLy = (LinearLayout)findViewById(R.id.dialog_botton_container);
		positiveBtn = (Button) findViewById(R.id.dialog_positive_button);
		nevigativeBtn = (Button) findViewById(R.id.dialog_nevigative_button);
		
		listView.setSelection(checkedItem);
		adapter = new DialogItemAdapter(context, itemStr, checkedItem);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				if (checkedItem == position)
//					return;
				checkedItem = position;
				adapter.setSelectedItem(position);
				if (onItemSelectedListener != null)
					onItemSelectedListener.onItemSelected(position);
				SingleChoiceDialog.this.dismiss();
			}
		});
		if (title != null)
			titleView.setText(title);
		if (positiveText != null || nevigativeText != null) {
			buttonLy.setVisibility(View.VISIBLE);
			positiveBtn.setText(positiveText);
			nevigativeBtn.setText(nevigativeText);
			positiveBtn.setOnClickListener(this);
			nevigativeBtn.setOnClickListener(this);
		}
		
	}
	
	@Override
	public Bundle onSaveInstanceState() {
		Bundle savedInstanceState = new Bundle();
		savedInstanceState.putStringArray(KEY_CHOICE_ITEM, itemStr);
		savedInstanceState.putInt(KEY_CHECKED_ITEM, checkedItem);
		return savedInstanceState;
	}
	
	public OnItemSelectedListener getOnItemSelectedListener() {
		return onItemSelectedListener;
	}

	public void setOnItemSelectedListener(
			OnItemSelectedListener onItemSelectedListener) {
		this.onItemSelectedListener = onItemSelectedListener;
	}

	public int getSelectedItem() {
		return listView.getSelectedItemPosition();
	}

	public interface OnItemSelectedListener {
		/**
		 * 当用户点击选项时调用
		 * 
		 * @param pos
		 *            选项的位置，从0开始
		 */
		public void onItemSelected(int pos);
	}
}
