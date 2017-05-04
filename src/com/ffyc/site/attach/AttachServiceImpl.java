package com.ffyc.site.attach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ffyc.site.common.image.ImageInfo;
import com.ffyc.site.service.SiteService;
import com.nationsky.pub.utils.FileUtils;
import com.peernet.mobile.server.admin.common.cst.AttachDir;
import com.peernet.mobile.server.admin.common.utils.AttachUtil;
import com.peernet.mobile.server.admin.common.vo.AttachVO;

/**
 * 附件服务实现
 * 
 * @author 宋翔
 * @date 2014-7-25 下午12:30:19
 */
@Service("attachService")
public class AttachServiceImpl implements AttachService
{
	@Autowired
	private SiteService siteService;
	
	private static SimpleDateFormat subFolderFormat = new SimpleDateFormat("yyMMdd");
	
	
	@Override
	public AttachVO uploadAttach4KindEditor(MultipartFile attach) throws AttachUploadException
    {
        AttachVO attachVO = this.uploadAttach(attach);
        attachVO.setUrl("../"+attachVO.getUrl());
        return attachVO;
    }
	
    @Override
    public AttachVO uploadAttach(MultipartFile attach) throws AttachUploadException
    {
        AttachVO attachVO = new AttachVO();
        String fileName = FileUtils.rename(attach.getOriginalFilename());

        try
        {
        	String subFolderName = this.getSubFolderName();
        	String url = AttachDir.ATTACH_BASE_DIR+"/"+subFolderName+"/"+fileName;
        	File targetDir = new File(AttachDir.ATTACH_ADMIN_DIR,subFolderName);
        	targetDir.mkdirs();
            attach.transferTo(new File(targetDir, fileName));
            ImageInfo image = new ImageInfo();
            image.setFileName(fileName);
            image.setPath(subFolderName);
            this.siteService.insertImageInfo(image);
            
            attachVO.setId(image.getId());
            attachVO.setName(image.getFileName());
            attachVO.setUrl(url);
        }
        catch(IllegalStateException e)
        {
            throw new AttachUploadException("系统内部异常:" + e.getMessage());
        }
        catch(IOException e)
        {
            throw new AttachUploadException("写入文件出错：" + e.getMessage());
        }

        return attachVO;
    }

    @Override
    public void getAttach(HttpServletResponse response, String attachId) throws FileNotFoundException, AttachIOException
    {
        File file = new File(AttachDir.ATTACH_DIR, attachId);

        if(!file.exists())
        {
            throw new FileNotFoundException("找不到附件");
        }

        try
        {
            AttachUtil.writeToResponse(file, response);
        }
        catch(IOException e)
        {
            throw new AttachIOException("附件下载失败，" + e.getMessage());
        }
    }
    
    private String getSubFolderName(){
		return subFolderFormat.format(new Date());
    }
}
