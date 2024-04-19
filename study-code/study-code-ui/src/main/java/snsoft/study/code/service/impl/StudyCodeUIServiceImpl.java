package snsoft.study.code.service.impl;

import org.springframework.stereotype.Service;
import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.bas.TableRowValues;
import snsoft.ft.bas.utils.ModifyCheckUtils;
import snsoft.ft.trd.tx.prot.bas.service.BasProtPageNavParams;
import snsoft.ft.trd.tx.prot.bas.service.BasProtParams;
import snsoft.study.code.service.StudyCodeParam;
import snsoft.study.code.service.StudyCodeService;
import snsoft.study.code.service.StudyCodeUIService;
import snsoft.study.code.vo.StudyAgcyGood;
import snsoft.study.code.vo.StudyAgcySI;
import snsoft.study.code.vo.StudyAgency;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>标题：学习代码 UIServiceimpl</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.service.impl.StudyCodeUIServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service("STUDY-CODE.StudyCodeUIService")
public class StudyCodeUIServiceImpl implements StudyCodeUIService {

    @Resource(name = StudyCodeService.BEAN_NAME)
    
    private StudyCodeService studyCodeService;

    @Override
    public QueryResults<StudyAgency> queryEntryUI(StudyCodeParam params){
        return studyCodeService.queryEntry(params);
    }
    @Override
    public QueryResults<StudyAgency> queryProtUI(BasProtPageNavParams params) {
        return this.studyCodeService.queryProt(params);
    }
    @Override
    public QueryResults<StudyAgcyGood> queryProtGoodUI(BasProtParams params) {
        return this.studyCodeService.queryProtGood(params);
    }
    @Override
    public QueryResults<StudyAgcySI> queryProtSiUI(BasProtParams params) {
        return this.studyCodeService.queryProtSi(params);
    }
    @Override
    public SaveResults saveProtUI(SaveParams<StudyAgency> params) {
        return this.studyCodeService.saveProt(params);
    }

    @Override
    public List<TableRowValues> addSettItemsUI(@NotNull String pticode, @NotNull String sheetcode, StudyCodeService.AgcyImpProtaddSIParams paramV) {
        ModifyCheckUtils.checkDataValidity(ModifyCheckUtils.initValidContainer(pticode, sheetcode, paramV.getModifydate()));
        return this.studyCodeService.addSettItems(pticode, sheetcode, paramV);
    }
}
