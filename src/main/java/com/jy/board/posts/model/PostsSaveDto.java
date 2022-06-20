package com.jy.board.posts.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@NoArgsConstructor
public class PostsSaveDto {

    private PostsDto postsDto;

    private List<TagsDto> tagsDto;
}
