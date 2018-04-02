package com.lyu.csdndemo.common.util.node;

import com.lyu.csdndemo.sys.entity.Tree;

import java.util.*;

public class NodeUtil {

    private static List<Tree> returnList = new ArrayList<>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list
     * @param typeId
     * @return
     */
    public static List<Tree> getChildNode(List<Tree> list, Long typeId) {
        returnList = new ArrayList<>();
        if (list == null && typeId == null) {
            return returnList;
        }
        for (Iterator<Tree> iterator = list.iterator(); iterator.hasNext(); ) {
            Tree node = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getpId() == 0 && typeId == node.getId()) {
                recursionFn(list, node);
            }
            // 二、遍历所有的父节点下的所有子节点
            if (node.getpId() == 0) {
                recursionFn(list, node);
            }
        }
        // 对顶层菜单按照treeOrder从大到小进行进行排序
        Collections.sort(returnList);
        return returnList;
    }

    /**
     * 递归获取菜单子节点
     *
     * @param list
     * @param node
     */
    private static void recursionFn(List<Tree> list, Tree node) {
        List<Tree> childList = getChildList(list, node);
        if (hasChild(list, node)) {
            Iterator<Tree> it = childList.iterator();
            while (it.hasNext()) {
                Tree n = it.next();
                if (hasChild(list, n)) {
                    recursionFn(list, n);
                }
            }
            node.setChild(childList);
            returnList.add(node);
        }
    }

    /**
     * 得到子节点列表
     *
     * @param list
     * @param node
     * @return
     */
    private static List<Tree> getChildList(List<Tree> list, Tree node) {
        List<Tree> nodeList = new ArrayList<>();
        Iterator<Tree> it = list.iterator();
        while (it.hasNext()) {
            Tree n = it.next();
            if (n.getpId() == node.getId()) {
                nodeList.add(n);
            }
        }
        Collections.sort(nodeList);
        return nodeList;
    }

    /**
     * 判断是否有子节点
     *
     * @param list
     * @param node
     * @return
     */
    private static boolean hasChild(List<Tree> list, Tree node) {
        return getChildList(list, node).size() > 0;
    }
}
