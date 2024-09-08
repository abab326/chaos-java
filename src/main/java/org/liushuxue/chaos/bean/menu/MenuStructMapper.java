package org.liushuxue.chaos.bean.menu;

import org.liushuxue.chaos.entity.MenuPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface MenuStructMapper {
    MenuStructMapper INSTANCES = Mappers.getMapper(MenuStructMapper.class);
    MenuVo toVo(MenuPo menu);
    List<MenuVo> toVoList(List<MenuPo> menuPoList);
}
