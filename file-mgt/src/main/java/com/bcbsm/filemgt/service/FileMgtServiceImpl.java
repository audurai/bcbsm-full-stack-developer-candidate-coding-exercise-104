package com.bcbsm.filemgt.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bcbsm.filemgt.exception.FileMgtException;
import com.bcbsm.filemgt.processor.FileProcessor;

@Service
public class FileMgtServiceImpl implements FileMgtService {

	@Autowired
	private FileProcessor fileProcessor;

	@Override
	public Resource downloadCompressedFile(MultipartFile[] files, long fileProcessId) throws FileMgtException {
		Resource resource = null;
		// Creating processor directory
		File processorDir = fileProcessor.createDirectory(fileProcessId);
		// Store the files in processor directory
		fileProcessor.storeFiles(files, processorDir.toURI());
		// Retrieving all file paths
		List<String> filePaths = fileProcessor.readFileNames(processorDir);
		try {
			// Compressing all files in the processor directory
			String zipFilePath = fileProcessor.compressFiles(filePaths, processorDir, fileProcessId);
			// Loding the compressed file as a resource
			resource = fileProcessor.loadFileAsResource(zipFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}

}
