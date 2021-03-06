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
<b>优点</b><br>
分离接口与实现。<br>
<b>缺点</b><br>
（1）类文件的爆炸性增加；<br>
（2）不太容易扩展新的产品类，因为每当我们增加一个产品类就需要修改抽象工厂，那么所有的具体工厂类都需要被修改。

### 6. 策略模式
#### 定义
策略模式定义了一系列的算法，并将每一个算法封装起来，而且使它们还可以相互替换。

#### 使用场景
（1）针对同一类型问题的多种处理方式，仅仅是具体行为有差别时；<br>
（2）需要安全地封装多种同一类型的操作时； <br>
（3）出现同一抽象类有多个子类，而又需要使用if-else或者switch-case选择具体子类时。<br>

#### 如何创建策略模式
大致思路就是：抽象出一个Strategy的接口，然后不同的算法对应不同的具体Strategy类，实现了Strategy接口。
同时有一个Context角色，起到一个桥接的作用，基本包括setStrategy方法和算法对应的方法。如下所示：<br>
```java
public interface CalculateStrategy {
    int calculatePrice(int km);
}
```
```java
public class SubwayStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        // 具体算法实现
    }
}
```
```java
public class BusStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        // 具体算法实现
    }
}
```
```java
public class TrafficCalculator {

    private CalculateStrategy mStrategy;

    public void setStrategy(CalculateStrategy strategy) {
        this.mStrategy = strategy;
    }

    public int calculatePrice(int km) {
        return mStrategy.calculatePrice(km);
    }

    public static void main(String[] args) {
        TrafficCalculator trafficCalculator = new TrafficCalculator();
        // 设置计算价格策略为公交策略
        trafficCalculator.setStrategy(new BusStrategy());
        System.out.println("公交车乘16公里的价格是： " + trafficCalculator.calculatePrice(16));
        // 设置计算价格策略为地铁策略
        trafficCalculator.setStrategy(new SubwayStrategy());
        System.out.println("地铁乘16公里的价格是： " + trafficCalculator.calculatePrice(16));
    }

}
```

#### android 源码中的策略模式实现
动画中的插值器其实就是策略模式的体现，动画中存在着不同的插值器，包括线性插值器（LinearInterpolator），用于匀速动画；
加速减速插值器(AccelerateDecelerateInterpolator)用于先加速后减速动画；减速插值器（DecelerateInterpolator）用于减速动画。

##### 插值器-Interpolator
它的作用是根据时间流逝的百分比计算出当前属性值改变的百分比。

##### 类型估值器-TypeEvaluator
它的作用是根据当前属性改变的百分比来计算改变后的属性值。

#### 深入属性动画
##### 属性动画体系的总体设计
Animator通过PropertyValuesHolder来更新对象的目标属性，如果用户没有设置目标属性的Property对象，那么会通过反射的形式调用
目标属性的setter方法来更新属性值；否则，通过Property的set方法来设置属性值。这个属性值则通过KeyFrameSet的计算得到，而KeyFrameSet
又是通过时间插值器和类型估值器来计算，在动画执行过程中不断地计算当前时刻目标属性的值，然后更新属性值来达到动画效果。

#### 小结
策略模式主要用于分离算法，在相同的行为抽象下有不同的具体实现策略。这个模式很好的演示了开闭原则（OCP），也就是定义抽象，注入不同的实现，从而达到很好的可扩展性。

### 7. 状态模式
#### 定义
对一个对象的内在状态改变时云讯改变其行为，这个对象看起来像是改变了其类。状态模式中的行为是由状态来决定的，不同的状态有不同的行为。
状态模式和策略模式的机构几乎完全一样。但他们的目的、本质却完全不一样。状态模式的意图是让一个对象在其内部状态发生改变的时候，其行为也随之改变。

#### 使用场景
（1）一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为。<br>
（2）代码中包含大量与对象状态有关的条件语句。例如，一个操作中含有庞大的多分支语句（if-else或switch-case），且这些分支依赖于该对象的状态。

