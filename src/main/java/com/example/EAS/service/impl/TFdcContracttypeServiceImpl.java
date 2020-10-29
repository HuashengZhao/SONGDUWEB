package com.example.EAS.service.impl;

import com.example.EAS.model.TFdcContracttype;
import com.example.EAS.mapper.TFdcContracttypeMapper;
import com.example.EAS.service.ITFdcContracttypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.util.Util;
import com.example.EAS.vo.ContractTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ContractTypeVO getContractType(ContractTypeVO vo) {
        ContractTypeVO contractTypeVO = new ContractTypeVO();
        List<ContractTypeVO> contractTypes = contracttypeMapper.selectData(vo);
        if (contractTypes != null && contractTypes.size() > 0) {
            for (ContractTypeVO contractType : contractTypes) {
//                需关联合约规划的合同类型：土地合同、前期合同、建安合同、基础设施、公建配套、设计合同、工程营销（工程合同）
                String title = contractType.getTitle();
                contractType.setWhetherPC(0);
                if (Util.isNotEmpty(title)){
                    if (title.contains("土地合同")||title.contains("前期合同")||title.contains("建安合同")
                    ||title.contains("基础设施")||title.contains("公建配套")||title.contains("设计合同")
                    ||title.contains("工程营销")){
                        contractType.setWhetherPC(1);
                    }
                }
                Integer isMarket = contractType.getIsMarket();
                if (Util.isEmpty(isMarket)||isMarket==0){
                    contractType.setIsMarket(0);
                }else {
                    contractType.setIsMarket(1);
                }
                String orgType = contractType.getContractWFStartType();
                    if (Util.isNotEmpty(orgType)) {
                        if (orgType.contains("BIGRANGE")) {
                            contractType.setContractWFStartType("集团/事业部/城市公司");
                        } else if (orgType.contains("SMALLRANGE")) {
                            contractType.setContractWFStartType("项目部");
                        } else if (orgType.contains("ALLRANGE")) {
                            contractType.setContractWFStartType("集团/事业部/城市公司-项目部");
                        } else if (orgType.contains("NEIBU")) {
                            contractType.setContractWFStartType("内部关联公司往来类");
                        }
                    }

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
        Date date = new Date();
        String dateInfo = formatter.format(date);
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
            contracttypeMapper.updateNumRecord(value);
            format = decimalFormat.format(value);
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer conNumber = stringBuffer.append("WEB.")
                .append(orgNumber+".").append(typeNumber+".").append(format);
        contractTypeVO.setContractNumber(conNumber == null ? null : String.valueOf(conNumber));

        return String.valueOf(conNumber);
    }
}
