package com.hh.board.domain.file;
import lombok.*;

@Builder
@Value
//객체 외부 생성 제한
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class FileVo {

    private int fileId;

    private int postId;

    private String fileOriginalName;

    private String fileName;

    private String filePath;

    private long fileSize;

    /**
     * dto를 vo로 전환
     * db로 들어감(업로드)
     * @param fileRequestDto - dto
     * @return fileVo - vo
     */

    public static FileVo toVo(FileRequestDto fileRequestDto) {
        FileVo fileVo = FileVo.builder()
                .postId(fileRequestDto.getPostId())
                .fileOriginalName(fileRequestDto.getFileOriginalName())
                .fileName(fileRequestDto.getFileName())
                .filePath(fileRequestDto.getFilePath())
                .fileSize(fileRequestDto.getFileSize())
                .build();
        return fileVo;
    }
}
