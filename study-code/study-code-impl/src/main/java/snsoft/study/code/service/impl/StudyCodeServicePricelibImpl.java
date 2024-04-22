package snsoft.study.code.service.impl;

import org.springframework.stereotype.Service;
import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.ft.busi.service.FTBusiAccessServiceImpl;
import snsoft.study.code.service.StudyCodePricelibParam;
import snsoft.study.code.service.StudyCodePricelibService;
import snsoft.study.code.vo.StudyAgency;
import snsoft.study.code.vo.StudyPriceLib;

/**
 * <p>标题：价格库 impl</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/22</p>
 * <p>类路径：snsoft.study.code.service.impl.StudyCodeServicePricelibImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class StudyCodeServicePricelibImpl extends FTBusiAccessServiceImpl<StudyPriceLib> implements StudyCodePricelibService {
    @Override
    public QueryResults<StudyPriceLib> queryEntry(StudyCodePricelibParam params) {
        return super.uiQuery(params, null);
    }
    
    @Override
    public SaveResults save(SaveParams<StudyPriceLib> params) {
        return super.uiSave(params);
    }
}
