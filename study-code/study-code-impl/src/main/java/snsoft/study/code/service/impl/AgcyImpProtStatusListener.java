package snsoft.study.code.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.dx.VO;
import snsoft.bas.sheet.busi.utils.SheetUtils;
import snsoft.bas.sheet.busi.vo.BusiObject;
import snsoft.commons.dx.VOUtils;
import snsoft.commons.spring.SpringBeanUtils;
import snsoft.commons.util.StrUtils;
import snsoft.dx.DAO;
import snsoft.ft.trd.comm.datacopy.service.TrdBusiSheetCopyService;
import snsoft.ft.trd.ex.TrdException;
import snsoft.ft.trd.tx.prot.bas.service.ProtCommCheckService;
import snsoft.ft.trd.tx.prot.bas.util.ProtCommUtil;
import snsoft.plat.bas.busi.service.FunctionSaveListener;
import snsoft.plat.bas.busi.service.impl.AbstractStatusListener;
import snsoft.plat.bas.copy.service.CopyServiceFactory;
import snsoft.plat.bas.copy.vo.CopyDef;
import snsoft.sql.SqlValue;
import snsoft.study.code.vo.StudyAgency;

@Component("ST-CODE.TX.AgcyImpProtStatusListener")
@Scope("prototype")
public class AgcyImpProtStatusListener extends AbstractStatusListener<StudyAgency> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String copycode;
    
    public AgcyImpProtStatusListener(Map<String, Object> params) {
        this.copycode = StrUtils.obj2str(params.get("copycode"));
    }
    
    protected void beforeChange(FunctionSaveListener.FunctionSaveShareEvent<StudyAgency> se, SaveParams<StudyAgency> params, SaveResults results, StudyAgency record, String oldStatus, String newStatus) {
        ProtCommUtil.checkEndOptStatus(record, oldStatus, newStatus);
    }
    
    protected void beforeStop(FunctionSaveListener.FunctionSaveShareEvent<StudyAgency> se, SaveParams<StudyAgency> params, SaveResults results, StudyAgency record) {
        ProtCommCheckService<StudyAgency> checkSerV = (ProtCommCheckService)SpringBeanUtils.getBeanByName("FT-TRD.TX.ProtCommCheckService");
        checkSerV.showMessageBox(checkSerV.getPrjdetails(record, "ft_trd_prj_extpe"), (Set)null);
    }
    
    protected void beforeCancelStop(FunctionSaveListener.FunctionSaveShareEvent<StudyAgency> se, SaveParams<StudyAgency> params, SaveResults results, StudyAgency record) {
    }
    
    protected void afterRatify(FunctionSaveListener.FunctionSaveShareEvent<StudyAgency> se, SaveParams<StudyAgency> params, StudyAgency record) {
        this.copyToSettPlan(se, record);
    }
    
    protected void beforeCancelEnd(FunctionSaveListener.FunctionSaveShareEvent<StudyAgency> se, SaveParams<StudyAgency> params, SaveResults results, StudyAgency record) {
        ProtCommUtil.checkTimeOverlap(record);
    }
    
    private void copyToSettPlan(FunctionSaveListener.FunctionSaveShareEvent<StudyAgency> se, StudyAgency record) {
        if (!StrUtils.isEmpty(this.copycode)) {
            boolean isNormal = record.getVsn().compareTo(1) == 0;
            TrdBusiSheetCopyService.TrdBusiSheetCopyParams busiSheetCopyParams = new TrdBusiSheetCopyService.TrdBusiSheetCopyParams();
            busiSheetCopyParams.setCopycode(this.copycode);
            busiSheetCopyParams.setTgtInnercode((String)null);
            if (!isNormal) {
                CopyServiceFactory<Map<String, BigDecimal>, VO, VO, String> copyServiceFactory = (CopyServiceFactory)SpringBeanUtils.getBeanByName("SN-PLAT.CopyServiceFactory");
                CopyDef copyDef = copyServiceFactory.getCopyDef(this.copycode);
                if (copyDef == null && this.logger.isDebugEnabled()) {
                    this.logger.info("代理进口协议生效后调用拷贝定义时，未找到拷贝定义配置：copycode= {} ！", this.copycode);
                }
                
                TrdException.requireNonNull(copyDef, new Object[]{"copyDef"});
                String targetsheetcode = copyDef.getAttrValue("sheetcode");
                if (StrUtils.isEmpty(targetsheetcode) && this.logger.isDebugEnabled()) {
                    this.logger.info("代理进口协议生效后调用拷贝定义{}时，未配置目标单据sheetcode ！", this.copycode);
                }
                
                TrdException.requireNonNull(targetsheetcode, new Object[]{"targetsheetcode"});
                BusiObject targetObject = SheetUtils.getBusiObject(targetsheetcode);
                DAO<VO> dao = SheetUtils.newDAO(targetsheetcode);
                VO srcTarget = (VO)dao.queryOne(new String[]{targetObject.getInnerfld()}, StrUtils.strcat("pticode", "=?"), new SqlValue[]{new SqlValue(record.getInnercode())});
                if (srcTarget != null) {
                    busiSheetCopyParams.setTgtSheetCode(targetsheetcode);
                    busiSheetCopyParams.setTgtInnercode(StrUtils.obj2str(VOUtils.getFieldValue(srcTarget, targetObject.getInnerfld())));
                }
            }
            
            Map<String, Map<String, Object>> sourseParam = new HashMap();
            sourseParam.put(record.getInnercode(), new HashMap());
            busiSheetCopyParams.setSrcParams(sourseParam);
            busiSheetCopyParams.setTgtStatus("70");
            busiSheetCopyParams.setUsercode(se.getUsercode());
            busiSheetCopyParams.setSrcInnercodeColName("pticode");
            Map<String, Object> extParam = busiSheetCopyParams.getExtParams();
            extParam.put("srcSheetcode", record.getSheetcode());
            busiSheetCopyParams.setExtParams(extParam);
            TrdBusiSheetCopyService trdBusiSheetCopyService = (TrdBusiSheetCopyService)SpringBeanUtils.getBeanByName("FT-TRD.TrdBusiSheetCopyService");
            String tgtincode = trdBusiSheetCopyService.copyData(busiSheetCopyParams);
            if (this.logger.isDebugEnabled()) {
                if (StrUtils.isNotEmpty(tgtincode) && StrUtils.isEmpty(busiSheetCopyParams.getTgtInnercode())) {
                    this.logger.info("代理进口协议生效后生成代理进口结算方案：copycode= {} ，SrcInnercode = {} ,tgtInnercode= {}！", new Object[]{this.copycode, record.getInnercode(), tgtincode});
                } else {
                    this.logger.info("代理进口协议生效后更新代理进口结算方案：copycode= {} ，SrcInnercode = {} ,tgtInnercode= {}！", new Object[]{this.copycode, record.getInnercode(), busiSheetCopyParams.getTgtInnercode()});
                }
            }
            
        }
    }
}
