package org.liushuxue.chaos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.bean.menu.MenuVo;
import org.liushuxue.chaos.entity.MenuPo;
import org.liushuxue.chaos.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "菜单管理")
@Log4j2
@RequestMapping("/menu")
@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    @Operation(summary = "获取菜单列表")
    @GetMapping("/treeList")
    List<MenuVo> findTreeMenuList() {
        return menuService.findTreeList("");
    }

    @Operation(summary = "获取菜单列表")
    @GetMapping("/list")
    List<MenuPo> findMenuList() {
        return menuService.list();
    }
}
