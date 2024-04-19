package snsoft.study.code.service.impl;

import org.springframework.stereotype.Service;
import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.bas.TableRowValues;
import snsoft.api.dx.VO;
import snsoft.bas.sheet.busi.utils.SheetUtils;
import snsoft.bas.sheet.defaultvalue.factory.SheetDefaultValueServiceFactory;
import snsoft.bas.sheet.defaultvalue.service.SheetDefaultValueService;
import snsoft.commons.dx.VOClassInfo;
import snsoft.commons.dx.VOUtils;
import snsoft.commons.spring.SpringBeanUtils;
import snsoft.commons.util.DateUtils;
import snsoft.commons.util.ObjectUtils;
import snsoft.commons.util.StrUtils;
import snsoft.context.AppContext;
import snsoft.ft.busi.service.FTBusiAccessServiceImpl;
import snsoft.ft.trd.comm.utils.TrdCommUtils;
import snsoft.ft.trd.tx.prot.bas.service.BasProtPageNavParams;
import snsoft.ft.trd.tx.prot.bas.service.BasProtParams;
import snsoft.study.code.service.StudyCodeParam;
import snsoft.study.code.service.StudyCodeService;
import snsoft.study.code.vo.StudyAgcyGood;
import snsoft.study.code.vo.StudyAgcySI;
import snsoft.study.code.vo.StudyAgency;

import java.util.*;

/**
 * <p>标题：学习代码服务 impl</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.service.impl.StudyCodeServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class StudyCodeServiceImpl extends FTBusiAccessServiceImpl<StudyAgency> implements StudyCodeService {


    @Override
    public QueryResults<StudyAgency> queryEntry(StudyCodeParam params){
        if (StrUtils.isEmpty(params.getSheetcode()))
        {
            params.setSheetcode(StudyAgency.SheetCode);
        }
        if (params.getValiddatefm() != null)
        {
            params.setValiddatefm(DateUtils.toDate0(params.getValiddatefm()));
        }
        if (params.getValiddateto() != null)
        {
            params.setValiddateto(DateUtils.toDate0(params.getValiddateto()));
        }
        return super.uiQuery(params, null);
    };

    @Override
    public QueryResults<StudyAgency> queryProt(BasProtPageNavParams params) {
        return super.uiQuery(params, (String)null);
    }
    @Override
    public QueryResults<StudyAgcyGood> queryProtGood(BasProtParams params) {
        return this.funcQuery(params, null, TrdCommUtils.getDetailClass("ST-CODE.TX.Prot.XYAgcyImpProt", StudyAgcyGood.class));
    }
    @Override
    public QueryResults<StudyAgcySI> queryProtSi(BasProtParams params) {
        return this.funcQuery(params, null, TrdCommUtils.getDetailClass("ST-CODE.TX.Prot.XYAgcyImpProt", StudyAgcySI.class));
    }

    @Override
    public SaveResults saveProt(SaveParams<StudyAgency> params) {
        return super.uiSave(params);
    }


    @Override
    public List<TableRowValues> addSettItems(String pticode,String sheetcode, AgcyImpProtaddSIParams paramV) {
        if (StrUtils.isEmpty(paramV.getTgtTable())) {
            return new ArrayList();
        } else {
            StudyAgency oldProt = (StudyAgency)this.queryCascadeByInnerCode(pticode);
            Object[] rptypes = paramV.getRptypes();
            if (rptypes != null && rptypes.length != 0) {
                StudyAgency cloneProt = (StudyAgency) ObjectUtils.deepClone(oldProt);
                List<VO> details = TrdCommUtils.getVOList(cloneProt, paramV.getTgtTable());
                int maxidx = 0;
                Set<String> oldRptypes = new HashSet();
                Iterator var10 = details.iterator();

                while(var10.hasNext()) {
                    VO detail = (VO)var10.next();
                    int idx = StrUtils.obj2int(VOUtils.getFieldValue(detail, "idx"), 0);
                    if (idx > maxidx) {
                        maxidx = idx;
                    }

                    String rptype = (String)VOUtils.getFieldValue(detail, "rptype");
                    oldRptypes.add(rptype);
                }

                rptypes = Arrays.asList(rptypes).stream().filter((rptypex) -> {
                    return !oldRptypes.contains(rptypex);
                }).toArray();
                if (rptypes.length == 0) {
                    return new ArrayList();
                } else {
                    SheetDefaultValueServiceFactory sheetDefaultFactory = (SheetDefaultValueServiceFactory) SpringBeanUtils.getBeanByName("SN-BAS.SheetDefaultValueServiceFactory");
                    SheetDefaultValueService defSerV = sheetDefaultFactory.newDefaultValueService(sheetcode);
                    VOClassInfo detailClass = SheetUtils.getVOClassInfo(sheetcode, paramV.getTgtTable());
                    int i = 0;

                    for(int len = rptypes.length; i < len; ++i) {
                        VO detail = (VO)detailClass.newInstance();
                        VOUtils.setFieldValue(detail, "idx", maxidx + (i + 1) * 10);
                        VOUtils.setFieldValue(detail, "rptype", rptypes[i]);
                        detail.setInsert();
                        detail.addStoredColumns(new String[]{"idx", "rptype"});
                        details.add(detail);
                    }

                    cloneProt.setUpdate();
                    defSerV.setSheetDefaultValues(Arrays.asList(cloneProt), true);
                    cloneProt.setModifydate(DateUtils.getServerDate());
                    cloneProt.addStoredColumn("modifydate");
                    cloneProt.setModifier(AppContext.getUserSession(true).getUserCode());
                    cloneProt.addStoredColumn("modifier");
                    this.save(cloneProt);
                    return TrdCommUtils.buildReplaceV(cloneProt, oldProt);
                }
            } else {
                return new ArrayList();
            }
        }
    }
}