#### 如何使用
参考代码: https://github.com/yuruiyin/AndroidDesignPattern/tree/master/designpattern/src/main/java/com/yuruiyin/designpattern/state

#### WIFI管理中的状态模式
在不同状态下对于扫描WIFI这个请求的处理是完全不一样的。在初始状态下扫描请求被直接忽略，在驱动加载中状态下WIFI扫描请求被添加到延迟处理的消息队列中，
在驱动加载完状态下扫描WIFI的请求直接被处理。不同状态下改变了扫描WIFI请求的行为，这就是状态模式的精髓所在。它的实现原理就是将请求的处理封装到状态类中，
在不同的状态类中对同一请求进行不同的处理。它能够消除一些重复的if-else逻辑，使得程序的结构更为清晰，可扩展性、稳定性也有一定的提高。

#### 状态模式实战
用户登录系统。比如微博在用户未登录的情况下点击评论、转发按钮，此时会先让用户登录，然后再执行转发评论操作。如果是已登录的情况下，
那么用户可以直接执行转发操作。

#### 小结
状态模式的核心就是使用多态来实现if-else。

### 8. 责任链模式
#### 定义
使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。将这些对象连成一个链，并沿着这条链传递该请求，直到有对象处理它为止。
将请求的发起者和处理者解耦。

#### 简单实现
<b>抽象领导类</b>
```java
public abstract class Leader {

    protected Leader nextHandler;

    public final void handleRequest(int money) {
        if (money <= limit()) {
            handle(money);
        } else {
            if (null != nextHandler) {
                nextHandler.handleRequest(money);
            }
        }
    }

    /**
     * 自身能批复的额度
     * @return 额度
     */
    public abstract int limit();

    /**
     * 处理报账行为
     * @param money 具体金额
     */
    public abstract void handle(int money);

}
```
<b>各个具体的领导类</b>
```java
public class GroupLeader extends Leader {

    @Override
    public int limit() {
        return 1000;
    }

    @Override
    public void handle(int money) {
        System.out.println("组长批复报销： " + money + "元");
    }
}

public class Director extends Leader {
    @Override
    public int limit() {
        return 5000;
    }

    @Override
    public void handle(int money) {
        System.out.println("主管批复报销： " + money + "元");
    }
}

public class Manager extends Leader {
    @Override
    public int limit() {
        return 10000;
    }

    @Override
    public void handle(int money) {
        System.out.println("经理批复报销： " + money + "元");
    }
}

public class Boss extends Leader {
    @Override
    public int limit() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void handle(int money) {
        System.out.println("老板批复报销： " + money + "元");
    }
}
```

<b>客户类</b>
```java
public class Client {

    public static void main(String[] args) {
        // 构造各个领导对象
        GroupLeader groupLeader = new GroupLeader();
        Director director = new Director();
        Manager manager = new Manager();
        Boss boss = new Boss();

        groupLeader.nextHandler = director;
        director.nextHandler = manager;
        manager.nextHandler = boss;

        groupLeader.handleRequest(20000);
    }

}
```
说明：对于责任链的一个处理者对象，其只有两个行为，一个是处理请求，一个是将请求转送给下一个节点，不允许某个处理者对象在处理的请求之后又
将请求转给下一个节点的情况。<br>
<b>纯的责任链</b><br>
请求最终被某个处理对象所处理；<br>
<b>不纯的责任链</b><br>
请求最终未被任何处理对象所处理；<br>

#### Android源码中的责任链模式
（1）view的事件分发。ViewGroup事件投递的递归调用就类似于一条责任链，一旦其寻找到责任者（某个view），那么将由责任者持有并消费掉这次事件。
具体地体现在View的onTouchEvent方法中返回值的设置，如果onTouchEvent返回false，那么意味着当前View不会是该次事件的责任人，将不会
对其持有；如果为true则相反，此时view会持有该事件并不再向外传递。<br>
（2）有序广播。有序广播是根据优先级依次传播的，直到有接收者将其终止或所有接收者都不终止它。

