package org.liushuxue.chaos.service;

import org.liushuxue.chaos.bean.menu.MenuVo;
import org.liushuxue.chaos.entity.MenuPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_menu(菜单)】的数据库操作Service
* @createDate 2024-09-05 20:37:41
*/
public interface MenuService extends IService<MenuPo> {

    List<MenuVo> findTreeList(String parentCode);
}
