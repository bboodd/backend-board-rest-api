package com.hh.board.domain.post;

import com.hh.board.common.exception.PostErrorCode;
import com.hh.board.common.paging.Pagination;
import com.hh.board.common.paging.PagingResponse;
import com.hh.board.common.vo.SearchVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hh.board.domain.post.PostResponseDto.toDto;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    /**
     * 게시글 등록
     * @param postVo - 저장할 정보
     * @return 등록된 postId
     */

    @Transactional
    public int savePost(PostVo postVo) {
        postMapper.savePost(postVo);
        int postId = postVo.getPostId();

        return postId;
    }

    /**
     * 검색 게시글 수 조회
     * @param searchVo - 검색조건
     * @return - int
     */

    public int countAllPostBySearch(SearchVo searchVo) {
        return postMapper.countAllPostBySearch(searchVo);
    }

    /**
     * 검색 게시글 목록 조회
     * @param searchVo - 검색조건
     * @param pagination - 페이징
     * @return 페이징 객체
     */

    public PagingResponse<PostResponseDto> findAllPostBySearch(SearchVo searchVo, Pagination pagination) {

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<PostVo> postList = postMapper.findAllPostBySearch(searchVo);

        List<PostResponseDto> result = postList.stream()
                .map(PostResponseDto::toDto).collect(toList());
        return new PagingResponse<>(result, pagination);
    }

    /**
     * 게시글 단건 조회
     * @param postId - id
     * @return 게시글 반환dto
     */

    public PostResponseDto findPostById(int postId) {
        PostVo postVo = postMapper.findPostById(postId);
        if(postVo == null) {
            throw PostErrorCode.POST_NOT_FOUND.defaultException();
        }
        PostResponseDto result = toDto(postVo);
        return result;
    }

    /**
     * 게시글 비밀번호 찾기
     * @param postId - id
     * @return 비밀번호
     */

    public String findPostPasswordById(int postId) {
        String password = postMapper.findPostPasswordById(postId);
        return password;
    }

    /**
     * 조회수 1 증가
     * @param postId - id
     * @return 성공시 1 실패시 0
     */

    public int increaseViewCountById(int postId) {
        int result = postMapper.increaseViewCountById(postId);
        return result;
    }

    /**
     * 게시글 삭제
     * @param postId - id
     * @return id
     */

    public int deletePostById(int postId) {
        postMapper.deletePostById(postId);
        return postId;
    }

    /**
     * 게시글 업데이트
     * @param postVo - 업데이트 정보
     * @return 성공시 1 실패시 0
     */

    @Transactional
    public int updatePost(PostVo postVo) {
        int result = postMapper.updatePost(postVo);
        return result;
    }

}
