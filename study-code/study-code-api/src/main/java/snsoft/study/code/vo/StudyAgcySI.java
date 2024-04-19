package snsoft.study.code.vo;

import snsoft.api.validation.annotation.ValidResource;
import snsoft.ft.trd.tx.prot.aimp.vo.AcImpProtSI;

import javax.persistence.Table;

/**
 * <p>标题：贸易协议结算项目 vo</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/18</p>
 * <p>类路径：snsoft.study.code.vo.StudyAgcySI</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "st_xy_prot_si")
@ValidResource("STUDY-CODE")
public class StudyAgcySI extends AcImpProtSI {
    private static final long serialVersionUID = 4761957946238310627L;
    
    public StudyAgcySI() {
    }
}
