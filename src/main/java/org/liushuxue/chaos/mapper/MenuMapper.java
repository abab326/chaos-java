package org.liushuxue.chaos.mapper;

import org.apache.ibatis.annotations.Param;
import org.liushuxue.chaos.entity.MenuPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_menu(菜单)】的数据库操作Mapper
* @createDate 2024-09-05 20:37:41
* @Entity org.liushuxue.chaos.entity.MenuPo
*/
public interface MenuMapper extends BaseMapper<MenuPo> {

  List<MenuPo> findTreeList(@Param("code") String parentCode);
  List<MenuPo> findChildren();

}




