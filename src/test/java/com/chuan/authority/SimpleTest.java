package com.chuan.authority;

import com.google.common.collect.HashMultimap;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/30 0:05
 */
public class SimpleTest {

    @Test
    public void splitTest(){
        String ip="127.0.0.1";
        String s = ip.split(",")[0];
        System.out.println(s);
    }

    @Test
    public void multiMapTest(){
    }
}
