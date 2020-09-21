package com.example.EAS.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.model.TFdcPaymenttype;
import com.example.EAS.vo.PaymentTypeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface TFdcPaymenttypeMapper extends BaseMapper<TFdcPaymenttype> {


    List<PaymentTypeVO> selectDatas(PaymentTypeVO vo);
}
