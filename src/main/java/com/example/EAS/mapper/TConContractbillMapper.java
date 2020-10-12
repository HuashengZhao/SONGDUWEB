package com.example.EAS.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.model.TConContractbill;
import com.example.EAS.vo.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface TConContractbillMapper extends BaseMapper<TConContractbill> {

    List<ContractVO> selectDatas(ContractVO vo);

    String selectProject(String projectId);

    List<String> selectProjectIds(String projectId);

    List<String> selectConTypeById(String conTypeId);

    String selectPersonByNum(String number);

    String selectDeptByNum(String number);

    String selectUnionBankId(String unionBankNum);

    List<ContractDetailVO> selectConDetailsByCT(String contractTypeId);

    List<ContractSignDetailVO> selectSignInfos(String id);

    List<ContractDetailVO> selectDetails(String id);

    List<ContractAddVO> selectContractAdds(String fnumber);

    List<MarketContDetailVO> selectMarketCons(String id);

    ContractVO viewContractVO(ContractVO vo);

    void insertAddContract(List<ContractAddVO> contractAddVOS);


    List<ContractVO> selectMainNums(ContractVO vo);

    String selectUserByNum(String creator);


    List<String> selectHYGH(String hyghId);

    List<String> selectHYGHInContract(String hyghId);
}
