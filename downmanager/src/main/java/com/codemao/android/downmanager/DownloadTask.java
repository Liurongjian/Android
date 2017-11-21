package com.codemao.android.downmanager;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by rong on 2017/7/12.
 */

@Entity
public class DownloadTask {

	public final static int STATUS_INIT             = 0;
	public final static int STATUS_WAIT             = 1;
	public final static int STATUS_CONNECTING       = 2;
	public final static int STATUS_PAUSE            = 3;
	public final static int STATUS_CANCEL           = 4;
	public final static int STATUS_FINISH           = 6;
	public final static int STATUS_ERROR_NET        = 7;
	public final static int STATUS_ERROR_LINK       = 8;
	public final static int STATUS_ERROR_CHECK      = 9;
	final static int STATUS_PROGRESS_CHANGE         = 10;
	final static int STATUS_RESUME                  = 11;

	@Id(autoincrement = true)
	private Long _id;
	@Index
	private String url;
	private long downloadedSize;
	private int status;
	private String saveFileDir;
	private long size;
	@Transient
	private DownloadListener downloadListener;

	@Generated(hash = 295526682)
	public DownloadTask(Long _id, String url, long downloadedSize, int status,
									String saveFileDir, long size) {
					this._id = _id;
					this.url = url;
					this.downloadedSize = downloadedSize;
					this.status = status;
					this.saveFileDir = saveFileDir;
					this.size = size;
	}

	@Generated(hash = 1999398913)
	public DownloadTask() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getDownloadedSize() {
		return downloadedSize;
	}

	public void setDownloadedSize(long downloadedSize) {
		this.downloadedSize = downloadedSize;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSaveFileDir() {
		return saveFileDir;
	}

	public void setSaveFileDir(String saveFileDir) {
		this.saveFileDir = saveFileDir;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public DownloadListener getDownloadListener() {
		return downloadListener;
	}

	public void setDownloadListener(DownloadListener downloadListener) {
		this.downloadListener = downloadListener;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	@Override
	public int hashCode() {
		return url == null ? 0 : url.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return url == null ? false : url.equals(obj);
	}
}
