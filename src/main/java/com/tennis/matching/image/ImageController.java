package com.tennis.matching.image;

import com.tennis.matching.util.image.AwsS3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ImageController {

    private final AwsS3Util awsS3Util;

    // 단일이미지 업로드
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam(value = "file") MultipartFile file
    ) {
        return new ResponseEntity<>(awsS3Util.uploadFile(file), HttpStatus.OK);
    }

    // 다중이미지 업로드
    @PostMapping("/uploads")
    public ResponseEntity<?> uploadFiles(
            @RequestParam(value = "files") List<MultipartFile> files
    ) {
        return new ResponseEntity<>(awsS3Util.uploadFiles(files), HttpStatus.OK);
    }

    // 이미지 다운로드
    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = awsS3Util.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    // 이미지 삭제
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(awsS3Util.deleteFile(fileName), HttpStatus.OK);
    }
}
