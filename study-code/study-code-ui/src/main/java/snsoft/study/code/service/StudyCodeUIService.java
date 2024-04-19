package snsoft.study.code.service;

import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.bas.TableRowValues;
import snsoft.api.service.AuthParam;
import snsoft.api.service.CuicodeFilter;
import snsoft.api.service.InnerCode;
import snsoft.api.service.Remoteable;
import snsoft.api.validation.annotation.ValidResource;
import snsoft.ft.trd.tx.prot.bas.service.BasProtPageNavParams;
import snsoft.ft.trd.tx.prot.bas.service.BasProtParams;
import snsoft.plat.bas.service.LimitConst;
import snsoft.study.code.vo.StudyAgcyGood;
import snsoft.study.code.vo.StudyAgcySI;
import snsoft.study.code.vo.StudyAgency;

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
public interface StudyCodeUIService {

    @AuthParam(sheetCode = "ST-CODE.TX.Prot.XYAgcyImpProt",
            opids = { LimitConst.Opid_R, LimitConst.Opid_C })
    @CuicodeFilter
    QueryResults<StudyAgency> queryEntryUI(StudyCodeParam params);

    @AuthParam(
            sheetCode = "ST-CODE.TX.Prot.XYAgcyImpProt",
            opids = {"R", "C"},
            useHasLimit = true
    )
    @CuicodeFilter
    QueryResults<StudyAgency> queryProtUI(BasProtPageNavParams var1);

    @AuthParam(
            sheetCode = "ST-CODE.TX.Prot.XYAgcyImpProt",
            opids = {"R", "C"},
            joinColumn = "pticode"
    )
    QueryResults<StudyAgcyGood> queryProtGoodUI(BasProtParams var1);

    @AuthParam(
            sheetCode = "ST-CODE.TX.Prot.XYAgcyImpProt",
            opids = {"R", "C"},
            joinColumn = "pticode"
    )
    QueryResults<StudyAgcySI> queryProtSiUI(BasProtParams var1);
    @AuthParam(
            sheetCode = "ST-CODE.TX.Prot.XYAgcyImpProt",
            opids = {"C"}
    )
    SaveResults saveProtUI(SaveParams<StudyAgency> var1);

    @AuthParam(
            sheetCode = "@Param(1)",
            opids = {"C"}
    )
    @Remoteable
    List<TableRowValues> addSettItemsUI(@InnerCode @NotNull(message = "{ptcode}") String var1, @NotNull(message = "{sheetcode}") String var2, StudyCodeService.AgcyImpProtaddSIParams var3);
}
