package com.zx.cruddemo.util;

import com.zx.cruddemo.service.BasicService;
import com.zx.cruddemo.specification.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {

    static Sort sort = null;

    //获取request中的sort信息
    public static void getSortInfos(String sortKey, String sortOrder){
        if(sortKey==""){  //不能写null，后台传来的就是“”
            sort = new Sort(Sort.Direction.DESC,"id");
        }else{
            if(sortOrder.equals("normal")){
                sort = new Sort(Sort.Direction.DESC,"id");
            }else{
                sort = new Sort(Sort.Direction.fromString(sortOrder),sortKey);
            }
        }
    }

    //显示的分页结果
    public static Map<String, Object> getCurrentPageInfos(BasicService bs, String cacheName, Integer page, Integer size){
        Map<String, Object> map = new HashMap<String, Object>();
        Page list = bs.findAllPagebleT(new PageRequest(page-1, size, sort), cacheName);  //这个PageRequest咋回事?
        int total = bs.findAllT(cacheName).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //查询的分页结果
    public static Map<String, Object> getCriteriaPageInfos(BasicService bs, Criteria criteria, Integer page, Integer size){
        Map<String, Object> map = new HashMap<String, Object>();
        Page list = bs.findPageTsByXX(criteria, new PageRequest(page-1, size, new Sort(Sort.Direction.DESC,"id")));  //这个PageRequest过时了咋办
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
