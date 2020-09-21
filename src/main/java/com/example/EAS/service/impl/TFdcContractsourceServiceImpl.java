package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TFdcContractsourceMapper;
import com.example.EAS.model.TFdcContractsource;
import com.example.EAS.service.ITFdcContractsourceService;
import com.example.EAS.vo.MadeWayVO;
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
public class TFdcContractsourceServiceImpl extends ServiceImpl<TFdcContractsourceMapper, TFdcContractsource> implements ITFdcContractsourceService {

    @Autowired
    private TFdcContractsourceMapper mapper;

    @Override
    public MadeWayVO getMadeWay(MadeWayVO vo) {
        MadeWayVO madeWayVO = new MadeWayVO();
        List<MadeWayVO> madeWayVOList = mapper.selectDatas(vo);
        if (madeWayVOList!=null&&madeWayVOList.size()>0){
            madeWayVO.setMadeWayVOList(madeWayVOList);
        }
        return madeWayVO;
    }
}
