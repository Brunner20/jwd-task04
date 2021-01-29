package by.tc.task04.entity;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {

    private static final long SerialVersionUID  = 1532L;
    private RequestType request;
    private Object param;
    private Object param2;

    public Request(RequestType request){
        this.request=request;
    }
    public Request(RequestType request,Object param){
        this.request=request;
        this.param=param;
    }

    public Request(RequestType request, Object param, Object param2) {
        this.request = request;
        this.param = param;
        this.param2 = param2;
    }

    public RequestType getRequest() {
        return request;
    }

    public void setRequest(RequestType request) {
        this.request = request;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Object getParam2() {
        return param2;
    }

    public void setParam2(Object param2) {
        this.param2 = param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request1 = (Request) o;
        return request == request1.request && Objects.equals(param, request1.param) && Objects.equals(param2, request1.param2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, param, param2);
    }

    @Override
    public String toString() {
        return "Request{" +
                "request=" + request +
                ", param=" + param +
                ", param2=" + param2 +
                '}';
    }
}
