package snsoft.study.code.service;

import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.bas.TableRowValues;
import snsoft.api.dx.SheetInfo;
import snsoft.api.service.AuthParam;
import snsoft.api.service.CuicodeFilter;
import snsoft.api.service.SpringBean;
import snsoft.approval.client.service.SheetApprAccessService;
import snsoft.ft.busi.service.FTBusiAccessService;
import snsoft.ft.trd.tx.prot.bas.service.BasProtPageNavParams;
import snsoft.ft.trd.tx.prot.bas.service.BasProtParams;
import snsoft.plat.bas.busi.service.Funccode;
import snsoft.plat.bas.service.LimitConst;
import snsoft.study.code.vo.StudyAgcyGood;
import snsoft.study.code.vo.StudyAgcySI;
import snsoft.study.code.vo.StudyAgency;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>标题：学习代码服务</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/11</p>
 * <p>类路径：snsoft.study.code.service.StudyCodeService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = StudyCodeService.BEAN_NAME)
@Funccode("ST-CODE.TX.Prot.XYAgcyImpProt.SV")
@SheetInfo("ST-CODE.TX.Prot.XYAgcyImpProt")
public interface StudyCodeService extends FTBusiAccessService<StudyAgency> {

    String BEAN_NAME = "STUDY-CODE.StudyCodeService";
    /**
     * 查询入口
     *
     * @param params
     * @return
     */
    QueryResults<StudyAgency> queryEntry(StudyCodeParam params);


    QueryResults<StudyAgency> queryProt(BasProtPageNavParams var1);

    QueryResults<StudyAgcyGood> queryProtGood(BasProtParams var1);

    QueryResults<StudyAgcySI> queryProtSi(BasProtParams var1);

    SaveResults saveProt(SaveParams<StudyAgency> var1);

    List<TableRowValues> addSettItems(String pticode,String sheetcode, AgcyImpProtaddSIParams paramV);

    public static class AgcyImpProtaddSIParams implements Serializable {
        private static final long serialVersionUID = -8180323414137456084L;
        private Date modifydate;
        private String table;
        private String tgtTable;
        private String innercode;
        private Object[] rptypes;

        public AgcyImpProtaddSIParams() {
        }

        public Date getModifydate() {
            return this.modifydate;
        }

        public void setModifydate(Date modifydate) {
            this.modifydate = modifydate;
        }

        public String getTable() {
            return this.table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getTgtTable() {
            return this.tgtTable;
        }

        public void setTgtTable(String tgtTable) {
            this.tgtTable = tgtTable;
        }

        public String getInnercode() {
            return this.innercode;
        }

        public void setInnercode(String innercode) {
            this.innercode = innercode;
        }

        public Object[] getRptypes() {
            return this.rptypes;
        }

        public void setRptypes(Object[] rptypes) {
            this.rptypes = rptypes;
        }
    }

}
