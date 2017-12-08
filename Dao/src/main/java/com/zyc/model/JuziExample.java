package com.zyc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JuziExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public JuziExample() {
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

        public Criteria andJuziidIsNull() {
            addCriterion("juziid is null");
            return (Criteria) this;
        }

        public Criteria andJuziidIsNotNull() {
            addCriterion("juziid is not null");
            return (Criteria) this;
        }

        public Criteria andJuziidEqualTo(Integer value) {
            addCriterion("juziid =", value, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidNotEqualTo(Integer value) {
            addCriterion("juziid <>", value, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidGreaterThan(Integer value) {
            addCriterion("juziid >", value, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidGreaterThanOrEqualTo(Integer value) {
            addCriterion("juziid >=", value, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidLessThan(Integer value) {
            addCriterion("juziid <", value, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidLessThanOrEqualTo(Integer value) {
            addCriterion("juziid <=", value, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidIn(List<Integer> values) {
            addCriterion("juziid in", values, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidNotIn(List<Integer> values) {
            addCriterion("juziid not in", values, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidBetween(Integer value1, Integer value2) {
            addCriterion("juziid between", value1, value2, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuziidNotBetween(Integer value1, Integer value2) {
            addCriterion("juziid not between", value1, value2, "juziid");
            return (Criteria) this;
        }

        public Criteria andJuzineirongIsNull() {
            addCriterion("juzineirong is null");
            return (Criteria) this;
        }

        public Criteria andJuzineirongIsNotNull() {
            addCriterion("juzineirong is not null");
            return (Criteria) this;
        }

        public Criteria andJuzineirongEqualTo(String value) {
            addCriterion("juzineirong =", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongNotEqualTo(String value) {
            addCriterion("juzineirong <>", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongGreaterThan(String value) {
            addCriterion("juzineirong >", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongGreaterThanOrEqualTo(String value) {
            addCriterion("juzineirong >=", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongLessThan(String value) {
            addCriterion("juzineirong <", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongLessThanOrEqualTo(String value) {
            addCriterion("juzineirong <=", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongLike(String value) {
            addCriterion("juzineirong like", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongNotLike(String value) {
            addCriterion("juzineirong not like", value, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongIn(List<String> values) {
            addCriterion("juzineirong in", values, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongNotIn(List<String> values) {
            addCriterion("juzineirong not in", values, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongBetween(String value1, String value2) {
            addCriterion("juzineirong between", value1, value2, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzineirongNotBetween(String value1, String value2) {
            addCriterion("juzineirong not between", value1, value2, "juzineirong");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuIsNull() {
            addCriterion("juzichuchu is null");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuIsNotNull() {
            addCriterion("juzichuchu is not null");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuEqualTo(String value) {
            addCriterion("juzichuchu =", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuNotEqualTo(String value) {
            addCriterion("juzichuchu <>", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuGreaterThan(String value) {
            addCriterion("juzichuchu >", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuGreaterThanOrEqualTo(String value) {
            addCriterion("juzichuchu >=", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuLessThan(String value) {
            addCriterion("juzichuchu <", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuLessThanOrEqualTo(String value) {
            addCriterion("juzichuchu <=", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuLike(String value) {
            addCriterion("juzichuchu like", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuNotLike(String value) {
            addCriterion("juzichuchu not like", value, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuIn(List<String> values) {
            addCriterion("juzichuchu in", values, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuNotIn(List<String> values) {
            addCriterion("juzichuchu not in", values, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuBetween(String value1, String value2) {
            addCriterion("juzichuchu between", value1, value2, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzichuchuNotBetween(String value1, String value2) {
            addCriterion("juzichuchu not between", value1, value2, "juzichuchu");
            return (Criteria) this;
        }

        public Criteria andJuzileixingIsNull() {
            addCriterion("juzileixing is null");
            return (Criteria) this;
        }

        public Criteria andJuzileixingIsNotNull() {
            addCriterion("juzileixing is not null");
            return (Criteria) this;
        }

        public Criteria andJuzileixingEqualTo(Integer value) {
            addCriterion("juzileixing =", value, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingNotEqualTo(Integer value) {
            addCriterion("juzileixing <>", value, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingGreaterThan(Integer value) {
            addCriterion("juzileixing >", value, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingGreaterThanOrEqualTo(Integer value) {
            addCriterion("juzileixing >=", value, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingLessThan(Integer value) {
            addCriterion("juzileixing <", value, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingLessThanOrEqualTo(Integer value) {
            addCriterion("juzileixing <=", value, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingIn(List<Integer> values) {
            addCriterion("juzileixing in", values, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingNotIn(List<Integer> values) {
            addCriterion("juzileixing not in", values, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingBetween(Integer value1, Integer value2) {
            addCriterion("juzileixing between", value1, value2, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andJuzileixingNotBetween(Integer value1, Integer value2) {
            addCriterion("juzileixing not between", value1, value2, "juzileixing");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianIsNull() {
            addCriterion("tianjiashijian is null");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianIsNotNull() {
            addCriterion("tianjiashijian is not null");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianEqualTo(Date value) {
            addCriterion("tianjiashijian =", value, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianNotEqualTo(Date value) {
            addCriterion("tianjiashijian <>", value, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianGreaterThan(Date value) {
            addCriterion("tianjiashijian >", value, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianGreaterThanOrEqualTo(Date value) {
            addCriterion("tianjiashijian >=", value, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianLessThan(Date value) {
            addCriterion("tianjiashijian <", value, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianLessThanOrEqualTo(Date value) {
            addCriterion("tianjiashijian <=", value, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianIn(List<Date> values) {
            addCriterion("tianjiashijian in", values, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianNotIn(List<Date> values) {
            addCriterion("tianjiashijian not in", values, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianBetween(Date value1, Date value2) {
            addCriterion("tianjiashijian between", value1, value2, "tianjiashijian");
            return (Criteria) this;
        }

        public Criteria andTianjiashijianNotBetween(Date value1, Date value2) {
            addCriterion("tianjiashijian not between", value1, value2, "tianjiashijian");
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