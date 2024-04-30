package snsoft.study.code.vo;

import lombok.Data;
import snsoft.api.dx.BasVO;
import snsoft.api.dx.SheetInfo;
import snsoft.api.dx.VOField;
import snsoft.api.validation.annotation.CodeTable;
import snsoft.api.validation.annotation.ValidResource;
import snsoft.ft.code.comm.vo.FTSheetVO;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>标题：研究价格库</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.vo.StudyPriceLib</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Data
@Table(name = "st_xy_pricelib")
@SheetInfo("ST-CODE.TX.XYPricelib")
@ValidResource("STUDY-CODE")
public class StudyPriceLib extends BasVO {
    
    private static final long   serialVersionUID = -272422946219486538L;
    public static final  String SheetCode        = "ST-CODE.TX.XYPricelib";
    
    /**
     * 料件号
     */
    @Column
    @CodeTable(codetbl = "FT-CODE.Gcodes")
    private String     item;
    /**
     * 料件名称
     */
    @Column
    private String     itemname;
    /**
     * 英文名称
     */
    @Column
    private String     enamedesc;
    /**
     * 增值税税率
     */
    @Column(scale = 4)
    @VOField(maxdeci = "4")
    private BigDecimal addtaxrate;
    /**
     * 客户
     */
    @Column
    @CodeTable(codetbl = "FT-CODE.CcodeLMExt")
    private String     ccode;
    
    /**
     * 销售币种类型
     */
    @Column
    @CodeTable(value = "FT-CODE.Fcode")
    private String     ct_fcode;
    /**
     * 销售不含税单价
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal ct_exprice;
    
    /**
     * 销售含税单价
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal ct_natprice;
    /**
     * 议价不含税
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal natupric;
    /**
     * 供应商
     */
    @Column
    @CodeTable(codetbl = "FT-CODE.CcodeLMExt")
    private String     sccode;
    /**
     * 采购币种类型
     */
    @Column
    @CodeTable(value = "FT-CODE.Fcode")
    private String     sc_fcode;
    
    /**
     * 采购不含税单价
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal sc_exprice;
    
    /**
     * 采购含税单价
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal sc_natupric;

    /**
     * 适用部门
     */
    @Column
    @CodeTable(value = "FT-CODE.BWcode")
    private String     suitbcode;
    /**
     * 起始日期
     */
    @Column
    private Date       stdate;
    /**
     * 终止日期
     */
    @Column
    private Date       ledate;
    /**
     * 备注
     */
    @Column
    private String     remark;
    
}
