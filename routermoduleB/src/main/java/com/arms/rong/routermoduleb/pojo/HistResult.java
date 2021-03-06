package com.arms.rong.routermoduleb.pojo;

/**
 * Created by rong on 2017/5/23.
 *
 * {
 "_id":"20101001m2",
 "title":"西安大明宫国家遗址公园开园",
 "pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201110/2/6D83516539.jpg",
 "year":2010,
 "month":10,
 "day":1,
 "des":"2010年10月1日 (农历八月廿四)，西安大明宫国家遗址公园开园。",
 "lunar":"庚寅年八月廿四"
 }
 */

public class HistResult {

	private String _id;
	private String title;
	private String pic;
	private int year;
	private int month;
	private int day;
	private String des;
	private String lunar;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getLunar() {
		return lunar;
	}

	public void setLunar(String lunar) {
		this.lunar = lunar;
	}
}
