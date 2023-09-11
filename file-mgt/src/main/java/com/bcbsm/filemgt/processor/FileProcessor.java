package com.bcbsm.filemgt.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bcbsm.filemgt.exception.FileMgtException;

@Component
public class FileProcessor {

	@Value("${file-mgt.processor.location}")
	private String FILER_PROCESSOR_DIR;

	/**
	 * This compress the files and create the zip file in the same directory.
	 * 
	 * @param filePathList  List of files available in the processor directory.
	 * @param processorDir  Processor directory where files are available for
	 *                      process.
	 * @param fileProcessId It is a unique id.
	 * @return
	 * @throws IOException
	 */
	public String compressFiles(List<String> filePathList, File processorDir, long fileProcessId) throws IOException {
		// Zip file name with path
		String zipFilePath = processorDir + "/tmp_" + fileProcessId + ".zip";
		FileOutputStream fos = new FileOutputStream(zipFilePath);
		ZipOutputStream zos = new ZipOutputStream(fos);
		for (String filePath : filePathList) {
			System.out.println("File path " + filePath);
			// for ZipEntry we need to keep only relative file path, so we used substring on
			// absolute path
			ZipEntry ze = new ZipEntry(
					filePath.substring(processorDir.getAbsolutePath().length() + 1, filePath.length()));
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
		return zipFilePath;
	}

	/**
	 * This collects only the file paths in that directory.
	 * 
	 * @param dir
	 * @return
	 * @throws FileMgtException
	 */
	public List<String> readFileNames(File dir) throws FileMgtException {
		List<String> filesInDir = new ArrayList<>();
		File[] files = dir.listFiles();
		if (Objects.isNull(files)) {
			throw new FileMgtException("File processor location not found");
		} else {
			for (File file : files) {
				if (file.isFile()) {
					filesInDir.add(file.getAbsolutePath());
				}
			}
		}

		return filesInDir;
	}

	/**
	 * This creates the directory for processing the files.
	 * 
	 * @param fileProcessId It is a unique id.
	 * @return
	 * @throws FileMgtException
	 */
	public File createDirectory(long fileProcessId) throws FileMgtException {
		Path fileProcessorDir = Paths.get(FILER_PROCESSOR_DIR + fileProcessId);
		Path absolutePath = fileProcessorDir.normalize().toAbsolutePath();
		System.out.println(absolutePath.toUri());
		File file = new File(absolutePath.toUri());
		if (file.mkdir() == true) {
			System.out.println(FILER_PROCESSOR_DIR + "created");
		} else {
			throw new FileMgtException("File processor directory not created");
		}
		return file;
	}

	/**
	 * This method stores the selected files in this directory for creating zip
	 * file.
	 * 
	 * @param files Files selected by the client.
	 * @param uri
	 * @throws FileMgtException
	 */
	public void storeFiles(MultipartFile[] files, URI uri) throws FileMgtException {
		if (files == null) {
			throw new FileMgtException("File is not available");
		} else {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				Path fileProcessorDir = Paths.get(uri);
				Path destinationFile = fileProcessorDir.resolve(Paths.get(file.getOriginalFilename())).normalize()
						.toAbsolutePath();
				System.out.println(" destinationFile :: " + destinationFile);
				try (InputStream inputStream = file.getInputStream()) {
					Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					throw new FileMgtException(e.getMessage());
				}
			}

		}
	}

	/**
	 * It returns the zip file as resource.
	 * 
	 * @param zipFilePath This is the path where zip file is located.
	 * @return
	 * @throws MalformedURLException
	 * @throws FileMgtException
	 */
	public Resource loadFileAsResource(String zipFilePath) throws MalformedURLException, FileMgtException {
		Path zipFilePathNormalized = Paths.get(zipFilePath).normalize();
		Resource resource = new UrlResource(zipFilePathNormalized.toUri());
		if (resource.exists()) {
			return resource;
		} else {
			throw new FileMgtException("Zip file not available");
		}
	}

}
