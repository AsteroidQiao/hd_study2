package snsoft.study.code.service;

import lombok.Data;
import snsoft.api.bas.QueryParams;
import snsoft.api.sql.SqlColumn;
import snsoft.api.sql.SqlExpression;

import java.util.Date;

/**
 * <p>标题：价格库查询参数</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/22</p>
 * <p>类路径：snsoft.study.code.service.StudyCodePricelibParam</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Data
public class StudyCodePricelibParam extends QueryParams {
    
    private static final long serialVersionUID = 3582490286788075237L;
    //基本参数
    
    /**
     * 材料号（可通过料件号、名称，英文名称搜索，忽略大小写）
     */
    @SqlColumn(sqlop = SqlExpression.LIKE, column = "gcode,gname,engname", ignorecase = true)
    private String item;
    /**
     * 起始日期从
     */
    @SqlColumn(column = "stdate", sqlop = SqlExpression.GE)
    private Date   stdatefrom;
    /**
     * 到
     */
    @SqlColumn(column = "stdate", sqlop = SqlExpression.LE)
    private Date   stdateto;
    /**
     * 截止日期从
     */
    @SqlColumn(column = "ledate", sqlop = SqlExpression.GE)
    private Date   ledatefrom;
    /**
     * 到
     */
    @SqlColumn(column = "ledate", sqlop = SqlExpression.LE)
    private Date   ledateto;
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
    
    
    
    
    //更多参数
    /**
     * 适用部门
     */
    @SqlColumn()
    private String suitbcode;
    
    /**
     * 销售币种类型
     */
    @SqlColumn()
    private String ct_fcode;
    
    /**
     * 采购币种类型
     */
    @SqlColumn()
    private String sc_fcode;
    ///**
    // * 客户
    // */
    //@SqlColumn()
    //private String ccode;
    ///**
    // * 供应商
    // */
    //@SqlColumn()
    //private String sccode;
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
    
    ///**
    // * 起始日期
    // */
    //@SqlColumn()
    //private Date stdate;
    ///**
    // * 截止日期
    // */
    //@SqlColumn()
    //private Date ledate;
}
