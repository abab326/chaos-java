package org.liushuxue.chaos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.liushuxue.chaos.bean.menu.MenuStructMapper;
import org.liushuxue.chaos.bean.menu.MenuVo;
import org.liushuxue.chaos.entity.MenuPo;
import org.liushuxue.chaos.service.MenuService;
import org.liushuxue.chaos.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_menu(菜单)】的数据库操作Service实现
* @createDate 2024-09-05 20:37:41
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuPo>
    implements MenuService{

    @Override
    public List<MenuVo> findTreeList(String parentCode) {
        List<MenuPo> treeList = baseMapper.findTreeList(parentCode);
        return MenuStructMapper.INSTANCES.toVoList(treeList);
    }
}




