package com.example.EAS.service;

import com.example.EAS.model.TConContractchangesettlebill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.ChangeSettleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-22
 */
public interface ITConContractchangesettlebillService extends IService<TConContractchangesettlebill> {

    PageBean<ChangeSettleVO> getChangeSettleList(ChangeSettleVO vo);
}
