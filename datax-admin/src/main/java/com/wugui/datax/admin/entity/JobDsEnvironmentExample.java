package com.wugui.datax.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobDsEnvironmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JobDsEnvironmentExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIsNull() {
            addCriterion("datasource_id is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIsNotNull() {
            addCriterion("datasource_id is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdEqualTo(String value) {
            addCriterion("datasource_id =", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotEqualTo(String value) {
            addCriterion("datasource_id <>", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdGreaterThan(String value) {
            addCriterion("datasource_id >", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("datasource_id >=", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLessThan(String value) {
            addCriterion("datasource_id <", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLessThanOrEqualTo(String value) {
            addCriterion("datasource_id <=", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLike(String value) {
            addCriterion("datasource_id like", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotLike(String value) {
            addCriterion("datasource_id not like", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIn(List<String> values) {
            addCriterion("datasource_id in", values, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotIn(List<String> values) {
            addCriterion("datasource_id not in", values, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdBetween(String value1, String value2) {
            addCriterion("datasource_id between", value1, value2, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotBetween(String value1, String value2) {
            addCriterion("datasource_id not between", value1, value2, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameIsNull() {
            addCriterion("datasource_name is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameIsNotNull() {
            addCriterion("datasource_name is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameEqualTo(String value) {
            addCriterion("datasource_name =", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameNotEqualTo(String value) {
            addCriterion("datasource_name <>", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameGreaterThan(String value) {
            addCriterion("datasource_name >", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("datasource_name >=", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameLessThan(String value) {
            addCriterion("datasource_name <", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameLessThanOrEqualTo(String value) {
            addCriterion("datasource_name <=", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameLike(String value) {
            addCriterion("datasource_name like", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameNotLike(String value) {
            addCriterion("datasource_name not like", value, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameIn(List<String> values) {
            addCriterion("datasource_name in", values, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameNotIn(List<String> values) {
            addCriterion("datasource_name not in", values, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameBetween(String value1, String value2) {
            addCriterion("datasource_name between", value1, value2, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceNameNotBetween(String value1, String value2) {
            addCriterion("datasource_name not between", value1, value2, "datasourceName");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeIsNull() {
            addCriterion("datasource_type is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeIsNotNull() {
            addCriterion("datasource_type is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeEqualTo(String value) {
            addCriterion("datasource_type =", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotEqualTo(String value) {
            addCriterion("datasource_type <>", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeGreaterThan(String value) {
            addCriterion("datasource_type >", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("datasource_type >=", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeLessThan(String value) {
            addCriterion("datasource_type <", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeLessThanOrEqualTo(String value) {
            addCriterion("datasource_type <=", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeLike(String value) {
            addCriterion("datasource_type like", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotLike(String value) {
            addCriterion("datasource_type not like", value, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeIn(List<String> values) {
            addCriterion("datasource_type in", values, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotIn(List<String> values) {
            addCriterion("datasource_type not in", values, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeBetween(String value1, String value2) {
            addCriterion("datasource_type between", value1, value2, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andDatasourceTypeNotBetween(String value1, String value2) {
            addCriterion("datasource_type not between", value1, value2, "datasourceType");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("`path` is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("`path` is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("`path` =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("`path` <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("`path` >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("`path` >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("`path` <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("`path` <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("`path` like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("`path` not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("`path` in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("`path` not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("`path` between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("`path` not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andDefaultFsIsNull() {
            addCriterion("default_fs is null");
            return (Criteria) this;
        }

        public Criteria andDefaultFsIsNotNull() {
            addCriterion("default_fs is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultFsEqualTo(String value) {
            addCriterion("default_fs =", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsNotEqualTo(String value) {
            addCriterion("default_fs <>", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsGreaterThan(String value) {
            addCriterion("default_fs >", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsGreaterThanOrEqualTo(String value) {
            addCriterion("default_fs >=", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsLessThan(String value) {
            addCriterion("default_fs <", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsLessThanOrEqualTo(String value) {
            addCriterion("default_fs <=", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsLike(String value) {
            addCriterion("default_fs like", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsNotLike(String value) {
            addCriterion("default_fs not like", value, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsIn(List<String> values) {
            addCriterion("default_fs in", values, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsNotIn(List<String> values) {
            addCriterion("default_fs not in", values, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsBetween(String value1, String value2) {
            addCriterion("default_fs between", value1, value2, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andDefaultFsNotBetween(String value1, String value2) {
            addCriterion("default_fs not between", value1, value2, "defaultFs");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(String value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(String value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(String value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(String value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(String value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(String value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLike(String value) {
            addCriterion("file_type like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotLike(String value) {
            addCriterion("file_type not like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<String> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<String> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(String value1, String value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(String value1, String value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterIsNull() {
            addCriterion("field_delimiter is null");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterIsNotNull() {
            addCriterion("field_delimiter is not null");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterEqualTo(String value) {
            addCriterion("field_delimiter =", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterNotEqualTo(String value) {
            addCriterion("field_delimiter <>", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterGreaterThan(String value) {
            addCriterion("field_delimiter >", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterGreaterThanOrEqualTo(String value) {
            addCriterion("field_delimiter >=", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterLessThan(String value) {
            addCriterion("field_delimiter <", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterLessThanOrEqualTo(String value) {
            addCriterion("field_delimiter <=", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterLike(String value) {
            addCriterion("field_delimiter like", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterNotLike(String value) {
            addCriterion("field_delimiter not like", value, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterIn(List<String> values) {
            addCriterion("field_delimiter in", values, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterNotIn(List<String> values) {
            addCriterion("field_delimiter not in", values, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterBetween(String value1, String value2) {
            addCriterion("field_delimiter between", value1, value2, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andFieldDelimiterNotBetween(String value1, String value2) {
            addCriterion("field_delimiter not between", value1, value2, "fieldDelimiter");
            return (Criteria) this;
        }

        public Criteria andNameNodesIsNull() {
            addCriterion("name_nodes is null");
            return (Criteria) this;
        }

        public Criteria andNameNodesIsNotNull() {
            addCriterion("name_nodes is not null");
            return (Criteria) this;
        }

        public Criteria andNameNodesEqualTo(String value) {
            addCriterion("name_nodes =", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesNotEqualTo(String value) {
            addCriterion("name_nodes <>", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesGreaterThan(String value) {
            addCriterion("name_nodes >", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesGreaterThanOrEqualTo(String value) {
            addCriterion("name_nodes >=", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesLessThan(String value) {
            addCriterion("name_nodes <", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesLessThanOrEqualTo(String value) {
            addCriterion("name_nodes <=", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesLike(String value) {
            addCriterion("name_nodes like", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesNotLike(String value) {
            addCriterion("name_nodes not like", value, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesIn(List<String> values) {
            addCriterion("name_nodes in", values, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesNotIn(List<String> values) {
            addCriterion("name_nodes not in", values, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesBetween(String value1, String value2) {
            addCriterion("name_nodes between", value1, value2, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameNodesNotBetween(String value1, String value2) {
            addCriterion("name_nodes not between", value1, value2, "nameNodes");
            return (Criteria) this;
        }

        public Criteria andNameServicesIsNull() {
            addCriterion("name_services is null");
            return (Criteria) this;
        }

        public Criteria andNameServicesIsNotNull() {
            addCriterion("name_services is not null");
            return (Criteria) this;
        }

        public Criteria andNameServicesEqualTo(String value) {
            addCriterion("name_services =", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesNotEqualTo(String value) {
            addCriterion("name_services <>", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesGreaterThan(String value) {
            addCriterion("name_services >", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesGreaterThanOrEqualTo(String value) {
            addCriterion("name_services >=", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesLessThan(String value) {
            addCriterion("name_services <", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesLessThanOrEqualTo(String value) {
            addCriterion("name_services <=", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesLike(String value) {
            addCriterion("name_services like", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesNotLike(String value) {
            addCriterion("name_services not like", value, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesIn(List<String> values) {
            addCriterion("name_services in", values, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesNotIn(List<String> values) {
            addCriterion("name_services not in", values, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesBetween(String value1, String value2) {
            addCriterion("name_services between", value1, value2, "nameServices");
            return (Criteria) this;
        }

        public Criteria andNameServicesNotBetween(String value1, String value2) {
            addCriterion("name_services not between", value1, value2, "nameServices");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andProxyProviderIsNull() {
            addCriterion("proxy_provider is null");
            return (Criteria) this;
        }

        public Criteria andProxyProviderIsNotNull() {
            addCriterion("proxy_provider is not null");
            return (Criteria) this;
        }

        public Criteria andProxyProviderEqualTo(String value) {
            addCriterion("proxy_provider =", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderNotEqualTo(String value) {
            addCriterion("proxy_provider <>", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderGreaterThan(String value) {
            addCriterion("proxy_provider >", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderGreaterThanOrEqualTo(String value) {
            addCriterion("proxy_provider >=", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderLessThan(String value) {
            addCriterion("proxy_provider <", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderLessThanOrEqualTo(String value) {
            addCriterion("proxy_provider <=", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderLike(String value) {
            addCriterion("proxy_provider like", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderNotLike(String value) {
            addCriterion("proxy_provider not like", value, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderIn(List<String> values) {
            addCriterion("proxy_provider in", values, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderNotIn(List<String> values) {
            addCriterion("proxy_provider not in", values, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderBetween(String value1, String value2) {
            addCriterion("proxy_provider between", value1, value2, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andProxyProviderNotBetween(String value1, String value2) {
            addCriterion("proxy_provider not between", value1, value2, "proxyProvider");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNull() {
            addCriterion("comments is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("comments is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(String value) {
            addCriterion("comments =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(String value) {
            addCriterion("comments <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(String value) {
            addCriterion("comments >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(String value) {
            addCriterion("comments >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(String value) {
            addCriterion("comments <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(String value) {
            addCriterion("comments <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLike(String value) {
            addCriterion("comments like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotLike(String value) {
            addCriterion("comments not like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<String> values) {
            addCriterion("comments in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<String> values) {
            addCriterion("comments not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(String value1, String value2) {
            addCriterion("comments between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(String value1, String value2) {
            addCriterion("comments not between", value1, value2, "comments");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
