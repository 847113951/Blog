package com.ylb.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ylb.Mapper.TagMapper;
import com.ylb.Pojo.Tag;
import com.ylb.Pojo.Type;
import com.ylb.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public int removeTagById(Long id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateByPrimaryKey(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Tag> getTagList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<Tag>(tagMapper.getAllTags());
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> Tags() {
        return tagMapper.getAllTags();
    }

    @Override
    public List<Tag> getTagsByIds(String ids) {
        List<Long> list = new ArrayList<>();
        System.out.println("ids" + ids);
        if (ids != null && !"".equals(ids)) {
            String[] arr = ids.split(",");
            for (int i = 0; i < arr.length; i++) {
                list.add(new Long(arr[i]));
            }
        }
        System.out.println("集合" + list);
        return tagMapper.getTagsByIds(list);
    }

    @Override
    public String getTagsById(Long id) {
        List<Long> tagsById = tagMapper.getTagsById(id);
        StringBuffer stringBuffer = new StringBuffer();
        for (Long str : tagsById) {
            stringBuffer.append(str + ",");
        }
        if (stringBuffer.length() != 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    @Override
    public List<Tag> getTagLimit() {
       return tagMapper.getTagLimit();
    }

    @Override
    public List<Tag> getTags() {
        return tagMapper.getTags();
    }

    @Override
    public Integer deleteBlogTag(Long id) {
        return tagMapper.deleteBlogTag(id);
    }


}
