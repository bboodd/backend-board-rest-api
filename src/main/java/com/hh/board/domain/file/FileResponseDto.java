package com.hh.board.domain.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class FileResponseDto {

    private int fileId;

    private int postId;

    private String fileOriginalName;

    private String fileName;

    private String filePath;

    private long fileSize;

    private int status;


    /**
     * vo객체를 dto객체로
     * 다운로드용 화면에 뿌려주기 위함
     * @param fileVo
     * @return fileDto
     */

    public static FileResponseDto toDto(FileVo fileVo){
        FileResponseDto fileResponseDto = FileResponseDto.builder()
                .fileId(fileVo.getFileId())
                .postId(fileVo.getPostId())
                .fileName(fileVo.getFileName())
                .fileOriginalName(fileVo.getFileOriginalName())
                .filePath(fileVo.getFilePath())
                .build();
        return fileResponseDto;
    }
}
