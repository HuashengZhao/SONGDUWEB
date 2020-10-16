package com.example.EAS.service;

import com.example.EAS.model.TConContractwithouttext;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.NoTextContractVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-28
 */
public interface ITConContractwithouttextService extends IService<TConContractwithouttext> {

    PageBean<NoTextContractVO> getNoTextBills(NoTextContractVO vo);

    NoTextContractVO viewNoTextBill(NoTextContractVO vo);

    NoTextContractVO saveNoTextBill(NoTextContractVO vo);

    String getNoTextNum(NoTextContractVO vo);

    NoTextContractVO submitNoTextBill(NoTextContractVO vo);

    void deleteNoTextNum(NoTextContractVO vo);
}
