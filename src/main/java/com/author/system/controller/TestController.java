/**
 * 
 */
package com.author.system.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import oracle.sql.BLOB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.controller.BaseController;
import com.author.base.model.Message;
import com.author.common.utils.MessageManager;
import com.author.system.bean.TestBlob;
import com.author.system.dao.BlobTestDao;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-13 下午3:22:53
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private BlobTestDao blobTestDao;
	
	@ResponseBody
	@RequestMapping("/gurd")
	public Message delete(){
		
		Message msg = new Message();
		msg.setSuccess(true);
		msg.setMessage("123456");
		return msg;
	} 
	
	@ResponseBody
	@RequestMapping({"/getbyid","get/{id}"})
	public Message get(HttpServletRequest request,@MatrixVariable(value="id",required=false)String id){
		if(StringUtils.isEmpty(id)){
			id = request.getParameter("id");
		}
		Message msg = new Message();
		msg.setSuccess(true);
		msg.setMessage(id);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/timeout")
	public synchronized Message timeoutTest() throws InterruptedException{
		System.out.println("超时测试");
		wait(10000);
		System.out.println("停止等待");
		Message msg = new Message();
		msg.setSuccess(true);
		msg.setMessage("123456");
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/save/blob")
	public Message saveBlob() throws SQLException, IOException{
		TestBlob bean = new TestBlob();
		
		Double id = Math.random();
		id = id*100000;
		Integer i = id.intValue();
		bean.setId(i.toString());
		
		BLOB blob = BLOB.getEmptyBLOB();
		String str = "Test Blob";
		blob.setBytes(str.getBytes());
		/*OutputStream outputStream = blob.getBinaryOutputStream();
		outputStream.write(str.getBytes());*/
		//bean.setTextControl(blob);
		
		bean = this.blobTestDao.save(bean);
		//this.blobTestDao.updateBlob(bean.getId(), str);
		return MessageManager.save();
	}
	
	@RequestMapping("/rtf/html")
	public void rtfToHtml(){
		
		
	}
	
}
