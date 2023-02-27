package model;

import java.io.Serializable;

public class MotionData implements Serializable {
	private String motionDataId;
	private String empId;
	private String motionData;
	private String motionDataSectionCode;
	private String motoinDataDatetime;

	public MotionData() {
		super();
	}

	public MotionData(String motionDataId, String empId, String motionData, String motionDataSectionCode,
			String motoinDataDatetime) {
		super();
		this.motionDataId = motionDataId;
		this.empId = empId;
		this.motionData = motionData;
		this.motionDataSectionCode = motionDataSectionCode;
		this.motoinDataDatetime = motoinDataDatetime;
	}

	public String getMotionDataId() {
		return motionDataId;
	}

	public void setMotionDataId(String motionDataId) {
		this.motionDataId = motionDataId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getMotionData() {
		return motionData;
	}

	public void setMotionData(String motionData) {
		this.motionData = motionData;
	}

	public String getMotionDataSectionCode() {
		return motionDataSectionCode;
	}

	public void setMotionDataSectionCode(String motionDataSectionCode) {
		this.motionDataSectionCode = motionDataSectionCode;
	}

	public String getMotoinDataDatetime() {
		return motoinDataDatetime;
	}

	public void setMotoinDataDatetime(String motoinDataDatetime) {
		this.motoinDataDatetime = motoinDataDatetime;
	}

}
