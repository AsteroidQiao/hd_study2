package snsoft.study.code.service;

import lombok.Data;
import snsoft.api.bas.QueryParams;
import snsoft.api.sql.SqlColumn;

import javax.management.Query;
import java.sql.Date;

@Data
public class StudyCodePricelibParam extends QueryParams {
    
    private static final long serialVersionUID = 3582490286788075237L;
    
    /**
     * 适用部门
     */
    @SqlColumn()
    private String suitbcode;
    
    /**
     * 采购币种类型
     */
    @SqlColumn()
    private String ct_fcode;
    
    /**
     * 采购币种类型
     */
    @SqlColumn()
    private String sc_fcode;
    /**
     * 客户
     */
    @SqlColumn()
    private String ccode;
    /**
     * 供应商
     */
    @SqlColumn()
    private String sccode;
    /**
     * 销售不含税单价
     */
    @SqlColumn()
    private String ct_exprice;
    /**
     * 采购不含税单价
     */
    @SqlColumn()
    private String sc_exprice;
    
    /**
     * 起始日期
     */
    @SqlColumn()
    private Date stdate;
    /**
     * 截止日期
     */
    @SqlColumn()
    private Date ledate;
}
