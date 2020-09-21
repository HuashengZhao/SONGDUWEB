package com.example.EAS.service;

import com.example.EAS.model.TFdcLanddeveloper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.PartAVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
public interface ITFdcLanddeveloperService extends IService<TFdcLanddeveloper> {

    PartAVO getPartA(PartAVO vo);
}
