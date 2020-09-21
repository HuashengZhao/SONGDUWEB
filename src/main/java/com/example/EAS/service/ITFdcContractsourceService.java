package com.example.EAS.service;

import com.example.EAS.model.TFdcContractsource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.MadeWayVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
public interface ITFdcContractsourceService extends IService<TFdcContractsource> {

    MadeWayVO getMadeWay(MadeWayVO vo);
}
