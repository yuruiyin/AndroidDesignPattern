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
    1）判断是使用CPU绘制还是GPU绘制；<br>
    2）获取绘制表面Surface对象；<br>
    3）通过Surface对象获取并且锁住Canvas绘图对象；<br>
    4）从DecorView开始发起整棵视图树的绘制流程；<br>
    5）Surface对象解锁Canvas，并且通知SurfaceFlinger更新视图。<br>

### 3. 原型模式

### 4. 工作方法模式

### 5. 抽象工厂模式

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