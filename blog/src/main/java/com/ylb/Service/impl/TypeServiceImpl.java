package com.ylb.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.ylb.Mapper.TypeMapper;
import com.ylb.Pojo.Type;
import com.ylb.Service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.insert(type);
    }

    @Transactional
    @Override
    public int removeTypeById(Long id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        Type type1 = typeMapper.selectByPrimaryKey(type.getId());
        if (type1 == null) {
            try {
                throw new NotFoundException("不存在");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.selectByPrimaryKey(id);
    }


    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllTypes();
    }

    @Override
    public PageInfo<Type> getTypeList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<Type>(typeMapper.getAllTypes());
    }

    @Override
    public List<Type> getTypeLimit() {
        return typeMapper.getTypeLimit();
    }

    @Override
    public List<Type> getTypeAndCountBlog() {
        return typeMapper.getTypeAndCountBlog();
    }


}
