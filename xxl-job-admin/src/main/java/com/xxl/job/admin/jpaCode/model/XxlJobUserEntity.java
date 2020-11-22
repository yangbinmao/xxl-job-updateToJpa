package com.xxl.job.admin.jpaCode.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * @author xuxueli 2019-05-04 16:43:12
 */
@Data
@Table(name="xxl_job_user")
@Entity
public class XxlJobUserEntity {
	@Id    //主键id
	@GeneratedValue(strategy= GenerationType.IDENTITY) //主键生成策略
	@Column(name="id")
	private int id;


	@Column(name ="username")
	private String username;		// 账号
	@Column(name ="password")
	private String password;		// 密码
	@Column(name ="role")
	private int role;				// 角色：0-普通用户、1-管理员
	@Column(name ="permission")
	private String permission;	// 权限：执行器ID列表，多个逗号分割



	// plugin
	public boolean validPermission(int jobGroup){
		if (this.role == 1) {
			return true;
		} else {
			if (StringUtils.hasText(this.permission)) {
				for (String permissionItem : this.permission.split(",")) {
					if (String.valueOf(jobGroup).equals(permissionItem)) {
						return true;
					}
				}
			}
			return false;
		}

	}

}