#### 小结
优点：可以将请求者和处理者进行解耦，提高代码的灵活性。<br>
缺点：对链中请求处理者的遍历，如果处理太多，那么遍历必定会影响性能，特别是一些递归调用中，要慎重。


### 9. 解释器模式
#### 定义
给定一个语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示来解释语言中的句子。
#### 使用场景
（1）如果某个简单的语言需要解释执行而且可以将该语言中的语句表示为一个抽象语法树时，可以考虑使用解释器模式。
比如一个简单的含有加减法运算的数学表达式：p+q+m+n <br>
（2）在某些特定的领域出现不断重复的问题时，可以将该领域的问题转化为一种语法规则下的语句，然后构建解释器来解释该语句。

#### Android源码中的解释器实现
用于解析AndroidManifest.xml文件的PackageParser这个类。

#### 小结
##### 优点
灵活的扩展性，当我们想对文法规则进行扩展时，只需要增加响应的非终结符解释器，并在构建抽象语法树时，使用到新增的解释器对象进行具体的解释即可，非常方便。

##### 缺点
对于每一条文法都可以对应至少一个解释器，其会生成大量的类，导致后期维护困难；同时，对于过于复杂的文法，构建其抽象语法树会显得异常繁琐，
甚至有可能会出现需要构建多棵抽象语法树的情况，因此，对于复杂的文法并不推荐使用解释器模式。

### 10. 命令模式
#### 定义
将一个请求封装成一个对象，从而让用户使用不同的请求把客户端参数化；对请求排队或者记录请求日志，以及支持可撤销的操作。

#### 使用场景
（1）需要抽象出待执行的动作，然后以参数的形式提供出来--类似于过程设计中的回调机制，而命令模式正是回调机制的一个面向对象的替代品。<br>
（2）在不同的时刻指定、排列和执行请求。一个命令对象可以有与初始请求无关的生存期。<br>
（3）需要支持取消操作；<br>
（4）支持修改日志功能，这样当系统崩溃时，这些修改可以被重做一遍；<br>
（5）需要支持事务操作。<br>
（6）需要支持undo、redo操作。

#### 命令模式的角色
（1）Receiver：接受者角色。即执行具体逻辑的角色。<br>
（2）Command：命令角色。即定义所有具体命令类的抽象接口。<br>
（3）ConcreteCommand：具体命令角色。<br>
（4）Invoker：请求者角色。即调用命令对象执行具体的请求。<br>
（5）Client：客户端角色。比如人。<br>
总之, 命令角色的存在是为了让接收者和请求者之间进行解耦。

#### 如何使用
参考代码: https://github.com/yuruiyin/AndroidDesignPattern/tree/master/designpattern/src/main/java/com/yuruiyin/designpattern/command

#### Android源码中的命令模式实现
android的事件机制中底层逻辑对事件的转发处理。接收者就是具体硬件驱动，命令者就是NotifyArgs，请求者就是InputDispatcher。

#### 小结
在命令模式中，其充分体现了几乎所有设计模式的通病，就是类的膨胀，大量衍生类的创建，这是一个不可避免的问题，但是，其给我们带来的好处也非常多，
更弱的耦合性、更灵活的控制性以及更好的扩展性。

### 11. 观察者模式
使用java util中的Observable抽象类，里面的notifyObservers是逆序调用observers通知更新的。
#### 定义
观察者模式是一个使用频率非常高的模式，它常用的地方就是GUI系统、订阅-发布系统。定义对象间一种一对多的依赖关系，使得每当一个对象改变
状态，则所有依赖于它的对象都会得到通知并被自动更新。目的依然是为了解耦。

#### 使用场景
（1）关联行为场景，需要注意的是，关联行为是可拆分的，而不是“组合”关系；<br> 
（2）事件多级触发场景；<br>
（3）跨系统的消息交换场景，如消息队列、事件总线的处理机制。

#### 观察者模式的角色介绍
（1）Subject：抽象主题，也就是被观察者（Observable）的角色；<br>
（2）ConcreteSubject：具体主题. <br>
（3）Observer：抽象观察者；<br>
（4）ConcreteObserver：具体观察者。

