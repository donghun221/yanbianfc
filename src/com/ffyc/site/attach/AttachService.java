package com.ffyc.site.attach;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import com.ffyc.site.attach.AttachIOException;
import com.ffyc.site.attach.AttachUploadException;
import com.peernet.mobile.server.admin.common.vo.AttachVO;

/**
 * 附件相关服务
 * 
 * @author 宋翔
 * @date 2014-7-25 下午12:20:51
 */
public interface AttachService
{
    /**
     * 上传附件
     * 
     * @param attach 附件
     * @param userSeq 当前登录用户名
     * @return 附件名称
     * @throws AttachUploadException 附件保存失败，抛出该异常
     */
    AttachVO uploadAttach(MultipartFile attach) throws AttachUploadException;

    /**
     * 获取附件
     * 
     * @param response
     * @param attachId 附件ID
     * @throws FileNotFoundException
     * @throws AttachIOException
     */
    void getAttach(HttpServletResponse response, String attachId) throws FileNotFoundException, AttachIOException;

    
    //4 KINDEDITOR
	AttachVO uploadAttach4KindEditor(MultipartFile attach) throws AttachUploadException;
}