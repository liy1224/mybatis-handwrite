package com.handrt.mybatis.entity;

import java.util.Date;

public class SystemRole {
   
    private Long id;

    private Long tenant_id;

    private String tenant_name;

    private Integer tenant_type;

    private Long project_id;

    private String project_name;

    private String name;

    private String pinyin;

    private String short_pinyin;

    private String permissions;

    private String descp;

    private Integer status;

    private String modified_account;

    private String modified_name;

    private Date create_time;

    private Date modified_time;

    private Integer access_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(Long tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getTenant_name() {
		return tenant_name;
	}

	public void setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
	}

	public Integer getTenant_type() {
		return tenant_type;
	}

	public void setTenant_type(Integer tenant_type) {
		this.tenant_type = tenant_type;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getShort_pinyin() {
		return short_pinyin;
	}

	public void setShort_pinyin(String short_pinyin) {
		this.short_pinyin = short_pinyin;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getModified_account() {
		return modified_account;
	}

	public void setModified_account(String modified_account) {
		this.modified_account = modified_account;
	}

	public String getModified_name() {
		return modified_name;
	}

	public void setModified_name(String modified_name) {
		this.modified_name = modified_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModified_time() {
		return modified_time;
	}

	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}

	public Integer getAccess_type() {
		return access_type;
	}

	public void setAccess_type(Integer access_type) {
		this.access_type = access_type;
	}
}