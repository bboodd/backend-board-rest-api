package com.hh.board.domain.file;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.Collections;
import java.util.List;

import static com.hh.board.domain.file.FileResponseDto.toDto;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    /**
     * 파일 다중 등록
     * @param postId - 게시글 번호
     * @param files - 파일 정보 list
     */
    @Transactional
    public void saveFiles(int postId, List<FileRequestDto> files){
        if(CollectionUtils.isEmpty(files)) {
            return;
        }
        for(FileRequestDto file : files){
            file.setPostId(postId);
        }
        List<FileVo> fileVoList = files.stream()
                .map(FileVo::toVo).collect(toList());

        fileMapper.saveAllFile(fileVoList);
    }

    /**
     * 파일 등록
     * @param fileVo 파일 정보
     * @return 성공시 1 실패시 0
     */

    public int saveFile(FileVo fileVo){
        int result = fileMapper.saveFile(fileVo);
        return result;
    }

    /**
     * 파일 목록 조회
     * @param postId - 게시글 번호
     * @return 파일 상세정보 list
     */

    public List<FileResponseDto> findAllFileByPostId(int postId){
        List<FileVo> fileList = fileMapper.findAllFileByPostId(postId);

        List<FileResponseDto> result = fileList.stream()
                .map(FileResponseDto::toDto).collect(toList());
        return result;
    }

    /**
     * 파일 상세정보 조회
     * @param fileId - id
     * @return - 파일 상세정보
     */

    public FileResponseDto findFileById(int fileId){
        FileVo fileVo = fileMapper.findFileById(fileId);
        FileResponseDto result = toDto(fileVo);
        return result;
    }

    /**
     * 파일 삭제
     * @param fileId - id
     * @return - 성공시 1 실패시 0
     */

    public int deleteFile(int fileId){
        int result = fileMapper.deleteFileById(fileId);
        return result;
    }

    /**
     * 파일 리스트 조회
     * @param ids - pk list
     * @return - file list
     */
    public List<FileResponseDto> findAllFileByIds(List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<FileVo> fileVoList = fileMapper.findAllFileByIds(ids);
        return fileVoList.stream().map(FileResponseDto::toDto).collect(toList());
    }


    /**
     * 파일 삭제
     * @param ids - id list
     */
    public void deleteAllFileByIds(List<Integer> ids){
        if(CollectionUtils.isEmpty(ids)){
            return;
        }
        fileMapper.deleteAllFileByIds(ids);
    }
}
