package com.example.EAS.controller;


import com.example.EAS.service.impl.BaseDataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author watson
 * @since 2020-08-17
 */
@Slf4j
@RestController
@RequestMapping("/EAS/baseData")
public class BaseDataController {
    @Autowired
    private BaseDataServiceImpl service;

}
