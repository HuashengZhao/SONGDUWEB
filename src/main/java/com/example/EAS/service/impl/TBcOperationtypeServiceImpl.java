package com.example.EAS.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.EAS.mapper.TBcOperationtypeMapper;
import com.example.EAS.model.TBcOperationtype;
import com.example.EAS.service.ITBcOperationtypeService;
import com.example.EAS.util.Util;
import com.example.EAS.vo.OperationTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
@Service
public class TBcOperationtypeServiceImpl extends ServiceImpl<TBcOperationtypeMapper, TBcOperationtype> implements ITBcOperationtypeService {

    @Autowired
    private TBcOperationtypeMapper mapper;

    @Override
    public OperationTypeVO getOperationType(OperationTypeVO vo) {
        OperationTypeVO operationTypeVO = new OperationTypeVO();
        List<OperationTypeVO> voList = mapper.selectDatas(vo);
        if (Util.isNotEmpty(voList)){
            operationTypeVO.setVos(voList);
        }
        return operationTypeVO;
    }
}
