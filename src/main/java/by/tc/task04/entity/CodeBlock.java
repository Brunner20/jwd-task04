package by.tc.task04.entity;

import java.util.Objects;

public class CodeBlock implements Component{

    private static final long SerialVersionUID  = 3845L;
    private String code;

    public CodeBlock() {
    }

    public CodeBlock(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeBlock codeBlock = (CodeBlock) o;
        return code.equals(codeBlock.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "CodeBlock{" +
                "code='" + code + '\'' +
                '}';
    }
}
