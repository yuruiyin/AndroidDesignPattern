# AndroidDesignPattern
针对《Android源码设计模式解析与实战》(第2版)这本书的的读书笔记

## 面向对象6种设计原则
### 1. 单一职责原则
一个类应该职责单一。
### 2. 开闭原则（open-close）
一个类应该对扩展是开放的，对修改是关闭的。
### 3. 里氏替换原则
也就是充分利用了面向对象的继承和多态特性。比如Button和TextView都继承了View，我们可以直接View view = findViewById(R.id.xxx)，来获取view（不管是Button还是TextView），可以直接使用到View的方法。
### 4. 依赖倒置原则
让一个类依赖于抽象而不是具体细节。
### 5. 接口隔离原则
一种定义：客户端不应该依赖它不需要的接口；另一种定义：类之间的依赖关系应该建立在最小的接口之上。就是为了类之间的解耦。
### 6. 迪米特原则
一个对象应该对其它要引用的对象有最少的了解。
### 小结：
在应用开发过程中，最难的不是完成应用的开发工作，而是在后续的升级、维护过程中让应用系统能够拥抱变化。拥抱变化也就意味着在满足需求且不破坏系统稳定性的前提下保持高可扩展性、高内聚、低耦合，在经历了个版本的变更之后依然保持清晰、灵活、稳定的系统架构。

## 23种设计模式
### 1. 单例模式
定义：确保每个类在内存中只有一个实例，减少了内存开销，特别是一个对象需要频繁创建、销毁时，而且创建或销毁时性能又无法优化。
存在多种实现方式：

#### (1) 饿汉单例模式;
```java
public class HungrySingleton {

    private static final HungrySingleton mInstance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return mInstance;
    }

}
```
#### (2) 懒汉单例模式；
```java
public class LazySingleton {

    private static LazySingleton mInstance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (mInstance == null) {
            mInstance = new LazySingleton();
        }
        return mInstance;
    }

}
```
  
#### (3) DCL(双重校验锁)单例模式；
```java
public class DoubleCheckLockSingleton {

    // volatile可以保证每次都从主内存去取对象实例，解决DCL失效问题
    private volatile static DoubleCheckLockSingleton mInstance;

    private DoubleCheckLockSingleton() {

    }

    /**
     * 第一个判空时为了减少不必要的同步，提高效率，因为mInstance不为null的时候可直接return那个实例。
     * 第二个判空是为了保证mInstance为null的时候才会创建实例。
     * 然而，mInstance = new DoubleCheckLockSingleton()虽然是一句代码，但是它并不是原子操作
     * 这句代码最终会被编译成多条汇编指令，它大致做了3件事情：
     * (1) 给Singleton的实例分配内存；
     * (2) 调用Singleton()的构造函数，初始化成员字段；
     * (3) 将mInstance对象指向分配的内存空间（此时mInstance就不等于null了）
     * 但是，上面第二和第三条指令的顺讯是无法保证的，因此若存在线程A和线程B，线程A已经执行了new操作之后，mInstance不为null了，
     * 此时，在第二条指令未执行的情况下，线程B直接取走了mInstance，再使用就会出错。
     * @return
     */
    public static DoubleCheckLockSingleton getInstance() {
        if (mInstance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (mInstance == null) {
                    mInstance = new DoubleCheckLockSingleton();
                }
            }
        }
        return mInstance;
    }

}
```
#### (4) 静态内部类单例模式；
```java
public class StaticInnerClassSingleton {

    private static class StaticInnerClassSingletonHolder {
        private static final StaticInnerClassSingleton mInstance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {

    }

    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClassSingletonHolder.mInstance;
    }

}
```
#### (5) 枚举单例模式；
```java
interface EnumSingleton {
    void doSomething();
}

public enum SingletonEnum implements EnumSingleton {

    INSTANCE {
        @Override
        public void doSomething() {
            System.out.println("枚举单例，doSomething");
        }
    };

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

}
```

#### (6) 容器单例模式；
在程序的初始。将多种单例类型注入到一个统一的管理类中，在使用时根据key获取对应类型的对象。
比如ContextImpl就是使用这种方式来获取对应的service，各种系统服务都会注册到ContextImpl的map容器中。然后需要获取服务的时候就通过map的key来获取。

LayoutInflater的实现类的是<b>PhoneLayoutInflater</b>。
##### inflate方法主要包括以下几步：
（1）解析xml中的根标签；<br>
（2）如果根标签是merge，那么调用rInflate进行解析，rInflate会将merge标签下的所有子view直接添加到根标签中；<br>
（3）如果标签是普通元素，则调用createViewFromTag对该元素进行解析；<br>
（4）调用rInflate解析temp根元素下的所有子View，并且将这些子view都添加到temp下；<br>
（5）返回解析到根视图。

