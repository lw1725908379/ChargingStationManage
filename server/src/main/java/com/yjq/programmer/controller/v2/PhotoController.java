package com.yjq.programmer.controller.v2;

import com.edu.basic.service.FileStorageService;
import com.lw.common.aliyun.GreenImageScan;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.utils.CommonUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 统一图片查看器
 *
 * @author wenLiu
 */
@RequestMapping("v2/photo")
@RestController
@Api(tags = "图片管理")
@Slf4j
public class PhotoController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private GreenImageScan greenImageScan;

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    /**
     * 系统统一的图片查看方法
     *
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
    @RequestMapping(value = "/view")
    public ResponseEntity<?> viewPhoto(@RequestParam(name = "filename", required = true) String filename) {
        logger.info("load file from minio path: {}", filename);
        byte[] bytes = fileStorageService.downLoadFile(filename);

        try {
            return ResponseEntity.ok(bytes);
        } catch (Exception e) {
            logger.error("Failed to load photo with filename: {}", filename, e);
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * 上传图片统一处理
     *
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
    @Async // IO操作和调用阿里云图片审核操作比较耗时，因此采用异步调用方式
    @PostMapping(value = "/upload")
    public ResponseDTO<String> uploadPhoto(MultipartFile photo, HttpServletRequest request) {
        if (photo == null) {
            return ResponseDTO.errorByMsg(CodeMsg.PHOTO_EMPTY);
        }
        //检查上传文件大小 不能超过5MB
        if (photo.getSize() > 5 * 1024 * 1024) {
            return ResponseDTO.errorByMsg(CodeMsg.PHOTO_SURPASS_MAX_SIZE);
        }
        String originalFilename = photo.getOriginalFilename();

        //获取文件后缀
        String suffix = originalFilename.substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
        if (!CommonUtil.isPhoto(suffix)) {
            return ResponseDTO.errorByMsg(CodeMsg.PHOTO_FORMAT_NOT_CORRECT);
        }
        //上传前调用阿里云图片审查接口 审查要钱的 少用...
        try {
            List<byte[]> imageList = Collections.singletonList(photo.getBytes());
            Map map = greenImageScan.imageScan(imageList);
            if (map!=null) {
                //审核失败
                if(map.get("suggestion").equals("block")){
                   return ResponseDTO.errorByMsg(CodeMsg.PHOTO_ILLEGAL_IMAGE_UPLOAD);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //2.上传图片到minIO中
        //TODO:将上传文件名改为base64 防止恶意上传相同图片
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String fileId = null;
        try {
            fileId = fileStorageService.uploadImgFile("", fileName + "." + suffix, photo.getInputStream());
            log.info("上传图片到MinIO中，fileId:{}", fileId);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("图片上传文件失败！");
        }
        logger.info("保存图片的路径:{}", fileId);
        return ResponseDTO.successByMsg(fileId, "图片上传成功！");
    }
}
