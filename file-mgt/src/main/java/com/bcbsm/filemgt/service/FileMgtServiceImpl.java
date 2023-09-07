package com.bcbsm.filemgt.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcbsm.filemgt.processor.FileProcessor;

@Service
public class FileMgtServiceImpl implements FileMgtService {

	@Autowired
	private FileProcessor fileProcessor;

	@Override
	public void compressFiles(String folderLocation) {
		File dir = new File(folderLocation);
		List<String> filePaths = fileProcessor.readFileNames(dir);
		try {
			fileProcessor.compressFiles(filePaths, dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
