package com.example.EAS.service;

import com.example.EAS.model.TConPaycontenttype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.vo.PayContentTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author watson
 * @since 2020-09-02
 */
public interface ITConPaycontenttypeService extends IService<TConPaycontenttype> {

    PayContentTypeVO getPayContentType(PayContentTypeVO vo);

}