#### 观察者模式的简单实现
```java
public class Coder implements Observer {

    private String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("Hi, " + name + ", 开发技术前线更新啦， 内容：" + arg);
    }
}

public class DevTechFrontier extends Observable {

    public void postNewPublication(String content) {
        // 标识状态或内容发生改变
        setChanged();
        // 通知所有观察者
        notifyObservers(content);
    }

    /**
     * 批量添加观察者
     * @param observers 观察者列表
     */
    public void addObservers(Observer ...observers) {
        for (Observer observer : observers) {
            addObserver(observer);
        }
    }

}

public class Test {

    public static void main(String[] args) {
        // 被观察者
        DevTechFrontier devTechFrontier = new DevTechFrontier();
        // 若干观察者
        Coder coder1 = new Coder("coder1");
        Coder coder2 = new Coder("coder2");
        Coder coder3 = new Coder("coder3");

        // 将观察者注册到被观察者的观察者列表中
        devTechFrontier.addObservers(coder1, coder2, coder3);

        // 发布消息
        devTechFrontier.postNewPublication("新的一期开发技术前线周报发布啦！");
    }

}
```
#### Android源码中的观察者模式--RecyclerView
RecyclerView和ListView的Adapter都使用到了观察者模式。这里拿RecyclerView来分析: <br>
（1）首先，在RecyclerView实例化的时候就会创建一个Observer，如下：
```java
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild {
    // 省略其它代码
    private final RecyclerViewDataObserver mObserver = new RecyclerViewDataObserver();
    // 省略其它代码
}

```
（2）接着，当我们new RecyclerView.Adapter的时候，就会创建一个Observable，如下：
```java
public abstract static class Adapter<VH extends ViewHolder> {
    private final AdapterDataObservable mObservable = new AdapterDataObservable();
    // 省略其它代码
}
```
（3）此时，已经有了Observer和Observable，那是什么时候将Observer注册进Observable中呢？答案是当setAdapter的时候，如下：
```java
    public void setAdapter(Adapter adapter) {
        // bail out if layout is frozen
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);  // 这个方法
        requestLayout();
    }

    private void setAdapterInternal(Adapter adapter, boolean compatibleWithPrevious,
            boolean removeAndRecycleViews) {
        // 省略其它代码
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);  // 这里执行了注册操作
            adapter.onAttachedToRecyclerView(this);
        }
        // 省略其它代码
    }
    
    // 然后看Adapter的registerAdapterDataObserver方法
    public abstract static class Adapter<VH extends ViewHolder> {
        // 省略其它代码
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            mObservable.registerObserver(observer); 
        }
        // 省略其它代码
    }
    
    // 最后看看Observable这个类，里面有一个ArrayList来存储Observers，以及实现了注册和解注册方法
    public abstract class Observable<T> {
        // 观察者列表
        protected final ArrayList<T> mObservers = new ArrayList<T>();
    
        /**
         * 注册观察者，即将观察者添加到观察者列表中
         */
        public void registerObserver(T observer) {
            if (observer == null) {
                throw new IllegalArgumentException("The observer is null.");
            }
            synchronized(mObservers) {
                if (mObservers.contains(observer)) {
                    throw new IllegalStateException("Observer " + observer + " is already registered.");
                }
                mObservers.add(observer);
            }
        }
    
        /**
         * 解注册观察者，即将观察者从观察者列表中移除
         */
        public void unregisterObserver(T observer) {
            if (observer == null) {
                throw new IllegalArgumentException("The observer is null.");
            }
            synchronized(mObservers) {
                int index = mObservers.indexOf(observer);
                if (index == -1) {
                    throw new IllegalStateException("Observer " + observer + " was not registered.");
                }
                mObservers.remove(index);
            }
        }
    
        /**
         * 移除所有的观察者
         */
        public void unregisterAll() {
            synchronized(mObservers) {
                mObservers.clear();
            }
        }
    }

```
（4）通过以上三步，就成功地将观察者注册到被观察者中。接下来看看观察者模式的更新流程。
```java
    public abstract static class Adapter<VH extends ViewHolder> {
        // 省略其它代码
        public final void notifyDataSetChanged() {
            mObservable.notifyChanged();
        }
        // 省略其它代码
    }
    
    // 我们来看看Observable的notifyChanged()方法
    static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public void notifyChanged() {
            // 通知所有的观察者更新，这里正常情况下，应该只会存在一个观察者
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                // 以下的onChanged方法，实际上调用的是RecyclerViewDataObserver的onChange方法
                mObservers.get(i).onChanged();
            }
        }
    }
    
    // 我们再来看看RecyclerViewDataObserver的onChange方法，里头调用了requestLayout重新布局RecyclerView。
    private class RecyclerViewDataObserver extends AdapterDataObserver {
        // 省略其它代码
        @Override
        public void onChanged() {
            assertNotInLayoutOrScroll(null);
            mState.mStructureChanged = true;

            setDataSetChangedAfterLayout();
            if (!mAdapterHelper.hasPendingUpdates()) {
                requestLayout(); // 请求对RecyclerView重新布局，更新用户界面
            }
        }
        // 省略其它代码
    }
```
至此，RecyclerView中使用观察者模式进行数据更新的流程基本完毕，具体的细节还是需要去细细读一下源码。

