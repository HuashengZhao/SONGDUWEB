package com.example.EAS.service;

import com.example.EAS.model.TBdPerson;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.util.PageBean;
import com.example.EAS.vo.PersonsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-31
 */
public interface ITBdPersonService extends IService<TBdPerson> {

    PageBean<PersonsVO> getPersons(PersonsVO vo);
}
