package designPattern.prototypePattern;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 16:05
 * @Description:
 */
public class Friend implements Cloneable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    protected Friend clone() throws CloneNotSupportedException {
        return (Friend) super.clone();
    }
}
