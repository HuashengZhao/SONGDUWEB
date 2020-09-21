package com.example.EAS.service;

import com.example.EAS.model.TConContractinvoice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.InvoiceVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface ITConContractinvoiceService extends IService<TConContractinvoice> {

    PageBean<InvoiceVO> getInvoice(InvoiceVO vo);
}
