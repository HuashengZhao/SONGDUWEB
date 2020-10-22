package com.example.EAS.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.model.TConPayrequestbill;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.PayRequestBillVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-10-09
 */
public interface ITConPayrequestbillService extends IService<TConPayrequestbill> {

    PageBean<PayRequestBillVO> getPayRequestBillVO(PayRequestBillVO vo);

    PayRequestBillVO viewPayRequestBill(PayRequestBillVO vo) throws Exception;
}
