//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package snsoft.study.code.service.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiConsumer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.dx.VO;
import snsoft.ft.trd.comm.formula.TrdCommBusiFormulaListener;
import snsoft.ft.trd.comm.formula.service.TrdCommBusiFormulaService;
import snsoft.ft.trd.comm.formula.service.TrdFormulaService;
import snsoft.ft.trd.tx.prot.agcyimp.vo.AgcyImpProt;
import snsoft.plat.bas.busi.service.FunctionSaveListener;
import snsoft.study.code.vo.StudyAgency;

@Component("ST-CODE.TX.AgcyImpProtBusiFormulaListener")
@Scope("prototype")
public class AgcyImpProtBusiFormulaListener extends TrdCommBusiFormulaListener<StudyAgency> {
    public AgcyImpProtBusiFormulaListener(Map<String, Object> parameter) {
        super(parameter);
    }
    
    public void beforeSave(FunctionSaveListener.FunctionSaveShareEvent<StudyAgency> se, SaveParams<StudyAgency> params, SaveResults results, StudyAgency record) {
        super.beforeSave(se, params, results, record);
        boolean hasGood = "Y".equals(record.getIsgood());
        if (!hasGood) {
            this.addMarginfcyFormula(record);
        } else {
            TrdCommBusiFormulaListener.FormulaParams<StudyAgency> formulaParams = this.newFormulaParams("st_xy_prot_good", se, params, results, record);
            this.addSynRateFormula(formulaParams, "st_xy_prot", "fcode");
            this.addSynRateFormula(formulaParams, "st_xy_prot", "sfcode");
            this.addSynRateFormula(formulaParams, "st_xy_prot", "fserate");
            this.addSynRateFormula(formulaParams, "st_xy_prot", "fcerate");
            this.addSynRateFormula(formulaParams, "st_xy_prot", "fuerate");
            this.addZFcyByFcyFormula(formulaParams, "st_xy_prot", "pticode");
            this.calcFormulas(formulaParams);
            TrdCommBusiFormulaService.TrdCommBusiFormulaParams busiParams = formulaParams.getBusiParams();
            this.addSumFormula(formulaParams, "st_xy_prot_good", "st_xy_prot", busiParams.getFcyFieldName(), busiParams.getFcyFieldName());
            this.addSumFormula(formulaParams, "st_xy_prot_good", "st_xy_prot", busiParams.getScyFieldName(), busiParams.getScyFieldName());
            this.addSumFormula(formulaParams, "st_xy_prot_good", "st_xy_prot", busiParams.getZcnyFieldName(), busiParams.getZcnyFieldName());
            this.addSumFormula(formulaParams, "st_xy_prot_good", "st_xy_prot", busiParams.getZusdFieldName(), busiParams.getZusdFieldName());
            this.calcFormulas(formulaParams);
            TrdCommBusiFormulaListener.FormulaParams<StudyAgency> mformulaParams = this.newFormulaParams("st_xy_prot", se, params, results, record);
            this.addMarginfcyFormula(mformulaParams, "st_xy_prot");
            this.calcFormulas(mformulaParams);
        }
    }
    
    protected void addMarginfcyFormula(StudyAgency main) {
        boolean isCalc = main.isColumnStored("isgood");
        if (isCalc) {
            main.setMarginfcy((BigDecimal)null);
            main.addStoredColumn("marginfcy");
        }
    }
    
    protected void addMarginfcyFormula(TrdCommBusiFormulaListener.FormulaParams<StudyAgency> formulaParams, String tableName) {
        TrdCommBusiFormulaService.TrdCommBusiFormulaParams params = formulaParams.getBusiParams();
        TrdFormulaService.TrdFormulaTool tool = new TrdFormulaService.TrdFormulaTool();
        String[] fields = new String[]{params.getScyFieldName(), "marginrate", "margintype"};
        tool.addByEntry(formulaParams.getTableName(), fields).setByInsert(true).setByDelete(false);
        BiConsumer<TrdCommBusiFormulaListener.FormulaParams<StudyAgency>, VO> calc = (fParams, detail) -> {
            params.setDetail(detail);
            TrdFormulaService.TrdFormulaField margintype = new TrdFormulaService.TrdFormulaField(detail, "margintype");
            if ("20".equals((String)margintype.getValue())) {
                TrdFormulaService.TrdFormulaField scy = params.getFormulaField(params.getScyFieldName());
                TrdFormulaService.TrdFormulaField margintrate = params.getFormulaField("marginrate");
                TrdFormulaService.TrdFormulaField margintfcy = params.getFormulaField("marginfcy");
                BigDecimal sum = scy.getBigValue().multiply(margintrate.getBigValue());
                margintfcy.setValue(sum.setScale(margintfcy.getScale(), params.getRoundingMode()));
            }
            
        };
        formulaParams.addFormula(new TrdCommBusiFormulaListener.FormulaEntry(tool, calc));
    }
}
