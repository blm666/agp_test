hello Android Gradle Plugin
创建一个plugin比较方便的方法是在目标目录下执行
```shell
gradlew init
```
执行下面的命令发布组件到repo目录下
```shell
./gradlew publishMyhelloPublicationToMavenRepository
```

总结：
Maven上存储的都是通用意义上的Artifact，GroupId+ArtifactId+Version唯一确定一个构件（或者说是jar包），这个jar包中有多个class文件。
存储gradle插件的jar包只是Maven中构件的一种。
一个jar包可能包含一到多个gradle插件。
gradle中依赖插件时要通过PluginId指定插件，而非类名或者ArtfactId。
那怎么知道一个PluginId对应的主类（实现Plugin接口的那个类）是哪个呢？
这个并不是存储在Maven仓库的pom.xml中，而是存储在jar文件的META-INF中：在gradle-plugins目录下以`[`PluginId`]`.properties命令的文件中会有
```text
implementation-class=myplugin.MypluginPlugin
```
这样的字段，这就是该PluginId对应的实现类。也就是说一个`[`PluginId`]`.properties就记录了一个PluginId与实现类的对应关系。实际上一个jar中可能有多个`[`PluginId`]`.properties，这样这个jar中就有多个gradle插件。

也就是说唯一确定一个Plugin的方式是：maven仓库->GroupId->ArtifactId->Version->PluginId
其中添加maven仓库的方式是在项目的build.gradle中做如下配置：
```groovy
buildscript {
    repositories {
        maven {
            url = "$rootDir/repo"
        }
    }
}
```
指定GroupId和ArtifactId和Version的方式是在项目的build.gradle中做如下配置：
```groovy
buildscript {
    dependencies {
        classpath 'com.myexample.plugin:hello:0.0.2'//注意这里有两个冒号，形式为GROUPID:ARTIFACTID:VERSION
    }
}
```
指定PluginId的方式是在项目的build.gradle中做如下配置：
```groovy
apply plugin:'myplugin.greeting'//这里的id是plugin的id，而不是dependencies里面classpath的名字
```