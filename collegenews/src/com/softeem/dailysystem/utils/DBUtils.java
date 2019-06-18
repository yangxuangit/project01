package com.softeem.dailysystem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * ͨ�����ݿ⹤���࣬����Druid���ӳ�ʵ��
 * �������¹���:
 * 1. ��ȡ����
 * 2. �ر���Դ
 * 3. ִ��ͨ�õĸ��²���
 * 4. ִ��ͨ�õĲ�ѯ�б����
 * 5. ִ��ͨ�õĲ�ѯ������¼����
 * @author mrchai
 *
 */
public class DBUtils {
	
		//����druid���ӳض���
		private static DruidDataSource pool;
		/**���ݿ�����URL��ַ**/
		private static String url;
		/**�˺�**/
		private static String username;
		/**����**/
		private static String password;
		/**��ʼ������**/
		private static int initialSize;
		/**���������**/
		private static int maxActive;
		/**��С����������**/
		private static int minIdle;
		/**���Ӻľ�ʱ���ȴ���ȡ����ʱ��**/
		private static long maxWait;
		
		static{
			init();
		}
		
		/**
		 * ���������ļ�����ȡ�����ļ��е����ݽ������ø�������Ϣ
		 * @param propName
		 */
		private static void loadProp(){
			try {
				//�����ļ�λ��src��Ŀ¼ʱ���ӡ�/����Ҫʹ��ClassLoader�����ʹ��ClassLoader�����衰/��
				InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
				Properties p = new Properties();
				p.load(is);
				
				url = p.getProperty("jdbc.url");
				username = p.getProperty("jdbc.username");
				password = p.getProperty("jdbc.password");
				
				initialSize = Integer.parseInt(p.getProperty("initialSize").trim());
				maxActive = Integer.parseInt(p.getProperty("maxActive").trim());
				minIdle = Integer.parseInt(p.getProperty("minIdle").trim());
				maxWait = Long.parseLong(p.getProperty("maxWait").trim());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * ��ʼ�����ӳ�
		 */
		private static void init(){
			pool = new DruidDataSource();
			//���������ļ���ʼ������
			loadProp();
			
			pool.setUrl(url);
			pool.setUsername(username);
			pool.setPassword(password);
			//�������ӳ��г�ʼ������
			pool.setInitialSize(initialSize);
			//�������������
			pool.setMaxActive(maxActive);
			//������С����������
			pool.setMinIdle(minIdle);
			//�������ĵȴ�ʱ��(�ȴ���ȡ���ӵ�ʱ��)
			pool.setMaxWait(maxWait);
		}
		
		/**
		 * ���ӻ�ȡ
		 * @return
		 */
		public static Connection getConn(){
			try {
				//������ӳ�Ϊ�ջ��߱��쳣�رգ������³�ʼ��һ��
				if(pool == null || pool.isClosed()){
					init();
				}
				return pool.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	/**
	 * ��Դ�ر�
	 * 
	 * @param stmt
	 * @param conn
	 */
	public static void close(Statement stmt, Connection conn) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��װͨ�õĸ��²����������и���(INSERT,UPDATE��DELETE)�йصĲ�������ͨ���÷���ʵ��
	 * 
	 * @param sql
	 * @return
	 * 
	 * 		insert into daily(id,user,dept,content,bak) values(?,?,?,?,?)
	 *         delete from daily where id=? update daily set content=? where
	 *         id=?
	 */
	public static boolean exeUpdate(Connection conn,String sql, Object... obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, null);
		}
		return false;
	}

	/**
	 * ��������: ���ͣ����Ͽ�ܣ����䣬JDBC ��װͨ�ò�ѯ����������
	 * 
	 * @param t
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryList(Class<T> t, String sql, Object... params) {
		List<T> list = new ArrayList<>();
		T obj = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			 rs = ps.executeQuery();
			// ��ȡ���������е�Ԫ����(��ȡ�����ͣ������Լ����ȵ���Ϣ)
			ResultSetMetaData rsmd = rs.getMetaData();
			// ����һ��map���ϣ�������ʱ�洢��ѯ����һ�����ݣ�key��������value����ֵ��
			Map<String, Object> map = new HashMap<>();
			// ���������
			while (rs.next()) {
				// ��ֹ������һ������
				map.clear();
				// �������е���
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					// ��ȡ����
					String cname = rsmd.getColumnLabel(i + 1);
					//��ȡ�����͵�int��ʾ��ʽ���Լ�����������
//					System.out.println("������:"+rsmd.getColumnType(i + 1)+"----"+rsmd.getColumnTypeName(i+1));
					// ��ȡ��ֵ
					Object value = rs.getObject(cname);
					// ����������ֵ�洢��map��
					map.put(cname, value);
				}
				// ���÷��佫map�е�����ע�뵽Java�����У�����������뼯��
				if (!map.isEmpty()) {
					// ��ȡmap���ϼ���(��������)
					Set<String> columnNames = map.keySet();
					// ��������
					obj = t.newInstance();//new Student() //java.lang.Object
//					System.out.println("-------"+obj);
					for (String column : columnNames) {
						// ���ݼ���ȡֵ
						Object value = map.get(column);
						//�����ݶ���Ϊ��ʱ����ע�����ݵ�������
						if(Objects.nonNull(value)){	
							// ��ȡ���Զ���
							Field f = t.getDeclaredField(column);
							// ��������Ϊ�ɷ���״̬
							f.setAccessible(true);
							// Ϊ��������
							f.set(obj, value);
						}
					}
					list.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
					DBUtils.close(ps, conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ��װ��ѯ��������ķ���
	 * 
	 * @param t
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryOne(Class<T> t, String sql, Object... params) {
		T obj = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			//ORM�����������ϵӳ�䣩
			if (rs.next()) {
				// ����һ��ָ�����͵�ʵ������(�������Ĭ�Ϲ�����)
				obj = t.newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					//��ȡָ���е�������
					String cname = rsmd.getColumnLabel(i + 1);
					//��ȡ��ֵ
					Object value = rs.getObject(cname);
					if(Objects.nonNull(value)){						
						//���������ƻ�ȡJava���������(Ҫ����е������Ʊ��������е�����������һ��)
						Field field = t.getDeclaredField(cname);
						//���ֶ�����Ϊ�ɷ���״̬
						field.setAccessible(true);
						//Ϊ�ֶ���������ֵ
						field.set(obj, value);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
					DBUtils.close(ps, conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

//	public static void main(String[] args) {
//		System.out.println(getConn());
//	}
	
}
