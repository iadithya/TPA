package com.server.vo;

public class eServiceVO extends AbstractObjectValues {

	private String eserviceName;

	private String minstryName;

	private String projectManagerName;
	
	
	public eServiceVO(String eserviceName, String minstryName,
			String projectManagerName) {
		super();
		this.eserviceName = eserviceName;
		this.minstryName = minstryName;
		this.projectManagerName = projectManagerName;
	}



	public eServiceVO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getEserviceName() {
		return eserviceName;
	}




	public void setEserviceName(String eserviceName) {
		this.eserviceName = eserviceName;
	}


	public String getMinstryName() {
		return minstryName;
	}

	public void setMinstryName(String minstryName) {
		this.minstryName = minstryName;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.eserviceName + ";" + this.minstryName + ";"
				+ this.projectManagerName + ";" + super.getId() + ";"
				+ super.getCreateDate() + ";" + super.getUpdateDate();
	}

}
