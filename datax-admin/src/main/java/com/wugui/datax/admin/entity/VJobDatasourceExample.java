package com.wugui.datax.admin.entity;

import java.util.ArrayList;
import java.util.List;

public class VJobDatasourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VJobDatasourceExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andJobDescIsNull() {
            addCriterion("job_desc is null");
            return (Criteria) this;
        }

        public Criteria andJobDescIsNotNull() {
            addCriterion("job_desc is not null");
            return (Criteria) this;
        }

        public Criteria andJobDescEqualTo(String value) {
            addCriterion("job_desc =", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotEqualTo(String value) {
            addCriterion("job_desc <>", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescGreaterThan(String value) {
            addCriterion("job_desc >", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescGreaterThanOrEqualTo(String value) {
            addCriterion("job_desc >=", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLessThan(String value) {
            addCriterion("job_desc <", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLessThanOrEqualTo(String value) {
            addCriterion("job_desc <=", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescLike(String value) {
            addCriterion("job_desc like", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotLike(String value) {
            addCriterion("job_desc not like", value, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescIn(List<String> values) {
            addCriterion("job_desc in", values, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotIn(List<String> values) {
            addCriterion("job_desc not in", values, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescBetween(String value1, String value2) {
            addCriterion("job_desc between", value1, value2, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andJobDescNotBetween(String value1, String value2) {
            addCriterion("job_desc not between", value1, value2, "jobDesc");
            return (Criteria) this;
        }

        public Criteria andSourcenameIsNull() {
            addCriterion("sourceName is null");
            return (Criteria) this;
        }

        public Criteria andSourcenameIsNotNull() {
            addCriterion("sourceName is not null");
            return (Criteria) this;
        }

        public Criteria andSourcenameEqualTo(String value) {
            addCriterion("sourceName =", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameNotEqualTo(String value) {
            addCriterion("sourceName <>", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameGreaterThan(String value) {
            addCriterion("sourceName >", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameGreaterThanOrEqualTo(String value) {
            addCriterion("sourceName >=", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameLessThan(String value) {
            addCriterion("sourceName <", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameLessThanOrEqualTo(String value) {
            addCriterion("sourceName <=", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameLike(String value) {
            addCriterion("sourceName like", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameNotLike(String value) {
            addCriterion("sourceName not like", value, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameIn(List<String> values) {
            addCriterion("sourceName in", values, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameNotIn(List<String> values) {
            addCriterion("sourceName not in", values, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameBetween(String value1, String value2) {
            addCriterion("sourceName between", value1, value2, "sourcename");
            return (Criteria) this;
        }

        public Criteria andSourcenameNotBetween(String value1, String value2) {
            addCriterion("sourceName not between", value1, value2, "sourcename");
            return (Criteria) this;
        }

        public Criteria andTargetnameIsNull() {
            addCriterion("targetName is null");
            return (Criteria) this;
        }

        public Criteria andTargetnameIsNotNull() {
            addCriterion("targetName is not null");
            return (Criteria) this;
        }

        public Criteria andTargetnameEqualTo(String value) {
            addCriterion("targetName =", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameNotEqualTo(String value) {
            addCriterion("targetName <>", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameGreaterThan(String value) {
            addCriterion("targetName >", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameGreaterThanOrEqualTo(String value) {
            addCriterion("targetName >=", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameLessThan(String value) {
            addCriterion("targetName <", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameLessThanOrEqualTo(String value) {
            addCriterion("targetName <=", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameLike(String value) {
            addCriterion("targetName like", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameNotLike(String value) {
            addCriterion("targetName not like", value, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameIn(List<String> values) {
            addCriterion("targetName in", values, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameNotIn(List<String> values) {
            addCriterion("targetName not in", values, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameBetween(String value1, String value2) {
            addCriterion("targetName between", value1, value2, "targetname");
            return (Criteria) this;
        }

        public Criteria andTargetnameNotBetween(String value1, String value2) {
            addCriterion("targetName not between", value1, value2, "targetname");
            return (Criteria) this;
        }

        public Criteria andSourceuserIsNull() {
            addCriterion("sourceUser is null");
            return (Criteria) this;
        }

        public Criteria andSourceuserIsNotNull() {
            addCriterion("sourceUser is not null");
            return (Criteria) this;
        }

        public Criteria andSourceuserEqualTo(String value) {
            addCriterion("sourceUser =", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserNotEqualTo(String value) {
            addCriterion("sourceUser <>", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserGreaterThan(String value) {
            addCriterion("sourceUser >", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserGreaterThanOrEqualTo(String value) {
            addCriterion("sourceUser >=", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserLessThan(String value) {
            addCriterion("sourceUser <", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserLessThanOrEqualTo(String value) {
            addCriterion("sourceUser <=", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserLike(String value) {
            addCriterion("sourceUser like", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserNotLike(String value) {
            addCriterion("sourceUser not like", value, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserIn(List<String> values) {
            addCriterion("sourceUser in", values, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserNotIn(List<String> values) {
            addCriterion("sourceUser not in", values, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserBetween(String value1, String value2) {
            addCriterion("sourceUser between", value1, value2, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andSourceuserNotBetween(String value1, String value2) {
            addCriterion("sourceUser not between", value1, value2, "sourceuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserIsNull() {
            addCriterion("targetUser is null");
            return (Criteria) this;
        }

        public Criteria andTargetuserIsNotNull() {
            addCriterion("targetUser is not null");
            return (Criteria) this;
        }

        public Criteria andTargetuserEqualTo(String value) {
            addCriterion("targetUser =", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserNotEqualTo(String value) {
            addCriterion("targetUser <>", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserGreaterThan(String value) {
            addCriterion("targetUser >", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserGreaterThanOrEqualTo(String value) {
            addCriterion("targetUser >=", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserLessThan(String value) {
            addCriterion("targetUser <", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserLessThanOrEqualTo(String value) {
            addCriterion("targetUser <=", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserLike(String value) {
            addCriterion("targetUser like", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserNotLike(String value) {
            addCriterion("targetUser not like", value, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserIn(List<String> values) {
            addCriterion("targetUser in", values, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserNotIn(List<String> values) {
            addCriterion("targetUser not in", values, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserBetween(String value1, String value2) {
            addCriterion("targetUser between", value1, value2, "targetuser");
            return (Criteria) this;
        }

        public Criteria andTargetuserNotBetween(String value1, String value2) {
            addCriterion("targetUser not between", value1, value2, "targetuser");
            return (Criteria) this;
        }

        public Criteria andSourceurlIsNull() {
            addCriterion("sourceUrl is null");
            return (Criteria) this;
        }

        public Criteria andSourceurlIsNotNull() {
            addCriterion("sourceUrl is not null");
            return (Criteria) this;
        }

        public Criteria andSourceurlEqualTo(String value) {
            addCriterion("sourceUrl =", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlNotEqualTo(String value) {
            addCriterion("sourceUrl <>", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlGreaterThan(String value) {
            addCriterion("sourceUrl >", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlGreaterThanOrEqualTo(String value) {
            addCriterion("sourceUrl >=", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlLessThan(String value) {
            addCriterion("sourceUrl <", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlLessThanOrEqualTo(String value) {
            addCriterion("sourceUrl <=", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlLike(String value) {
            addCriterion("sourceUrl like", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlNotLike(String value) {
            addCriterion("sourceUrl not like", value, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlIn(List<String> values) {
            addCriterion("sourceUrl in", values, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlNotIn(List<String> values) {
            addCriterion("sourceUrl not in", values, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlBetween(String value1, String value2) {
            addCriterion("sourceUrl between", value1, value2, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andSourceurlNotBetween(String value1, String value2) {
            addCriterion("sourceUrl not between", value1, value2, "sourceurl");
            return (Criteria) this;
        }

        public Criteria andTargeturlIsNull() {
            addCriterion("targetUrl is null");
            return (Criteria) this;
        }

        public Criteria andTargeturlIsNotNull() {
            addCriterion("targetUrl is not null");
            return (Criteria) this;
        }

        public Criteria andTargeturlEqualTo(String value) {
            addCriterion("targetUrl =", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlNotEqualTo(String value) {
            addCriterion("targetUrl <>", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlGreaterThan(String value) {
            addCriterion("targetUrl >", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlGreaterThanOrEqualTo(String value) {
            addCriterion("targetUrl >=", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlLessThan(String value) {
            addCriterion("targetUrl <", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlLessThanOrEqualTo(String value) {
            addCriterion("targetUrl <=", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlLike(String value) {
            addCriterion("targetUrl like", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlNotLike(String value) {
            addCriterion("targetUrl not like", value, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlIn(List<String> values) {
            addCriterion("targetUrl in", values, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlNotIn(List<String> values) {
            addCriterion("targetUrl not in", values, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlBetween(String value1, String value2) {
            addCriterion("targetUrl between", value1, value2, "targeturl");
            return (Criteria) this;
        }

        public Criteria andTargeturlNotBetween(String value1, String value2) {
            addCriterion("targetUrl not between", value1, value2, "targeturl");
            return (Criteria) this;
        }

        public Criteria andJobJsonIsNull() {
            addCriterion("job_json is null");
            return (Criteria) this;
        }

        public Criteria andJobJsonIsNotNull() {
            addCriterion("job_json is not null");
            return (Criteria) this;
        }

        public Criteria andJobJsonEqualTo(String value) {
            addCriterion("job_json =", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonNotEqualTo(String value) {
            addCriterion("job_json <>", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonGreaterThan(String value) {
            addCriterion("job_json >", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonGreaterThanOrEqualTo(String value) {
            addCriterion("job_json >=", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonLessThan(String value) {
            addCriterion("job_json <", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonLessThanOrEqualTo(String value) {
            addCriterion("job_json <=", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonLike(String value) {
            addCriterion("job_json like", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonNotLike(String value) {
            addCriterion("job_json not like", value, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonIn(List<String> values) {
            addCriterion("job_json in", values, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonNotIn(List<String> values) {
            addCriterion("job_json not in", values, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonBetween(String value1, String value2) {
            addCriterion("job_json between", value1, value2, "jobJson");
            return (Criteria) this;
        }

        public Criteria andJobJsonNotBetween(String value1, String value2) {
            addCriterion("job_json not between", value1, value2, "jobJson");
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