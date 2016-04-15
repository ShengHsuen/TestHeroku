package com.mett.writeMe.contracts;

import java.util.List;

import com.mett.writeMe.pojo.MyLibraryPOJO;

public class MyLibraryResponse extends BaseResponse{
	
	private List<MyLibraryPOJO> libreria;

	public MyLibraryResponse() {
		super();
	}
	
	public List<MyLibraryPOJO> getMyLibrary() {
		return libreria;
	}

	public void setMyLibrary(List<MyLibraryPOJO> libreria) {
		this.libreria = libreria;
	}
}
