package com.hh.board.domain.file;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    int saveFile(FileVo fileVo);

    /**
     * 파일 리스트 조회
     * @param postId - 게시글 id
     * @return - 파일 정보 list
     */
    List<FileVo> findAllFileByPostId(int postId);

    /**
     * 파일 리스트 조회
     * @param ids - id list
     * @return 파일 정보 list
     */
    List<FileVo> findAllFileByIds(List<Integer> ids);

    /**
     * 파일 삭제
     * @param ids - id list
     */
    void deleteAllFileByIds(List<Integer> ids);

    /**
     * 파일 상세정보 조회
     * @param fileId - id
     * @return 파일 상세정보
     */
    FileVo findFileById(int fileId);

    /**
     * 파일 삭제
     * @param fileId - id
     * @return - 1 or 0
     */
    int deleteFileById(int fileId);

    /**
     * 파일 리스트 저장
     * @param files - 파일 정보 list
     */
    void saveAllFile(List<FileVo> files);
}
