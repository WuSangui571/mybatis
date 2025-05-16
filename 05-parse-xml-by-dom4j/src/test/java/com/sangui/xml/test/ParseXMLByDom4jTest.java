package com.sangui.xml.test;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;


import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-16
 * @Description: 使用 dom4j 转化 XML 文件
 * @Version: 1.0
 */
public class ParseXMLByDom4jTest {
    @Test
    public void testParseSqlMapperXML() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(ParseXMLByDom4jTest.class.getClassLoader().getResource("CarMapper.xml"));
        Element mapper = document.getRootElement();
        String mapperNamespace = mapper.attributeValue("namespace");
//        String xPath = "//select";
//        List<Node> nodes = document.selectNodes(xPath);
//        for (Node node : nodes) {
//            Element insert = (Element) node;
//            String insertId = insert.attributeValue("id");
//            System.out.println(insertId);
//            String insertSQL = insert.getText();
//            System.out.println(insertSQL);
//        }
        List<Element> mapperInner = mapper.elements();
        for (Element mapperInnerElement : mapperInner) {
            String id = mapperInnerElement.attributeValue("id");
            String resultType = mapperInnerElement.attributeValue("resultType");
            String sql = mapperInnerElement.getText();
            String newSql = sql.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println(newSql);
            System.out.println(id);
            System.out.println(resultType);
        }
    }

    @Test
    public void TestParseMyBatisConfigXML() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(ParseXMLByDom4jTest.class.getClassLoader().getResource("mybatis-config.xml"));
        Element rootElement = document.getRootElement();

        // 获取default默认的环境id
        String xPath = "/configuration/environments";
        Element environments = (Element) document.selectSingleNode(xPath);
        String defaultEnvironmentId = environments.attributeValue("default");

        // 获取具体的environment
        xPath = "/configuration/environments/environment[@id='" + defaultEnvironmentId + "']";
        Element environment = (Element) document.selectSingleNode(xPath);

        // 获取 environment 节点下的 transactionManager 标签
        Element transactionManager = environment.element("transactionManager");
        String transactionManagerType = transactionManager.attributeValue("type");

        // 获取 environment 节点下的 transactionManager 标签
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");

        List<Element> propertyList = dataSource.elements("property");
        for (Element property : propertyList) {
            String propertyName = property.attributeValue("name");
            String propertyValue = property.attributeValue("value");
            System.out.println(propertyName + "=" + propertyValue);
        }

        // 获取所有的mapper标签（法一）
//        xPath = "/configuration/mappers"; // 这里不同
//        Element mappers = (Element) document.selectSingleNode(xPath);
//        List<Element> mapperList = mappers.elements();
//        for (Element mapper : mapperList) {
//            String mapperResource = mapper.attributeValue("resource");
//            System.out.println(mapperResource);
//        }
        // 获取所有的mapper标签（法二）
        xPath = "//mapper"; // 这里不同
        List<Node> nodes = document.selectNodes(xPath);
        for (Node node : nodes) {
            Element mapper = (Element) node;
            String mapperResource = mapper.attributeValue("resource");
            System.out.println(mapperResource);
        }
    }
}
