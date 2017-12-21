package com.absentm.demo.beans;

import org.springframework.web.multipart.MultipartFile;

/**
 * FileModel
 * 
 * @author AbsentM
 *
 */
public class FileModel {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
