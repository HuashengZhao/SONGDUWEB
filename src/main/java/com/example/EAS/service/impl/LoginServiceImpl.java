//package com.example.service;
//
//import java.util.List;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.ehcache.Cache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.EAS.dto.LoginDto;
//import com.example.EAS.util.BusErrorCodeEnum;
//import com.example.EAS.util.BusinessException;
//import com.example.EAS.util.EhcacheUtils;
//import com.example.EAS.util.EnAndDeCodeUtils;
//import com.example.EAS.util.TokenMgr;
//import com.google.common.collect.Lists;
//
//@Service
//public class LoginServiceImpl{
//
//
//    private Cache<String,String> userCache=EhcacheUtils.getCache("HelloWorldCache");
//
//    public LoginDto loginByPassword(){
//        if(StringUtils.isEmpty("name")
//                || StringUtils.isEmpty("password")){
//            throw new BusinessException(BusErrorCodeEnum.NO_PARAM,"缺少参数！");
//        }
//
//        String password =EnAndDeCodeUtils.encodeByMd5AndBase64(sysUser.getPassword());
//        SysUser oldSysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
//                .eq("user_name", sysUser.getUserName())
//                .eq("data_state", 1).eq("password", password));
//        if(oldSysUser == null){
//            throw new BusinessException(BusErrorCodeEnum.PASSWORD_ERROR,"密码错误！");
//        }
//
//        if(oldSysUser.getLocked() == 1){
//            throw new BusinessException(BusErrorCodeEnum.USER_LOCKED,"用户被冻结,请联系管理员！");
//        }
//
//        LoginDto loginDto=new LoginDto();
//
//        UserDto userDto=new UserDto();
//        userDto.setId(oldSysUser.getId());
//        userDto.setUsername(oldSysUser.getUserName());
//        userDto.setUserType(oldSysUser.getUserType());
//
//        List<AreaDto> areaLits = sysUserAreaMapper.selectAllForUser(oldSysUser.getId());
//        if(CollectionUtils.isNotEmpty(areaLits)){
//            List<Long> areaIds= Lists.newArrayList();
//            for(AreaDto areaDto : areaLits){
//                areaIds.add(areaDto.getId());
//            }
//            userDto.setAreaIds(areaIds);
//        }
//
//        String jwt=TokenMgr.createJWT(oldSysUser.getId().toString()
//                , TokenMgr.generalSubject(userDto), 10*24*60*60*1000);
//        loginDto.setUsername(oldSysUser.getUserName());
//        loginDto.setName(oldSysUser.getName());
//        loginDto.setJwt(jwt);
//        loginDto.setUserType(oldSysUser.getUserType());
//        userCache.put(oldSysUser.getId().toString(), jwt);
//        return loginDto;
//    }
//
//}
