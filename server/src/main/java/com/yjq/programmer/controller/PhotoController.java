package com.yjq.programmer.controller;

import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.utils.CommonUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * 统一图片查看器
 * @author wenLiu
 *
 */
@RequestMapping("/photo")
@RestController
@Api(tags = "图片管理")
@Slf4j
public class PhotoController {

	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${yjq.upload.photo.path}")
	private String uploadPhotoPath;//文件保存位置

	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

	/**
	 * 系统统一的图片查看方法
	 * @param filename
	 * @return
	 */
	@ApiOperation("查看图片")
	@ApiImplicitParam(name = "filename", value = "图片文件名", required = true, dataType = "String", paramType = "query")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "图片上传成功！", response = ResponseDTO.class),
			@ApiResponse(code = -7, message = "图片格式不正确！"),
			@ApiResponse(code = -8, message = "上传的图片不能超过2MB！"),
			@ApiResponse(code = -9, message = "上传的图片格式不正确！"),
			@ApiResponse(code = -16, message = "上传的图片不能为空！"),
			@ApiResponse(code = 500, message = "文件保存异常")
	})
	@RequestMapping(value="/view")
	public ResponseEntity<?> viewPhoto(@RequestParam(name="filename", required=true)String filename){
		logger.info("load file path: {}", uploadPhotoPath + filename);
		Resource resource = resourceLoader.getResource("file:" + uploadPhotoPath + filename);
		try {
			return ResponseEntity.ok(resource);
		} catch (Exception e) {
			logger.error("Failed to load photo with filename: {}", uploadPhotoPath + filename, e);
			return ResponseEntity.notFound().build();
		}
	}


	/**
	 * 上传图片统一处理
	 * @param photo
	 * @param request
	 * @return
	 */
	@ApiOperation("上传图片")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "图片上传成功！", response = ResponseDTO.class),
			@ApiResponse(code = -7, message = "图片格式不正确！"),
			@ApiResponse(code = -8, message = "上传的图片不能超过2MB！"),
			@ApiResponse(code = -9, message = "上传的图片格式不正确！"),
			@ApiResponse(code = -16, message = "上传的图片不能为空！"),
			@ApiResponse(code = 500, message = "文件保存异常")
	})
	@PostMapping(value="/upload")
	public ResponseDTO<String> uploadPhoto(MultipartFile photo, HttpServletRequest request){
		if(photo == null){
			return ResponseDTO.errorByMsg(CodeMsg.PHOTO_EMPTY);
		}
		//检查上传文件大小 不能超过2MB
		if(photo.getSize() > 2*1024*1024) {
			return ResponseDTO.errorByMsg(CodeMsg.PHOTO_SURPASS_MAX_SIZE);
		}
		//获取文件后缀
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1);
		if(!CommonUtil.isPhoto(suffix)){
			return ResponseDTO.errorByMsg(CodeMsg.PHOTO_FORMAT_NOT_CORRECT);
		}
//		//linux 路径保存有问题
//		String savePath = uploadPhotoPath + CommonUtil.getFormatterDate(new Date(), "yyyyMMdd") + "\\";
		String savePath = uploadPhotoPath + CommonUtil.getFormatterDate(new Date(), "yyyyMMdd") + File.separator;
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			//若不存在改目录，则创建目录
			savePathFile.mkdir();
		}
		//TODO 文件名修改为base64进行存储
		String filename = new Date().getTime()+"."+suffix;
		logger.info("保存图片的路径:{}",savePath + filename);
		try {
			//将文件保存至指定目录
			photo.transferTo(new File(savePath + filename));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseDTO.errorByMsg(CodeMsg.SAVE_FILE_EXCEPTION);
		}
//		String filepath = CommonUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + filename;
		String filepath = CommonUtil.getFormatterDate(new Date(), "yyyyMMdd") + File.separator + filename;
		logger.info("保存图片的路径:{}",filepath);
		return ResponseDTO.successByMsg(filepath, "图片上传成功！");
	}
}
