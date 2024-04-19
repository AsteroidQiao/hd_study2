package snsoft.study.code.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import snsoft.api.dft.DefaultValue;
import snsoft.api.dx.SheetInfo;
import snsoft.api.dx.VOField;
import snsoft.api.validation.annotation.CodeTable;
import snsoft.api.validation.annotation.ValidResource;
import snsoft.api.validation.validator.SubmitGroup;
import snsoft.approval.entity.ApprConstants;
import snsoft.ft.trd.tx.prot.agcyimp.vo.AgcyImpProtSI;
import snsoft.ft.trd.tx.prot.aimp.vo.AcImpProt;
import snsoft.ft.trd.tx.prot.bas.constants.ProtConstants;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>标题：贸易协议主表 vo</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.vo.StudyAgency</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Setter
@Getter
@Table(name = "st_xy_prot")
@ValidResource("STUDY-CODE")
@SheetInfo("ST-CODE.TX.Prot.XYAgcyImpProt")
public class StudyAgency extends AcImpProt {

    private static final long    serialVersionUID    = -272426977719486538L;
    public static final String SheetCode = "ST-CODE.TX.Prot.XYAgcyImpProt";

    @Column
    @NotNull
    @DefaultValue(ApprConstants.STATUS_DRAFT)
    @CodeTable(value = "90.status", params = "sheetcode:'ST-CODE.TX.Prot.XYAgcyImpProt'")
    private String					status;


    /**业务类型*/
    @Column
    @CodeTable(value = "DT_FT-CODE.BusiType")
    @NotNull
    @DefaultValue("20")
    private String					protinnertype;

    /**
     * 协议性质
     */
    @Column
    @CodeTable(value = "DT_FT-TRD.ProtProp")
    private String					protprop;
    /**
     * 系统协议性质
     */
    @Column
    @CodeTable(value = "DT_FT-TRD.SysProtProp")
    private String					sysprotprop;

    /**协议类型*/
    @Column
    @CodeTable(value = "DT_FT-TRD.AgcyProtType")
    @NotEmpty(groups = { SubmitGroup.class })
    private String					prottype;

    /**开票方式*/
    @Column
    @CodeTable(value = "DT_FT-TRD.AgcyImpInvMode")
    @NotEmpty(groups = { SubmitGroup.class })
    private String					invmode;

    /**委托方*/
    @Column
    @CodeTable(codetbl = "FT-CODE.CcodeLMExt")
    private String					ccodetrust;

    /**客户*/
    @Column
    @CodeTable(codetbl = "FT-CODE.CcodeLMExt")
    private String					ccode;

    /**服务费收取方式*/
    @Column
    @CodeTable(codetbl = "DT_FT-TRD.AgcyImpAgentFeeMode")
    @DefaultValue("10")
    private String					agentfeemode;

    /**服务费是否含税*/
    @Column
    private String					isagentfeetax;

    /**单笔服务费不低于人民币*/
    @Column
    private BigDecimal				sscleastrmb;

    /**报关币种折人民币汇率取*/
    @Column
    private String					cusratetype;

    /**回签人*/
    @Column
    private String					signbacker;

    /**回签日期*/
    @Column
    private Date					signbacktime;

    /**是否格式协议*/
    @Column
    @CodeTable(value = "DT_90.YN")
	@DefaultValue("N")
    private String					isfmtprot;

    /**有无商品*/
    @Column
    @CodeTable(value = "DT_90.YN")
    @DefaultValue("Y")
    private String					isgood;

    /**保证金类型*/
    @Column
    @CodeTable(value = "DT_FT-TRD.AgcyImpIMarginType")
    private String					margintype;

    /**保证金比例*/
    @Column
    @Positive
    @VOField(maxdeci = "4")
    private BigDecimal				marginrate;

    /**保证金金额*/
    @Column
    @Positive
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal				marginfcy;

    /**币种*/
    @Column
    @CodeTable(value = "FT-CODE.Fcode")
    private String					fcode;

    /**本位币种*/
    @Column
    @CodeTable(value = "FT-CODE.Fcode")
    private String					sfcode;

    /**对本位币汇率*/
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.ERate}")
    @Positive
    private BigDecimal				fserate;

    /**对人民币汇率*/
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.ERate}")
    @Positive
    private BigDecimal				fcerate;

    /**对美元汇率*/
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.ERate}")
    @Positive
    private BigDecimal				fuerate;

    /**原币金额*/
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal				fcy;

    /**本位币金额*/
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal				scy;

    /**人民币金额*/
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal				zcny;

    /**美元金额*/
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal				zusd;

    /**
     * 商品表
     */
    @Valid
    @OneToMany
    @JoinColumn(name = ProtConstants.PTICODE_COL)
    private List<StudyAgcyGood> protgoods;

    /**
     * 结算项目
     */
    @Valid
    @OneToMany
    @JoinColumn(name = ProtConstants.PTICODE_COL)
    private List<AgcyImpProtSI>		protsis;

    /**
     * 代理规则
     */
    @Column
    private String agcyrule;

    /**
     * 约束规则
     */
    @Column
    @CodeTable(value = "DT_STUDY-CODE.agcyconstraint")
    @DefaultValue("10")
    private String agcyconstraint;

    /**
     * 优惠原币金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal disfcy;

    /**
     * 优惠本位币金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal disscy;

    /**
     * 优惠人民币金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal diszcny;

    /**
     * 优惠美元金额
     */
    @Column
    @VOField(maxdeci = "${OPTS.Decimal.Money}")
    private BigDecimal diszusd;
    
    public List<StudyAgcyGood> getProtgoods() {
        if (protgoods == null) {
            protgoods = new ArrayList<>();
        }
        return protgoods;
    }
    
    public List<AgcyImpProtSI> getProtsis() {
        if (protsis == null) {
            protsis = new ArrayList<>();
        }
        return protsis;
    }
}
