package org.liushuxue.chaos.bean.user;

import org.liushuxue.chaos.entity.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserStructMapper {
    UserStructMapper INSTANCES = Mappers.getMapper(UserStructMapper.class);
    UserVo toUserVo(UserPo userPo);
}
