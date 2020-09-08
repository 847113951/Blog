package com.ylb.Util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkdownUtils {

    /**
     * markdown格式转换成HTML格式
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /**
     * 增加扩展[标题锚点，表格生成]
     * Markdown转换成HTML
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtmlExtensions(String markdown) {
        //h标题生成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的HTML
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /**
     * 处理标签的属性
     */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            //改变a标签的target属性为_blank
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }

    }
        public static void main(String[] args) {
            String str = "## 1、Spring Boot 简介\n" +
                    "\n" +
                    "> 简化Spring应用开发的一个框架；\n" +
                    ">\n" +
                    "> 整个Spring技术栈的一个大整合；\n" +
                    ">\n" +
                    "> J2EE开发的一站式解决方案；\n" +
                    "\n" +
                    "## 2、微服务\n" +
                    "\n" +
                    "2014，martin fowler\n" +
                    "\n" +
                    "微服务：架构风格（服务微化）\n" +
                    "\n" +
                    "一个应用应该是一组小型服务；可以通过HTTP的方式进行互通；\n" +
                    "\n" +
                    "单体应用：ALL IN ONE\n" +
                    "\n" +
                    "微服务：每一个功能元素最终都是一个可独立替换和独立升级的软件单元；\n" +
                    "\n" +
                    "[详细参照微服务文档](https://martinfowler.com/articles/microservices.html#MicroservicesAndSoa)\n";
            String s = MarkdownUtils.markdownToHtmlExtensions(str);
            System.out.println(s);
    }


}
