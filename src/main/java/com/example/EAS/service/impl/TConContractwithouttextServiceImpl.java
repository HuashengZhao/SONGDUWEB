package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBasAttachmentMapper;
import com.example.EAS.mapper.TConContractbillMapper;
import com.example.EAS.mapper.TConContractwithouttextMapper;
import com.example.EAS.model.TConContractwithouttext;
import com.example.EAS.service.ITConContractwithouttextService;
import com.example.EAS.util.PageBean;
import com.example.EAS.util.Util;
import com.example.EAS.vo.NoTextContractVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-28
 */
@Service
public class TConContractwithouttextServiceImpl extends ServiceImpl<TConContractwithouttextMapper, TConContractwithouttext> implements ITConContractwithouttextService {

    @Autowired
    private TConContractwithouttextMapper mapper;
    @Autowired
    private TBasAttachmentMapper attachmentMapper;
    @Autowired
    private TConContractbillMapper contractbillMapper;

    @Override
    public PageBean<NoTextContractVO> getNoTextBills(NoTextContractVO vo) {
        String orgId = vo.getOrgId();
        //        項目id集合      有父節點則是分期 沒有是項目 id防入集合
        List<String> projectIdList = new ArrayList<>();
        if (Util.isNotEmpty(vo.getProjectId())) {
            String parentId = contractbillMapper.selectProject(vo.getProjectId());
            if (Util.isNotEmpty(parentId)) {
                projectIdList.add(vo.getProjectId());
            } else {
                List<String> ids = contractbillMapper.selectProjectIds(vo.getProjectId());
                projectIdList.addAll(ids);
            }
        }

        String state1 = vo.getState();
        if (Util.isNotEmpty(state1)) {
            if (state1.contains("已提交")) {
                vo.setState("2SUB");
            } else if (state1.contains("保存")) {
                vo.setState("1SAVED");
            } else if (state1.contains("审批中")) {
                vo.setState("3AUD");
            } else if (state1.contains("已审批")) {
                vo.setState("4");
            } else if (state1.contains("终止")) {
                vo.setState("5");
            } else if (state1.contains("已下发")) {
                vo.setState("7");
            } else if (state1.contains("已签证")) {
                vo.setState("8");
            } else if (state1.contains("作废")) {
                vo.setState("9");
            } else if (state1.contains("已上报")) {
                vo.setState("10");
            } else if (state1.contains("被打回")) {
                vo.setState("11");
            } else if (state1.contains("修订中")) {
                vo.setState("12REVISING");
            } else if (state1.contains("已修订")) {
                vo.setState("12REVISE");
            } else if (state1.contains("已确认")) {
                vo.setState("13");
            }
        }
        if (Util.isEmpty(orgId)||Util.isEmpty(projectIdList)){
            return null;
        }
        vo.setProjectIdList(projectIdList);
        PageHelper.startPage(vo.getCurrentPage(),vo.getPageSize());
        List<NoTextContractVO> noTextContractVOList = mapper.selectDatas(vo);
        if (noTextContractVOList!=null && noTextContractVOList.size()>0){
            for (NoTextContractVO noTextContractVO : noTextContractVOList) {
                String state = noTextContractVO.getState();
                if (Util.isNotEmpty(state)) {
                    if (state.contains("2SUBMIT")) {
                        noTextContractVO.setState("已提交");
                    } else if (state.contains("1SAVED")) {
                        noTextContractVO.setState("保存");
                    } else if (state.contains("3AUD")) {
                        noTextContractVO.setState("审批中");
                    } else if (state.contains("4")) {
                        noTextContractVO.setState("已审批");
                    } else if (state.contains("5")) {
                        noTextContractVO.setState("终止");
                    } else if (state.contains("7")) {
                        noTextContractVO.setState("已下发");
                    } else if (state.contains("8")) {
                        noTextContractVO.setState("已签证");
                    } else if (state.contains("9")) {
                        noTextContractVO.setState("作废");
                    } else if (state.contains("10")) {
                        noTextContractVO.setState("已上报");
                    } else if (state.contains("11")) {
                        noTextContractVO.setState("被打回");
                    } else if (state.contains("12REVISING")) {
                        noTextContractVO.setState("修订中");
                    } else if (state.contains("12REVISE")) {
                        noTextContractVO.setState("已修订");
                    } else if (state.contains("13")) {
                        noTextContractVO.setState("已确认");
                    }
                }
                String personName = noTextContractVO.getPersonName();
                if (Util.isNotEmpty(personName)){
                    noTextContractVO.setReceiverName(personName);
                }
            }
        }
        PageBean<NoTextContractVO> pageBean = new PageBean<>();
        pageBean.setTotalCount(((Page) noTextContractVOList).getTotal());
        pageBean.setPageData(noTextContractVOList);
        return pageBean;
    }

    @Override
    public NoTextContractVO viewNoTextBill(NoTextContractVO vo) {

        return null;
    }


}
