package work;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\7\5 0005.
 */
public class WorkTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        String s = list.toString().replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(s);
        JSONArray arry = new JSONArray();
        arry = JSONArray.parseArray(list.toString());
        System.out.println(arry);
    }
}
