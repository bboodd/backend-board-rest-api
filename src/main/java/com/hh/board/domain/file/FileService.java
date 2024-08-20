package com.hh.board.domain.file;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.hh.board.domain.file.FileDto.toDto;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    @Value("${filePath}")
    String filePath;

    /**
     * 파일 업로드
     * multipartfile 받아서 fileVo로 변환후 save
     * @param postId
     * @param file
     * @return
     * @throws IOException
     */

    public int uploadAndSaveFile(int postId, MultipartFile file) throws IOException {

        String originalName = file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        String extension = originalName.substring(originalName.lastIndexOf("."));

        String saveName = uuid + extension;

        String savePath = filePath + saveName;

        long fileSize = file.getSize();

        FileVo fileVo = FileVo.builder()
                .postId(postId)
                .fileOriginalName(originalName)
                .fileName(saveName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();

        file.transferTo(new File(savePath));

        int result = saveFile(fileVo);

        return result;
    }

    /**
     * 파일 등록
     * @param fileVo
     * @return
     */

    public int saveFile(FileVo fileVo){
        int result = fileMapper.saveFile(fileVo);
        return result;
    }

    /**
     * 파일 목록 조회
     * @param postId
     * @return
     */

    public List<FileDto> findAllFileByPostId(int postId){
        List<FileVo> fileList = fileMapper.findAllFileByPostId(postId);

        List<FileDto> result = fileList.stream()
                .map(FileDto::toDto).collect(toList());
        return result;
    }

    /**
     * 파일 단건 조회
     * @param fileId
     * @return
     */

    public FileDto findFileById(int fileId){
        FileVo fileVo = fileMapper.findFileById(fileId);
        FileDto result = toDto(fileVo);
        return result;
    }

    /**
     * 파일 삭제
     * @param fileId
     * @return
     */

    public int deleteFile(int fileId){
        int result = fileMapper.deleteFileById(fileId);
        return result;
    }
}
