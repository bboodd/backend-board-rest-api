package com.hh.board.domain.file;


import com.hh.board.common.exception.CustomException;
import com.hh.board.common.file.FileUtils;
import com.hh.board.common.response.Response;
import com.hh.board.docs.FileControllerDocs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class FileController implements FileControllerDocs {

    private final FileService fileService;
    private final FileUtils fileUtils;

    // 파일 리스트 조회
    @GetMapping("/posts/{postId}/files")
    public ResponseEntity<Response> getFiles(@PathVariable int postId) {

        List<FileResponseDto> fileList = fileService.findAllFileByPostId(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(fileList));

    }

    // 첨부파일 다운로드
    @GetMapping("/posts/{postId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable int postId, @PathVariable int fileId) {

        FileResponseDto file = fileService.findFileById(fileId);
        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String fileName = URLEncoder.encode(file.getFileOriginalName(), "UTF-8");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileName + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getFileSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new CustomException("fileName encoding failed : " + file.getFileOriginalName(), e);
        }
    }
}
