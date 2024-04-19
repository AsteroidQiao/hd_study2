package snsoft.study.code.vo;

import lombok.Data;
import snsoft.api.dft.DefaultValue;
import snsoft.api.dx.VOField;
import snsoft.api.validation.annotation.CodeTable;
import snsoft.api.validation.annotation.ValidResource;
import snsoft.api.validation.validator.SubmitGroup;
import snsoft.ft.trd.tx.prot.aimp.vo.AcImpProtGood;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>标题：贸易协议商品 vo</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.vo.StudyAgcyGood</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Data
@Table(name = "st_xy_prot_good")
@ValidResource("STUDY-CODE")
public class StudyAgcyGood extends AcImpProtGood {

    private static final long serialVersionUID = 4965871512579453502L;

    /**
     * 优惠单价
     */
    @Column
    private BigDecimal disupric;

    /**
     * 优惠原币金额
     */
    @Column
    private BigDecimal disfcy;

    /**
     * 优惠本位币金额
     */
    @Column
    private BigDecimal disscy;

    /**
     * 优惠人民币金额
     */
    @Column
    private BigDecimal diszcny;

    /**
     * 优惠美元金额
     */
    @Column
    private BigDecimal diszusd;


    @Id
    @NotNull(groups = {SubmitGroup.class})
    @DefaultValue("Accode:FT-TRD.InnerCode")
    @Column
    private String ptgicode;
    /**
     * 协议内码
     */
    @NotNull(groups = {SubmitGroup.class})
    @DefaultValue("CopyMaster:pticode")
    @Column
    private String pticode;
    /**
     * 序号
     */
    @Column
    @DefaultValue(value = "AutoInc:10")
    private Integer idx;
    /**
     * 商品编码
     */
    @Column
    @CodeTable(codetbl = "FT-CODE.Gcodes")
    private String gcode;
    /**
     * 客户货号
     */
    @Column
    private String cgcode;
    /**
     * 海关编码
     */
    @Column
    @CodeTable(value = "FT-CODE.Hscode")
    private String hscode;
    /**
     * 商品英文名称
     */
    @Column
    private String enamedesc;
    /**
     * 商品名称
     */
    @Column
    private String cnamedesc;
    /**
     * 品牌
     */
    @Column
    private String brand;
    /**
     * 规格型号
     */
    @Column
    private String specifidesc;
    /**
     * 货物描述
     */
    @Column
    private String goodsdesc;
    /**
     * 商品类目
     */
    @Column
    @CodeTable(codetbl = "FT-CODE.Gvcodes")
    private String gvcode;
    /**
     * 商品类目属性00
     */
    @Column
    private String tgvattr00;
    /**
     * 启用申报要素属性标识
     */
    @Column
    @CodeTable(value = "DT_90.YN")
    private String has_me;
    /**
     * 库存属性组
     */
    @Column
    private String sgtrgrpcode;
    /**
     * 库存属性00
     */
    @Column
    private String sgattr00;
    /**
     * 库存属性01
     */
    @Column
    private String sgattr01;
    /**
     * 库存属性02
     */
    @Column
    private String sgattr02;
    /**
     * 库存属性03
     */
    @Column
    private String sgattr03;
    /**
     * 库存属性04
     */
    @Column
    private String sgattr04;
    /**
     * 库存属性05
     */
    @Column
    private String sgattr05;
    /**
     * 库存属性06
     */
    @Column
    private String sgattr06;
    /**
     * 库存属性07
     */
    @Column
    private String sgattr07;
    /**
     * 库存属性08
     */
    @Column
    private String sgattr08;
    /**
     * 库存属性09
     */
    @Column
    private String sgattr09;
    /**
     * 库存属性10
     */
    @Column
    private String sgattr10;
    /**
     * 库存属性11
     */
    @Column
    private String sgattr11;
    /**
     * 库存属性12
     */
    @Column
    private String sgattr12;
    /**
     * 库存属性13
     */
    @Column
    private String sgattr13;
    /**
     * 库存属性14
     */
    @Column
    private String sgattr14;
    /**
     * 库存属性15
     */
    @Column
    private String sgattr15;
    /**
     * 库存属性16
     */
    @Column
    private String sgattr16;
    /**
     * 库存属性17
     */
    @Column
    private String sgattr17;
    /**
     * 库存属性18
     */
    @Column
    private String sgattr18;
    /**
     * 库存属性19
     */
    @Column
    private String sgattr19;
    /**
     * 合同数量
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Qty}")
    @Positive() //禁止输入负数及0
    private BigDecimal qtc;
    /**
     * 合同单位
     */
    @Column
    @CodeTable(codetbl = "FT-CODE.SysUnit")
    private String qtcunit;
    /**
     * 统计数量
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Qty}")
    @Positive() //禁止输入负数及0
    private BigDecimal qty;
    /**
     * 统计单位
     */
    @Column
    @CodeTable(codetbl = "FT-CODE.SysUnit")
    private String qtyunit;
    /**
     * 合同单位对统计单位换算比例
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.UnitRatio}")
    @Positive()
    private BigDecimal qcyrate;
    /**
     * 单价
     */
    @VOField(maxdeci = "${OPTS.Decimal.Upric}")
    @PositiveOrZero() //禁止输入负数
    @Column
    private BigDecimal upric;
    /**
     * 原币金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    @PositiveOrZero()
    private BigDecimal fcy;
    /**
     * 本位币金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    @PositiveOrZero()
    private BigDecimal scy;
    /**
     * 人民币金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    @PositiveOrZero()
    private BigDecimal zcny;
    /**
     * 美元金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    @PositiveOrZero()
    private BigDecimal zusd;
    /**
     * 币种
     */
    @Column
    @CodeTable(value = "FT-CODE.Fcode")
    private String fcode;
    /**
     * 本位币种
     */
    @Column
    @CodeTable(value = "FT-CODE.Fcode")
    private String sfcode;
    /**
     * 对本位币汇率
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.ERate}")
    @Positive
    private BigDecimal fserate;
    /**
     * 对人民币汇率
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.ERate}")
    @Positive
    private BigDecimal fcerate;
    /**
     * 对美元汇率
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.ERate}")
    @Positive
    private BigDecimal fuerate;
    /**
     * 关税税率
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.TRate}")
    @PositiveOrZero
    private BigDecimal customrate;
    /**
     * 消费税税率
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.TRate}")
    @PositiveOrZero
    private BigDecimal consumerate;
    /**
     * 增值税税率
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.TRate}")
    @PositiveOrZero
    private BigDecimal addtaxrate;
    /**
     * 历史标识
     */
    @Column
    private Integer hisflag;
    /**
     * 虚拟商品标识
     */
    @Column
    private Integer virtualflag;
    /**
     * 预留文本字段00
     */
    @Column
    private String txt00;
    /**
     * 预留文本字段01
     */
    @Column
    private String txt01;
    /**
     * 预留文本字段02
     */
    @Column
    private String txt02;
    /**
     * 预留文本字段03
     */
    @Column
    private String txt03;
    /**
     * 预留文本字段04
     */
    @Column
    private String txt04;
    /**
     * 预留文本字段05
     */
    @Column
    private String txt05;
    /**
     * 预留文本字段06
     */
    @Column
    private String txt06;
    /**
     * 预留文本字段07
     */
    @Column
    private String txt07;
    /**
     * 预留文本字段08
     */
    @Column
    private String txt08;
    /**
     * 预留文本字段09
     */
    @Column
    private String txt09;
    /**
     * 预留大文本字段01
     */
    @Column
    private String ltxt01;
    /**
     * 预留大文本字段02
     */
    @Column
    private String ltxt02;
    /**
     * 预留日期字段00
     */
    @Column
    private Date dat00;
    /**
     * 预留日期字段01
     */
    @Column
    private Date dat01;
    /**
     * 预留日期字段02
     */
    @Column
    private Date dat02;
    /**
     * 预留整数字段00
     */
    @Column
    private Integer int00;
    /**
     * 预留整数字段01
     */
    @Column
    private Integer int01;
    /**
     * 预留整数字段02
     */
    @Column
    private Integer int02;
    /**
     * 预留数值字段00
     */
    @Column
    private BigDecimal num00;
    /**
     * 预留数值字段01
     */
    @Column
    private BigDecimal num01;
    /**
     * 预留数值字段02
     */
    @Column
    private BigDecimal num02;

    public StudyAgcyGood() {
    }
}
