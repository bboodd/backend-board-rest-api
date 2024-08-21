package com.hh.board.domain.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class FileRequestDto {

    int fileId;
    int postId;
    String fileOriginalName;
    String fileName;
    String filePath;
    long fileSize;
}
