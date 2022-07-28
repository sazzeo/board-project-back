package com.jy.board.posts.service;

import com.jy.board.blog.dao.BlogRepository;
import com.jy.board.blog.dao.CategoryRepository;
import com.jy.board.blog.model.BlogDto;
import com.jy.board.common.exception.CustomException;
import com.jy.board.common.exception.ExceptionCode;
import com.jy.board.common.model.Pageable;
import com.jy.board.member.model.MemberDto;
import com.jy.board.posts.dao.PostsRepository;
import com.jy.board.posts.model.PostsDto;
import com.jy.board.posts.model.TagsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    private final BlogRepository blogRepository;


    /**
     * 설명 : 게시글 리스트 조회
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim
     * @param pageable
     * @return
     */
    @Transactional
    public List<PostsDto> selectPosts(Pageable pageable) {
        List<PostsDto> res = postsRepository.selectPosts(pageable);
        return res;
    }


    /**
     * 설명 : 카테고리별 글 선택 기능
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim
     * @param url
     * @param parentCategory
     * @param childCategory
     * @return
     */
    //카테고리별 글 선택기능
    @Transactional
    public List<PostsDto> selectPosts(String url , String parentCategory , String childCategory) {
        if(parentCategory == null) { //전체보기로 검색하기
            return postsRepository.selectAllPost(url);
        }
        if(childCategory == null) {

            System.out.println(postsRepository.selectPostsOfParentCategory(url , parentCategory));
            return postsRepository.selectPostsOfParentCategory(url , parentCategory);
        }

        return postsRepository.selectPostsOfChildCategory(url , childCategory);
    }



    /**
     * 설명 : 검색기능...(미완성)
     * 작성일 : 2022. 07. 01.
     * @author : jy.lim
     * @param pageable
     * @param s
     * @param o
     * @return
     */
  @Transactional
    public List<PostsDto> selectPosts(Pageable pageable ,String s,String o) {
      List<PostsDto> res = null;
        if(s !=null) {
            res = postsRepository.selectPostsAsOption(pageable , s , o );
        }else {
            res = postsRepository.selectPosts(pageable);
        }
        return res;
    }


    /**
     * 설명 : 포스트 단건 조회
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim
     * @param postsSeq
     * @return
     */
    @Transactional
    public PostsDto selectPostBySeq(Long postsSeq) {
        PostsDto postsDto = postsRepository.selectPost(postsSeq);

        if(postsDto == null) throw new CustomException(ExceptionCode.PATH_ERROR);
        postsRepository.updatePostsViews(postsSeq); //조회수 up
        return postsDto;
    }


    /**
     * 설명 : 포스트 작성
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param memberDto
     * @param postsDto
     */
    @Transactional
    public void insertPost(MemberDto memberDto , PostsDto postsDto ) {

        postsDto.setId(memberDto.getId());
        postsRepository.insertPost(postsDto);
        for (TagsDto dto : postsDto.getTagList()) {
            dto.setPostsSeq(postsDto.getPostsSeq());
            postsRepository.insertTag(dto);
        }
       // categoryRepository.updateCategoryTotalCnt(postsDto.getCategorySeq());
    }

    /**
     * 설명 : 게시글 수정
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param postsSeq
     * @param postsDto
     */
    @Transactional
    public void updatePost(Long postsSeq , PostsDto postsDto) {
        postsDto.setPostsSeq(postsSeq);

        //원래 태그
        List<TagsDto> tags = postsRepository.selectPost(postsSeq).getTagList();
        //수정된 태그
        List<TagsDto> newTags = postsDto.getTagList();

        //없어진 태그 삭제하기
        List<Long> tagSeqList = tags.stream()
                .filter(tag-> !newTags.contains(tag))
                .map(TagsDto::getTagSeq)
                .collect(Collectors.toList());

        if(tagSeqList.size()>0) {
            postsRepository.deleteTagsBySeqList(tagSeqList);
        }

        //새로 생긴 태그 추가하기
        newTags.removeAll(tags);
        newTags.forEach(insertTag-> {
            insertTag.setPostsSeq(postsSeq);
            postsRepository.insertTag(insertTag);
        });

        postsRepository.updatePost(postsDto);
    }

    
    /**
     * 설명 : 태그로 posts 서칭
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param postsSeq
     * @return
     */
    @Transactional
    public List<String> selectTagListBySeq(Long postsSeq) {
        List<String> TagNameList = postsRepository.selectTagsBySeq(postsSeq).stream().map(TagsDto::getTagName).collect(Collectors.toList());

        return TagNameList;
    }

    /**
     * 설명 : tag box 정보 가져오기
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param id
     * @return
     */
    @Transactional
    public List<TagsDto> selectTagsOrderByTop(String id) {
        BlogDto blogInfo = blogRepository.selectTagInfo(id);
        if(blogInfo == null ) throw new CustomException(ExceptionCode.PATH_ERROR);
        boolean tagYn =  blogInfo.getTagYn();

        if(!tagYn) {
            return null;
        }

        int tagMin = blogInfo.getTagMin();  //태그 몇개부터 노출할건지?
        int tagCardinal = blogInfo.getTagCardinal(); //태그의 총 개수는 몇개로할건지?
        List<TagsDto> tagList = postsRepository.selectTagsOrderByTop(id , tagMin , tagCardinal);


        return tagList;
    }


    /**
     * 설명 : 태그이름으로 posts 검색
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param id
     * @param tagName
     * @return
     */
    @Transactional
    public List<PostsDto> selectPostsByTagName(String id , String tagName  ) {
        return postsRepository.selectPostsByTagName(id ,tagName);
    }

    /**
     * 설명 : 게시글 삭제
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param postsSeq
     * @param memberDto
     */
    @Transactional
    public void deletePosts(Long postsSeq , MemberDto memberDto) {
        PostsDto postsDto = postsRepository.selectPost(postsSeq);
        if(postsDto == null) throw new CustomException(ExceptionCode.PATH_ERROR);
        if(!memberDto.getId().equals(postsDto.getId())) throw new CustomException(ExceptionCode.FORBIDDEN);
        postsRepository.deleteTagsByPostsSeq(postsSeq);
        postsRepository.deletePostsBySeq(postsSeq);
    }
}
