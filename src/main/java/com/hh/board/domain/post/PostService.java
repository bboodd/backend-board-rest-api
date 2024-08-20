package com.hh.board.domain.post;

import com.hh.board.common.dto.SearchDto;
import com.hh.board.common.exception.PostNotFoundException;
import com.hh.board.common.paging.Pagination;
import com.hh.board.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.hh.board.common.vo.SearchVo.toVo;
import static com.hh.board.domain.post.PostResponseDto.toDto;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    //TODO:받을때 dto안됨

    private final PostMapper postMapper;

    /**
     * 게시글 등록
     * @param postVo
     * @return 등록된 postId
     */

    @Transactional
    public int savePost(PostVo postVo){
        postMapper.savePost(postVo);
        int postId = postVo.getPostId();

        return postId;
    }

    /**
     * 검색 게시글 목록 조회
     * @param searchDto
     * @return 페이징 객체
     */

    public PagingResponse<PostResponseDto> findAllPostBySearch(SearchDto searchDto){

        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = postMapper.count(toVo(searchDto));
        if(count < 1){
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입 객체에 계산된 페이지 정보 저장
        // pagination 객체 생성자에 searchDto.setPagination 포함
        Pagination pagination = new Pagination(count, searchDto);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<PostVo> postList = postMapper.findAllPostBySearch(toVo(searchDto));

        List<PostResponseDto> result = postList.stream()
                .map(PostResponseDto::toDto).collect(toList());
        return new PagingResponse<>(result, pagination);
    }

    /**
     * 게시글 단건 조회
     * @param postId
     * @return 게시글 반환dto
     */

    public PostResponseDto findPostById(int postId){
        PostVo postVo = postMapper.findPostById(postId);
        if(postVo == null){
            throw new PostNotFoundException();
        }
        PostResponseDto result = toDto(postVo);
        return result;
    }

    /**
     * 게시글 비밀번호 찾기
     * @param postId
     * @return 비밀번호
     */

    public String findPostPasswordById(int postId){
        String password = postMapper.findPostPasswordById(postId);
        return password;
    }

    /**
     * 조회수 1 증가
     * @param postId
     * @return 성공시 1 실패시 0
     */

    public int increaseViewCountById(int postId){
        int result = postMapper.increaseViewCountById(postId);
        return result;
    }

    /**
     * 게시글 삭제
     * @param postId
     * @return 성공시 1 실패시 0
     */

    public int deletePostById(int postId){
        int result = postMapper.deletePostById(postId);
        return result;
    }

    /**
     * 게시글 업데이트
     * @param postVo
     * @return 성공시 1 실패시 0
     */

    @Transactional
    public int updatePost(PostVo postVo) {
        int result = postMapper.updatePost(postVo);
        return result;
    }

}
