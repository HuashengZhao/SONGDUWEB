package com.example.EAS.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.model.TConPayrequestbill;
import com.example.EAS.vo.PayRequestBillVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-10-09
 */
public interface TConPayrequestbillMapper extends BaseMapper<TConPayrequestbill> {

    List<PayRequestBillVO> selectDatas(PayRequestBillVO vo);

    PayRequestBillVO selectDataById(PayRequestBillVO vo);

    void updateData(String easid);

    BigDecimal selectFamount(String contractId);

    BigDecimal selectActualAmount(String contractId);
}
