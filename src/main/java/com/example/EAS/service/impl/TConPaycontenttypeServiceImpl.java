package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TConPaycontenttypeMapper;
import com.example.EAS.model.TConPaycontenttype;
import com.example.EAS.service.ITConPaycontenttypeService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.PayContentTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
@Service
public class TConPaycontenttypeServiceImpl extends ServiceImpl<TConPaycontenttypeMapper, TConPaycontenttype> implements ITConPaycontenttypeService {

    @Autowired
    private TConPaycontenttypeMapper mapper;

    @Override
    public PayContentTypeVO getPayContentType(PayContentTypeVO vo) {
        PayContentTypeVO payContentTypeVO = new PayContentTypeVO();
        if (Util.isEmpty(vo.getContractTypeId())){
            return null;
        }
        List<PayContentTypeVO> voList = mapper.selectDatas(vo);
        if (Util.isNotEmpty(voList)){
            payContentTypeVO.setPayContentTypeVOList(voList);
        }
        return payContentTypeVO;
    }
}
