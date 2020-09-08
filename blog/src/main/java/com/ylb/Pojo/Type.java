package com.ylb.Pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class Type implements Serializable {
    private Long id;
    @Size(max = 10, message = "类型长度不能超过10位字符")
    @NotEmpty(message = "类型名称不能为空")
    private String name;

    private List<Blog> blogs;

    private static final long serialVersionUID = 1L;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Integer getCounts() {
        return counts;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                ", counts=" + counts +
                '}';
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    private Integer counts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Type() {
    }

    public Type(Long id, String name, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }

}