package lms.foodchainR.fragment;

import java.io.File;

import lms.foodchainR.data.Constants;
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

public class MyAlertDialogFragment extends DialogFragment {
	public static final int CHOOSEPIC = 0;
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
		switch (mNum) {
		case CHOOSEPIC:
			setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);
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

								File fileDir = new File(Constants.TEMP_FOLDER
										+ Constants.CAMERA);
								if (!fileDir.exists()) {
									fileDir.mkdir();
								}

								File camerFile = new File(Constants.TEMP_FOLDER
										+ Constants.CAMERA, fileName);
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
		default:
			dialog = super.onCreateDialog(savedInstanceState);
			break;
		}
		return dialog;
	}
}
