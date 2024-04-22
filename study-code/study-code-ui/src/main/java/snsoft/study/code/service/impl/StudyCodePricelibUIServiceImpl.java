package snsoft.study.code.service.impl;

import org.springframework.stereotype.Service;
import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.study.code.service.StudyCodePricelibParam;
import snsoft.study.code.service.StudyCodePricelibService;
import snsoft.study.code.service.StudyCodePricelibUIService;
import snsoft.study.code.vo.StudyPriceLib;

import javax.annotation.Resource;

/**
 * <p>标题：学习代码 pricelib uiservice impl</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/22</p>
 * <p>类路径：snsoft.study.code.service.impl.StudyCodePricelibUIServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service("STUDY-CODE.StudyCodePricelibUIService")
public class StudyCodePricelibUIServiceImpl implements StudyCodePricelibUIService {
    @Resource
    private StudyCodePricelibService service;
    
    @Override
    public QueryResults<StudyPriceLib> queryEntryUI(StudyCodePricelibParam params) {
        return service.queryEntry(params);
    }
    
    @Override
    public SaveResults saveUI(SaveParams<StudyPriceLib> params) {
        return service.save(params);
    }
}
