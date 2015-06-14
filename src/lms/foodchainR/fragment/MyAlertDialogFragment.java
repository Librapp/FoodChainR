package lms.foodchainR.fragment;

import java.io.File;
import java.sql.SQLException;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.R;
import lms.foodchainR.dao.DatabaseHelper;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.data.UserData;
import lms.foodchainR.ui.DetailActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

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

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new AlertDialog.Builder(getActivity());
		Dialog dialog = null;
		setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);
		switch (mNum) {
		case CHOOSEPIC:
			CharSequence[] c = { "拍照", "相册" };
			dialog = builder.setTitle("选择图片")
					.setItems(c, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case 0:
								Intent intent = new Intent(
										Intent.ACTION_GET_CONTENT);
								intent.setType("image/*");
								getActivity().startActivityForResult(intent,
										DetailActivity.GETIMAGE_BYSDCARD);
								break;
							case 1:
								Intent iPic = new Intent(
										"android.media.action.IMAGE_CAPTURE");
								String fileName = "camera" + ".tmp";

								File fileDir = new File(
										FoodchainRApplication.TEMP_FOLDER
												+ FoodchainRApplication.CAMERA);
								if (!fileDir.exists()) {
									fileDir.mkdir();
								}

								File camerFile = new File(
										FoodchainRApplication.TEMP_FOLDER
												+ FoodchainRApplication.CAMERA,
										fileName);
								UserData.self().headPic = camerFile
										.getAbsolutePath();
								Uri originalUri = Uri.fromFile(camerFile);
								iPic.putExtra(MediaStore.EXTRA_OUTPUT,
										originalUri);
								startActivityForResult(iPic,
										DetailActivity.GETIMAGE_BYCAMERA);
								break;
							default:
								break;
							}
						}
					}).create();
			break;
		case EDITCASESTYLE:
			final EditText et = (EditText) LayoutInflater.from(getActivity())
					.inflate(R.layout.editcasestyle, null);
			et.setHint("请输入类型名称");
			dialog = builder.setView(et)
					.setPositiveButton("确定", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if (TextUtils.isEmpty(et.getText().toString())) {
								Toast.makeText(getActivity(), "",
										Toast.LENGTH_SHORT).show();
							} else {
								CaseStyleData csd = new CaseStyleData();
								csd.name = et.getText().toString();
								OrmLiteSqliteOpenHelper helper = OpenHelperManager
										.getHelper(getActivity()
												.getBaseContext(),
												DatabaseHelper.class);
								try {
									helper.getDao(CaseStyleData.class)
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

			break;
		default:
			dialog = super.onCreateDialog(savedInstanceState);
			break;
		}
		return dialog;
	}
}
