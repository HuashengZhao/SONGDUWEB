package com.example.EAS.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.model.TConSupplierapply;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.SupplierApplyVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface ITConSupplierapplyService extends IService<TConSupplierapply> {

    PageBean<SupplierApplyVO> getSupplierApply(SupplierApplyVO vo) throws Exception;

    JSONObject addSupplierApply(SupplierApplyVO vo);

    String getNewNumber(SupplierApplyVO vo);

    void deleteSupplierApply(SupplierApplyVO vo);

//    Integer whetherRepeat(SupplierApplyVO vo);

    void supplierSubmit(SupplierApplyVO vo);

//    void supplierAudit(SupplierApplyVO vo);

    JSONObject suplierUpdate(SupplierApplyVO vo);

//    void downLoadAttachment(HttpServletRequest request, HttpServletResponse response, AttachmentsVO vo);

}
