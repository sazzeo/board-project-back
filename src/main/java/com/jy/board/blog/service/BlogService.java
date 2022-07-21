package com.jy.board.blog.service;

import com.jy.board.blog.dao.BlogRepository;
import com.jy.board.blog.dao.CategoryRepository;
import com.jy.board.blog.model.BlogDto;
import com.jy.board.blog.model.CategoryDto;
import com.jy.board.blog.model.MemberBlogDto;
import com.jy.board.member.model.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Map<String, Object> selectProfileBoxInfo(String url) {
        return blogRepository.selectProfileBoxInfo(url);
    }

    @Transactional
    public MemberBlogDto selectUserBlogProfile(MemberDto memberDto) {
        String id = memberDto.getId();
        return blogRepository.selectProfile(id);
    }


    @Transactional
    public int updateBlog(BlogDto blogDto) {
        return blogRepository.updateBlog(blogDto);
    }

    @Transactional
    public List<CategoryDto> selectCategories(String id) {
        List<CategoryDto> categories = categoryRepository.selectCategories(id);
        return categories;
    }

    @Transactional
    public int updateCategories(MemberDto memberDto, List<CategoryDto> newCategoryDto){
        List<CategoryDto> oldCategoryDto = categoryRepository.selectCategoriesForUpdate(memberDto.getId());

        System.out.println("새카테고리: " + newCategoryDto);
        System.out.println("기존 카테고리: " + oldCategoryDto);
        
        List<Long> removeCategorySeqList = new ArrayList<>();
        
        //null인경우 일단 db에 데이터 추가하고 list 에서는 제외함 , 이후 CategorySeq로 정렬시킴
        Iterator<CategoryDto> newCategoryIter = newCategoryDto.stream().filter(categoryDto -> {
            boolean isNew = categoryDto.getCategorySeq() == null;
            if(isNew) {
                System.out.println("추가로직 넣을곳 : " + categoryDto.getTitle());
            }
            return !isNew; //추가후 제거 
        }).sorted(Comparator.comparing(CategoryDto::getCategorySeq)).iterator();

        //새 카테고리는 2차원 구조로, 원래 카테고리는 1차원 구조로 되어있음
        //정렬이 보장 되어야 함.

        for (CategoryDto oldCategory : oldCategoryDto) {
            if(newCategoryIter.hasNext()) {
                CategoryDto newCategory = newCategoryIter.next();
                Long oldCategorySeq = oldCategory.getCategorySeq();
                Long newCategorySeq = newCategory.getCategorySeq();

                //만약 둘이 같은 경우?
                if(oldCategorySeq.equals(newCategorySeq)) {
                    if(!oldCategory.equals(newCategory)) {
                        System.out.println("둘이 다름!");
                    }
                }
                //만약 둘이 다른 경우? => new쪽에서 지워진 것
                else {
                    removeCategorySeqList.add(oldCategorySeq);
                }
            }

        }

      //  System.out.println(newCategoryDto);
        //정렬 시키기
        //근데 만약에 부모랑 자식을 같이 추가하면? 이게 또 문제임
//        Iterator<CategoryDto> newCategoryIter = oldCategoryDto.iterator();
//        int index = 0;
//        //없는 애 찾기
//        for(CategoryDto oldCategory:oldCategoryDto) {
//            //만약 new가 있는경우
//            if(newCategoryIter.hasNext()) {
//
//
//            }else {
//
//            }
//
//            Long newCategorySeq = newCategoryIter.next().getCategorySeq();
//            //만약 둘이 같으면
//            if(oldCategory.getCategorySeq().equals(newCategorySeq)) continue;
//            else()
//        }

        //없는애 => remove
        //새로 생긴애 => insert
        //기존애 => 달라졌으면 update


        System.out.println("정렬후:" + newCategoryDto);

        return 1;
    }
}
