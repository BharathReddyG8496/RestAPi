package com.bharath.rest.webservices.restfulwebservices.Helloworld;

public class HelloWorld {

    String message;

    public HelloWorld(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message +
                "]";
    }
}
