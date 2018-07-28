package com.dbsoft.whjd.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
	
	/**
	 * 将信息持久化到数据库中
	 * @param t
	 * @return
	 */
	public Serializable save(T t);
	
	/**
	 * 删除一个对象
	 * @param t 传入待删除的对象
	 */
	public void delete(T t);
	
	/**
	 * 更新一个对象
	 * @param t 传入待更新的对象
	 */
	public void update(T t);
	
	//public void commit();
	//2018-2-8添加
	//public void flush();
	
	/**
	 * 更新或者插入一个对象
	 * @param t 对象
	 */
	public void saveOrUpdate(T t);
	
	/**
	 * 获取一个对象，当查询语句没有参数的时候可以使用
	 * @param hql 查询语句
	 * @return
	 */
	public T get(String hql);
	
	/**
	 * 获得一个对象
	 * 
	 * @param c 对象类型
	 * @param id 对象编号
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);
	
	/**
	 * 获取一个对象，Hibernate4不推荐使用
	 * @param hql 查询语句
	 * @param params 参数数组
	 * @return
	 */
	public T get(String hql, Object[] params);
	
	/**
	 * 获取一个对象
	 * @param hql 查询语句
	 * @param params 参数Map列表
	 * @return
	 */
	public T get(String hql, Map<String, Object> params);
	
	/**
	 * 查询
	 * @param hql 查询语句
	 * @return 以列表的形式返回查询结果
	 */
	public List<T> find(String hql);
	
	/**
	 * 查询
	 * @param hql 查询语句
	 * @param params 参数列表
	 * @return 以列表的形式返回查询结果
	 */
	public List<T> find(String hql, Map<String, Object> params);
	
	/**
	 * 不传参翻页
	 * @param hql 查询语句
	 * @param page 要查询第几页的数据
	 * @param rows 该页显示多少条数据
	 * @return 以列表的形式返回查询结果
	 */
	public List<T> find(String hql, int page, int rows);
	
	/**
	 * 分页查询
	 * @param hql 查询语句
	 * @param params 参数列表
	 * @param page 要查询第几页的数据
	 * @param rows 该页显示多少条数据
	 * @return 以列表的形式返回查询结果
	 */
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);
	
	/**
	 * 不带参查询记录条数
	 * @param hql 查询语句
	 * @return
	 */
	public Long count(String hql);
	
	/**
	 * 查询记录条数
	 * @param hql 查询语句
	 * @param params 参数列表
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);
	
}
