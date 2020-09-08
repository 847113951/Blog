package com.ylb.Service;

import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    int removeTypeById(Long id);

    int updateType(Type type);

    Type getTypeById(Long id);

    PageInfo<Type> getTypeList(int pageNum, int pageSize);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getTypeLimit();

    List<Type> getTypeAndCountBlog();
}
