package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TInvTenderaccepterresultMapper;
import com.example.EAS.model.TInvTenderaccepterresult;
import com.example.EAS.service.ITInvTenderaccepterresultService;
import com.example.EAS.vo.TenderAccepterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
@Service
public class TInvTenderaccepterresultServiceImpl extends ServiceImpl<TInvTenderaccepterresultMapper, TInvTenderaccepterresult> implements ITInvTenderaccepterresultService {

    @Autowired
    private TInvTenderaccepterresultMapper mapper;

    @Override
    public TenderAccepterVO getTenderAcc(TenderAccepterVO vo) {
        TenderAccepterVO accepterVO = new TenderAccepterVO();
            List<TenderAccepterVO> accepterVOList = mapper.selectDatas(vo);
            accepterVO.setAccepterVOList(accepterVOList);
        return accepterVO;
    }
}
