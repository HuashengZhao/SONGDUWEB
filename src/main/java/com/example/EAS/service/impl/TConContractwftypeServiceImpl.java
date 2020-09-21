package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConContractwftypeMapper;
import com.example.EAS.model.TConContractwftype;
import com.example.EAS.service.ITConContractwftypeService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.ContractProcessTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
@Service
public class TConContractwftypeServiceImpl extends ServiceImpl<TConContractwftypeMapper, TConContractwftype> implements ITConContractwftypeService {

    @Autowired
    private TConContractwftypeMapper typeMapper;

    @Override
    public ContractProcessTypeVO getContractProcessType(ContractProcessTypeVO vo) {
        if (Util.isEmpty(vo.getContractTypeId())){
            return null;
        }
        ContractProcessTypeVO contractProcessTypeVO = new ContractProcessTypeVO();
        List<ContractProcessTypeVO> contractProcessTypeVOS = typeMapper.selectDatas(vo);
        if (contractProcessTypeVOS.size()>0){
            contractProcessTypeVO.setContractProcessTypeVOList(contractProcessTypeVOS);
        }
        return contractProcessTypeVO;
    }
}
