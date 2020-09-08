package com.ylb.Service;

import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Blog;
import com.ylb.Pojo.Tag;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlogById(Long id);

    PageInfo<Blog> getBlogList(Integer pageNumber, Integer pageSize, Blog blog);

    int addBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlogById(Long id);

    PageInfo<Blog> getByPublished(int pageNumber, int size);

    List<Blog> getRecommened();

    PageInfo<Blog> search(Integer pageNum, Integer pageSiz, String query);

    Blog getByPublishedById(Long id);

    Blog MyGetBlogById(Long id);

    PageInfo<Blog> getByTypeId(int pageNumber, int size, Long id);

    PageInfo<Blog> getByTagId(int pageNumber, int size, Long id);



    Map<String, List<Blog>> archiveBlog();

    Integer getCountBlog();

    List<Blog> getFooterBlogList();
}
