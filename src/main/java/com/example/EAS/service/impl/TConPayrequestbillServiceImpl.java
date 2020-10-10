package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConDeductofpayreqbillMapper;
import com.example.EAS.mapper.TConPayrequestbillMapper;
import com.example.EAS.model.TConDeductofpayreqbill;
import com.example.EAS.model.TConPayrequestbill;
import com.example.EAS.service.ITConPayrequestbillService;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.AttachmentsVO;
import com.example.EAS.vo.PayRequestBillVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-10-09
 */
@Service
public class TConPayrequestbillServiceImpl extends ServiceImpl<TConPayrequestbillMapper, TConPayrequestbill> implements ITConPayrequestbillService {

    @Autowired
    private TConPayrequestbillMapper mapper;

    @Autowired
    private TConDeductofpayreqbillMapper deductofpayreqbillMapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;


    @Override
    public PageBean<PayRequestBillVO> getPayRequestBillVO(PayRequestBillVO vo) {
        String orgId = vo.getOrgId();
        if(Util.isEmpty(orgId)){
            return null;
        }
        String projectId = vo.getProjectId();
        if (Util.isEmpty(projectId)){
            return null;
        }
        PageHelper.startPage(vo.getCurrentPage(),vo.getPageSize());
        List<PayRequestBillVO> payRequestBillVOS =  mapper.selectDatas(vo);
        if (payRequestBillVOS!=null&&payRequestBillVOS.size()>0){
            for (PayRequestBillVO payRequestBillVO : payRequestBillVOS) {
//                状态
                String state = payRequestBillVO.getState();
                if (Util.isNotEmpty(state)) {
                if (state.contains("2SUBMIT")) {
                    payRequestBillVO.setState("已提交");
                } else if (state.contains("1SAVED")) {
                    payRequestBillVO.setState("保存");
                } else if (state.contains("3AUD")) {
                    payRequestBillVO.setState("审批中");
                } else if (state.contains("4")) {
                    payRequestBillVO.setState("已审批");
                } else if (state.contains("5")) {
                    payRequestBillVO.setState("终止");
                } else if (state.contains("7")) {
                    payRequestBillVO.setState("已下发");
                } else if (state.contains("8")) {
                    payRequestBillVO.setState("已签证");
                } else if (state.contains("9")) {
                    payRequestBillVO.setState("作废");
                } else if (state.contains("10")) {
                    payRequestBillVO.setState("已上报");
                } else if (state.contains("11")) {
                    payRequestBillVO.setState("被打回");
                } else if (state.contains("12REVISING")) {
                    payRequestBillVO.setState("修订中");
                } else if (state.contains("12REVISE")) {
                    payRequestBillVO.setState("已修订");
                } else if (state.contains("13")) {
                    payRequestBillVO.setState("已确认");
                }
            }
//                扣款金额
                BigDecimal deductAmt=BigDecimal.ZERO;
                String id = payRequestBillVO.getId();
                if (Util.isNotEmpty(id)){
//                    是否有附件
                    payRequestBillVO.setIfHasAttach(0);
                    List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectAttachMent(id);
                    if (attachmentsVOS!=null && attachmentsVOS.size()>0){
                        payRequestBillVO.setIfHasAttach(1);
                    }
                    List<TConDeductofpayreqbill> billList = deductofpayreqbillMapper.selectList
                            (new QueryWrapper<TConDeductofpayreqbill>()
                            .eq("FPAYMENTBILLID", id));
                    if (billList!=null && billList.size()>0){
                        for (TConDeductofpayreqbill tConDeductofpayreqbill : billList) {
                            Double famount = tConDeductofpayreqbill.getFamount();
                            if (Util.isNotEmpty(famount)){
                                deductAmt=deductAmt.add(BigDecimal.valueOf(famount));
                            }
                        }
                    }
                    payRequestBillVO.setDeductAmt(deductAmt);
                }
            }
        }
        PageBean<PayRequestBillVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) payRequestBillVOS).getTotal());
        pageBean.setPageData(payRequestBillVOS);
        return pageBean;
    }

    @Override
    public PayRequestBillVO viewPayRequestBill(PayRequestBillVO vo) {
        String id = vo.getId();
        if (Util.isEmpty(id)){
            return null;
        }
        PayRequestBillVO payRequestBillVO =  mapper.selectDataById(vo);
        return null;
    }
}
