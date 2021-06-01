package com.demo.service.impl;

import com.demo.dto.ResponseDto;
import com.demo.service.SoftFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class SoftFileLocalServiceImpl implements SoftFileService {

    private static final String patternNoSpecialCharacter = "[^a-zA-Z0-9០-៩ក-៓]+";
    private static final String UPLOAD_SUCCESS = "File upload successfully!";
    private static final String UPLOAD_FAILED = "File upload failed!";

    @Value("${file.upload-dir}")
    private String filePath;

    @Override
    public ResponseDto upload(String[] dir, MultipartFile[] files){
        int num = 0;
        int i = 1;
        StringBuilder fileName;
        String folder = "/" + dir[0];
        String subFilder;

        List<Map<String, Object>> dataList  = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                Map<String, Object> data  = new HashMap<>();

                subFilder = "/" + (folder.equals("/Customer") ? dir[1]: dir[i]);

                Path fileStorageLocation = Paths.get(filePath + folder + subFilder).toAbsolutePath().normalize();

                if (!Files.exists(fileStorageLocation.getParent())) {
                    Files.createDirectory(fileStorageLocation.getParent());
                }

                if (!Files.exists(fileStorageLocation)) {
                    Files.createDirectory(fileStorageLocation);
                }

                fileName = new StringBuilder(Objects.requireNonNull(file.getOriginalFilename()));
                Path targetLocation = fileStorageLocation.resolve(fileName.toString());
                File file2 = new File(targetLocation.toString());
                while (file2.exists()) {
                    fileName = new StringBuilder(Objects.requireNonNull(file.getOriginalFilename()));
                    fileName.insert(fileName.lastIndexOf("."), "(" + ++num + ")");
                    targetLocation = fileStorageLocation.resolve(fileName.toString());
                    file2 = new File(targetLocation.toString());
                }
                Files.copy(file.getInputStream(), targetLocation);

                data.put("fileName", fileName);
                data.put("filePath", fileStorageLocation.toString());
                i++;
                dataList.add(data);
            }
            return new ResponseDto(UPLOAD_SUCCESS, dataList);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseDto(UPLOAD_FAILED);
        }
    }

    @Override
    public ResponseEntity<byte[]> download(String fileLocated, HttpServletRequest request) {
        try {
            Path fileStorageLocation = Paths.get(fileLocated).toAbsolutePath().normalize();
            Path path = fileStorageLocation.resolve(fileLocated).normalize();
            // Try to determine file's content type
            String contentType;
            contentType = request.getServletContext().getMimeType(path.toString());
            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                            URLEncoder.encode(path.getFileName().toString(), "UTF-8"))
                    .body(Files.readAllBytes(path));

        } catch (IOException e) {
            return ResponseEntity.ok().body(null);
        }
    }


}
