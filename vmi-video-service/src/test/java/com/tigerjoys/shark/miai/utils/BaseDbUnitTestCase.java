package com.tigerjoys.shark.miai.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.DatabaseTestCase;
import org.dbunit.IOperationListener;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.dataset.xml.XmlDataSetWriter;
import org.dbunit.operation.DatabaseOperation;
import org.h2.engine.Mode;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类的基础类
 * @author chengang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseDbUnitTestCase extends DatabaseTestCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DBTestCase.class);

    @Autowired
    private DataSource dataSource;
    
    // use one connection to reduce the overhead of creating a new connection
    // 1 在不同Test Class之间共享IDatabaseConnection
    private static IDatabaseConnection connection = null;
    
    private static final Object LOCK = new Object();
    
    @PostConstruct
    public void tweakH2CompatibilityMode() {
        Mode mode = Mode.getInstance("MYSQL");
        LOGGER.info("Setting convertInsertNullToZero to false");
        mode.convertInsertNullToZero = false;
    }
    
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("dbtest/dataset/empty.xml"));
    }
	
	/**
     * 在同一Test Class中不同TestMethod之间共享_connection
     *
     * @return 在setUp和tearDown后不释放连接
     */
    @Override
	protected IOperationListener getOperationListener() {
        return IOperationListener.NO_OP_OPERATION_LISTENER;
    }
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
        if (connection == null) {
            synchronized (LOCK) {
                if (connection == null) {
                    connection = new DatabaseDataSourceConnection(dataSource);
                }
            }
        }
        return connection;
    }
	
	/**
     * 获取dataset stream
     *
     * @param xmlFile dataset 定义的文件名称
     * @return stream
     */
    protected IDataSet getDataSet(String xmlFile) throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        return builder.build(new ClassPathResource(xmlFile).getURL());
    }
	
	@Before
	@Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /** 
     * 导出数据到指定文件 
     * @param xmlFileClassPath - 数据XML文件
     * @param tableName - 表名
     */
    protected void exportTable(String file, String tableName) {  
        QueryDataSet dataSet = new QueryDataSet(connection);
        
        File outputFile = new File(file);
        try {
			FileUtils.touch(outputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
        
        try(Writer writer = new FileWriter(file)) {
            dataSet.addTable(tableName);
            XmlDataSetWriter w = new XmlDataSetWriter(writer);
            w.setIncludeColumnComments(true);
            w.write(dataSet);
            writer.flush();
        } catch(Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    /** 
     * 验证file中包含的表中的数据和数据库中的相应表的数据是否一致
     * @param xmlFileClassPath - 数据XML文件
     */
    protected void verifyDataSet(String xmlFileClassPath) {
        try(InputStream in = new ClassPathResource(xmlFileClassPath).getInputStream()) {  
            IDataSet expected = new XmlDataSet(in);
            IDataSet dataset = connection.createDataSet();
            for (String tableName : expected.getTableNames()) {
                Assertion.assertEquals(expected.getTable(tableName), dataset.getTable(tableName));
            }
        } catch(Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    /** 
     * 导入数据到表
     * @param xmlFileClassPath - 数据XML文件
     * @param tableName - 表名
     */
    protected void importTable(String xmlFileClassPath) {
        try(InputStream in = new ClassPathResource(xmlFileClassPath).getInputStream()) {  
            IDataSet dataSet = new XmlDataSet(in);
            DatabaseOperation.INSERT.execute(connection, dataSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  
    }

}
