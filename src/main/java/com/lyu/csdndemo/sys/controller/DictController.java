package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import com.lyu.csdndemo.common.base.constant.SystemStaticConst;
import com.lyu.csdndemo.common.util.dict.DictCache;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.Dict;
import com.lyu.csdndemo.sys.entity.QueryDict;
import com.lyu.csdndemo.sys.service.DictService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author lyu */
@Controller
@RequestMapping("/dict")
public class DictController extends GenericController<Dict, QueryDict> {
  @Inject private DictService dictService;

  @Override
  protected GenericService<Dict, QueryDict> getService() {
    return dictService;
  }

  /**
   * 将字典数据初始化到前端
   *
   * @return
   */
  @RequestMapping(
    value = "/loadDict",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseBody
  public Map<String, Object> loadDict() {
    Map<String, Object> result = new HashMap<>();
    List<Dict> dictList = dictService.query(new QueryDict("1"));
    result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
    result.put("data", dictList);
    return result;
  }

  /**
   * 重新加载数据字典到内存中
   *
   * @return
   */
  @RequestMapping(
    value = "/reload",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseBody
  public Map<String, Object> reload() {
    Map<String, Object> result = new HashMap<>();
    List<Dict> dictList = dictService.query(new QueryDict("1"));
    DictCache.reload(dictList);
    result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
    result.put(SystemStaticConst.MSG, "重新加载数据字典成功！");
    return result;
  }
}
