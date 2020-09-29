package com.example.EAS.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.model.TConContractbill;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.ContractDetailVO;
import com.example.EAS.vo.ContractVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface ITConContractbillService extends IService<TConContractbill> {

    PageBean<ContractVO> getContractList(ContractVO vo) throws Exception;

    ContractVO saveContractBill(ContractVO vo);

    List<ContractDetailVO> getContractDetails(ContractDetailVO vo);

    ContractVO viewContractBill(ContractVO vo) throws Exception;

    ContractVO submitToOa(ContractVO vo);

    void deleteContractBills(ContractVO vo);

    List<ContractVO> getMainContractNums(ContractVO vo);

}
