package com.example.EAS.service;

import com.example.EAS.model.TBasMultiapprove;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.MultiApproveVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface ITBasMultiapproveService extends IService<TBasMultiapprove> {

    PageBean<MultiApproveVO> getMultiApprove(MultiApproveVO vo);
}
