package com.example.EAS.dto;

import com.example.EAS.vo.OrgVO;

import java.util.ArrayList;
import java.util.List;

public class deno {

    public static void main(String[] args) {
        OrgVO orgVO = new OrgVO();
        List<OrgVO> orgVOList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            OrgVO orgVO1 = new OrgVO();
            orgVO1.setTitle(""+i);
            boolean add = orgVO.getOrgVOList().add(orgVO1);
        }
        System.out.println(orgVO);
    }

}
