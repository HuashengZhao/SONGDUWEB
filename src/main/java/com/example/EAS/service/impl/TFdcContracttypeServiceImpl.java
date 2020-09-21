package com.example.EAS.service.impl;

import com.example.EAS.model.TFdcContracttype;
import com.example.EAS.mapper.TFdcContracttypeMapper;
import com.example.EAS.service.ITFdcContracttypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.vo.ContractTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-29
 */
@Service
public class TFdcContracttypeServiceImpl extends ServiceImpl<TFdcContracttypeMapper, TFdcContracttype> implements ITFdcContracttypeService {

    @Autowired
    private TFdcContracttypeMapper contracttypeMapper;

    @Override
    public ContractTypeVO getContractType(ContractTypeVO vo) {
        ContractTypeVO contractTypeVO = new ContractTypeVO();
        List<ContractTypeVO> contractTypes = contracttypeMapper.selectData(vo);
        if (contractTypes != null && contractTypes.size() > 0) {
            for (ContractTypeVO contractType : contractTypes) {
                if (contractType.getLongNumber()!=null) {
                    contractType.setLongNumber(contractType.getLongNumber()
                            .replace("-", ".")
                            .replace("!", "."));
                }
            }
            contractTypeVO.setContractTypeVOList(contractTypes);
        }

        return contractTypeVO;
    }

    @Override
    public String getNewContractNumber(ContractTypeVO vo) {
        ContractTypeVO contractTypeVO = new ContractTypeVO();
        String typeNumber = vo.getNum();
        if (typeNumber == null) {
            typeNumber = "errorNumber";
        }

//        生成合同编码规则额："web"+组织编码+合同类型编码+四位数流水号
        DecimalFormat decimalFormat=new DecimalFormat("0000");
        int value=0;
        String format="'errorNumber'";
        String orgNumber = "errorNumber";
        if (vo.getOrgId() != null) {
            orgNumber = contracttypeMapper.selectOrgNumber(vo);
        }
        Integer numberRecord = contracttypeMapper.selectNumberRecord();
        if (numberRecord != null) {
            value = value + numberRecord + 1;
            format = decimalFormat.format(value);
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer conNumber = stringBuffer.append("WEB.")
                .append(orgNumber+".").append(typeNumber+".").append(format);
        contractTypeVO.setContractNumber(conNumber == null ? null : String.valueOf(conNumber));

        return String.valueOf(conNumber);
    }
}
