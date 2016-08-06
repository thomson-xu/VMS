/**
 * 
 */
package com.author.system.dao.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import oracle.sql.BLOB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.author.base.Sql;
import com.author.system.bean.TestBlob;
import com.author.system.dao.BlobTestDao;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-2-11 下午5:29:10
 * @version v1.0
 *
 */
@Repository
public class BlobTestDaoImpl implements BlobTestDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	protected DataSource dataSource;
	/* (non-Javadoc)
	 * @see com.author.system.dao.BlobTestDao#save(com.author.system.bean.TestBlob)
	 */
	@Override
	@Transactional
	public TestBlob save(TestBlob bean){
		// TODO Auto-generated method stub
		//this.entityManager.get
		//Session session = entityManager.unwrap(org.hibernate.Session.class);
		//Blob blob = bean.getTextControl();
		String str = "Hello world";
		bean.setTextControl(str.getBytes());
		//this.entityManager.getTransaction().begin();
		this.entityManager.persist(bean);
		//this.entityManager.getTransaction().commit();
		
		
		return bean;
	}
	
	public void updateBlob(String id,String blob){
		Sql sql = new Sql("SELECT TEXT_CONTROL FROM TEST_BLOB WHERE ID = ?");
		Sql update = new Sql("UPDATE TEST_BLOB SET TEXT_CONTROL = ? WHERE ID = ?");
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = this.dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql.getSql());
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				BLOB b = (BLOB) rs.getBlob("TEXT_CONTROL");
				System.out.println(b);
				OutputStream out = b.getBinaryOutputStream();
				out.write(blob.getBytes());
				out.close();
				//out.write(b)
				ps = conn.prepareStatement(update.getSql());
				ps.setBlob(1, b);
				ps.setString(2, id);
				
				ps.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
