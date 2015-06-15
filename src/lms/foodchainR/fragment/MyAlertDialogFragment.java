package lms.foodchainR.fragment;

import java.sql.SQLException;

import lms.foodchainR.R;
import lms.foodchainR.dao.DatabaseHelper;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.data.TableStyleData;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyAlertDialogFragment extends DialogFragment {
	public static final int CHOOSEPIC = 0;
	public static final int EDITCASESTYLE = 1;
	public static final int EDITTABLESTYLE = 2;
	private static int mNum;

	public static MyAlertDialogFragment choosePicInstance() {
		MyAlertDialogFragment f = new MyAlertDialogFragment();
		mNum = CHOOSEPIC;
		return f;
	}

	@SuppressLint("InflateParams")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new AlertDialog.Builder(getActivity());
		Dialog dialog = null;
		setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);
		switch (mNum) {
		case EDITCASESTYLE:
			final EditText etCs = (EditText) LayoutInflater.from(getActivity())
					.inflate(R.layout.casestyle_edit_dialog, null);
			dialog = builder.setView(etCs)
					.setPositiveButton("确定", new OnClickListener() {
						@SuppressWarnings("unchecked")
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (TextUtils.isEmpty(etCs.getText().toString())) {
								Toast.makeText(getActivity(), "",
										Toast.LENGTH_SHORT).show();
							} else {
								CaseStyleData csd = new CaseStyleData();
								csd.name = etCs.getText().toString();
								try {
									DatabaseHelper.getHelper(getActivity())
											.getDao(CaseStyleData.class)
											.createIfNotExists(csd);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						}
					}).setNegativeButton("取消", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).create();
			break;
		case EDITTABLESTYLE:
			View v = LayoutInflater.from(getActivity()).inflate(
					R.layout.tablestyle_edit_dialog, null);
			final EditText name = (EditText) v
					.findViewById(R.id.et_dialog_name);
			final EditText sCount = (EditText) v
					.findViewById(R.id.et_dialog_seatcount);
			final EditText tCount = (EditText) v
					.findViewById(R.id.et_dialog_tablecount);
			dialog = builder.setView(v)
					.setPositiveButton("确定", new OnClickListener() {
						@SuppressWarnings("unchecked")
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (TextUtils.isEmpty(name.getText().toString())
									|| TextUtils.isEmpty(sCount.getText()
											.toString())
									|| TextUtils.isEmpty(tCount.getText()
											.toString())) {
								Toast.makeText(getActivity(), "",
										Toast.LENGTH_SHORT).show();
							} else {
								TableStyleData tsd = new TableStyleData();
								tsd.name = name.getText().toString();
								tsd.seatCount = Integer.parseInt(sCount
										.getText().toString());
								tsd.tableCount = Integer.parseInt(tCount
										.getText().toString());
								try {
									DatabaseHelper.getHelper(getActivity())
											.getDao(TableStyleData.class)
											.createIfNotExists(tsd);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						}
					}).setNegativeButton("取消", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).create();
			break;
		default:
			dialog = super.onCreateDialog(savedInstanceState);
			break;
		}
		return dialog;
	}
}
