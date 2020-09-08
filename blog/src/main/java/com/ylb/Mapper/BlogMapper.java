package com.ylb.Mapper;

import com.ylb.Pojo.Blog;
import com.ylb.Pojo.Example.BlogExample;

import java.util.List;

import com.ylb.Pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);


    List<Blog> selectByExampleWithBLOBs(BlogExample example);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);


    List<Blog> getBlog(Blog blog);


    int saveBlogTags(@Param("id") Long id, @Param("tids") List<Tag> longs);

    int deleteBlogTag(Long id);

    List<Blog> getByPublished();


    List<Blog> getRecommened();

    List<Blog> search(String query);

    Blog getByPublishedById(Long id);

    Blog MyGetBlogById(Long id);

    List<Blog> getType(Long id);

    List<Blog> getBlogByTag(Long id);

    List<String> findGroupYear();

    List<Blog> countBlog(String year);


    Integer getCountBlog();

    List<Blog> getFooterBlogList();
}