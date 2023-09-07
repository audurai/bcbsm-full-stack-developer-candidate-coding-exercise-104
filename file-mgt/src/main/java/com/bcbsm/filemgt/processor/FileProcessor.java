package com.bcbsm.filemgt.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

@Component
public class FileProcessor {

	/**
	 * This compress the files and create the tmp.zip file in the same directory.
	 * 
	 * @param filePathList The files available in the directory.
	 * @param dir          The directory which has files to be compressed.
	 * @throws IOException
	 */
	public synchronized void compressFiles(List<String> filePathList, File dir) throws IOException {
		// Zip file name with path
		String zipFilePath = dir + "/tmp.zip";
		FileOutputStream fos = new FileOutputStream(zipFilePath);
		ZipOutputStream zos = new ZipOutputStream(fos);
		for (String filePath : filePathList) {
			System.out.println("File path " + filePath);
			// for ZipEntry we need to keep only relative file path, so we used substring on
			// absolute path
			ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
			zos.putNextEntry(ze);
			// read the file and write to ZipOutputStream
			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			zos.closeEntry();
			fis.close();
		}
		zos.close();
		fos.close();
	}

	/**
	 * This collects only the file paths in that directory.
	 * 
	 * @param dir
	 * @return
	 */
	public List<String> readFileNames(File dir) {
		List<String> filesInDir = new ArrayList<>();
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				filesInDir.add(file.getAbsolutePath());
			}
		}
		return filesInDir;
	}

}
