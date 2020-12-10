package com.acculytixs.wayuparty.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.acculytixs.wayuparty.util.FileInfo;
import com.acculytixs.wayuparty.util.FileUploadUtil;



@Controller
public class FileController {

	@Value("${static.path}")
	private String staticPath;
	
	
	@RequestMapping(value = "/upload/{type}", method = RequestMethod.POST)
	@ResponseBody
	public List<FileInfo> uploadFile(MultipartHttpServletRequest request, @PathVariable String type,
			@RequestParam Long vendorId,@RequestParam(defaultValue = "0") int x,
			@RequestParam(defaultValue = "0") int y, @RequestParam(defaultValue = "0") int w,
			@RequestParam(defaultValue = "0") int h, @RequestParam(defaultValue = "0") int chunk,
			@RequestParam(defaultValue = "0") int chunks, String name, String uploadid) {

		List<FileInfo> fileInfoList = new ArrayList<>();
		Iterator<String> itrator = request.getFileNames();
		while (itrator.hasNext()) {
			FileInfo fileInfo = new FileInfo();
			boolean chunkDataFlag = false;
			MultipartFile multiFile = request.getFile(itrator.next());
			if (name == null)
				name = multiFile.getOriginalFilename();
			File file = new File(staticPath,
					"temp" + System.getProperty("file.separator") + (uploadid != null ? uploadid + "-" : "") + name.replaceAll(" ", "_"));
			
			File folderFile = new File(staticPath, "/" + "temp" );
			if (!folderFile.exists())
				folderFile.mkdirs();
			
			try (FileOutputStream fis = new FileOutputStream(file, true);) {
				fis.write(multiFile.getBytes());
				chunkDataFlag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (chunks > chunk + 1) {
				return fileInfoList;
			}
			FileUploadUtil fileUploadUtil = new FileUploadUtil();
					String fileURL = fileUploadUtil.uploadFile(staticPath, vendorId, type, file);
					fileInfo.setFileName(multiFile.getOriginalFilename());
					fileInfo.setFileURL(type.equalsIgnoreCase("ticket") ? fileURL.replace("/ticket/", "/temp/") : fileURL);
					fileInfo.setContentType(multiFile.getContentType());
					fileInfoList.add(fileInfo);
		}

		return fileInfoList;
	}
	
	
}
