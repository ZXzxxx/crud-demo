package com.zx.cruddemo.util;

import com.zx.cruddemo.service.BasicService;
import com.zx.cruddemo.specification.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {

    //显示的分页结果
    public static Map<String, Object> getCurrentPageInfos(BasicService bs, String cacheName, Integer page, Integer size){
        Map<String, Object> map = new HashMap<String, Object>();
        Page list = bs.findAllPagebleT(new PageRequest(page-1, size), cacheName);  //这个PageRequest咋回事?
        int total = bs.findAllT(cacheName).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //查询的分页结果
    public static Map<String, Object> getCriteriaPageInfos(BasicService bs, Criteria criteria, Integer page, Integer size){
        Map<String, Object> map = new HashMap<String, Object>();
        Page list = bs.findPageTsByXX(criteria, new PageRequest(page-1, size));  //这个PageRequest过时了咋办
        int total = bs.findTsByXX(criteria).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //将String串转化为List集合
    public static List IDConverter(String ids) {
        String[] stringIds = ids.split(","); //用数组效率高
        List<Integer>  arrIds = new ArrayList<Integer>();
        if(stringIds[0] != ""){
            for (int i = 0; i<stringIds.length; i++) {
                arrIds.add(Integer.parseInt(stringIds[i]));
            }
        }
        return arrIds;
    }
}
