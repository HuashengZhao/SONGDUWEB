package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConContractbillMapper;
import com.example.EAS.mapper.TConProgrammingcontractMapper;
import com.example.EAS.model.TConProgrammingcontract;
import com.example.EAS.service.ITConProgrammingcontractService;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.ProgramConVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
@Service
public class TConProgrammingcontractServiceImpl extends ServiceImpl<TConProgrammingcontractMapper, TConProgrammingcontract> implements ITConProgrammingcontractService {

    @Autowired
    private TConProgrammingcontractMapper mapper;
    @Autowired
    private TConContractbillMapper contractbillMapper;

    @Override
    public PageBean<ProgramConVO> getProgramCon(ProgramConVO vo) {
        long st = System.currentTimeMillis();
        ProgramConVO programConVO = new ProgramConVO();
        if (Util.isEmpty(vo.getContractTypeId())) {
            return null;
        }
        if (Util.isEmpty(vo.getProjectId())) {
            return null;
        }
        List<ProgramConVO> programConVOList = new ArrayList<>();
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        if (vo.getOperationType()!=null && vo.getOperationType()==1) {
            programConVOList = mapper.selectDataCanBeLinked(vo);
        }else if (vo.getOperationType()!=null && vo.getOperationType()==2){
            programConVOList = mapper.selectDataHasBalance(vo);
        }

        if (Util.isNotEmpty(programConVOList)) {
            for (ProgramConVO conVO : programConVOList) {
                String longNumber = conVO.getLongNumber();
                if (Util.isNotEmpty(longNumber)) {
                    conVO.setLongNumber(longNumber
                            .replace("!", ".")
                            .replace("-", "."));
                }
            }
            programConVO.setProgramConVOS(programConVOList);
        }
        PageBean<ProgramConVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) programConVOList).getTotal());
        pageBean.setPageData(programConVOList);
        long et = System.currentTimeMillis();
        System.out.println("查询合约规划耗时：" + (et - st) + "ms");
        return pageBean;
    }
}
