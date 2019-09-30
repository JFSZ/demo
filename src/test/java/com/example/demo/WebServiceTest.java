package com.example.demo;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.junit.Test;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * @description: webservice 调用远程接口测试
 * 1、AXIS调用远程的web service
 * 2、SOAP调用远程的webservice
 * 3、使用eclipse生成客户端.idea类同
 * @author: chenxue
 * @create: 2019-09-26 16:24
 **/
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class WebServiceTest {

    @Test
    public void axisTest(){
        try {
            String endpoint = "http://www.webxml.com.cn/WebServices/RandomFontsWebService.asmx?wsdl";
            Service service = new Service();
            Call _call = (Call)service.createCall();
            _call.setTargetEndpointAddress(endpoint);
            _call.setOperationName(new QName("http://WebXml.com.cn/","getCharFonts"));
            _call.addParameter("byProvinceName", XMLType.XSD_INTEGER, ParameterMode.IN);
            _call.setSOAPActionURI("http://WebXml.com.cn/getCharFonts");
            _call.setUseSOAPAction(true);
            _call.setReturnType(XMLType.XSD_STRING);
            String[] result = (String[])_call.invoke(new Object[]{"byFontsLength"}); //反射远程调用
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
