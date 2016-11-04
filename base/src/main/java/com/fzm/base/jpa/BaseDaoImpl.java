package com.fzm.base.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.fzm.base.dto.ResultPage;

public class BaseDaoImpl<T,ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T,ID>{

	@SuppressWarnings("unused")
	private final EntityManager em;
	
	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em=em;
	}

	@Override
	public ResultPage<T> getPageInfo(Integer pageIndex, Integer pageSize, final Map<String,Object> conditions) {
		// TODO Auto-generated method stub
		ResultPage<T> resultPage = new ResultPage<T>();
		resultPage.setPageIndex(pageIndex);
		resultPage.setPageSize(pageSize);
		if(pageIndex == null){
			pageIndex = 0;
		}
		else{
			pageIndex--; // 因为从0开始计算
		}
		if(pageSize == null){
			pageSize = 10;
		}
		Pageable pageable = new PageRequest(pageIndex, pageSize, Sort.Direction.DESC, "id");
		// 查询条件
		Specification<T> spec = new Specification<T>() {
            @Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
            	// CriteriaQuery对象只对实体类型或嵌入式类型的Criteria查询起作用
            	// Predicate：一个简单或复杂的谓词类型，其实就相当于条件或者是条件组合
            	// 在criteria 查询中，查询条件通过Predicate或Expression实例应用到CriteriaQuery对象上
            	// CriteriaBuilder也作为Predicate实例的工厂，通过调用CriteriaBuilder 的条件方（ equal，notEqual， gt， ge，lt， le，between，like等）创建Predicate对象
            	// 复合的Predicate 语句可以使用CriteriaBuilder的and, or andnot 方法构建
            	return query.where(generateExpression(root, cb, conditions))  
                        .getRestriction();
			}
        };
		Page<T> japPage = this.findAll(spec, pageable);
		resultPage.setDataList(japPage.getContent());
		resultPage.setTotalCount(japPage.getTotalElements());		
		return resultPage;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Predicate generateExpression(Root<T> root, CriteriaBuilder cb, Map<String,Object> conditions) {
		
		if (conditions==null) {
			return cb.conjunction();
		}
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (String key : conditions.keySet()){
			String operator = key.trim().split("_")[0].toUpperCase();
			String field = key.trim().split("_")[1];
			Path expression = root.get(field);
			switch (operator) {
			case "EQ":
				predicates.add(cb.equal(expression,
						conditions.get(key)));
				break;
			case "NEQ" :
				predicates.add(cb.notEqual(expression, conditions.get(key)));
				break;
			case "LIKE":
				predicates.add(cb.like(expression, "%"
						+ conditions.get(key) + "%"));
				break;
			case "LLIKE":
				predicates.add(cb.like(expression, "%"
						+ conditions.get(key)));
				break;
			case "RLIKE":
				predicates.add(cb.like(expression,
						conditions.get(key) + "%"));
				break;
			case "NLIKE":
				predicates.add(cb.notLike(expression,
						conditions.get(key) + "%"));
				break;
			case "GT":
				predicates.add(cb.greaterThan(expression,
						(Comparable) conditions.get(key)));
				break;
			case "LT":
				predicates.add(cb.lessThan(expression,
						(Comparable) conditions.get(key)));
				break;
			case "GE":
				predicates.add(cb.greaterThanOrEqualTo(
						expression, (Comparable) conditions.get(key)));
				break;
			case "LE":
				predicates.add(cb.lessThanOrEqualTo(
					expression, (Comparable) conditions.get(key)));
				break;
			case "NULL":
				predicates.add(expression.isNull());
				break;
			case "NOTNULL":
				predicates.add(expression.isNotNull());
				break;
			case "IN":
				Predicate predicate = null;
				if (conditions.get(key) instanceof String) {
					String[] values = String.valueOf(conditions.get(key))
							.split(",");
					predicate = expression
							.in(Arrays.asList(values));
				} else if (conditions.get(key) instanceof String[]) {
					predicate = expression.in(Arrays
							.asList((String[]) conditions.get(key)));
				} else {
					predicate = expression
							.in((Collection<Object>) conditions.get(key));
				}
				predicates.add(predicate);
				break;
			case "NOTIN":
				Predicate predicate2 = null;
				if (conditions.get(key) instanceof String) {
					String[] values = String.valueOf(conditions.get(key))
							.split(",");
					predicate2 = expression
							.in(Arrays.asList(values));
				} else if (conditions.get(key) instanceof String[]) {
					predicate2 = expression.in(Arrays
							.asList((String[]) conditions.get(key)));
				} else {
					predicate2 = expression
							.in((Collection<Object>) conditions.get(key));
				}
				predicates.add(cb.not(predicate2));
				break;
			}
			
		}
		// 将所有条件用 and 联合起来
		if (predicates.size() > 0) {
			return cb.and(predicates
					.toArray(new Predicate[predicates.size()]));
		}
		return cb.conjunction();
	}
	
}
