package com.ylb.Service;

import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Tag;
import com.ylb.Pojo.Type;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);

    int removeTagById(Long id);

    int updateTag(Tag tag);

    Tag getTagById(Long id);

    PageInfo<Tag> getTagList(int pageNum, int pageSize);

    Tag getTagByName(String name);

    List<Tag> Tags();

    List<Tag> getTagsByIds(String ids);

    String getTagsById(Long id);

    List<Tag> getTagLimit();

    List<Tag> getTags();

    Integer deleteBlogTag(Long id);
}
