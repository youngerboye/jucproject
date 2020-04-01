package filestream;

import java.io.File;
import java.io.IOException;

public class TestStream {
    public static void main(String[] args) throws IOException {
        //创建file对象
        File file1 = new File("D:\\javaproject\\jucproject\\src\\filestream\\file\\score");
        System.out.println("是否是文件:"+file1.isFile());
        System.out.println("是否是文件夹:"+file1.isDirectory());

        File file2 = new File("D:\\javaproject\\jucproject\\src\\filestream\\file\\text.txt");
        if(!file2.exists()){
            file2.createNewFile();
        }
    }
}
