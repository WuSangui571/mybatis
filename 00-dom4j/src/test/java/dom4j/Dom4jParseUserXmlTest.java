package dom4j;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-16
 * @Description: dom4j 解析 User
 * @Version: 1.0
 */
public class Dom4jParseUserXmlTest {
    @Test
    public void Test01() throws Exception {
        // 创建解析器对象
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(Dom4jParseUserXmlTest.class.getClassLoader().getResource("user.xml"));
        Element rootElement = document.getRootElement();
        System.out.println("user.xml根节点的名字：" + rootElement.getName());
        List<Element> usersSubElementList = rootElement.elements();
        for (Element usersSubElement : usersSubElementList) {
            System.out.println("users的子标签列表之一：" + usersSubElement.getName());
            System.out.println("users的子标签的id属性的值是：" + usersSubElement.attributeValue("id"));
            System.out.println("users的子标签的country属性的值是：" + usersSubElement.attributeValue("country"));
            List<Element> userSubElements = usersSubElement.elements();
            for (Element userSubElement : userSubElements) {
                System.out.println("user的子标签列表之一：" + userSubElement.getName() + ",对应的Value：" + userSubElement.getText());
            }
        }
        // 获取users标签的第一个user的标签
        Element firstUserElement = rootElement.element("user");
        // 第一个user的标签的password属性
        String password = firstUserElement.elementText("password");
        System.out.println("第一个user的标签的password属性：" + password);
    }
}
