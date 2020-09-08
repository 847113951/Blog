package com.ylb.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ylb.Mapper.BlogMapper;
import com.ylb.Pojo.Blog;
import com.ylb.Pojo.Comment;
import com.ylb.Service.BlogService;
import com.ylb.Service.CommentService;
import com.ylb.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper mapper;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;


    @Override
    public Blog getBlogById(Long id) {
        Blog blog = mapper.selectByPrimaryKey(id);
        return blog;
    }


    @Override
    public PageInfo<Blog> getBlogList(Integer pageNumber, Integer pageSize, Blog blog) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Blog> blog1 = mapper.getBlog(blog);
        PageInfo pageInfo = new PageInfo(blog1);
        return pageInfo;
//        BlogExample blogExample = new BlogExample();
//        BlogExample.Criteria criteria = blogExample.createCriteria();
//        if (blog.getTitle() != null) {
//            criteria.andTitleLike("%" + blog.getTitle() + "%");
//        }
//        if (blog.getTypeId() != null) {
//            criteria.andTypeIdEqualTo(blog.getTypeId());
//        }
//        if (blog.getRecommened() != null) {
//            criteria.andRecommenedEqualTo(blog.getRecommened());
//        }
//        List<Blog> blogs = mapper.selectByExample(blogExample);

    }

    @Transactional
    @Override
    public int addBlog(Blog blog) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        blog.setCreateTime(parse);
        blog.setUpdateTime(parse);
        blog.setViews(0);
        int insert = mapper.insert(blog);
        return mapper.saveBlogTags(blog.getId(), blog.getTags());
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(parse);
        System.out.println(blog);
        mapper.deleteBlogTag(blog.getId());
        mapper.updateByPrimaryKeySelective(blog);
        blog.setUpdateTime(parse);
        return mapper.saveBlogTags(blog.getId(), blog.getTags());
    }


    @Transactional
    @Override
    public int deleteBlogById(Long id) {
        commentService.deleteCommentByBlogId(id);
        tagService.deleteBlogTag(id);
        mapper.deleteBlogTag(id);
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Blog> getByPublished(int pageNumber, int size) {
        PageHelper.startPage(pageNumber, size);
        List<Blog> blog1 = mapper.getByPublished();

        return new PageInfo(blog1);
    }

    @Override
    public List<Blog> getRecommened() {
        return mapper.getRecommened();
    }

    @Override
    public PageInfo<Blog> search(Integer pageNum, Integer pageSiz, String query) {
        PageHelper.startPage(pageNum, pageSiz);
        PageInfo pageInfo = new PageInfo(mapper.search(query));
        return pageInfo;
    }

    public Blog getByPublishedById(Long id) {
        Blog blog = mapper.getByPublishedById(id);
        blog.setViews(blog.getViews() + 1);
        mapper.updateByPrimaryKey(blog);
        return blog;
    }

    @Override
    public Blog MyGetBlogById(Long id) {
        return mapper.MyGetBlogById(id);
    }

    @Override
    public PageInfo<Blog> getByTypeId(int pageNumber, int size, Long id) {
        PageHelper.startPage(pageNumber, size);
        List<Blog> blogs = mapper.getType(id);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        return blogPageInfo;
    }

    @Override
    public PageInfo<Blog> getByTagId(int pageNumber, int size, Long id) {
        PageHelper.startPage(pageNumber, size);
        List<Blog> blogs = mapper.getBlogByTag(id);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        return blogPageInfo;
    }


    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> groupYear = mapper.findGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : groupYear) {
            map.put(year, mapper.countBlog(year));
        }
        return map;
    }

    @Override
    public Integer getCountBlog() {
        return mapper.getCountBlog();
    }

    @Override
    public List<Blog> getFooterBlogList() {
        return mapper.getFooterBlogList();
    }
}
