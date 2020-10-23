package com.example.EAS.mapper;

import com.example.EAS.model.TConSupplierapply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-03
 */
public interface TConSupplierapplyMapper extends BaseMapper<TConSupplierapply> {

    List<SupplierApplyVO> selectDatas(SupplierApplyVO vo);

    Integer selectNumberRecord();


    String selectCreatorId(String creatorNum);

    void updateNumberRecord(Integer numberRecord);

    List<SupplierApplyVO> selectByNum(String taxerNum);

    List<SupplierApplyVO> selectByName(String title);

    List<SupplierApplyVO> selectByNumId(String taxerNum, String id);

    List<SupplierApplyVO> selectByTaxNum(String taxerNum);

    SupplierApplyVO selectDataById(String id);

    String selectEASURL();

    String selectOAURL();


    void insertOaid(String easid, String oaid);

    void updateOaid(String billId, String oaId);

    List<String> selectOaid(String id);

    PersonsVO selectCreator(String creatorNum);

    String selectDeptName(String orgNum);

    String selectDeptId(String orgNum);

    List<LoginVO> selectIFExist(String org, String person);

    String selectOrgId(String id);

    List<Object> selectData(String billId,String oaId);

    void insertAttachMent(AttachmentsVO attachmentsVO);

    List<AttachmentsVO> selectAttachments(String easId);

    Integer selectFileNumberRecord();


    void updateFileNumrecord(Integer numberRecord);

    PersonsVO selectPersonByNum(String person1);

    void deleteData(String billId, String oaId);

    void deletAttach(String id);

    List<String> selectNumFromWebAttachment(String id);

    void deletNumList(List<String> existList);

    void updateWebUrl(String newUrl, String billId);

    List<AttachmentsVO> selectAttachmentsByNum(String num);

    void updateCreatorId(String creatorId,String id);

    List<String> selectOrgInfoByPerson(String person);

    String selectUUIDByOriName(String fileNum);

    String selecWEBURLByOriName(String fileNum);

    List<OrgVO> selectOrgList();


    List<SupplierApplyVO> selectByNameId(String title,String id);

    List<Object> selectSupplierByName(String title);

    String selectViewUrl();
}

