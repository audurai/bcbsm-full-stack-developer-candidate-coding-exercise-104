package com.bcbsm.filemgt.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.bcbsm.filemgt.exception.FileMgtException;

public interface FileMgtService {

	public Resource downloadCompressedFile(MultipartFile[] files, long fileProcessId) throws FileMgtException;

}
