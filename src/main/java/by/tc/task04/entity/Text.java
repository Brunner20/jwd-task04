package by.tc.task04.entity;

import java.util.List;
import java.util.Objects;

public class Text implements  Component{

    private static final long SerialVersionUID  = 75389845159L;
    private List<Component> text;

    public Text(List<Component> text) {
        this.text = text;
    }

    public List<Component> getText() {
        return text;
    }

    public void setText(List<Component> text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text1 = (Text) o;
        return Objects.equals(text, text1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Text{" +
                "text=" + text +
                '}';
    }
}
