package snsoftx.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年8月10日下午2:03:38</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class BootConfig
{
	@Autowired
	protected Environment env;
	/**
	 * 项目个性化配置
	 */
}
