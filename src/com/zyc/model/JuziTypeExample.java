package com.zyc.model;

import java.util.ArrayList;
import java.util.List;

public class JuziTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public JuziTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        public Criteria andLeixingidIsNull() {
            addCriterion("leixingid is null");
            return (Criteria) this;
        }

        public Criteria andLeixingidIsNotNull() {
            addCriterion("leixingid is not null");
            return (Criteria) this;
        }

        public Criteria andLeixingidEqualTo(Integer value) {
            addCriterion("leixingid =", value, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidNotEqualTo(Integer value) {
            addCriterion("leixingid <>", value, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidGreaterThan(Integer value) {
            addCriterion("leixingid >", value, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidGreaterThanOrEqualTo(Integer value) {
            addCriterion("leixingid >=", value, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidLessThan(Integer value) {
            addCriterion("leixingid <", value, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidLessThanOrEqualTo(Integer value) {
            addCriterion("leixingid <=", value, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidIn(List<Integer> values) {
            addCriterion("leixingid in", values, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidNotIn(List<Integer> values) {
            addCriterion("leixingid not in", values, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidBetween(Integer value1, Integer value2) {
            addCriterion("leixingid between", value1, value2, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingidNotBetween(Integer value1, Integer value2) {
            addCriterion("leixingid not between", value1, value2, "leixingid");
            return (Criteria) this;
        }

        public Criteria andLeixingmingIsNull() {
            addCriterion("leixingming is null");
            return (Criteria) this;
        }

        public Criteria andLeixingmingIsNotNull() {
            addCriterion("leixingming is not null");
            return (Criteria) this;
        }

        public Criteria andLeixingmingEqualTo(String value) {
            addCriterion("leixingming =", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingNotEqualTo(String value) {
            addCriterion("leixingming <>", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingGreaterThan(String value) {
            addCriterion("leixingming >", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingGreaterThanOrEqualTo(String value) {
            addCriterion("leixingming >=", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingLessThan(String value) {
            addCriterion("leixingming <", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingLessThanOrEqualTo(String value) {
            addCriterion("leixingming <=", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingLike(String value) {
            addCriterion("leixingming like", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingNotLike(String value) {
            addCriterion("leixingming not like", value, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingIn(List<String> values) {
            addCriterion("leixingming in", values, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingNotIn(List<String> values) {
            addCriterion("leixingming not in", values, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingBetween(String value1, String value2) {
            addCriterion("leixingming between", value1, value2, "leixingming");
            return (Criteria) this;
        }

        public Criteria andLeixingmingNotBetween(String value1, String value2) {
            addCriterion("leixingming not between", value1, value2, "leixingming");
            return (Criteria) this;
        }
    }

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