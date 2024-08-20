package com.hh.board.domain.post;

import com.hh.board.common.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    int savePost(PostVo postVo);

    List<PostVo> findAllPostBySearch(SearchVo searchVo);

    int count(SearchVo searchVo);

    PostVo findPostById(int postId);

    int deletePostById(int postId);

    int increaseViewCountById(int postId);

    int updatePost(PostVo postVo);

    String findPostPasswordById(int postId);
}
