package com.yuruiyin.designpattern.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/25
 */
public class WordDocument implements Cloneable {
    // 文本
    private String mText;
    // 图片名列表
    private ArrayList<String> mImages = new ArrayList<>();

    public WordDocument() {
        System.out.println("---------------- WordDocument构造函数 ----------------");
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void addImage(String imageName) {
        mImages.add(imageName);
    }

    public void showDocument() {
        System.out.println("---------------- Word Content Start ----------------");
        System.out.println("Text: " + mText);
        System.out.println("Images List: ");
        for (String imgName: mImages) {
            System.out.println("image name: " + imgName);
        }
        System.out.println("---------------- Word Content End ----------------");
    }

    @Override
    protected WordDocument clone() {
        try {
            WordDocument document = (WordDocument) super.clone();
            document.mText = this.mText;
//            document.mImages = this.mImages; // 浅拷贝。 这样话，会导致拷贝出来的文档对象的图片对象和原型文档对象中的图片对象是同一对象
            document.mImages = (ArrayList<String>) this.mImages.clone(); // 深拷贝。这样，就相当于拷贝了一份图片对象。
            return document;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