##### createViewFromTag： 
就是用来解析单个标签元素，若tag中不包含.（譬如<TextView）则说明该标签是一个内置的view，那么就会调用PhoneLayoutInflater的onCreateView给View标签名前面加上“android.widget”前缀。然后再传递给createView进行解析。

##### createView：
如果有前缀，那么构造View的完整路径，并且将该类加载到虚拟机中，然后获取该类的构造函数并且缓存起来，再通过构造函数来创建该View的对象，最后将View对象返回，这就是解析单个VIew的过程。

##### rInflate：
通过深度优先遍历来构造视图树，每解析到一个View元素就会递归调用rInflate，直到这条路径下的最后一个元素，然后再回溯过来将每一个View元素添加到他们的parent中，通过rInflate的解析之后，整颗视图树就构建完毕，当调用了Activity的onResume之后，我们通过setContentView设置的内容就会出现在我们的视野之中。

Android-Universal-Image-Loader也是使用到（DCL）单例模式。

### 2. Builder模式
#### 定义：
将一个复杂对象的构建和表示分离，使得同样的构建过程可以创建不同的表示。同时，也是将配置从目标类中隔离出来，避免过多的setter方法。

#### 优点：
(1)良好的封装性，使用Builder模式可以使客户端不必关心产品内部组成的细节。<br>
(2)builder独立，易于扩展。

#### 使用场景
(1)相同的方法，不同的执行顺序，产生不同的事件结果时；<br>
(2)多个部件或零件，都可以装配到一个对象中，但是产生的运行结果又不相同时；<br>
(3)产品类非常复杂，或者产品类的调用顺序不同产生了不同的作用时；<br>
(4)当初始化一个对象特别复杂，如参数多，且很多参数都具有了默认值时。<br>

#### Android源码中的Builder模式
##### AlertDialog.Builder
<b>使用如下：</b> <br>
```java
public static void showAlertDialog(final Context context) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("title");
    builder.setMessage("message");
    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(context, "点击了确定按钮", Toast.LENGTH_SHORT).show();
        }
    });

    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
        }
    });

    builder.show();
}
```
<b>核心思想：</b> <br>
(1)首先，Builder是AlertDialog的一个静态内部类；<br>
(2)Builder类可以设置AlertDialog中的title、message、button等参数，这些参数都存储在类型为AlertParams的成员变量P中。<br>
在调用Builder类的create函数时会创建AlertDialog，并且将Builder成员变量P保存的参数应用到AlertDialog的mAlert对象中，即通过P.apply（dialog.mAlert）实现。<br>
(3)Builder中的每个setXXX方法都是会返回builder自身对象，这样可以实现链式调用，如上使用方式。如：
```java
public Builder setTitle(@Nullable CharSequence title) {
    P.mTitle = title;
    return this;
}
```
(4)AlertDialog通过onCreate函数中调用了AlertController的installContent方法，而installContent方法里头，使用setContentView设置了内容视图布局，类似activity。如下：
```java
public void installContent() {
    final int contentView = selectContentView();
    mDialog.setContentView(contentView);
    setupView();
}
```
(5)如上installContent中有一个setupView方法，该方法用来初始化AlertDialog布局中的各个部分，如title、message、title区域等。<br>

#### WindowManager
(1)所有需要显示到屏幕上的view最终是通过WindowManager来操作的。<br>
(2)Window对象通过setWindowManager方法将Window对象与WindowManager对象建立了联系。<br>
(3)ViewRootImpl继承自Handler，作为native层与java层View系统通信的桥梁; <br>
(4)Android Framework与WMS之间也是通过Binder机制进行通信；<br>
(5)视图的绘制过程包括测量（Measure）、布局(Layout)、绘制(Draw)，其中draw过程可以分为以下几个步骤：<br>
&nbsp;&nbsp; 1）判断是使用CPU绘制还是GPU绘制；<br>
&nbsp;&nbsp; 2）获取绘制表面Surface对象；<br>
&nbsp;&nbsp; 3）通过Surface对象获取并且锁住Canvas绘图对象；<br>
&nbsp;&nbsp; 4）从DecorView开始发起整棵视图树的绘制流程；<br>
&nbsp;&nbsp; 5）Surface对象解锁Canvas，并且通知SurfaceFlinger更新视图。<br>

### 3. 原型模式
#### 定义
用原型实例指定创建对象的种类，并通过复制这些原型创建新的对象。