#### Android源码中的观察者模式--BroadcastReceiver
（1）广播的发送和接收都是以ActivityManagerService为中心的。<br>
（2）简单来说，广播就是一个订阅--发布的过程，也就是观察者模式，通过一些map存储BroadcastReceiver，key就是封装了这些广播的信息类，
如Action之类的。当发布一个广播时通过AMS到这个map中查询注册了这个广播的IntentFilter的BroadcastReceiver，然后通过
ReceiverDispatcher将广播分发给各个订阅对象，从而完成这个发布--订阅过程。

#### 事件总线实现组件之间的通信
（1）相比使用广播接收器实现Activity、Fragment、service之间的数据传递，通过事件总线的方法可以简化操作，并降低组件之间的耦合。<br>
（2）一般都包括一个tag和一个mode，tag就是一个action，mode就是该订阅函数的执行线程。<br>
（3）总线也需要register和unregister的过程，因为总线的生命周期是全局的，跟Application一样，它持有了Activity,就必须
在Activity销毁的时候unregister掉，否则会造成Activity对象被总线对象引用而无法被GC回收，而造成Activity的内存泄漏。

#### 其它
（1）通过广播接收器发送的实体类必须实现序列化接口。

#### 小结
总之，观察者模式天生就是为了解耦而存在的，将观察者和被观察者隔离。可以非常灵活的应对业务变化。

##### 缺点
在java中消息的通知默认是顺序执行，一个观察者卡顿，会影响整体的执行效率，在这种情况下，一般考虑采用异步的方式。


### 12. 备忘录模式
#### 定义
备忘录模式是一种行为模式，该模式用于保存对象当前状态，并且在之后可以再次恢复到此状态，这有点像我们平时说的“后悔药”。
在不破坏封闭的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样，以后就可将该对象恢复到原先保存的状态。

#### 使用场景
（1）需要保存一个对象的某一个时刻的状态或部分状态。<br>
（2）如果用一个接口来让其他对象得到这些状态，将会暴露对象的实现细节并破坏对象的封装性，一个对象不希望外界直接访问其内部状态，
通过中间对象可以间接访问其内部状态。

#### 备忘录模式的角色介绍
（1）Originator：负责创建一个备忘录，可以记录、恢复自身的内部状态; <br>
（2）Memento: 备忘录角色，用于存储Originator的内部状态，并且防止Originator以外的对象访问Memonto; <br>
（3）Caretaker: 负责存储备忘录，不能对备忘录的内容进行操作和访问，只能够将备忘录传递给其它对象。

#### Android源码中的备忘录模式
Activity中的状态保存，也就是onSaveInstanceState和onRestoreInstanceState。

