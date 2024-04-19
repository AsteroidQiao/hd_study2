package snsoft.study.code.service;

import snsoft.api.dx.SheetInfo;
import snsoft.api.service.SpringBean;
import snsoft.plat.bas.busi.service.Funccode;

/**
 * <p>标题：学习代码 pricelib 服务</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：AsteroidQiao</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024/04/10</p>
 * <p>类路径：snsoft.study.code.service.StudyCodePricelibService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = StudyCodePricelibService.BEAN_NAME)
@Funccode("ST-CODE.TX.XYPricelib.SV")
@SheetInfo("ST-CODE.TX.XYPricelib")
public interface StudyCodePricelibService {
    String BEAN_NAME = "STUDY-CODE.StudyCodePricelibService";
}
