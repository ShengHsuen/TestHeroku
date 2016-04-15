package com.mett.writeMe.contracts;

import com.mett.writeMe.contracts.BaseRequest;
import com.mett.writeMe.pojo.MyLibraryPOJO;

public class MyLibraryRequest extends BaseRequest {

private MyLibraryPOJO library;
	
	public MyLibraryRequest() {
		super();
	}
	
	public MyLibraryPOJO getLibrary() {
		return library;
	}
	
	public void setMyLibrary(MyLibraryPOJO library) {
		this.library = library;
	}

	@Override
	public String toString() {
		return "MyLibraryRequest [library=" + library + "]";
	}
}
