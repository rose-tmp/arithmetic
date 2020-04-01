package designPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/10 - 14:46
 * @Description:开闭原则：对提供方的扩展开放，对适用方的修改关闭 是编程中最基础，最重要的设计原则
 * 当我们想绘制三角形的时候需要改动的地方较多 (在需要改动的地方有阿拉伯数字标记)
 * 使用方GraphEditor也修改了代码 不满足开闭原则
 * OcpPlus类是其改进
 */
//绘制图形的类(使用方)
class GraphEditor{
    public void draw(Shape shape){
        if(shape.type == "rectangle")
            System.out.println("绘制矩形");
        if(shape.type == "circle")
            System.out.println("绘制圆形");

        //2
        if(shape.type == "triangle")
            System.out.println("绘制三角形");
    }
}

//基类
class Shape{
    String type;
}
class Rectangle extends Shape{
    Rectangle(){
        super.type = "rectangle";
    }
}
class Circle extends Shape{
    Circle(){
        super.type = "circle";
    }
}

// 1
class Triangle extends Shape{
    Triangle(){
        super.type = "triangle";
    }
}
public class Ocp {
    public static void main(String[] args) {
        GraphEditor graph = new GraphEditor();
        graph.draw(new Rectangle());
        //3
        graph.draw(new Triangle());
    }
}