#### 使用场景
（1）类初始化需要消耗非常多的资源，这个资源包括数据、硬件资源等，通过原型复制避免这些开销；<br>
（2）通过new产生一个对象需要非常繁琐的数据准备或访问权限，这时可以使用原型模式；<br>
（3）一个对象需要提供给其它对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式复制多个对象供调用者使用，即保护性拷贝。<br>
&nbsp;&nbsp; 需要注意的是，通过实现Cloneable接口的原型模式在调用clone函数构造实例的时候并不一定比通过new操作速度快，只有当通过new构造对象
较为耗时或者说成本较高时，通过clone方法才能获得效率上的提升。当然，实现原型模式也不一定要实现Cloneable接口。

#### 原型模式的三大角色
<b>Client</b>: 客户端用户<br>
<b>Prototype</b>: 抽象类或者接口，声明具备clone能力<br>
<b>ConcretePrototype</b>: 具体的原型类<br>

#### 原型模式的简单实现
```java
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
```
<b>注意：通过clone拷贝对象时并不会执行构造函数。</b>

#### Android源码中的原型模式实现
java中：ArrayList就是原型模式

##### Intent
（1）intent用于activity跳转、启动服务、发布广播等功能，它是android系统各组件之间的纽带，也是组件之间传递数据的载体。目的是让Android组件之间低耦合。<br>
（2）intent.clone()其实调用的是new Intent(this)，至于不用super.clone来实现对象拷贝的原因是由于构造intent对象的成本不是很高，因此直接new比clone更高效。<br>

#### Intent的查找组件的过程
大致思路如下: 在系统启动时PackageManagerService（PMS）会启动，此时PMS将解析所有已安装的应用的信息，构建一个信息表，
当用户通过Intent来跳转到某个组件时，会根据Intent中包含的信息到PMS中查找对应的组件列表，最后跳转到目标组件。

#### 小结
原型拷贝本质上是对象拷贝，有浅拷贝和深拷贝之分。有两个用处：<br>
1）解决构建复杂对象的资源消耗问题，能够在某些场景下提升创建对象的效率；<br>
2）保护性拷贝，也就是某个对象对外可能是只读的。<br>

### 4. 工作方法模式
#### 定义
定义一个用于创建对象的接口，让子类决定实例化哪个类。

#### 使用场景
在任何需要生成复杂对象的地方，都可以使用工厂方法模式。

#### 几种创建方法
（1）一个产品对应一个具体工厂类；<br>
（2）多个产品就一个具体工厂类，通过传入的class类型进行反射生产出具体的产品类。<br>

#### Android源码中的工厂方法模式
（1）ArrayList和HashSet中的iterator方法就相当于一个工厂方法。该方法返回一个具体的迭代器。<br>
（2）Activity的onCreate方法其实也是一个工厂方法，它会构造一个View对象，并设置为当前界面的ContentView返回给Framework层处理。

#### 关于onCreate方法
（1）其实我们的主Activity并非应用的程序入口，真正的入口应该是ActivityThread类。可以发现ActivityThread里头有我们熟悉的main函数。<br>
（2）ActivityThread是一个final类，是不能被继承的。<br>
（3）当Zygote进程孵化出一个新的应用进程后，会执行ActivityThread的main方法，其中main方法做了一些比较常规的逻辑，如准备Looper和消息队列，
然后调用ActivityThread的attach方法将其绑定到ActivityManagerService中，开始不断地读取消息队列中的消息并分发消息。<br>
（4）ActivityThread内部有一个继承与Handler的子类H，负责处理消息。比如处理LAUNCH_ACTIVITY的消息。<br>

### 5. 抽象工厂模式
#### 定义
围绕一个超级工厂创建其它工厂。

#### 使用场景
比如Q3和Q7具有不同的轮胎、发动机和制动系统。这种情况可以使用抽象工厂。

#### Android源码中的抽象工厂模式实现
比如MediaPlayer

#### 小结
<b>优点</b>
分离接口与实现。
<b>缺点</b>
（1）类文件的爆炸性增加；<br>
（2）不太容易扩展新的产品类，因为每当我们增加一个产品类就需要修改抽象工厂，那么所有的具体工厂类都需要被修改。

### 6. 策略模式


### 7. 状态模式

### 8. 责任链模式
android中的view的事件分发就用到该模式。

### 9. 解释器模式

### 10. 命令模式

### 11. 观察者模式

### 12. 备忘录模式

### 13. 迭代器模式

### 14. 模板方法模式

### 15. 访问者模式

### 16. 中介者模式

### 17. 代理模式

### 18. 组合模式

### 19. 适配器模式

### 20. 装饰模式

### 21. 享元模式

### 22. 外观模式

### 23. 桥接模式

## MVC/MVP/MVVM
### (1) MVC

### (2) MVP

### (3) MVVM


## 致谢
《Android源码设计模式解析与实战》(第2版) 何红辉 关爱民
https://book.douban.com/subject/30199128/