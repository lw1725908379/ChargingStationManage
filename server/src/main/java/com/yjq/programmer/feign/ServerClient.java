package com.yjq.programmer.feign;

import com.lw.api.server.IServerClient;
import com.yjq.programmer.controller.v2.PhotoController;
import com.yjq.programmer.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 17259
 * @create 2024-07-06 20:13
 */
@RestController
public class ServerClient implements IServerClient {

    @Autowired
    private PhotoController photoController;

    @Override
    public ResponseEntity<?> viewPhoto(String filename) {
        return photoController.viewPhoto(filename);
    }

    @Override
    public ResponseDTO<String> uploadPhoto(MultipartFile photo, HttpServletRequest request) {
        return photoController.uploadPhoto(photo, request);
    }
}
