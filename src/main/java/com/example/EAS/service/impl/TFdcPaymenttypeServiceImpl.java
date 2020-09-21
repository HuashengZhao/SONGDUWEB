package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TFdcPaymenttypeMapper;
import com.example.EAS.model.TFdcPaymenttype;
import com.example.EAS.service.ITFdcPaymenttypeService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.PaymentTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
@Service
public class TFdcPaymenttypeServiceImpl extends ServiceImpl<TFdcPaymenttypeMapper, TFdcPaymenttype> implements ITFdcPaymenttypeService {

    @Autowired
    private TFdcPaymenttypeMapper mapper;

    @Override
    public PaymentTypeVO getPaymentType(PaymentTypeVO vo) {
        PaymentTypeVO paymentTypeVO = new PaymentTypeVO();
        List<PaymentTypeVO> paymentTypeVOList = mapper.selectDatas(vo);
        if (Util.isNotEmpty(paymentTypeVOList)){
            paymentTypeVO.setPaymentTypeVOS(paymentTypeVOList);
        }
        return paymentTypeVO;
    }
}
