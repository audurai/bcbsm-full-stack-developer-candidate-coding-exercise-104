package com.bcbsm.filemgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcbsm.filemgt.model.FolderDtlsModel;
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

	@PostMapping
	public void getZipOrRarFile(@RequestBody FolderDtlsModel folderDtlsModel) {
		fileMgtService.compressFiles(folderDtlsModel.getFolderLocation());
	}

}
