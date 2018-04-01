package com.lyu.csdndemo.common.config.init;

import com.lyu.csdndemo.common.util.dict.DictCache;
import com.lyu.csdndemo.sys.dao.DictDao;
import com.lyu.csdndemo.sys.entity.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
@Order(value = 1)
public class InitDictCache implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitDictCache.class);

    @Inject
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DictDao dictDao;

    @Override
    public void run(String... strings) throws Exception {
        logger.info("-------------初始化数据字典-------------");
        // 加载所有的数据字典的数据
        List<Dict> dictList = dictDao.loadAll();
        DictCache.load(dictList);
        logger.info("-------------完成数据字典初始化-------------");
    }
}
