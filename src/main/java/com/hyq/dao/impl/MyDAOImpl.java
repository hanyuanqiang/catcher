package com.hyq.dao.impl;

import com.hyq.dao.MyDAO;
import com.hyq.entity.PageBean;
import com.hyq.util.CheckUtil;
import com.hyq.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository("myDAO")
@Transactional
@SuppressWarnings("all")
public class MyDAOImpl<T> implements MyDAO<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public List<T> find(Class<T> c, T s_o, PageBean pageBean) {
		Criteria criteria = this.getCurrentSession().createCriteria(c);
		if (CheckUtil.isNotNull(s_o)){
			Field[] fields = s_o.getClass().getDeclaredFields();
			try {
				for (Field field : fields){
					String fieldName = field.getName();		//字段名
					String fieldType = field.getType().getName();	//字段类型
					String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);	//合成该字段的getter方法
					Object fieldValue = s_o.getClass().getMethod(methodName).invoke(s_o);	//执行getter方法获取该字段的值
					if(CheckUtil.isNotNull(fieldValue)){	//如果当前字段不为null，表示该字段是过滤字段
						if (fieldType.equals("java.lang.String")){	//字符串类型用模糊查询
							criteria = criteria.add(Restrictions.like(fieldName,"%"+fieldValue+"%"));
						}else if (fieldType.startsWith("com.hyq.entity")){		//关联属性过滤
							criteria = criteria.createCriteria(fieldName);
							try {
								Class clazz = Class.forName(fieldType);
								Field[] subFields = clazz.getDeclaredFields();
								for (Field f : subFields){
									String fName = f.getName();
									String fType = f.getType().getName();
									String mName = "get"+fName.substring(0,1).toUpperCase()+fName.substring(1);
									Object fValue = clazz.getMethod(mName).invoke(fieldValue);
									if (CheckUtil.isNotNull(fValue)){
										//下面只对字符串类型或数字类型的属性值进行过滤
										if (fType.equals("java.lang.String")){
											criteria = criteria.add(Restrictions.like(fName,"%"+fValue+"%"));
										}else if(Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz)){
											criteria = criteria.add(Restrictions.eq(fName,fValue));
										}
									}
								}
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
							criteria = criteria.setResultTransformer(Criteria.ROOT_ENTITY);	//恢复默认的结果集状态
						}else {
							criteria = criteria.add(Restrictions.eq(fieldName,fieldValue));
						}
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		if (CheckUtil.isNotNull(pageBean)){
			criteria = criteria.setFirstResult(pageBean.getStart());
			criteria = criteria.setMaxResults(pageBean.getPageSize());
		}
		criteria.addOrder(Order.desc("id"));

		return criteria.list();
	}

	/**
	 * 批量删除实体
	 * @param clazz
	 * @param ids	要删除实体的id集合
	 * @return
	 */
	public int delete(Class<T> clazz,Set<Integer> ids){
		StringBuffer hql = new StringBuffer("delete ");
		String className = clazz.getName();
		className = className.substring(className.lastIndexOf(".")+1);
		hql.append(className);
		if (ids.size()>0){
			hql.append(" where id in(");
			for (Integer id : ids){
				hql.append(id+",");
			}
			hql.append(")");
		}
		return this.getCurrentSession().createQuery(StringUtil.replaceLast(hql.toString(),",","")).executeUpdate();
	}

	public T getEntityByField(Class<T> clazz,String fieldName,String fieldValue) {
		return (T) this.getCurrentSession().createCriteria(clazz).add(Restrictions.eq(fieldName,fieldValue)).uniqueResult();
	}

	public void updateOneFieldOfEntity(Class<T> c, String fieldName, Object fieldValue, int id) {
		String hql = "update "+c.getSimpleName()+" set "+fieldName+" = ?1 where id = ?2";
		this.getCurrentSession().createQuery(hql).setString("1",fieldValue.toString()).setInteger("2",id).executeUpdate();
	}

	public Object merge(T o) {
		return this.getCurrentSession().merge(o);
	}
}
