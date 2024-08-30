package com.hh.board.domain.post;


import com.hh.board.common.dto.SearchDto;
import com.hh.board.common.exception.PostErrorCode;
import com.hh.board.common.file.FileUtils;
import com.hh.board.common.paging.Pagination;
import com.hh.board.common.paging.PagingResponse;
import com.hh.board.common.response.Response;
import com.hh.board.common.vo.SearchVo;
import com.hh.board.domain.file.FileRequestDto;
import com.hh.board.domain.file.FileResponseDto;
import com.hh.board.domain.file.FileService;
import com.hh.board.domain.file.FileVo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.hh.board.common.vo.SearchVo.toVo;
import static com.hh.board.domain.file.FileVo.toVo;
import static com.hh.board.domain.post.PostVo.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    // TODO: 컨트롤러에서는 정상 케이스만
    // 게시글 리스트 조회
    @GetMapping("/posts")
    public ResponseEntity<Response> getPosts(SearchDto searchDto) {

        // 빈 반환 객체 생성
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 객체를 담아 반환
        PagingResponse<PostResponseDto> listWithPagination = new PagingResponse<>(Collections.emptyList(), null);

        int count = postService.countAllPostBySearch(toVo(searchDto));

        if(count >= 1) {
            // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입 객체에 계산된 페이지 정보 저장
            // pagination 객체 생성자에 searchDto.setPagination 포함
            Pagination pagination = new Pagination(count, searchDto);

            listWithPagination = postService.findAllPostBySearch(toVo(searchDto), pagination);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(listWithPagination));
    }

    // 게시글 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Response> getPost(@PathVariable int postId) {

        postService.increaseViewCountById(postId);

        PostResponseDto postResponseDto = postService.findPostById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(postResponseDto));
    }

    // 게시글 저장
    @PostMapping("/posts")
    public ResponseEntity<Response> savePost(@Valid PostRequestDto postRequestDto) {

        validation(postRequestDto);

        int postId = postService.savePost(PostVo.toVo(postRequestDto));

        // 파일 업로드 및 저장
        List<MultipartFile> files = postRequestDto.getFiles();
        fileUploadAndSave(postId, files);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response(postId + "번 게시글 생성 완료"));
    }

    // 게시글 수정
    @PutMapping("/posts/{postId}")

    public ResponseEntity<Response> updatePost(@PathVariable int postId, @Valid PostRequestDto postRequestDto) {

        validation(postRequestDto);

        // 게시글 정보 수정
        postService.updatePost(PostVo.toVo(postRequestDto));

        // 파일 업로드 및 저장
        List<MultipartFile> files = postRequestDto.getFiles();
        fileUploadAndSave(postId, files);

        List<Integer> removeFileIds = postRequestDto.getRemoveFileIds();
        if(!CollectionUtils.isEmpty(removeFileIds)) {
            // 삭제할 파일 정보 조회 (from database)
            List<FileResponseDto> deleteFiles = fileService.findAllFileByIds(removeFileIds);
            // 파일 삭제 (from disk)
            fileUtils.deleteFiles(deleteFiles);
            // 파일 삭제 (from database)
            fileService.deleteAllFileByIds(removeFileIds);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(postId + "번 게시글 수정 완료"));
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Response> deletePost(@PathVariable int postId) {

        postService.deletePostById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(postId + "번 게시글 삭제 완료"));
    }

    // 비밀번호 체크
    @PostMapping("/posts/{postId}/password")
    public ResponseEntity<Response> checkPassword(@PathVariable int postId, @NotBlank @RequestBody String inputPassword) {
        String savedPassword = postService.findPostPasswordById(postId);

        String decodePassword = URLDecoder.decode(inputPassword.substring(0, inputPassword.length()-1));

        if(!decodePassword.equals(savedPassword)) {
            throw PostErrorCode.PASSWORD_CHECK_ERROR.defaultException();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("비밀번호 일치"));
    }


    /**
     * multipart 리스트를 업로드와 저장
     * @param postId - id
     * @param files - multipart list
     */
    private void fileUploadAndSave(int postId, List<MultipartFile> files) {
        if(!CollectionUtils.isEmpty(files)) {
            List<FileRequestDto> uploadFiles = fileUtils.uploadFiles(files);

            List<FileVo> fileVoList = new ArrayList<>();
            uploadFiles.stream().forEach(f -> {
                f.setPostId(postId);
                fileVoList.add(toVo(f));
            });

            fileService.saveFiles(fileVoList);
        }
    }

    /**
     * 게시물 등록 및 수정시 비밀번호와 비밀번호 확인 검증
     * @param postRequestDto - request
     * @return - true
     */
    private boolean validation(PostRequestDto postRequestDto) {
        //게시물 등록및 수정시
        String inputPassword = postRequestDto.getPassword();
        String checkPassword = postRequestDto.getCheckPassword();
        if(!inputPassword.equals(checkPassword)) {
            throw PostErrorCode.PASSWORD_CHECK_ERROR.defaultException();
        }

        //게시물 수정시
        if(postRequestDto.getPostId() != 0) {
            int postId = postRequestDto.getPostId();
            String savedPassword = postService.findPostPasswordById(postId);
            if(!inputPassword.equals(savedPassword)) {
                throw PostErrorCode.PASSWORD_CHECK_ERROR.defaultException();
            }
        }
        return true;
    }


}
