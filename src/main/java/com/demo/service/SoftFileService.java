package com.demo.service;

import com.demo.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface SoftFileService {

    ResponseDto upload(String[] dir, MultipartFile[] files);

    ResponseEntity<byte[]> download(String fileLocated, HttpServletRequest request);
}
