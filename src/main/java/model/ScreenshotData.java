package model;

import java.io.Serializable;

public class ScreenshotData implements Serializable {
	private String screenshotDataId;
	private String empId;
	private String screenshotDataPath;
	private String screenshotDataDatetime;

	public ScreenshotData() {
		super();
	}

	public ScreenshotData(String screenshotDataId, String empId, String screenshotDataPath,
			String screenshotDataDatetime) {
		super();
		this.screenshotDataId = screenshotDataId;
		this.empId = empId;
		this.screenshotDataPath = screenshotDataPath;
		this.screenshotDataDatetime = screenshotDataDatetime;
	}

	public String getScreenshotDataId() {
		return screenshotDataId;
	}

	public void setScreenshotDataId(String screenshotDataId) {
		this.screenshotDataId = screenshotDataId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getScreenshotDataPath() {
		return screenshotDataPath;
	}

	public void setScreenshotDataPath(String screenshotDataPath) {
		this.screenshotDataPath = screenshotDataPath;
	}

	public String getScreenshotDataDatetime() {
		return screenshotDataDatetime;
	}

	public void setScreenshotDataDatetime(String screenshotDataDatetime) {
		this.screenshotDataDatetime = screenshotDataDatetime;
	}
}
