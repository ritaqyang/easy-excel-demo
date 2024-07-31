package com.rita;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson2.JSON;
import com.rita.data.PersonData;
import com.rita.util.TestFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.io.File;


@Slf4j
public class ReadTestPerson {

    @Test
    public void readPerson() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "person.xlsx";
        EasyExcel.read(fileName, PersonData.class, new PageReadListener<PersonData>(dataList -> {
            for (PersonData pData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(pData));
            }
        })).sheet().doRead();

    }
}