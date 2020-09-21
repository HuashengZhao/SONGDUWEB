package com.example.EAS.mapper;

import com.example.EAS.model.TConContractinvoice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.InvoiceVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface TConContractinvoiceMapper extends BaseMapper<TConContractinvoice> {

    List<InvoiceVO> selectDatas(InvoiceVO vo);
}
