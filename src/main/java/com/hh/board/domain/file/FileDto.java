package com.hh.board.domain.file;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class FileDto {

    int fileId;

    @NotBlank(message = "fileDto에 postId값이 들어오지 않았습니다")
    int postId;

    String fileOriginalName;

    String fileName;

    String filePath;

    long fileSize;

    /**
     * vo객체를 dto객체로
     * 다운로드용 화면에 뿌려주기 위함
     * @param fileVo
     * @return fileDto
     */

    public static FileDto toDto(FileVo fileVo){
        FileDto fileDto = FileDto.builder()
                .fileId(fileVo.getFileId())
                .postId(fileVo.getPostId())
                .fileName(fileVo.getFileName())
                .fileOriginalName(fileVo.getFileOriginalName())
                .filePath(fileVo.getFilePath())
                .build();
        return fileDto;
    }
}
