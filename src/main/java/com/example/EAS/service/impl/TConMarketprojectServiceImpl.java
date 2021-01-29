package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConMarketprojectMapper;
import com.example.EAS.model.TConMarketproject;
import com.example.EAS.service.ITConMarketprojectService;
import com.example.EAS.util.FileContentTypeUtils;
import com.example.EAS.util.Util;
import com.example.EAS.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
@Service
public class TConMarketprojectServiceImpl extends ServiceImpl<TConMarketprojectMapper, TConMarketproject> implements ITConMarketprojectService {
    @Autowired
    private TConMarketprojectMapper marketprojectMapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public MarketProjectVO getMarketProjects(MarketProjectVO vo) {
        List<MarketProjectVO> marketProjectVOList = marketprojectMapper.selectDatas(vo);
        if (marketProjectVOList != null && marketProjectVOList.size() > 0) {
            for (MarketProjectVO marketProjectVO : marketProjectVOList) {
                String state = marketProjectVO.getState();
                if (state.contains("2SUB")) {
                    marketProjectVO.setState("已提交");
                } else if (state.contains("1SAVED")) {
                    marketProjectVO.setState("保存");
                } else if (state.contains("3AUD")) {
                    marketProjectVO.setState("审批中");
                } else if (state.contains("4A")) {
                    marketProjectVO.setState("已审批");
                } else if (state.contains("5C")) {
                    marketProjectVO.setState("终止");
                } else if (state.contains("7A")) {
                    marketProjectVO.setState("已下发");
                } else if (state.contains("8V")) {
                    marketProjectVO.setState("已签证");
                } else if (state.contains("9I")) {
                    marketProjectVO.setState("作废");
                } else if (state.contains("10P")) {
                    marketProjectVO.setState("已上报");
                } else if (state.contains("11B   ")) {
                    marketProjectVO.setState("被打回");
                } else if (state.contains("12REVISING")) {
                    marketProjectVO.setState("修订中");
                } else if (state.contains("12REVISE")) {
                    marketProjectVO.setState("已修订");
                } else if (state.contains("13")) {
                    marketProjectVO.setState("已确认");
                }
//                保存=1SAVED,已提交=2SUBMITTED,审批中=3AUDITTING,已审批=4AUDITTED,终止=5CANCEL,已下发=7ANNOUNCE,已签证=8VISA,
//                作废=9INVALID,已上报=10PUBLISH,被打回=11BACK,修订中=12REVISING,已修订=12REVISE,已确认=13CONFIRMED
            }
            vo.setMarketProjectVOList(marketProjectVOList);
        }
        return vo;
    }

    @Override
    public MarketProjectVO viewMarketProject(MarketProjectVO vo) {
        String id = vo.getId();
        if (Util.isEmpty(id)) return null;

        MarketProjectVO marketProjectVO = marketprojectMapper.selectDataById(id);
        if (Util.isEmpty(marketProjectVO)) return null;

        String mpId = marketProjectVO.getId();
        List<CostAccountVO> costAccountVOS = marketprojectMapper.selectCostAccounts(mpId);
        if (Util.isNotEmpty(costAccountVOS)) {
            for (CostAccountVO costAccountVO : costAccountVOS) {
                String longNumber = costAccountVO.getLongNumber();
                String replace = longNumber.replace("!", ".");
                costAccountVO.setLongNumber(replace);
            }
            marketProjectVO.setCostAccountVOS(costAccountVOS);
        }

        List<PriceUnitVO> priceUnitVOS = marketprojectMapper.selectPriceUnits(mpId);
        if (Util.isNotEmpty(priceUnitVOS)) {
            marketProjectVO.setPriceUnitVOS(priceUnitVOS);
        }

        List<AttachmentsVO> attachmentsVOS = attachmentMapper.selectAttachMent(vo.getId());
        if (attachmentsVOS != null && attachmentsVOS.size() > 0) {
            for (AttachmentsVO easAttFile : attachmentsVOS) {
                String fileType = easAttFile.getFileType();
                if (Util.isNotEmpty(fileType)) {
                    String s = FileContentTypeUtils.contentType("." + fileType);
                    easAttFile.setContentType(s);
                }
            }
            marketProjectVO.setAttachmentsVOS(attachmentsVOS);
        }

        List<SupplierVO> supplierVOS = marketprojectMapper.selectSignSuppliers(mpId);
        if (Util.isNotEmpty(supplierVOS)) {
            marketProjectVO.setSupplierVOS(supplierVOS);
        }
        return marketProjectVO;
    }
}
