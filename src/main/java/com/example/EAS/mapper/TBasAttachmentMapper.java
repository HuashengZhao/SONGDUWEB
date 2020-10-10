package com.example.EAS.mapper;

import com.example.EAS.model.TBasAttachment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.EAS.vo.AttachmentsVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author watson
 * @since 2020-09-24
 */
public interface TBasAttachmentMapper extends BaseMapper<TBasAttachment> {

    List<AttachmentsVO> selectAttachMent(String id);

    List<AttachmentsVO> selectByNumber(String attachNum);

    List<AttachmentsVO> selectWEBAttach(String id);
}
