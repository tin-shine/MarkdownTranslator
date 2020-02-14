# Markdown-HTML转义工具

## 项目结构

* 源文件

```shell
./com/tinshine/MarkdownTranslator/*.java
```

* 类文件

```shell
./classes/com/tinshine/MarkdownTranslator/*.class
```

* jar包

```shell
./output/MarkdownTranslator.jar
```

## 项目引用

### idea下导入jar包到webapp中：

1. 将jar包复制到WEB-INF下的lib目录中（若无lib目录则手动创建）
```shell
/src/main/webapp/WEB-INF/lib
```

2. 鼠标右键jar包，选择"Add as library..."并确认

### 在程序中使用：

项目有一个入口类：MainCompiler.class，其中的转换函数定义为：
```java
public String transform(String inputText);
```

* 用例：
```java
// 导入类
import com.tinshine.MarkdownTranslator.MainCompiler;

// 从session中获取jsp页面提交的数据
String inputText = request.getParameter("content");

// 转换
String outputText = new MainCompiler().transform(inputText);
```
此时获得的``outputText``为``html``格式的字符串，可将其直接放置在``textarea``标签中显示。