/*#
 lib=snsoft/study/studydisupricqty.js
#*/
package snsoft.study.code.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.dx.VO;
import snsoft.commons.spring.SpringBeanUtils;
import snsoft.commons.util.StrUtils;
import snsoft.ft.trd.comm.formula.TrdFormulaListener;
import snsoft.ft.trd.comm.formula.service.TrdFormulaService;
import snsoft.ft.trd.comm.formula.service.TrdUpricQtcFormulaService;

import java.util.Map;

/**
 * <p>标题计算优惠折扣 </p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.service.impl.StudyDisupricQty</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service(StudyDisupricQty.BeanID)
@Scope("prototype")
public class StudyDisupricQty <V extends VO> extends TrdFormulaListener<V> {

    public static final String BeanID = "STUDY-CODE.COMM.TrdUpricQtcListener";
    protected TrdUpricQtcFormulaService upricQtcFormulaService;
    /**
     * 优惠单价列
     */
    protected final String disupricColumn;
    /**
     * 数量列
     */
    protected final String qtyColumn;
    /**
     * 优惠原币金额=优惠单价x签约数量
     */
    protected final String disfcyColumn;
    /**
     * 表
     */
    protected final String tblname;

    public StudyDisupricQty(Map<String, Object> parameter) {

        super(parameter);
        this.disupricColumn = StrUtils.obj2str(parameter.get("disupricColumn"), "disupric");
        this.qtyColumn = StrUtils.obj2str(parameter.get("qtyColumn"), "qty");
        this.disfcyColumn = StrUtils.obj2str(parameter.get("disfcyColumn"), "disfcy");
        this.tblname = (String)parameter.get("tblname");
        this.upricQtcFormulaService = SpringBeanUtils.getBeanByName(TrdUpricQtcFormulaService.BeanID);
    }

    @Override
    public void beforeSave(FunctionSaveShareEvent<V> se, SaveParams<V> params, SaveResults results, V record) {
        TrdFormulaService.TrdFormulaTool trdFormulaTool = new TrdFormulaService.TrdFormulaTool();
        trdFormulaTool.addByEntry(tblname, disupricColumn, qtyColumn, disfcyColumn).setByInsert(true).setByDelete(false);
//        trdFormulaTool.addByEntry(this.tblname, new String[]{this.disupricColumn, this.qtyColumn, this.disfcyColumn}).setByInsert(true).setByDelete(false);
        if (trdFormulaTool.isFieldsChanged(record)) {
            TrdUpricQtcFormulaService.TrdUpricQtcFormulaParams formulaParams = new TrdUpricQtcFormulaService.TrdUpricQtcFormulaParams(record, this.tblname);
            formulaParams.setFcyFieldName(this.disupricColumn);
            formulaParams.setQtcFieldName(this.qtyColumn);
            formulaParams.setUpricFieldName(this.disfcyColumn);
            //优惠原币金额=优惠单价x签约数量
            //价数量货值计算公式 输入qtc时：disfcy=qtc*disupric 输入disupric时：disfcy=qtc*disupric 输入disfcy时：disupric=disfcy/qtc
            this.upricQtcFormulaService.calcUpricQtc(formulaParams);
        }
    }
}
