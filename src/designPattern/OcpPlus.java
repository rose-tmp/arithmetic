package designPattern;

/**
 * @author - ZwZ
 * @date - 2020/2/10 - 15:01
 * @Description:Ocp类的优化
 */
abstract class ShapePlus{
    String type;
    public abstract void draw();
}
class RectanglePlus extends ShapePlus{
    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}
class CirclePlus extends ShapePlus{
    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }
}

// 1
class TrianglePlus extends ShapePlus{
    @Override
    public void draw() {
        System.out.println("绘制三角形");
    }
}
/** 
* @Author: ZwZ
* @Description:使用方 当新增三角形的时候，只需要让提供方增加一个 TrianglePlus 类即可
 * 使用方的代码无需改变，即相对于Ocp 将业务逻辑代码(打印输出“绘制XXX”)交给了提供方，即每一个类
* @Param:  
* @return:  
* @Date: 2020/2/10-15:07
*/
class GraphEditorPlus{
    public void draw(ShapePlus shapePlus){
        shapePlus.draw();
    }
}
public class OcpPlus {
    public static void main(String[] args) {
        GraphEditorPlus graph = new GraphEditorPlus();
        graph.draw(new TrianglePlus());
    }
}
