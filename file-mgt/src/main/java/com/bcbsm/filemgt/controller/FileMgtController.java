package com.bcbsm.filemgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcbsm.filemgt.exception.FileMgtException;
import com.bcbsm.filemgt.service.FileMgtService;

@RequestMapping("/file-mgt")
@RestController
public class FileMgtController {

	@Autowired
	private FileMgtService fileMgtService;

	@GetMapping
	public Map<String, Object> home(Principal loggedInUser) {
		Map<String, Object> model = new HashMap<>();
		model.put("username", loggedInUser.getName());
		return model;
	}

	@PostMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> downloadCompressedFile(@RequestParam("files") MultipartFile[] files)
			throws FileMgtException {
		Resource resource = fileMgtService.downloadCompressedFile(files, System.currentTimeMillis());

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
