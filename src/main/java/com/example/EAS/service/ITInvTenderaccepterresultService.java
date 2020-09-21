package com.example.EAS.service;

import com.example.EAS.model.TInvTenderaccepterresult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.TenderAccepterVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
public interface ITInvTenderaccepterresultService extends IService<TInvTenderaccepterresult> {

    TenderAccepterVO getTenderAcc(TenderAccepterVO vo);

}
