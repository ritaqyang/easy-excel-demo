package com.rita;
import com.rita.data.*;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.rita.util.TestFileUtil;
import org.junit.jupiter.api.Test;
import java.util.*;

public class SimpleWriteTest {

    /**
     * First simple write uses a supplier for data
     * ideal for situations where data is not available in memory at once/ dynamically fetched
     * or needs to be lazily loaded
     * supplier provides data on demand, which is more memory efficient for large datasets
     */
    @Test
    public void simpleWriteUsingSupplier() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
        // 写法1 JDK8+
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, DemoData.class)
                .sheet("模板")
                .doWrite(() -> {
                    // 分页查询数据
                    return data();
                });
    }

    /**
     * Second simple write, directly passing the data
     *  Requires data to be available in memory at once. Simple and direct for small to moderate datasets.
     */
    @Test
    public void simpleWriteDirectPassData() {

        // 写法2
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }

    /**
     * Method: Manually constructs an ExcelWriter and WriteSheet and writes data using these objects.
     * Use Case: Useful for more control over the writing process or when dealing with complex Excel file generation scenarios.
     * Advantages: Provides greater flexibility and control. You can manage the Excel writing process more explicitly, handle multiple sheets, and more.
     * Behavior: Explicitly manages the ExcelWriter and WriteSheet objects, which allows for additional configuration and operations during the writing process.
     */
    @Test
    public void simpleWriteUsingExcelWriter() {
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        }
    }

    /**
     * 重复多次写入,使用模板
     */
    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("string" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


}
