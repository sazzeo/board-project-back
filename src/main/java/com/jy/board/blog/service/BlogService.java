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

    /**
     * 설명 : 유저 블로그 진입시 블로그정보 + 사용자 정보 가져오기
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param url
     * @return
     */
    @Transactional
    public Map<String, Object> selectProfileBoxInfo(String url) {
        return blogRepository.selectProfileBoxInfo(url);
    
    }

    /**
     * 설명 : 로그인 유저의 정보 가져오기
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param memberDto
     * @return
     */
    @Transactional
    public MemberBlogDto selectUserBlogProfile(MemberDto memberDto) {
        String id = memberDto.getId();
        return blogRepository.selectProfile(id);
    }


    /**
     * 설명 : 블로그 정보 update
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param blogDto
     * @return
     */
    @Transactional
    public int updateBlog(BlogDto blogDto) {
        return blogRepository.updateBlog(blogDto);
    }


    /**
     * 설명 : 카테고리 box 용 데이터 불러오기
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim 
     * @param id
     * @return
     */
    @Transactional
    public List<CategoryDto> selectCategories(String id) {
        List<CategoryDto> categories = categoryRepository.selectCategories(id);
        return categories;
    }

    /**
     * 설명 : 포스트 작성시 카테고리 선택용
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim
     * @param id
     * @return
     */
    @Transactional
    public List<CategoryDto> selectCategoriesForInsertPosts(String id) {
        return categoryRepository.selectCategoriesForInsertPosts(id);
    }

    /**
     * 설명 : 카테고리 update 기능
     * 작성일 : 2022. 07. 28.
     * @author : jy.lim
     * @param memberDto
     * @param newCategoryList
     * @return
     */
    @Transactional
    public int updateCategories(MemberDto memberDto, List<CategoryDto> newCategoryList){
        List<CategoryDto> oldCategoryList = categoryRepository.selectCategoriesForUpdate(memberDto.getId());
        List<Long> removeCategorySeqList = new ArrayList<>();

        //같은게 있는지 판단하는 로직 버블 정렬해서 바로 옆에있는애랑 같으면 같다고 판단?
        //원소가 같은게 있는지 판단하는 로직=>set으로 만들어서 줄어들었으면 같은애로 판단


        //null 인경우 일단 db에 데이터 추가하고 list 에서는 제외함 , 이후 CategorySeq로 정렬시킴
        PriorityQueue<CategoryDto> newCategoryQueue = newCategoryList.stream().filter(categoryDto -> {
            boolean isNew = categoryDto.getCategorySeq() == null;
            categoryDto.setBlogSeq(oldCategoryList.get(0).getBlogSeq()); //oldCategoryList는 반드시 1개이상있어야 함.
            if(isNew) {
                if(categoryDto.getChildren()==null || categoryDto.getChildren().size()==0) {
                    categoryRepository.insertCategory(categoryDto);
                }else {
                    categoryRepository.insertCategory(categoryDto);
                    Long upCategorySeq = categoryDto.getCategorySeq();
                    for(CategoryDto child :categoryDto.getChildren()) {
                        child.setUpCategory(upCategorySeq);
                        categoryRepository.insertCategory(categoryDto);
                    }
                }
            }
            return !isNew; //추가후 리스트에서 제거 후 우선순위 큐로 만듦.
        }).collect(Collectors.toCollection(()-> new PriorityQueue<>(Comparator.comparing(CategoryDto::getCategorySeq))));

        for(CategoryDto oldCategory : oldCategoryList) {
            CategoryDto newCategory = newCategoryQueue.peek();
            Long oldCategorySeq = oldCategory.getCategorySeq();
            if(newCategory == null) {
                removeCategorySeqList.add(oldCategorySeq);
                continue;
            }
            Long newCategorySeq = newCategory.getCategorySeq();

            //만약 두개의 시퀀스가 같은경우
            if(oldCategorySeq.equals(newCategorySeq)) {
                if(!newCategory.equals(oldCategory)){ //두 객체가 완전 같은 객체인지 판단한다. 같지 않을 경우 update
                    categoryRepository.updateCategory(newCategory);
                }
                newCategoryQueue.poll();
            }else { //같지 않을 경우 remove 로 간주한다.
                removeCategorySeqList.add(oldCategorySeq);
            }
        }
        if(removeCategorySeqList.size() >0 ){
            categoryRepository.deleteCategory(removeCategorySeqList);
        }

        return 1;
    }
}