##### onSaveInstanceState
onSaveInstanceState主要分成以下三个步骤：<br>
（1）存储窗口的视图树的状态(saveHierarchyState), 主要存储了与当前UI、ActionBar相关的View状态，而View中的saveHierarchyState
实际上调用的是dispatchSaveInstanceState来存储自身的状态。ViewGroup则是遍历子View调用子View的dispatchSaveInstanceState。
不过，若View中没有设置id时，这个View的状态是不会被存储的到Bundle中的；<br>
（2）存储Fragment的状态； <br>
（3）调用Activity的ActivityLifecycleCallbacks的onSaveInstanceState函数进行状态存储。 <br>

onSaveInstanceState是在onStop之前执行的。<br>
##### Bundle存储在哪？
存储在ActivityClientRecord对象的state字段中。

##### onSaveInstanceState的调用时机
笼统的讲，就是当Activity变得容易被系统销毁时，该Activity的onSaveInstanceState函数就会被执行。用户主动按Back键返回时，是不会调用的。<br>
具体的时机如下：<br>
（1）当用户按下home键回到桌面时；<br>
（2）按menu键准备切换程序时；<br>
（3）按下电源键关闭屏幕时；<br>
（4）从Activity A跳转到Activity B; <br>
（5）屏幕方向切换，如从竖屏切换到横屏；<br>
（6）电话打入等情况发生时；<br>

#### 实现了undo/redo功能的EditText
参考代码: https://github.com/yuruiyin/AndroidDesignPattern/tree/master/app/src/main/java/com/yuruiyin/androiddesignpattern/memonto

#### 小结
备忘录模式是在不破坏封装的条件下，通过备忘录对象（Memonto）存储另一个对象内部状态的快照，在将来合适的时候把这个对象还原到存储起来的状态。


### 13. 迭代器模式
#### 定义
提供一种方法顺序访问一个容器对象中的各个元素，而又不需要暴露该对象的内部表示。

#### 使用场景
遍历一个容器对象时。

#### 简单实现
```java
// 迭代器接口
public interface Iterator<T> {

    /**
     * 是否还有下一个元素
     * @return true表示有，false表示没有
     */
    boolean hasNext();

    /**
     * 返回当前位置的元素并将位置后移
     * @return 当前位置的元素
     */
    T next();

}

// 具体迭代器类
public class ConcreteIterator<T> implements Iterator<T> {

    private List<T> mList = new ArrayList<T>();
    private int cursor = 0;

    public ConcreteIterator(List<T> list) {
        mList = list;
    }

    @Override
    public boolean hasNext() {
        return cursor != mList.size();
    }

    @Override
    public T next() {
        T obj = null;
        if (this.hasNext()) {
            obj = this.mList.get(cursor++);
        }
        return obj;
    }
}

// 容器接口
public interface Aggregate<T> {

    /**
     * 添加一个元素
     * @param obj 元素
     */
    void add(T obj);

    /**
     * 删除一个元素
     * @param obj 元素
     */
    void remove(T obj);

    /**
     * 获取容器的迭代器
      * @return 迭代器
     */
    Iterator<T> iterator();
}

// 具体容器类
public class ConcreteAggregate<T> implements Aggregate<T> {
    private List<T> mList = new ArrayList<>();

    @Override
    public void add(T obj) {
        mList.add(obj);
    }

    @Override
    public void remove(T obj) {
        mList.remove(obj);
    }

    @Override
    public Iterator<T> iterator() {
        return new ConcreteIterator<>(mList);
    }
}

// 客户端测试类
public class Client {

    public static void main(String[] args) {
        Aggregate<String> aggregate = new ConcreteAggregate<>();

        aggregate.add("Android");
        aggregate.add("design");
        aggregate.add("pattern");

        Iterator<String> iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
```

#### Android源码中的迭代器模式
（1）各种数据结构，如List，Map，Set等都包含迭代器。<br>
（2）android的sqlite数据库中的cursor其实也是一个迭代器。<br>

#### 小结
迭代器模式发展至今，几乎每一种高级语言都有相应的内置实现，几乎不需要开发者去自己实现迭代器了。

