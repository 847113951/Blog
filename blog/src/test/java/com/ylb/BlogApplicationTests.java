package com.ylb;

import com.fasterxml.jackson.core.io.DataOutputAsStream;
import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Blog;
import com.ylb.Pojo.Comment;
import com.ylb.Pojo.Tag;
import com.ylb.Pojo.Type;
import com.ylb.Service.*;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class BlogApplicationTests {

    public static int candy(int[] ratings) {
        int sum = 0;
        if (ratings.length == 1) {
            return 1;
        }
        int[] arrs = new int[ratings.length];
        Arrays.fill(arrs, 1);
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i] > ratings[i + 1]) {
                arrs[i] += 1;
            }
        }

        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i] > ratings[i - 1]) {
                arrs[i] += 1;
            }
        }

        for (int i = 0; i < arrs.length; i++) {
            sum += arrs[i];
        }

        return sum;
    }


    @Test
    public void string() throws  Exception{
        InputStreamReader input=new InputStreamReader(System.in);
        BufferedReader binput=new BufferedReader(input);
        String[] strs=binput.readLine().(" ");

    }

}
