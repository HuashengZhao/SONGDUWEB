package com.example.EAS.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.model.TConProgrammingcontract;
import com.example.EAS.vo.ProgramConVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface ITConProgrammingcontractService extends IService<TConProgrammingcontract> {

    ProgramConVO getProgramCon(ProgramConVO vo);
}
