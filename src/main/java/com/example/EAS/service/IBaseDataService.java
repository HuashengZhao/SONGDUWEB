package com.example.EAS.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.EAS.model.BaseData;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author watson
 * @since 2020-08-17
 */
public interface IBaseDataService  extends IService<BaseData> {

    JSONObject acceptHandle(JSONObject body);

}
