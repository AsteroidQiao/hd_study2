package snsoft.study.code.service;

import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.bas.TableRowValues;
import snsoft.api.service.AuthParam;
import snsoft.api.service.InnerCode;
import snsoft.api.service.Remoteable;
import snsoft.api.validation.annotation.ValidResource;
import snsoft.ft.trd.tx.prot.bas.service.BasProtPageNavParams;
import snsoft.ft.trd.tx.prot.bas.service.BasProtParams;
import snsoft.plat.bas.service.LimitConst;
import snsoft.study.code.vo.StudyAgcyGood;
import snsoft.study.code.vo.StudyAgcySI;
import snsoft.study.code.vo.StudyAgency;
import snsoft.study.code.vo.StudyPriceLib;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>标题：学习代码 UIService</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.service.StudyCodeUIService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@ValidResource("STUDY-CODE")
public interface StudyCodePricelibUIService {
    
    @AuthParam(sheetCode = "ST-CODE.TX.XYPricelib", opids = {LimitConst.Opid_R, LimitConst.Opid_C})
    QueryResults<StudyPriceLib> queryEntryUI(StudyCodePricelibParam params);
    
    @AuthParam(sheetCode = "ST-CODE.TX.XYPricelib", opids = {"C"})
    SaveResults saveUI(SaveParams<StudyPriceLib> params);
}
