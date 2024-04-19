package snsoft.study.code.service;


import lombok.Getter;
import lombok.Setter;
import snsoft.api.bas.QueryParams;
import snsoft.api.sql.BcodeColumn;
import snsoft.api.sql.SqlColumn;
import snsoft.api.sql.SqlExpression;
import snsoft.api.sql.SubColumn;
import snsoft.ft.comm.filter.constans.FTFilterConstans;
import snsoft.ft.trd.tx.prot.bas.constants.ProtConstants;

import javax.persistence.Column;
import java.util.Date;

/**
 * <p>标题：学习代码参数</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/10</p>
 * <p>类路径：snsoft.study.code.service.StudyCodeParam</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class StudyCodeParam extends QueryParams {

    private static final long    serialVersionUID    = -3040314123313892997L;
    /**
     * 协议号
     */
    @SqlColumn(sqlop = SqlExpression.LIKE)
    private String				ptcode;
    /**
     * 协议号/外部协议号
     */
    @SqlColumn(filterBean = FTFilterConstans.FTMixCodeFilterBean, column = "IC:ptcode,outptcode")
    private String				outptcode;
    /**
     * 甲方委托方
     */
    @SqlColumn
    private String				ccodetrust;
    /**
     * 签约日期从
     */
    @Column
    @SqlColumn(column = "signdate", sqlop = SqlExpression.GE)
    private Date				signdatefrom;
    /**
     * 到
     */
    @Column
    @SqlColumn(column = "signdate", sqlop = SqlExpression.LE)
    private Date				signdateto;
    /**
     * 截止日期从
     */
    @Column
    @SqlColumn(column = "duedate", sqlop = SqlExpression.GE)
    private Date				duedatefrom;
    /**
     * 到
     */
    @Column
    @SqlColumn(column = "duedate", sqlop = SqlExpression.LE)
    private Date				duedateto;
    /**
     * 公司
     */
    @BcodeColumn(btype = "02")
    private String				corpbcode;
    /**
     * 人员
     */
    @SqlColumn
    private String				wcode;
    /**
     * 部门
     */
    @BcodeColumn
    private String				bcode;
    /**
     * 状态
     */
    @SqlColumn
    private String				status;
    /**
     * 商户
     */
    @SqlColumn
    private String				cuicode;
    @SqlColumn
    private String				sheetcode;
    /**
     * 协议性质
     */
    @SqlColumn
    private String				protprop;
    /**
     * 协议类型
     */
    @SqlColumn
    private String				prottype;
    /**
     * 协议内部类型
     */
    @SqlColumn
    private String				protinnertype;
    /**
     * 第三方
     */
    @SubColumn(pJoinColumn = "pticode", table = ProtConstants.PROT_THREE_TABLENAME)
    private String				ccode;
    /**
     * 创建人
     */
    @SqlColumn
    private String				vprepare;
    /**
     * 引发终结的协议内码
     */
    @SqlColumn
    private String				endpticode;
    /**
     * 有效日期从
     */
    @SqlColumn(column = "startdate", sqlop = SqlExpression.LE, dateDelay = true)
    private Date				validdatefm;
    /**
     * 到
     */
    @SqlColumn(column = "duedate", sqlop = SqlExpression.GE)
    private Date				validdateto;
    /**
     *创建日期从
     */
    @SqlColumn(column = "predate", sqlop = SqlExpression.GE)
    private Date				predatefm;
    /**
     * 到
     */
    @SqlColumn(column = "predate", sqlop = SqlExpression.LE, dateDelay = true)
    private Date				predateto;
    @SqlColumn
    private String				agentfeemode;
    /**
     * 备注
     */
    @SqlColumn(sqlop = SqlExpression.LIKE)
    private String				remark;
    /**
     * 主表的客户
     */
    @SqlColumn(column = "ccode")
    private String				mccode;
    @SqlColumn
    private String				isgood;
    @SqlColumn
    private String				isfmtprot;
    @SqlColumn(column = "paydesc,agentdesc,remark",sqlop = SqlExpression.LIKE)
    private String 				payagenremark;
}
