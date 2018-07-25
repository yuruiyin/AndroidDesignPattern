package com.yuruiyin.designpattern.prototype;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/25
 */
public class Client {

    public static void main(String[] args) {
        // 1. 构建文档对象
        WordDocument originDoc = new WordDocument();
        // 2. 编辑文档，添加图片等
        originDoc.setText("这是一篇文档");
        originDoc.addImage("图片1");
        originDoc.addImage("图片2");
        originDoc.addImage("图片3");
        originDoc.showDocument();

        // 以原始文档为原型。拷贝一份副本
        WordDocument newDoc = originDoc.clone();
        newDoc.showDocument();
        // 修改文档副本，不会影响原始文档
        newDoc.setText("这是修改后的文档");
        newDoc.addImage("新增的图片4");
        newDoc.showDocument();

        originDoc.showDocument();
    }

}
