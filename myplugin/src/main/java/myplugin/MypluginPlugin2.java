package myplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MypluginPlugin2 implements Plugin<Project> {//必须实现Plugin接口

    public void apply(Project project) {
        // Register a task
        project.getTasks().register("myhelloworld", task -> {
            task.doLast(s -> System.out.println("Hello from plugin 'myplugin.helloworld'"));
        });
    }
}