### 14. 模板方法模式
#### 定义
定义一个操作中的算法的框架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

#### 使用场景
（1）多个子类有公有的方法时，并且逻辑基本相同时；<br>
（2）重要、复杂的算法，可以把核心算法设计为模板方法，周边的相关细节功能则由各个子类实现；<br>
（3）重构时，模板方法模式是一个经常使用的模式，把相同的代码抽取到父类中，然后通过钩子函数约束其行为。

#### 角色介绍
（1）AbsTemplate: 抽象类，定义一套算法框架；<br>
（2）ConcreteImplA：具体实现类A；<br>
（3）ConcreteImplB：具体实现类B；<br>

#### 代码实现
https://github.com/yuruiyin/AndroidDesignPattern/tree/master/designpattern/src/main/java/com/yuruiyin/designpattern/template

#### Android源码中的模板方法模式-AsyncTask
AsyncTask中的execute方法里面顺序执行onPreExecute --> doInBackground --> onPostExecute。而且execute为final方法，不可被重写。
相当于算法框架是固定的。但是用户可以根据自己的需要重写onPreExecute，doInBackground和onPostExecute。

#### Android源码中的模板方法模式--Activity的生命周期函数
（1）我们知道，在Android系统启动时，第一个启动的进程是zygote进程，然后由zygote启动SystemServer，再后就是启动ActivityManagerService、
WindowManagerService等系统核心服务，这些服务承载着整个android系统与客户端程序交互的重担。zygote除了启动系统服务与进程之外，
普通的用户进程也是由zygote进程fork而来，当一个应用进程启动起来后，就会加载用户在AndroidManifest.xml中配置的默认加载的Activity，
此时加载的入口是ActivityThread的main方法，这个main方法就是整个应用程序的入口。<br>
（2）在调用了ActivityThread的main方法之后，会一次执行Activity的onCreate、onStart和onResume方法。这就是典型的模板方法。<br>
（3）渲染视图的工作是由ActivityManagerService完成的。<br>
（4）用户的布局视图是添加到decor view的content view 区域的。decor view包括title区域和content区域。<br>
（5）ViewGroup是继承于View，其实是视图的容器。界面的视图树即可通过ViewGroup和View来构建，view是叶子节点，ViewGroup是非叶子节点。<br>

#### 小结
模板方法模式用4个字概括就是：流程封装。也就是把某个固定的流程封装到一个final函数中，并且让子类能够定制这个流程中的某些或者所有步骤，
这就要求父类提取共用的代码，提升代码的复用率，同时也带来了更好的可扩展性。<br>

##### 优点
（1）封装不可变部分，扩展可变部分;<br>
（2）提取公共部分代码，便于维护。

##### 缺点
模板方法会带来代码阅读的难度，会让用户觉得难以理解。


### 15. 访问者模式
据说是最复杂的一种设计模式，其实也不错如此。。真的，别被吓到了。。其实不难。
#### 基本思想
软件系统中拥有一个由许多对象构成的、比较稳定的对象结构，这些对象的类都拥有一个accept方法用来接收访问者对象的访问。访问者是一个接口，
它拥有一个visit方法，这个方法对访问到的对象结构中不同类型的元素做出不同的处理。在对象结构的一次访问过程中，我们遍历整个对象结构，
对每一个元素都实施accept方法，在每一个元素的accept方法中会调用访问者的visit方法，从而使访问者得以处理对象结构的每一个元素，
我们可以针对对象结构设计不同的访问者类来完成不同的操作，达到区别对待的效果。

#### 定义
封装一些作用于某种数据结构中的各元素的操作，它可以在不改变这个数据结构的前提下定义作用于这些元素的新的操作。

#### 使用场景
（1）对象结构比较稳定，但经常需要在此对象结构上定义新的操作（增加新的visitor执行新的visit方法，即将对象属性组合起来）;<br>
（2）需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免这些操作“污染”这些对象的类，也不希望在增加新操作时修改这些类。<br>

