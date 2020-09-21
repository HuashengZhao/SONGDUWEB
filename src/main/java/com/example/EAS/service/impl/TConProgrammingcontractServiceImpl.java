package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConProgrammingcontractMapper;
import com.example.EAS.model.TConProgrammingcontract;
import com.example.EAS.service.ITConProgrammingcontractService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.ProgramConVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
@Service
public class TConProgrammingcontractServiceImpl extends ServiceImpl<TConProgrammingcontractMapper, TConProgrammingcontract> implements ITConProgrammingcontractService {

    @Autowired
    private TConProgrammingcontractMapper mapper;

    @Override
    public ProgramConVO getProgramCon(ProgramConVO vo) {
        ProgramConVO programConVO = new ProgramConVO();
        if (Util.isEmpty(vo.getContractTypeId())){
            return null;
        }
        if (Util.isEmpty(vo.getProjectId())){
            return null;
        }
        List<ProgramConVO> programConVOList = mapper.selectDatas(vo);
        if (Util.isNotEmpty(programConVOList)){
            for (ProgramConVO conVO : programConVOList) {
                String longNumber = conVO.getLongNumber();
                if (Util.isNotEmpty(longNumber)){
                    conVO.setLongNumber(longNumber
                    .replace("!",".")
                    .replace("-","."));
                }
            }
            programConVO.setProgramConVOS(programConVOList);
        }
        return programConVO;
    }
}
