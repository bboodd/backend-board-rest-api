package com.hh.board.domain.post;


import com.hh.board.common.dto.SearchDto;
import com.hh.board.common.file.FileUtils;
import com.hh.board.common.paging.PagingResponse;
import com.hh.board.common.response.Response;
import com.hh.board.domain.file.FileRequestDto;
import com.hh.board.domain.file.FileResponseDto;
import com.hh.board.domain.file.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hh.board.domain.post.PostVo.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    // 게시글 리스트 조회
    @GetMapping("/posts")
    public ResponseEntity<Response> getPosts(SearchDto searchDto) {

        PagingResponse<PostResponseDto> listWithPagination = postService.findAllPostBySearch(searchDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.success(listWithPagination));
    }

    // 게시글 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Response> getPost(@PathVariable int postId) {

        PostResponseDto postResponseDto = postService.findPostById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.success(postResponseDto));
    }

    // 게시글 저장
    @PostMapping("/posts")
    public ResponseEntity<Response> savePost(@Valid PostRequestDto postRequestDto) {
        int postId = postService.savePost(toVo(postRequestDto));
        List<FileRequestDto> files = fileUtils.uploadFiles(postRequestDto.getFiles());
        fileService.saveFiles(postId, files);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.success(postId));
    }

    // 게시글 수정
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<Response> updatePost(@PathVariable int postId,@Valid PostRequestDto postRequestDto) {

        // 1. 게시글 정보 수정
        postService.updatePost(toVo(postRequestDto));
        // 2. 파일 업로드 (to disk)
        List<FileRequestDto> uploadFiles = fileUtils.uploadFiles(postRequestDto.getFiles());
        // 3. 파일 정보 저장 (to database)
        fileService.saveFiles(postRequestDto.getPostId(), uploadFiles);
        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileResponseDto> deleteFiles = fileService.findAllFileByIds(postRequestDto.getRemoveFileIds());
        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);
        // 6. 파일 삭제 (from database)
        fileService.deleteAllFileByIds(postRequestDto.getRemoveFileIds());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.success(postId));
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable int postId) {

    }


}
