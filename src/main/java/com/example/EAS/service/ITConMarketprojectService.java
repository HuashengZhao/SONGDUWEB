package com.example.EAS.service;

import com.example.EAS.model.TConMarketproject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.MarketProjectVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITConMarketprojectService extends IService<TConMarketproject> {

    MarketProjectVO getMarketProjects(MarketProjectVO vo);

    MarketProjectVO viewMarketProject(MarketProjectVO vo);

}
