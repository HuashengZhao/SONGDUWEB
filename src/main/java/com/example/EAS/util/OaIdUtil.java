package com.example.EAS.util;

import com.example.EAS.mapper.TConSupplierapplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OaIdUtil {
    @Autowired
    private TConSupplierapplyMapper mapper;

    public void updateId(String billId, String oaId) {
        mapper.updateOaid(billId, oaId);
    }

    public void insertId(String billId, String oaId) {
        mapper.insertOaid(billId, oaId);
    }
    public void deleteId(String billId, String oaId) {
        mapper.deleteData(billId, oaId);
    }
    public void getString(String billId, String oaId) {
        List<Object> obj = mapper.selectData(billId, oaId);
        if (obj.size() == 0 || obj == null) {
            insertId(billId, oaId);
        } else if (obj.size() > 0) {
            updateId(billId, oaId);
        }

    }
}
