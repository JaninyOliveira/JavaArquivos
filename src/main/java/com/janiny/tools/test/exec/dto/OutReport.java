package com.janiny.tools.test.exec.dto;

import java.util.ArrayList;
import java.util.List;

public class OutReport {
	private List<InputFile> files;
	
	
	public List<InputFile> getFiles() {
		if(files == null){
			files = new ArrayList<InputFile>();
		}
		return files;
	}
	
	
}
