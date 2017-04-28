package com.envee.eshopping.data;

import java.io.File;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.storage.OnObbStateChangeListener;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;

public class ObbManager {
	private static final String TAG = "ObbManager";

	private Context context = null;
	
	private static ObbManager obbmanager = null;
		
	private static final boolean DEBUG_LOG = true;

	private String obbMountPath = "";

	private boolean obbMounted = false;

	public static ObbManager getInstance(Context context) {
		
		if (obbmanager == null && context != null) {
			obbmanager = new ObbManager(context);
		}
		
		return obbmanager;
	}
	
	public ObbManager(Context context) {
		this.context = context;
		init();
	}

	public String getObbFilePath() {
		String pckName = context.getPackageName();
		PackageInfo packinfo = null;
		int versionName = 1;
		
		try {
			packinfo = context.getPackageManager().getPackageInfo(pckName, 0);
			versionName = packinfo.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = context.getObbDir();
		if (file == null) {
			return "";
		} else {
			return file.getPath() + "/main." + versionName + "." + pckName + ".obb";
		}
	}

	public void init() {
		String expansionFilePath = "";
		String filePath = getObbFilePath();
		if (TextUtils.isEmpty(filePath)) {
			setObbMounted(false);
			return;
		}
		final File obbFile = new File(filePath);

		if (DEBUG_LOG) {
			Log.e(TAG, "mainFile.getAbsolutePath()" + obbFile.getAbsolutePath()
					+ "  obbFile.exists()=" + obbFile.exists());
		}
		StorageManager storageManager = (StorageManager) context
				.getSystemService(Context.STORAGE_SERVICE);
		if (!storageManager.isObbMounted(obbFile.getAbsolutePath())) {
			if (obbFile.exists()) {
				if (!storageManager.mountObb(obbFile.getAbsolutePath(), null,
						obbStateChangeListener)) {
					Log.e(TAG, "Mount call was failed queued");
					setObbMounted(false);
				}
			} else {
				Log.e(TAG, "obb file not found");
				setObbMounted(false);
			}
		} else {
			expansionFilePath = storageManager.getMountedObbPath(obbFile
					.getAbsolutePath());
			if (DEBUG_LOG) {
				Log.e(TAG, "expansionFilePath: " + expansionFilePath
						+ " Already Mounted!!");
			}
			setObbMountPath(expansionFilePath);
			setObbMounted(true);
		}
	}

	public String getObbMountPath() {
		return obbMountPath;
	}

	public void setObbMountPath(String obbMountPath) {
		this.obbMountPath = obbMountPath;
	}

	public boolean isObbMounted() {
		return obbMounted;
	}

	public void setObbMounted(boolean obbMounted) {
		this.obbMounted = obbMounted;
	}

	private OnObbStateChangeListener obbStateChangeListener = new OnObbStateChangeListener() {

		@Override
		public void onObbStateChange(String path, int state) {
			// TODO Auto-generated method stub
			super.onObbStateChange(path, state);
			StorageManager storageManager = (StorageManager) context
					.getSystemService(Context.STORAGE_SERVICE);

			String obbPath = storageManager.getMountedObbPath(path);
			if (state == OnObbStateChangeListener.MOUNTED) {
				obbPath = storageManager.getMountedObbPath(path);
				if (DEBUG_LOG) {
					Log.e(TAG, "onObbStateChange obbPath =" + obbPath
							+ "-->MOUNTED" + " path = " + path + "  state="
							+ state);
				}
				setObbMountPath(obbPath);
				setObbMounted(true);
			} else {
				if (DEBUG_LOG) {
					Log.e(TAG, "onObbStateChange UNMOUNTED  " + state);
				}
				setObbMounted(false);
			}
		}
	};

}
