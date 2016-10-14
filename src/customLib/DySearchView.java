package customLib;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.leo.androidtest.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class DySearchView extends SearchView {

	private View searchPlate;
	private View submitArea;

	private AutoCompleteTextView editText;

	private ImageView searchButton;
	private ImageView submitButton;
	private ImageView closeButton;
	private ImageView hintSearchIcon;

	private static final String TAG = "DiySeachView";

	public DySearchView(Context context) {
		super(context);
		init();
	}
	
	public DySearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * ��ʼ������ȡ���ָ���˽�г�Ա
	 */
	private void init() {
		if (21 < VERSION.SDK_INT)
			return;
		try {
			Field plate = getDeclaredField(this, "mSearchPlate");
			plate.setAccessible(true);
			searchPlate = (View) plate.get(this);

			Field submitf = getDeclaredField(this, "mSubmitArea");
			submitf.setAccessible(true);
			submitArea = (View) submitf.get(this);

			Field hint = getDeclaredField(this, "mQueryTextView");
			hint.setAccessible(true);
			editText = (AutoCompleteTextView) hint.get(this);

			Field search = getDeclaredField(this, "mSearchButton");
			search.setAccessible(true);
			searchButton = (ImageView) search.get(this);

			Field submit = getDeclaredField(this, "mSubmitButton");
			submit.setAccessible(true);
			submitButton = (ImageView) submit.get(this);

			Field close = getDeclaredField(this, "mCloseButton");
			close.setAccessible(true);
			closeButton = (ImageView) close.get(this);

			Field hintSearch = getDeclaredField(this, "mSearchHintIcon");
			hintSearch.setAccessible(true);
			hintSearchIcon = (ImageView) hintSearch.get(this);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * ����������OnEditorActionListener������������Ӧ����֮ǰ��Ӧ����
		 */
		if (editText != null) {
			editText.setOnEditorActionListener(new OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId,
						KeyEvent event) {
					Log.i(TAG, "EditorAction search");
					if (checkQueryText()) {
						submitQuery();
					}
					return true;
				}
			});
		}

	}

	/**
	 * ��ȡ��ִ�и����onSubmitQuery ����
	 */
	private void submitQuery() {
		try {
			Method submitQuery = this.getClass().getSuperclass()
					.getDeclaredMethod("onSubmitQuery");
			submitQuery.setAccessible(true);
			submitQuery.invoke(this);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��д�����setSubmitButtonEnabled �������޸��ύ��ť���¼��� ��ִ���ύ�¼�֮ǰ��������Ƿ�Ϊ�գ�
	 * �ǣ��򲥷��𶯶���������ʾ�û� �������ؼ��̣���ִ�и���onSubmitQuery����
	 */
	@Override
	public void setSubmitButtonEnabled(boolean enabled) {
		super.setSubmitButtonEnabled(enabled);
		if (submitButton == null) return;
		if (enabled) {
			submitButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (checkQueryText()) {
						submitQuery();
					}
				}
			});
		} else {
			Field onClickListener = getDeclaredField(this, "mOnClickListener");
			onClickListener.setAccessible(true);
			try {
				submitButton
				.setOnClickListener((OnClickListener) onClickListener
						.get(this));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ������������Ƿ�Ϊ�գ�Ϊ�գ��򲥷��𶯶������������ؼ���
	 * 
	 * @return ���������Ƿ�Ϊ��
	 */
	private boolean checkQueryText() {
		if (getQuery() == null || TextUtils.getTrimmedLength(getQuery()) == 0) {
			Animation anim = AnimationUtils.loadAnimation(getContext(),
					R.anim.shack_search);
			startAnimation(anim);
			return false;
		} else {
			/* ��������� */
			InputMethodManager imm = (InputMethodManager) editText.getContext()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm.isActive()) {
				imm.hideSoftInputFromWindow(
						editText.getApplicationWindowToken(), 0);
			}
		}
		return true;
	}

	/**
	 * ����searchView�����
	 * 
	 * @param plateRes
	 *            �����򱳾���Դid
	 * @param submitRes
	 *            ������ť������Դid
	 * @param color
	 *            ������ɫ
	 * @param hintColor
	 *            hint������ɫ
	 * @param searchBtn
	 *            ������ťͼƬ��Դid
	 * @param submitBtn
	 *            �ύ��ťͼƬ��Դid
	 * @param closeBtn
	 *            �رհ�ťͼƬ��Դid
	 */
	public void setResouce(int plateRes, int submitRes, int color,
			int hintColor, int searchBtn, int submitBtn, int closeBtn) {
		if (21 < VERSION.SDK_INT)
			return;
		searchPlate.setBackgroundResource(plateRes);
		submitArea.setBackgroundResource(submitRes);
		editText.setTextColor(color);
		editText.setHintTextColor(hintColor);

		searchButton.setImageResource(searchBtn);
		submitButton.setImageResource(submitBtn);
		closeButton.setImageResource(closeBtn);
		hintSearchIcon.setImageResource(searchBtn);

		setHintIcon(searchBtn);
	}

	/**
	 * ����hint���ֵ�����ͼƬ��Դ
	 * 
	 * @param icon
	 */
	private void setHintIcon(int icon) {
		Field hintId = getDeclaredField(this, "mSearchIconResId");
		if (hintId != null) {
			hintId.setAccessible(true);
			try {
				hintId.set(this, icon);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		try {
			Class<?> clazz = Class
					.forName("android.widget.SearchView$SearchAutoComplete");

			Field hint = getDeclaredField(this, "mQueryHint");
			hint.setAccessible(true);

			SpannableStringBuilder stopHint = new SpannableStringBuilder("   ");
			stopHint.append((CharSequence) hint.get(this));

			// Add the icon as an spannable
			Drawable searchIcon = getResources().getDrawable(icon);
			Method textSizeMethod = clazz.getMethod("getTextSize");
			Float rawTextSize = (Float) textSizeMethod.invoke(editText);
			int textSize = (int) (rawTextSize * 1.25);
			searchIcon.setBounds(0, 0, textSize, textSize);
			stopHint.setSpan(new ImageSpan(searchIcon), 1, 2,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			// Set the new hint text
			Method setHintMethod = clazz.getMethod("setHint",
					CharSequence.class);
			setHintMethod.invoke(editText, stopHint);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ѭ������ת��, ��ȡ����� DeclaredField
	 * 
	 * @param object
	 *            : �������
	 * @param fieldName
	 *            : �����е�������
	 * @return �����е����Զ���
	 */

	public Field getDeclaredField(Object object, String fieldName) {
		Field field = null;
		Class<?> clazz = object.getClass();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				return field;
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
			}
		}
		return null;
	}
}
