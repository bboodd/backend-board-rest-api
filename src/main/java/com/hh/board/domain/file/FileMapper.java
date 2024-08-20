package com.hh.board.domain.file;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    int saveFile(FileVo fileVo);

    List<FileVo> findAllFileByPostId(int postId);

    FileVo findFileById(int fileId);

    int deleteFileById(int fileId);
}
