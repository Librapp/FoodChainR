package lms.foodchainR.fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.dao.Table_DBHelper;
import lms.foodchainR.data.OtherData;
import lms.foodchainR.data.Self;
import lms.foodchainR.data.TableStyleData;
import lms.foodchainR.util.BitmapUtils;
import lms.foodchainR.util.CommonUtils;
import lms.foodchainR.util.FileInfoUtils;
import lms.foodchainR.util.InfomationHelper;
import lms.foodchainR.util.MediaInfoUtils;
import lms.foodchainR.util.PicUtils;
import lms.foodchainR.util.SharePerformanceUtil;
import lms.foodchainR.widget.TableStyleAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.Loader.OnLoadCompleteListener;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class ManageFragment extends Fragment implements OnClickListener,
		OnItemLongClickListener {

	private RelativeLayout rdButtom;
	private LinearLayout rdMiddle;

	private ImageView logo;
	private ImageView bg;

	private TextView name;
	private TextView tel;
	private TextView sms;
	private TextView location;
	private TextView intro;
	private TextView noTableTip;

	private EditText nameE;
	private EditText telE;
	private EditText smsE;
	private EditText locationE;
	private EditText introE;

	private Button edit;
	private Button add;

	// 桌子类型列表
	private ListView tableList;
	// 桌子类型适配器
	private TableStyleAdapter tsa;
	private Table_DBHelper tdb;
	// 编辑餐厅信息
	private boolean modified = false;
	// 桌子类型位置
	private int whatItem = 0;
	private final int EDIT = 1;
	private final int DELETE = 2;

	private String theSmall = "";
	private Bitmap photoResult;

	private String FileDIR;

	private List<TableStyleData> tableStyleList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.restaurantdetail, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initViews();
		getData();
		super.onActivityCreated(savedInstanceState);
	}

	private void getData() {
		tdb = new Table_DBHelper(getActivity());
		tableStyleList = tdb.getTableStyleDataList();
		if (tableStyleList.size() > 0) {
			tsa = new TableStyleAdapter(getActivity(), tableStyleList);
			tableList.setAdapter(tsa);
			noTableTip.setVisibility(View.GONE);
			tableList.setVisibility(View.VISIBLE);
		} else {
			noTableTip.setVisibility(View.VISIBLE);
			tableList.setVisibility(View.GONE);
		}
	}

	private void initViews() {
		rdMiddle = (LinearLayout) getView().findViewById(R.id.rd_mid);
		rdButtom = (RelativeLayout) getView().findViewById(R.id.rd_bottom);
		FileDIR = OtherData.RDETAIL_PIC + "logo.jpg";
		logo = (ImageView) getView().findViewById(R.id.rd_logo);

		Drawable d = Drawable.createFromPath(FileDIR);
		if (d != null) {
			logo.setImageDrawable(d);
		}
		int w = CommonUtils.dip2px(getActivity(), 100);
		RelativeLayout.LayoutParams lp = new LayoutParams(w, w);
		logo.setLayoutParams(lp);
		logo.setScaleType(ScaleType.FIT_XY);
		logo.setClickable(false);
		logo.setOnClickListener(this);

		bg = (ImageView) getView().findViewById(R.id.rd_bg);
		bg.setOnClickListener(this);

		name = (TextView) getView().findViewById(R.id.rd_name_text);
		name.setText(Self.current().name);
		nameE = (EditText) getView().findViewById(R.id.rd_name_edit);

		tel = (TextView) getView().findViewById(R.id.rd_tel_num);
		tel.setText(Self.current().tel);
		telE = (EditText) getView().findViewById(R.id.rd_tel_edit);
		telE.setText(Self.current().tel);

		sms = (TextView) getView().findViewById(R.id.rd_sms_num);
		sms.setText(Self.current().sms);
		smsE = (EditText) getView().findViewById(R.id.rd_sms_edit);

		location = (TextView) getView().findViewById(R.id.rd_location_txt);
		location.setText(Self.current().address);
		locationE = (EditText) getView().findViewById(R.id.rd_location_edit);

		intro = (TextView) getView().findViewById(R.id.rd_intro_txt);
		intro.setText(Self.current().intro);
		introE = (EditText) getView().findViewById(R.id.rd_intro_edit);

		edit = (Button) getView().findViewById(R.id.rd_edit);
		edit.setOnClickListener(this);
		getView().findViewById(R.id.rd_employee).setOnClickListener(this);
		getView().findViewById(R.id.rd_vip).setOnClickListener(this);
		getView().findViewById(R.id.bill).setOnClickListener(this);
		getView().findViewById(R.id.store).setOnClickListener(this);

		add = (Button) getView().findViewById(R.id.rd_add_btn);
		add.setOnClickListener(this);
		noTableTip = (TextView) getView().findViewById(R.id.rd_notabledatatip);
		noTableTip.setText(Html.fromHtml("<u>" + "没有餐桌数据，请点此创建" + "</u>"));
		noTableTip.setOnClickListener(this);
		tableList = (ListView) getView().findViewById(R.id.rd_tablelist);
		tableList.setOnItemLongClickListener(this);
		tableList
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.add(0, EDIT, 0, "编辑");
						menu.add(0, DELETE, 0, "删除");
					}
				});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case EDIT:
			editTableStyle();
			break;
		case DELETE:
			deleteTableStyle();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rd_edit:
			if (modified) {
				if (!nameE.getText().toString().trim().equals("")) {
					Self.current().name = nameE.getText().toString();
					name.setText(Self.current().name);
				}
				nameE.setVisibility(View.GONE);
				name.setVisibility(View.VISIBLE);
				if (!telE.getText().toString().equals("")) {
					Self.current().tel = telE.getText().toString();
					tel.setText(Self.current().tel);
				}
				telE.setVisibility(View.GONE);
				tel.setVisibility(View.VISIBLE);
				if (!smsE.getText().toString().equals("")) {
					Self.current().sms = smsE.getText().toString();
					sms.setText(Self.current().sms);
				}
				smsE.setVisibility(View.GONE);
				sms.setVisibility(View.VISIBLE);
				if (!locationE.getText().toString().equals("")) {
					Self.current().address = locationE.getText().toString();
					location.setText(Self.current().address);
				}
				locationE.setVisibility(View.GONE);
				location.setVisibility(View.VISIBLE);
				if (!introE.getText().toString().equals("")) {
					Self.current().intro = introE.getText().toString();
					intro.setText(Self.current().intro);
				}
				introE.setVisibility(View.GONE);
				intro.setVisibility(View.VISIBLE);
				rdMiddle.setVisibility(View.VISIBLE);
				rdButtom.setVisibility(View.VISIBLE);
				// TODO
				SharePerformanceUtil.saveLInfo(getActivity());
				logo.setClickable(false);
				edit.setText("编辑");
				modified = false;
			} else {
				rdMiddle.setVisibility(View.GONE);
				rdButtom.setVisibility(View.GONE);
				name.setVisibility(View.GONE);
				nameE.setText(Self.current().name);
				nameE.setVisibility(View.VISIBLE);
				tel.setVisibility(View.GONE);
				telE.setVisibility(View.VISIBLE);
				sms.setVisibility(View.GONE);
				smsE.setText(Self.current().sms);
				smsE.setVisibility(View.VISIBLE);
				location.setVisibility(View.GONE);
				locationE.setText(Self.current().address);
				locationE.setVisibility(View.VISIBLE);
				intro.setVisibility(View.GONE);
				introE.setText(Self.current().intro);
				introE.setVisibility(View.VISIBLE);

				logo.setClickable(true);
				edit.setText("完成");
				modified = true;
			}
			break;
		case R.id.rd_employee:
			Toast.makeText(getActivity(), "正在开发", Toast.LENGTH_SHORT).show();
			// Intent iE = new Intent(getActivity(), EmployeeActivity.class);
			// startActivity(iE);
			break;
		case R.id.rd_vip:
			Toast.makeText(getActivity(), "正在开发", Toast.LENGTH_SHORT).show();
			break;
		case R.id.bill:
			Toast.makeText(getActivity(), "正在开发", Toast.LENGTH_SHORT).show();
			break;
		case R.id.store:
			Toast.makeText(getActivity(), "正在开发", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rd_logo:
			CharSequence[] items = { "相册", "拍照" };
			ChooseImage(items);
			break;
		case R.id.rd_notabledatatip:
			createNewTableStyle();
			break;
		case R.id.rd_add_btn:
			createNewTableStyle();
			break;
		default:
			break;
		}
	}

	private void createNewTableStyle() {
		MyDialogFragment.tableStyleInstance("", 4, 10).show(
				getChildFragmentManager(), "dialog");
	}

	private void editTableStyle() {
		TableStyleData ts = tableStyleList.get(whatItem);
		MyDialogFragment.tableStyleInstance(ts.id, ts.count, ts.seatCount)
				.show(getChildFragmentManager(), "dialog");
	}

	private void deleteTableStyle() {

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		whatItem = arg2;
		return false;
	}

	public void ChooseImage(CharSequence[] items) {
		AlertDialog imageDialog = new AlertDialog.Builder(getActivity())
				.setTitle("请选择图片")
				.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						if (item == 0) {
							Intent intent = new Intent(
									Intent.ACTION_GET_CONTENT);
							intent.setType(PicUtils.IMAGE_UNSPECIFIED);
							startActivityForResult(intent, PicUtils.PHOTO);
						} else if (item == 1) {
							Intent intent = new Intent(
									"android.media.action.IMAGE_CAPTURE");

							String fileName = "FCRc.tmp";
							File camerFile = new File(InfomationHelper
									.getCamerPath(), fileName);
							Toast.makeText(getActivity(),
									InfomationHelper.getCamerPath(),
									Toast.LENGTH_SHORT).show();

							theSmall = InfomationHelper.getCamerPath()
									+ fileName;

							Uri originalUri = Uri.fromFile(camerFile);
							intent.putExtra(MediaStore.EXTRA_OUTPUT,
									originalUri);
							startActivityForResult(intent, PicUtils.CAMERA);
						}
					}
				}).create();
		imageDialog.show();
	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, PicUtils.IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PicUtils.PHOTORESULT);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case PicUtils.PHOTORESULT:
				Bundle extras = data.getExtras();
				if (extras != null) {
					photoResult = extras.getParcelable("data");
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					photoResult.compress(Bitmap.CompressFormat.JPEG, 100,
							stream);
					BitmapUtils.saveBitmap(OtherData.RDETAIL_PIC, "logo.jpg",
							photoResult);
					logo.setImageBitmap(photoResult);
				}
				break;
			case PicUtils.CAMERA:
				// 设置原始图像
				photoResult = InfomationHelper.getFinalScaleBitmap(
						getActivity(), theSmall);
				logo.setImageBitmap(photoResult);

				Uri uri = Uri.fromFile(new File(theSmall));
				startPhotoZoom(uri);
				break;
			case PicUtils.PHOTO:
				if (data == null)
					return;

				Uri thisUri = data.getData();
				try {
					photoResult = MediaStore.Images.Media.getBitmap(
							getActivity().getContentResolver(), thisUri);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] proj = { MediaStore.Images.Media.DATA };
				CursorLoader loader = new CursorLoader(getActivity(), thisUri,
						proj, null, null, null);
				loader.registerListener(0,
						new OnLoadCompleteListener<Cursor>() {

							@Override
							public void onLoadComplete(Loader<Cursor> loader,
									Cursor data) {
								if (data != null) {
									int column_index = data
											.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
									if (data.getCount() > 0
											&& data.moveToFirst()) {
										theSmall = data.getString(column_index);
										String attFormat = FileInfoUtils
												.getFileFormat(theSmall);
										if (!"photo".equals(MediaInfoUtils
												.getContentType(attFormat))) {
											Toast.makeText(getActivity(),
													"请选择图片文件",
													Toast.LENGTH_SHORT).show();
											return;
										}
										// 设置原始图片
										photoResult = InfomationHelper
												.getFinalScaleBitmap(
														getActivity(), theSmall);
										logo.setImageBitmap(photoResult);

										Uri uri1 = Uri.fromFile(new File(
												theSmall));
										startPhotoZoom(uri1);
									}
								}
							}
						});
				loader.startLoading();
				break;
			default:
				break;
			}
		} else {

		}
	}
}
