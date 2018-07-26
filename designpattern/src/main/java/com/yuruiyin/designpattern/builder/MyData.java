package com.yuruiyin.designpattern.builder;

/**
 * <p>Title: Builder设计模式</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/26
 */
public class MyData {

    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name;
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public MyData create() {
            MyData myData = new MyData();
            myData.setId(this.id);
            myData.setName(this.name);
            return myData;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MyData.Builder().setId(1).setName("yuruiyin").create().toString());
    }

}
