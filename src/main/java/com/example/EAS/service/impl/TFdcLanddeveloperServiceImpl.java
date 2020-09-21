package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TFdcLanddeveloperMapper;
import com.example.EAS.model.TFdcLanddeveloper;
import com.example.EAS.service.ITFdcLanddeveloperService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.PartAVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-01
 */
@Service
public class TFdcLanddeveloperServiceImpl extends ServiceImpl<TFdcLanddeveloperMapper, TFdcLanddeveloper> implements ITFdcLanddeveloperService {

    @Autowired
    private TFdcLanddeveloperMapper mapper;

    @Override
    public PartAVO getPartA(PartAVO vo) {
        PartAVO partAVO = new PartAVO();
        if (Util.isEmpty(vo.getOrgId())) {
            return null;
        }
//            甲方
            List<PartAVO> partAVOS = mapper.selectDatas(vo);
            if (Util.isNotEmpty(partAVOS)) {
                partAVO.setPartAVOList(partAVOS);
            }
        return partAVO;
    }
}
