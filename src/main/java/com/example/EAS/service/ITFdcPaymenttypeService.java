package com.example.EAS.service;

import com.example.EAS.model.TFdcPaymenttype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.PaymentTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface ITFdcPaymenttypeService extends IService<TFdcPaymenttype> {

    PaymentTypeVO getPaymentType(PaymentTypeVO vo);
}
