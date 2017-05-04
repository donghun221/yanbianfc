package com.ffyc.site.attach;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.peernet.mobile.server.admin.common.message.FailureResponseMessage;
import com.peernet.mobile.server.admin.common.message.ResponseMessage;
import com.peernet.mobile.server.admin.common.message.SuccessResponseMessage;
import com.peernet.mobile.server.admin.common.vo.AttachVO;

/**
 * 附件功能转发器
 * 
 * @author 宋翔
 * @date 2014-7-24 16:57:43
 */
@Controller
@RequestMapping("/admin/attach")
public class AttachController {
	@Autowired
	private AttachService attachService;

	private static HashMap<String, String> extMap = new HashMap<String, String>();
	long maxSize = 1000000;
	static {
		extMap.put("all",
				"gif,jpg,jpeg,png,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}

	/**
	 * 上传文件
	 */
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public @ResponseBody ResponseMessage uploadAttachKindEditor(
			@RequestParam("attach") MultipartFile attach) {
		try {
			return new SuccessResponseMessage(attachService.uploadAttach(attach));
		} catch (AttachUploadException e) {
			return new FailureResponseMessage(e);
		}
	}

	@RequestMapping(value = "kindEditor", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public @ResponseBody ResponseMessage uploadAttach(@RequestParam("imgFile") MultipartFile attach) {
		try {

			String fileName = attach.getOriginalFilename();
			// 检查文件大小
			if (attach.getSize() > maxSize) {
				throw new AttachUploadException("上传文件大小超过限制。");
			}
			// 检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String>asList(extMap.get("all").split(",")).contains(fileExt)) {
				throw new AttachUploadException("上传文件扩展名是不允许的扩展名。");
			}

			AttachVO attachVO = this.attachService.uploadAttach4KindEditor(attach);

			return new SuccessResponseMessage(attachVO);
		} catch (AttachUploadException e) {
			return new FailureResponseMessage(e);
		}
	}

	/*
	 * @RequestMapping(value = "kindEditor", method = RequestMethod.POST,
	 * produces = "text/plain; charset=UTF-8") public @ResponseBody
	 * ResponseMessage uploadAttach(HttpServletRequest request) { try {
	 * 
	 * FileItemFactory factory = new DiskFileItemFactory(); ServletFileUpload
	 * upload = new ServletFileUpload(factory);
	 * upload.setHeaderEncoding("UTF-8"); List items =
	 * upload.parseRequest(request); Iterator itr = items.iterator(); while
	 * (itr.hasNext()) { FileItem item = (FileItem) itr.next(); String fileName
	 * = item.getName(); long fileSize = item.getSize(); if
	 * (!item.isFormField()) { //检查文件大小 if(item.getSize() > maxSize){ throw new
	 * ApplicationContextException("上传文件大小超过限制。"); } //检查扩展名 String fileExt =
	 * fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	 * if(!Arrays.<String>asList(extMap.get("all").split(",")).contains(fileExt)
	 * ){ throw new ApplicationContextException("上传文件扩展名是不允许的扩展名。"); }
	 * 
	 * AttachVO attachVO = this.attachService.uploadAttach4KindEditor(item);
	 * 
	 * return new SuccessResponseMessage(attachVO); } } return new
	 * FailureResponseMessage("上传文件失败-1"); } catch(AttachUploadException e) {
	 * return new FailureResponseMessage(e); } catch (FileUploadException e) {
	 * return new FailureResponseMessage(e); } }
	 */

	/**
	 * 下载附件
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = AttachRouters.ATTACH_ITEM, method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	public void downloadAttach(HttpServletResponse response, @PathVariable String id) throws IOException {
		try {
			attachService.getAttach(response, id);
		} catch (FileNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
		} catch (AttachIOException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
