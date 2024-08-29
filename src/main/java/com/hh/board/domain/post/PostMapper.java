package com.hh.board.domain.post;

import com.hh.board.common.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * @param postVo - 게시글 정보
     * @return - 0 or 1
     */
    int savePost(PostVo postVo);

    /**
     * 게시글 list 조회
     * @param searchVo - 검색조건
     * @return - 게시글 정보 list
     */
    List<PostVo> findAllPostBySearch(SearchVo searchVo);

    /**
     * 게시글 수 조회
     * @param searchVo - 검색조건
     * @return - many
     */
    int countAllPostBySearch(SearchVo searchVo);

    /**
     * 게시글 조회
     * @param postId - id
     * @return - 게시글 정보
     */
    PostVo findPostById(int postId);

    /**
     * 게시글 삭제
     * @param postId - id
     * @return - 0 or 1
     */
    int deletePostById(int postId);

    /**
     * 게시글 조회수 1증가
     * @param postId - id
     * @return - 0 or 1
     */
    int increaseViewCountById(int postId);

    /**
     * 게시글 업데이트
     * @param postVo - 업데이트 정보
     * @return - 0 or 1
     */
    int updatePost(PostVo postVo);

    /**
     * 게시글 비밀번호 조회
     * @param postId - id
     * @return - 비밀번호
     */
    String findPostPasswordById(int postId);
}
