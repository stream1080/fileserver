package src;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.util.HashMap;

/**
 * @author stream
 * @since 2021/11/3 23:00
 */
public class clientTest {
    public static void main(String[] args) {
        // 文件上传测试
        uploadFile("D:\\file\\2021\\11\\01\\3e004830-d26a-44e3-8794-9de9247f1a66.jpg");

        // 文件信息测试
        testGetInfo("8bee928e-f2ea-4037-94c6-8b73139c69da.jpg");

        // 文件下载测试
        testDownLoad("8bee928e-f2ea-4037-94c6-8b73139c69da.jpg");

    }

    public static void uploadFile(String fileName) {
        HashMap<String, Object> paramMap = new HashMap<>();
        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        paramMap.put("uploadFile", FileUtil.file(fileName));
        String result= HttpUtil.post("http://localhost:9001/msg/upload", paramMap);
        System.out.println(result);
    }

    public static void testDownLoad(String name){
        HashMap<String, Object> paramMap = new HashMap();
        paramMap.put("name",name);
        String result= HttpUtil.get("http://localhost:9001/msg/download", paramMap);
        File folder = new File(System.getProperty("user.dir")+"\\Server\\src\\main\\resources\\downFile\\");
        //是否目录
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        System.out.println(result);
    }

    public static void testGetInfo(String name){
        HashMap<String, Object> paramMap = new HashMap();
        paramMap.put("name",name);
        String result= HttpUtil.get("http://localhost:9001/msg/info", paramMap);
        System.out.println(result);
    }
}