#### 角色介绍
（1）Visitor：接口或抽象类，它定义了对每个元素(Element)访问的行为，方法的参数就是可以访问的元素，它的方法个数理论上与元素个数是一样的，
因此，访问者模式要求元素的类族要稳定，如果经常添加、移除元素类，必然会导致频繁地修改Visitor接口。如果出现这种情况，则说明不适合使用访问者模式。<br>
（2）ConcreteVisitor：具体的访问者，它需要给出对每一个元素类访问时所产生的具体行为。<br>
（3）Element：元素接口或者抽象类，它定义了一个接受访问者访问的方法（accept）,其意义是指每一个元素都要可以被访问者访问。<br>
（4）ElementA、ElementB: 具体的元素类，它提供接受访问方法的具体实现，而这个具体的实现，通常情况下是使用访问者提供的访问该元素类的方法。<br>
（5）ObjectStructure: 定义当中所提到的对象结构，对象结构是一个抽象表述，它内部管理了元素集合，并且可以迭代这些元素供访问者访问。

#### Android源码中的访问者模式
（1）注解分为两种类型：一种是运行时注解，一种是编译期注解。运行时注解有性能问题。<br>
（2）著名的ButterKnife、Dagger、Retrofit等开源库使用注释都是基于APT(Annotaion Processing Tools)，是一种编译期注解。<br>
（3）编译期注解就使用到了访问者模式。<br>
（4）编译期注解会在编译时对源代码进行处理，产生新的类。这样也会影响编译的效率。

#### 编译器注解
具体过程如下：<br>
（1）通过ViewInject注解标识一些View成员变量；<br>
（2）通过ViewInjectProcessor捕获添加了ViewInject注解的元素，并且按照宿主类进行分类；<br>
（3）为每个含有ViewInject注解的宿主类生成一个InjectAdapter辅助类，并且在它的inject函数中生成初始化View的代码；<br>
（4）在SimpleDagger的inject函数中构建生成辅助类，此时内部会调用这个InjectAdapter辅助类对象的inject函数，这个函数中又会初始化
宿主类中的view成员变量，至此，View就已经被初始化了。

#### 小结
##### 优点
（1）各角色职责分离，符合单一职责原则；<br>
（2）具有优秀的扩展性；<br>
（3）使得数据结构和作用于结构上的操作解耦，使得操作集合可以独立变化；<br>
（4）灵活性。
#### 缺点
（1）具体元素对访问者公布细节，违反了迪米特原则；<br>
（2）具体元素变更时导致修改成本大；<br>
（3）违反了依赖倒置原则，为了达到“区别对待”而依赖了具体类，没有依赖抽象。


### 16. 中介者模式
#### 定义
中介者模式将多对多的相互作用转化为一对多的相互作用。

#### 使用场景
当对象之间的交互操作很多且每个对象的行为操作都依赖彼此时，为防止在修改一个对象的行为时，同时涉及修改很多其他对象的行为，
可采用中介者模式来解决紧耦合问题。

#### 角色介绍
（1）Mediator: 抽象中介者角色；<br>
（2）ConcreteMediator：具体中介者角色，它从具体的同事对象接收消息，向具体同事对象发出命令；<br>
（3）Colleague：抽象同事类角色，

#### android源码中的中介者模式
（1）Keyguard锁屏的功能实现是一种中介者模式，有一个KeyguardViewMediator,很多XXXManager管理器就是中介者模式中的同事类；<br>
（2）Binder机制也是使用到了中介者模式。

#### 小结
在面向对象的编程语言中，一个类必然会与其他类产生依赖关系，如果这种依赖关系如网状般错综复杂，那么必然会影响我们的代码逻辑以及执行效率，
适当地使用中介者模式可以对这种依赖关系进行解耦使逻辑结构清晰，但是，如果几个类之间的依赖关系并不复杂，使用中介者模式反而会使得
原本不复杂的逻辑结构变得复杂。

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
《Android源码设计模式解析与实战》(第2版) 何红辉 关爱民 <br>
https://book.douban.com/subject/30199128/